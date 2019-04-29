
package views.Admin.Client;

import beans.Client;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import controllers.ClientController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Jose Morente
 */
public class FXMLClientViewController implements Initializable {    
    private static FXMLClientViewController instance;
    private ObservableList<String> observableRoles;
    @FXML TableView<Client> tableView;
    @FXML TableColumn<Client, Long> dpi;
    @FXML TableColumn<Client, String> fullname;
    @FXML TableColumn<Client, String> username;
    @FXML TableColumn<Client, String> password;
    @FXML TableColumn<Client, String> role;
    
    @FXML ComboBox combo;
    @FXML TextField ePlate, eBrand, eModel, ePath, filter;
    @FXML TextField eName, eUsername, ePassword;
    @FXML Button aceptar, editar, eliminar, cancelar, subir;
    @FXML Text texto;
    @FXML StackPane stackPane;

    public FXMLClientViewController() {
    }
    
    
    /**
     * @return the instance
    **/
    public static FXMLClientViewController getInstance(){
        if(instance == null){
            instance = new FXMLClientViewController();
        }
        return instance;
    }

    public ObservableList<String> getObservableRoles() {
        String[] array = {"Oro", "Normal"};
        observableRoles = FXCollections.observableArrayList(array);
        return observableRoles;
    }
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combo.setItems(getObservableRoles());
        //ePath = new TextField();
        editar.setVisible(false);
        cancelar.setVisible(false);
        texto.setText("Add a new Spear Part");
        initTableView();
        dpi.setCellValueFactory(new PropertyValueFactory<>("dpi"));
        fullname.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
    }    
    
    /**
     * INICIALIZAR DATOS EN TABLA 
     */
    public void initTableView() {
        ObservableList<Client> observableList = ClientController.getInstance().getClients();
        tableView.setItems(observableList);
    }
    
    /**
     * ELIMINAR DATOS
     */        
   @FXML
   private void delete(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            ClientController.getInstance().delete(tableView.getSelectionModel().getSelectedItem().getDpi());
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
            ClientController.getInstance().addAtEnd(eName.getText(), eUsername.getText(), ePassword.getText(), combo.getSelectionModel().getSelectedItem().toString());
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
            ClientController.getInstance().update(tableView.getSelectionModel().getSelectedItem().getDpi(), 
                eName.getText(), 
                eUsername.getText(), 
                ePassword.getText(), 
                combo.getSelectionModel().getSelectedItem().toString());
            clearFields();
            initTableView();
            aceptar.setVisible(true);
            editar.setVisible(false);
            cancelar.setVisible(false);
            texto.setText("Add a new Spare Part");
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
   
    /**
     * OBTENER DATOS
     */ 
    @FXML
    private void getSpare(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            Client c = ClientController.getInstance().getClient(tableView.getSelectionModel().getSelectedItem().getDpi());
            if(c != null) {
                aceptar.setVisible(false);
                editar.setVisible(true);
                cancelar.setVisible(true);
                texto.setText("Edit the Spare Part");
                eName.setText(c.getFullName());
                ePassword.setText(c.getPassword());
                eUsername.setText(c.getUsername());
                combo.getSelectionModel().select(c.getRole());
            }
        } else {
           getAlert(" No items have been selected.");
       }
    }
    
    @FXML
    public void bulkLoad(ActionEvent event) {
        
    }
    
    /*VALIDA SI YA EXISTE EL NOMBRE DE USUARIO O SI DEJA CAMPOS EN BLANCO*/
    public boolean getValidations(){
        if(!eName.getText().isEmpty() && !eUsername.getText().isEmpty() &&
            !ePassword.getText().isEmpty() && combo.getSelectionModel().getSelectedItem() != null){
            return true;
        }
        getAlert("You can not leave fields blank.");
        return false;
    }
    
    public boolean isNumber(String option, String option2){
       /*boolean resultado;
        try {
            Double.parseDouble(option);
            Integer.parseInt(option2);
            resultado = true;
        } catch (NumberFormatException e) {
            resultado = false;
        }*/
        
        return false;   
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
       eName.clear();
       ePassword.clear();
       eUsername.clear();
       combo.getSelectionModel().clearSelection();
    }