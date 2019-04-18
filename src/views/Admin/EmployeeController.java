
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
    public int count = 0;
    
    /*CONSTRUCTOR*/
    public EmployeeController() {
       
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        id.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        
        tableView.setItems(getEmployee());
        
        
    }    
     
    
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

    
    public ObservableList<Employee> getEmployee(){
        
        ObservableList<Employee> list = FXCollections.observableArrayList(this.array);
        return list;
    }
    
}
