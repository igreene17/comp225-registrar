package edu.macalester.registrar;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class Student {
    private String name;
    private Set<Course> courses = new HashSet<Course>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Course> getCourses() {
        return Collections.unmodifiableSet(courses);
    }

    /**
     * Add this student to the given course's roster.
     * Has no effect if the student is already registered.
     * Equivalent to course.enroll(student).
     */
    public boolean enrollIn(Course course) {
        if (course.getStudents().contains(this)){
            return true;
        }
        if (course.getStudents().size() < course.getEnrollmentLimit() ) {
            courses.add(course);
            course.enroll(this);
            return true;
        }
        else {
                if( !(course.getStudents().contains(this))&& !(course.getWaitList().contains(this)) ){
                    course.addToWaitList(this);
                    }
            return false;}


        }
    public void drop(Course course) {
        courses.remove(course);
        course.drop(this);
        if(course.getWaitList()!= null){
            Student stud = course.getFirstPerson();
            course.enroll(stud);
        }
    }
}
