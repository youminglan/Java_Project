<template>
    <div>
        <el-table
                ref="filterTable"
                :data="logs"
                style="width: 100%">
            <el-table-column
                    prop="createTime"
                    label="时间"
                    sortable
                    width="180"
                    :formatter="dateFormatter"
            >
            </el-table-column>
            <el-table-column
                    prop="level"
                    label="级别"
                    width="100"
                    :filters="[{ text: '通知', value: 0 }, { text: '警告', value: 1 }, { text: '危险', value: 2 }]"
                    :filter-method="filterLevel">
                <template slot-scope="scope">
                    <el-tag :type="$constant.LEVEL_STYLE[scope.row.level]">{{$constant.LEVEL_NAME[scope.row.level]}}</el-tag>
                </template>
            </el-table-column>
            <el-table-column
                    prop="appId"
                    label="调度源"
                    width="180">
            </el-table-column>
            <el-table-column
                    label="内容">
                <template slot-scope="scope">
                    <div v-html="scope.row.content"></div>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pageNum"
                :page-sizes="[10, 20, 30, 40, 50]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
        </el-pagination>
    </div>
</template>

<script>
    import { ListAllLogs, ListLogsByAppId } from '@/api/log'

    export default {
        name: 'LogTable',
        props: ['appId'],
        data () {
            return {
                logs: [],
                pageNum: 1,
                total: 1,
                pageSize: 10
            }
        },
        methods: {
            filterLevel (value, row) {
                return row.level === value
            },
            // page
            handleSizeChange (val) {
                console.log(`每页 ${val} 条`)
            },
            handleCurrentChange (val) {
                console.log(`当前页: ${val}`)
            },
            dateFormatter(row, column) {
                return this.$utils.getISOTimeFormat(row.createTime);
            },
            listLogs(val) {
                if (!val) {
                    ListAllLogs().then(({ data }) => {
                        switch (data.code) {
                            case 0:
                                this.logs = data.data
                                this.total = data.data.length
                        }
                    })
                } else {
                    ListLogsByAppId(val).then(({ data }) => {
                        switch (data.code) {
                            case 0:
                                this.logs = data.data
                                this.total = data.data.length
                        }
                    })
                }
            }
        },
        watch: {
            appId (val) {
                this.listLogs(val);
            }
        },
        mounted() {
            this.listLogs(this.appId);
        }
    }
</script>

<style scoped>
    .el-pagination {
        margin-top: 30px;
        text-align: center;
    }
</style>
