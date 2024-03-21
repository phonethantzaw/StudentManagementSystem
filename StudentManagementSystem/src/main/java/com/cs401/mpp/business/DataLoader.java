package com.cs401.mpp.business;

import com.cs401.mpp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataLoader {
    public static List<Address> loadAddressData() {
        List<Address> AddList = new ArrayList<>();
        AddList.add(new Address("802 N 8 st", "Fairfield", "IA", "52556"));
        AddList.add(new Address("1000 N", "Fairfield", "IA", "52557"));
        AddList.add(new Address("2124 Granville Ave", "Fairfield", "IA", "52557"));
        AddList.add(new Address("200 N Main", "Fairfield", "IA", "52556"));
        AddList.add(new Address("2701 W Burlington Ave", "Fairfield", "IA", "52556"));

        return AddList;
    }

    public static List<Instructor> loadInstructorData(List<Address> adList) {
        List<Instructor> instructors = new ArrayList<>();
        instructors.add(new Instructor("IT01", "Burmaa", "Enkhbat", "3192339366", "burmaa.enkhbat@miu.edu", adList.get(2)));
        instructors.add(new Instructor("IT02", "Obinna", "Kalu", "6412339333", "obinna.kalu@miu.edu", adList.get(1)));
        instructors.add(new Instructor("IT03", "Asaad", "Saad", "3190039311", "asaad.saad@miu.edu", adList.get(2)));
        instructors.add(new Instructor("IT04", "Premchand", "Nair", "6412339462", "premchand.nair@miu.edu", adList.get(3)));
        instructors.add(new Instructor("IT05", "Ankhtuya", "Ochirbat", "3190039300", "ankhtuya.ochirbat@miu.edu", adList.get(3)));
        instructors.add(new Instructor("IT06", "Tacettin Umur", "Inan", "6412449461", "tacettin.inan@miu.edu", adList.get(3)));
        instructors.add(new Instructor("IT07", "Payman", "Salek", "6412449461", "payman.salek@miu.edu", adList.get(2)));


        // Add more instructors if needed
        return instructors;
    }

    public static List<Student> loadStudentsData(List<Address> adList) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("STD01", LocalDate.of(1998, 8, 11), LocalDate.of(2023, 12, 20),
                "Daniel", "White", "3190339333", "daniel.white@miu.edu", adList.get(0)));
        students.add(new Student("STD02", LocalDate.of(1992, 4, 20), LocalDate.of(2023, 12, 20),
                "Mia", "Wilson", "6412339423", "mia.wilson@miu.edu", adList.get(1)));
        students.add(new Student("STD03", LocalDate.of(1993, 5, 13), LocalDate.of(2023, 12, 20),
                "Elizabeth", "Martin", "6410339311", "elizabeth.martin@miu.edu", adList.get(1)));
        students.add(new Student("STD04", LocalDate.of(1996, 3, 9), LocalDate.of(2023, 12, 25),
                "Lucas", "Wilson", "6410339456", "lucas.wilson@miu.edu", adList.get(2)));
        students.add(new Student("STD05", LocalDate.of(1992, 9, 19), LocalDate.of(2023, 12, 25),
                "Oliver", "Williams", "6410339456", "oliver.williams@miu.edu", adList.get(2)));
        students.add(new Student("STD06", LocalDate.of(1999, 3, 25), LocalDate.of(2023, 12, 25),
                "Alexander", "Taylor", "3191339446", "alexander.taylor@miu.edu", adList.get(2)));
        students.add(new Student("STD07", LocalDate.of(1996, 3, 9), LocalDate.of(2023, 12, 18),
                "Sofia", "Thompson", "3190339456", "sofia.thompson@miu.edu", adList.get(2)));
        students.add(new Student("STD08", LocalDate.of(1999, 8, 19), LocalDate.of(2023, 12, 21),
                "Emily", "Taylor", "3191339457", "emily.taylor@miu.edu", adList.get(2)));
        students.add(new Student("STD09", LocalDate.of(1996, 3, 9), LocalDate.of(2023, 12, 26),
                "Ella", "Robinson", "6410009459", "ella.robinson@miu.edu", adList.get(2)));
        students.add(new Student("STD10", LocalDate.of(1996, 3, 9), LocalDate.of(2023, 12, 31),
                "William", "Brown", "3190339412", "william.brown@miu.edu", adList.get(2)));

        // Add more students if needed
        return students;
    }

    public static List<Admin> loadAdminsData(List<Address> adList) {
        List<Admin> admins = new ArrayList<>();
        admins.add(new Admin("ADM01", "Ethan", "Jackson",
                "3197263847", "ethan.jackson@miu.edu", adList.get(4)));
        admins.add(new Admin("ADM02", "Jack", "Robinson",
                "6412339423", "jack.robinson@miu.edu", adList.get(3)));

        // Add more admins if needed
        return admins;
    }

    public static List<Course> loadCoursesData() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("CS390", "FPP", "Functional Programming Practice", 3));
        courses.add(new Course("CS401", "MPP", "Modern Programming Practice", 4));
        courses.add(new Course("CS472", "WAP", "Web Application Programming", 4));
        courses.add(new Course("CS572", "MWA", "Modern Web Applications", 4));
        courses.add(new Course("CS522", "BD", "Big Data", 4));
        courses.add(new Course("CS544", "EA", "Enterprise Architecture", 4));
        courses.add(new Course("CS545", "WAA", "Web Application Architecture", 4));
        courses.add(new Course("CS422", "DBMS", "Database Management Systems ", 4));
        // Add more courses if needed
        return courses;
    }

    public static List<Enrollment> loadEnrollmentData(List<Student> students, List<Section> sections) {

        List<Enrollment> enrollments = new ArrayList<>();
        IntStream.range(0, 10)
                .forEach(i -> {
                    enrollments.add(new Enrollment(students.get(i), sections.get(0), "B"));
                    enrollments.add(new Enrollment(students.get(i), sections.get(1), "B"));
                });

        // Add more enrollments if needed
        return enrollments;
    }

    public static List<Section> loadSectionsData(List<Instructor> instructors, List<Course> courses) {
        List<Section> sections = new ArrayList<>();
        sections.add(new Section("SEC01", LocalDate.of(2024, 2, 1), LocalDate.of(2024, 2, 29),
                courses.get(0), instructors.get(0)));
        sections.add(new Section("SEC02", LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 31),
                courses.get(1), instructors.get(1)));
        sections.add(new Section("SEC03", LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 30),
                courses.get(2), instructors.get(4)));
        sections.add(new Section("SEC04", LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 30),
                courses.get(3), instructors.get(2)));
        sections.add(new Section("SEC05", LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 30),
                courses.get(4), instructors.get(3)));
        sections.add(new Section("SEC06", LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 30),
                courses.get(6), instructors.get(5)));
        sections.add(new Section("SEC07", LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 31),
                courses.get(7), instructors.get(5)));
        sections.add(new Section("SEC08", LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 31),
                courses.get(5), instructors.get(6)));


        // Add more sections if needed
        return sections;
    }
}
