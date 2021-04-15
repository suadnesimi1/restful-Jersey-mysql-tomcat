package Repository;

import Connection.DbConnection;
import Domain.Course;
import Domain.Exam;
import Domain.ExamGrade;
import Domain.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentRepo {

    private PreparedStatement ps;
    private ResultSet rs;

    public Student createStudent(Student student) {
        String sql = "insert into students(id,name,faculty_id)value(?,?,?)";
        try {
            ps = DbConnection.getConnection().prepareStatement(sql);
            ps.setString(1, student.getId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getFacultyId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            System.out.println("Its running");
            DbConnection.closeAll(ps);
        }
        return student;
    }


    public List<Student> getStudent() {
        List<Student> studentList = new ArrayList<>();
        String sql = "select * from students";
        try {
            ps = DbConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getString(1));
                student.setName(rs.getString(2));
                student.setFacultyId(rs.getString(3));
                studentList.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            DbConnection.closeAll(ps);
        }
        return studentList;
    }

    public Student getStudentByName(String name) {
        Student student = null;
        String sql = "select st.id, st.name,st.faculty_id,c.id, c.name from students st\n" +
                "left join studentrelation sc on st.id = sc.student_id\n" +
                "right join courses c on sc.course_id = c.id where st.name=?";
        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (student == null) {
                    student = new Student(rs);
                }
                String courseId = rs.getString("c.id");
                if (courseId != null) {
                    Course course = new Course();
                    course.setId(courseId);
                    course.setName(rs.getString("c.name"));
                    student.getCourses().add(course);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbConnection.closeConnection(connection);
            DbConnection.closeAll(ps);
        }
        return student;
    }

    public Student getStudentById(String id) {
        Student student = null;
        String sql = "select st.id,st.name as studentname,e.id,eg.grade as examgrade from students st\n" +
                "inner join exam_grade eg on st.id = eg.student_id\n" +
                "inner join exams e on eg.exam_id = e.id where student_id =?";
        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (student == null) {
                    student = new Student(rs);
                }
                String ExamId = rs.getString("e.id");
                if (ExamId != null) {
                    Exam exam = new Exam();
                    exam.setId(ExamId);
                    exam.setGrade(rs.getString("examgrade"));
                    student.getExam().add(exam);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbConnection.closeConnection(connection);
            DbConnection.closeAll(ps);
        }
        return student;
    }

    public Student getStudentGrade(String id) {
        Student student = null;
        String sql = "select s.id,s.name,c.name ,fg.grade from courses c\n" +
                "left join final_grade fg on c.id = fg.course_id\n" +
                "left join students s on fg.student_id = s.id where student_id=?";
        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (student == null) {
                    student = new Student(rs);
                }
                String courseId = rs.getString("c.id");
                if (courseId != null) {
                    Course course = new Course();
                    course.setId(courseId);
                    course.setName(rs.getString("c.name"));
                    course.setFinalGrade(rs.getString("fg.grade"));
                    student.getCourses().add(course);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbConnection.closeConnection(connection);
            DbConnection.closeAll(ps);
        }
        return student;

    }

    // kjo eshte version i pare me arraylist dhe me for loop

    public List<Student> getStudentsFull() {
        List<Student> students = new ArrayList<>();
        String sql = "select s.id, s.name,c.id, c.name, fg.grade from students s\n" +
                "    left join studentrelation sc on s.id = sc.student_id\n" +
                "    left join courses c on sc.course_id = c.id\n" +
                "    left join final_grade fg on s.id = fg.student_id and c.id = fg.course_id;";

        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Student student = null;
                String studentId = rs.getString("s.id");
                for (Student s : students) {
                    if (s.getId().equals(studentId)) {
                        student = s;
                        break;
                    }
                }
                if (student == null) {
                    student = new Student();
                    student.setId(studentId);
                    student.setName(rs.getString("s.name"));
                    student.setCourses(new ArrayList<>());
                    students.add(student);
                }
                String courseId = rs.getString("c.id");
                if (courseId != null) {
                    Course course = new Course();
                    course.setId(courseId);
                    course.setName(rs.getString("c.name"));
                    course.setFinalGrade(rs.getString("fg.grade"));
                    student.getCourses().add(course);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            DbConnection.closeAll(ps);
            DbConnection.closeConnection(connection);
        }

        return students;
    }

    // ky eshte versioni i dyte me hashmap - te cilen e perdorim
    public List<Student> getStudentsFullV2() {
        HashMap<String, Student> studentsMap = new HashMap<>();
        String sql = "select s.id, s.name,s.faculty_id, c.id, c.name, fg.grade from students s\n" +
                "    left join studentrelation sc on s.id = sc.student_id\n" +
                "    left join courses c on sc.course_id = c.id\n" +
                "    left join final_grade fg on s.id = fg.student_id and c.id = fg.course_id";

        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String studentId = rs.getString("s.id");
                Student student = studentsMap.get(studentId);
                if (student == null) {
                    student = new Student();
                    student.setId(studentId);
                    student.setName(rs.getString("s.name"));
                    student.setFacultyId(rs.getString("s.faculty_id"));
                    student.setCourses(new ArrayList<>());
                    studentsMap.put(studentId, student);
                }
                String courseId = rs.getString("c.id");
                if (courseId != null) {
                    Course course = new Course();
                    course.setId(courseId);
                    course.setName(rs.getString("c.name"));
                    course.setFinalGrade(rs.getString("fg.grade"));
                    student.getCourses().add(course);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            DbConnection.closeAll(ps);
            DbConnection.closeConnection(connection);
        }

        return new ArrayList<>(studentsMap.values());
    }

    // ky eshte versioni trete me arraylist po me stream ne vend te for loop
    public List<Student> getStudentsFullV3() {
        List<Student> students = new ArrayList<>();
        String sql = "select s.id, s.name, c.id, c.name, fg.grade from students s\n" +
                "    left join studentrelation sc on s.id = sc.student_id\n" +
                "    left join courses c on sc.course_id = c.id\n" +
                "    left join final_grade fg on s.id = fg.student_id and c.id = fg.course_id;";

        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String studentId = rs.getString("s.id");
                Student student = students.stream().filter(s -> s.getId()
                        .equals(studentId)).findFirst().orElse(null);
                if (student == null) {
                    student = new Student();
                    student.setId(studentId);
                    student.setName(rs.getString("s.name"));
                    student.setCourses(new ArrayList<>());
                    students.add(student);
                }
                String courseId = rs.getString("c.id");
                if (courseId != null) {
                    Course course = new Course();
                    course.setId(courseId);
                    course.setName(rs.getString("c.name"));
                    course.setFinalGrade(rs.getString("fg.grade"));
                    student.getCourses().add(course);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            DbConnection.closeAll(ps);
            DbConnection.closeConnection(connection);
        }
        return students;
    }

    /*
     * get students examsid and exams grade
     */
    public List<Student> getStudentExam() {
        HashMap<String, Student> studentHashMap = new HashMap<>();
        String query = "select s.id,s.name,s.faculty_id,eg.exam_id,eg.grade from students s\n" +
                "left join exam_grade eg on s.id = eg.student_id\n" +
                "left join exams e on eg.exam_id = e.id;";
        Connection connection = null;
        try {
            connection = DbConnection.getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String studentId = rs.getString("s.id");
                Student student = studentHashMap.get(studentId);
                if (student == null) {
                    student = new Student();
                    student.setId(studentId);
                    student.setName(rs.getString("s.name"));
                    student.setFacultyId(rs.getString("s.faculty_id"));
                    student.setExamGrades(new ArrayList<>());
                    studentHashMap.put(studentId, student);
                }
                String examGradeId = rs.getString("s.id");
                if (examGradeId!=null){
                    ExamGrade examGrade = new ExamGrade();
                    examGrade.setExamId(examGradeId);
                    examGrade.setStudentId(rs.getString("s.id"));
                    examGrade.setGrade(rs.getString("eg.grade"));
                    student.getExamGrades().add(examGrade);
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            DbConnection.closeAll(ps);
            DbConnection.closeConnection(connection);
            return new ArrayList<>(studentHashMap.values());
        }

    }
}
