import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarritoDeCompras {

    private ArrayList<Producto> productos;

    public CarritoDeCompras() {
        this.productos = new ArrayList<>();
    }


    public void agregarProducto(Producto producto) {
        if (producto.getCantidad() > 3) {
            JOptionPane.showMessageDialog(null, "No se puede comprar mas de tres productos del mismo tipo de acuerdo con la normativa aduanera.");
        } else {
            productos.add(producto);
        }
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






