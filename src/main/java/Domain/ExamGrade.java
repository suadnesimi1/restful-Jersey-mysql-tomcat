package Domain;

public class ExamGrade {
    String studentId;
    String examId;
    String grade;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String student_id) {
        this.studentId=studentId;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String exam_id) {
        this.examId=examId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
