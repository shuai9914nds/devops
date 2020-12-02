<template>
  <div>
    <a-form layout="inline">
      <a-form-item label="用户名称">
        <a-input v-model="name" />
      </a-form-item>

      <a-config-provider :auto-insert-space-in-button="false">
        <a-button type="primary" @click="selectUserPage"> 查询 </a-button>
      </a-config-provider>
    </a-form>

    <a-table
      :columns="columns"
      :row-key="(record) => record.uid"
      :data-source="data"
      :pagination="pagination"
      :loading="loading"
      @change="handleTableChange"
    >

     <template slot="roleList" slot-scope="text, record">
        <span v-if="record.roleList != null || record.roleList.length != 0">
          <span v-for="(item, i) in record.roleList" :key="i">
          {{item.roleName}}
          </span>
        </span>
      </template>
    </a-table>
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

export default {
  data() {
    return {
      data: [],
      pagination: {},
      loading: false,
      columns,
      name,
      collapsed: false,
    };
  },
  mounted() {
    this.selectUserPage();
  },
  methods: {
    handleTableChange(pagination, filters, sorter) {
      console.log(pagination);
      const pager = { ...this.pagination };
      pager.current = pagination.current;
      this.pagination = pager;
      this.selectUserPage({
        size: pagination.pageSize,
        current: pagination.current,
        sortField: sorter.field,
        sortOrder: sorter.order,
        ...filters,
      });
    },
    selectUserPage(params = {}) {
      this.$axios
        .get("/user/role/page", {
          params: {
            size: 10,
            current: params.current === undefined ? 1 : params.current,
            name: this.name,
            orderBy: 'state',
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
          this.selectUserPage();
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>