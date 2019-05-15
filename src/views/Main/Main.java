package views.Main;

import beans.Car;
import beans.Client;
import beans.ColaPrioridad;
import beans.Employee;
import beans.Service;
import beans.WorkOrder;
import controllers.BillController;
import controllers.ClientsController;
import controllers.EmployeesController;
import controllers.InterpreterController;
import controllers.ServicesController;
import controllers.SparesPartsController;
import controllers.TDAQueueCarsFinished;
import controllers.WorkOrderController;
import java.io.File;
import java.util.Date;

import javafx.application.Application;
import javafx.stage.Stage;
import views.Account.Account;
import views.Admin.Admin;
import views.Client.ClientView;

public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        
        EmployeesController.getInstance().initEmployee();
        ServicesController.getInstance().initServices();
        
        
        
        
        //Account.getInstance().start(stage);
        //Admin.getInstance().start(stage);
        ClientView.getInstance().start(stage, ClientsController.getInstance().searchClient(1));
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    
    
}
