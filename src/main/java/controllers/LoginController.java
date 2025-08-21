package controllers;

import Persistence.ClienteRepository;
import Service.ClienteService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import modelo.Cliente;
import utilities.Paths;

import java.io.IOException;

public class LoginController {
    @FXML
    private PasswordField txtClave;

    @FXML
    private TextField txtUsuario;

    private ClienteService servi = new ClienteService(new ClienteRepository());




    @FXML
    void Ingresar(ActionEvent event) {
        if (servi.validarUsuario(txtUsuario.getText(),txtClave.getText())) {
            try {
                Cliente cliente = servi.buscarByUsuario(txtUsuario.getText(), txtClave.getText());

                // Cargo el nuevo contenido
                FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.DASHBOARD));
                Parent dashboardRoot = loader.load();

                // Paso datos al controlador
                DashBoardController controller = loader.getController();
                controller.setCliente(cliente);

                // Cambio el contenido de la escena actual
                Scene escenaActual = ((Node) event.getSource()).getScene();
                escenaActual.setRoot(dashboardRoot);


            }
            catch (IOException e){
                e.printStackTrace();
                servi.mostrarAlerta("Error", "No se pudo abrir el dashboard", Alert.AlertType.ERROR);
            }


        }
        else {
            servi.mostrarAlerta("Error","Usuario o clave erronea", Alert.AlertType.ERROR);
        }

    }





}



