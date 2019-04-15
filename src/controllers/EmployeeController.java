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
public class EmployeeController {
    
    /*SINGLETON*/
    private static EmployeeController instance;
    public static EmployeeController getInstance(){
        if(instance == null){
            instance = new EmployeeController();
        }
        return instance;
    }
    /*---------------*/
    
    
    /*Variables*/
    private int count = 0;
    private Employee first;
    private Employee last;
    
    
    public EmployeeController(){
        first = null;
        last = null;
    }
    
    public void add(String name, String role, String username, String password){
        Employee e = new Employee(count, name, role, username, password);
        if (first == null) {
            first = e;
            first.next = null;
            first.previous = null;
            last = first;
        } else {
            last.next = e;
            e.previous = last;
            e.next = null;
            last = e;
        }
        count++;
    }
    
    public void show(){
        Employee actual = new Employee();
        actual = last;
        while (actual != null) {
            System.out.println(actual.getName());
            actual = actual.previous;
        }
    }
    public void showNormal(){
       Employee e = new Employee();
       e = first;
       while (e != null) {
           System.out.println("el nombre es : "+ e.getId() +" " + e.getName());
           e = e.next;
       }
   }
    
    
                    
    /*------------BUSCAR EMPLEADO---------------*/
    public Employee search(int id){
        Employee actual = new Employee();
        actual = first;
        while(actual != null){
            if (actual.getId() == id) {
                return actual;
            }
            actual = actual.next;
        }
        return null;
    }
    
    public Employee authenticate(String username, String password){
        Employee actual = new Employee();
        actual = first;
        while(actual != null){
            if (actual.getUsername().equals(username) && actual.getPassword().equals(password)) {
                return actual;
            }
            actual = actual.next;
        }
        return null;
    }
    /*---------------------------*/
    
    
    
    public void edit(int id, String name, String role, String username, String password ){
        Employee actual = new Employee();
        actual = first;
        while(actual != null){
            if (actual.getId() == id) {
                actual.setName(name);
                actual.setPassword(password);
                actual.setRole(role);
                actual.setUsername(username);
        
            }
            actual = actual.next;
        }
        
    }
    
    public void delete(int id){
        Employee actual = new Employee();
        Employee temp = new Employee();
        temp = null;
        actual = first;
        while(actual != null){
            if (actual.getId() == id) {
                if (actual == first) {
                    first = first.next;
                    first.previous = null;
                } else {
                    temp.next = actual.next;
                    actual.next.previous = actual.previous; 
                }
            }
            temp = actual;
            actual = actual.next;
        }
    }
}
