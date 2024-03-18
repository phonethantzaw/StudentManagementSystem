package com.cs401.mpp.model;

import com.cs401.mpp.utils.TableViewUtils;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends Person {
    private String instructorId;
    private List<Section> sections;

    public Instructor(String instructorId, String firstName, String lastName, String phoneNo, String email, Address address) {
        super(firstName, lastName, phoneNo, email, address);
        this.instructorId = instructorId;
        this.sections = new ArrayList<>();
    }

    public String getInstructorId() {
        return instructorId;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections.addAll(sections);
    }

    public void viewCoursesTaught() {

        var headers = List.of("Course Id", "Course Name", "Description", "Start Date", "End Date");

        var data = sections.stream()
                .map(s -> List.of(
                        s.getCourse().getCourseId(),
                        s.getCourse().getName(),
                        s.getCourse().getDescription(),
                        String.valueOf(s.getStartDate()),
                        String.valueOf(s.getEndDate())))
                .toList();

        TableViewUtils.printTable(headers, data);


    }


}
