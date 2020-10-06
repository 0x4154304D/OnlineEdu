package com.at0m.mpdemo1005;

import com.at0m.mpdemo1005.bean.User;
import com.at0m.mpdemo1005.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class Mpdemo1005ApplicationTests {

    @Autowired
    private UserMapper userMapper;

    //查询user表中所有数据
    @Test
    public void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
    
    //添加操作
    @Test
    public void addUser(){
        User user = new User();
        user.setName("狄仁杰");
        user.setAge(27);
        user.setEmail("direnjie@gmail.com");

//        user.setCreateTime(new Date());
//        user.setUpdateTime(new Date());

        int insert = userMapper.insert(user);
        System.out.println("insert " + insert);
    }

    //修改操作
    @Test
    public void updateUser(){
        User user = new User();

        user.setId(3L);
        user.setAge(52);

        int row = userMapper.updateById(user);
        System.out.println(row);
    }

//    测试乐观锁
    @Test
    public void testOptimisticLocker(){

//        根据id查询数据
        User user = userMapper.selectById(1313116452613533698L);
//        进行修改
        user.setName("夏洛特");

        userMapper.updateById(user);
    }

//    测试乐观锁失败
    @Test
    public void testOptimisticLockerFall(){
        User user = userMapper.selectById(1313111891035361282L);
        user.setName("赵云");
        user.setVersion(user.getVersion() - 1);

        userMapper.updateById(user);
    }

//    单个id的查询
    @Test
    public void testSelectDemo(){
        User user = userMapper.selectById(1313305509511643138L);
        System.out.println(user);
    }
//    多个id的批量查询
    @Test
    public void testSelectDemo1(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(3L, 4L, 5L));
        users.forEach(System.out::println);
    }

//    简单的条件查询
    @Test
    public void testSelectByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Jone");
        map.put("age",18);
        List<User> users = userMapper.selectByMap(map);

        users.forEach(System.out::println);
    }

//    分页查询
    @Test
    public void testPage(){
        //1 创建page对象
        //传入两个参数：当前页、每页记录数
        Page<User> page = new Page<>(1,5);

        //调用mp中分页查询
        //调用用mp分页查询过程，底层封装
        //把分页所有的数据封装到page对象里面
        userMapper.selectPage(page, null);

        // 通过page对象获取分页数据
        System.out.println(page.getCurrent());  //当前页
        System.out.println(page.getRecords());  //每页数据list集合
        System.out.println(page.getSize()); //每页显示记录数
        System.out.println(page.getTotal()); //总记录数
        System.out.println(page.getPages()); //总页数

        System.out.println(page.hasNext()); //是否有下页
        System.out.println(page.hasPrevious()); //是否又上页
    }

//    测试selectMapsPage分页：结果集是Map
    @Test
    public void testSelectMapsPage(){
        Page<User> page = new Page<>(1, 5);
        IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(page, null);

        //mapIpage 获取记录（必须），否则会有数据类型转换错误
        mapIPage.getRecords().forEach(System.out::println);
        System.out.println(page.getCurrent());  //当前页
        System.out.println(page.getRecords());  //每页数据list集合
        System.out.println(page.getSize()); //每页显示记录数
        System.out.println(page.getTotal()); //总记录数
        System.out.println(page.getPages()); //总页数

        System.out.println(page.hasNext()); //是否有下页
        System.out.println(page.hasPrevious()); //是否又上页
    }

//    删除操作
    @Test
    public void testDeleteById(){
        int result = userMapper.deleteById(1313305509511643138L);
        System.out.println(result);
    }

//    批量删除
    @Test
    public void testDeleteBatchId(){
        int result = userMapper.deleteBatchIds(Arrays.asList(1,2));
        System.out.println(result);
    }

//    条件查询删除、
    @Test
    public void testDeleteByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Helen");
        map.put("age",18);

        int result = userMapper.deleteByMap(map);
        System.out.println(result);
    }

//    mp实现复杂查询操作
    @Test
    public void testSelectQuery(){
//        创建querywrapper对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        //通过QueryWrapper设置条件
        //ge、gt、le、lt
        //查询age>=30记录
        //第一个参数字段名称，第二个参数设置值
//        wrapper.ge("age",30);

        //eq、ne
//        wrapper.eq("name","lilei");
//        wrapper.ne("name","lilei");

        //between
        //查询年龄 20-30
//         wrapper.between("age",20,30);

        //like
//        wrapper.like("name","岳");

        //orderByDesc
//         wrapper.orderByDesc("id");

        //last
//        wrapper.last("limit 1");

        //指定要查询的列
        wrapper.select("id","name");

        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);

    }
}