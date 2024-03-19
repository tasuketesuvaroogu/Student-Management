package model;

public class Student{
    private int studentId;
    private String name;
    private String address;
    private String phoneNumber;
    private String teacherId;

    public Student(int studentId, String name, String address, String phoneNumber, String teacherId) {
        this.studentId = studentId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.teacherId = teacherId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
    
    @Override
    public String toString(){
        return "Student Id: " + studentId + " Name: " + name + " Address: " + address + " Phone Number: " + phoneNumber + " Teacher Id: " + teacherId;
    }
}