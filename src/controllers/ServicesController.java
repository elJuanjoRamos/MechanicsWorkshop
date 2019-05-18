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
    protected Service first;
    protected ServiceRandom firstRandom;
    private int aux;
    Stack a = new Stack();
        
    public ServicesController() {
        
        services = FXCollections.observableArrayList();
        first = null;
        
    }
    
    public void initServices(){
        add("Diagnostic", "Any", "Any", a, 500.00, 0.0, true);
    }
    /*CONSULTA SI ESTA VACIA LA LISTA*/
    public boolean isNull() {
        return first == null;
    }
    /*METODO AGREGAR*/
    public void add(String name, String mark, String model, Stack stack,  Double workPrice, Double spPrice, boolean state){
    
        Service newService = new Service(count, name, mark, model, stack, workPrice, spPrice, (workPrice+ spPrice), state);
        
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
            if (aux.getName().equalsIgnoreCase(name)) {
                return aux;
            }
            aux = aux.getNext();
        }
        return null;
    }
    
    /*MODIFICAR SERVICIO*/
    public void edit(int id, String name, String mark, String model, Stack list, Double workPrice, Double spPrice, boolean state) {
        
        
        Service actual = new Service();
        actual = first;
        while( actual != null ) {
            
            if (id != 1 ) {
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
            
            }
            actual = actual.next;
        }
    }
    
    /*ELIMINAR SERVICIO*/
    public void delete(int id) {
        if (id != 1) {
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
        }
    }
    
    public ObservableList<String> getServiceName(String model, String mark) {
        ObservableList<String> serviceName = FXCollections.observableArrayList();

        // Crea una copia de la lista.
        Service aux = first;
        // Posicion de los elementos de la lista.
        // Recorre la lista hasta el final.
        while (aux != null) {
            if (aux.getModel().equalsIgnoreCase(model) && aux.getMark().equalsIgnoreCase(mark) || aux.getModel().equals("Any")   ) {
                if (aux.getState() == true) {
                    serviceName.add(aux.getName());
                }
            }
            aux = aux.getNext();
        }
        return serviceName;
    }
    
    public void change(int id){
        this.aux = id;
    }
    
    public int getIdAux(){
        return aux;
    }
    
    /**
     * OBTENER SERVICIO ALEATORIO
     */
    public Service getServiceRandom(String model, String mark) {
        firstRandom = null;
        Service aux = first;
        int quantity = 1;
        
        //SI ES CERO DEJARLO COMO DIAGNOSTICO
        while (aux != null) {
            if (aux.getModel().equalsIgnoreCase(model) && aux.getMark().equalsIgnoreCase(mark) || aux.getModel().equals("Any")   ) {
                if (aux.getState() == true) {
                    System.out.println("ESTADO 2" + aux);
                    quantity++;
                    System.out.println(quantity);
                    add(quantity, aux);
                }
            }
            aux = aux.getNext();
        }
        int numeroRandom = valorAleatorio(quantity-1);
        System.out.println("ID RANDOM" + numeroRandom);
        return searchRandom(numeroRandom);
    }
        
    public int valorAleatorio(int n) {
        return (int) (Math.random() * n) + 1;
    }
    
    
    /***
     * BUSCAR EN LA LISTA DE SERVICIO RANDOM 
     */
    public Service searchRandom(int id){
        ServiceRandom aux = firstRandom;
        
        while (aux != null) {
            System.out.println(aux.getService());
            if (aux.getId() == id) {
                System.out.println("SERVICIO ENCONTRADO" +aux.getService());
                return aux.getService();
            }
            aux = aux.getNext();
        }
        return null;
    }
    
    /**
     * AGREGAR A LA LISTA DE SERVICIOS RANDOM 
     */
    public void add(int id, Service service){
        ServiceRandom sr = new ServiceRandom(id, service);
         if (firstRandom == null) {
            firstRandom = sr;
        } else {
            ServiceRandom aux = firstRandom;
            while (aux.getNext() != null) {
                aux = aux.getNext();
            }
            aux.setNext(sr);
        }
    }
    
}


class ServiceRandom {
    private int id;
    private Service service;
    private ServiceRandom next;

    public ServiceRandom(int id, Service service) {
        this.id = id;
        this.service = service;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the service
     */
    public Service getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(Service service) {
        this.service = service;
    }

    /**
     * @return the next
     */
    public ServiceRandom getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(ServiceRandom next) {
        this.next = next;
    }
    
}
