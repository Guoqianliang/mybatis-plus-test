package com.gql.mybatisplustest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gql.mybatisplustest.entity.User;
import com.gql.mybatisplustest.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Wrapper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: Mybatis-PluseCRUD测试
 * 把对象交给spring管理
 * @author Guoqianliang
 * @date 11:42 - 2021/4/1
 */
@SpringBootTest
class MybatisPlusTestApplicationTests {

    @Autowired
    private UserMapper userMapper;

    /**
     * 删除-------------------------------------------------------
     *      1.根据id删除
     *      2.批量删除
     *      3.简单条件删除
     *      4.逻辑删除
     */

    // 1.根据id删除
    @Test
    public void testDeleteById() {
        int count = userMapper.deleteById(1377773978646581249L);
        System.out.println(count);
    }

    // 2.批量删除
    @Test
    public void testDeleteBatchIds() {
        int count = userMapper.deleteBatchIds(Arrays.asList(2, 3, 4));
        System.out.println(count);
    }

    // 3.简单条件删除
    @Test
    public void testDeleteByMap() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name", "Billie");
        columnMap.put("age", 24);
        int count = userMapper.deleteByMap(columnMap);
        System.out.println(count);
    }

    /**
     * 测试乐观锁-------------------------------------------------------
     */
    @Test
    public void testOptimisticLocker() {
        // 根据id先查询到user对象
        User user = userMapper.selectById(1377558342641213441L);
        // 修改user对象
        user.setName("张三");
        userMapper.updateById(user);
    }

    /**
     * 更新-------------------------------------------------------
     */
    // 更新数据
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(1377521230353031169L);
        user.setName("Hudie666");
        user.setEmail("Hudie666@qq.com");
        int count = userMapper.updateById(user);
        System.out.println("影响行数是：" + count);
    }

    /**
     * 添加-------------------------------------------------------
     */
    // 添加数据
    @Test
    public void testAdd() {
        User user = new User(null, "李四", 21, "lisi@qq.com");
        int count = userMapper.insert(user);
        System.out.println("影响行数是：" + count);
    }

    /**
     * 查询-------------------------------------------------------
     *      1.查询所有
     *      2.多个id批量查询
     *      3.简单条件查询
     *      4.分页查询
     */
    // 1.查询所有
    @Test
    public void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    // 2.多个id批量查询
    @Test
    public void testSelect01() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        System.out.println(users);
    }

    // 3.简单条件查询：查询出name=Tom & age=28 的用户信息
    @Test
    public void testSelect02() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name", "Tom");
        columnMap.put("age", "28");
        List<User> users = userMapper.selectByMap(columnMap);
        System.out.println(users);
    }

    /**
     * 4.分页查询
     * new Page(current,size):
     *      current:当前页
     *      size:   每页记录数
     */
    @Test
    public void testSelectPage() {
        Page<User> page = new Page(1, 3);
        Page<User> userPage = userMapper.selectPage(page, null);
        // 分页的所有数据都在userPage对象中封装着
        // 获取总页数
        long pages = userPage.getPages();
        // 获取当前页
        long current = userPage.getCurrent();
        // 获取当前页数据集合
        List<User> records = userPage.getRecords();
        // 获取总记录数
        long total = userPage.getTotal();
        // 当前页是否有下一页
        boolean hasNext = userPage.hasNext();
        // 当前页是否有上一页
        boolean hasPrevious = userPage.hasPrevious();

        System.out.println("总页数pages=" + pages);
        System.out.println("当前页current=" + current);
        System.out.println("当前页数据集合records=" + records);
        System.out.println("总记录数total=" + total);
        System.out.println("是否有下一页hasNext=" + hasNext);
        System.out.println("是否有上一页hasPrevious=" + hasPrevious);
    }

    /**
     * 复杂查询-------------------------------------------------------
     */

    // ge 大于等于
    @Test
    public void testSelect() {
        // 1.创建Wrapper对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 2.构造条件:使用ge查询年龄大于等于21岁的记录.
        queryWrapper.ge("age", 21);
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }

    // eq 等于
    @Test
    public void testSelect1() {
        // 1.创建Wrapper对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 2.构造条件:使用eq查询name=xiaolong的记录
        queryWrapper.eq("name", "xiaolong");
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }

    // between
    @Test
    public void testSelect2() {
        // 1.创建Wrapper对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 2.构造条件:使用between查询年龄在[22,28]中的记录
        queryWrapper.between("age", 22, 28);
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }

    // like
    @Test
    public void testSelect3() {
        // 1.创建Wrapper对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 2.构造条件:使用like查询name=张xx的记录
        queryWrapper.like("name", "张");
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }

    // orderByDesc
    @Test
    public void testSelect4() {
        // 1.创建Wrapper对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 2.构造条件:使用orderByDesc根据id对查询结果降序排列
        queryWrapper.orderByDesc("id");
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }

}
