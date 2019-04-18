/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Account;

import beans.Employee;
import views.Admin.EmployeeController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import views.Admin.Admin;

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
        // TODO
    }
    
    
        
    @FXML
    private void authenticate(ActionEvent event) {
        
        
        Employee emp = EmployeeController.getInstance().authenticate(user.getText(), pass.getText());
        if (emp != null) {
            if (emp.getRole().equals("Administrator")) {
                Admin.getInstance().start(Account.s);
            }
        }
        
        
        
       
        
        
    }
    
    
    
}
