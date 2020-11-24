<template>
    <a-table
            :columns="columns"
            :row-key="(record) => record.uid"
            :data-source="data"
            :pagination="pagination"
            :loading="loading"
            @change="handleTableChange"
    >
        <template slot="name" slot-scope="name">
            {{ name.first }} {{ name.last }}
        </template>
    </a-table>
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
            title: "身份证号",
            dataIndex: "idCardNum",
        },
        {
            title: "用户状态",
            dataIndex: "state",
            filters: [
                  { text: 0, value: "正常" },
                  { text: 1, value: "锁定" },
                  { text: 2, value: "禁用" },
                ],
        },
        {
            title: "创建人",
            dataIndex: "createBy",
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

    export default {
        data() {
            return {
                data: [],
                pagination: {},
                loading: false,
                columns,
            };
        },
        mounted() {
            this.getUserPage();
        },
        methods: {
            handleTableChange(pagination, filters, sorter) {
                console.log(pagination);
                const pager = {...this.pagination};
                pager.current = pagination.current;
                this.pagination = pager;
                this.getUserPage({
                    size: pagination.pageSize,
                    current: pagination.current,
                    sortField: sorter.field,
                    sortOrder: sorter.order,
                    ...filters,
                });
            },
            getUserPage(params = {}) {
                this.$axios
                    .get("/user/user/page", {
                        params: {
                            size: 10,
                            current: params.current === undefined ? 1 : params.current,
                            ...params
                        },
                        headers: {
                            "User-Token": localStorage.getItem("token"),
                        },
                    })
                    .then((response) => {
                        const pagination = {...this.pagination};
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
        },
    };
</script>
