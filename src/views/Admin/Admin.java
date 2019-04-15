/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Admin;

import beans.Employee;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Juan José Ramos
 */
public class Admin {
    /*SINGLETON*/
    private static Admin instance;
    public static Admin getInstance(){
        if(instance == null){
            instance = new Admin();
        }
        return instance;
    }
    /*---------------*/

    public Admin() {
    }
    

    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {}
    }
    
    
}
