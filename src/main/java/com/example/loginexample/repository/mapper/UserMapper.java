package com.example.loginexample.repository.mapper;

import com.example.loginexample.domain.User;
import java.time.LocalDateTime;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

@Mapper
public interface UserMapper {

  @Select("SELECT * FROM USER WHERE user_id = #{userId}")
  @Results(id = "User", value = {
      @Result(id = true, column = "user_id", property = "userId"),
      @Result(column = "name", property = "name"),
      @Result(column = "password", property = "password"),
      @Result(column = "created_at", property = "createdAt"),
      @Result(column = "updated_at", property = "updatedAt"),
      @Result(column = "lock_version", property = "lockVersion"),
      @Result(column = "delete_flag", property = "deleteFlag")
  })
  User findById(@Param("userId") Long userId);

  @ResultMap("User")
  @Select("SELECT * FROM USER WHERE name = #{name}")
  User findByName(@Param("name") String name);

  @InsertProvider(type = UserSqlProvider.class, method = "insert")
  void insert(User user);

  @UpdateProvider(type = UserSqlProvider.class, method = "update")
  void update(User user);

  @DeleteProvider(type = UserSqlProvider.class, method = "logicalDelete")
  void delete(Long userId);

  class UserSqlProvider {

    public String insert(User user) {
      return new SQL()
          .INSERT_INTO("USER")
          .VALUES("name, password", "#{name}, #{password}")
          .toString();
    }

    public String update(User user) {
      return new SQL()
          .UPDATE("USER")
          .SET("name = #{name}")
          .SET("password = #{password}")
          .SET("lock_version = lock_version + 1")
          .SET("updated_at = \"" + LocalDateTime.now().toString() + "\"")
          .WHERE("user_id = #{userId}")
          .toString();
    }

    public String logicalDelete(Long userId) {
      return new SQL()
          .UPDATE("USER")
          .SET("delete_flag = 1")
          .SET("lock_version = lock_version + 1")
          .SET("updated_at = \"" + LocalDateTime.now().toString() + "\"")
          .WHERE("user_id = #{userId}")
          .toString();
    }
  }

}
