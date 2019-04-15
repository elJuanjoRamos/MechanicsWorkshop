/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Main;

import controllers.EmployeeController;
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
        //Account.getInstance().start(stage);
        Admin.getInstance().start(stage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EmployeeController.getInstance().add("Juan Ramos", "Administrator", "admin", "admin");
        
        launch(args);
    }
    
    
    
}
