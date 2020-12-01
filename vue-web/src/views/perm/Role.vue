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
          <template slot="footer">
            <a-button key="back" @click="handleCancel"> Return</a-button>
            <a-button
              key="submit"
              type="primary"
              :loading="loading"
              @click="handleOk"
            >
              Submit
            </a-button>
          </template>
          <template>
            <a-tree
              checkable
              :tree-data="treeData"
              :default-expanded-keys="['0-0-0', '0-0-1']"
              :default-selected-keys="['0-0-0', '0-0-1']"
              :default-checked-keys="['0-0-0', '0-0-1']"
              @select="onSelect"
              @check="onCheck"
            >
              <span slot="title0010" style="color: #1890ff">sss</span>
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

const treeData = [
  {
    title: "parent 1",
    key: "0-0",
    children: [
      {
        title: "parent 1-0",
        key: "0-0-0",
        disabled: true,
        children: [
          { title: "leaf", key: "0-0-0-0", disableCheckbox: true },
          { title: "leaf", key: "0-0-0-1" },
        ],
      },
      {
        title: "parent 1-1",
        key: "0-0-1",
        children: [{ key: "0-0-1-0", slots: { title: "title0010" } }],
      },
    ],
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
      treeData,
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
    showModal() {
      this.visible = true;
    },
    handleOk(e) {
      this.loading = true;
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
    },
  },
};
</script>
