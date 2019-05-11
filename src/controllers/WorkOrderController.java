/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Car;
import beans.Client;
import beans.Employee;
import beans.Service;
import beans.WorkOrder;
import java.util.Date;
import java.util.Stack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Juan José Ramos
 */
public class WorkOrderController {
    
    private static WorkOrderController instance;

    /*SINGLETON*/
    public static WorkOrderController getInstance() {
        if (instance == null) {
            instance = new WorkOrderController();
        }
        return instance;
    }

    
        /*VARIABLES*/
    private ObservableList<WorkOrder> observableList;
    private int count = 1;
    private WorkOrder first;

    
    public WorkOrderController() {
        observableList = FXCollections.observableArrayList();
        first = null;
    }
    
    /*CONSULTA SI ESTA VACIA LA LISTA*/
    public boolean isNull() {
        return first == null;
    }
    
     /*METODO AGREGAR A LISTA LISTA SIMPLE*/
    public void add(Car car, Client client, Service service, Employee employee, String state) {
        int priority;
        if (client.getRole().equalsIgnoreCase("gold")) {
            priority = 1;
        } else {
            priority = 0;
        }
        
        WorkOrder newWorkOrder = new WorkOrder(count, car, client, employee, service,new Date(), state, priority);
        if (isNull()) {
                first = newWorkOrder;
            
        
        } else {
            WorkOrder aux = first;
            
            while (aux.getNext() != null) {
                aux = aux.getNext();
            }
            
            aux.setNext(newWorkOrder);
        }
        
        count++;
    }

    public WorkOrder get(){
        return first;
    }
    
    
    public void show(){
        WorkOrder aux = first;
        
        while(aux != null){
            System.out.println("el cliente es " + aux.getClientName() +" y su rol es "  + aux.getClient().getRole());
            aux = aux.getNext();
        }
    }
    
    /*OBTIENE EL LISTADO DE EMPLEADOS*/
    public ObservableList<WorkOrder> getOrders() {
        this.observableList.clear();
        WorkOrder actual = new WorkOrder();
        actual = first;
        
        while (actual != null) {
            
            observableList.add(actual);
            //actual = actual.next; pasar a publico el next del bean
            actual = actual.getNext();
        }
        
        return observableList;
    }
    
    
    
    
   
}
