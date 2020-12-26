<template>
  <div>
    <a-form layout="inline">
      <a-form-item label="用户名称">
        <a-input v-model="name" />
      </a-form-item>

      <a-com.devops.base.config-provider :auto-insert-space-in-button="false">
          <a-button type="primary" @click="selectUserRolePage"> 查询</a-button>
      </a-com.devops.base.config-provider>
    </a-form>
    <template>
      <div>
        <a-modal v-model="visible" title="分配角色" on-ok="addHandle">
          <div>
            <a-row>
              <a-col :span="4"> 添加角色：</a-col>
              <a-col :span="10">
                <a-select style="width: 200px" @change="handleChange">
                  <!-- <a-select style="width: 200px" @change="addUserRole"> -->
                  <a-select-option
                    v-for="item in roleList"
                    :key="item"
                    :value="item"
                  >
                    {{ item.roleName }}
                  </a-select-option>
                </a-select>
              </a-col>
              <a-col :span="2"></a-col>
              <a-col :span="3">
                <a-button type="primary" @click="addUserRole"> 添加</a-button>
              </a-col>
            </a-row>
          </div>
          <template slot="footer">
            <a-button key="back" @click="handleCancel"> 关闭</a-button>
          </template>
          <a-table
            :columns="smapllColumns"
            :row-key="(record) => record.uid"
            :data-source="data1"
            :pagination="false"
            :loading="loading"
            @change="handleTableChange"
          >
            <template slot="role" slot-scope="text, record">
              <span v-if="record != null">
                {{ record.role.roleName }}
              </span>
            </template>
            <template slot="action" slot-scope="text, record">
              <span>
                <a
                  href="javascript:;"
                  @click="deleteUserRole(record.uid, record.role)"
                  >删除</a
                >
              </span>
            </template>
          </a-table>
        </a-modal>
      </div>
    </template>
    <template>
      <a-table
        :columns="columns"
        :row-key="(record) => record.uid"
        :data-source="data"
        :pagination="pagination"
        :loading="loading"
        @change="handleTableChange"
      >
        <template slot="roleList" slot-scope="text, record">
          <span v-if="record.roleList != null">
            <span v-for="(item, i) in record.roleList" :key="i">
              {{ item.roleName }}
            </span>
          </span>
        </template>
        <template slot="action" slot-scope="text, record">
          <span>
            <a href="javascript:;" @click="showModal(record)">分配权限</a>
          </span>
        </template>
      </a-table>
    </template>
  </div>
</template>
<script>
const columns = [
  {
    title: "用户id",
    dataIndex: "uid",
  },
  {
    title: "用户名",
    dataIndex: "username",
  },
  {
    title: "姓名",
    dataIndex: "name",
  },
  {
    title: "角色",
    dataIndex: "roleList",
    scopedSlots: { customRender: "roleList" },
  },
  {
    title: "操作",
    dataIndex: "action",
    scopedSlots: { customRender: "action" },
  },
];

const smapllColumns = [
  {
    title: "姓名",
    dataIndex: "name",
  },
  {
    title: "角色",
    dataIndex: "role",
    scopedSlots: { customRender: "role" },
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
      data1: [],
      pagination: {},
      loading: false,
      columns,
      smapllColumns,
      name,
      collapsed: false,
      loading: false,
      visible: false,
      roleList: [],
      addRoleId: "",
      addUid: "",
    };
  },
  mounted() {
    this.selectUserRolePage();
    this.selectAllRoleList();
  },
  methods: {
    handleTableChange(pagination, filters, sorter) {
      console.log(pagination);
      const pager = { ...this.pagination };
      pager.current = pagination.current;
      this.pagination = pager;
      this.selectUserRolePage({
        size: pagination.pageSize,
        current: pagination.current,
        sortField: sorter.field,
        sortOrder: sorter.order,
        ...filters,
      });
    },
    selectUserRolePage(params = {}) {
      this.$axios
        .get("/user/role/page", {
          params: {
            size: 10,
            current: params.current === undefined ? 1 : params.current,
            name: this.name,
            orderBy: "state",
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
          pagination.pageSize = page.size;
          console.log(pagination);
          this.loading = false;
          this.pagination = pagination;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    onCellChange(key, dataIndex, value) {
      const dataSource = [...this.dataSource];
      const target = dataSource.find((item) => item.key === key);
      if (target) {
        target[dataIndex] = value;
        this.dataSource = dataSource;
      }
    },
    updateUserState(uid, state) {
      this.$axios
        .post(
          "/user/state",
          {
            uid: uid,
            state: state,
          },
          {
            headers: {
              // "content-type": "application/json",
              "User-Token": localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          this.selectUserRolePage();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    showModal(data) {
      this.visible = true;
      if (null != data) {
        const list = data.roleList;
        if (null != list && list.length > 0) {
          for (var i = 0; i < list.length; i++) {
            if (!this.data1[i]) {
              this.data1[i] = {};
            }
            this.data1[i].role = list[i];
            this.data1[i].name = data.name;
            this.data1[i].uid = data.uid;
          }
        }
      }
      this.addUid = data.uid;
      console.log(this.data1);
    },
    addHandle(e) {
      this.loading = true;
      setTimeout(() => {
        this.visible = false;
        this.loading = false;
      }, 3000);
    },
    handleCancel(e) {
      this.visible = false;
    },
    addUserRole() {
      this.$axios
        .put(
          "/user/role",
          {
            uid: this.addUid,
            roleId: this.addRoleId,
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
    deleteUserRole(paramUid, paramRole) {
      this.$axios
        .delete("/role", {
          params: {
            uid: paramUid,
            roleId: paramRole.roleId,
          },

          headers: {
            // "content-type": "application/json",
            "User-Token": localStorage.getItem("token"),
          },
        })
        .then((response) => {
          this.selectUserRolePage();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    selectAllRoleList() {
      this.$axios
        .get("/role/all/list", {
          params: {},
          headers: {
            "User-Token": localStorage.getItem("token"),
          },
        })
        .then((response) => {
          this.roleList = response.data.obj;
        })
        .catch((error) => {
          console.log(error);
        });
    },

    handleChange(data) {
      this.addRoleId = data.roleId;
    },
    filteredOptions() {
      return this.roleList.filter((o) => !this.selectedItems.includes(o));
    },
  },
};
</script>