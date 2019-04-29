/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Admin.Service;

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
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class ServicesViewController implements Initializable {

    /*SINGLETON*/
    private static ServicesViewController instance;

    public static ServicesViewController getInstance() {
        if (instance == null) {
            instance = new ServicesViewController();
        }
        return instance;
    }
    /*---------------*/

    /*VARIABLES*/
    @FXML
    TableView<Service> tableView;
    @FXML
    TableColumn<Service, Integer> id;
    @FXML
    TableColumn<Service, String> name;
    @FXML
    TableColumn<Service, String> mark;
    @FXML
    TableColumn<Service, String> model;
    @FXML
    TableColumn<Service, String> workPrice;
    @FXML
    TableColumn<Service, String> spPrice;
    @FXML
    StackPane stackPane;

    @FXML
    Button cancelar, editar;
    @FXML
    TextField sName, sMark, sModel, sPrice;
    public static Service s;

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
        try {
            ObservableList<Service> observableList = ServicesController.getInstance().getServices();
            tableView.setItems(observableList);
        } catch (NullPointerException e) {
            System.out.println(e.getCause());
        }
    }

    @FXML

    /*ELIMINA UN SERVICIO*/
    private void delete_Service(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            ServicesController.getInstance().delete(tableView.getSelectionModel().getSelectedItem().getId());
            tableView.getSelectionModel().clearSelection();
            initTableView();
        } else {
            getAlert(" No items have been selected.");
        }
    }

    /*AGREGA UN SERVICIO*/
    @FXML
    private void add_Service() {
        if (getValidations()) {
            ServicesController.getInstance().add(sName.getText(), sMark.getText(), sModel.getText(), Double.parseDouble(sPrice.getText()), 0.0);
            initTableView();
        }
    }

    @FXML
    private void open_Dialog(ActionEvent event) throws IOException {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            ServicesController.getInstance().change(tableView.getSelectionModel().getSelectedItem());
            JFXDialogLayout content = new JFXDialogLayout();
            JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
            try {
                Parent stack = (Parent) FXMLLoader.load(getClass().getResource("/views/Admin/Service/ViewInsertService.fxml"));;
                content.setBody(stack);

            } catch (Exception e) {
            }

            JFXButton button = new JFXButton("Close");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    dialog.close();
                }
            });;

            content.setActions(button);

            dialog.show();
        } else {
            getAlert(" No items have been selected.");
        }

    }

    /*VALIDA QUE LOS CAMPOS NO SEAN VACIOS*/
    public boolean getValidations() {
        if (!sName.getText().isEmpty() && !sMark.getText().isEmpty()
                && !sModel.getText().isEmpty() && !sPrice.getText().isEmpty()) {

            try {
                Double.parseDouble(sPrice.getText());
                return true;
            } catch (NumberFormatException e) {
                getAlert("You can not enter text in numeric fields");
                return false;
            }

        } else {
            getAlert("You can not leave fields blank.");
            return false;

        }
    }
    /*MUESTRA UNA ALERTA CUANDO UN ELEMNTO NO SE HA SELECCIONADO*/

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
}
