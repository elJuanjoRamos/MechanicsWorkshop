package views.Main;

import beans.Car;
import beans.Client;
import beans.ColaPrioridad;
import beans.Employee;
import beans.Service;
import beans.WorkOrder;
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
        SparesPartsController.getInstance().initSpareParts();
        ServicesController.getInstance().initServices();
        
        
//        Client client = ClientsController.getInstance().searchClient(1);
//        Client client2 = ClientsController.getInstance().searchClient(2);
//        Client client3 = ClientsController.getInstance().searchClient(3);
//        Client client4 = ClientsController.getInstance().searchClient(4);
//        
//        
//        Car car = new Car(1,"123456sdf", "Toyota", "Corolla", "");
//        Car car1 = new Car(2,"25654ffs", "Toyota", "Corolla", "");
//        Car car2 = new Car(3,"66554fdss", "Toyota", "Corolla", "");
//        Car car3 = new Car(4,"9989fsdf", "Toyota", "Corolla", "");
//    
//        
//        File file = new File("C:\\Users\\Juan José Ramos\\Documents\\USAC\\2.Segundo Año\\TERCER SEMESTRE\\Introduccion a la programacion 1\\Java\\MechanicsWorkshop\\src\\resources\\files\\service.tms");
//        InterpreterController.getInstance().interpret(file, "*.tms");
//        
//        Service s = ServicesController.getInstance().search(2);
//        Service s2 = ServicesController.getInstance().search(3);
//        Service s3 = ServicesController.getInstance().search(2);
//        Service s4 = ServicesController.getInstance().search(4);
//        Service s5 = ServicesController.getInstance().search(5);
//        Service s6 = ServicesController.getInstance().search(1);
//        
//        
//        Employee mecanico = EmployeesController.getInstance().search(3);
//        Employee mecanico2 = EmployeesController.getInstance().search(4);
//        Employee mecanico3 = EmployeesController.getInstance().search(5);
//        Employee mecanico4 = EmployeesController.getInstance().search(6);
//        Employee mecanico5 = EmployeesController.getInstance().search(7);
//        
//        
//        WorkOrderController.getInstance().add(car,client, s2, mecanico,"");
//        WorkOrderController.getInstance().add(car2,client2, s2,mecanico2, "");
//        WorkOrderController.getInstance().add(car3,client3, s3, mecanico3,"");
//        WorkOrderController.getInstance().add(car2,client4, s2,mecanico4, "" );
//        WorkOrderController.getInstance().add(car,client,  s2,mecanico2,"");
//        WorkOrderController.getInstance().add(car2,client2,s6, mecanico3,  "");
//        WorkOrderController.getInstance().add(car3,client3, s5, mecanico4, "");
//        WorkOrderController.getInstance().add(car3,client4, s3, mecanico5,  "");
//        
//        
//        
        
        ////
        //WorkOrder e = WorkOrderController.getInstance().getWO(1);
        //WorkOrder wemp = new WorkOrder(e.getId(), e.getCar(), e.getClient(), e.getEmployee(), e.getService(), e.getDate(), e.getState(), e.getPriority());
        //TDAQueueCarsFinished.getInstance().push(wemp);
        
//        ////
//        WorkOrder wok2 = WorkOrderController.getInstance().getWO(2);
//        WorkOrder wemp2 = new WorkOrder(wok2.getId(), wok2.getCar(), wok2.getClient(), wok2.getEmployee(), wok2.getService(), wok2.getDate(), wok2.getState(), wok2.getPriority());
//        TDAQueueCarsFinished.getInstance().push(wemp2);
//                ////
//                    ////
//        WorkOrder wok3 = WorkOrderController.getInstance().getWO(3);
//        WorkOrder wemp3 = new WorkOrder(wok3.getId(), wok3.getCar(), wok3.getClient(), wok3.getEmployee(), wok3.getService(), wok3.getDate(), wok3.getState(), wok3.getPriority());
//        TDAQueueCarsFinished.getInstance().push(wemp3);
//        
//
//////
//        WorkOrder wok4 = WorkOrderController.getInstance().getWO(4);
//        WorkOrder wemp4 = new WorkOrder(wok4.getId(), wok4.getCar(), wok4.getClient(), wok4.getEmployee(), wok4.getService(), wok4.getDate(), wok4.getState(), wok4.getPriority());
//        TDAQueueCarsFinished.getInstance().push(wemp4);
//        
//        ////
//        WorkOrder wok5 = WorkOrderController.getInstance().getWO(5);
//        WorkOrder wemp5 = new WorkOrder(wok5.getId(), wok5.getCar(), wok5.getClient(), wok5.getEmployee(), wok5.getService(), wok5.getDate(), wok5.getState(), wok5.getPriority());
//        TDAQueueCarsFinished.getInstance().push(wemp5);
//        
//        
//////
//        WorkOrder wok6 = WorkOrderController.getInstance().getWO(6);
//        WorkOrder wemp6 = new WorkOrder(wok6.getId(), wok6.getCar(), wok6.getClient(), wok6.getEmployee(), wok6.getService(), wok6.getDate(), wok6.getState(), wok6.getPriority());
//        TDAQueueCarsFinished.getInstance().push(wemp6);
//        
//        
//////
//        WorkOrder wok7 = WorkOrderController.getInstance().getWO(7);
//        WorkOrder wemp7 = new WorkOrder(wok7.getId(), wok7.getCar(), wok7.getClient(), wok7.getEmployee(), wok7.getService(), wok7.getDate(), wok7.getState(), wok7.getPriority());
//        TDAQueueCarsFinished.getInstance().push(wemp7);
//        ////
//        WorkOrder wok8 = WorkOrderController.getInstance().getWO(8);
//        WorkOrder wemp8 = new WorkOrder(wok8.getId(), wok8.getCar(), wok8.getClient(), wok8.getEmployee(), wok8.getService(), wok8.getDate(), wok8.getState(), wok8.getPriority());
//        TDAQueueCarsFinished.getInstance().push(wemp8);
//        
        
        
        
        
        Account.getInstance().start(stage);
        //Admin.getInstance().start(stage);
        //ClientView.getInstance().start(stage, ClientsController.getInstance().searchClient(1));
    }

    public static void main(String[] args) {
        
        launch(args);
    }
    
    
    
}
