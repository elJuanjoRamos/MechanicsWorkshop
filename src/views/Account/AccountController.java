/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Account;

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
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Juan José Ramos
 */
public class AccountController implements Initializable {

    @FXML
    private VBox vbox;
    
    @FXML
    private Parent fxml;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(vbox.getLayoutX() * 31);
        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = (Parent) FXMLLoader.load(getClass().getResource("/views/Account/Login.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ex) {
                System.out.println(ex);
                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
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
                fxml = (Parent) FXMLLoader.load(getClass().getResource("/views/Account/Login.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ex) {
                System.out.println(ex);
                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
    }
    @FXML
    private void open_signIn(ActionEvent event) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(30);
        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = (Parent)FXMLLoader.load(getClass().getResource("/views/Account/SignIn.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
    }
    
    
}
