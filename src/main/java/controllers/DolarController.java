package controllers;

import Persistence.ClienteRepository;
import Service.ClienteService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import modelo.Cliente;

import java.awt.event.ActionEvent;
import java.lang.classfile.Label;

public class DolarController {
    private Cliente cliente;
    private Double valorDolar;

    public DolarController() {
    }

    public void setCliente(Cliente cliente, Double valorDolar) {
        this.cliente = cliente;
        this.valorDolar = 1.335;
    }

    ClienteService servi = new ClienteService(new ClienteRepository());

    @FXML
    private TextField tfDolarTotal;

    @FXML
    private TextField txtMontoAEnviar;

    @FXML
    private Label txtValorDolar;

    @FXML
    void ClickCancelar(ActionEvent event) {

    }

    @FXML
    void ClickComprar(ActionEvent event) {
        servi.ComprarDolares(txtMontoAEnviar,tfDolarTotal);

    }
}
