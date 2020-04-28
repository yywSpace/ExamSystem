package com.example.examsystem.mapper;

import com.example.examsystem.entity.Setting;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SettingMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into setting " +
            "values(#{dutyCycle}, #{pageCount}, #{timeThreshold}, #{uploadBytesUpper}, " +
            "#{uploadBytesLower}, #{allowClearAndDelete})")
    void insertSetting(Setting setting);

    @Update("update setting " +
            "set dutyCycle = #{dutyCycle}, pageCount = #{pageCount}, timeThreshold = #{timeThreshold}, " +
            "uploadBytesUpper = #{uploadBytesUpper}, uploadBytesLower = #{uploadBytesLower}, allowClearAndDelete = #{allowClearAndDelete} " +
            "where id = #{id}")
    void updateSetting(Setting setting);

    @Delete("delete from setting where id = #{id}")
    void deleteSettingById(int id);

    @Select("select * from setting where id = #{id}")
    Setting getSettingById(int id);

    @Select("select * from setting")
    List<Setting> getSettings();


}
