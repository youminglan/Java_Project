<template>
    <d2-container>
        <template slot="header">邮件模板</template>
        <div class="filter">
            <el-select v-model="appId" placeholder="请选择源" clearable @change="listMailTemplates">
                <el-option
                        v-for="id in appIds"
                        :key="id"
                        :label="id"
                        :value="id">
                </el-option>
            </el-select>
        </div>
        <el-row>
            <el-col :span="6" v-for="(mailTemplate, index) in mailTemplates" :key="index" :offset="index > 0 ? 1 : 0">
                <el-card>
                    <div slot="header">
                        <span>{{mailTemplate.name}}</span>
                        <el-button type="text" class="button" style="padding: 3px 0"
                                   v-clipboard="mailTemplate.mdValue"
                                   @success="copySuccess">复制
                        </el-button>
                    </div>
                    <div v-html="mailTemplate.content"></div>
                    <div style="margin-top: 50px; border-top: 1px solid #ddd; font-size: 13px">
                        <div style="margin-top: 10px">调度源：
                            <router-link :to="`/app-monitor?appId=${mailTemplate.appId}`"
                                         style="text-decoration: underline">
                                {{mailTemplate.appId}}
                            </router-link>
                        </div>
                        <div class="bottom time">创建时间：{{ $utils.getISOTimeFormat(mailTemplate.createTime)}}</div>
                    </div>
                </el-card>
            </el-col>
        </el-row>

        <template slot="footer">
            <footer-link/>
        </template>
    </d2-container>
</template>

<script>
    import FooterLink from '../../components/FooterLink'
    import {ListAppIds} from '../../api/source'
    import {ListAllMailTemplates, ListMailTemplatesByAppId} from '../../api/mailTemplate'

    export default {
        // 如果需要缓存页面
        // name 字段需要设置为和本页路由 name 字段一致
        name: 'mail-template',
        components: {
            FooterLink
        },
        data() {
            return {
                appId: '',
                appIds: [],
                mailTemplates: []
            }
        },
        beforeRouteEnter(to, from, next) {
            next(vm => {
                if (to.query.appId) {
                    vm.appId = to.query.appId
                }
            })
        },
        methods: {
            listMailTemplates(val) {
                if (!val) {
                    ListAllMailTemplates().then(({data}) => {
                        switch (data.code) {
                            case 0:
                                this.mailTemplates = data.data
                        }
                    })
                } else {
                    ListMailTemplatesByAppId(val).then(({data}) => {
                        switch (data.code) {
                            case 0:
                                this.mailTemplates = data.data
                        }
                    })
                }
            },
            copySuccess() {
                this.$message({
                    message: '复制成功，快去发送邮件吧',
                    type: 'success'
                })
            }
        },
        mounted() {
            ListAppIds().then(({data}) => {
                switch (data.code) {
                    case 0:
                        this.appIds = data.data
                }
            })
            this.listMailTemplates()
        }
    }
</script>

<style scoped>
</style>
