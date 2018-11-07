package app.domein.entities.p03_universitysystem;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "students")
public class Student extends Person {
    private double averageGrade;
    private boolean attendance;
    private Set<Course> courses;

    public Student() {
    }

    @Column(name = "acerage_grade")
    public double getAverageGrade() {
        return this.averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    @Column(name = "attendance")
    public boolean isAttendance() {
        return this.attendance;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }

    @ManyToMany()
    @JoinTable(name = "students_courses"
            , joinColumns = @JoinColumn(name = "courses", referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "students", referencedColumnName = "id"))
    public Set<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
