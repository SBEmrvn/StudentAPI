package com.student.question2studentapi.controller.student;

import com.student.question2studentapi.model.student.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST Controller for Student Registration API
 * Handles all HTTP requests for student operations
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    // In-memory storage for students
    private List<Student> students = new ArrayList<>();
    private Long nextId = 1L;

    /**
     * Constructor - Initialize with 5 sample students
     */
    public StudentController() {
        students.add(new Student(nextId++, "John", "Doe", "john.doe@university.edu", "Computer Science", 3.8));
        students.add(new Student(nextId++, "Jane", "Smith", "jane.smith@university.edu", "Engineering", 3.9));
        students.add(new Student(nextId++, "Michael", "Johnson", "michael.j@university.edu", "Computer Science", 3.5));
        students.add(new Student(nextId++, "Emily", "Brown", "emily.b@university.edu", "Business", 3.7));
        students.add(new Student(nextId++, "David", "Wilson", "david.w@university.edu", "Engineering", 3.2));
    }

    /**
     * GET /api/students
     * Returns all students
     * Status: 200 OK
     */
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(students);
    }

    /**
     * GET /api/students/{studentId}
     * Returns a specific student by ID
     * Status: 200 OK if found, 404 NOT FOUND if not found
     */
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        Optional<Student> student = students.stream()
                .filter(s -> s.getStudentId().equals(studentId))
                .findFirst();
        
        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * GET /api/students/major/{major}
     * Get all students by major (path variable)
     * Status: 200 OK
     */
    @GetMapping("/major/{major}")
    public ResponseEntity<List<Student>> getStudentsByMajor(@PathVariable String major) {
        List<Student> matchingStudents = students.stream()
                .filter(s -> s.getMajor().equalsIgnoreCase(major))
                .toList();
        
        return ResponseEntity.ok(matchingStudents);
    }

    /**
     * GET /api/students/filter?gpa={minGpa}
     * Filter students with GPA greater than or equal to minimum
     * Status: 200 OK
     */
    @GetMapping("/filter")
    public ResponseEntity<List<Student>> filterStudentsByGpa(@RequestParam Double gpa) {
        List<Student> matchingStudents = students.stream()
                .filter(s -> s.getGpa() >= gpa)
                .toList();
        
        return ResponseEntity.ok(matchingStudents);
    }

    /**
     * POST /api/students
     * Register a new student
     * Status: 201 CREATED
     */
    @PostMapping
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        student.setStudentId(nextId++);
        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    /**
     * PUT /api/students/{studentId}
     * Update student information
     * Status: 200 OK if updated, 404 NOT FOUND if student doesn't exist
     */
    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student updatedStudent) {
        Optional<Student> existingStudent = students.stream()
                .filter(s -> s.getStudentId().equals(studentId))
                .findFirst();
        
        if (existingStudent.isPresent()) {
            Student student = existingStudent.get();
            // Update fields (keep the same ID)
            student.setFirstName(updatedStudent.getFirstName());
            student.setLastName(updatedStudent.getLastName());
            student.setEmail(updatedStudent.getEmail());
            student.setMajor(updatedStudent.getMajor());
            student.setGpa(updatedStudent.getGpa());
            
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}