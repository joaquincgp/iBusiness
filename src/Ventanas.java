import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class Ventanas {
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
                    contrasena = txtContrasena.getText();
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
                    contrasena = txtContrasenaEditar.getText();
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
                    txtDireccionActual.setText(usuarioBuscado.getDireccionEntrega().toString());

                }
            }
        });
    }

    public static Usuario buscarUsuario(Set<Usuario> usuariosRegistrados, String correo, String contrasena) {
        for (Usuario usuario : usuariosRegistrados) {
            if (usuario.getCorreo().equals(correo) && usuario.getContrasena().equals(contrasena)) {
                return usuario;
            }
        }
        return null; // Usuario no encontrado
    }


    public void llenarInformacion(){

    }

    public void limpiarCampos(){
        txtNombre.setText("");
        txtApellido.setText("");
        txtCedula.setText("");
        txtCorreo.setText("");
        txtContrasena.setText("");
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
