<template>
    <d2-container>
        <template slot="header">查看邮件调度日志</template>
        <div class="filter">
            <el-select v-model="appId" placeholder="请选择源" clearable>
                <el-option
                        v-for="id in appIds"
                        :key="id"
                        :label="id"
                        :value="id">
                </el-option>
            </el-select>
            <el-button @click="handleClick" type="primary" style="margin-left: 25px" plain :disabled="!appId">持久化
            </el-button>
        </div>
        <log-table :app-id="appId"/>
        <template slot="footer">
            <footer-link/>
        </template>
    </d2-container>
</template>

<script>
    import FooterLink from '../../components/FooterLink'
    import LogTable from './components/LogTable'
    import {ListAppIds} from '../../api/source'
    import {DoPersist} from '../../api/log'

    export default {
        // 如果需要缓存页面
        // name 字段需要设置为和本页路由 name 字段一致
        name: 'log',
        components: {
            LogTable,
            FooterLink
        },
        data() {
            return {
                appId: '',
                appIds: []
            }
        },
        methods: {
            handleClick() {
                DoPersist(this.appId).then(({data}) => {
                    switch (data.code) {
                        case 0:
                            this.$message.success('临时数据已转储为日志文件 ' + data.data);
                            break;
                        default:
                            this.$message.error('持久化失败')
                    }
                })
            }
        },
        beforeRouteEnter(to, from, next) {
            next(vm => {
                if (to.query.appId) {
                    vm.appId = to.query.appId
                }
            })
        },
        mounted() {
            ListAppIds().then(({data}) => {
                switch (data.code) {
                    case 0:
                        this.appIds = data.data
                }
            })
        }
    }
</script>

<style scoped>

</style>
