package Model;

public class Module {

    private String courseName;
    private String courseId;
    private String moduleName;
    private int yearCategory;

    public Module(String courseName, String courseId, String moduleName, int yearCategory) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.moduleName = moduleName;
        this.yearCategory = yearCategory;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getYearCategory() {
        return yearCategory;
    }

    public void setYearCategory(int yearCategory) {
        this.yearCategory = yearCategory;
    }
}
