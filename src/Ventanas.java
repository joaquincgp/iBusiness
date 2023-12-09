import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
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
    private JTextField txtNuevoNombre;
    private JLabel apellidoActual;
    private JTextField textField1;
    private JButton buscarUsuarioButton;
    private JLabel correoActual;
    private JTextField txtNuevoCorreo;
    private JLabel contrasenaActual;
    private JTextField textField2;
    private JTextArea txtDireccionActual;
    private JComboBox comboBox1;
    private JList metodosDePagoList;
    private JButton agregarMetodoDePagoButton;
    private JButton editarMetodoDePagoButton;
    private JTextArea textAreaDireccion;
    private JPasswordField txtPasswordEditar;
    private JPasswordField txtPasswordRegistro;
    private JButton verFormasDePagoButton;
    private Set<Usuario> usuariosRegistrados = new HashSet<>();

    public Ventanas() {
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
                if(usuarioBuscado == null){
                    JOptionPane.showMessageDialog(null, "El usuario no existe o la contraseña es incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Usuario encontrado. Bienvenido "+usuarioBuscado.getNombre());
                    nombreActual.setText(usuarioBuscado.getNombre());
                    apellidoActual.setText(usuarioBuscado.getApellido());
                    correoActual.setText(usuarioBuscado.getCorreo());
                    contrasenaActual.setText(usuarioBuscado.getContrasena());
                    textAreaDireccion.setText(usuarioBuscado.getDireccionEntrega().toString());

                }
            }
        });
        verFormasDePagoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaAutenticacion();
            }
        });
    }

    private void mostrarVentanaAutenticacion() {
        // Crear un panel para solicitar la información de autenticación
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JTextField txtUsuario = new JTextField();
        JPasswordField txtContrasena = new JPasswordField();
        panel.add(new JLabel("Usuario:"));
        panel.add(txtUsuario);
        panel.add(new JLabel("Contraseña:"));
        panel.add(txtContrasena);

        // Mostrar el cuadro de diálogo modal
        int opcion = JOptionPane.showConfirmDialog(this, panel, "Autenticación", JOptionPane.OK_CANCEL_OPTION);

        // Verificar la opción seleccionada y la información de autenticación
        if (opcion == JOptionPane.OK_OPTION) {
            String usuario = txtUsuario.getText();
            String contrasena = new String(txtContrasena.getPassword());
            Usuario user = buscarUsuario(usuariosRegistrados, usuario, contrasena);
            // Realizar la autenticación (verificar usuario y contraseña)
            if (user != null) {
                // Si la autenticación es exitosa, mostrar la ventana de métodos de pago
                mostrarMetodosPago(user);
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void mostrarVentanaAgregarMetodoPago(Usuario user) {
        // Crear un panel para solicitar la información de autenticación
        JPanel panel = new JPanel(new GridLayout(2, 5));
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
        int opcion = JOptionPane.showConfirmDialog(this, panel, "Autenticación", JOptionPane.OK_CANCEL_OPTION);

        // Verificar la opción seleccionada y la información de autenticación
        if (opcion == JOptionPane.OK_OPTION) {
            String titular = txtTitular.getText();
            String numero = txtNumeroTarjeta.getText();

            Usuario user = buscarUsuario(usuariosRegistrados, usuario, contrasena);
            // Realizar la autenticación (verificar usuario y contraseña)
            if (user != null) {
                // Si la autenticación es exitosa, mostrar la ventana de métodos de pago
                mostrarMetodosPago(user);
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
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
        List<MetodoPago> listado = (List<MetodoPago>) user.getBilletera();
        DefaultListModel dlm =new DefaultListModel();
        for(MetodoPago mp:listado){
            dlm.addElement(mp.toString());
        }
        metodosDePagoList.setModel(dlm);
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
