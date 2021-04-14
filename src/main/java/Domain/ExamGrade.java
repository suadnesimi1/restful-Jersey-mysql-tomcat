package Domain;

public class ExamGrade {
    String studentId;
    String examId;
    String grade;

    public String getStudent_id() {
        return studentId;
    }

    public void setStudent_id(String student_id) {
        this.studentId=student_id;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String exam_id) {
        this.examId=exam_id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
