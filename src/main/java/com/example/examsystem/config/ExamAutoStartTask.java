package com.example.examsystem.config;

import com.example.examsystem.entity.Exam;
import com.example.examsystem.service.ExamServiceImpl;
import com.example.examsystem.service.SettingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Configuration
@EnableScheduling
public class ExamAutoStartTask implements SchedulingConfigurer {
    @Autowired
    SettingServiceImpl settingService;
    @Autowired
    ExamServiceImpl examService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(() -> {
                    List<Exam> examList = examService.getAutoStartExamListNotStart();
                    Date now = new Date();
                    for (Exam exam : examList) {
                        System.out.println(exam);
                        try {
                            Date examStartTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(exam.getStartTime());
                            System.out.println(now.before(examStartTime));
                            if (now.after(examStartTime) && exam.getPaperName() != null) {
                                exam.setRunning(true);
                                examService.updateExam(exam);
                                break;
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                },
                triggerContext -> {
                    int minute = settingService.getSetting().getDutyCycle();
                    return new CronTrigger(String.format("0 0/%d * * * ?", minute)).nextExecutionTime(triggerContext);
                });
    }
}
