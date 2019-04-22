/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import beans.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Stack;

public class ServicesController {
    private static ServicesController instance;
    
    /*SINGLETON*/
    public static ServicesController getInstance() {
        if(instance == null) {
            instance = new ServicesController();
        }
        return instance;
    }
    
    /*VARIABLES*/
    private ObservableList<Service> services;
    private int count = 1;
    private Service first;
    private Service last;
    
    public ServicesController() {
        services = FXCollections.observableArrayList();
        first = null;
        last = null;
        
    }
    
    public void initServices(){
        Stack a = new Stack();
        a.add("fafads");
        a.add(4156);
        add("Motor: Engine oil and refill", "Toyota", "Corolla", a, 150.5, 130.75);
        add("Motor: Replace oil filter.", "Audi", "A3", a, 10.5, 500.00);
        add("Motor: Check for general oil leaks.", "Toyota", "Corolla", a, 150.5, 130.75);
        add("Motor: Check radiator condition, security and report any leaks", "Ferrary", "195 Inter", a, 1000.0, 1050.75);

    }
    /*METODO AGREGAR*/
    public void add(String name, String mark, String model, Stack list, Double workPrice, Double spPrice){
        Service newService = new Service(count, name, mark, model, list, workPrice, spPrice, (workPrice+ spPrice));
        if (first == null) {
            first = newService;
            first.next = null;
            last = first;
        } else {
            last.next = newService;
            newService.next = null;
            last = newService;
        }
        
        count ++;
    }
    /*METODO MOSTRAR */
    public ObservableList<Service> getServices(){
        this.services.clear();
        Service actual = new Service();
        actual = first;
        while (actual != null) {
            services.add(actual);
            actual = actual.next;
        }
        return this.services;
    }
    /*BUSCAR SERVICIO*/
    public Service search(int id){
        Service actual = new Service();
        actual = first;
        while(actual != null){
            if (actual.getId() == id) {
                return actual;
            }
            actual = actual.next;
        }
        return null;
    }
    
    /*MODIFICAR SERVICIO*/
    public void edit(int id, String name, String mark, String model, Stack list, Double workPrice, Double spPrice) {
        Service actual = new Service();
        actual = first;
        while( actual != null ) {
            
            if (actual.getId() == id) {
                actual.setName(name);
                actual.setModel(model);
                actual.setMark(mark);
                actual.setSparePartsPrice(spPrice);
                actual.setWorkPrice(workPrice);
                actual.setTotal(spPrice+workPrice);
            }
            
            actual = actual.next;
        }
    }
    
    /*ELIMINAR SERVICIO*/
    public void delete(int id){
        Service actual = new Service();
        Service temp = new Service();
        actual = first;
        temp = null;
        while(actual != null){
            if (actual.getId() == id) {
                if (actual == first) {
                    first = first.next;
                } else {
                    temp.next = actual.next;
                }
            }
            temp = actual;
            actual = actual.next;
        }
    }

}
