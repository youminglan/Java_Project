### Swagger整合测试

![image-20210514102138733](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210514102145.png)

1. 生成在线接口文档
2. 方便接口测试

#### 创建公共模块，整合swagger，为了使所有模块都能进行使用

![image-20210514102350550](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210514102350.png)



![image-20210514103135822](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210514103135.png)



![image-20210514102926158](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210514102926.png)



#### 使用swagger

在service_edu引入依赖service_base依赖

![image-20210514103353999](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210514103354.png)



#### 在service_edu启动类添加注解，设置包扫描规则

![image-20210514103709158](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210514103709.png)



#### 访问swagger

![image-20210514103910919](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210514103910.png)



![image-20210514104125767](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210514104125.png)



测试逻辑删除，删除id为2的信息

![image-20210514104314071](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210514104314.png)



![image-20210514104416318](https://raw.githubusercontent.com/youminglan/Picture/main/img/20210514104416.png)