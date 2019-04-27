/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Admin.Service;

import beans.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import controllers.ServicesController;
import controllers.SparesPartsController;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Juan José Ramos
 */
public class ViewInsertServiceController implements Initializable {

     /*SINGLETON*/
    private static ViewInsertServiceController instance;
    public static ViewInsertServiceController getInstance(){
        if(instance == null){
            instance = new ViewInsertServiceController();
        }
        return instance;
    }
    /*---------------*/
    
    @FXML ComboBox list;
    @FXML Button add, remove, ok;
    @FXML TableView<SpareParts> tableView;
    @FXML TableColumn<SpareParts, Integer> id;
    @FXML TableColumn<SpareParts, String> name;
    @FXML TableColumn<SpareParts, String> price;
    @FXML TextField sName, sMark, sModel, sPrice;
    @FXML StackPane stackPane;
    @FXML Text textInfo;
    
    private Service serviceAux = ServicesController.getInstance().getAux();
    
    public ViewInsertServiceController() {
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list.setItems(null);
        list.setItems(SparesPartsController.getInstance().getSparePartsName());
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        initTableView();
        loadService();
    }    
    
    /*CARGA EL SERVICIO Y SUS PARTES*/
    
    public void loadService(){
        sName.setText(serviceAux.getName());
        sPrice.setText(String.valueOf(serviceAux.getWorkPrice()));
        sMark.setText(serviceAux.getMark());
        sModel.setText(serviceAux.getModel());
    }
    
    
    
     /*INICIALIZAR TABLA*/
    public void initTableView() {
        ObservableList observable = FXCollections.observableArrayList();
        
        
            for (int i = 0; i < serviceAux.getSparePartList().size(); i++) {
                if (serviceAux.getSparePartList().get(i) != serviceAux.getSparePartList().get(0)) {
                    
                    observable.add(serviceAux.getSparePartList().get(i));

                }
    
                
            }
            tableView.setItems(observable);
        
        
        
    }
    
    @FXML
    public void addPartsToTable(ActionEvent event){
        if (list.getSelectionModel().getSelectedItem() != null) {
            Stack stackAux = serviceAux.getSparePartList();
            stackAux.push(SparesPartsController.getInstance().searchForName(list.getSelectionModel().getSelectedItem().toString()));
            serviceAux.setSparePartList(stackAux);
        } 
        initTableView();
    } 
    @FXML
    public void addFinalSparePartsList(ActionEvent event){
        if (serviceAux.getSparePartList().size() > 1) {
            
            Double price = serviceAux.getSparePartsPrice();
            
            for (int i = 0; i < serviceAux.getSparePartList().size(); i++) {
                SpareParts o = (SpareParts)serviceAux.getSparePartList().get(i);
                price = price + o.getPrice();
            }
            
            ServicesController.getInstance().edit(serviceAux.getId(), serviceAux.getName() , serviceAux.getMark() , serviceAux.getModel(), 
                    serviceAux.getSparePartList(), Double.parseDouble(sPrice.getText()) , price);
            
            ServicesViewController.getInstance().initTableView();
            
        }
    }
    
    @FXML
    public void updateInfo(ActionEvent event){
        if (getValidations()) {
            ServicesController.getInstance().edit(serviceAux.getId(), sName.getText() , sMark.getText() , sModel.getText() , 
                    serviceAux.getSparePartList(), Double.parseDouble(sPrice.getText()) , serviceAux.getSparePartsPrice());
            
            ServicesViewController.getInstance().initTableView();
            
        } 
    }
    
    
    @FXML
    public void deleteFromTable(ActionEvent event){
        Stack stackAux = serviceAux.getSparePartList();
        if (stackAux.size() > 1) {
            stackAux.pop();
            serviceAux.setSparePartList(stackAux);
        }
        
        ServicesViewController.getInstance().initTableView();
            
        
    }
    
    /*VALIDA QUE LOS CAMPOS NO SEAN VACIOS*/
    public boolean getValidations(){
        if(!sName.getText().isEmpty() && !sMark.getText().isEmpty() &&
                 !sModel.getText().isEmpty() && !sPrice.getText().isEmpty()){
            if (ServicesController.getInstance().searchForName(sName.getText()) == null) {
                try {
                    Double.parseDouble(sPrice.getText());
                    return true;
                } catch (NumberFormatException e) {
                    textInfo.setText("You can not enter text in numeric fields");
                    return false;
                }
            } else {
                textInfo.setText("There is already a service with the same name");
                return false;
            }
            
        } else {
            textInfo.setText("You can not leave fields blank.");
            return false;
            
         }
    }
    
}
