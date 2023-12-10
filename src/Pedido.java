import java.time.LocalDate;
import java.util.LinkedList;
import java.util.UUID;

public class Pedido {
    private String codigoTracking;
    private Usuario cliente;
    private LocalDate fechaPedido;
    private LinkedList<Producto> productos;
    private double costoEnvio;
    private double total;
    private enum  EstadoPedido{
        EN_PROCESO,
        ENVIADO,
        ENTREGADO;
    }

    public Pedido(String codigoTracking, Usuario cliente, LocalDate fechaPedido, LinkedList<Producto> productos, double costoEnvio, double total) {
        this.codigoTracking = codigoTracking;
        this.cliente = cliente;
        this.fechaPedido = fechaPedido;
        this.productos = productos;
        this.costoEnvio = costoEnvio;
        this.total = total;
    }

    public String generarTrackingID() {
        UUID idAleatorio = UUID.randomUUID();
        return idAleatorio.toString();
    }

    public void setCodigoTracking(String codigoTracking) {
        this.codigoTracking = codigoTracking;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public LinkedList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(LinkedList<Producto> productos) {
        this.productos = productos;
    }

    public double getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(double costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
