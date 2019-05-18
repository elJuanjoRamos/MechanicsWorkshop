/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Client.CarToService;

import beans.Car;
import beans.Client;
import beans.Service;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import controllers.CarController;
import controllers.ServicesController;
import controllers.WorkOrderController;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import views.Client.ClientView;

public class SendCarToServiceController implements Initializable {

    private static SendCarToServiceController instance;
    @FXML
    TableView<Car> tableView;
    @FXML
    TableColumn<Car, String> tableColumnPlate;
    @FXML
    TableColumn<Car, String> tableColumnBrand;
    @FXML
    TableColumn<Car, String> tableColumnModel;
    @FXML
    TableColumn<Car, String> tableColumnPath;

    @FXML
    TextField ePlate, eBrand, eModel, ePath, filter;
    @FXML
    Button aceptar, editar, eliminar, cancelar, subir;
    @FXML
    Text texto;
    @FXML
    ComboBox comboService;
    @FXML
    ImageView imageView;
    @FXML
    StackPane stackPane;

    /*VARIABLES */
    Image image = new Image("resources/img/car1.png");
    Client client;
    Car start = new Car();
    Car start2 = new Car();

    /**
     * @return SINGLETON
     *
     */
    public static SendCarToServiceController getInstance() {
        if (instance == null) {
            instance = new SendCarToServiceController();
        }
        return instance;
    }

    public SendCarToServiceController() {
        client = ClientView.client;
        start = client.getCarList();
        start2 = start;
        start = null;
        CarController.getInstance().setCarClient(start2);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTableView();
        ePlate.setEditable(false);
        eBrand.setEditable(false);
        eModel.setEditable(false);

        imageView.setImage(image);
        tableColumnPlate.setCellValueFactory(new PropertyValueFactory<>("plate"));
        tableColumnBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        tableColumnModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        tableColumnPath.setCellValueFactory(new PropertyValueFactory<>("path"));
    }

    /**
     * INICIALIZAR DATOS EN TABLA
     */
    public void initTableView() {
        ObservableList<Car> observableList = FXCollections.observableArrayList();
        if (client.getCarList() != null) {

            Car aux = client.getCarList();
            do {
                observableList.add(aux);
                aux = aux.getNext();

            } while (aux != client.getCarList());
        }
        tableView.setItems(observableList);
    }

    @FXML
    public void getCar() {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            /*OBTIENE EL CARRO*/
            Car car = tableView.getSelectionModel().getSelectedItem();
            imageView.setImage(new Image(car.getPath()));
            ePlate.setText(car.getPlate());
            eBrand.setText(car.getBrand());
            eModel.setText(car.getModel());

            /*LLAMA LOS SERVICIOS DISPONIBLES*/
            comboService.setItems(ServicesController.getInstance().getServiceName(eModel.getText(), eBrand.getText()));
        }
    }

    @FXML
    public void sendMyCar() {

        if (tableView.getSelectionModel().getSelectedItem() != null) {
            if (comboService.getSelectionModel().getSelectedItem() != null) {
                //METODO RANDOM
                if(comboService.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("Diagnostic")) {
                    Service service = ServicesController.getInstance().searchForName(comboService.getSelectionModel().getSelectedItem().toString());
                    Service serviceRandom = ServicesController.getInstance().getServiceRandom(eModel.getText(), eBrand.getText());
                    if(serviceRandom!=null) {
                        if(!serviceRandom.getName().equalsIgnoreCase("Diagnostico")) {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Detalles");
                            alert.setHeaderText(service.getName());
                            alert.setContentText("¿Desea que se le realice un servicio de " + serviceRandom.getName() + "?");

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK){
                                WorkOrderController.getInstance().add(tableView.getSelectionModel().getSelectedItem(), client, serviceRandom, null, "UNATTENDED");
                                getAlert("Work order generated successfully.", "Details");
                                cancel();
                            } else {
                                cancel();
                            }
                        } else {
                            getAlert("Error de sistema intente nuevamente.", "Details");
                        }
                    } else {
                        getAlert("No se encontro un servicio adecuado para su vehículo.", "Details.");
                        cancel();
                    }
                } else {
                    Service service = ServicesController.getInstance().searchForName(comboService.getSelectionModel().getSelectedItem().toString());
                    WorkOrderController.getInstance().add(tableView.getSelectionModel().getSelectedItem(), client, service, null, "UNATTENDED");
                    getAlert("Work order generated successfully.", "Details");
                    cancel();
                }
                /*Car car = tableView.getSelectionModel().getSelectedItem();
                Service service = ServicesController.getInstance().searchForName(comboService.getSelectionModel().getSelectedItem().toString());
                WorkOrderController.getInstance().add(car, client, service, null, "UNATTENDED");
                getAlert("Work order generated successfully", "Details");
                cancel();*/
            } else {
                getAlert("Please, select a service for your car.", "Error!");
            }
        } else {
            getAlert("Please, select the car you want to send to service.", "Error!");
        }

    }

    @FXML
    public void cancel() {
        imageView.setImage(image);
        ePlate.setText("");
        eBrand.setText("");
        eModel.setText("");
        tableView.getSelectionModel().clearSelection();

    }

    /*MUESTRA UNA ALERTA CUANDO UN ELEMNTO NO SE HA SELECCIONADO*/
    public void getAlert(String content, String content2) {

        JFXDialogLayout c = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(stackPane, c, JFXDialog.DialogTransition.CENTER);
        c.setHeading(new Text(content2));
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
