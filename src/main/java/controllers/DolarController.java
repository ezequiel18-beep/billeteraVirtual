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

    private Double dolarValor;

    @FXML
    private TextField tfDolarTotal;

    @FXML
    private TextField txtMontoAEnviar;


    @FXML
    private Label txtValorDolar;


    ClienteService servi = new ClienteService(new ClienteRepository());



    public DolarController() {
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        this.dolarValor = 1.335;
    }

    @FXML
    void ClickCancelar(ActionEvent event) {

    }

    @FXML
    void ClickComprar(ActionEvent event) {
        servi.ComprarDolares(txtMontoAEnviar,tfDolarTotal);

    }


    @FXML
    public void initialize(){

    }



}
