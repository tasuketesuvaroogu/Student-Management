package view;

import controller.*;

import java.util.*;

import utils.*;

public class StudentManagement{
     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Sql sql = new Sql();
        MenuController menu = new MenuController(sql);
        TeacherController teacher = new TeacherController(sql);
         loginPage(teacher, menu);
        do {
            menu.printMenu();
            int choice = scanner.nextInt();
            int optionId = menu.getOptionByIndex(choice);
            procedureCall(teacher, optionId, menu);

        }
        while (true);
    }

    private static void procedureCall(TeacherController controller, int optionId, MenuController menu) {
        switch (optionId) {
            case 1:
                controller.addStudent();
                break;
            case 2:
                controller.editStudent();
                break;
            case 3:
                controller.deleteStudent();
                break;
            case 4:
                controller.addTeacher();
                break;
            case 5:
                controller.editTeacher();
                break;
            case 6:
                controller.deleteTeacher();
                break;
            case 7:
                menu.menuList.clear();
                loginPage(controller, menu);
                break;
      
        }
    }
    private static void loginPage(TeacherController teacher, MenuController menu){
        System.out.println("---LOGIN PAGE---");
        boolean auth;
        do{
        auth = teacher.login();
        }
        while(!auth);
        if(teacher.isAdmin()){
            menu.addMenu(0);
            menu.addMenu(1);         
        }
        else{
            menu.addMenu(0);
        }
    }
}