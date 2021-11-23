<template>
    <d2-container>
        <template slot="header">控制调度源和调度任务</template>
        <div class="filter">
            <el-select v-model="appId" placeholder="请选择源" clearable>
                <el-option
                        v-for="id in appIds"
                        :key="id"
                        :label="id"
                        :value="id">
                </el-option>
            </el-select>
            <span style="margin-left: 25px">调度器状态：
                <el-tag :type="$constant.SCHEDULER_STATES_STYLE[status]">
                        {{$constant.SCHEDULER_STATES_NAME[status]}}
                    </el-tag>
            </span>
            <span style="margin-left: 50px">操作：</span>
            <el-button @click="startScheduler" type="success" plain>开启调度</el-button>
            <el-button @click="pauseScheduler" type="danger" style="margin-left: 25px" plain>暂停调度</el-button>
        </div>
        <manage-table :app-id="appId"/>
        <template slot="footer">
            <footer-link/>
        </template>
    </d2-container>
</template>

<script>
    import FooterLink from '../../components/FooterLink'
    import ManageTable from './components/ManageTable'
    import { ListAppIds } from '../../api/source'
    import { PauseScheduler, StartScheduler, GetSchedulerStatus } from '../../api/job'

    export default {
        // 如果需要缓存页面
        // name 字段需要设置为和本页路由 name 字段一致
        name: 'dispatch-manage',
        data () {
            return {
                appId: '',
                appIds: [],
                status: 0
            }
        },
        components: {
            ManageTable,
            FooterLink
        },
        beforeRouteEnter (to, from, next) {
            next( vm => {
                if (to.query.appId) {
                    vm.appId = to.query.appId;
                }
            })
        },
        methods: {
            pauseScheduler() {
                PauseScheduler().then(({ data }) => {
                    switch (data.code) {
                        case 0:
                            this.$message({
                                message: '已暂停调度器',
                                type: 'success'
                            })
                    }
                });
            },
            startScheduler() {
                StartScheduler().then(({ data }) => {
                    switch (data.code) {
                        case 0:
                            this.$message({
                                message: '已启动调度器',
                                type: 'success'
                            })
                    }
                });
            }
        },
        mounted() {
            ListAppIds().then(({ data }) => {
                switch (data.code) {
                    case 0:
                        this.appIds = data.data
                }
            })
            GetSchedulerStatus().then(({data}) => {
                switch (data.code) {
                    case 0:
                        this.status = data.data
                }
            })
        }
    }
</script>

<style scoped>
</style>
