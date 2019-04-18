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

    public EmployeesController() {
        employees = new ArrayList<>();
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
        return employees;
    }

    public void add(String name, String role, String username, String password){
        employees.add(new Employee(count, name,role,username,password));
        count++;
    }
    
    /*METODO PARA AUTENTICAR*/
    public Employee authenticate(String username, String password){
        for (Employee e : employees) {
            if (e.getUsername().equals(username) && e.getPassword().equals(password)) {
                return e;
            }
        }
        return null;
    }
    
    
}
