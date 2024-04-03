package classify.student;
//@@author tayponghee
public class SubjectGrade {
    private String subject;
    private double grade;
    private static int classesAttended;

    public SubjectGrade(String subject, double grade, int classesAttended) {
        this.subject = subject;
        this.grade = grade;
        SubjectGrade.classesAttended = classesAttended;
    }

    public String getSubject() {
        return subject;
    }

    public double getGrade() {
        return grade;
    }

    public static int getClassesAttended() {
        return classesAttended;
    }

    //@@author blackmirag3
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public void setClassesAttended(int classesAttended) {
        SubjectGrade.classesAttended = classesAttended;
    }
}
