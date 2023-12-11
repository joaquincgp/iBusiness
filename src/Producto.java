import javax.swing.*;

public class Producto {
    public enum Categoria {
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


    public Producto(String nombre, String descripcion, double pesosKilos, double precio, int cantidad, String tiposProductosString) {
        setNombre(nombre);
        setDescripcion(descripcion);
        setPesosKilos(pesosKilos);
        setPrecio(precio);
        this.categoriaAduana = null;
        setCantidad(cantidad);
        tipo = Producto.TiposProductos.valueOf(tiposProductosString);
        setTipo(tipo);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre del producto no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Campo vacio");
        }
        String nombreSinEspacios = nombre.trim();
        int cantidadPalabras = contarPalabras(nombreSinEspacios);

        // Verificar si no excede el límite de 5 palabras
        if(cantidadPalabras >4){
            JOptionPane.showMessageDialog(null, "El nombre del producto no puede contener mas de 4 palabras", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Nombre muy largo");
        }
        this.nombre = nombre;
    }
    private int contarPalabras(String texto) {
        // Dividir la cadena en palabras usando espacios en blanco como delimitador
        String[] palabras = texto.split("\\s+");
        // Contar las palabras resultantes
        return palabras.length;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (nombre == null || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La descripcion del producto no puede estar vacia", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Campo vacio");
        }
        this.descripcion = descripcion;
    }

    public double getPesosKilos() {
        return pesosKilos;
    }

    public void setPesosKilos(double pesosKilos) {
        if (pesosKilos<=0 ||pesosKilos>100) {
            JOptionPane.showMessageDialog(null, "El peso excede los limites reglamentarios", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Peso excesivo");
        }
        this.pesosKilos = pesosKilos;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio>5000 ||precio<=0) {
            JOptionPane.showMessageDialog(null, "El precio esta fuera del rango permitido por producto", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Precio impreciso");
        }
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if (cantidad>3 ||cantidad<=0) {
            JOptionPane.showMessageDialog(null, "La cantidad por producto esta fuera de lo permitido", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Cantidad restringida");
        }
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
        return "Nombre: " + nombre + '/' +
                " Descripcion: '" + descripcion + '/' +
                " Peso (kg): " + pesosKilos +'/'+
                " Precio: $" + precio + '/'+
                " Categoria: " + categoriaAduana + '/'+
                " Cantidad: " + cantidad + '/'+
                " Tipo: "+tipo + '\n';
    }

    public Categoria determinarCategoria() throws Exception {
        if (!(tipo == TiposProductos.Vestimenta) && (pesosKilos < 4.0) && (precio < 400)) {
            categoriaAduana = Categoria.B;
        } else if (!(tipo ==TiposProductos.Vestimenta) && (pesosKilos > 4.0) && (pesosKilos < 100.0) && (precio > 400.0) && (precio < 5000.0)) {
            categoriaAduana = Categoria.C;
        } else if (tipo == TiposProductos.Vestimenta && (pesosKilos <= 20.0) && (precio <= 2000)) {
            categoriaAduana = Categoria.D;
        } else {
            JOptionPane.showMessageDialog(null, "El producto no corresponde a ninguna de las categorias con las que trabaja la empresa" ,"Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception("El producto no corresponde a ninguna de las categorias con las que trabaja la empresa");
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
