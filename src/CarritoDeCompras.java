import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarritoDeCompras {

    double pesosB = 0.0;
    double pesosC = 0.0;
    double pesosD = 0.0;

    double costosB = 0.0;
    double costosC = 0.0;
    double costosD = 0.0;


    private ArrayList<Producto> productos;


    public CarritoDeCompras() {
        this.productos = new ArrayList<>();
    }


    public void agregarProducto(Producto producto) {
        double pesoMaximo = 0.0;
        if (producto.getCantidad() > 3) {
            JOptionPane.showMessageDialog(null, "No se puede comprar mas de tres productos del mismo tipo de acuerdo con la normativa aduanera.");
            throw new RuntimeException("Mas de 3 productos");
        }

        switch (producto.getCategoriaAduana()){
            case B:
            if(!(pesosB<=4) && !(costosB<=400)){
                JOptionPane.showMessageDialog(null, "Se excede alguno de los limites del paquete de categoria B", "Advertencia", JOptionPane.WARNING_MESSAGE);
                throw new RuntimeException("Ya no se permiten mas productos en el paquete de categoria B");
            }
            double disponiblePesoB = 4 - pesosB;
            double disponibleCostoB = 400 - costosB;
            if(pesosB>disponiblePesoB){
                JOptionPane.showMessageDialog(null, "Se excede alguno de los limites del paquete de categoria B", "Advertencia", JOptionPane.WARNING_MESSAGE);
                throw new RuntimeException("Ya no se permiten mas productos en el paquete de categoria B");
            }
            if(costosB>disponibleCostoB){
                JOptionPane.showMessageDialog(null, "Se excede alguno de los limites del paquete de categoria B", "Advertencia", JOptionPane.WARNING_MESSAGE);
                throw new RuntimeException("Ya no se permiten mas productos en el paquete de categoria B");
            }
            pesosB = pesosB + producto.getPesosKilos();
            costosB = costosB + producto.getPrecio();
            break;

            case C:
                if(!(pesosC<=100) && !(costosC<=5000)){
                    JOptionPane.showMessageDialog(null, "Se excede alguno de los limites del paquete de categoria C", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    throw new RuntimeException("Ya no se permiten mas productos en el paquete de categoria C");
                }
                double disponiblePesoC = 100 - pesosC;
                double disponibleCostoC = 5000 - costosC;
                if(pesosC>disponiblePesoC){
                    JOptionPane.showMessageDialog(null, "Se excede alguno de los limites del paquete de categoria C", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    throw new RuntimeException("Ya no se permiten mas productos en el paquete de categoria C");
                }
                if(costosC>disponibleCostoC){
                    JOptionPane.showMessageDialog(null, "Se excede alguno de los limites del paquete de categoria C", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    throw new RuntimeException("Ya no se permiten mas productos en el paquete de categoria C");
                }
                pesosC = pesosC + producto.getPesosKilos();
                costosC = costosC + producto.getPrecio();
                break;
            case D:
                if(!(pesosD<=20) && !(costosD<=2000)){
                    JOptionPane.showMessageDialog(null, "Se excede alguno de los limites del paquete de categoria D", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    throw new RuntimeException("Ya no se permiten mas productos en el paquete de categoria D");
                }
                double disponiblePesoD = 20 - pesosC;
                double disponibleCostoD = 2000 - costosC;
                if(pesosD>disponiblePesoD){
                    JOptionPane.showMessageDialog(null, "Se excede alguno de los limites del paquete de categoria D", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    throw new RuntimeException("Ya no se permiten mas productos en el paquete de categoria D");
                }
                if(costosD>disponibleCostoD){
                    JOptionPane.showMessageDialog(null, "Se excede alguno de los limites del paquete de categoria D", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    throw new RuntimeException("Ya no se permiten mas productos en el paquete de categoria D");
                }
                pesosD = pesosD + producto.getPesosKilos();
                costosD = costosD + producto.getPrecio();
                break;
        }
            productos.add(producto);

    }

    public void eliminarProducto(Producto producto){
        productos.remove(producto);
    }
    public boolean contieneProducto(Producto producto) {
        boolean encontro = false;
        for(Producto p: productos){
            if(compararNombresSinEspacios(p.getNombre(), producto.getNombre())){
                encontro = true;
            }
        }
        return encontro;
    }

    private boolean compararNombresSinEspacios(String nombre1, String nombre2) {
        // Eliminar espacios en blanco y comparar los nombres sin importar mayúsculas y minúsculas
        return nombre1.trim().equalsIgnoreCase(nombre2.trim());
    }




    public double calcularTotal() {
        double total = 0.0;
        double subtotal = 0.0;
        double pesototal = 0.0;
        for (Producto p: productos) {
            pesototal += p.getPesosKilos();
            subtotal += p.calcularSubtotal();
            if (pesototal > 0 && pesototal < 1.362) {
                pesototal = pesototal * 8;
            } else if (pesototal >= 1.362) {
                pesototal = pesototal * 7;
            }
        }
        total = pesototal + subtotal;
        return total;
    }




    public List<Producto> getProductos() {
        return this.productos;
    }

}






