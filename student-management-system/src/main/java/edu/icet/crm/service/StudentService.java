package edu.icet.crm.service;

import edu.icet.crm.model.Student;

import java.util.List;

public interface StudentService {
    Student persist(Student student);

    List<Student> retriveAllStudents();

    Student retriveStudentById(Integer studentId);

    Student removeStudent(Integer studentID);

    Student updateStudent(Student student);
}
