package Service;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import modelo.Cliente;
import Persistence.ClienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ClienteService {

    private ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente buscarByUsuario(String usuario, String clave) {
        List<Cliente> clientes = repository.cargarClientes();
        for (Cliente c : clientes) {
            if (c.getUsuario().equals(usuario) && c.getClave().equals(clave)) {
                return c;
            }
        }
        return null;
    }

    public Cliente traerContacto(String usuario) {
        List<Cliente> clientes = repository.cargarClientes();
        for (Cliente c : clientes) {
            if (c.getUsuario().equals(usuario)) {
                return c;
            }
        }
        return null;
    }


    public List<Cliente> traerListaClientes() {
        List<Cliente> clientes = new ArrayList<>();
        return clientes = repository.cargarClientes();
    }

    public Object agregarContacto(String usuario) {
        List<Cliente> clientes = repository.cargarClientes();
        for (Cliente c : clientes) {
            if (c.getUsuario().equals(usuario)) {
                return null;

            } else {
                Cliente nuevoCliente = new Cliente();
                nuevoCliente.setNombre(usuario);
                int claveRandom = ThreadLocalRandom.current().nextInt(1000, 10000);
                nuevoCliente.setClave(String.valueOf(claveRandom));
                nuevoCliente.setSaldo(100.000);
                clientes.add(nuevoCliente);
                repository.guardarClientes(clientes);


            }
        }
        return true;
    }

    public boolean validarUsuario(String usuario, String clave){
        return buscarByUsuario(usuario,clave)!= null;
    }

    public void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null); // Sin texto de cabecera
        alert.setContentText(mensaje);
        alert.showAndWait(); // Espera a que el usuario cierre la ventana
    }


    public boolean realizarTransferencia(Cliente duenio, Cliente contacto, Double monto) {

        if (monto> duenio.getSaldo()) {
            return false;
        }
        duenio.setSaldo(duenio.getSaldo() - monto);
        contacto.setSaldo(contacto.getSaldo()+monto);
        return true;
    }


    public double parsearString(TextField txtMontoAEnviar) {
        String monto = "";
        String texto = txtMontoAEnviar.getText();
        for (int i=0; i < texto.length(); i++){
            char c = texto.charAt(i);
            if(c !='$' ){
                monto += c;

            }
        }
       double montoReal = Double.parseDouble(monto);
        return montoReal;
    }

    public void eliminarUsuario(String usuario) {
        repository.eliminarUsuario(usuario);
    }

    public void ComprarDolares(TextField txtMontoAEnviar, TextField tfDolarTotal) {
         Double montoAComprar = Double.valueOf(txtMontoAEnviar.getText());
         Double montoEnDolar = Double.valueOf(tfDolarTotal.getText());
        if (montoAComprar>0){

        }
    }
}
