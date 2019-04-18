/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Admin;

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
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import views.Account.AccountController;

/**
 * FXML Controller class
 *
 * @author Juan José Ramos
 */
public class AdminController implements Initializable {

    @FXML 
    private VBox vbox;
    @FXML
    private Parent fxml;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        transition("/views/Admin/Home.fxml");
        
    }    
    @FXML
    private void open_employee(ActionEvent event) {
      transition("/views/Admin/Employee.fxml");
        
    }
    @FXML
    private void open_home(ActionEvent event) {
       // transition("/views/Admin/Home.fxml");
        
    }
    
    public void transition(String component){
        try {
                fxml = (Parent) FXMLLoader.load(getClass().getResource(component));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ex) {
                System.out.println(ex);
                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
            }
        /*TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(vbox.getLayoutX());
        t.play();
        t.setOnFinished((e) -> {
            
            
        });*/
    }
}
