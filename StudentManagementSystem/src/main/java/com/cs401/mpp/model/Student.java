package com.cs401.mpp.model;

import com.cs401.mpp.utils.TableViewUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person {

    private String studentId;
    private LocalDate dateOfBirth;
    private LocalDate enrollmentDate;
    private List<Enrollment> enrollments;


    public Student(String studentId, LocalDate dateOfBirth, LocalDate enrollmentDate, String firstName, String lastName, String phoneNo, String email, Address address) {
        super(firstName, lastName, phoneNo, email, address);
        this.studentId = studentId;
        this.enrollmentDate = enrollmentDate;
        this.dateOfBirth = dateOfBirth;
        this.enrollments = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments.addAll(enrollments);
//        this.enrollments = enrollments;
    }

    public void viewEnrollCourses() {

        var headers = List.of("Course Id", "Course Name", "Credits", "Grade");

        var data = enrollments.stream()
                .map(s -> List.of(
                        s.getSection().getCourse().getCourseId(),
                        s.getSection().getCourse().getName(),
                        String.valueOf(s.getSection().getCourse().getCredit()),
                        s.getGrade()))
                .toList();

        TableViewUtils.printTable(headers, data);

    }


    public Enrollment registerCourse(Section section) {
        Enrollment enrollment = new Enrollment(this, section, "");
        this.enrollments.add(enrollment);
        return enrollment;
    }

    public boolean isAlreadyRegisteredCourse(String courseName) {
        return enrollments.stream()
                .anyMatch(i -> i.getSection().getCourse().getName().equals(courseName));

    }
}