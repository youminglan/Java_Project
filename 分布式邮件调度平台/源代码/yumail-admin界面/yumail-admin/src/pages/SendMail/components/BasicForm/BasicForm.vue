<template>
    <div className="basic-form">
        <basic-container>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-position="left" label-width="90px"
                     class="demo-ruleForm">
                <el-form-item label="主题" prop="subject">
                    <el-input v-model="ruleForm.subject"></el-input>
                </el-form-item>
                <!-- todo 此处可记录历史 -->
                <el-form-item label="发送至" prop="toUser">
                    <el-autocomplete
                            v-model="ruleForm.toUser"
                            :fetch-suggestions="querySearch"
                            placeholder="请输入接收者邮箱"
                            clearable
                    />
                </el-form-item>
                <el-form-item label="定时发送" prop="timer">
                    <el-switch v-model="ruleForm.timer"></el-switch>
                </el-form-item>
                <el-form-item label="定时规则" v-if="ruleForm.timer">
                    <el-col :span="12">
                        <el-tooltip placement="top">
                            <div slot="content"><a href="http://cron.qqe2.com/" target="_blank"
                                                   style="color: white;font-size: 14px;">在线Cron表达式生成器</a></div>
                            <el-input v-model="ruleForm.crontab" placeholder="请填写crontab表达式（如 * * * * * ? * ）"/>
                        </el-tooltip>
                    </el-col>
                    <el-col :span="8" :offset="2">
                        <el-radio-group v-model="interval" @change="onChange">
                            <el-radio label="1">每分</el-radio>
                            <el-radio label="2">每时</el-radio>
                            <el-radio label="3">每天</el-radio>
                        </el-radio-group>
                    </el-col>

                </el-form-item>
                <el-form-item label="时间范围">
                    <el-date-picker
                            v-model="ruleForm.timeRange"
                            type="datetimerange"
                            range-separator="至"
                            start-placeholder="开始日期"
                            end-placeholder="结束日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="调度源" prop="source">
                    <el-radio-group v-model="ruleForm.source">
                        <el-radio v-for="appId in appIds" :label="appId">{{appId}}</el-radio>
                    </el-radio-group>
                </el-form-item>
                <mavon-editor v-model="mdValue"/>
                <el-form-item style="margin-top: 30px;">
                    <el-button type="primary" @click="submitForm('ruleForm')">发送</el-button>
                    <el-button @click="resetForm('ruleForm')">重置</el-button>
                </el-form-item>
            </el-form>
        </basic-container>
    </div>
</template>

<script>
    import BasicContainer from '@vue-materials/basic-container'
    import marked from 'marked'
    import { SendHtmlMail } from '@/api/mail'
    import { ListAppIds } from '@/api/source'
    import { AddMailTemplate } from '@/api/mailTemplate'
    import util from '@/libs/util'

    let renderMD = new marked.Renderer()
    marked.setOptions({
        renderer: renderMD,
        gfm: true,
        tables: true,
        breaks: false,
        pedantic: false,
        sanitize: false,
        smartLists: true,
        smartypants: false
    })

    export default {
        components: { BasicContainer },
        name: 'BasicForm',

        data () {
            return {
                appIds: [],
                ruleForm: {
                    subject: '',
                    toUser: '',
                    timeRange: [],
                    timer: false,
                    crontab: '',
                    source: '',
                    content: ''
                },
                toUsers: [],
                mdValue: '',
                interval: '',
                rules: {
                    subject: [
                        { required: true, message: '请输入邮件主题', trigger: 'blur' }
                    ],
                    toUser: [
                        { required: true, message: '请选择接收者邮箱', trigger: 'change' }
                    ],
                    crontab: [
                        { required: true, message: '请输入crontab表达式', trigger: 'change' }
                    ]
                }
            }
        },
        methods: {
            querySearch (queryString, cb) {
                let toUsers = this.toUsers
                var results = queryString ? toUsers.filter(this.createFilter(queryString)) : toUsers
                // 调用 callback 返回建议列表的数据
                cb(results)
            },
            createFilter (queryString) {
                return (toUser) => {
                    return (toUser.value.toLowerCase().indexOf(queryString.toLowerCase()) >= 0)
                }
            },
            onChange (index) {
                switch (index) {
                    case '1':
                        this.ruleForm.crontab = '0 0/1 * * * ? *'
                        break
                    case '2':
                        this.ruleForm.crontab = '0 0 0/1 * * ? *'
                        break
                    case '3':
                        this.ruleForm.crontab = '0 0 0 1/1 * ? *'
                        break
                }

            },
            submitForm (formName) {
                let toUsers = this.toUsers.map(toUser => {
                    return toUser.value
                })
                // 缓存收件人
                if (toUsers.indexOf(this.ruleForm.toUser) < 0) {
                    this.toUsers.push({
                        value: this.ruleForm.toUser
                    })
                    util.cookies.set('toUsers', JSON.stringify(this.toUsers))
                }
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        // md转换为html
                        this.ruleForm.content = marked(this.mdValue, { sanitize: true })
                        this.ruleForm.startTime = this.ruleForm.timeRange[0]
                        this.ruleForm.endTime = this.ruleForm.timeRange[1]
                        this.$notify.info({
                            title: '发送中',
                            message: '邮件正在发送，请耐心等待'
                        })
                        SendHtmlMail(this.ruleForm).then(({ data }) => {
                            switch (data.code) {
                                case 0:
                                    this.$confirm('发送成功，是否保存邮件内容为模板', {
                                        confirmButtonText: '确定',
                                        cancelButtonText: '取消',
                                        type: 'success'
                                    }).then(() => {
                                        this.$prompt('请输入模板名', '创建模板', {
                                            confirmButtonText: '确定',
                                            cancelButtonText: '取消',
                                        }).then(({ value }) => {
                                            AddMailTemplate({
                                                name: value,
                                                mdValue: this.mdValue,
                                                content: this.ruleForm.content,
                                                appId: this.ruleForm.source
                                            }).then(({data}) => {
                                                switch (data.code) {
                                                    case 0:
                                                        this.$message({
                                                            type: 'success',
                                                            message: '创建模板成功'
                                                        });
                                                        break;
                                                    default:
                                                        this.$message.error('创建失败')
                                                }
                                            })
                                        })

                                    })
                                    break;
                                default:
                                    this.$message.error('发送失败，' + data.msg)
                            }
                        })
                    } else {
                        console.log('error submit!!')
                        return false
                    }
                })
            },
            resetForm (formName) {
                this.$refs[formName].resetFields()
            }
        },
        mounted () {
            ListAppIds().then(({ data }) => {
                switch (data.code) {
                    case 0:
                        this.appIds = data.data
                        this.ruleForm.source = this.appIds[0]
                }
            })
            let toUsers = util.cookies.get('toUsers')
            this.toUsers = toUsers ? JSON.parse(toUsers) : []
        }
    }

</script>

<style>
</style>
