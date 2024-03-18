package com.cs401.mpp.business;

import com.cs401.mpp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    public static List<Address> loadAddressData() {
        List<Address> AddList = new ArrayList<>();
        AddList.add(new Address("802 N", "Fairfield", "IA", "52556"));
        AddList.add(new Address("1000 N", "Fairfield", "IA", "52557"));
        return AddList;
    }

    public static List<Instructor> loadInstructorData(List<Address> adList) {
        List<Instructor> instructors = new ArrayList<>();
        instructors.add(new Instructor("IT01", "Obinna", "Kalu", "6412339333", "obinna.kalu@miu.edu", adList.get(0)));
        instructors.add(new Instructor("IT02", "Burmaa", "Enkhbat", "3192339366", "burmaa@miu.edu", adList.get(1)));
        instructors.add(new Instructor("IT03", "Rujuan", "Xing", "6411339722", "rujuan@miu.edu", adList.get(1)));
        instructors.add(new Instructor("IT04", "Sridevi", "Malasani", "6412339462", "sridevi@miu.edu", adList.get(1)));

        // Add more instructors if needed
        return instructors;
    }

    public static List<Student> loadStudentsData(List<Address> adList) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("STD01", LocalDate.of(1998, 8, 11), LocalDate.of(2023, 2, 20),
                "John", "Mike", "3197263847", "John@miu.edu", adList.get(0)));
        students.add(new Student("STD02", LocalDate.of(1992, 4, 20), LocalDate.of(2022, 2, 10),
                "Busing", "Pius", "6412339423", "pius@miu.edu", adList.get(1)));
        students.add(new Student("STD03", LocalDate.of(1993, 5, 20), LocalDate.of(2022, 2, 10),
                "Ade", "Kumar", "3192339423", "ade@miu.edu", adList.get(1)));
        // Add more students if needed
        return students;
    }

    public static List<Admin> loadAdminsData(List<Address> adList) {
        List<Admin> admins = new ArrayList<>();
        admins.add(new Admin("ADM01", "Klop", "Jurgen",
                "3197263847", "John@miu.edu", adList.get(0)));

        admins.add(new Admin("ADM02", "Ronaldo", "Cristiano",
                "6412339423", "pius@miu.edu", adList.get(1)));
        admins.add(new Admin("ADM03",
                "Eric", "Pep", "3192339423", "ade@miu.edu", adList.get(1)));
        // Add more students if needed
        return admins;
    }

    public static List<Course> loadCoursesData() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("CS390", "FPP", "Functional Programming Practice", 3));
        courses.add(new Course("CS401", "MPP", "Modern Programming Practice", 4));
        courses.add(new Course("CS405", "WAP", "Web Application Programming", 4));
        courses.add(new Course("CS545", "WAA", "Web Application Architecture", 4));
        // Add more courses if needed
        return courses;
    }

    public static List<Enrollment> loadEnrollmentData(List<Student> students, List<Section> sections) {
        List<Enrollment> enrollments = new ArrayList<>();
        enrollments.add(new Enrollment(students.get(0), sections.get(0), "B"));
        enrollments.add(new Enrollment(students.get(1), sections.get(1), "A"));
        enrollments.add(new Enrollment(students.get(0), sections.get(2), "B"));
        enrollments.add(new Enrollment(students.get(2), sections.get(3), ""));
        // Add more enrollments if needed
        return enrollments;
    }

    public static List<Section> loadSectionsData(List<Instructor> instructors, List<Course> courses) {
        List<Section> sections = new ArrayList<>();
        sections.add(new Section("SEC01", LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 31),
                courses.get(0), instructors.get(0)));
        sections.add(new Section("SEC02", LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 31),
                courses.get(1), instructors.get(1)));
        sections.add(new Section("SEC03", LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 30),
                courses.get(2), instructors.get(2)));
        sections.add(new Section("SEC04", LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 30),
                courses.get(2), instructors.get(3)));
        sections.add(new Section("SEC05", LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 30),
                courses.get(3), instructors.get(1)));
        // Add more sections if needed
        return sections;
    }
}
