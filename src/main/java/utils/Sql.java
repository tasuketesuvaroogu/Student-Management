package utils;

import controller.*;
import model.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sql {

    private Connection connection = null;

    public Sql() {
        String connectionUrl = "jdbc:sqlserver://127.0.0.1:1433;"
                + "database=StudentManagement;"
                + "user=database;"
                + "password=RPSsql12345;"
                + "encrypt=true;"
                + "trustServerCertificate=true;"
                + "loginTimeout=30;";
        try {
            this.connection = DriverManager.getConnection(connectionUrl);
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet syncWithDatabase(String action, String table, int id, TeacherController controller) {
        ResultSet resultSet = null;
        try {
            PreparedStatement statement = null;
            String sql = "";
            switch (action) {
                case "SELECT":
                sql = "SELECT * FROM " + table;  
                statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery();
                    break;
                case "UPSERT":
                    if (table.equals("Teacher")) {
                        sql = "MERGE INTO Teacher AS target USING (VALUES (?, ?, ?, ?, ?, ?, ?)) AS source (teacherId, name, address, password, phoneNumber, subject, isAdmin) ON target.teacherId = source.teacherId WHEN MATCHED THEN UPDATE SET target.name = source.name, target.address = source.address, target.password = source.password, target.phoneNumber = source.phoneNumber, target.subject = source.subject, target.isAdmin = source.isAdmin WHEN NOT MATCHED THEN INSERT (teacherId, name, address, password, phoneNumber, subject, isAdmin) VALUES (source.teacherId, source.name, source.address, source.password, source.phoneNumber, source.subject, source.isAdmin);";
                        statement = connection.prepareStatement(sql);
                        statement.setInt(1, controller.teacherList.get(id).getTeacherId());
                        statement.setString(2, controller.teacherList.get(id).getName());
                        statement.setString(3, controller.teacherList.get(id).getAddress());
                        statement.setString(4, controller.teacherList.get(id).getPassword());
                        statement.setString(5, controller.teacherList.get(id).getPhoneNumber());
                        statement.setString(6, controller.teacherList.get(id).getSubject());
                        statement.setBoolean(7, controller.teacherList.get(id).isIsAdmin());
                        statement.executeUpdate();
                    }
                    if (table.equals("Student")) {
                        sql = "MERGE INTO Student AS target USING (VALUES (?, ?, ?, ?, ?)) AS source (id, name, address, phoneNumber, teacherId) ON target.id = source.id WHEN MATCHED THEN UPDATE SET target.name = source.name, target.address = source.address, target.phoneNumber = source.phoneNumber, target.teacherId = source.teacherId WHEN NOT MATCHED THEN INSERT (id, name, address, phoneNumber, teacherId) VALUES(source.id, source.name, source.address, source.phoneNumber, source.teacherId);";
                        statement = connection.prepareStatement(sql);
                        statement.setInt(1, controller.studentList.get(id).getStudentId());
                        statement.setString(2, controller.studentList.get(id).getName());
                        statement.setString(3, controller.studentList.get(id).getAddress());
                        statement.setString(4, controller.studentList.get(id).getPhoneNumber());
                        statement.setString(5, controller.studentList.get(id).getTeacherId());
                        statement.executeUpdate();
                    }
                    break;
                    case "DELETE":
                    if (table.equals("Teacher")) {
                        sql = "DELETE FROM Teacher WHERE teacherId = ?"; 
                    }
                    if (table.equals("Student")) {
                        sql = "DELETE FROM Student WHERE id = ?";
                    }
                    statement = connection.prepareStatement(sql);
                    statement.setInt(1, id);
                    statement.executeUpdate(); 
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return resultSet;
    }

}
