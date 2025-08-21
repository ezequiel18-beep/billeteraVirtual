package Persistence;

import modelo.Cliente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {

    private static final String FILE = "billetera.ser";

    // Guarda toda la lista de clientes en el archivo
    public void guardarClientes(List<Cliente> clientes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(clientes);
            System.out.println("Clientes guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error guardando clientes: " + e.getMessage());
        }
    }

    // Carga la lista de clientes desde el archivo
    @SuppressWarnings("unchecked")
    public List<Cliente> cargarClientes() {
        File archivo = new File(FILE);
        if (!archivo.exists()) {
            return new ArrayList<>(); // Lista vacía si no hay archivo
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Cliente>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error cargando clientes: " + e.getMessage());
            return new ArrayList<>();

        }
    }


}

