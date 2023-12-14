package fr.efrei.student.web.rest;

import fr.efrei.student.domain.Student;
import fr.efrei.student.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-test.properties")

public class StudentResourceIT {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @Transactional
    void createStudent() throws Exception {

        int databaseSizeBeforeCreate = studentRepository.findAll().size();
        assertThat(databaseSizeBeforeCreate).isEqualTo(1);

        Student student = new Student();
        student.setId(2);
        student.setName("Johnny");
        student.setAge(25);
        studentRepository.save(student);

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeCreate + 1);
    }

    @Test
    @Transactional
    void getAllStudents() {

        Student student = new Student();
        student.setId(2);
        student.setName("Patrick");
        student.setAge(33);

        studentRepository.save(student);
        List<Student> students = studentRepository.findAll();
        assertThat(students).isNotEmpty();

    }

    @Test
    void getStudentById() {

        Student student = new Student();
        student.setId(2);
        student.setName("Titouin");
        student.setAge(31);
        Integer studentId = 2;
        studentRepository.save(student);

        Student studentFound = studentRepository.findById(studentId).orElse(null);
        System.out.println(studentRepository.findAll());

        assertThat(studentFound).isNotNull();
        assertThat(studentFound.getId()).isEqualTo(studentId);
    }
    @Test
    @Transactional
    void updateStudent() {

        Student student = new Student();
        student.setId(1);
        student.setName("Nathalie");
        student.setAge(31);
        studentRepository.save(student);

        Integer studentId = 1;
        Student existingStudent = studentRepository.findById(studentId).orElse(null);

        assertThat(existingStudent).isNotNull();


        String updatedName = "Updated Name";
        existingStudent.setName(updatedName);

        studentRepository.save(existingStudent);


        Student updatedStudent = studentRepository.findById(studentId).orElse(null);
        assertThat(updatedStudent).isNotNull();
        assertThat(updatedStudent.getName()).isEqualTo(updatedName);
    }
    @Test
    @Transactional
    void deleteStudent() {

        Student student = new Student();
        student.setId(1);
        student.setName("Nathalie");
        student.setAge(31);
        Integer studentId = 1;

        studentRepository.save(student);

        Student existingStudent = studentRepository.findById(studentId).orElse(null);

        assertThat(existingStudent).isNotNull();

        studentRepository.deleteById(studentId);

        Student deletedStudent = studentRepository.findById(studentId).orElse(null);
        assertThat(deletedStudent).isNull();
    }
}