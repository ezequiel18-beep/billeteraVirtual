package application;

import Persistence.ClienteRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.Cliente;
import modelo.Transaccion;
import utilities.Paths;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {
    private Cliente cliente;
    public static void main(String[] args) {
        launch();
        }


    public void start(Stage primarystage) throws Exception {

        ClienteRepository repo = new ClienteRepository();

        // Cargar clientes existentes
        List<Cliente> clientes = repo.cargarClientes();
        List<Transaccion> transacciones = new ArrayList<>();



        if (clientes.isEmpty()){
            transacciones.add(new Transaccion("Transferencia", 1500.00, LocalDateTime.now().minusDays(2)));
            transacciones.add(new Transaccion("Retiro", 500.00, LocalDateTime.now().minusDays(1)));
            transacciones.add(new Transaccion("Transferencia", 3000.50, LocalDateTime.now().minusHours(5)));
            transacciones.add(new Transaccion("Retiro", 200.00, LocalDateTime.now().minusMinutes(30)));
            transacciones.add(new Transaccion("Transferencia", 1000.00, LocalDateTime.now()));

            Cliente c1 = new Cliente("ezequiel22g",500.000, "2178", "12343");
            Cliente c2= new Cliente("brisaferreyra18",800.000,"3232","21324");
            Cliente c3 = new Cliente("juanperez10", 1200.50, "1111", "54321");
            Cliente c4 = new Cliente("maria.lopez", 950.75, "2222", "67890");
            Cliente c5 = new Cliente("carlos_dev", 300.00, "3333", "98765");
            Cliente c6 = new Cliente("sofia23", 1500.00, "4444", "13579");
            Cliente c7 = new Cliente("martin_ok", 50.00, "5555", "24680");
            Cliente c8 = new Cliente("luciagonzalez", 2000.00, "6666", "11223");
            Cliente c9 = new Cliente("pabloCoder", 725.25, "7777", "44556");
            Cliente c10 = new Cliente("anabel_92", 340.10, "8888", "77889");

            c1.setTransacciones(transacciones);

            clientes.add(c1);
            clientes.add(c2);
            clientes.add(c3);
            clientes.add(c4);
            clientes.add(c5);
            clientes.add(c6);
            clientes.add(c7);
            clientes.add(c8);
            clientes.add(c9);
            clientes.add(c10);



            // Guardar la lista actualizada
            repo.guardarClientes(clientes);

        }



        AnchorPane load = FXMLLoader.load(getClass().getResource(Paths.LOGIN));
        Scene scene = new Scene(load);
        primarystage.setScene(scene);
        primarystage.initStyle(StageStyle.UTILITY);
        primarystage.setOnCloseRequest(e -> repo.guardarClientes(clientes));
        primarystage.show();



    }

}
