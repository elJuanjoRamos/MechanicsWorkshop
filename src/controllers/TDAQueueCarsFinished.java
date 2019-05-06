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

/**
 *
 * @author Juan José Ramos
 */
public class TDAQueueCarsFinished {
     private static TDAQueueCarsFinished instance;
    public static TDAQueueCarsFinished getInstance() {
        if (instance == null) {
            instance = new TDAQueueCarsFinished();
        }
        return instance;
    }
    
    
    private WorkOrder firstNode;
    private int length;

    public TDAQueueCarsFinished() {
        firstNode = null;
        length = 0;
    }

    private boolean isEmpty() {
        return firstNode == null;
    }

    public void push(WorkOrder wo) {
        WorkOrder node = wo;
        if (isEmpty()) {
            firstNode = node;
        } else {
            getLastNode().setNext(node);
        }
        readNodes();
        length++;
    }

    public void readNodes() {
        WorkOrder auxiliaryNode = firstNode;
        while (auxiliaryNode != null) {
            System.out.print(auxiliaryNode.getClientName()+ " <-- ");
            auxiliaryNode = auxiliaryNode.getNext();
        }
        System.out.println();
    }

    
    public WorkOrder getTDAQueue(){
        return firstNode;
    }
    
    public void pop() {
        if (!isEmpty()) {
            firstNode = firstNode.getNext();
            length--;
        }
    }

    public Object getfirstNode() {
        if (!isEmpty()) {
            return firstNode;
        }
        return null;
    }

    public int getLength() {
        return length;
    }

    private WorkOrder getLastNode() {
        WorkOrder lastNode = firstNode;
        while (lastNode.getNext() != null) {
            lastNode = lastNode.getNext();
        }
        return lastNode;
    }

    private WorkOrder searchNode(int id) {
        WorkOrder auxiliaryNode = firstNode;
        while (auxiliaryNode != null && auxiliaryNode.getId() != id) {
            auxiliaryNode = auxiliaryNode.getNext();
        }
        return auxiliaryNode;
    }
}
