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
          <span v-for="(item, i) in record.roleList">
          {{item.roleName}}
          </span>
        </span>
      </template>
      <!-- <template slot="state" slot-scope="text, record">
        <span v-if="record.state == 0">正常</span>
        <span v-if="record.state == 1">锁定</span>
        <span v-if="record.state == 2">禁用</span>
      </template>
      <template slot="action" slot-scope="text, record">
        <span v-if="record.state == 0">
          <span>
            <a-popconfirm
              v-if="data.length"
              title="是否锁定该用户?"
              @confirm="() => updateUserState(record.uid, '1')"
            >
              <a href="javascript:;">锁定</a>
            </a-popconfirm>

            <a-divider type="vertical" />
          </span>
          <span>
            <a-popconfirm
              title="是否禁用该用户?"
              @confirm="() => updateUserState(record.uid, '2')"
            >
              <a href="javascript:;">禁用</a>
            </a-popconfirm>
          </span>
        </span>
        <span v-if="record.state == 1">
          <span>
            <a-popconfirm
              v-if="data.length"
              title="是否恢复正常?"
              @confirm="() => updateUserState(record.uid, '0')"
            >
              <a href="javascript:;">恢复正常</a>
            </a-popconfirm>
            <a-divider type="vertical" />
          </span>
          <span>
            <a-popconfirm
              title="是否禁用该用户?"
              @confirm="() => updateUserState(record.uid, '2')"
            >
              <a href="javascript:;">禁用</a>
            </a-popconfirm>
          </span>
        </span>
        <span v-if="record.state == 2">
          <span>
            <a-popconfirm
              v-if="data.length"
              title="是否恢复正常?"
              @confirm="() => updateUserState(record.uid, '0')"
            >
              <a href="javascript:;">恢复正常</a>
            </a-popconfirm>

            <a-divider type="vertical" />
          </span>
          <span>
            <a-popconfirm
              title="是否锁定该用户?"
              @confirm="() => updateUserState(record.uid, '1')"
            >
              <a href="javascript:;">锁定</a>
            </a-popconfirm>
          </span>
        </span>
      </template> -->
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
  //   {
  //     title: "Gender",
  //     dataIndex: "gender",
  //     filters: [
  //       { text: "Male", value: "male" },
  //       { text: "Female", value: "female" },
  //     ],
  //     width: "20%",
  //   },
  //   {
  //     title: "Email",
  //     dataIndex: "email",
  //   },
];

// const EditableCell = {
//   template: `
//       <div class="editable-cell">
//         <div v-if="editable" class="editable-cell-input-wrapper">
//           <a-input :value="value" @change="handleChange" @pressEnter="check" /><a-icon
//             type="check"
//             class="editable-cell-icon-check"
//             @click="check"
//           />
//         </div>
//         <div v-else class="editable-cell-text-wrapper">
//           {{ value || ' ' }}
//           <a-icon type="edit" class="editable-cell-icon" @click="edit" />
//         </div>
//       </div>
//     `,
//   props: {
//     text: String,
//   },
//   data() {
//     return {
//       value: this.text,
//       editable: false,
//     };
//   },
//   methods: {
//     handleChange(e) {
//       const value = e.target.value;
//       this.value = value;
//     },
//     check() {
//       this.editable = false;
//       this.$emit("change", this.value);
//     },
//     edit() {
//       this.editable = true;
//     },
//   },
// };

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
    // updateUserState(uid) {
    //   const dataSource = [...this.data];
    //   const params = dataSource.filter((item) => item.uid == uid);
    //   this.updateUserState({
    //     uid: params[0].uid,
    //     state: params[0].state,
    //   });
    // },
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