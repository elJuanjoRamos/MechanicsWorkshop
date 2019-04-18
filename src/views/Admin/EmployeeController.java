/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Admin;

import controllers.EmployeesController;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import beans.Employee;

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
    public ArrayList<Employee> array;
    private ObservableList<Employee> observableList;
    public int count = 1;
    
    /*CONSTRUCTOR*/
    public EmployeeController() {
        array = new ArrayList<>();
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        initTableView();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
    }    
    
    /*INICIALIZAR TABLA*/
    public void initTableView() {
        observableList = FXCollections.observableArrayList(EmployeesController.getInstance().getEmployees());
        tableView.setItems(observableList);
    }
     
    
}
