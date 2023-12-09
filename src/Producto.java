import javax.swing.*;

public class Producto {
    private enum Categoria {
        B,
        C,
        D,
    }

    private enum TiposProductos {
        Electronica,
        Hogar,
        Vestimenta,
        Belleza,
        Deportes,
        Juguetes,
        Automotriz,
        Oficina,
        Mascotas,
        Alimentacion,
        Jardin
    }

    private String nombre;
    private String descripcion;
    private double pesosKilos;
    private double precio;
    private int cantidad;
    private Categoria categoriaAduana;
    private TiposProductos tipo;


    public Producto(String nombre, String descripcion, double pesosKilos, double precio, double agregarSubtotal, Categoria categoriaAduana, int cantidad, TiposProductos tipo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.pesosKilos = pesosKilos;
        this.precio = precio;
        this.categoriaAduana = null;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPesosKilos() {
        return pesosKilos;
    }

    public void setPesosKilos(double pesosKilos) {
        this.pesosKilos = pesosKilos;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Categoria getCategoriaAduana() {
        return categoriaAduana;
    }

    public void setCategoriaAduana(Categoria categoriaAduana) {
        this.categoriaAduana = categoriaAduana;
    }

    public TiposProductos getTipo() {
        return tipo;
    }

    public void setTipo(TiposProductos tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", pesosKilos=" + pesosKilos +
                ", precio=" + precio +
                ", categoriaAduana=" + categoriaAduana +
                '}';
    }

    public Categoria determinarCategoria() throws Exception {
        if (tipo == TiposProductos.Vestimenta && pesosKilos <= 20.0 && precio <= 2000) {
            categoriaAduana = Categoria.D;
        } else if (tipo != TiposProductos.Vestimenta && pesosKilos < 4.0 && precio < 400) {
            categoriaAduana = Categoria.B;
        } else if (tipo != TiposProductos.Vestimenta && pesosKilos > 4.0 && pesosKilos < 100.0 && precio > 400.0 && precio < 5000.0) {
            categoriaAduana = Categoria.C;
        } else {
            JOptionPane.showMessageDialog(null, "El producto no corresponde a ninguna de las categorías con las que trabaja la empresa", "Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception("El producto no corresponde a ninguna de las categorías con las que trabaja la empresa");
        }
        return categoriaAduana;
    }


    public double calcularSubtotal() {
        double valorSubtotal = 0.0;
        if (categoriaAduana.equals(Categoria.B)) {
            valorSubtotal = precio * cantidad;
        } else if (categoriaAduana.equals(Categoria.C)) {
            valorSubtotal = (precio + (precio * 0.12) + (precio * 0.05)) * cantidad;
        } else if (categoriaAduana.equals(Categoria.D)) {
            valorSubtotal = (precio + (precio * 0.10) + (precio * 0.12) + (precio * 0.05) + (5.5 * pesosKilos)) * cantidad;
        }
        return valorSubtotal;
    }

}
