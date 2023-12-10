import javax.swing.*;
import java.util.*;

public class Usuario {
    private String nombre;
    private String apellido;
    private String cedula;
    private String correo;
    private String contrasena;
    public Direccion direccionEntrega;
    private LinkedList<Pedido> historialPedidos;
    private Set<MetodoPago> billetera;


    public Usuario(String nombre, String apellido, String cedula, String correo, String contrasena, Direccion direccion) {
        setNombreApellido(nombre, apellido);
        setCedulaEcuatoriana(cedula);
        setCorreo(correo);
        setContrasena(contrasena);
        this.direccionEntrega = direccion;
        this.historialPedidos = new LinkedList<>();
        this.billetera = new HashSet<>();
    }

    //Metodos de validacion para poder asignar las variables en el constructor
    public void setNombreApellido(String nombre, String apellido) {
        if (nombre == null || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre y el apellido no pueden estar vacios", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("El nombre y el apellido no pueden estar vacios");
        }
        if (nombre.split("\\s+").length > 1) {
            JOptionPane.showMessageDialog(null, "El nombre y el apellido no pueden contener espacios adicionales", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("El nombre y el apellido no pueden contener espacios");
        }
        if (apellido == null || apellido.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre y el apellido no pueden estar vacios", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("El nombre y el apellido no pueden estar vacios");
        }
        if (apellido.split("\\s+").length > 1) {
            JOptionPane.showMessageDialog(null, "El nombre y el apellido no pueden contener espacios adicionales", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("El nombre y el apellido no pueden contener espacios adicionales");
        }
        if(apellido.matches(".*\\d.*")|| nombre.matches(".*\\d.*")){
            JOptionPane.showMessageDialog(null, "El nombre y el apellido no pueden contener numeros", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("El nombre y el apellido no pueden contener numeros");
        }
        this.nombre = nombre;
        this.apellido = apellido;
    }



    public void setCorreo(String correo) {
        if (correo == null || correo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El correo no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("El correo no puede estar vacío");
        }

        // Verificar el formato del correo electrónico utilizando una expresión regular simple
        if (!correo.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            JOptionPane.showMessageDialog(null, "Formato de correo electrónico no válido", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Formato de correo electrónico no válido");
        }

        this.correo = correo;
    }



    public void setCedulaEcuatoriana(String cedula) {
        boolean cedulaCorrecta = false;
        try {
            if (cedula.length() == 10) // ConstantesApp.LongitudCedula
            {
                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito < 6) {
                    int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
                    int verificador = Integer.parseInt(cedula.substring(9,10));
                    int suma = 0;
                    int digito = 0;
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1))* coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }
                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        cedulaCorrecta = true;
                    }
                    else if ((10 - (suma % 10)) == verificador) {
                        cedulaCorrecta = true;
                    } else {
                        cedulaCorrecta = false;
                    }
                } else {
                    cedulaCorrecta = false;
                }
            } else {
                cedulaCorrecta = false;
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null,"Formato no valido", "Error", JOptionPane.ERROR_MESSAGE);
            cedulaCorrecta = false;
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null,"Formato no valido", "Error", JOptionPane.ERROR_MESSAGE);
            cedulaCorrecta = false;
        }
        if (!cedulaCorrecta) {
           JOptionPane.showMessageDialog(null,"La cédula ingresada no es ecuatoriana", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Cedula no ecuatoriana");
        }else{
            this.cedula = cedula;
        }
    }


    public void setContrasena(String contrasena) {
        if (contrasena == null || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La contraseña no puede estar vacía", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }

        // Verificar la longitud de la contraseña
        if (contrasena.length() <= 8) {
            JOptionPane.showMessageDialog(null, "La contraseña debe tener más de 8 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("La contraseña debe tener más de 8 caracteres");
        }

        // Verificar si la contraseña contiene al menos un carácter especial y números
        if (!contrasena.matches("^(?=.*[!@#$%^&*(),.?\":{}|<>])(?=.*[0-9]).+$")) {
            JOptionPane.showMessageDialog(null, "La contraseña debe contener al menos un carácter especial y números", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("La contraseña debe contener al menos un carácter especial y números");
        }
        this.contrasena = contrasena;
    }

    public boolean editarPerfil(String nuevoNombre, String nuevoApellido, String nuevoCorreo) {
        setNombreApellido(nuevoNombre, nuevoApellido);
        setCorreo(nuevoCorreo);
        return true;
    }



    public void agregarMetodoPago(MetodoPago nuevoMetodoPago) {
        billetera.add(nuevoMetodoPago);
    }
    public boolean eliminarMetodoDePago(MetodoPago metodoPago) {
        boolean eliminar = false;
        if (billetera.contains(metodoPago)) {
            billetera.remove(metodoPago);
            eliminar = true;
        } else {
            JOptionPane.showMessageDialog(null, "El método de pago no existe en la billetera", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return eliminar;
    }

    public String getNombre() {
        return nombre;
    }


    public String getApellido() {
        return apellido;
    }



    public String getCedula() {
        return cedula;
    }


    public String getCorreo() {
        return correo;
    }



    public String getContrasena() {
        return contrasena;
    }


    public LinkedList<Pedido> getHistorialPedidos() {
        return historialPedidos;
    }

    public void setHistorialPedidos(LinkedList<Pedido> historialPedidos) {
        this.historialPedidos = historialPedidos;
    }

    public Set<MetodoPago> getBilletera() {
        return billetera;
    }

    public void setBilletera(Set<MetodoPago> billetera) {
        this.billetera = billetera;
    }

    public Direccion getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(Direccion direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    @Override
    public String toString() {
        return "USUARIO" + '\n'+
                "Nombre: " + nombre + '\n' +
                "Apellido: " + apellido + '\n' +
                "Cedula: " + cedula + '\n' +
                "Correo: " + correo + '\n';
    }
}
