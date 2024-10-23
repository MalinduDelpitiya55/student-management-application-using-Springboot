package edu.icet.crm.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.crm.entity.StudentEntity;
import edu.icet.crm.exception.StudentNotFoundException;
import edu.icet.crm.model.Student;
import edu.icet.crm.repository.JpaRepository;
import edu.icet.crm.repository.StudentRepository;
import edu.icet.crm.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    @Autowired
    JpaRepository jpaRepository;

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ObjectMapper mapper;

    @Override
    public Student persist(Student student) {
        StudentEntity savedStudent = jpaRepository.save(
                mapper.convertValue(student, StudentEntity.class)
        );
        return mapper.convertValue(savedStudent,Student.class);
    }

    @Override
    public List<Student> retriveAllStudents() {
        Iterable<StudentEntity> studentList = jpaRepository.findAll();
        List<Student> studentModel = new ArrayList<>();
            studentList.forEach(studentEntity -> {
                studentModel.add(
                    mapper.convertValue(studentEntity, Student.class));
        });
        return studentModel;
    }

    @Override
    public Student retriveStudentById(Integer studentId) {
        Optional<StudentEntity> student = jpaRepository.findById(studentId);
        if (student.isEmpty())
            throw new StudentNotFoundException("Student not found");
        return mapper.convertValue(student,Student.class);
    }

    @Override
    public Student removeStudent(Integer studentID) {
        Optional<StudentEntity> student = jpaRepository.findById(studentID);
        if (student.isEmpty()) throw new StudentNotFoundException("Student not found");
        jpaRepository.delete(student.get());
        return mapper.convertValue(student,Student.class);
    }

    @Override
    public Student updateStudent(Student student) {
        Student currentStdData = mapper.convertValue(jpaRepository.findById(Integer.valueOf(student.getId())), Student.class);
        if (!currentStdData.isValidStudent())
            throw new StudentNotFoundException("Student not found");

        if (!student.getStdName().isEmpty() && currentStdData.getStdName()!=student.getStdName())
            currentStdData.setStdName(student.getStdName());

        if (!student.getStdAge().isEmpty() && currentStdData.getStdAge()!=student.getStdAge())
            currentStdData.setStdAge(student.getStdAge());

        if (!student.getStdContactNo().isEmpty() && currentStdData.getStdContactNo()!=student.getStdContactNo())
            currentStdData.setStdContactNo(student.getStdContactNo());

        if (!student.getGuardianName().isEmpty() && currentStdData.getGuardianName()!=student.getGuardianName())
            currentStdData.setGuardianName(student.getGuardianName());

        if (!student.getGuardianAddress().isEmpty() && currentStdData.getGuardianAddress()!=student.getGuardianAddress())
            currentStdData.setGuardianAddress(student.getGuardianAddress());

        if (!student.getGuardianContactNo().isEmpty() && currentStdData.getGuardianContactNo()!=student.getGuardianContactNo())
            currentStdData.setGuardianContactNo(student.getGuardianContactNo());


        StudentEntity savedStudent = jpaRepository.save(
                mapper.convertValue(currentStdData, StudentEntity.class)
        );
        return mapper.convertValue(savedStudent,Student.class);
    }
}
