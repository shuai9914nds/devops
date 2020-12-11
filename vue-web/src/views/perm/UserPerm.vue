<template>
  <div>
    <a-form layout="inline">
      <a-form-item label="用户名称">
        <a-input v-model="name" />
      </a-form-item>

      <a-config-provider :auto-insert-space-in-button="false">
        <a-button type="primary" @click="selectUserRolePage"> 查询</a-button>
      </a-config-provider>
    </a-form>
    <template>
      <div>
        <a-modal v-model="visible" title="分配角色" on-ok="addHandle">
          <template slot="footer">
            <a-button key="back" @click="handleCancel"> 关闭</a-button>
            <a-button
              key="submit"
              type="primary"
              :loading="loading"
              @click="addHandle"
            >
              新增
            </a-button>
          </template>
          <a-table
            :columns="smapllColumns"
            :row-key="(record) => record.uid"
            :data-source="data1"
            :pagination="false"
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
                <a href="javascript:;" @click="showModal">删除</a>
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
    dataIndex: "roleList",
    scopedSlots: { customRender: "roleList" },
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
    };
  },
  mounted() {
    this.selectUserRolePage();
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
      this.data1[0] = data;
      console.log(this.data1[0]);
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
    handleChange(value) {
      console.log(`selected ${value}`);
    },
  },
};
</script>