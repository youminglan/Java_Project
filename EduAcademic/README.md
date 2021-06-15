技术栈：Redis、Nginx、Springboot、maven、Git



### 第一天

1. 项目背景

   在线教育：网络教学
   
   
   
2. 商业模式

   B2B2C、B2C（管理员和普通用户）

   ![image-20210614173455665](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210614173502.png)

   ![image-20210614173727133](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210614173727.png)

3. 功能模块

   **系统后台（管理员）**：讲师管理模块、课程分类管理模块、课程管理模块、统计分析模块、订单管理模块、banner模块（轮播图）、权限管理模块

   **系统前台（普通用户）**：首页数据显示、讲师列表和详情、课程列表和课程详情、登录和注册功能、微信扫码登录、微信扫码支付

   

4. 技术栈

   前后端分离开发：

   **后端技术**：Springboot、Springcloud、MybatisPlus、Spring security、Redis、maven、easyExcel、jwt、OAuth2

   **前端技术**：vue、element-ui、axios、node js

   **其他技术**：阿里云oss、阿里云视频点播服务、阿里云短信服务、微信支付和登录、docker、git、Jenkins

   

5. 学习技术Mybatis-Plus

   Mybatis-Plus是Mybatis增强工具，提高效率

   创建springboot项目

   主键生成策略：自动增长、UUID每次生成随机唯一的值、Redis生成、mp自带策略

   ![image-20210614220748297](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210614220748.png)
   
   mp实现修改操作：自动填充
   
   ![image-20210615103038651](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210615103038.png)
   
   
   
   乐观锁：丢失更新
   
   ![image-20210615105105191](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210615105105.png)
   
   解决方案：
   
   悲观锁：串行
   
   乐观锁：版本号
   
   ![image-20210615105435398](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210615105435.png)
   
   乐观锁具体实现：1. 表添加字段，作为乐观锁版本号；2. 对应实体类添加版本号属性；3. 在实体类版本号属性添加注解；4. 配置乐观锁插件
   
   ![image-20210615111935838](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210615111935.png)
   
   mp简单查询：
   
   1. 根据id查询
   
      ```java
      User user = userMapper.selectById(1404431873507889156L);
      ```
   
      
   
   2. 多个id批量查询
   
      ```java
          //多个id批量查询
          @Test
          void testSelectDemo1(){
                  List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
                  System.out.println(users);
      ```
   
      

