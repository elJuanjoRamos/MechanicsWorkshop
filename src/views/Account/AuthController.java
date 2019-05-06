/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Account;

import beans.Client;
import beans.Employee;
import controllers.ClientsController;
import controllers.EmployeesController;
import views.Admin.Employee.EmployeeViewController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import views.Admin.Admin;
import views.Client.ClientView;

/**
 * FXML Controller class
 *
 * @author Juan José Ramos
 */
public class AuthController implements Initializable {

    /*VARIABLES*/
    @FXML
    private TextField user, pass, name, dpi, user2, pass2;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    
        
    @FXML
    private void authenticate(ActionEvent event) {
        
        String[] data = {user.getText(), pass.getText()};
        Employee emp = EmployeesController.getInstance().authenticate(data);
        Client cln = ClientsController.getInstance().authenticate(user.getText(), pass.getText());
        
        if (emp != null && cln == null) {
            if (emp.getRole().equals("Administrator")) {
                Admin.getInstance().start(Account.s);
            }
        } else if(emp == null && cln != null){
            ClientView.getInstance().start(Account.s, cln);
        } 
    }
    
    @FXML
    private void getAccess(ActionEvent event) {
        
        String[] data = {user2.getText()};
        Employee emp = EmployeesController.getInstance().authenticate(data);
        
        if (emp == null) {
            if (emp.getRole().equals("Administrator")) {
                EmployeesController.getInstance().addLast(name.getText(), "Administrator", user2.getText(), pass2.getText());
                Admin.getInstance().start(Account.s);
            }
        }   
    }
    
}
