package org.techlab;

import org.techlab.producto.Producto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductoService {
    private ArrayList<Producto> productos = new ArrayList<>();
    private Scanner scanner;

    public ProductoService(Scanner scanner) {
        this.scanner = scanner;
    }

    public void agregarProducto() {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        productos.add(new Producto(nombre, precio, stock));
        System.out.println("Producto Agregado.");
    }

    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos a mostrar.");
            return;
        }
        for (Producto p : productos) {
            System.out.println(p);
        }
    }

    public Producto buscarPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public void actualizarProducto() {
        System.out.print("Ingrese el ID del producto : ");
        int id = scanner.nextInt();
        Producto producto = buscarPorId(id);
        if (producto == null) {
            System.out.println("No se encontro el producto");
            return;
        }
        System.out.print("El precio actual es de " + producto.getPrecio() + " ,ingrese el nuevo valor : ");
        producto.setPrecio(scanner.nextDouble());
        System.out.print("El stock actual es  " + producto.getStock() + " ,ingrese el nuevo valor : ");
        producto.setStock(scanner.nextInt());
        System.out.println("Producto actualizado.");
    }

    public void eliminarProducto() {
        System.out.print("ID del producto a eliminar: ");
        int id = scanner.nextInt();
        Producto producto = buscarPorId(id);
        if (producto != null) {
            System.out.println("El producto a eliminar es: " + producto.getNombre() + " ?");
            System.out.println("Desea eliminarlo ?");
            System.out.println("1: SI");
            System.out.println("2: NO");
            int eliminar = scanner.nextInt();
            if (eliminar == 1) {
                productos.remove(producto);
                System.out.println("Producto eliminado.");
            } else {
                System.out.println("Operacion cancelada.");
            }
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }
}
