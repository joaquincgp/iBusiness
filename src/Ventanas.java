import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class Ventanas extends Component {
    private JTabbedPane tabbedPane1;
    private JPanel panelRegistro;
    private JPanel panelPedidos;
    private JPanel main;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCedula;
    private JTextField txtCorreo;
    private JTextField txtContrasena;
    private JButton registrarButton;
    private JTextField txtCalle1;
    private JTextField txtCalle2;
    private JTextField txtZip;
    private JTextField txtCiudad;
    private JTextField txtRef;
    private JComboBox cboProvincia;
    private JTextField txtUsuarioEditar;
    private JTextField txtContrasenaEditar;
    private JButton guardarCambiosButton;
    private JLabel nombreActual;
    private JLabel apellidoActual;
    private JTextField textField1;
    private JButton buscarUsuarioButton;
    private JLabel correoActual;
    private JLabel contrasenaActual;
    private JTextField textField2;
    private JTextArea txtDireccionActual;
    private JList metodosDePagoList;
    private JButton agregarMetodoDePagoButton;
    private JButton editarMetodoDePagoButton;
    private JTextArea textAreaDireccion;
    private JPasswordField txtPasswordEditar;
    private JPasswordField txtPasswordRegistro;
    private JButton verFormasDePagoButton;
    private JTextArea metodosPagoTextArea;
    private JPanel panelPerfil;
    private JLabel callePActual;
    private JTextArea textAreaDirActual;
    private JButton editarDireccionButton;
    private JTextField txtNuevoNombre;
    private JTextField txtNuevoCorreo;
    private Set<Usuario> usuariosRegistrados = new HashSet<>();
    private Usuario userAux = null;

    //Datos quemados para pruebas
    Direccion aux = new Direccion("hdahda", "ahdhada", "Tungurahua","ajsoja", "190101", "dadad" );

    public Ventanas() {
        //Dato quemado
        usuariosRegistrados.add(new Usuario("John", "Sanchez", "1805226048","j@gmail.com","chacon#123", aux));
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = null;
                String nombre = null;
                String apellido = null;
                String correo = null;
                String contrasena = null;
                try {
                    nombre = txtNombre.getText();
                    apellido = txtApellido.getText();
                    cedula = txtCedula.getText();
                    correo = txtCorreo.getText();
                    contrasena = txtPasswordRegistro.getText();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Algun campo esta vacio");
                }
                String callePrincipal = null;
                String calleSecundaria = null;
                String provincia = null;
                String ciudad = null;
                String zip = null;
                String referencia = null;

                try {
                    callePrincipal = txtCalle1.getText();
                    calleSecundaria = txtCalle2.getText();
                    provincia = (String) cboProvincia.getSelectedItem();
                    ciudad = txtCiudad.getText();
                    zip = txtZip.getText();
                    referencia = txtRef.getText();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Algun campo esta vacio");
                }
                Direccion direccion = new Direccion(callePrincipal, calleSecundaria,provincia, ciudad, zip, referencia);
                Usuario nuevoUsuario = new Usuario(nombre, apellido, cedula, correo, contrasena, direccion);

                if (usuariosRegistrados.add(nuevoUsuario)) {
                    limpiarCampos();
                    JOptionPane.showMessageDialog(null, "El usuario se registró correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "El usuario ya está registrado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        guardarCambiosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        buscarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario =null;
                String contrasena = null;
                try{
                    usuario = txtUsuarioEditar.getText();
                    contrasena = txtPasswordEditar.getText();
                    if(usuario == null || usuario.isEmpty() || contrasena == null || contrasena.isEmpty()){
                        throw new Exception("Campos vacios");
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Algun campo esta vacio", "Error", JOptionPane.ERROR_MESSAGE);
                }

                Usuario usuarioBuscado = buscarUsuario(usuariosRegistrados, usuario, contrasena);
                userAux = usuarioBuscado;
                if(usuarioBuscado == null){
                    JOptionPane.showMessageDialog(null, "El usuario no existe o la contraseña es incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Usuario encontrado. Bienvenido "+usuarioBuscado.getNombre());
                    nombreActual.setText(usuarioBuscado.getNombre());
                    apellidoActual.setText(usuarioBuscado.getApellido());
                    correoActual.setText(usuarioBuscado.getCorreo());
                    contrasenaActual.setText("********");
                    textAreaDirActual.setText(usuarioBuscado.getDireccionEntrega().toString());


                }
            }
        });
        verFormasDePagoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(userAux==null){
                    JOptionPane.showMessageDialog(null, "No se ha iniciado sesion", "Error", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException("No se inicia sesion");
                }
                if (!userAux.getBilletera().isEmpty()) {
                    // Si la autenticación es exitosa, mostrar la ventana de métodos de pago
                    mostrarMetodosPago(userAux);
                }else{
                    JOptionPane.showMessageDialog(null, "No hay metodos de pagos asociados a este usuario", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        agregarMetodoDePagoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(userAux==null){
                    JOptionPane.showMessageDialog(null, "No se ha iniciado sesion", "Error", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException("No se inicia sesion");
                }
                mostrarVentanaAgregarMetodoPago();
            }
        });

        editarDireccionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(userAux==null){
                    JOptionPane.showMessageDialog(null, "No se ha iniciado sesion", "Error", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException("No se inicia sesion");
                }
                mostrarVentanaEditarDireccion();
            }
        });
    }

    private void mostrarVentanaEditarDireccion() {
        // Crear un panel para solicitar la información de autenticación
        JPanel panel = new JPanel(new GridLayout(6, 2));
        JTextField txtCallePrincipal = new JTextField();
        JTextField txtCalleSecundaria = new JTextField();
        JTextField txtCiudad = new JTextField();
        JTextField txtCodigoPostal = new JTextField();
        JTextField txtReferencia = new JTextField();
        JTextField txtProvincia = new JTextField();


        panel.add(new JLabel("Calle principal:"));
        panel.add(txtCallePrincipal);
        txtCallePrincipal.setText(userAux.getDireccionEntrega().getCallePrincipal());
        panel.add(new JLabel("Calle secundaria:"));
        panel.add(txtCalleSecundaria);
        txtCalleSecundaria.setText(userAux.getDireccionEntrega().getCalleSecundaria());
        panel.add(new JLabel("Ciudad:"));
        panel.add(txtCiudad);
        txtCiudad.setText(userAux.getDireccionEntrega().getCiudad());
        panel.add(new JLabel("Provincia"));
        panel.add(txtProvincia);
        txtProvincia.setText(userAux.getDireccionEntrega().getProvincia());
        panel.add(new JLabel("Codigo postal:"));
        panel.add(txtCodigoPostal);
        txtCodigoPostal.setText(userAux.getDireccionEntrega().getCodigoPostal());
        panel.add(new JLabel("Referencia:"));
        panel.add(txtReferencia);
        txtReferencia.setText(userAux.getDireccionEntrega().getReferencia());

        // Mostrar el cuadro de diálogo modal
        int opcion = JOptionPane.showConfirmDialog(this, panel, "Nueva direccion", JOptionPane.OK_CANCEL_OPTION);

        // Verificar la opción seleccionada y la información de autenticación
        if (opcion == JOptionPane.OK_OPTION) {
            String callePrincipal = null;
            String calleSecundaria = null;
            String ciudad = null;
            String provincia = null;
            String zip = null;
            String referencia = null;
            try{
                callePrincipal = txtCallePrincipal.getText();
                calleSecundaria = txtCalleSecundaria.getText();
                ciudad = txtCiudad.getText();
                provincia = (String) cboProvincia.getSelectedItem();
                zip = txtCodigoPostal.getText();
                referencia = txtReferencia.getText();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Algun dato no tiene el formato apropiado", "Error", JOptionPane.ERROR_MESSAGE);
            }

            Direccion nuevaDireccion = new Direccion(callePrincipal, calleSecundaria, provincia, ciudad, zip, referencia);


            try{
                userAux.setDireccionEntrega(nuevaDireccion);  //SE AGREGA EL METODO DE PAGO
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Direccion no actualizada. Algun campo es incorrecto o esta vacio");
            }
            JOptionPane.showMessageDialog(null, "Direccion actualizada");
            textAreaDirActual.setText(userAux.getDireccionEntrega().toString());
        }
    }

    private void mostrarVentanaAgregarMetodoPago() {
        // Crear un panel para solicitar la información de autenticación
        JPanel panel = new JPanel(new GridLayout(5, 2));
        JTextField txtTitular = new JTextField();
        JTextField txtNumeroTarjeta = new JTextField();
        JTextField txtFechaVencimiento = new JTextField();
        JTextField codigoSeguridad = new JTextField();
        String[] opciones = {"CREDITO", "DEBITO"};
        JComboBox<String> cboOpciones = new JComboBox<>(opciones);

        panel.add(new JLabel("Titular:"));
        panel.add(txtTitular);
        panel.add(new JLabel("Numero de tarjeta:"));
        panel.add(txtNumeroTarjeta);
        panel.add(new JLabel("Fecha de vencimiento:"));
        panel.add(txtFechaVencimiento);
        panel.add(new JLabel("CVV:"));
        panel.add(codigoSeguridad);
        panel.add(new JLabel("Tipo:"));
        panel.add(cboOpciones);

        // Mostrar el cuadro de diálogo modal
        int opcion = JOptionPane.showConfirmDialog(this, panel, "Nuevo metodo de pago", JOptionPane.OK_CANCEL_OPTION);

        // Verificar la opción seleccionada y la información de autenticación
        if (opcion == JOptionPane.OK_OPTION) {
            String titular = null;
            String numero = null;
            String fechaString = null;
            LocalDate fecha = null;
            int codigo = 0;
            String tipo = null;
            try{
                titular = txtTitular.getText();
                numero = txtNumeroTarjeta.getText();
                fechaString = txtFechaVencimiento.getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                // Convertir el String a LocalDate
                fecha = LocalDate.parse(fechaString, formatter);
                codigo = Integer.parseInt(codigoSeguridad.getText());
                tipo = (String) cboOpciones.getSelectedItem();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Algun dato no tiene el formato apropiado", "Error", JOptionPane.ERROR_MESSAGE);
            }

            MetodoPago nuevoMetodoPago = new MetodoPago(titular, numero, fecha, codigo, tipo);

            try{
                userAux.agregarMetodoPago(nuevoMetodoPago);  //SE AGREGA EL METODO DE PAGO
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Metodo de pago no pudo agregarse");
            }
            JOptionPane.showMessageDialog(null, "Metodo de pago agregado");



            if (userAux != null) {
                mostrarMetodosPago(userAux);
            }
        }
    }

    public static Usuario buscarUsuario(Set<Usuario> usuariosRegistrados, String correo, String contrasena) {
        for (Usuario usuario : usuariosRegistrados) {
            if (usuario.getCorreo().equals(correo) && usuario.getContrasena().equals(contrasena)) {
                return usuario;
            }
        }
        return null; // Usuario no encontrado
    }


    private void mostrarMetodosPago(Usuario user){
        metodosPagoTextArea.setText("");
        Set<MetodoPago> listado = null;
        try{
            listado = user.getBilletera();
        }catch (NullPointerException ex ){
            JOptionPane.showMessageDialog(null, "El usuario no tiene metodos de pago", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int cont = 1;
        for (MetodoPago mp : listado) {
            metodosPagoTextArea.append("TARJETA "+cont+'\n'+mp.toString());
            cont = cont +1;
        }
    }

    public void limpiarCampos(){
        txtNombre.setText("");
        txtApellido.setText("");
        txtCedula.setText("");
        txtCorreo.setText("");
        txtPasswordRegistro.setText("");
        txtCalle1.setText("");
        txtCalle2.setText("");
        txtCiudad.setText("");
        txtZip.setText("");
        txtRef.setText("");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventanas");
        frame.setContentPane(new Ventanas().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }




}
