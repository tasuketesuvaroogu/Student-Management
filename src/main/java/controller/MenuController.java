package controller;
import model.Menu;
import java.util.*;
import utils.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuController {
    public ArrayList<Menu> menuList = new ArrayList();
    private Sql sql = null;
    public MenuController(Sql sql){
        this.sql = sql;
    }
    
    public void addMenu(int type){
        ResultSet resultSet = this.sql.syncWithDatabase("SELECT", "Menu", 0, null);
        try {
            while(resultSet.next() ){
                if (resultSet.getInt("Type") == type)
                   menuList.add(new Menu(resultSet.getInt("Id"), resultSet.getString("Name"),
                        resultSet.getString("Description"), resultSet.getInt("Index") , resultSet.getInt("Type")));
            }
            // this.sortByIndex();
        } catch (SQLException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void sortByIndex(){
        Collections.sort(menuList, new indexComparator());
    }
    
    public void printMenu(){
        for (Menu m : menuList){
            System.out.println(m.getIndex() + ". " + m.getName());
        }
    }    
    public int getOptionByIndex(int index){
         for (Menu m : menuList){
             if (m.getIndex() == index){
                 return m.getId();
             }
         }
         return -1;
    }
}














class indexComparator implements Comparator<Menu> {
    @Override
    public int compare(Menu a, Menu b) {
        return a.getIndex()- b.getIndex();
    }
}