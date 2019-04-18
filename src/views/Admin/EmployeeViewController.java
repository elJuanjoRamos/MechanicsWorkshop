/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Admin;


import controllers.EmployeesController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import beans.Employee;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.*;

public class EmployeeViewController implements Initializable {

    
     /*SINGLETON*/
    private static EmployeeViewController instance;
    public static EmployeeViewController getInstance(){
        if(instance == null){
            instance = new EmployeeViewController();
        }
        return instance;
    }
    /*---------------*/

    
    /*VARIABLES*/
    private ObservableList<String> roles;
    @FXML TableView<Employee> tableView;
    @FXML TableColumn<Employee, Integer> id;
    @FXML TableColumn<Employee, String> name;
    @FXML TableColumn<Employee, String> username;
    @FXML TableColumn<Employee, String> password;
    @FXML TableColumn<Employee, String> role;
    
    
    @FXML ComboBox combo;
    @FXML TextField eName;
    @FXML TextField eUsername;
    @FXML TextField ePassword;
    @FXML Button aceptar, editar, eliminar;
    @FXML Text texto;
    
    public int count = 1;
    
    /*CONSTRUCTOR*/
    public EmployeeViewController() {
        
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        editar.setVisible(false);
        texto.setText("Add a new Employee");
        String[] array = {"Administrator", "Mechanic", "Payer"};
        roles = FXCollections.observableArrayList(array);
        combo.setItems(roles);
        
        
        initTableView();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
    }    
    
    /*INICIALIZAR TABLA*/
    public void initTableView() {
            
        ObservableList<Employee> observableList = EmployeesController.getInstance().getEmployees();
        tableView.setItems(observableList);
    }
    
    /*METODO AGREGAR DEL BOTON*/
    @FXML
    private void add_Employee(ActionEvent event) {
         if (!eName.getText().isEmpty() && !eUsername.getText().isEmpty() &&
                 !ePassword.getText().isEmpty() && combo.getSelectionModel().getSelectedItem() != null) {
             String[] data = {eUsername.getText()};
             Employee e = EmployeesController.getInstance().authenticate(data);
             if (e == null) {
                 EmployeesController.getInstance().add(eName.getText(), combo.getSelectionModel().getSelectedItem().toString(), eUsername.getText(), ePassword.getText());
                 eName.clear();
                 ePassword.clear();
                 eUsername.clear();
                 initTableView();
             }
         }
  
    }
    
    
            
   @FXML
   private void getSelection(ActionEvent event) {
       if (tableView.getSelectionModel().getSelectedItem() != null) {
            EmployeesController.getInstance().delete(tableView.getSelectionModel().getSelectedItem().getId());
            tableView.getSelectionModel().clearSelection();
            initTableView();
        }
    } 
    
    
    
}
