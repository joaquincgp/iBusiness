import javax.swing.*;
import java.time.LocalDate;

public class MetodoPago {
    private enum Tipo{
        CREDITO,
        DEBITO
    }
    private String titular;
    private String numeroTarjeta;
    private LocalDate fechaVencimiento;
    private int codigoSeguridad;
    private Tipo tipoDeTarjeta;

    public MetodoPago(String titular, String numeroTarjeta, LocalDate fechaVencimiento, int codigoSeguridad, String tipoString) {
        if (titular != null && esTitularValido(titular)) {
            this.titular = titular;
        }else{
            JOptionPane.showMessageDialog(null, "El titular debe tener nombre y apellido", "Error", JOptionPane.ERROR_MESSAGE);

        }
        if(esNumeroTarjetaValido(numeroTarjeta) && !numeroTarjeta.isEmpty()){
            this.numeroTarjeta = numeroTarjeta;
        }else{
            JOptionPane.showMessageDialog(null, "El numero de tarjeta no es valido", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if(esFechaVencimientoValida(fechaVencimiento)){
            this.fechaVencimiento = fechaVencimiento;
        }else{
            JOptionPane.showMessageDialog(null, "La fecha de vencimiento debe ser mayor a la actual", "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("La fecha no es valida");
        }
        if (esCodigoSeguridadValido(codigoSeguridad) && codigoSeguridad!=0) {
            this.codigoSeguridad = codigoSeguridad;
        } else {
            JOptionPane.showMessageDialog(null, "El código de seguridad debe tener 3 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        Tipo tipoEnum = null;
        try{
            tipoEnum = Tipo.valueOf(tipoString);
        }catch (IllegalArgumentException e ){
            JOptionPane.showMessageDialog(null, "Solo puede ser de credito o debito", "Error", JOptionPane.ERROR_MESSAGE);

        }
        this.tipoDeTarjeta = tipoEnum;
    }

    private boolean esCodigoSeguridadValido(int codigoSeguridad) {
        String codigoSeguridadStr = String.valueOf(codigoSeguridad);
        return codigoSeguridadStr.length() == 3 && codigoSeguridadStr.matches("\\d{3}");
    }

    private boolean esNumeroTarjetaValido(String numeroTarjeta) {
        String numeroTarjetaSinEspacios = numeroTarjeta.replaceAll("\\s|-", "");
        return tieneCantidadDigitosValida(numeroTarjetaSinEspacios, 16, 16);
    }

    private boolean tieneCantidadDigitosValida(String numeroTarjeta, int minDigitos, int maxDigitos) {
        int longitud = numeroTarjeta.length();
        return longitud >= minDigitos && longitud <= maxDigitos;
    }

    public String getNumeroTarjetaEnmascarado() {
        return "****-****-****-" + numeroTarjeta.substring(numeroTarjeta.length() - 4);
    }

    public boolean esFechaVencimientoValida(LocalDate fechaVencimiento) {
        LocalDate fechaActual = LocalDate.now();
        return fechaVencimiento.isAfter(fechaActual);
    }

    private boolean esTitularValido(String titular){
        // Verifica si el titular contiene un nombre y un apellido
        String[] partes = titular.split("\\s+");
        return partes.length == 2 && partes[0].matches("[A-Za-z]+") && partes[1].matches("[A-Za-z]+");
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(int codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public Tipo getTipoDeTarjeta() {
        return tipoDeTarjeta;
    }

    public void setTipoDeTarjeta(Tipo tipoDeTarjeta) {
        this.tipoDeTarjeta = tipoDeTarjeta;
    }

    @Override
    public String toString() {
        return "Titular: " + titular + '\n' +
                " Tipo de tarjeta: " + tipoDeTarjeta + '\n' +
                " Numero: " + getNumeroTarjetaEnmascarado() + '\n' +
                " Fecha de vencimiento: " + fechaVencimiento + '\n';
    }
}
