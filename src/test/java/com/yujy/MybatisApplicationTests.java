package com.yujy;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yujy.entity.User;
import com.yujy.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setAge(11).setName("qyy").setEmail("222@qq.com");
        int insert = userMapper.insert(user);
        System.out.println("insert++++++++++" + insert);


    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setAge(22);
        userMapper.updateById(user);

    }

    /**
     * 测试乐观锁
     */
    @Test
    public void testOptimisticLocker() {
        User user = userMapper.selectById(1319086475339276289L);
        user.setAge(54);
        userMapper.updateById(user);
    }

    /**
     * 批量查询
     */
    @Test
    public void testSelect() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(user -> System.out.println(user));

    }

    /**
     * 条件查询
     */
    @Test
    public void testSelectDemo2() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("version", 0);
//        map.put("age",11);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    /**
     * 分页查询
     */
    @Test
    public void testPagination() {
        //1.创建page对象
        //当前页   和 每页记录数
        Page<User> page = new Page<>(1, 2);
        //2。调用mp分页查询的方法
        userMapper.selectPage(page, null);
        //分页数据都会封装到page对象里
        //通过page对象获取分页数据

        //当前所在页页数
        System.out.println(page.getCurrent());
        //当前页记录
        System.out.println(page.getRecords());
        //当前页记录数
        System.out.println(page.getSize());
        //表中总记录数
        System.out.println(page.getTotal());

        System.out.println(page.getClass());
        System.out.println(page.toString());
        //是否有下一页
        System.out.println(page.hasNext());
        //是否有上一页
        System.out.println(page.hasPrevious());
    }

    /**
     * 不加插件物理删除
     * 加上插件逻辑删除
     */
    @Test
    public void deleteById() {
        userMapper.deleteById(1319100407416242177L);
    }

    /**
     * 批量删除
     */
    @Test
    public void deleteMany() {
        userMapper.deleteBatchIds(Arrays.asList(2, 3, 7));

    }

    @Test
    public void testSelectQuery() {
        //创建QueryWrapper对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        //通过QueryWrapper设置条件
        //ge gt le lt
//        wrapper.ge("age",30);
//        List<User> users = userMapper.selectList(wrapper);
//        System.out.println(users);


        //eq 等于  ne 不等于
//        wrapper.eq("name","qyy");
//        wrapper.ne("name","sdf");
//        List<User> users = userMapper.selectList(wrapper);
//        System.out.println(users);


        //between  like 模糊查询
//        wrapper.between("age",0,16);
//        wrapper.like("name","q");
//        List<User> users = userMapper.selectList(wrapper);
//        System.out.println(users);


        //last 拼接
//        wrapper.last("limit 1");
//        List<User> users1 = userMapper.selectList(wrapper);
//        System.out.println(users1);

        //查询指定列
        wrapper.select("id","name");
    }
}
