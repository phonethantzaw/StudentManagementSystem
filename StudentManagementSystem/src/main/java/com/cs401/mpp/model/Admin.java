package com.cs401.mpp.model;

import com.cs401.mpp.utils.TableViewUtils;

import java.util.ArrayList;
import java.util.List;

public class Admin extends Person {
    private String adminId;

    private List<Student> students;


    public Admin(String adminId, String firstName, String lastName, String phoneNo, String email, Address address) {
        super(firstName, lastName, phoneNo, email, address);
        this.adminId = adminId;
        this.students = new ArrayList<>();
    }

    public void addNewStudent(Student student) {
        students.add(student);
    }


    public void updateStudentInfo(Student student) {
        //get
    }

    public String getAdminId() {
        return adminId;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }


    public void viewAllStudents(){
        var headers = List.of("Student Id", "Name");
        var data = students.stream()
                .map(t -> List.of(
                        t.getStudentId(),
                        t.getFullName()))
                .toList();

        TableViewUtils.printTable(headers, data);

    }
}
