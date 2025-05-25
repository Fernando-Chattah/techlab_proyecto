package org.techlab;

import org.techlab.excepciones.InsuficienteStockException;
import org.techlab.pedido.Pedido;
import org.techlab.pedido.LineaPedido;
import org.techlab.producto.Producto;

import java.util.ArrayList;
import java.util.Scanner;

public class PedidoService {
    private ArrayList<Pedido> pedidos = new ArrayList<>();
    private ProductoService productoService;
    private Scanner scanner;

    public PedidoService(ProductoService productoService, Scanner scanner) {
        this.productoService = productoService;
        this.scanner = scanner;
    }

    public void crearPedido() {

        boolean salir = false;

        while (!salir) {
            if (productoService.getProductos().isEmpty()) {
                System.out.println("No se puede solicitar el pedido ya que no hay productos");
                break;
            } else {
                System.out.println("Se listan los productos que estan registrados :");
                productoService.listarProductos();

                System.out.print("Ingrese el ID del producto para solicitar el Stock: ");
                int id = scanner.nextInt();

                Producto producto = productoService.buscarPorId(id);

                if (producto == null) {
                    System.out.println("Producto no encontrado.");
                    break;
                }

                Pedido pedido = new Pedido();
                System.out.print("Cantidad: ");
                int cantidad = scanner.nextInt();

                try {
                    if (cantidad > producto.getStock()) {
                        throw new InsuficienteStockException("No hay suficiente stock.");
                    } else if (cantidad < 1) {
                        throw new Error("No puede haber stock menor que 1");
                    }
                    producto.setStock(producto.getStock() - cantidad);
                    pedido.agregarLinea(new LineaPedido(producto, cantidad));
                } catch (InsuficienteStockException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                pedidos.add(pedido);
                System.out.println("Pedido creado:\n" + pedidos.toString());
            }

            System.out.println("Desea solicitar un nuevo pedido ?");
            System.out.println("1: SI");
            System.out.println("2: NO");

            int opcion = scanner.nextInt();
            if (opcion == 2) {
                salir = true;
                break;
            }
        }
    }

    public void listarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos realizados.");
            return;
        }
        for (Pedido p : pedidos) {
            System.out.println(p);
            System.out.println("--------------------");
        }
    }
}