package edu.icet.crm.controller;

import edu.icet.crm.exception.StudentNotFoundException;
import edu.icet.crm.model.Student;
import edu.icet.crm.model.SuccessResponse;
import edu.icet.crm.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/students")
    public void getAllStudents() {
        studentService.retriveAllStudents();
    }

    @GetMapping("/student/id/{id}")
    public Student getStudentsById(@PathVariable(name = "id") Integer studentID) {
        if (studentID == null) {
            throw new StudentNotFoundException("Student Enter Valid ID");
        }
        return studentService.retriveStudentById(studentID);
    }

    @PostMapping("/student")
    public SuccessResponse saveCustomer(@RequestBody Student student) {
        if (!student.isValidStudent())
            throw new StudentNotFoundException("Student details not found");
        Student std = studentService.persist(student);
        return SuccessResponse.builder().data(std).status("Successfull").message("Student Added Successfull").build();
    }

    @DeleteMapping("/student/id/{id}")
    public SuccessResponse deleteStudent(@PathVariable(name = "id") Integer studentID) {
        if (studentID == null)
            throw new StudentNotFoundException("Student Enter Valid ID");

        Student deletedStudent = studentService.removeStudent(studentID);
        return SuccessResponse.builder().data(deletedStudent).message("Student Deleted Success").status("Success").build();
    }

    @PutMapping("/student")
    public SuccessResponse updateStudent(@RequestBody Student student) {
        if (!student.isValidStudent())
            throw new StudentNotFoundException("Student details not found");
        Student updatedStudent = studentService.updateStudent(student);
        return SuccessResponse.builder().data(updatedStudent).message("Student Update Success").status("Success").build();
    }

}
