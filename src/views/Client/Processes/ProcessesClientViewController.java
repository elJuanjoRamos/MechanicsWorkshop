/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Client.Processes;

import beans.Client;
import beans.WorkOrder;
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
import views.Client.ClientView;


public class ProcessesClientViewController implements Initializable {

    
    @FXML TableView<WorkOrder> tableView;
    @FXML TableColumn<WorkOrder, String> car;
    @FXML TableColumn<WorkOrder, String> service;
    @FXML TableColumn<WorkOrder, String> mechanic;
    @FXML TableColumn<WorkOrder, String> workPrice;
    @FXML TableColumn<WorkOrder, String> spPrice;
    @FXML TableColumn<WorkOrder, String> total;
    
    @FXML TableView<WorkOrder> tableViewFinished;
    @FXML TableColumn<WorkOrder, String> carFinished;
    @FXML TableColumn<WorkOrder, String> serviceFinished;
    @FXML TableColumn<WorkOrder, String> mechanicFinished;
    @FXML TableColumn<WorkOrder, String> workPriceFinished;
    @FXML TableColumn<WorkOrder, String> spPriceFinished;
    @FXML TableColumn<WorkOrder, String> totalFinished;
    @FXML TableColumn<WorkOrder, String> dateFinished;
    
    @FXML ImageView imageView;
    
    /*VARIABLES*/
    Client client;

    public ProcessesClientViewController() {
        client = ClientView.client;
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        initTableViewCarsInProcess();
        initTableViewCarsFinished();
        //CARS IN PROCESS
        car.setCellValueFactory(new PropertyValueFactory<>("carDetails"));
        service.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        mechanic.setCellValueFactory(new PropertyValueFactory<>("mechaic"));
        workPrice.setCellValueFactory(new PropertyValueFactory<>("workPrice"));
        spPrice.setCellValueFactory(new PropertyValueFactory<>("spPrice"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));
        
        //CARS FINISHED
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
            if (wo.getClient().getId() == client.getId()) {
                carsInProcess.add(wo);
            }
            wo = wo.getNext();
        }

        tableView.setItems(carsInProcess);


 
    }
    
    
    
    //init table view cars finished
    public void initTableViewCarsFinished(){
        WorkOrder wol = TDAQueueCarsFinished.getInstance().getTDAQueue();
        ObservableList<WorkOrder> carsFinished = FXCollections.observableArrayList();
        while (wol != null) {
            if (wol.getClient().getId() == client.getId()) {
                carsFinished.add(wol);
            }
            wol = wol.getNext();
        }
        tableViewFinished.setItems(carsFinished);
    }
    
    
}
