package controllers;

import beans.*;
import beans.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class CarController {
    private static CarController instance;
    private ObservableList<Car> cars;
    private int size = 1;
    public Car carClient;
    private Car start;
    
    public CarController() {
        cars = FXCollections.observableArrayList();
        start = null;
    }

    /**
     * @return the instance
     */
    public static CarController getInstance() {
        if(instance == null) {
            instance = new CarController();
        }
        return instance;
    }
    
    /**
     * INICIALIZAR DATOS
    **/
    
    
    
    public Car returnCars(){
        return start;
        
    }
    /*SETEA EN LA LISTA LOS CARROS DE UN CLIENTE EN ESPECIFICO PARA TRABAJAS LOS */
    /*METODOS DE LA LISTA SOLO CON ESOS CARROS*/
    public void setCarClient(Car c){
        this.start = c;
    }
    
    
    /**
     * MÉTODO PARA VERIFICAR SI ESTA VACÍA LA LISTA
     * @return true
     */
    public boolean isEmpty() {
        return start == null;
    }
    
    /**
     * MÉTODO PARA RETORNAR LOS NODOS DE LA LISTA CIRUCLAR SIMPLE
     * @return the cars
     */
    public ObservableList<Car> getCars() {
        this.cars.clear();

        if (!isEmpty()) {
            Car auxiliaryNode = start.getNext();
            do {
                cars.add(auxiliaryNode);
                auxiliaryNode = auxiliaryNode.getNext();
            } while (auxiliaryNode != start.getNext());
            System.out.println();
        }
        
        return cars;
    }
    
    
    /**
     * AGREGAR NUEVO NODO AL FINAL DE LA LISTA CIRCULAR SIMPLE
     * @param plate
     * @param brand
     * @param model
     * @param path
     */
    public void addAtEnd(String plate, String brand, String model, String path) {
        Car c = new Car(size, plate, brand, model, path);
        if (start != null) {
            c.setNext(start.getNext());
            start.setNext(c);
        }
        start = c;
        
        size++;
    }



    public void readNodes() {
        if (!isEmpty()) {
            Car auxiliaryNode = start.getNext();
            do {
                System.out.print(auxiliaryNode.getModel()+ " --> ");
                // System.out.println(auxiliaryNode.getNextNode().getObject());
                auxiliaryNode = auxiliaryNode.getNext();
            } while (auxiliaryNode != start.getNext());
            System.out.println();
        }
    }


    /**
     * BUSCAR NODO POR REFERENCIA EN LA LISTA CIRCULAR SIMPLE
     * @param plate
     * @return found
     */
    public boolean search(int id){
        Car aux = start;
        do{
            if (aux.getId() == id){
                return true;
            }
            else{
                aux = aux.getNext();
            }
        }while(aux != start);
        return false;
    }
    
    /**
     * BUSCAR NODO POR REFERENCIA EN LA LISTA CIRCULAR SIMPLE
     * @param plate
     * @return car
     */
    public Car getCar(String plate){
        Car aux = start;
        do{
            if (aux.getPlate().equals(plate)){
                return aux;
            }
            else{
                aux = aux.getNext();
            }
        }while(aux != start);
        return null;
    }
    
    /**
     * ELIMINAR NODO DE LA LISTA CIRCULAR SIMPLE
     * @param plate
     */
    public void delete(int id) {
        
        Car actual = start;
        boolean found = false;
        while (actual.getNext() != start && !found) {
            found = (actual.getNext().getId()== id);
            if (!found) {
                actual = actual.getNext();
            }
        }
        found = (actual.getNext().getId()== id);
        if (found) {
            Car aux = actual.getNext();
            if (start == start.getNext()) {
                start = null;
            } else {
                if (aux == start) {
                    start = actual;
                }
                actual.setNext(aux.getNext());
            }
        }
    }
    
    /**
     * ACTUALIZAR NODO DE LA LISTA CIRCULAR SIMPLE
     * @param plate
     * @param brand
     * @param model
     * @param path
     */
    public void update(int id, String plate, String brand, String model, String path) {
        if(search(id)) {
            Car aux = start;
            do {
                if(aux.getId()== id) {
                    aux.setBrand(brand);
                    aux.setModel(model);
                    aux.setPath(path);
                    aux.setPlate(plate);
                }
                aux = aux.getNext();
            } while(aux != start);
        }
    }
    
    
    
    public void cleanList(){
        start = null;
    }

}