package com.cs401.mpp;


import com.cs401.mpp.business.StudentManagementSystem;

public class Main {

    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();
        system.loadLogin();
    }

}
