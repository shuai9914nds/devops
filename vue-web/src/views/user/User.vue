<template>
  <div>
    <a-form layout="inline" @submit="this.selectUserPage">
      <a-form-item label="用户名称">
        <a-input v-model="this.name" />
      </a-form-item>

      <a-config-provider :auto-insert-space-in-button="false">
        <a-button type="primary" html-type="submit"> 查询 </a-button>
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
      <template slot="state" slot-scope="text, record">
        <span v-if="record.state == 0">正常</span>
        <span v-if="record.state == 1">锁定</span>
        <span v-if="record.state == 2">禁用</span>
      </template>
      <!-- <template slot="name" slot-scope="text, record">
        <editable-cell
          :text="text"
          @change="onCellChange(record.uid, 'name', $event)"
        />
      </template> -->
      <template slot="action" slot-scope="text, record">
        <!-- <editable-cell
          :text="text"
          @change="onCellChange(record.key, 'action', $event)"
        /> -->
        <span v-if="record.state == 0">
          <span>
            <a-popconfirm
              v-if="data.length"
              title="是否锁定该用户?"
              @confirm="() => onDelete(record.uid)"
            >
              <a href="javascript:;">锁定</a>
            </a-popconfirm>

            <a-divider type="vertical" />
          </span>
          <span>
            <!-- <a-popconfirm
              title="Sure to delete?"
              @confirm="() => onDelete(record.key)"
            >
            </a-popconfirm> -->
            <a href="javascript:;">禁用</a>
          </span>
        </span>
        <span v-if="record.state == 1">
          <a>恢复正常</a>
          <a-divider type="vertical" />
          <a>禁用</a>
        </span>

        <span v-if="record.state == 2">
          <a>恢复正常</a>
          <a-divider type="vertical" />
          <a>锁定</a>
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
    scopedSlots: { customRender: "name" },
  },
  {
    title: "身份证号",
    dataIndex: "idCardNum",
  },
  {
    title: "用户状态",
    dataIndex: "state",
    scopedSlots: { customRender: "state" },
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

const EditableCell = {
  template: `
      <div class="editable-cell">
        <div v-if="editable" class="editable-cell-input-wrapper">
          <a-input :value="value" @change="handleChange" @pressEnter="check" /><a-icon
            type="check"
            class="editable-cell-icon-check"
            @click="check"
          />
        </div>
        <div v-else class="editable-cell-text-wrapper">
          {{ value || ' ' }}
          <a-icon type="edit" class="editable-cell-icon" @click="edit" />
        </div>
      </div>
    `,
  props: {
    text: String,
  },
  data() {
    return {
      value: this.text,
      editable: false,
    };
  },
  methods: {
    handleChange(e) {
      const value = e.target.value;
      this.value = value;
    },
    check() {
      this.editable = false;
      this.$emit("change", this.value);
    },
    edit() {
      this.editable = true;
    },
  },
};

export default {
  data() {
    return {
      data: [],
      pagination: {},
      loading: false,
      columns,
      name: name,
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
        .get("/user/page", {
          params: {
            size: 10,
            current: params.current === undefined ? 1 : params.current,
            name: this.name,
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
    onDelete(uid) {
      const dataSource = [...this.data];
      const params = dataSource.filter((item) => item.uid == uid);
      this.updateUserState({
        uid: params[0].uid,
        state: params[0].state,
      });
    },
    updateUserState(params = {}) {
      this.$axios
        .post(
          "/user/state",
          {
            uid: params.uid,
            state: params.state,
          },
          {
            headers: {
              // "content-type": "application/json",
              "User-Token": localStorage.getItem("token"),
            },
          }
        )
        .then((response) => {
          this.data = response.data.obj.records;
          this.selectUserPage();
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style>
.editable-cell {
  position: relative;
}

.editable-cell-input-wrapper,
.editable-cell-text-wrapper {
  padding-right: 24px;
}

.editable-cell-text-wrapper {
  padding: 5px 24px 5px 5px;
}

.editable-cell-icon,
.editable-cell-icon-check {
  position: absolute;
  right: 0;
  width: 20px;
  cursor: pointer;
}

.editable-cell-icon {
  line-height: 18px;
  display: none;
}

.editable-cell-icon-check {
  line-height: 28px;
}

.editable-cell:hover .editable-cell-icon {
  display: inline-block;
}

.editable-cell-icon:hover,
.editable-cell-icon-check:hover {
  color: #108ee9;
}

.editable-add-btn {
  margin-bottom: 8px;
}
</style>