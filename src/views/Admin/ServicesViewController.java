/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Admin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import beans.Service;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import controllers.ServicesController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class ServicesViewController implements Initializable {

    
     /*SINGLETON*/
    private static ServicesViewController instance;
    public static ServicesViewController getInstance(){
        if(instance == null){
            instance = new ServicesViewController();
        }
        return instance;
    }
    /*---------------*/
    
    /*VARIABLES*/
    @FXML TableView<Service> tableView;
    @FXML TableColumn<Service, Integer> id;
    @FXML TableColumn<Service, String> name;
    @FXML TableColumn<Service, String> mark;
    @FXML TableColumn<Service, String> model;
    @FXML TableColumn<Service, String> workPrice;
    @FXML TableColumn<Service, String> spPrice;
    @FXML StackPane stackPane;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTableView();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        mark.setCellValueFactory(new PropertyValueFactory<>("mark"));
        model.setCellValueFactory(new PropertyValueFactory<>("model"));
        workPrice.setCellValueFactory(new PropertyValueFactory<>("workPrice"));
        spPrice.setCellValueFactory(new PropertyValueFactory<>("sparePartsPrice"));
        
    }    
    
    /*INICIALIZAR TABLA*/
    public void initTableView() {
            
        ObservableList<Service> observableList = ServicesController.getInstance().getServices();
        tableView.setItems(observableList);
    }
    @FXML
    private void delete_Service(ActionEvent event) {
       if (tableView.getSelectionModel().getSelectedItem() != null) {
            ServicesController.getInstance().delete(tableView.getSelectionModel().getSelectedItem().getId());
            tableView.getSelectionModel().clearSelection();
            initTableView();
        }
    }
    
    @FXML
    private void open_Dialog(ActionEvent event) {
        JFXDialogLayout content = new JFXDialogLayout(); 
        JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
        content.setHeading(new Text("Add a new Service"));
        
        
        HBox hbox = new HBox();
        Label l = new Label("Name");
        TextField name = new TextField();
        
        HBox hbox1 = new HBox();
        Label l1 = new Label("PASSWORD");
        TextField name1 = new TextField();
        
        
        hbox.getChildren().addAll(l, name);
        hbox1.getChildren().addAll(l1, name1);
        
        content.setBody(hbox, null, hbox1);
        
        
        JFXButton button = new JFXButton("Close");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(name.getText());
                dialog.close();
            }
        });;
        
        content.setActions(button);
        
        dialog.show();
    }
   
}
