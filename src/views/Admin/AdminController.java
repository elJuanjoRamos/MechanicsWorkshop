
package views.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import views.Account.AccountController;


public class AdminController implements Initializable {

    @FXML 
    private VBox vbox;
    @FXML
    private Parent fxml;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        changeView("/views/Admin/Home.fxml");
        
    }    
    @FXML
    private void open_employee(ActionEvent event) {
      changeView("/views/Admin/Employee/EmployeeView.fxml");
        
    }
    @FXML
    private void open_home(ActionEvent event) {
        changeView("/views/Admin/Home.fxml");
        
    }
    @FXML
    private void open_services(ActionEvent event) {
        changeView("/views/Admin/Service/ServicesView.fxml");
        
    }
    @FXML
    private void open_parts(ActionEvent event) {
        changeView("/views/Admin/SpareParts/SparePartsView.fxml");
        
    }
    @FXML
    private void open_cars(ActionEvent event) {
        changeView("/views/Admin/Car/FXMLCarView.fxml");
    }
    
    public void changeView(String component){
        try {
            fxml = (Parent) FXMLLoader.load(getClass().getResource(component));
            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(fxml);
        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
