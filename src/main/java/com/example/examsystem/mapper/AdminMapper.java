package com.example.examsystem.mapper;

import com.example.examsystem.entity.Admin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AdminMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into admin(name,password) values(#{name}, #{password})")
    void insertAdmin(Admin admin);

    @Update("update admin set name = #{name}, password = #{password} where id = #{id}")
    void updateAdmin(Admin admin);

    @Delete("delete from admin where id = #{id}")
    void deleteAdminById(int id);

    @Select("select * from admin where name = #{name}")
    Admin getAdminByName(String name);

    @Select("select * from admin")
    List<Admin> getAdminList();

    @Select("select count(*) from admin")
    int getAdminCount();
}
