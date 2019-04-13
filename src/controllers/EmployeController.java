/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Employee;

/**
 *
 * @author Juan José Ramos
 */
public class EmployeController {
    /*Variables*/
    
    private int count;
    private Employee next;
    private Employee previus;
    
    
    /*Constructor*/
    public EmployeController() {
    }
    
     /*SINGLETON*/
    private static EmployeController instance;
    public static EmployeController getInstance(){
        if(instance == null){
            instance = new EmployeController();
        }
        return instance;
    }
    
    
    
    
}
