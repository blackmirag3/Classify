package classify.student;

import java.time.LocalDate;
import java.util.List;

/**
 * Meant to represent a particular student of the tuition centre.
 * The student can first be declared with only a name, and the subsequent details
 * added later.
 */
public class Student {
    private static final String SUBJECTINFOSEPARATOR = " ## ";
    private static final String SUBJECTSEPARATIOR = " #--# ";

    //@@author Cryolian
    public String name;

    //@@author tayponghee
    private StudentAttributes attributes;
    private int totalClassesAttended;

    //@@author Cryolian
    public Student(String name) {
        this.name = name;
        this.attributes = new StudentAttributes(this);
    }

    //@@ author tayponghee
    public int getTotalClassesAttended() {
        return totalClassesAttended;
    }

    public void setTotalClassesAttended(int totalClassesAttended) {
        this.totalClassesAttended = totalClassesAttended;
    }

    public void setLastPaymentDate(LocalDate lastPaymenDate) {
        this.attributes.setLastPaymentDate(lastPaymenDate);
    }

    //@@author tayponghee
    public StudentAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(StudentAttributes attributes) {
        this.attributes = attributes;
    }

    public String getGender() {
        return attributes.getGender();
    }

    public int getPhoneNumber() {
        return attributes.getPhoneNumber();
    }

    public LocalDate getLastPaymentDate() {
        return attributes.getLastPaymentDate();
    }
    public boolean hasSubject(String subject) {
        for (SubjectGrade subjectGrade : attributes.getSubjectGrades()) {
            if (subjectGrade.getSubject().equalsIgnoreCase(subject)) {
                return true;
            }
        }
        return false;
    }

    //@@author ParthGandhiNUS
    /**
     * 
     * This code accesses the current students' Subject details.
     * It then uses a Stringbuilder to build the string in the proper format to be used
     * for the Student_Information text file 
     * 
     * @return Returns a String to store all subject attributes associated with a certain student.
     */
    public String listOfSubjectAttributes(){
        List<SubjectGrade> currentStudent = this.getAttributes().getSubjectGrades();

        StringBuilder subjectInfo = new StringBuilder("");
        StringBuilder separateSubjectInfo = new StringBuilder(SUBJECTINFOSEPARATOR);
        StringBuilder separateSubject = new StringBuilder(SUBJECTSEPARATIOR);
        //Checks for an empty list
        if (currentStudent.isEmpty()){
            return subjectInfo.toString();
        }

        int numberOfSubjects = currentStudent.size();

        for (int i = 0; i < numberOfSubjects ; i++){
            //Subject Name
            StringBuilder subject = new StringBuilder(currentStudent.get(i).getSubject());
            //Grade information
            StringBuilder gradeInformation = doubleToString(currentStudent.get(i).getGrade());
            //Class Attended Information
            StringBuilder classInformation = integerToString(currentStudent.get(i).getClassesAttended());           

            subjectInfo.append(subject);
            subjectInfo.append(separateSubjectInfo);
            subjectInfo.append(gradeInformation);
            subjectInfo.append(separateSubjectInfo);
            subjectInfo.append(classInformation);
            subjectInfo.append(separateSubject);
        }
        return subjectInfo.toString();
    }

    /**
     * 
     * Converts an double input to StringBuilder form
     * 
     * @param input     an integer which needs to be converted to a StringBuilder Format
     * @return          Stringbuilder format of the double value
     */
    public static StringBuilder doubleToString(Double input){
        String outputData = Double.toString(input);
        StringBuilder output = new StringBuilder(outputData);
        return output;
    }

    /**
     * Converts an integer input to StringBuilder form
     * 
     * @param input     an integer which needs to be converted to a StringBuilder Format 
     * @return          Stringbuilder format of the integer value
     */
    public static StringBuilder integerToString(Integer input){
        String outputData = Integer.toString(input);
        StringBuilder output = new StringBuilder(outputData);
        return output;
    }

    /**
     * 
     * Builds a string in the proper storing format
     * 
     * @return      a String in the correct format to be stored 
     *              in the Student_Information Text File
     */
    public String textFileInputString() {
        return String.format("%s ~~ %s ~~ %s ~~ %s ~~ %s ~~ %s",
            getName().trim(), 
            getAttributes().getGender(), 
            getAttributes().getPhoneNumber(),
            getAttributes().getLastPaymentDate(),
            getAttributes().getRemarks(),
            listOfSubjectAttributes());
    }

    //@@author Cryolian
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public void printStringNumber() {
        System.out.println(this.name + ": " + String.valueOf(this.getPhoneNumber()));
    }
}
