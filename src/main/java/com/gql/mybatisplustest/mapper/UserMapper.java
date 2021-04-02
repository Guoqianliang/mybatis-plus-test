package com.gql.mybatisplustest.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gql.mybatisplustest.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @Description: UserMapper接口
 *  本接口继承BaseMapper接口,继承BaseMapper接口后，无需编写 mapper.xml 文件，即可获得CRUD功能
 *  1).问题1:为什么要在mapper接口上使用@Repository注解?
 *      使用@Repository注解是把接口的一个实现类交给spring管理。如果不加这个注解,IDEA会报错,但是程序却可以正常运行。
 *      那是因为spring配置文件中配置了MapperScannerConfigurer这个bean，它会扫描持久层接口创建实现类并交给spring管理。
 * @author Guoqianliang
 * @date 11:15 - 2021/4/1
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}
