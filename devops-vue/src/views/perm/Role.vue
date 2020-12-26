<template>
  <div>
    <template>
      <div>
        <a-row>
          <!-- <a-col :span="2"> </a-col> -->
          <a-col :span="8">
            <a-form layout="inline">
              <a-form-item label="角色名称">
                <a-input v-model="roleRelParam.roleName" />
              </a-form-item>

              <a-com.devops.base.config-provider :auto-insert-space-in-button="false">
                  <a-button type="primary" @click="getRolePage">
                      查询
                  </a-button>
              </a-com.devops.base.config-provider>
            </a-form>
          </a-col>
          <a-col :span="13"> </a-col>
          <a-col :span="3">
            <a-button type="primary" @click="addShowRole"> 新增角色</a-button>
          </a-col>
        </a-row>
        <a-modal v-model="addVisible" title="新增角色" on-ok="addHandleOk">
          <a-row>
            <a-col :span="1"></a-col>
            <a-col :span="15">
              <a-form layout="inline">
                <a-form-item label="角色名称">
                  <a-input v-model="roleRelParam.roleName" />
                </a-form-item>
              </a-form>
            </a-col>
          </a-row>
          <template slot="footer">
            <a-button key="back" @click="addHandleCancel"> 关闭</a-button>
            <a-button
              key="submit"
              type="primary"
              :loading="loading"
              @click="addHandleOk"
            >
              确认
            </a-button>
          </template>
          <template>
            <a-tree
              v-model="checkedKeys"
              checkable
              :tree-data="treeData"
              @select="addSelect"
              @check="addCheck"
            >
            </a-tree>
          </template>
        </a-modal>

        <a-modal
          v-model="updateVisible"
          title="修改角色"
          on-ok="updateHandleOk"
        >
          <a-row>
            <a-col :span="1"></a-col>
            <a-col :span="15">
              <a-form layout="inline">
                <a-form-item label="角色名称">
                  <a-input v-model="roleRelParam.roleName" />
                </a-form-item>
              </a-form>
            </a-col>
          </a-row>
          <template slot="footer">
            <a-button key="back" @click="updateHandleCancel"> 关闭</a-button>
            <a-button
              key="submit"
              type="primary"
              :loading="loading"
              @click="updateHandleOk"
            >
              确认
            </a-button>
          </template>
          <template>
            <a-tree
              v-model="checkedKeys"
              checkable
              :tree-data="treeData"
              @select="updateSelect"
              @check="updateCheck"
            >
            </a-tree>
          </template>
        </a-modal>
      </div>
    </template>
    <a-table
      :columns="columns"
      :row-key="(record) => record.uid"
      :data-source="data"
      :pagination="pagination"
      :loading="loading"
      @change="handleTableChange"
    >
      <template slot="isAvailable" slot-scope="text, record">
        <span v-if="record.isAvailable == true">是</span>
        <span v-if="record.isAvailable == false">否</span>
      </template>

      <template slot="action" slot-scope="text, record">
        <span>
          <a href="javascript:;" @click="updateShowRole(record.roleId)">修改</a>
        </span>
        <a-divider type="vertical" />
        <a-popconfirm
          v-if="data.length"
          title="是否删除这个角色?"
          ok-text="确定"
          cancel-text="取消"
          @confirm="deleteRole(record.roleId)"
        >
          <a href="javascript:;">删除</a>
        </a-popconfirm>
      </template>
    </a-table>
  </div>
</template>

<script>
const columns = [
  {
    title: "角色id",
    dataIndex: "roleId",
  },
  {
    title: "角色名称",
    dataIndex: "roleName",
  },
  {
    title: "是否可用",
    dataIndex: "isAvailable",
    scopedSlots: { customRender: "isAvailable" },
  },
  {
    title: "创建人",
    dataIndex: "createBy",
  },
  {
    title: "操作",
    dataIndex: "action",
    scopedSlots: { customRender: "action" },
  },
];

export default {
  data() {
    return {
      data: [],
      pagination: {},
      loading: false,
      columns,
      loading: false,
      addVisible: false,
      updateVisible: false,
      treeData: [],
      roleRelParam: {
        menuIds: [],
        roleName: "",
        roleId: null,
      },
      roleId: null,
      checkedKeys: [],
    };
  },
  mounted() {
    this.getRolePage();
  },
  methods: {
    handleTableChange(pagination, filters, sorter) {
      console.log(pagination);
      const pager = { ...this.pagination };
      pager.current = pagination.current;
      this.pagination = pager;
      this.getRolePage({
        size: pagination.pageSize,
        current: pagination.current,
        sortField: sorter.field,
        sortOrder: sorter.order,
        ...filters,
      });
    },
    getRolePage(params = {}) {
      this.$axios
        .get("/role/page", {
          params: {
            size: 10,
            current: params.current === undefined ? 1 : params.current,
            roleName: this.roleRelParam.roleName,
            ...params,
          },
          headers: {
            "User-Token": localStorage.getItem("token"),
          },
        })
        .then((response) => {
          const pagination = { ...this.pagination };
          const page = response.data.obj;
          this.data = response.data.obj.records;
          pagination.total = page.total;
          // pagination.size = page.size;
          pagination.pageSize = page.size;
          this.loading = false;
          this.pagination = pagination;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getPermTree() {
      this.$axios
        .get("/perm/all/tree", {
          params: {},
          headers: {
            "User-Token": localStorage.getItem("token"),
          },
        })
        .then((response) => {
          this.treeData = response.data.obj;
          const list = response.data.obj;
          if (list.length > 0) {
            for (var i = 0; i < list.length; i++) {
              this.treeData[i].key = list[i].menuId;
              this.treeData[i].title = list[i].menuName;
              if (list[i].children != null && list[i].children.length > 0) {
                this.treeData[i].children = list[i].children;
                const children = list[i].children;
                for (var j = 0; j < children.length; j++) {
                  this.treeData[i].children[j].key = children[j].menuId;
                  this.treeData[i].children[j].title = children[j].menuName;
                }
              }
            }
          }
          this.roleRelParam.roleName = "";
          console.log(this.treeData);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addShowRole() {
      this.getPermTree();
      this.checkedKeys = [];
      this.roleRelParam.roleName = "";
      this.roleRelParam.roleId = null;
      this.addVisible = true;
    },
    updateShowRole(roleId) {
      this.getRoleByRoleId(roleId);
      this.roleRelParam.roleId = roleId;
      this.updateVisible = true;
    },

    getRoleByRoleId(roleId) {
      this.getPermTree();
      this.$axios
        .get("/perm/map/" + roleId, {
          params: {},
          headers: {
            "User-Token": localStorage.getItem("token"),
          },
        })
        .then((response) => {
          const data = response.data.obj;
          if (data != null) {
            const role = data.role;
            if (role != null) {
              this.roleRelParam.roleName = role.roleName;
              this.roleRelParam.roleId = role.roleId;
            }
            this.checkedKeys = data.permIdList;
          }
        })
        .catch((error) => {
          console.log(error);
        });
      this.updateVisible = false;
    },
    addHandleOk(e) {
      console.log(e);
      this.loading = true;
      this.addRole();
      setTimeout(() => {
        this.addVisible = false;
        this.loading = false;
      }, 3000);
    },
    updateHandleOk(e) {
      this.loading = true;
      this.updateRole();
      setTimeout(() => {
        this.updateVisible = false;
        this.loading = false;
      }, 3000);
    },
    addHandleCancel(e) {
      this.addVisible = false;
    },
    updateHandleCancel(e) {
      this.updateVisible = false;
    },
    addSelect() {
      console.log("selected", this.checkedKeys);
    },
    addCheck(checkedKeys, info) {
      console.log("checked", this.checkedKeys);
      this.roleRelParam.menuIds = this.checkedKeys;
    },
    updateSelect() {
      console.log("selected", this.checkedKeys);
    },
    updateCheck(checkedKeys, info) {
      console.log("checked", this.checkedKeys);
      this.roleRelParam.menuIds = this.checkedKeys;
    },
    addRole() {
      this.$axios
        .put(
          "/menu/role",
          {
            roleName: this.roleRelParam.roleName,
            menuIds: this.roleRelParam.menuIds,
          },
          {
            headers: {
              // "content-type": "application/json",
              "User-Token": localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          this.getRolePage();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    updateRole() {
      this.$axios
        .post(
          "/menu/role",
          {
            roleName: this.roleRelParam.roleName,
            menuIds: this.checkedKeys,
            roleId: this.roleRelParam.roleId,
          },
          {
            headers: {
              // "content-type": "application/json",
              "User-Token": localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          this.getRolePage();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteRole(roleId) {
      this.getPermTree();
      this.$axios
        .delete("/role", {
          params: {
            roleId: roleId,
          },
          headers: {
            "User-Token": localStorage.getItem("token"),
          },
        })
        .then((response) => {
          this.getRolePage();
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
