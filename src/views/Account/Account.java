/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Account;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Juan José Ramos
 */
public class Account {
 
    /*SINGLETON*/
    private static final Account instance = new Account();
    
    
    private Account(){
        
    }
    
    public static Account getInstance(){
        return instance;
    }
    
    
    /*Inicio de aplicacion*/
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLAccount.fxml"));
        
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        
        stage.show();
    }
}
