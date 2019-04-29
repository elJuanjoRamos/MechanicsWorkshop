/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Admin.SpareParts;

import beans.SpareParts;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import controllers.InterpreterController;
import controllers.SparesPartsController;
import java.net.URL;
import java.util.ResourceBundle;
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
public class SparePartsViewController implements Initializable {

      /*SINGLETON*/
    private static SparePartsViewController instance;
    public static SparePartsViewController getInstance(){
        if(instance == null){
            instance = new SparePartsViewController();
        }
        return instance;
    }
    /*---------------*/

    @FXML TableView<SpareParts> tableView;
    @FXML TableColumn<SpareParts, Integer> id;
    @FXML TableColumn<SpareParts, String> name;
    @FXML TableColumn<SpareParts, String> mark;
    @FXML TableColumn<SpareParts, String> model;
    @FXML TableColumn<SpareParts, String> stock;
    @FXML TableColumn<SpareParts, String> price;
    
    @FXML TextField eName, eMark, eModel, ePrice, eStock, filter;
    @FXML Button aceptar, editar, eliminar, cancelar;
    @FXML Text texto;
    @FXML StackPane stackPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        editar.setVisible(false);
        cancelar.setVisible(false);
        texto.setText("Add a new Spear Part");
        
        
        initTableView();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        mark.setCellValueFactory(new PropertyValueFactory<>("mark"));
        model.setCellValueFactory(new PropertyValueFactory<>("model"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        
    }    
    
    /*INICIALIZAR TABLA*/
    public void initTableView() {
        
        try {
            ObservableList<SpareParts> observableList = SparesPartsController.getInstance().getSpareParts();
            tableView.setItems(observableList);
        
        } catch (Exception e){
            
        }
        
    }
    
    
    
      /*METODO ELIMINAR*/         
   @FXML
   private void delete(ActionEvent event) {
       if (tableView.getSelectionModel().getSelectedItem() != null) {
            SparesPartsController.getInstance().delete(tableView.getSelectionModel().getSelectedItem().getId());
            tableView.getSelectionModel().clearSelection();
            initTableView();
        } else {
           getAlert(" No items have been selected.");
       }
    } 
   
   
   
    /*METODO AGREGAR*/
   @FXML
   private void add(ActionEvent event) {
       if (getValidations()) {
           if (isNumber(ePrice.getText(), eStock.getText())) {
                SparesPartsController.getInstance().add(eName.getText(), eMark.getText(), eModel.getText(), Integer.parseInt(eStock.getText()), Double.parseDouble(ePrice.getText()));
                clearFields();
                initTableView();
            } else {
                getAlert("You can not enter text in numeric fields");
            }
       }
    }
   
   @FXML
    private void update(ActionEvent event) {
        if (getValidations() == true) {
            if (isNumber(ePrice.getText(), eStock.getText())) {
                SparesPartsController.getInstance().edit(tableView.getSelectionModel().getSelectedItem().getId(),  
                        eName.getText(), eMark.getText(), eModel.getText(), Integer.parseInt(eStock.getText()), Double.parseDouble(ePrice.getText()));
                clearFields();
                initTableView();
                aceptar.setVisible(true);
                editar.setVisible(false);
                cancelar.setVisible(false);
                texto.setText("Add a new Spare Part");
            } else {
                getAlert("You can not enter text in numeric fields");
            }
        }
    }
   
    @FXML
    private void bulkLoad(ActionEvent event) {
        InterpreterController.getInstance().openSelecFile("*.tmr");
       
    }
   
   @FXML
    private void cancel(ActionEvent event) {
       clearFields();
       initTableView();
       aceptar.setVisible(true);
       editar.setVisible(false);
       cancelar.setVisible(false);
       texto.setText("Add a new Spare Part");
    }
   
   /*Actualizar*/
    @FXML
    private void getSpare(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            SpareParts e = SparesPartsController.getInstance().search(tableView.getSelectionModel().getSelectedItem().getId());
            if (e != null) {
                
                aceptar.setVisible(false);
                editar.setVisible(true);
                cancelar.setVisible(true);
                texto.setText("Edit the Spare Part");

                eName.setText(e.getName());
                eMark.setText(e.getMark());
                eModel.setText(e.getModel());
                ePrice.setText(String.valueOf(e.getPrice()));
                eStock.setText(String.valueOf(e.getStock()));
            }
        } else {
           getAlert(" No items have been selected.");
       }
    }
    
   
   
    /*VALIDA SI YA EXISTE EL NOMBRE DE USUARIO O SI DEJA CAMPOS EN BLANCO*/
    public boolean getValidations(){
        if(!eName.getText().isEmpty() && !eMark.getText().isEmpty() &&
                 !eModel.getText().isEmpty() && !ePrice.getText().isEmpty() && !eStock.getText().isEmpty()){
            return true;
            
        } else {
            getAlert("You can not leave fields blank.");
            return false;
            
         }
    }
    
    public boolean isNumber(String option, String option2){
       boolean resultado;
        
        try {
            Double.parseDouble(option);
            Integer.parseInt(option2);
            resultado = true;
        } catch (NumberFormatException e) {
            resultado = false;
        }
        
        return resultado;   
    }
    
    public void getAlert(String content) {
        
        JFXDialogLayout c = new JFXDialogLayout(); 
        JFXDialog dialog = new JFXDialog(stackPane, c, JFXDialog.DialogTransition.CENTER);
        c.setHeading(new Text("Error!"));
        c.setBody(new Text(content));
        JFXButton button = new JFXButton("Close");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });;
        c.setActions(button);
        
        dialog.show();
        
    }
    public void clearFields(){
       eName.clear();
       eMark.clear();
       eModel.clear();
       ePrice.clear();
       eStock.clear();
    }
}
