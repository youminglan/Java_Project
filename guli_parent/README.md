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


   ### 第二天

   1. 前后端分离开发

      前端：html、css、js、jq 	主要作用：数据显示	ajax操作

      后端：controller、service、mapper 	主要作用：返回数据或者操作数据	开发接口不是interface，而是开发controller、service、mapper过程

      ![image-20210616090732816](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210616090739.png)

      

   2. 讲师管理模块（后端）：讲师crud操作

      - 准备工作

        创建数据库

        ![image-20210616092420035](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210616092420.png)

        创建项目结构

        ![image-20210616092441761](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210616092441.png)

        开发讲师管理模块

        1. 创建application.properties配置文件

        2. 编写controller、service、mapper代码内容。mp提高代码生成器，生成相关代码

           ![image-20210616103707130](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210616103707.png)

        3. 编写controller

           ```java
           @RestController
           @RequestMapping("/eduservice/teacher")
           public class EduTeacherController {
           
               //把service注入
               @Autowired
               private EduTeacherService teacherService;
           
               //查询讲师表中的所有数据
               //rest风格
               @GetMapping("findAll")
               public List<EduTeacher> findAllTeacher(){
                   //调用service方法实现查询所有的操作
                   List<EduTeacher> list = teacherService.list(null);
                   return list;
               }
           }
           ```
      
4. 创建启动类
        
           ```java
           @SpringBootApplication
           public class EduApplication {
           
               public static void main(String[] args) {
                   SpringApplication.run(EduApplication.class, args);
               }
           }
           ```
       
        5. 创建配置类，配置mapper扫描和其他
       
           ```java
           @Configuration
           @MapperScan("com.atguigu.eduservice.mapper")
           public class EduConfig {
           
           }
           ```
       
        6. 最终测试
       
           项目启动起来，端口号8001
           
           
       
        开发讲师管理模块
       
       1. 配置逻辑删除插件
       
          ```java
          /**
               * 逻辑删除插件
               */
              @Bean
              public ISqlInjector sqlInjector() {
                  return new LogicSqlInjector();
              }
          ```
       
       2. 逻辑删除属性上面添加注解
       
       3. 编写controller方法
       
          ```java
              //逻辑删除讲师的方法
              @DeleteMapping("{id}") //id值需要通过路径进行传递
              public boolean removeTeacher(@PathVariable String id){
                  boolean flag = teacherService.removeById(id);
                  return flag;
              }
          ```
       
       4. 如何测试
       
          借助一些工具进行测试：swagger、postman
       
          
       
       整合swagger进行接口测试
       
       1. 生成在线接口文档
       2. 方便接口测试
       3. 创建公共模块，整合swagger，为了所有模块都能进行使用
       ![image-20210616121333090](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210616121333.png)
       
       
       
       讲师分页功能
       
       1. 配置mp分页插件
       
          ```java
          /**
               * 分页插件
               */
              @Bean
              public PaginationInterceptor paginationInterceptor() {
                  return new PaginationInterceptor();
              }
          ```
       
          
       
       2. 编写讲师分页查询接口方法
       
          ```java
          @GetMapping("pageTeacher/{current}/{limit}")
              public R pageListTeacher(@PathVariable long current,
                                       @PathVariable long limit) {
                  //创建page对象
                  Page<EduTeacher> pageTeacher = new Page<>(current,limit);
          
                  int i = 10/0;
          
                  //调用方法实现分页
                  //调用方法时候，底层封装，把分页所有数据封装到pageTeacher对象里面
                  teacherService.page(pageTeacher,null);
          
                  long total = pageTeacher.getTotal();//总记录数
                  List<EduTeacher> records = pageTeacher.getRecords(); //数据list集合
                  return R.ok().data("total",total).data("rows",records);
              }
          ```
       
       3. 多条件组合查询带分页
       
          ![9 讲师分页查询和条件查询](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210618103316.png)
          
          
          
          讲师添加功能
          
          1. 自动填充
          
             ![10 讲师添加功能](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210618103310.png)
          
          2. 讲师修改功能
          
             ![11 讲师修改功能](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210618103929.png)
          
   ### 第三天
   
      1. 统一异常处理
   
         全局异常处理
   
         特定异常处理
   
         自定义异常处理
   
         ![2-统一异常处理](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210618145951.png)
         
         统一日志处理
         
         日志级别：ERROR、WARN、INFO、DEBUG
         
         Logback：把日志不仅输出到控制台，也可以输出到文件中，使用日志工具
   
   ![3-统一日志处理](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210618152607.png)
   
      2. 前端知识
   
         安装前端开发工具 vscode
   
         创建工作区
   
         ![4-vscode安装](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210621101523.png)
   
         ES6：一套标准，一套规范，JavaScript很好地遵循了这套规范
   
         ![5-es6介绍](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210621102259.png)
   
         Vue.js：一套用于构建用户界面的渐进式框架。
         
         ![6-vue入门案例](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210621223145.png)
         
         组件（Component）是 Vue.js 最强大的功能之一。
         
         组件可以扩展 HTML 元素，封装可重用的代码。
         
         组件系统让我们可以用独立可复用的小组件来构建大型应用，几乎任意类型的应用的界面都可以抽象为一个组件树
         
         
