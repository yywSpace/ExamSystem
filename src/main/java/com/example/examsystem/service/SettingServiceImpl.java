package com.example.examsystem.service;

import com.example.examsystem.entity.Setting;
import com.example.examsystem.mapper.SettingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingServiceImpl implements SettingService {
    @Autowired
    SettingMapper settingMapper;

    @Override
    public Setting getSetting() {
        List<Setting> settings = settingMapper.getSettings();
        if (settings.size() == 0) {
            Setting setting = new Setting();
            setting.setAllowClearAndDelete(true);
            setting.setDutyCycle(1);
            setting.setPageCount(10);
            setting.setTimeThreshold(30);
            setting.setUploadBytesLower(0);
            setting.setUploadBytesUpper(5120);
            settingMapper.insertSetting(setting);
            return setting;
        } else
            return settings.get(0);
    }

    @Override
    public void updateSetting(Setting setting) {
        settingMapper.updateSetting(setting);
    }

    @Override
    public void restoreToDefault(int id) {
        Setting setting = new Setting();
        setting.setId(id);
        setting.setAllowClearAndDelete(true);
        setting.setDutyCycle(1);
        setting.setPageCount(10);
        setting.setTimeThreshold(30);
        setting.setUploadBytesLower(0);
        setting.setUploadBytesUpper(5120);
        settingMapper.updateSetting(setting);
    }
}
