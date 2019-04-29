/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Car;
import beans.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ClientController {
    private static ClientController instance;
    private ObservableList<Client> clients;
    private Client start;
    private Client latest;
    private int size;
    private long dpi = 201900000;

    public ClientController() {
        clients = FXCollections.observableArrayList();
        initClient();
    }
    
    /**
     * INICIALIZAR DATOS
    **/
    public void initClient() {
        addAtEnd("Juan Ramos", "jramos", "jramos", "Oro");
        addAtEnd("José Morente", "jmorente", "jmorente", "Oro");
    }

    /**
     * @return the instance
     */
    public static ClientController getInstance() {
        if(instance == null) {
            instance = new ClientController();
        }
        return instance;
    }
    
    /**
     * MÉTODO PARA VERIFICAR SI ESTA VACÍA LA LISTA
     * @return true
     */
    public boolean isEmpty() {
        return start == null;
    }
    
    /**
     * AGREGAR NUEVO NODO AL FINAL DE LA LISTA CIRCULAR DOBLE
     * @param dpi
     * @param fullName
     * @param username
     * @param password
     * @param role
     */
    public void addAtEnd(String fullName, String username, String password, String role) {
        String id = String.valueOf(dpi).concat("0101");
        Client c = new Client(Long.parseLong(id), fullName, username, password, role);
        if(isEmpty()) {
            start = c;
            start.setNext(c);
            c.setPrevious(start);
            latest = c;
        } else {
            latest.setNext(c);
            c.setNext(start);
            c.setPrevious(latest);
            latest = c;
            start.setPrevious(latest);
        }
        size++;
        dpi++;
    }
    
    /**
     * MÉTODO PARA RETORNAR LOS NODOS DE LA LISTA CIRUCLAR DOBLE
     * @return the clients
     */
    public ObservableList<Client> getClients() {
        this.clients.clear();
        if(!isEmpty()) {
            Client aux = start;
            do {
                clients.add(aux);
                aux = aux.getNext();
            } while(aux != start);
        }
        return clients;
    }
    
    /**
     * BUSCAR NODO POR REFERENCIA EN LA LISTA CIRCULAR DOBLE
     * @param dpi
     */
    public boolean search(long dpi) {
        Client aux = latest;
        do{
            if (aux.getDpi() == dpi){
                return true;
            } else{
                aux = aux.getNext();
            }
        }while(aux != latest);
        return false;
    }
    
    /**
     * BUSCAR NODO POR REFERENCIA EN LA LISTA CIRCULAR DOBLE
     * @param dpi
     */
    public Client getClient(long dpi) {
        Client aux = latest;
        do{
            if (aux.getDpi() == dpi){
                return aux;
            } else{
                aux = aux.getNext();
            }
        }while(aux != latest);
        return null;
    }
    
    /**
     * ACTUALIZAR NODO DE LA LISTA CIRCULAR DOBLE
     * @param dpi
     * @param fullName
     * @param username
     * @param password
     * @param role
     * @param carList
     */
    public void update(long dpi, String fullName, String username, String password, String role) {
        if(search(dpi)) {
            Client aux = start;
            do {
                if(aux.getDpi() == dpi) {
                    aux.setFullName(fullName);
                    aux.setUsername(username);
                    aux.setPassword(password);
                    aux.setRole(role);
                }
                aux = aux.getNext();
            } while(aux != start);
        }
    }
    
    /**
     * ELIMINAR NODO DE LA LISTA CIRCULAR SIMPLE
     * @param dpi
     */
    public void delete(long dpi) {
        Client current = start;
        Client previous = latest;
        if(search(dpi)) {
            do {
                if(current.getDpi() == dpi) {
                    if(current == start) {
                        start = start.getNext();
                        latest.setNext(start);
                        start.setPrevious(latest);
                    } else if(current == latest) {
                        latest = previous;
                        start.setPrevious(latest);
                        latest.setNext(start);
                    } else {
                        previous.setNext(current.getNext());
                        current.getNext().setPrevious(previous);
                    }
                }
                previous = current;
                current = current.getNext();
            } while(current != start);
        }
    }
}
