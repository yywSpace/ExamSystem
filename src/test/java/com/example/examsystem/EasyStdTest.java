package com.example.examsystem;

import static org.junit.Assert.*;

import com.example.examsystem.entity.Student;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class EasyStdTest {

    public static Student student=new Student("1","姓名-1","17-1");
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        student = EasyMock.createMock(Student.class);
        System.out.println("开始测试");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("结束测试");
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("在每个测试方法执行前都会执行的代码段");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("在每个测试方法执行后都会执行的代码段");
    }


    @Ignore
    @Test
    public void testToString() {
        fail("Not yet implemented");
    }
    @Ignore
    @Test
    public void testSetJob() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetAge() {
        //创建模拟类型对象，必须重新创造一个mock，否则会测试失败
        student = EasyMock.createMock(Student.class);
        //设置模拟对象的方法及期望方法返回的值
        EasyMock.expect(student.getName()).andReturn("姓名-1");
        //重置该类型对象
        EasyMock.replay(student);
        //junit单元测试
        assertEquals("姓名-1", student.getName());
        //easymock验证该方法
        EasyMock.verify(student);
    }
    @Ignore
    @Test
    public void testGetLocal() {
        student = new Student();
        assertEquals("1", student.getId());
    }
    @Ignore
    @Test
    public void testSetLocal() {
        fail("Not yet implemented");
    }

}