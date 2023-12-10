import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class Direccion {
    private String callePrincipal;
    private String calleSecundaria;
    private String ciudad;
    private String provincia;
    private final String pais =  "Ecuador";
    private String codigoPostal;
    private String referencia;

    public Direccion(String callePrincipal, String calleSecundaria, String provincia, String ciudad, String codigoPostal, String referencia) {
        validarCalles(callePrincipal,calleSecundaria);
        validarCiudad(ciudad);
        this.provincia = provincia;
        validarCodigoPostal(codigoPostal);
        validarReferencia(referencia);
    }

//Validaciones para inicializar atributos
    private void validarCalles(String callePrincipal, String calleSecundaria) {
        if (callePrincipal == null || callePrincipal.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Datos incompletos", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("La calle principal no puede ser nula o vacía");
        }
        if (calleSecundaria == null || calleSecundaria.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Datos incompletos", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("La calle secundaria no puede ser nula o vacía");
        }
        this.callePrincipal = callePrincipal;
        this.calleSecundaria = calleSecundaria;
    }

    private void validarCiudad(String ciudad) {
        if (ciudad == null || ciudad.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Datos incomppletos", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("La direccion esta incompleta o vacia");
        }
        this.ciudad = ciudad;
    }

    private void validarCodigoPostal(String codigoPostal) {
        // Eliminar espacios en blanco y validar si es numérico
        String codigoPostalLimpiado = codigoPostal.trim();

        if (codigoPostalLimpiado.length() != 6 || !codigoPostalLimpiado.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "El código postal debe tener exactamente 6 dígitos numéricos", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("El código postal debe tener exactamente 6 dígitos numéricos");
        } else {
            this.codigoPostal = codigoPostalLimpiado;
        }
    }

    private void validarReferencia(String referencia) {
        if (referencia == null || referencia.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La referencia no puede ser nula o vacía", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("La referencia no puede ser nula o vacía");
        }
        this.referencia = referencia;
    }

    public String getCallePrincipal() {
        return callePrincipal;
    }

    public void setCallePrincipal(String callePrincipal) {
        this.callePrincipal = callePrincipal;
    }

    public String getCalleSecundaria() {
        return calleSecundaria;
    }

    public void setCalleSecundaria(String calleSecundaria) {
        this.calleSecundaria = calleSecundaria;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getProvincia() {
        return provincia;
    }

    @Override
    public String toString() {
        return "Direccion" + '\n'+
                "Calle Principal: " + callePrincipal + '\n' +
                "Calle Secundaria: " + calleSecundaria + '\n' +
                "Ciudad: " + ciudad + '\n' +
                "Provincia: " + provincia + '\n' +
                "Pais: " + pais + '\n' +
                "Codigo Postal: " + codigoPostal + '\n' +
                "Referencia: " + referencia + '\n';
    }
}
