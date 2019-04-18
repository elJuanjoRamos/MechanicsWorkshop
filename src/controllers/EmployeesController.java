/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Employee;
import java.util.ArrayList;

/**
 *
 * @author Jose Morente
 */
public class EmployeesController {
    private static EmployeesController instance;
    private ArrayList<Employee> employees;
    private int count = 1;
    private Employee first = null;
    private Employee last= null;
    
    
    public EmployeesController() {
        employees = new ArrayList<>();
        first = null;
        last = null;
    }
    
    
    public static EmployeesController getInstance() {
        if(instance == null) {
            instance = new EmployeesController();
        }
        return instance;
    }
    
    public void initEmployee() {
        add("Employee0", "Administrator", "admin", "admin");
        add("Employee1", "Seller", "user1", "password1");
        add("Employee2", "Seller", "user2", "password2");
        add("Employee3", "Packer", "user3", "password3");
    }

    public ArrayList<Employee> getEmployees() {
        Employee actual = new Employee();
        actual = last;
        
        while (actual != null) {
            employees.add(actual);
            actual = actual.previous;
        }
        
        return employees;
    }

    /*METODO AGREGAR*/
    public void add(String name, String role, String username, String password){
        Employee e = new Employee(count, name, role, username, password);
        
        if (first == null) {
            this.first = e;
            first.next = null;
            first.previous = null;
            this.last = first;
        } else {
            this.last.next = e;
            e.previous = last;
            e.next = null;
            this.last = e;
        }
        count++;
    }
    /*METODO MOSTRAR */
    public void show(){
        Employee actual = new Employee();
        actual = last;
        
        while (actual != null) {
            System.out.println( "esto es mostrar"+ actual.getName());
            actual = actual.previous;
        }
    }
    
    /*------------METODO BUSCAR---------------*/
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
    /*METODO EDITAR*/
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
    /*METODO ELIMINAR*/
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
    
    
    
    
    
    /*METODO PARA AUTENTICAR*/
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
    
    
}
