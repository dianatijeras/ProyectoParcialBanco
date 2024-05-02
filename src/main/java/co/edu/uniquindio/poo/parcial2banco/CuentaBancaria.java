package co.edu.uniquindio.poo.parcial2banco;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Clase abstracta CuentaBancaria modela una cuenta bancaria
 */
public abstract class CuentaBancaria {

    private final int numeroCuenta;
    private double saldo;
    private boolean estado; // Indica si la cuenta esta activa o no
    private Titular titular; // Titular de la cuenta
    private Collection<Transaccion> listaTransacciones; // Lista de transacciones realizadas en la cuenta

    /**
     * Constructor de la clase CuentaBancaria
     * @param numeroCuenta
     * @param titular
     */
    public CuentaBancaria(int numeroCuenta, Titular titular) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = 0;
        this.estado = true; //Por defecto, la cuenta esta activa
        this.titular = titular;
        this.listaTransacciones = new LinkedList<>();
    }

    /**
     *Getters y Setters
     */
    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }

    public Collection<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }

    public void setListaTransacciones(Collection<Transaccion> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
    }

    /**
     * Metodo para depositar dinero en la cuenta bancaria
     * @param monto
     */
    public void depositar (double monto){
        if (monto > 0){
            saldo += monto; //aumenta el saldo de la cuenta bancaria con el monto depositado
            //registra la transaccion en la lista de transacciones
            listaTransacciones.add(new Transaccion("DEPOSITO1", monto, LocalDate.now(),"Deposito", true));
        } else {
            System.out.println("El monto a depositar debe ser mayor que cero");
        }
    }

    /**
     * Metodo abstracto para retirar dinero de la cuenta bancaria
     * @param monto
     */
    public abstract void retirar (double monto);

    /**
     * Metodo para consultar el saldo de la cuenta bancaria
     * @return
     */
    public double consultarSaldo(){
        if (estado){
            return saldo;
        } else {
            return 0.0;
        }
    }

    /**
     * Metodo para tranferir dinero a otra cuenta bancaria
     * @param cuentaDestino
     * @param monto
     * @return
     */
    public boolean transferir (CuentaBancaria cuentaDestino, double monto){
        if (estado && cuentaDestino.estado){
            this.retirar(monto);
            cuentaDestino.depositar(monto);
            return true;
        }
        return false;
    }


}
