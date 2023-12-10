import javax.swing.*;

public class Direccion {
    private String callePrincipal;
    private String calleSecundaria;
    private String ciudad;
    private String provincia;
    private final String pais =  "Ecuador";
    private String codigoPostal;
    private String referencia;

    public Direccion(String callePrincipal, String calleSecundaria, String provincia, String ciudad, String codigoPostal, String referencia) {
        setCalles(callePrincipal,calleSecundaria);
        setCiudad(ciudad);
        this.provincia = provincia;
        setCodigoPostal(codigoPostal);
        setReferencia(referencia);
    }

//Validaciones para inicializar atributos
    private void setCalles(String callePrincipal, String calleSecundaria) {
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

    private void setCiudad(String ciudad) {
        if (ciudad == null || ciudad.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Datos incomppletos", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("La direccion esta incompleta o vacia");
        }
        if(ciudad.matches(".*\\d.*")){
            JOptionPane.showMessageDialog(null, "La ciudad no puede contener numeros", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("La ciudad no puede contener numeros");
        }
        this.ciudad = ciudad;
    }

    private void setCodigoPostal(String codigoPostal) {
        // Eliminar espacios en blanco y validar si es numérico
        String codigoPostalLimpiado = codigoPostal.trim();
        if (codigoPostalLimpiado.length() != 6 || !codigoPostalLimpiado.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "El código postal debe tener exactamente 6 dígitos numéricos", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("El código postal debe tener exactamente 6 dígitos numéricos");
        }
        else {
            this.codigoPostal = codigoPostalLimpiado;
        }
    }

    private void setReferencia(String referencia) {
        if (referencia == null || referencia.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La referencia no puede ser nula o vacía", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("La referencia no puede ser nula o vacía");
        }
        this.referencia = referencia;
    }

    public String getCallePrincipal() {
        return callePrincipal;
    }


    public String getCalleSecundaria() {
        return calleSecundaria;
    }


    public String getCiudad() {
        return ciudad;
    }



    public String getPais() {
        return pais;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }


    public String getReferencia() {
        return referencia;
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
