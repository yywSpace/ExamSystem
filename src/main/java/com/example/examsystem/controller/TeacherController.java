package com.example.examsystem.controller;

import com.example.examsystem.entity.*;
import com.example.examsystem.service.*;
import com.example.examsystem.utils.ExcelUtil;
import com.example.examsystem.utils.FileUtil;
import com.example.examsystem.utils.PasswordUtil;
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
    StudentAnswerServiceImpl studentAnswerService;
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

    @Autowired
    MessageServiceImpl messageService;

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

    @RequestMapping("/changePassword")
    public String changePassword(String password, HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        teacher.setPassword(PasswordUtil.getMD5(password));
        teacherService.updateTeacher(teacher);
        return "redirect:/teacherMainPage";
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
        Exam exam = examService.getRunningExam();
        if (exam == null) {
            model.addAttribute("examStatus", "no");
        } else {
            model.addAttribute("examStatus", "yes");
        }
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
        Exam exam = examService.getRunningExam();
        if (exam == null) {
            model.addAttribute("examStatus", "no");
        } else {
            model.addAttribute("examStatus", "yes");
        }
        model.addAttribute("examId", examId);
        model.addAttribute("type", "before");
        model.addAttribute("pageSize", settingService.getSetting().getPageCount());
        return "teacher/teacherAddStudentsPage";
    }

    @RequestMapping("/teacherExamStudentInfoPage")
    public String teacherExamStudentInfoPage(Model model) {
        Exam exam = examService.getRunningExam();
        if (exam == null) {
            model.addAttribute("examStatus", "no");
        } else {
            model.addAttribute("examStatus", "yes");
        }
        model.addAttribute("examId", exam.getId());
        model.addAttribute("type", "middle");
        model.addAttribute("pageSize", settingService.getSetting().getPageCount());
        return "teacher/teacherExamStudentInfoPage";
    }

    @RequestMapping("/teacherExamSummaryPage")
    public String examSummaryPage(Model model) {
        Exam exam = examService.getRunningExam();
        if (exam == null) {
            model.addAttribute("examStatus", "no");
        } else {
            model.addAttribute("examStatus", "yes");
        }
        int allStudentCount = studentExamService.getStudentExamCount(exam.getId());
        int loginCount = studentExamService.getStudentExamLoginCount(exam.getId());
        int uploadCount = studentAnswerService.getStudentAnswerCountByExamId(exam.getId());
        model.addAttribute("exam", exam);
        model.addAttribute("type", "middle");
        model.addAttribute("allStudentCount", allStudentCount);
        model.addAttribute("loginCount", loginCount);
        model.addAttribute("uploadCount", uploadCount);
        return "teacher/teacherExamSummaryPage";
    }

    @RequestMapping("/teacherLoginStudentListPage")
    public String loginStudentListPage(Model model) {
        Exam exam = examService.getRunningExam();
        if (exam == null) {
            model.addAttribute("examStatus", "no");
        } else {
            model.addAttribute("examStatus", "yes");
        }
        int loginCount = studentExamService.getStudentExamLoginCount(exam.getId());
        model.addAttribute("examId", exam.getId());
        model.addAttribute("type", "middle");
        model.addAttribute("loginCount", loginCount);
        return "teacher/teacherExamStudentLoginListPage";
    }

    @RequestMapping("/teacherNotLoginStudentListPage")
    public String teacherNotLoginStudentListPage(Model model) {
        Exam exam = examService.getRunningExam();
        if (exam == null) {
            model.addAttribute("examStatus", "no");
        } else {
            model.addAttribute("examStatus", "yes");
        }
        int allStudentCount = studentExamService.getStudentExamCount(exam.getId());
        int loginCount = studentExamService.getStudentExamLoginCount(exam.getId());
        model.addAttribute("examId", exam.getId());
        model.addAttribute("type", "middle");
        model.addAttribute("allStudentCount", allStudentCount);
        model.addAttribute("loginCount", loginCount);
        return "teacher/teacherExamStudentLoginNotListPage";
    }

    @RequestMapping("/teacherUploadStudentListPage")
    public String uploadStudentListPage(Model model) {
        Exam exam = examService.getRunningExam();
        if (exam == null) {
            model.addAttribute("examStatus", "no");
        } else {
            model.addAttribute("examStatus", "yes");
        }
        List<Student> students  =studentAnswerService.getUploadStudents(exam.getId());
        model.addAttribute("examId", exam.getId());
        model.addAttribute("type", "middle");
        model.addAttribute("uploadCount", students.size());
        return "teacher/teacherExamStudentUploadListPage";
    }

    @RequestMapping("/teacherNotUploadStudentListPage")
    public String teacherNotUploadStudentListPage(Model model) {
        Exam exam = examService.getRunningExam();
        if (exam == null) {
            model.addAttribute("examStatus", "no");
        } else {
            model.addAttribute("examStatus", "yes");
        }
        int allStudentCount = studentExamService.getStudentExamCount(exam.getId());
        List<Student> students  =studentAnswerService.getUploadStudents(exam.getId());
        model.addAttribute("examId", exam.getId());
        model.addAttribute("type", "middle");
        model.addAttribute("allStudentCount", allStudentCount);
        model.addAttribute("uploadCount", students.size());
        return "teacher/teacherExamStudentUploadNotListPage";
    }
    @ResponseBody
    @RequestMapping("/uploadStudentList")
    public Map<String, Object> uploadStudentList() {
        Exam exam = examService.getRunningExam();
        List<Student> students  =studentAnswerService.getUploadStudents(exam.getId());
        Map<String, Object> tableData = new HashMap<>();
        //这是layui要求返回的json数据格式
        tableData.put("code", 0);
        tableData.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        tableData.put("count", students.size());
        //将分页后的数据返回（每页要显示的数据）
        tableData.put("data", students);
        //返回给前端
        return tableData;
    }

    @ResponseBody
    @RequestMapping("/unUploadStudentList")
    public Map<String, Object> unUploadStudentList() {
        Exam exam = examService.getRunningExam();
        List<Student> students  =studentAnswerService.getUnUploadStudents(exam.getId());
        Map<String, Object> tableData = new HashMap<>();
        //这是layui要求返回的json数据格式
        tableData.put("code", 0);
        tableData.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        tableData.put("count", students.size());
        //将分页后的数据返回（每页要显示的数据）
        tableData.put("data", students);
        //返回给前端
        return tableData;
    }
    @ResponseBody
    @RequestMapping("/loginStudentList")
    public Map<String, Object> loginStudentList() {
        Exam exam = examService.getRunningExam();
        List<Student> students = studentExamService.getLoginStudentList(exam.getId());
        Map<String, Object> tableData = new HashMap<>();
        //这是layui要求返回的json数据格式
        tableData.put("code", 0);
        tableData.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        tableData.put("count", students.size());
        //将分页后的数据返回（每页要显示的数据）
        tableData.put("data", students);
        //返回给前端
        return tableData;
    }

    @ResponseBody
    @RequestMapping("/unLoginStudentList")
    public Map<String, Object> unLoginStudentList() {
        Exam exam = examService.getRunningExam();
        List<Student> students = studentExamService.getNotLoginStudentList(exam.getId());
        Map<String, Object> tableData = new HashMap<>();
        //这是layui要求返回的json数据格式
        tableData.put("code", 0);
        tableData.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        tableData.put("count", students.size());
        //将分页后的数据返回（每页要显示的数据）
        tableData.put("data", students);
        //返回给前端
        return tableData;
    }

    @RequestMapping("/teacherUnlockStudentPage")
    public String teacherUnlockStudentPage(Model model) {
        Exam exam = examService.getRunningExam();
        if (exam == null) {
            model.addAttribute("examStatus", "no");
        } else {
            model.addAttribute("examStatus", "yes");
        }
        model.addAttribute("examId", exam.getId());
        model.addAttribute("type", "middle");
        model.addAttribute("pageSize", settingService.getSetting().getPageCount());
        return "teacher/teacherExamUnlockStudentPage";
    }

    @RequestMapping("/teacherNotifyPage")
    public String teacherNotifyPage(Model model) {
        Exam exam = examService.getRunningExam();
        if (exam == null) {
            model.addAttribute("examStatus", "no");
        } else {
            model.addAttribute("examStatus", "yes");
        }
        model.addAttribute("examId", exam.getId());
        model.addAttribute("type", "middle");
        model.addAttribute("pageSize", settingService.getSetting().getPageCount());
        return "teacher/teacherExamNotifyPage";
    }


    @ResponseBody
    @RequestMapping("/insertMessage")
    public void insertMessage(int examId, String content) {
        messageService.insertMessage(
                new Message(
                        examId,
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
                        content)
        );
    }

    @ResponseBody
    @RequestMapping("/deleteMessage")
    public void deleteMessage(int id) {
        messageService.deleteMessageById(id);
    }

    @ResponseBody
    @RequestMapping("/messageList")
    public Map<String, Object> messageList(int examId, @RequestParam("page") int page, @RequestParam("limit") int limit) {
        List<Message> messages = messageService.getMessageListLimitBy(examId, page, limit);
        int messageCount = messageService.getMessageCount(examId);
        Map<String, Object> tableData = new HashMap<>();
        //这是layui要求返回的json数据格式
        tableData.put("code", 0);
        tableData.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        tableData.put("count", messageCount);
        //将分页后的数据返回（每页要显示的数据）
        tableData.put("data", messages);
        //返回给前端
        return tableData;
    }

    @ResponseBody
    @RequestMapping("/updateExam")
    public void updateExam(Exam exam) {
        System.out.println(exam);
        Exam newExam = examService.getExamById(exam.getId());
        newExam.setName(exam.getName());
        newExam.setStartTime(exam.getStartTime());
        newExam.setAutoStart(exam.getAutoStart());
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
    @RequestMapping("/queryStudentList")
    public Map<String, Object> queryStudentList(int examId, Student student, @RequestParam("page") int page, @RequestParam("limit") int limit) {
        System.out.println(examId);
        System.out.println(page);
        System.out.println(student);
        List<Student> students = studentExamService.getStudentExamByQuery(examId, student, page, limit);
        int studentExamCount = studentExamService.getStudentExamCountByQuery(examId, student);
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
    @RequestMapping("/queryStudentListByIP")
    public Map<String, Object> queryStudentListByIP(int examId, String ip, @RequestParam("page") int page, @RequestParam("limit") int limit) {
        List<Student> students = studentExamService.getStudentExamByIp(examId, ip, page, limit);
        int studentExamCount = studentExamService.getStudentExamCountByIp(examId, ip);
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
    @RequestMapping("/teacherExamList")
    public Map<String, Object> teacherExamList(@RequestParam("page") int page, @RequestParam("limit") int limit, HttpSession session) {
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        List<Exam> examList = examService.getTeacherExamLimitBy(teacher.getId(), page, limit);
        int examCount = examService.getTeacherExamCount(teacher.getId());
        Map<String, Object> tableData = new HashMap<>();
        //这是layui要求返回的json数据格式
        tableData.put("code", 0);
        tableData.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        tableData.put("count", examCount);
        //将分页后的数据返回（每页要显示的数据）
        tableData.put("data", examList);
        //返回给前端
        return tableData;
    }

    @ResponseBody
    @RequestMapping("/deleteStudent")
    public void deleteStudent(int examId, String id) {
        studentExamService.deleteByExamIdAndStudentId(examId, id);
    }

    @ResponseBody
    @RequestMapping("/resetIp")
    public void resetIp(String id) {
        Student student = studentService.getStudentById(id);
        student.setIp("");
        studentService.updateStudent(student);
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
        Exam exam = examService.getRunningExam();
        if (exam == null) {
            model.addAttribute("examStatus", "no");
        } else {
            model.addAttribute("examStatus", "yes");
        }
        int examCount = examService.getExamCount();
        model.addAttribute("adminCount", examCount);
        model.addAttribute("allow", settingService.getSetting().isAllowClearAndDelete());
        model.addAttribute("pageSize", settingService.getSetting().getPageCount());
        model.addAttribute("type", "before");
        return "teacher/teacherExamBeforePage";
    }

    @RequestMapping("/teacherModifyExamInfoPage")
    public String modifyExamInfo(int id, Model model) throws ParseException {
        Exam runningExam = examService.getRunningExam();
        if (runningExam == null) {
            model.addAttribute("examStatus", "no");
        } else {
            model.addAttribute("examStatus", "yes");
        }
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
        Exam exam = examService.getRunningExam();
        if (exam == null) {
            model.addAttribute("examStatus", "no");
        } else {
            model.addAttribute("examStatus", "yes");
        }
        int examCount = examService.getExamCount();
        model.addAttribute("adminCount", examCount);
        model.addAttribute("allow", settingService.getSetting().isAllowClearAndDelete());
        model.addAttribute("pageSize", settingService.getSetting().getPageCount());
        model.addAttribute("type", "after");
        return "teacher/teacherExamAfterPage";
    }

    @RequestMapping(value = "/uploadExamPaper", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> uploadSource(@RequestParam("file") MultipartFile file, int id) {
        Exam exam = examService.getExamById(id);
        Map<String,Object> map = new HashMap<>();
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
            System.out.println(files.getAbsolutePath());
            map.put("code",0);
            map.put("msg",files.getAbsolutePath());
            return map;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            map.put("code",-1);
            map.put("msg","");
            return  map;
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
