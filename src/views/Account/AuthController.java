/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Account;

import java.awt.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;

/**
 *
 * @author Juan José Ramos
 */
public class AuthController {
    /*VARIABLES*/
    @FXML
    private TextField username;
    private PasswordField password;
    
    
    
    /*METODO AUTENTICAR*/
    @FXML
    private void authenticate(ActionEvent event) {
        System.out.println(password.getText());
        System.out.println(username.getText());
    }
}
