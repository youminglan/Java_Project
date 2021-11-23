<template>
    <div>
        <el-table
                ref="filterTable"
                :data="jobs"
                style="width: 100%">
            <el-table-column
                    prop="jobName"
                    label="工作"
                    width="280"
            >
            </el-table-column>
            <el-table-column
                    prop="state"
                    label="状态"
                    width="80"
                    :filters="[{ text: '不存在', value: 0 }, { text: '运行', value: 1 }, { text: '暂停', value: 2 }
                    , { text: '完成', value: 3 }, { text: '错误', value: 4 }, { text: '阻塞', value: 5 } ]"
                    :filter-method="filterState">
                <template slot-scope="scope">
                    <el-tag :type="$constant.JOB_STATES_STYLE[scope.row.state]">
                        {{$constant.JOB_STATES_NAME[scope.row.state]}}
                    </el-tag>
                </template>
            </el-table-column>
            <el-table-column
                    prop="startTime"
                    label="开始时间"
                    sortable
                    width="160"
                    :formatter="dateFormatter"
            >
            </el-table-column>
            <el-table-column
                    prop="endTime"
                    label="结束时间"
                    sortable
                    width="160"
                    :formatter="dateFormatter"
            >
            </el-table-column>
            <el-table-column
                    prop="previousFireTime"
                    label="上次调度时间"
                    sortable
                    width="160"
                    :formatter="dateFormatter"
            >
            </el-table-column>
            <el-table-column
                    prop="nextFireTime"
                    label="下次调度时间"
                    sortable
                    width="160"
                    :formatter="dateFormatter"
            >
            </el-table-column>
            <el-table-column
                    prop="mail.subject"
                    label="主题">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作">
                <template slot-scope="scope">
                    <el-button @click="pauseJob(scope.row)" type="warning" size="small" plain>暂停</el-button>
                    <el-button @click="resumeJob(scope.row)" type="success" size="small" plain>恢复</el-button>
                    <el-button @click="delJob(scope.row)" type="danger" size="small" plain>移除</el-button>
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
    import { ListAllJobs, ListJobsByAppId, PauseJob, DelJob, ResumeJob } from '@/api/job'

    export default {
        name: 'ManageTable',
        props: ['appId'],
        data () {
            return {
                jobs: [],
                pageNum: 1,
                total: 1,
                pageSize: 10
            }
        },
        methods: {
            pauseJob(row) {
                PauseJob(row.jobName, row.jobGroupName).then(({data}) => {
                    switch (data.code) {
                        case 0:
                            this.$message.success('操作成功')
                            row.state = 2
                            break
                        default:
                            this.$message.error('操作失败')
                    }
                })
            },
            delJob(row) {
                DelJob(row.jobName, row.jobGroupName).then(({data}) => {
                    switch (data.code) {
                        case 0:
                            this.$message.success('操作成功')
                            this.jobs.splice(row.index, 1)
                            break
                        default:
                            this.$message.error('操作失败')
                    }
                })
            },
            resumeJob(row) {
                ResumeJob(row.jobName, row.jobGroupName).then(({data}) => {
                    switch (data.code) {
                        case 0:
                            this.$message.success('操作成功')
                            row.state = 1
                            break
                        default:
                            this.$message.error('操作失败')
                    }
                })
            },
            filterState (value, row) {
                return row.state === value
            },
            // page
            handleSizeChange (val) {
                console.log(`每页 ${val} 条`)
            },
            handleCurrentChange (val) {
                console.log(`当前页: ${val}`)
            },
            dateFormatter (row, column) {
                return this.$utils.getISOTimeFormat(row[column.property])
            },
            listJobs(val) {
                if (!val) {
                    ListAllJobs().then(({ data }) => {
                        switch (data.code) {
                            case 0:
                                this.jobs = data.data
                                this.total = data.data.length
                        }
                    })
                } else {
                    ListJobsByAppId(val).then(({ data }) => {
                        switch (data.code) {
                            case 0:
                                this.jobs = data.data
                                this.total = data.data.length
                        }
                    })
                }
            }
        },
        watch: {
            appId (val) {
                this.listJobs(val);
            }
        },
        mounted() {
            this.listJobs(this.appId);
        }
    }
</script>

<style scoped>
    .el-pagination {
        margin-top: 30px;
        text-align: center;
    }
</style>
