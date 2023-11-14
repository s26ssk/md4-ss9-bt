    package com.ra.controller;

    import com.ra.dto.StudentDTO;
    import com.ra.model.entity.ClassRoom;
    import com.ra.model.service.ClassRoomService;
    import com.ra.model.service.StudentService;

    import java.io.*;
    import java.text.ParseException;
    import java.text.SimpleDateFormat;
    import java.util.Date;
    import java.util.List;
    import javax.servlet.ServletException;
    import javax.servlet.http.*;
    import javax.servlet.annotation.*;

    @WebServlet(name = "StudentServlet", value = "/student")
    public class  StudentServlet extends HttpServlet {
        private StudentService studentService;
        private ClassRoomService classRoomService;
        @Override
        public void init() {
            studentService = new StudentService();
            classRoomService = new ClassRoomService();
        }

        @Override
        public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");
            String action = req.getParameter("action");
            if(action == null){
                showStudentList(req,resp);
            }
            switch (action) {
                case "add":
                    List<ClassRoom> list = classRoomService.findAll();
                    req.setAttribute("list",list);
                    req.getRequestDispatcher("views/student-add.jsp").forward(req,resp);
                    break;
                case "edit":
                    int studentIdToEdit = Integer.parseInt(req.getParameter("id"));
                    StudentDTO studentToEdit = studentService.findId(studentIdToEdit);
                    List<ClassRoom> classList = classRoomService.findAll();
                    req.setAttribute("studentToEdit", studentToEdit);
                    req.setAttribute("classList", classList);
                    req.getRequestDispatcher("views/student-edit.jsp").forward(req, resp);
                    break;
                case "delete":
                    int studentIdToDelete = Integer.parseInt(req.getParameter("id"));
                    studentService.delete(studentIdToDelete);
                    showStudentList(req, resp);
                    break;
                default:
                    break;
            }

        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");

            String action = req.getParameter("action");

            if ("update".equals(action)) {
                updateStudent(req, resp);
            } else {
                String name = req.getParameter("name");
                String dateStr = req.getParameter("birthday");
                Integer classId = Integer.parseInt(req.getParameter("classId"));

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date birthday = null;

                try {
                    birthday = formatter.parse(dateStr);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                StudentDTO student = new StudentDTO();
                student.setName(name);
                student.setClassId(classId);
                student.setBirthday(new java.sql.Date(birthday.getTime()));
                studentService.create(student);

                showStudentList(req, resp);
            }
        }

        @Override
        public void destroy() {

        }
        public void showStudentList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            List<StudentDTO> student = studentService.findAll();
            req.setAttribute("student", student);
            req.getRequestDispatcher("views/student.jsp").forward(req, resp);
        }

        private void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            // Lấy thông tin từ request
            int studentId = Integer.parseInt(req.getParameter("id")); // Lấy studentId từ parameter "id"
            String name = req.getParameter("name");
            String dateStr = req.getParameter("birthday");
            int classId = Integer.parseInt(req.getParameter("classId"));

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = null;
            try {
                birthday = formatter.parse(dateStr);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            // Tạo đối tượng StudentDTO để chứa thông tin sinh viên cần cập nhật
            StudentDTO student = new StudentDTO();
            student.setId(studentId);
            student.setName(name);
            student.setClassId(classId);
            student.setBirthday(new java.sql.Date(birthday.getTime()));
            // Gọi service để thực hiện cập nhật thông tin sinh viên
            studentService.update(student, studentId);
            // Hiển thị danh sách sinh viên sau khi cập nhật
            showStudentList(req, resp);
        }

    }