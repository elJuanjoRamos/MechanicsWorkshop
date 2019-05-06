/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Admin.Car;

import beans.Car;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import controllers.CarController;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FXMLCarViewController implements Initializable {
    private static FXMLCarViewController instance;
    @FXML TableView<Car> tableView;
    @FXML TableColumn<Car, String> tableColumnPlate;
    @FXML TableColumn<Car, String> tableColumnBrand;
    @FXML TableColumn<Car, String> tableColumnModel;
    @FXML TableColumn<Car, String> tableColumnPath;
    
    @FXML TextField ePlate, eBrand, eModel, ePath, filter;
    @FXML Button aceptar, editar, eliminar, cancelar, subir;
    @FXML Text texto;
    @FXML ImageView imageView;
    @FXML StackPane stackPane;
    
    /*VARIABLES*/
    Image image = new Image("resources/img/car1.png");
        
    /**
     * @return the instance
    **/
    public static FXMLCarViewController getInstance(){
        if(instance == null){
            instance = new FXMLCarViewController();
        }
        return instance;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ePath = new TextField();
        editar.setVisible(false);
        cancelar.setVisible(false);
        texto.setText("Add a new Car");
        initTableView();
        tableColumnPlate.setCellValueFactory(new PropertyValueFactory<>("plate"));
        tableColumnBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        tableColumnModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        tableColumnPath.setCellValueFactory(new PropertyValueFactory<>("path"));
        imageView.setImage(image);
    }    
    
    /**
     * INICIALIZAR DATOS EN TABLA 
     */
    public void initTableView() {
        ObservableList<Car> observableList = CarController.getInstance().getCars();
        tableView.setItems(observableList);
    }
    
    /**
     * ELIMINAR DATOS
     */        
   @FXML
   private void delete(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            CarController.getInstance().delete(tableView.getSelectionModel().getSelectedItem().getId());
            tableView.getSelectionModel().clearSelection();
            initTableView();
        } else {
            getAlert(" No items have been selected.");
        }
    } 
    
    /**
     * AGREGAR DATOS
     */ 
   @FXML
   private void add(ActionEvent event) {
        if (getValidations() == true) {
            CarController.getInstance().addAtEnd(0, ePlate.getText(), eBrand.getText(), eModel.getText(), ePath.getText());
            clearFields();
            initTableView();
        }
    }
   
    /**
     * ACTUALIZAR DATOS
     */ 
    @FXML
    private void update(ActionEvent event) {
        if (getValidations() == true) {
            CarController.getInstance().update(0, tableView.getSelectionModel().getSelectedItem().getPlate(), eBrand.getText(), eModel.getText(), ePath.getText());
            clearFields();
            initTableView();
            aceptar.setVisible(true);
            editar.setVisible(false);
            cancelar.setVisible(false);
            texto.setText("Add a new Car");
        }
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
            Car c = CarController.getInstance().getCar(tableView.getSelectionModel().getSelectedItem().getPlate());
            if (c != null) {
                aceptar.setVisible(false);
                editar.setVisible(true);
                cancelar.setVisible(true);

                texto.setText("Edit the Spare Part");
                ePlate.setText(c.getPlate());
                ePlate.setDisable(true);
                eBrand.setText(c.getBrand());
                eModel.setText(c.getModel());
                ePath.setText(c.getPath());
                imageView.setImage(new Image(c.getPath()));
            }
        } else {
           getAlert(" No items have been selected.");
       }
    }
    
    @FXML
    private void subir(ActionEvent event) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG imagenes", "*.JPG"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            System.out.println(selectedFile);
            String path = "file:///" + selectedFile.toString().replace("/", File.separator);
            ePath.setText(path);
            imageView.setImage(new Image(path));
        }
    }
   
   
    /*VALIDA SI YA EXISTE EL NOMBRE DE USUARIO O SI DEJA CAMPOS EN BLANCO*/
    public boolean getValidations(){
        if(!ePlate.getText().isEmpty() && !eBrand.getText().isEmpty() &&
            !eModel.getText().isEmpty() && !ePath.getText().isEmpty()){
            return true;
        }
        getAlert("You can not leave fields blank.");
        return false;
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
        button.setOnAction((ActionEvent event) -> {
            dialog.close();
        });;
        c.setActions(button);
        dialog.show();
    }
    
    /**
     * LIMPIAR CAMPOS DE TEXTO
     */
    public void clearFields(){
       ePlate.clear();
       eBrand.clear();
       eModel.clear();
       ePath.clear();
       imageView.setImage(image);
    
    }
    
}
