package model;

public class Teacher{
    private int teacherId;
    private String name;
    private String address;
    private String password;
    private String phoneNumber;
    private String subject;
    private boolean isAdmin;

    public Teacher(int teacherId, String name, String address, String password, String phoneNumber, String subject, boolean isAdmin) {
        this.teacherId = teacherId;
        this.name = name;
        this.address = address;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.subject = subject;
        this.isAdmin = isAdmin;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    
}