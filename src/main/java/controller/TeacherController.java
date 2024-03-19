package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.*;

public class TeacherController {

    public ArrayList<Teacher> teacherList = new ArrayList();
    private Teacher currentUser = null;
    public ArrayList<Student> studentList = new ArrayList();
    private Sql sql = null;

    public TeacherController(Sql sql) {
        this.sql = sql;
        this.loadStudent();
        this.loadTeacher();
    }

    public boolean login() {
        int account = Inputter.getIntFromInput("account: ");
        String password = Inputter.getStringFromInput("password: ");
        for (Teacher teacher : teacherList) {
            if (teacher.getTeacherId() == account && teacher.getPassword().equals(password)) {
                System.out.println("Login success.");
                this.currentUser = teacher;
                return true;
            }
        }
        System.err.println("Login failed! Try again.\n");
        return false;
    }
    
    public void logout() {
        this.currentUser = null;
    }

    public boolean isAdmin() {
        return currentUser.isIsAdmin();
    }

    private void loadTeacher() {
        ResultSet resultSet = this.sql.syncWithDatabase("SELECT", "Teacher", 0, null);
        try {
            while (resultSet.next()) {
                teacherList.add(new Teacher(resultSet.getInt("teacherId"), resultSet.getString("name"), resultSet.getString("address"), resultSet.getString("password"), resultSet.getString("subject"), resultSet.getString("phoneNumber"), resultSet.getBoolean("isAdmin")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     private void loadStudent() {
        ResultSet resultSet = this.sql.syncWithDatabase("SELECT", "Student", 0, null);
        try {
            while (resultSet.next()) {
                studentList.add(new Student(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("address"), resultSet.getString("phoneNumber"), resultSet.getString("teacherId")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addStudent() {
        studentList.add(new Student(Inputter.getIntFromInput("student Id"), Inputter.getStringFromInput("name"), Inputter.getStringFromInput("address"), Inputter.getStringFromInput("phone number"), Inputter.getStringFromInput("teacher Id")));
        sql.syncWithDatabase("UPSERT", "Student",0, this);

    }

    public Student findStudent(int studentId) {
        for (Student student : studentList) {
            if (student.getStudentId() == studentId) {

                return student;
            }
        }
        return null;
    }

    public void findStudentByTeacherId() {
        String teacherId = Inputter.getStringFromInput("teacher Id");
        for (Student student : studentList) {
            if (student.getTeacherId().equals(teacherId)) {
                System.out.println(student);
            }
        }
    }

    public void findStudentByName() {
        String name = Inputter.getStringFromInput("name");
        for (Student student : studentList) {
            if (student.getName().equals(name)) {
                System.out.println(student);
            }
        }
    }

    public void findStudentById() {
        int studentId = Inputter.getIntFromInput("student Id");
        Student student = findStudent(studentId);
        if (student != null) {
            System.out.println(student);
        }
    }

    public void editStudent() {
        int studentId = Inputter.getIntFromInput("student Id");
        Student student = findStudent(studentId);
        if (student != null) {
            student.setName(Inputter.getStringFromInput("name"));
            student.setAddress(Inputter.getStringFromInput("address"));
            student.setPhoneNumber(Inputter.getStringFromInput("phone number"));
            student.setTeacherId(Inputter.getStringFromInput("teacher Id"));
            sql.syncWithDatabase("UPSERT", "Student", 0, this);
        }
    }

    public void deleteStudent() {
        int studentId = Inputter.getIntFromInput("student Id");
        Student student = findStudent(studentId);
        if (student != null) {
            studentList.remove(student);
            sql.syncWithDatabase("DELETE", "Student", studentId, this);
            for(Student std: studentList) {
                 System.out.println(std.getName());
            }
        }
    }

    public void addTeacher() {
        teacherList.add(new Teacher(Inputter.getIntFromInput("teacher Id"), Inputter.getStringFromInput("name"), Inputter.getStringFromInput("address"), Inputter.getStringFromInput("password"), Inputter.getStringFromInput("phone number"), Inputter.getStringFromInput("subject"), Inputter.getBooleanFromInput("is admin")));
        sql.syncWithDatabase("UPSERT", "Teacher", 0, this);
    }

    public Teacher findTeacher(int teacherId) {
        for (Teacher teacher : teacherList) {
            if (teacher.getTeacherId() == teacherId) {
                return teacher;
            }
        }
        return null;
    }

    public void editTeacher() {
        int teacherId = Inputter.getIntFromInput("teacher Id");
        Teacher teacher = findTeacher(teacherId);
        if (teacher != null) {
            teacher.setName(Inputter.getStringFromInput("name"));
            teacher.setPassword(Inputter.getStringFromInput("password"));
            sql.syncWithDatabase("UPSERT", "Teacher", 0, this);
        }
    }

    public void deleteTeacher() {
        int teacherId = Inputter.getIntFromInput("teacher Id");
        Teacher teacher = findTeacher(teacherId);
        if (teacher != null) {
            teacherList.remove(teacher);
            sql.syncWithDatabase("DELETE", "Teacher", teacherId, this);
        }
    }

}
