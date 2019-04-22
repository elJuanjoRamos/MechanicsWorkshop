package views.Main;

import controllers.EmployeesController;
import controllers.ServicesController;
import views.Admin.EmployeeViewController;
import views.Account.Account;
import javafx.application.Application;
import javafx.stage.Stage;
import views.Admin.Admin;

public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        EmployeesController.getInstance().initEmployee();
        ServicesController.getInstance().initServices();
        
        Account.getInstance().start(stage);
        //Admin.getInstance().start(stage);
    }

    public static void main(String[] args) {
        
        launch(args);
    }
    
    
    
}
