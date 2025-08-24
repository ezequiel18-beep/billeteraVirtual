package controllers;

import Persistence.ClienteRepository;
import Service.ClienteService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import modelo.Cliente;
import javafx.scene.control.Label;



public class DolarController {
    private Cliente cliente;

    private Double dolarValor;

    @FXML
    private TextField tfDolarTotal;

    @FXML
    private TextField txtMontoAGastar;


    @FXML
    private Label txtValorDolar;


    ClienteService servi = new ClienteService(new ClienteRepository());



    public DolarController() {
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        this.dolarValor = 1.335;
        mostrarValorDolar(dolarValor);
    }


    public void mostrarValorDolar(Double dolarValor){
        txtValorDolar.setText(String.valueOf(dolarValor));
    }



    public void ClickComprar(javafx.event.ActionEvent event) {
        servi.ComprarDolares(txtMontoAGastar,tfDolarTotal,cliente);

    }

    public void ClickCancelar(javafx.event.ActionEvent event) {
    }

    @FXML
    public void initialize() {
        txtMontoAGastar.textProperty().addListener((observable, oldValue, newValue) -> {
            actualizarDolarTotal(newValue);
        });
    }

    private void actualizarDolarTotal(String texto) {
        if (texto == null || texto.isEmpty()) {
            tfDolarTotal.setText("");
            return;
        }
        try {
            double monto = Double.parseDouble(texto);
            double total = monto / dolarValor;
            tfDolarTotal.setText(String.format("%.2f", total));
        } catch (NumberFormatException e) {

            tfDolarTotal.setText("0");
        }
    }
}
