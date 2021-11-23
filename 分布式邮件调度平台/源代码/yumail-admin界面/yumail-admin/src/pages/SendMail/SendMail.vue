<template>
  <d2-container>
    <template slot="header">支持定时、延迟发送，可选择远程或本地调度源</template>
    <basic-form/>
    <template slot="footer">
      <footer-link/>
    </template>
  </d2-container>
</template>

<script>
import FooterLink from '../../components/FooterLink'
import BasicForm from './components/BasicForm/BasicForm'
export default {
  // 如果需要缓存页面
  // name 字段需要设置为和本页路由 name 字段一致
  name: 'send-mail',
  components: {
    BasicForm,
    FooterLink
  },
  data () {
    return {
    }
  }
}
</script>
