package com.stu.video.mapper;

import com.stu.video.entity.UserLikeVideo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (UserLikeVideo)表数据库访问层
 *
 * @author kimijiaqili
 * @since 2021-03-19 10:45:39
 */
@Repository
public interface UserLikeVideoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param username 主键
     * @return 实例对象
     */
    UserLikeVideo queryById(String username);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserLikeVideo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userLikeVideo 实例对象
     * @return 对象列表
     */
    List<UserLikeVideo> queryAll(UserLikeVideo userLikeVideo);

    /**
     * 新增数据
     *
     * @param userLikeVideo 实例对象
     * @return 影响行数
     */
    int insert(UserLikeVideo userLikeVideo);

    /**
     * 修改数据
     *
     * @param userLikeVideo 实例对象
     * @return 影响行数
     */
    int update(UserLikeVideo userLikeVideo);

    /**
     * 通过主键删除数据
     *
     * @param username 主键
     * @return 影响行数
     */
    int deleteById(String username, Integer videoId);

}