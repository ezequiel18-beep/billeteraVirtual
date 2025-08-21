package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    private String usuario;
    private Double saldo;
    private String clave;
    private String dni;
    private List<Transaccion> transacciones = new ArrayList<>();

    public Cliente() {

    }

    public Cliente(String nombre, Double saldo, String clave, String dni) {
        this.usuario = nombre;
        this.saldo = saldo;
        this.clave = clave;
        this.dni = dni;
        this.transacciones = new ArrayList<>();
    }

    public void setNombre(String nombre) {
        this.usuario = nombre;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void setDni(String dni)
    {
        this.dni = dni;
    }

    public String getUsuario() {
        return usuario;
    }
    public String getDni() {
        return dni;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public List<Transaccion> getTransacciones() {

        return transacciones;
    }

    public void agregarTransaccion(Transaccion t) {
        if(transacciones.isEmpty()){
            transacciones = new ArrayList<>();
        }
        transacciones.add(t);
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }
}




