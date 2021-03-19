package com.stu.video.mapper;

import com.stu.video.entity.UserFans;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (UserFans)表数据库访问层
 *
 * @author kimijiaqili
 * @since 2021-03-18 09:33:59
 */
@Repository
public interface UserFansDao {

    /**
     * 通过ID查询单条数据
     *
     * @param username 主键
     * @return 实例对象
     */
    UserFans queryById(String username);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserFans> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userFans 实例对象
     * @return 对象列表
     */
    List<UserFans> queryAll(UserFans userFans);

    /**
     * 新增数据
     *
     * @param userFans 实例对象
     * @return 影响行数
     */
    int insert(UserFans userFans);

    /**
     * 修改数据
     *
     * @param userFans 实例对象
     * @return 影响行数
     */
    int update(UserFans userFans);

    /**
     * 通过主键删除数据
     *
     * @param username 主键
     * @return 影响行数
     */
    int deleteById(String username);

    UserFans queryRelationShipByUsername(String username, String fansUsername);

    int deleteByUsername(String username, String fansUsername);

}