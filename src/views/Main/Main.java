/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Main;

import controllers.EmployeesController;
import views.Admin.EmployeeController;
import views.Account.Account;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Juan José Ramos
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
       
        /*ENVIO EMPLEADOS POR DEFECTO*/
        EmployeesController.getInstance().initEmployee();
        Account.getInstance().start(stage);
    }

    public static void main(String[] args) {
        
        launch(args);
    }
    
    
    
}
