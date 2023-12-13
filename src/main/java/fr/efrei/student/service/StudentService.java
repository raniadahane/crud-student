package fr.efrei.student.service;

import fr.efrei.student.domain.Student;
import fr.efrei.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    public final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> getStudentById(Integer id) {
        return studentRepository.findById(id);
    }
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    public Student updateStudent(Integer id, Student updatedStudent) {
        Optional<Student> existingStudent = studentRepository.findById(id);

        if (existingStudent.isPresent()) {
            Student student = existingStudent.get();
            student.setName(updatedStudent.getName());
            student.setAge(updatedStudent.getAge());
            return studentRepository.save(student);
        } else {

            return null;
        }
    }

    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

}