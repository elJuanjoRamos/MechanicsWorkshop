/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Main;

import views.Admin.EmployeeController;
import views.Account.Account;
import javafx.application.Application;
import javafx.stage.Stage;
import views.Admin.Admin;


/**
 *
 * @author Juan José Ramos
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
       
        EmployeeController.getInstance().add("Employee0", "Administrator", "admin", "admin");
        EmployeeController.getInstance().add("Employee1", "Seller", "user1", "password1");
        EmployeeController.getInstance().add("Employee2", "Seller", "user2", "password2");
        EmployeeController.getInstance().add("Employee3", "Packer", "user3", "password3");
        
        Account.getInstance().start(stage);
    }

    public static void main(String[] args) {
        
        launch(args);
    }
    
    
    
}
