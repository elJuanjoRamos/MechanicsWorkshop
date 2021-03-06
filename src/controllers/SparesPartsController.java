/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.SpareParts;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Stack;

public class SparesPartsController {

    private static SparesPartsController instance;

    /*SINGLETON*/
    public static SparesPartsController getInstance() {
        if (instance == null) {
            instance = new SparesPartsController();
        }
        return instance;
    }
    /*VARIABLES*/
    private ObservableList<SpareParts> parts;
    private int count = 1;
    protected SpareParts first;

    public SparesPartsController() {
        parts = FXCollections.observableArrayList();
        first = null;
    }

    /*CONSULTA SI ESTA VACIA LA LISTA*/
    public boolean isNull() {
        return first == null;
    }
    

    

    /*METODO AGREGAR*/
    public void add(String name, String mark, String model, int stock, Double price) {
        SpareParts newSpareParts = new SpareParts(count, name, mark, model, stock, price);
        // Consulta si la lista esta vacia.
        if (isNull()) {
            // Inicializa la lista agregando como inicio al nuevo nodo.
            first = newSpareParts;
        // Caso contrario recorre la lista hasta llegar al ultimo nodo
            //y agrega el nuevo.
        } else {
            // Crea ua copia de la lista.
            SpareParts aux = first;
            // Recorre la lista hasta llegar al ultimo nodo.
            while (aux.getNext() != null) {
                aux = aux.getNext();
            }
            // Agrega el nuevo nodo al final de la lista.
            aux.setNext(newSpareParts);
        }
        // Incrementa el contador de tamaño de la lista
        count++;
    }

    /*METODO BUSCAR*/
    public SpareParts search(int id) {
        // Crea una copia de la lista.
        SpareParts aux = first;
        // Recorre la lista hasta encontrar el elemento o hasta 
        // llegar al final de la lista.
        while (aux != null) {
            // Consulta si el valor del nodo es igual al de referencia.
            if (aux.getId() == id) {
                return aux;
            }
            aux = aux.getNext();
        }
        // Retorna el null.
        return null;
    }
    /*BUSCAR POR NOMBRE*/
    public SpareParts searchForName(String name) {
        // Crea una copia de la lista.
        SpareParts aux = first;
        // Recorre la lista hasta encontrar el elemento o hasta 
        // llegar al final de la lista.
        while (aux != null) {
            // Consulta si el valor del nodo es igual al de referencia.
            if (aux.getName().equalsIgnoreCase(name)) {
                return aux;
            }
            aux = aux.getNext();
        }
        // Retorna el null.
        return null;
    }
    /*METODO AGREGAR*/

    public void edit(int id, String name, String mark, String model, int stock, Double price) {
        SpareParts aux = first;
        while (aux != null) {
            if (aux.getId() == id) {
                aux.setName(name);
                aux.setMark(mark);
                aux.setModel(model);
                aux.setStock(stock);
                aux.setPrice(price);
            }
            aux = aux.getNext();
        }
    }

    /*METODO ELIMINAR*/
    public void delete(int id) {
        // Consulta si el valor de referencia existe en la lista.
        if (search(id) != null) {
            // Consulta si el nodo a eliminar es el pirmero
            if (first.getId() == id) {
                // El primer nodo apunta al siguiente.
                first = first.getNext();
            } else {

                SpareParts aux = first;

                while (aux.getNext().getId() != id) {
                    aux = aux.getNext();
                }
                // Guarda el nodo siguiente del nodo a eliminar.
                SpareParts next = aux.getNext().getNext();
                // Enlaza el nodo anterior al de eliminar con el 
                // sguiente despues de el.
                aux.setNext(next);
            }
        }
    }
    /*METODO OBTENER LAS PARTES DE REPUESTOS*/
    public ObservableList<SpareParts> getSpareParts() {
        this.parts.clear();

        // Crea una copia de la lista.
        SpareParts aux = first;
        // Posicion de los elementos de la lista.
        // Recorre la lista hasta el final.
        while (aux != null) {
            parts.add(aux);
            aux = aux.getNext();

        }
        return parts;
    }
    public ObservableList<String> getSparePartsName(String model, String mark) {
        ObservableList<String> partsName = FXCollections.observableArrayList();

        // Crea una copia de la lista.
        SpareParts aux = first;
        // Posicion de los elementos de la lista.
        // Recorre la lista hasta el final.
        while (aux != null) {
            if (aux.getModel().equalsIgnoreCase(model) && aux.getMark().equalsIgnoreCase(mark) && aux.getStock() > 0) {
                partsName.add(aux.getName());
            }
            aux = aux.getNext();

        }
        return partsName;
    }
    
    
    public void show() {
        // Crea una copia de la lista.
        SpareParts aux = first;
        // Posicion de los elementos de la lista.
        // Recorre la lista hasta el final.
        while (aux != null) {
            System.out.println(aux.getName());    
            aux = aux.getNext();

        }
    }
    
    
    
}

