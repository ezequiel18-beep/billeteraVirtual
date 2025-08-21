package modelo;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaccion implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tipo; // Ej: "Depósito", "Retiro"
    private double monto;
    private LocalDateTime fecha;


    public Transaccion(String tipo, double monto, LocalDateTime fecha) {
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return
                "Tipo: " + tipo  +
                " Monto: " + "$"+monto +
                " Fecha: " + fecha.format(formatter)
                ;
    }
}

