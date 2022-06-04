package com.gmail.at.kotamadeo.student;

import com.gmail.at.kotamadeo.utils.Utils;

import java.util.Objects;

public class Student {
    private String surname;
    private String name;
    private String group;
    private String studentId;

    public Student(String surname, String name, String group, String studentId) {
        this.surname = surname;
        this.name = name;
        this.group = group;
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        var student = (Student) o;
        return studentId.equals(student.studentId);
    }

    @Override
    public String toString() {
        return Utils.ANSI_PURPLE + "Студент " + group + "\nФИО: " + surname + " " + name + "\nИдентификатор: " +
                studentId + Utils.ANSI_RESET;
    }
}
