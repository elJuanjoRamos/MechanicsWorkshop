/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Car;
import beans.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jose Morente
 */
public class CarController {
    private static CarController instance;
    private ObservableList<Car> cars;
    private Car start;
    private Car latest;
    private int size;
    public CarController() {
        cars = FXCollections.observableArrayList();
        initCar();
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
     *
    **/
    public void initCar() {
        addAtEnd("0123456", "Honda", "CR-V", "resources/img/car.png");
        addAtEnd("AB90123", "Honda", "Civic Sedan", "resources/img/car1.png");
        addAtEnd("PV45612", "Honda", "City", "resources/img/car1.png");
        addAtEnd("AR4525X", "Honda", "Fit", "resources/img/car1.png");
        addAtEnd("XF4524S", "Volkswagen", "Polo Sedan", "resources/img/car1.png");
        addAtEnd("A54X7S1", "Volkswagen", "Beetle", "resources/img/car1.png");
        addAtEnd("PO54X12", "Chevrolet", "Colorado", "resources/img/car1.png");
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
        if(!isEmpty()) {
            Car aux = start;
            do {
                cars.add(aux);
                aux = aux.getNext();
            } while(aux != start);
        }
        return cars;
    }
    
    /**
     * OBTENER LISTA
     */
    public void getAll() {
        if(!isEmpty()) {
            Car aux = start;
            do {
                System.out.println(aux.getPlate());
                aux = aux.getNext();
            } while(aux != start);
        }
    }
    /**
     * AGREGAR NUEVO NODO AL FINAL DE LA LISTA CIRCULAR SIMPLE
     * @param plate
     * @param brand
     * @param model
     * @param path
     */
    public void addAtEnd(String plate, String brand, String model, String path) {
        Car c = new Car(plate, brand, model, path);
        if(isEmpty()) {
            start = c;
            latest = c;
            latest.setNext(start);
        } else {
            latest.setNext(c);
            c.setNext(start);
            latest = c;
        }
        size++;
    }


    /**
     * AGREGAR NUEVO NODO AL INICIO DE LA LISTA CIRCULAR SIMPLE
     * @param plate
     * @param brand
     * @param model
     * @param path
     */
    public void addAtStart(String plate, String brand, String model, String path) {
        Car c = new Car(plate, brand, model, path);
        if(isEmpty()) {
            start = c;
            latest = c;
            latest.setNext(start);
        } else {
            c.setNext(start);
            start = c;
            latest.setNext(start);
        }
        size++;
    }
    
    /**
     * BUSCAR NODO POR REFERENCIA EN LA LISTA CIRCULAR SIMPLE
     * @param plate
     * @return found
     */
    public boolean search(String plate){
        Car aux = start;
        do{
            if (aux.getPlate().equals(plate)){
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
    public void delete(String plate) {
        if(search(plate)) {
            if(start.getPlate().equals(plate)) {
                start = start.getNext();
                latest.setNext(start);
            } else {
                Car aux = start;
                while(!aux.getNext().getPlate().equals(plate)) {
                    aux = aux.getNext();
                }
                if(aux.getNext() == latest) {
                    aux.setNext(start);
                    latest = aux;
                } else {
                    Car next = aux.getNext();
                    aux.setNext(next.getNext());
                }
            }
            size--;
        }
    }
    
    /**
     * ACTUALIZAR NODO DE LA LISTA CIRCULAR SIMPLE
     * @param plate
     * @param brand
     * @param model
     * @param path
     */
    public void update(String plate, String brand, String model, String path) {
        if(search(plate)) {
            Car aux = start;
            do {
                if(aux.getPlate().equals(plate)) {
                    aux.setBrand(brand);
                    aux.setModel(model);
                    aux.setPath(path);
                }
                aux = aux.getNext();
            } while(aux != start);
        }
    }

}
