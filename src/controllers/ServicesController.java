/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import beans.*;
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
    private int aux;
    Stack a = new Stack();
        
    public ServicesController() {
        
        services = FXCollections.observableArrayList();
        first = null;
        
    }
    
    public void initServices(){
        add("Motor: Engine oil and refill", "Toyota", "Corolla", a, 150.5, 0.0);
        add("Motor: Replace oil filter.", "Audi", "A3",a, 10.5, 0.00);
        add("Motor: Check for general oil leaks.", "Toyota", "Corolla",a, 150.5, 0.0);
        add("Motor: Check radiator condition, security and report any leaks", "Honda", "Civic 2005", a, 1000.0, 0.0);

    }
    /*CONSULTA SI ESTA VACIA LA LISTA*/
    public boolean isNull() {
        return first == null;
    }
    /*METODO AGREGAR*/
    public void add(String name, String mark, String model, Stack stack,  Double workPrice, Double spPrice){
    
        Service newService = new Service(count, name, mark, model, stack, workPrice, spPrice, (workPrice+ spPrice), false);
        
         if (isNull()) {
            // Inicializa la lista agregando como inicio al nuevo nodo.
            first = newService;
        // Caso contrario recorre la lista hasta llegar al ultimo nodo
            //y agrega el nuevo.
        } else {
            // Crea ua copia de la lista.
            Service aux = first;
            // Recorre la lista hasta llegar al ultimo nodo.
            while (aux.getNext() != null) {
                aux = aux.getNext();
            }
            // Agrega el nuevo nodo al final de la lista.
            aux.setNext(newService);
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
        Service aux = first;
    
        while (aux != null) {
            if (aux.getId() == id) {
                return aux;
            }
            aux = aux.getNext();
        }
        return null;
    }
    /*BUSCAR SERVICIO*/
    public Service searchForName(String name){
        Service aux = first;
    
        while (aux != null) {
            if (aux.getName().equals(name)) {
                return aux;
            }
            aux = aux.getNext();
        }
        return null;
    }
    
    /*MODIFICAR SERVICIO*/
    public void edit(int id, String name, String mark, String model, Stack list, Double workPrice, Double spPrice, boolean state) {
        //System.out.println(id + " " + name + " " +  mark + " " +  model + " " +  list.size() + " " + workPrice + " " + spPrice );
        Service actual = new Service();
        actual = first;
        while( actual != null ) {
            
            if (actual.getId() == id) {
                actual.setName(name);
                actual.setModel(model);
                actual.setMark(mark);
                actual.setSparePartList(list);
                actual.setSparePartsPrice(spPrice);
                actual.setWorkPrice(workPrice);
                actual.setTotal(spPrice+workPrice);
                actual.setState(state);
            
            }
            System.out.println("el numero de partes de Servicio" + actual.getId() + " es " + actual.getSparePartList().size() );
            
            actual = actual.next;
        }
        
    }
    
    /*ELIMINAR SERVICIO*/
    public void delete(int id){
         if (search(id) != null) {
            // Consulta si el nodo a eliminar es el pirmero
            if (first.getId() == id) {
                // El primer nodo apunta al siguiente.
                first = first.getNext();
            } else {

                Service aux = first;

                while (aux.getNext().getId() != id) {
                    aux = aux.getNext();
                }
                // Guarda el nodo siguiente del nodo a eliminar.
                Service next = aux.getNext().getNext();
                // Enlaza el nodo anterior al de eliminar con el 
                // sguiente despues de el.
                aux.setNext(next);
            }
        }
        /*Service actual = new Service();
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
        }*/
    }

    
    public void change(int id){
        this.aux = id;
    }
    
    public int getIdAux(){
        return aux;
    }
    
    
    
}
