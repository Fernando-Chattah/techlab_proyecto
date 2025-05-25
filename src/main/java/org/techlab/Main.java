package org.techlab;

import org.techlab.producto.Producto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ProductoService productoService = new ProductoService(scanner);
        PedidoService pedidoService = new PedidoService(productoService, scanner);

        boolean salida = false;
        while (!salida) {
            System.out.println("===================================");
            System.out.println("   SISTEMA DE GESTIÓN - TECHLAB");
            System.out.println("===================================");
            System.out.println("1. Agregar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Buscar/Actualizar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Crear pedido");
            System.out.println("6. Listar pedidos");
            System.out.println("0. Salir");
            System.out.print("Elija una opción: ");

            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1": productoService.agregarProducto(); break;
                case "2": productoService.listarProductos(); break;
                case "3": productoService.actualizarProducto(); break;
                case "4": productoService.eliminarProducto(); break;
                case "5": pedidoService.crearPedido(); break;
                case "6": pedidoService.listarPedidos(); break;
                case "0": salida = true; break;
                default: {
                    System.out.println("Opción no válida.");
                    break;
                }
            }
        }
        System.out.println("Adios");
        scanner.close();
    }
}
