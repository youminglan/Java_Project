package com.atguigu.mpdemo1010;

import com.atguigu.mpdemo1010.entity.User;
import com.atguigu.mpdemo1010.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.jdbc.Null;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Wrapper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class Mpdemo1010ApplicationTests {

    @Autowired
    private UserMapper userMapper;

    //查询表中所有数据
    @Test
    void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);

    }

    //添加数据
    @Test
    void addUser(){
        User user = new User();
        user.setName("xiaohuang");
        user.setAge(20);
        user.setEmail("lucy@qq.com");

        int insert = userMapper.insert(user);
        System.out.println("insert:"+insert);
    }

    //修改数据
    @Test
    void updateUser(){
        User user = new User();
        user.setId(2L);
        user.setAge(30);
        int row = userMapper.updateById(user);
        System.out.println(row);
    }

    //测试乐观锁
    @Test
    void testOptimisticLocker(){
        //根据ID查询数据
        User user = userMapper.selectById(1404431873507889156L);

        //进行修改
        user.setAge(200);
        userMapper.updateById(user);
    }

    //多个id批量查询
    @Test
    void testSelectDemo1(){
            List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
            System.out.println(users);
    }

    //通过map封装查询条件
    @Test
    public void testSelectByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Helen");
        map.put("age", 18);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    //分页查询
    @Test
    void testPage(){
        //1. 创建page对象
        //传入两个参数
        Page<User> page = new Page<>(1,3);

        //调用mp分页查询方法
        //调用mp分页查询过程中，底层封装
        //把分页的所有数据封装到page对象里面
        userMapper.selectPage(page, null);

        //通过page对象获取分页数据
        System.out.println(page.getCurrent());//当前页
        System.out.println(page.getRecords());//每页数据list集合
        System.out.println(page.getSize());//每页显示记录数
        System.out.println(page.getTotal());//总记录数
        System.out.println(page.getPages());//总页数

        System.out.println(page.hasNext());//下一页
        System.out.println(page.hasPrevious());//上一页

    }

    //删除操作 物理删除
    @Test
    public void testDeleteById(){
        int result = userMapper.deleteById(1404431873507889157L);
        System.out.println(result);
    }

    //mp实现复杂查询操作
    @Test
    public void testSelectQuery(){
        //创建对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //通过QueryWrapper设置条件
        //ge、gt、le、lt
//        wrapper.ge("age",30);

        //eq,ne
//        wrapper.eq("name","xiaoming");
//        wrapper.ne("name","xiaoming");

        //between
        wrapper.between("age",20,50);
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);

    }

}
