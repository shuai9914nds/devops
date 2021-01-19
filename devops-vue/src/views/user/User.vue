<template>
    <div>
        <a-form layout="inline">
            <a-form-item label="用户名称">
                <a-input v-model="name"/>
            </a-form-item>

            <a-config-provider :auto-insert-space-in-button="false">
                <a-button type="primary" @click="selectUserPage"> 查询</a-button>
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
            <template slot="action" slot-scope="text, record">
        <span v-if="record.state == 0">
          <span>
            <a-popconfirm
                    v-if="data.length"
                    title="是否锁定该用户?"
                    ok-text="确定"
                    cancel-text="取消"
                    @confirm="() => updateUserState(record.uid, '1')"
            >
              <a href="javascript:;">锁定</a>
            </a-popconfirm>

            <a-divider type="vertical"/>
          </span>
          <span>
            <a-popconfirm
                    title="是否禁用该用户?"
                    ok-text="确定"
                    cancel-text="取消"
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
                    ok-text="确定"
                    cancel-text="取消"
                    @confirm="() => updateUserState(record.uid, '0')"
            >
              <a href="javascript:;">恢复正常</a>
            </a-popconfirm>
            <a-divider type="vertical"/>
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
                    ok-text="确定"
                    cancel-text="取消"
                    @confirm="() => updateUserState(record.uid, '0')"
            >
              <a href="javascript:;">恢复正常</a>
            </a-popconfirm>

            <a-divider type="vertical"/>
          </span>
          <span>
            <a-popconfirm
                    title="是否锁定该用户?"
                    ok-text="确定"
                    cancel-text="取消"
                    @confirm="() => updateUserState(record.uid, '1')"
            >
              <a href="javascript:;">锁定</a>
            </a-popconfirm>
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
            scopedSlots: {customRender: "name"},
        },
        {
            title: "身份证号",
            dataIndex: "idCardNum",
        },
        {
            title: "用户状态",
            dataIndex: "state",
            scopedSlots: {customRender: "state"},
        },
        {
            title: "创建人",
            dataIndex: "createBy",
        },
        {
            title: "操作",
            dataIndex: "action",
            scopedSlots: {customRender: "action"},
        },
    ];
    import Http from '@/utils/http'

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
            console.log('---', Http)
        },
        methods: {
            handleTableChange(pagination, filters, sorter) {
                console.log(pagination);
                const pager = {...this.pagination};
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
                Http({
                    url: "/user/page",
                    method: 'get',
                    data: {
                        size: 10,
                        current: params.current === undefined ? 1 : params.current,
                        name: this.name,
                        orderBy: "state",
                    },
                    success: response => {
                        const pagination = {...this.pagination};
                        const page = response;
                        this.data = response.records;
                        pagination.total = page.total;
                        pagination.pageSize = page.size;
                        console.log(pagination);
                        this.loading = false;
                        this.pagination = pagination;
                    }
                })
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
                Http({
                    url: "/user/state",
                    method: 'post',
                    data: {
                        uid: uid,
                        state: state,
                    },
                    success: response => {
                        this.selectUserPage();
                    }
                })
            },
        },
    };
</script>