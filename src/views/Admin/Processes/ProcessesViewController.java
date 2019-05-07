/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Admin.Processes;

import beans.*;
import controllers.EmployeesController;
import controllers.TDAQueueCarsFinished;
import controllers.TDAQueueCarsInProcess;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Juan José Ramos
 */
public class ProcessesViewController implements Initializable {
    
    @FXML TableView<WorkOrder> tableView;
    @FXML TableColumn<WorkOrder, String> client;
    @FXML TableColumn<WorkOrder, String> car;
    @FXML TableColumn<WorkOrder, String> service;
    @FXML TableColumn<WorkOrder, String> mechanic;
    @FXML TableColumn<WorkOrder, String> workPrice;
    @FXML TableColumn<WorkOrder, String> spPrice;
    @FXML TableColumn<WorkOrder, String> total;
    
    @FXML TableView<WorkOrder> tableViewFinished;
    @FXML TableColumn<WorkOrder, String> clientFinished;
    @FXML TableColumn<WorkOrder, String> carFinished;
    @FXML TableColumn<WorkOrder, String> serviceFinished;
    @FXML TableColumn<WorkOrder, String> mechanicFinished;
    @FXML TableColumn<WorkOrder, String> workPriceFinished;
    @FXML TableColumn<WorkOrder, String> spPriceFinished;
    @FXML TableColumn<WorkOrder, String> totalFinished;
    @FXML TableColumn<WorkOrder, String> dateFinished;
    
    
    
    @FXML TextField eClient, eCar, eService, eMechanic, eWP, eSP, eTotal;
    @FXML ImageView imageView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        initTableViewCarsInProcess();
        initTableViewCarsFinished();
        //CARS IN PROCESS
        client.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        car.setCellValueFactory(new PropertyValueFactory<>("carDetails"));
        service.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        mechanic.setCellValueFactory(new PropertyValueFactory<>("mechaic"));
        workPrice.setCellValueFactory(new PropertyValueFactory<>("workPrice"));
        spPrice.setCellValueFactory(new PropertyValueFactory<>("spPrice"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));
        
        //CARS FINISHED
        clientFinished.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        carFinished.setCellValueFactory(new PropertyValueFactory<>("carDetails"));
        serviceFinished.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        mechanicFinished.setCellValueFactory(new PropertyValueFactory<>("mechaic"));
        workPriceFinished.setCellValueFactory(new PropertyValueFactory<>("workPrice"));
        spPriceFinished.setCellValueFactory(new PropertyValueFactory<>("spPrice"));
        totalFinished.setCellValueFactory(new PropertyValueFactory<>("total"));
        dateFinished.setCellValueFactory(new PropertyValueFactory<>("date"));
    }   
    
    public void initTableViewCarsInProcess(){
        WorkOrder wo = TDAQueueCarsInProcess.getInstance().getTDAQueue();
        ObservableList<WorkOrder> carsInProcess = FXCollections.observableArrayList();

        while (wo != null) {
            carsInProcess.add(wo);
            wo = wo.getNext();
        }

        tableView.setItems(carsInProcess);


 
    }
    
    
    
    //init table view cars finished
    public void initTableViewCarsFinished(){
        WorkOrder wol = TDAQueueCarsFinished.getInstance().getTDAQueue();
        ObservableList<WorkOrder> carsFinished = FXCollections.observableArrayList();
        while (wol != null) {
            carsFinished.add(wol);
            wol = wol.getNext();
        }
        tableViewFinished.setItems(carsFinished);
    }
    
    @FXML 
    public void getNextCliet(){
        WorkOrder wo = TDAQueueCarsInProcess.getInstance().getfirstNode();
        
        eClient.setText(wo.getClientName());
        eCar.setText(wo.getCarDetails());
        eService.setText(wo.getServiceName());
        eMechanic.setText(wo.getMechaic());
        eSP.setText(String.valueOf(wo.getSpPrice()));
        eWP.setText(String.valueOf(wo.getWorkPrice()));
        eTotal.setText(String.valueOf(wo.getTotal()));
        imageView.setImage(new Image(wo.getImagePath()));
    }
    
    @FXML
    public void cancel(){
        eClient.setText("");
        eCar.setText("");
        eService.setText("");
        eMechanic.setText("");
        eSP.setText("");
        eWP.setText("");
        eTotal.setText("");
        imageView.setImage(new Image("resources/img/car2.png"));
    }
    
    
    @FXML
    public void endPocess(){
        
        WorkOrder w = TDAQueueCarsInProcess.getInstance().getfirstNode();
        WorkOrder wEnd = new WorkOrder(w.getId(), w.getCar(), 
                        w.getClient(), w.getEmployee(), w.getService(), w.getDate(), w.getState());
        
        TDAQueueCarsFinished.getInstance().push(wEnd);
        
        TDAQueueCarsInProcess.getInstance().pop();
        
        EmployeesController.getInstance().edit(w.getEmployee().getId(), w.getEmployee().getName(), w.getEmployee().getRole(), w.getEmployee().getUsername(), w.getEmployee().getPassword(), true);
        
        
        
        
        initTableViewCarsInProcess();
        initTableViewCarsFinished();
        cancel();
    }
}
