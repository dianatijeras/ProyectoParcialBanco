package co.edu.uniquindio.poo.parcial2banco;

import java.time.LocalDate;

/**
 * Clase CuentaCorriente modela una cuenta corriente
 */
public class CuentaCorriente extends CuentaBancaria{

    private double sobregiro;

    /**
     * Constructor de la clase CuentaCorriente
     * @param numeroCuenta
     * @param titular
     * @param sobregiro
     */
    public CuentaCorriente(int numeroCuenta, Titular titular, double sobregiro) {
        super(numeroCuenta, titular);
        this.sobregiro = sobregiro;
    }

    /**
     *Getters y Setters
     */
    public double getSobregiro() {
        return sobregiro;
    }

    public void setSobregiro(double sobregiro) {
        this.sobregiro = sobregiro;
    }

    /**
     * Metodo para retirar dinero de la cuenta corriente
     * @param monto
     */
    @Override
    public void retirar (double monto){
        if (monto > 0) {
            double saldoActual = getSaldo() + sobregiro; // Calcula el saldo actual considerando el limite de sobregiro
            if (monto <= saldoActual) {
                // Realiza el retiro
                setSaldo(getSaldo() - monto);
                // Si el saldo después del retiro es cero, establecer el estado de la cuenta como inactiva
                if (getSaldo() == 0) {
                    setEstado(false);
                }
                // Registrar la transacción
                getListaTransacciones().add(new Transaccion("RETIRO2", monto, LocalDate.now(), "Retiro", true));
            } else {
                System.out.println("El monto solicitado supera el límite de sobregiro");
            }
        } else {
            System.out.println("El monto a retirar debe ser mayor que cero");
        }
    }
}
