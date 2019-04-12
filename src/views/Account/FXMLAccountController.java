/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Account;

import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Juan José Ramos
 */
public class FXMLAccountController implements Initializable {

    @FXML
    private VBox vbox;
    private TextField username;
    private PasswordField password;
    
    
    /**
     * Initializes the controller class.
     */
    
    private Parent fxml;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(vbox.getLayoutX() * 31);
        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(FXMLAccountController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
    }    
    @FXML
    private void open_logIn(ActionEvent event) {
         TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(vbox.getLayoutX() * 31);
        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(FXMLAccountController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
    }
    @FXML
    private void open_signIn(ActionEvent event) {
         TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(14);
        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource("FXMLSignIn.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(FXMLAccountController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
    }
    
    
    
}
