/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Account;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

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
        if (user.getText().equals("admin") && pass.getText().equals("admin")) {
            System.out.println(user.getText());
        }
        
    }
    
}
