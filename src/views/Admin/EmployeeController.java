/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Admin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import beans.Employee;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Juan José Ramos
 */
public class EmployeeController implements Initializable {

    
     /*SINGLETON*/
    private static EmployeeController instance;
    public static EmployeeController getInstance(){
        if(instance == null){
            instance = new EmployeeController();
        }
        return instance;
    }
    /*---------------*/

    
    /*VARIABLES*/
    @FXML TableView<Employee> tableView;
    @FXML TableColumn<Employee, Integer> id;
    @FXML TableColumn<Employee, String> name;
    public ArrayList<Employee> array = new ArrayList<>();
    public int count = 1;
    
    /*CONSTRUCTOR*/
    public EmployeeController() {
       
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        id.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        add("Employee0", "Administrator", "user1", "password1");
        add("Employee1", "Seller", "user1", "password1");
        add("Employee2", "Seller", "user1", "password1");
        add("Employee3", "Packer", "user1", "password1");
        ObservableList<Employee> list = FXCollections.observableArrayList(getArray());
        tableView.setItems(list);
        //tableView.setItems(getEmployee2());
        //tableView.setItems(getEmployee());
        
        
    }    
     
    /**********************************/
    /*  RAFA LEE LOS COMENTARIOS, ES IMPORTANTE*/
    /**********************************/
    
    
    
    /*AQUI CREE UN METODO A MODO DE PRUEBA DONDE SE GUARDAN EMPLEADOS, LOS RECIBO DESDE LA MAIN VIEWS/MAIN*/
    public void add(String name, String role, String username, String password){
        array.add(new Employee(count, name,role,username,password));
        count++;
    }
    
    
    /*METODO PARA AUTENTICAR*/
    public Employee authenticate(String username, String password){
        for (Employee e : array) {
            if (e.getUsername().equals(username) && e.getPassword().equals(password)) {
                return e;
            }
        }
        return null;
    }
    
    /*NUEVO METODO GET EMPLOYEE*/
    public ArrayList<Employee> getArray() {
        return array;
    }

    /*AQUI ES DONDE SE SUPONE OBTENGO LA INFORMACION PARA MOSTRAR*/
    public ObservableList<Employee> getEmployeeTest(){
        
        /*SI SOLO LO HAGO ASÍ, LA TABLA APARECE VACÍA*/
        ObservableList<Employee> list = FXCollections.observableArrayList(this.array);
        
        return list;
    }
    
    
    /*SI EN EL EL METODO initialize() LLAMAS ESTE METODO */
    public ObservableList<Employee> getEmployee2(){
        
        ObservableList<Employee> list = FXCollections.observableArrayList();
        
        /*AQUI AGREGO EMPLEDOS DIRECTAMENTE Y ESTOS SI LOS MUESTRA*/
        list.add(new Employee(0,"Employee0", "Administrator", "user1", "password1"));
        list.add(new Employee(1,"Employee1", "Seller", "user1", "password1"));
        list.add(new Employee(2,"Employee2", "Seller", "user1", "password1"));
        list.add(new Employee(3,"Employee3", "Packer", "user1", "password1"));
        
        
        return list;
    }
    
    
}
