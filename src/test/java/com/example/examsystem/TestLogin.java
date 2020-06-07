package com.example.examsystem;
import static org.junit.Assert.*;
import com.example.examsystem.controller.TeacherController;
import com.example.examsystem.entity.Student;
import com.example.examsystem.entity.Teacher;
import com.example.examsystem.mapper.TeacherMapper;
import com.example.examsystem.service.*;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.easymock.*;
import org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.naming.Name;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.AssertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={ExamsystemApplication.class})
public class TestLogin   {



    @Autowired
    TeacherServiceImpl ts;

    @Autowired
    StudentServiceImpl ss;

    @Autowired
    MessageServiceImpl ms;


    @Test
    public void testLogin(){
        Teacher teacher1 = ts.login("teacher","111");
        int id = 67;
        assertEquals(id,teacher1.getId());

    }
    @Test
    public void testTeacherCount(){
        int res = ts.getTeacherCount();
        int num = 2;
        assertEquals(num,res);
    }

    @Test
    public void testdeleteTeacherById(){
        ts.deleteTeacherById(67);
        int res = ts.getTeacherCount();
        assertEquals(2,res);
    }
    @Test
    public void testgetStudentCount(){
        int num = ss.getStudentCount();
        assertEquals(207,num);
    }

    @Test
    public void testinsertStudent(){
        int num = ss.getStudentCount();
        Student student = new Student("11","11","11","");
        ss.insertStudent(student);
        int res = ss.getStudentCount();
        assertNotEquals(num+1,res);
    }
    @Test
    public void testgetStudentById(){
        Student student = ss.getStudentById("1");
        String name = "1";
        assertEquals(name,student.getName());

    }

    @Test
    public void testgetMessageCount(){
        int  examId = 55;
        int num = ms.getMessageCount(examId);
        assertEquals(5,num);

    }


}
