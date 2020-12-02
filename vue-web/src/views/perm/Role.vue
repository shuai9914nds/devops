<template>
  <div>
    <template>
      <div>
        <a-row>
          <a-col :span="21"></a-col>
          <a-col :span="3">
            <a-button type="primary" @click="showModal"> 新增角色</a-button>
          </a-col>
        </a-row>
        <a-modal v-model="visible" title="新增角色" on-ok="handleOk">
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
            <a-button key="back" @click="handleCancel"> 关闭</a-button>
            <a-button
              key="submit"
              type="primary"
              :loading="loading"
              @click="handleOk"
            >
              确认
            </a-button>
          </template>
          <template>
            <a-tree
              checkable
              :tree-data="treeData"
              :default-selected-keys="['0-0-0', '0-0-1']"
              :default-checked-keys="['0-0-0', '0-0-1']"
              @select="onSelect"
              @check="onCheck"
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
          <a-popconfirm
            v-if="data.length"
            @confirm="() => updateRole(record.roleId, '1')"
          >
            <a href="javascript:;">修改</a>
          </a-popconfirm>

          <a-divider type="vertical" />
        </span>
        <span>删除</span>
        <a-divider type="vertical" />
        <span v-if="record.isAvailable == true">停用</span>
        <span v-if="record.isAvailable == false">启用</span>
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
      visible: false,
      treeData: [],
      roleRelParam: {
        menuIds: [],
        roleName: "",
        roleId: null,
      },
    };
  },
  mounted() {
    this.getRolePage();
    this.getPerms();
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
    getPerms(params = {}) {
      this.$axios
        .get("/menu/all/tree", {
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
          console.log(this.treeData);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    showModal() {
      this.visible = true;
    },
    handleOk(e) {
      console.log("roleName：" + this.roleRelParam.roleName);
      console.log("menuIds：" + this.roleRelParam.menuIds);
      this.loading = true;
      this.addMenuRole();
      setTimeout(() => {
        this.visible = false;
        this.loading = false;
      }, 3000);
    },
    handleCancel(e) {
      this.visible = false;
    },
    onSelect(selectedKeys, info) {
      console.log("selected", selectedKeys, info);
    },
    onCheck(checkedKeys, info) {
      console.log("onCheck", checkedKeys, info);
      console.log(checkedKeys)
      this.roleRelParam.menuIds = checkedKeys;
    },
    addMenuRole() {
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
          this.selectUserPage();
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
