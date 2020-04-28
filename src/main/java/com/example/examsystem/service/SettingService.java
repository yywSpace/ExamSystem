package com.example.examsystem.service;

import com.example.examsystem.entity.Setting;

public interface SettingService {
    Setting getSetting();
    void updateSetting(Setting setting);
    void restoreToDefault(int id);
}
