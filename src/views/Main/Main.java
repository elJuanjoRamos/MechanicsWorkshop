package views.Main;

import beans.ColaPrioridad;
import controllers.ClientsController;
import controllers.EmployeesController;
import controllers.ServicesController;
import controllers.SparesPartsController;

import javafx.application.Application;
import javafx.stage.Stage;
import views.Account.Account;
import views.Admin.Admin;
import views.Client.ClientView;

public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        
        EmployeesController.getInstance().initEmployee();
        SparesPartsController.getInstance().initSpareParts();
        ServicesController.getInstance().initServices();
        
        //Account.getInstance().start(stage);
        //Admin.getInstance().start(stage);
        ClientView.getInstance().start(stage, ClientsController.getInstance().searchClient(1));
    }

    public static void main(String[] args) {
        
        launch(args);
    }
    
    
    
}
