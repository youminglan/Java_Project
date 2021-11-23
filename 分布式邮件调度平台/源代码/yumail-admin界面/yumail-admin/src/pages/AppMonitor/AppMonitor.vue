<template>
    <d2-container>
        <template slot="header">Yumail 内置本地调度源，更多调度源可
            <a href="http://www.liyupi.top" style="text-decoration: underline">通过SDK无缝接入</a>
        </template>
        <el-row>
            <el-col :span="6" v-for="(source, index) in sources" :key="index" :offset="1" style="margin-bottom: 20px">
                <el-card :body-style="{ padding: '15px' }">
                    <img src="@/assets/image/server.svg" class="image">
                    <div style="font-size: 14px">
                        <div style="margin-top: 20px">应用名：{{source.appId}}</div>
                        <div style="margin-top: 10px">地址：<a :href="source.url" style="text-decoration: underline">{{source.url}}</a>
                        </div>
                        <div style="margin-top: 10px">邮箱：{{source.username}}</div>
                        <div class="bottom clearfix">
                            <time class="time">启动时间：{{ $utils.getISOTimeFormat(source.startTime)}}</time>
                            <el-button type="text" class="button" style="margin-left: 5px;color: orange"
                                       @click="$router.push({path: '/log', query: {
                             appId: source.appId}})">日志
                            </el-button>
                            <el-button type="text" class="button"
                                       @click="$router.push({path: '/dispatch-manage', query: {appId: source.appId}})">
                                管理
                            </el-button>
                        </div>
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
    import {ListAllSources} from '@/api/source'

    export default {
        // 如果需要缓存页面
        // name 字段需要设置为和本页路由 name 字段一致
        name: 'app-monitor',
        components: {
            FooterLink
        },
        data() {
            return {
                currentDate: new Date(),
                sources: []
            }
        },
        mounted() {
            ListAllSources().then(({data}) => {
                switch (data.code) {
                    case 0:
                        this.sources = data.data
                        break
                }
            })
        }
    }
</script>

<style>
    .time {
        font-size: 13px;
        color: #999;
    }

    .bottom {
        margin-top: 12px;
        line-height: 12px;
    }

    .button {
        padding: 0;
        float: right;
    }

    .image {
        width: 100%;
        display: block;
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }

    .clearfix:after {
        clear: both
    }
</style>
