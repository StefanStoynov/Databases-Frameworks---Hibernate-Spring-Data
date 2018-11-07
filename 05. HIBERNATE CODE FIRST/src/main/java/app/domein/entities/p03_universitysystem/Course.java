package app.domein.entities.p03_universitysystem;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity(name = "courses")
public class Course extends BaseEntity {
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private int credits;
    private Set<Student> students;
    private Teacher teacher;

    public Course() {
    }

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "start_date")
    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Column(name = "end_date")
    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Column(name = "credits")
    public int getCredits() {
        return this.credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @ManyToMany(targetEntity = Student.class, mappedBy = "courses")
    public Set<Student> getStudents() {
        return this.students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @ManyToOne(targetEntity = Teacher.class)
    public Teacher getTeacher() {
        return this.teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
