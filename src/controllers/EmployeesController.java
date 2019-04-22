
package controllers;

import beans.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeesController {
    private static EmployeesController instance;
    private ObservableList<Employee> employees;
    private int count = 1;
    private Employee first = null;
    private Employee last= null;
    
    
    public EmployeesController() {
        employees = FXCollections.observableArrayList();
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
        add("Juan Ramos", "Client", "c", "c");
        add("Juan Ramos", "Administrator", "admin", "admin");
        add("Rafael Morente", "Administrator", "rmorente", "admin");
        add("Luis Velasquez", "Administrator", "lvelasquez", "admin");
        add("Employee1", "Seller", "user1", "password1");
        add("Employee2", "Seller", "user2", "password2");
        add("Employee3", "Packer", "user3", "password3");
    }

    public ObservableList<Employee> getEmployees() {
        this.employees.clear();
        Employee actual = new Employee();
        actual = first;
        
        while (actual != null) {
            employees.add(actual);
            actual = actual.next;
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
        actual = first;
        temp = null;
        if (actual != null && actual.next == null && actual.previous == null) {
                actual = null;
                first = null;
                last = null;
                this.employees.clear();

        } else {
            while(actual != null){
                if (actual.getId() == id) {
                    if (actual == first) {
                        first = first.next;
                        first.previous = null;
                    } else if(actual == last){
                        temp = actual.previous;
                        temp.next = null;
                        last = temp;
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
    
    
    
    
    
    /*METODO PARA AUTENTICAR Y VERIFICAR SI EL USERNAME YA EXISTE EXISTE*/
    public Employee authenticate(String[] data){
        Employee actual = new Employee();
        actual = first;
        while(actual != null){
            if (data.length == 2) {
                if (actual.getUsername().equals(data[0]) && actual.getPassword().equals(data[1])) {
                    return actual;
                }
            } else {
                if (actual.getUsername().equals(data[0])) {
                    return actual;
                }
            }
            actual = actual.next;
            
        }
        return null;
    }
    
}
