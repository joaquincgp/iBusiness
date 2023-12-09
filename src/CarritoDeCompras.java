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

    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
    }

    public double calcularTotal() {
        double total = 0.0;
        double subtotal = 0.0;
        double pesototal = 0.0;
        for (int i = 0; i < productos.size() - 1; i++) {
            pesototal += productos.get(i).getPesosKilos();
            subtotal += productos.get(i).calcularSubtotal();
            if (pesototal > 0 && pesototal < 1.362) {
                pesototal = pesototal * 8;
            } else if (pesototal >= 1.362) {
                pesototal = pesototal * 7;
            }
        }
        total = pesototal + subtotal;
        return total;
    }

    public void listarProductos() {
        for (Producto p : productos) {
            System.out.println(productos);
        }
    }

    public String generarTrackingID() {
        UUID idAleatorio = UUID.randomUUID();
        return idAleatorio.toString();
    }

    public List<Producto> getProductos() {
        return this.productos;
    }

}






