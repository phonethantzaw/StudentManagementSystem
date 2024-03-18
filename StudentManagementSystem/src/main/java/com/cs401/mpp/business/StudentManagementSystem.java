package com.cs401.mpp.business;

import com.cs401.mpp.model.*;
import com.cs401.mpp.utils.TableViewUtils;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StudentManagementSystem {

    private List<Address> adList;
    private List<Instructor> instructors;
    private List<Student> students;
    private List<Course> courses;
    private List<Section> sections;
    private List<Enrollment> enrollments;
    private Scanner sc;

    // ANSI escape code for bold text
    final String ANSI_BOLD = "\u001B[1m";
    // ANSI escape code to reset text attributes
    final String ANSI_RESET = "\u001B[0m";

    public StudentManagementSystem() {
        adList = DataLoader.loadAddressData();
        instructors = DataLoader.loadInstructorData(adList);
        students = DataLoader.loadStudentsData(adList);
        courses = DataLoader.loadCoursesData();
        sections = DataLoader.loadSectionsData(instructors, courses);
        enrollments = DataLoader.loadEnrollmentData(students, sections);

        sc = new Scanner(System.in);
    }

    public void loadLogin() {
        System.out.println();
        System.out.printf("%s %s %s \n", ANSI_BOLD, "Student Management System", ANSI_RESET);
        System.out.println();

        System.out.println("Please Login");
        System.out.println("1. Admin");
        System.out.println("2. Student");
        System.out.println("3. Instructor");

        System.out.println("Please choose the login role:");
        var roleId = sc.nextInt();

        switch (roleId) {
            case 1:
                break;
            case 2:
                loadStudent();
                break;
            case 3:
                loadInstructor();
                break;
            default:
                System.out.println("Default");
                break;

        }

        sc.close();
    }


    private void loadInstructor() {
        System.out.println("Instructor List:");

        var headers = List.of("Instructor Id", "Name");
        var data = instructors.stream()
                .map(t -> List.of(
                        t.getInstructorId(),
                        t.getFullName()))
                .toList();

        TableViewUtils.printTable(headers, data);

//        System.out.println("Instructor Id\t\tName");
//        instructors.forEach(s -> System.out.printf("%s\t\t\t\t%s \n", s.getInstructorId(), s.getFullName()));

        System.out.println("Please login to the system by entering the specified Instructor Id:");

        var instId = sc.next();
        instId = instId.toUpperCase();
        var instOptional = findInstructorById(instId);
        if (instOptional.isEmpty()) {
            System.out.println("Instructor Id Not Found.");
            return;
        }

        var instructor = instOptional.get();

        //assign Enrollment to Student
        var stdInstructors = sections.stream()
                .filter(s -> s.getInstructor().getInstructorId().equals(instructor.getInstructorId()))
                .toList();

        instructor.setSections(stdInstructors);

//        var stdEnrollments = enrollments.stream()
//                .filter(e -> e.getStudent().getStudentId().equals(instructor.getStudentId()))
//                .toList();
//        instructor.setEnrollments(stdEnrollments);

        System.out.printf("\n %s Welcome Professor %s %s \n", ANSI_BOLD, instructor.getFullName(), ANSI_RESET);

        instructorActions(instructor);
    }

    private void instructorActions(Instructor instructor) {
        System.out.println();
        System.out.println("=== Instructor Action ===");
        System.out.println("1. View All Course Sections");
        System.out.println("2. View Enroll Students By Course Section");
        System.out.println("3. Logout");
        System.out.println("4. Exit");

        var instAction = sc.nextInt();

        switch (instAction) {
            case 1:
                instructor.viewCoursesTaught();
                instructorActions(instructor);
                break;
            case 2:
                viewEnrolledStudentBySection(instructor);
//                registerCourse(student);
                instructorActions(instructor);
                break;
            case 3:
                loadLogin();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Default");
                break;
        }
    }

    private void viewEnrolledStudentBySection(Instructor instructor) {

        System.out.println("Please Choose Course");

        var availableCourse = getAvalialbeCourseByInstructorId(instructor.getInstructorId());

        //Print to the console
        int count = 0;
        for (String course : availableCourse) {
            System.out.printf("%d. %s \n", ++count, course);
        }

        //get course input from user
        var course = sc.nextInt();

        // convert input integer course to string display course
        String inputCourse = availableCourse.get(--course);

        showEnrolledStudentByChosenCourse(instructor.getInstructorId(), inputCourse);


    }

    private void loadStudent() {
        System.out.println("Student List:");

//        System.out.println("Student Id\t\tName");
//        students.forEach(s -> System.out.printf("%s\t\t\t%s \n", s.getStudentId(), s.getFullName()));

        var headers = List.of("Student Id", "Name");
        var data = students.stream()
                .map(t -> List.of(
                        t.getStudentId(),
                        t.getFullName()))
                .toList();

        TableViewUtils.printTable(headers, data);


        System.out.println("Please login to the system by entering the specified Student Id:");

        var stdId = sc.next();
        stdId = stdId.toUpperCase();
        var studentOptional = findStudentById(stdId);
        if (studentOptional.isEmpty()) {
            System.out.println("** Student Id Not Found. **");
            return;
        }

        var student = studentOptional.get();
        //assign Enrollment to Student
        var stdEnrollments = enrollments.stream()
                .filter(e -> e.getStudent().getStudentId().equals(student.getStudentId()))
                .toList();
        student.setEnrollments(stdEnrollments);

        System.out.printf("\n %s Welcome %s %s \n", ANSI_BOLD, student.getFullName(), ANSI_RESET);

        studentActions(student);
    }

    private void studentActions(Student student) {
        System.out.println();
        System.out.println("=== Student Action ===");
        System.out.println("1. View Enrolled courses");
        System.out.println("2. Register Course");
        System.out.println("3. Logout");
        System.out.println("4. Exit");

        var stdAction = sc.nextInt();


        switch (stdAction) {
            case 1:
                student.viewEnrollCourses();
                studentActions(student);
                break;
            case 2:
                registerCourse(student);
                studentActions(student);
                break;
            case 3:
                loadLogin();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Default");
                break;
        }
    }


    private void registerCourse(Student student) {

        System.out.println("Please Choose Month");
        var availableMonths = getAvailableMonths();

        //Print to the console
        int count = 0;
        for (String month : availableMonths) {
            System.out.printf("%d. %s \n", ++count, month);
        }

        //get month input from user
        var month = sc.nextInt();
        // convert input integer month to string display month
        String inputMonth = availableMonths.get(--month);

        showAvailableCourseSectionsByChosenMonth(inputMonth);

        System.out.println("Please Choose The Section Block Id ");
        var sectionId = sc.next();
        var sectionOptional = getSectionBySectionId(sectionId);

        if (sectionOptional.isEmpty()) {
            System.out.println("** Please choose the correct section Id **");
            return;
        }

        var section = sectionOptional.get();
        var enrollment = student.registerCourse(section);
        enrollments.add(enrollment);
        System.out.printf("Successfully Enroll in  %s \n", section.getCourse().getName());


    }

    private List<String> getAvalialbeCourseByInstructorId(String instId) {
        return sections.stream()
                .filter(s -> s.getInstructor().getInstructorId().equals(instId))
                .map(s -> s.getCourse().getName())
                .toList();
    }

    public void showEnrolledStudentByChosenCourse(String instructorId, String inputCourse) {

        // show available cause sections by course
        System.out.printf("Enrolled Student from %s Class\n", inputCourse);

       var studList = enrollments.stream()
                .filter(e -> e.getSection().getInstructor().getInstructorId().equals(instructorId) && e.getSection().getCourse().getName().equals(inputCourse))
                .map(Enrollment::getStudent)
                .toList();


        var headers = List.of("Student Id", "Name", "Email");
        var data = studList.stream()
                .map(student -> List.of(student.getStudentId(), student.getFullName(), student.getEmail()))
                .collect(Collectors.toList());

        TableViewUtils.printTable(headers, data);

    }



    private List<String> getAvailableMonths() {
        // get available months from section
        return sections
                .stream()
                .map(s -> s.getStartDate().getMonth().toString())
                .distinct()
                .toList();
    }

    private void showAvailableCourseSectionsByChosenMonth(String inputMonth) {
        // show available cause sections by month

        System.out.printf("Available Courses in %s \n", inputMonth);

        var headers = List.of("SectionId", "Course Name", "Instructor");
        var data = sections.stream()
                .filter(s -> s.getStartDate().getMonth().toString().equals(inputMonth))
                .map(s -> List.of(
                        s.getSectionId(),
                        s.getCourse().getName(),
                        s.getInstructor().getFullName()
                ))
                .toList();

        TableViewUtils.printTable(headers, data);


    }

    private Optional<Section> getSectionBySectionId(String sectionId) {
        return sections.stream()
                .filter(s -> s.getSectionId().equalsIgnoreCase(sectionId))
                .findFirst();

    }


    private Optional<Instructor> findInstructorById(String id) {
        return instructors.stream().filter(s -> s.getInstructorId().equals(id)).findFirst();
    }

    private Optional<Student> findStudentById(String id) {
        return students.stream().filter(s -> s.getStudentId().equals(id)).findFirst();
    }
}