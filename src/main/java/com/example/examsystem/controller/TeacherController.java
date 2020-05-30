package com.example.examsystem.controller;

import com.example.examsystem.entity.*;
import com.example.examsystem.service.*;
import com.example.examsystem.utils.ExcelUtil;
import com.example.examsystem.utils.FileUtil;
import com.example.examsystem.utils.ZipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class TeacherController {
    @Autowired
    TeacherServiceImpl teacherService;
    @Autowired
    StudentExamServiceImpl studentExamService;

    @Autowired
    ExamServiceImpl examService;

    @Autowired
    SettingServiceImpl settingService;
    @Autowired
    StudentServiceImpl studentService;

    @ResponseBody
    @RequestMapping("/teacherLogin")
    public String login(@RequestParam("account") String account, @RequestParam("password") String password, HttpSession session) {
        Teacher teacher = teacherService.login(account, password);

        if (teacher != null) {
            session.setAttribute("teacher", teacher);
            return "success";
        }
        return "error";
    }

    @RequestMapping("/teacherLogout")
    public String logout(HttpSession session) {
        session.setAttribute("teacher", null);
        return "redirect:/login";
    }

    @ResponseBody
    @RequestMapping("/archiveExam")
    public String archiveExam(int id, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
        Exam exam = examService.getExamById(id);
        System.out.println(exam);
        String path = Setting.uploadPath + exam.getName();
        String zipPath = Setting.uploadPath + "zip" + File.separator + exam.getName() + ".zip";
        ZipUtil.createZip(path, zipPath);
        File zipFile = new File(zipPath);
        exam.setArchived(true);
        examService.updateExam(exam);
        System.out.println(exam);
        return FileUtil.download(zipFile, response, request);
    }

    @ResponseBody
    @RequestMapping("/teacherInsertExam")
    public void insertExam(Exam exam, HttpSession session) throws IOException {
        System.out.println(exam.getName() + "" + exam.getStartTime() + "" + exam.getAutoStart());
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        exam.setTeacherId(teacher.getId());
        String folder = Setting.uploadPath + exam.getName();
        Path path = Paths.get(folder);
        Files.createDirectories(path);
        examService.insertExam(exam);
    }

    @RequestMapping("/teacherMainPage")
    public String mainPage(Model model) {
        model.addAttribute("type", "main");
        return "teacher/teacherMainPage";
    }

    @ResponseBody
    @RequestMapping("/startExam")
    public void startExam(int examId) {
        Exam exam = examService.getExamById(examId);
        exam.setRunning(true);
        examService.updateExam(exam);
    }
    @ResponseBody
    @RequestMapping("/closeExam")
    public void closeExam(int examId) {
        Exam exam = examService.getExamById(examId);
        exam.setRunning(false);
        exam.setFinished(true);
        examService.updateExam(exam);
    }

    @RequestMapping("/teacherAddStudentsPage")
    public String addStudentsPage(int examId, Model model) {
        model.addAttribute("examId", examId);
        model.addAttribute("pageSize", settingService.getSetting().getPageCount());
        return "teacher/teacherAddStudentsPage";
    }

    @ResponseBody
    @RequestMapping("/updateExam")
    public void updateExam(Exam exam) {
        System.out.println(exam);
        Exam newExam = examService.getExamById(exam.getId());
        newExam.setName(exam.getName());
        newExam.setStartTime(exam.getStartTime());
        examService.updateExam(newExam);
    }

    @ResponseBody
    @RequestMapping("/insertStudent")
    public void insertStudent(int examId, Student student) {
        System.out.println(examId);
        System.out.println(student.getName());
        studentService.insertStudent(student);
        studentExamService.insertStudentExam(new StudentExam(student.getId(), examId));
    }

    @ResponseBody
    @RequestMapping("/teacherStudentList")
    public Map<String, Object> studentList(int examId, @RequestParam("page") int page, @RequestParam("limit") int limit) {
        List<Student> students = studentExamService.getStudentExamListLimitBy(examId, page, limit);
        int studentExamCount = studentExamService.getStudentExamCount(examId);
        Map<String, Object> tableData = new HashMap<>();
        //这是layui要求返回的json数据格式
        tableData.put("code", 0);
        tableData.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        tableData.put("count", studentExamCount);
        //将分页后的数据返回（每页要显示的数据）
        tableData.put("data", students);
        //返回给前端
        return tableData;
    }

    @ResponseBody
    @RequestMapping("/deleteStudent")
    public void deleteStudent(int examId, String id) {
        studentExamService.deleteByExamIdAndStudentId(examId, id);
    }

    @ResponseBody
    @RequestMapping("/uploadStudentByExcel")
    public Map<String, Object> uploadStudentByExcel(@RequestParam("file") MultipartFile file, int examId) throws Exception {
        File excelFile = FileUtil.multipartFileToFile(file);
        List<Student> students = ExcelUtil.getStudentByExcel(excelFile, "student");
        students.forEach(student -> {
            String id = student.getId();
            if (id.contains(".")) {
                id = id.substring(0, id.lastIndexOf("."));
                student.setId(id);
            }
            studentService.insertStudent(student);
            studentExamService.insertStudentExam(new StudentExam(student.getId(), examId));
        });
        FileUtil.deleteTempFile(excelFile);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "");
        map.put("code", 1);
        return map;
    }


    @RequestMapping("/teacherExamBeforePage")
    public String beforePage(Model model) {
        int examCount = examService.getExamCount();
        model.addAttribute("adminCount", examCount);
        model.addAttribute("allow", settingService.getSetting().isAllowClearAndDelete());
        model.addAttribute("pageSize", settingService.getSetting().getPageCount());
        model.addAttribute("type", "before");
        return "teacher/teacherExamBeforePage";
    }

    @RequestMapping("/teacherModifyExamInfoPage")
    public String modifyExamInfo(int id, Model model) throws ParseException {
        Exam exam = examService.getExamById(id);
        model.addAttribute("exam", exam);
        model.addAttribute("type", "before");
        model.addAttribute("id", id);
        model.addAttribute("studentCount", studentExamService.getStudentExamCount(id));

        Date now = new Date();
        Date examStartTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(exam.getStartTime());
        if ((examStartTime.getTime() - now.getTime()) / 1000 / 60 > settingService.getSetting().getTimeThreshold())
            model.addAttribute("canStart", false);
        else
            model.addAttribute("canStart", true);
        return "teacher/teacherModifyExamInfoPage";
    }

    @RequestMapping("/teacherExamAfterPage")
    public String afterPage(Model model) {
        int examCount = examService.getExamCount();
        model.addAttribute("adminCount", examCount);
        model.addAttribute("allow", settingService.getSetting().isAllowClearAndDelete());
        model.addAttribute("pageSize", settingService.getSetting().getPageCount());
        model.addAttribute("type", "after");
        return "teacher/teacherExamAfterPage";
    }

    @RequestMapping(value = "/uploadExamPaper", method = RequestMethod.POST)
    @ResponseBody
    public String uploadSource(@RequestParam("file") MultipartFile file, int id) {
        Exam exam = examService.getExamById(id);
        String pathString = Setting.uploadPath + exam.getName();
        Path filePath = Paths.get(pathString);
        try {
            if (exam.getPaperName() != null && !exam.getPaperName().equals("")) {
                Path examPath = Paths.get(pathString + File.separator + exam.getPaperName());
                if (Files.exists(examPath)) {
                    Files.delete(examPath);
                }
            }
            if (!Files.exists(filePath))
                Files.createDirectories(filePath);
            File files = new File(filePath.toFile(), Objects.requireNonNull(file.getOriginalFilename()));
            file.transferTo(files);
            exam.setPaperName(file.getOriginalFilename());
            exam.setUploadExamPaper(true);
            examService.updateExam(exam);
            return "{\"code\":0,\"msg\":\"" + files.getAbsolutePath() + "\"}";

        } catch (Exception e) {
            return "{\"code\":-1,\"msg\":\"}";
        }
    }

    @ResponseBody
    @RequestMapping("/downloadExamPaper")
    public String downloadExamPaper(int id, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
        Exam exam = examService.getExamById(id);
        String filePath = Setting.uploadPath + exam.getName() + File.separator + exam.getPaperName();
        return FileUtil.download(new File(filePath), response, request);
    }
}
