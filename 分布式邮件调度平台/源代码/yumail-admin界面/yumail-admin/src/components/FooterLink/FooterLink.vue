<template>
  <span class="footer-link">
    欢迎使用 Yumail (飞鱼 - 分布式邮件调度平台） 这些链接会对你有帮助：
    <a class="footer-link_link" href="#" target="blank">使用指南</a> |
    <a class="footer-link_link" href="http://www.liyupi.top" target="blank">作者主页</a> |
    <a class="footer-link_link" href="https://github.com/liyupi" target="blank">作者仓库</a>
  </span>
</template>

<script>
export default {
  name: 'FooterLink'
}
</script>

<style lang="scss" scoped>
@import '~@/assets/style/public.scss';
.footer-link {
  .footer-link_link {
    color: $color-primary;
  }
}
</style>
