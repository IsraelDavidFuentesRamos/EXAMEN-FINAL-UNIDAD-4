// ==================== CAPA MODELO ====================
package modelo;

public class Hotel {
    private String nombre;
    private String ubicacion;
    private int estrellas;
    
    public Hotel(String nombre, String ubicacion, int estrellas) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.estrellas = estrellas;
    }
    
    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public int getEstrellas() { return estrellas; }
    public void setEstrellas(int estrellas) { this.estrellas = estrellas; }
}

public class Habitacion {
    private int numero;
    private String tipo;
    private double precio;
    
    public Habitacion(int numero, String tipo, double precio) {
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio;
    }
    
    // Getters y Setters
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
}

public class Huesped {
    private String nombre;
    private int edad;
    private String nacionalidad;
    
    public Huesped(String nombre, int edad, String nacionalidad) {
        this.nombre = nombre;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
    }
    
    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
}

// ==================== CAPA REPOSITORIO ====================
package repositorio;

import modelo.Hotel;
import java.util.ArrayList;
import java.util.List;

public class RepositorioHotel {
    private List<Hotel> hoteles;
    
    public RepositorioHotel() {
        this.hoteles = new ArrayList<>();
    }
    
    public void guardar(Hotel hotel) {
        hoteles.add(hotel);
    }
    
    public List<Hotel> obtenerTodos() {
        return new ArrayList<>(hoteles);
    }
    
    public Hotel buscarPorNombre(String nombre) {
        return hoteles.stream()
                     .filter(h -> h.getNombre().equals(nombre))
                     .findFirst()
                     .orElse(null);
    }
    
    public boolean actualizar(String nombre, Hotel hotelActualizado) {
        for (int i = 0; i < hoteles.size(); i++) {
            if (hoteles.get(i).getNombre().equals(nombre)) {
                hoteles.set(i, hotelActualizado);
                return true;
            }
        }
        return false;
    }
    
    public boolean eliminar(String nombre) {
        return hoteles.removeIf(h -> h.getNombre().equals(nombre));
    }
}

package repositorio;

import modelo.Habitacion;
import java.util.ArrayList;
import java.util.List;

public class RepositorioHabitacion {
    private List<Habitacion> habitaciones;
    
    public RepositorioHabitacion() {
        this.habitaciones = new ArrayList<>();
    }
    
    public void guardar(Habitacion habitacion) {
        habitaciones.add(habitacion);
    }
    
    public List<Habitacion> obtenerTodas() {
        return new ArrayList<>(habitaciones);
    }
    
    public Habitacion buscarPorNumero(int numero) {
        return habitaciones.stream()
                         .filter(h -> h.getNumero() == numero)
                         .findFirst()
                         .orElse(null);
    }
    
    public boolean actualizar(int numero, Habitacion habitacionActualizada) {
        for (int i = 0; i < habitaciones.size(); i++) {
            if (habitaciones.get(i).getNumero() == numero) {
                habitaciones.set(i, habitacionActualizada);
                return true;
            }
        }
        return false;
    }
    
    public boolean eliminar(int numero) {
        return habitaciones.removeIf(h -> h.getNumero() == numero);
    }
}

package repositorio;

import modelo.Huesped;
import java.util.ArrayList;
import java.util.List;

public class RepositorioHuesped {
    private List<Huesped> huespedes;
    
    public RepositorioHuesped() {
        this.huespedes = new ArrayList<>();
    }
    
    public void guardar(Huesped huesped) {
        huespedes.add(huesped);
    }
    
    public List<Huesped> obtenerTodos() {
        return new ArrayList<>(huespedes);
    }
    
    public Huesped buscarPorNombre(String nombre) {
        return huespedes.stream()
                       .filter(h -> h.getNombre().equals(nombre))
                       .findFirst()
                       .orElse(null);
    }
    
    public boolean actualizar(String nombre, Huesped huespedActualizado) {
        for (int i = 0; i < huespedes.size(); i++) {
            if (huespedes.get(i).getNombre().equals(nombre)) {
                huespedes.set(i, huespedActualizado);
                return true;
            }
        }
        return false;
    }
    
    public boolean eliminar(String nombre) {
        return huespedes.removeIf(h -> h.getNombre().equals(nombre));
    }
}

// ==================== CAPA CONTROLADOR ====================
package controlador;

import modelo.Hotel;
import repositorio.RepositorioHotel;
import java.util.List;

public class ControladorHotel {
    private RepositorioHotel repositorio;
    
    public ControladorHotel() {
        this.repositorio = new RepositorioHotel();
    }
    
    public void crearHotel(String nombre, String ubicacion, int estrellas) {
        // Validaciones
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del hotel no puede estar vacío");
        }
        if (estrellas < 1 || estrellas > 5) {
            throw new IllegalArgumentException("El número de estrellas debe estar entre 1 y 5");
        }
        
        Hotel hotel = new Hotel(nombre, ubicacion, estrellas);
        repositorio.guardar(hotel);
    }
    
    public List<Hotel> listarHoteles() {
        return repositorio.obtenerTodos();
    }
    
    public Hotel buscarHotel(String nombre) {
        return repositorio.buscarPorNombre(nombre);
    }
    
    public boolean actualizarHotel(String nombreOriginal, String nuevoNombre, String nuevaUbicacion, int nuevasEstrellas) {
        // Validaciones similares a crearHotel
        Hotel hotelActualizado = new Hotel(nuevoNombre, nuevaUbicacion, nuevasEstrellas);
        return repositorio.actualizar(nombreOriginal, hotelActualizado);
    }
    
    public boolean eliminarHotel(String nombre) {
        return repositorio.eliminar(nombre);
    }
}

package controlador;

import modelo.Habitacion;
import repositorio.RepositorioHabitacion;
import java.util.List;

public class ControladorHabitacion {
    private RepositorioHabitacion repositorio;
    
    public ControladorHabitacion() {
        this.repositorio = new RepositorioHabitacion();
    }
    
    public void crearHabitacion(int numero, String tipo, double precio) {
        // Validaciones
        if (numero <= 0) {
            throw new IllegalArgumentException("El número de habitación debe ser positivo");
        }
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de habitación no puede estar vacío");
        }
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que 0");
        }
        
        Habitacion habitacion = new Habitacion(numero, tipo, precio);
        repositorio.guardar(habitacion);
    }
    
    public List<Habitacion> listarHabitaciones() {
        return repositorio.obtenerTodas();
    }
    
    public Habitacion buscarHabitacion(int numero) {
        return repositorio.buscarPorNumero(numero);
    }
    
    public boolean actualizarHabitacion(int numeroOriginal, int nuevoNumero, String nuevoTipo, double nuevoPrecio) {
        // Validaciones similares a crearHabitacion
        Habitacion habitacionActualizada = new Habitacion(nuevoNumero, nuevoTipo, nuevoPrecio);
        return repositorio.actualizar(numeroOriginal, habitacionActualizada);
    }
    
    public boolean eliminarHabitacion(int numero) {
        return repositorio.eliminar(numero);
    }
}

package controlador;

import modelo.Huesped;
import repositorio.RepositorioHuesped;
import java.util.List;

public class ControladorHuesped {
    private RepositorioHuesped repositorio;
    
    public ControladorHuesped() {
        this.repositorio = new RepositorioHuesped();
    }
    
    public void crearHuesped(String nombre, int edad, String nacionalidad) {
        // Validaciones
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del huésped no puede estar vacío");
        }
        if (edad < 0 || edad > 150) {
            throw new IllegalArgumentException("La edad debe estar entre 0 y 150 años");
        }
        if (nacionalidad == null || nacionalidad.trim().isEmpty()) {
            throw new IllegalArgumentException("La nacionalidad no puede estar vacía");
        }
        
        Huesped huesped = new Huesped(nombre, edad, nacionalidad);
        repositorio.guardar(huesped);
    }
    
    public List<Huesped> listarHuespedes() {
        return repositorio.obtenerTodos();
    }
    
    public Huesped buscarHuesped(String nombre) {
        return repositorio.buscarPorNombre(nombre);
    }
    
    public boolean actualizarHuesped(String nombreOriginal, String nuevoNombre, int nuevaEdad, String nuevaNacionalidad) {
        // Validaciones similares a crearHuesped
        Huesped huespedActualizado = new Huesped(nuevoNombre, nuevaEdad, nuevaNacionalidad);
        return repositorio.actualizar(nombreOriginal, huespedActualizado);
    }
    
    public boolean eliminarHuesped(String nombre) {
        return repositorio.eliminar(nombre);
    }
}

// ==================== CAPA VISTA ====================
package vista;

import javax.swing.*;
import java.awt.*;
import controlador.ControladorHotel;
import controlador.ControladorHabitacion;
import controlador.ControladorHuesped;
import modelo.Hotel;
import modelo.Habitacion;
import modelo.Huesped;

public class VentanaPrincipal extends JFrame {
    private ControladorHotel controladorHotel;
    private ControladorHabitacion controladorHabitacion;
    private ControladorHuesped controladorHuesped;
    
    public VentanaPrincipal() {
        controladorHotel = new ControladorHotel();
        controladorHabitacion = new ControladorHabitacion();
        controladorHuesped = new ControladorHuesped();
        configurarVentana();
        crearMenu();
    }
    
    private void configurarVentana() {
        setTitle("Sistema de Gestión Hotelera");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void crearMenu() {
        JMenuBar barraMenu = new JMenuBar();
        
        // Menú Hotel
        JMenu menuHotel = new JMenu("Hoteles");
        JMenuItem itemCrearHotel = new JMenuItem("Crear Hotel");
        JMenuItem itemListarHoteles = new JMenuItem("Listar Hoteles");
        
        itemCrearHotel.addActionListener(e -> mostrarFormularioCrearHotel());
        itemListarHoteles.addActionListener(e -> mostrarListaHoteles());
        
        menuHotel.add(itemCrearHotel);
        menuHotel.add(itemListarHoteles);
        
        // Menú Habitación
        JMenu menuHabitacion = new JMenu("Habitaciones");
        JMenuItem itemCrearHabitacion = new JMenuItem("Crear Habitación");
        JMenuItem itemListarHabitaciones = new JMenuItem("Listar Habitaciones");
        
        itemCrearHabitacion.addActionListener(e -> mostrarFormularioCrearHabitacion());
        itemListarHabitaciones.addActionListener(e -> mostrarListaHabitaciones());
        
        menuHabitacion.add(itemCrearHabitacion);
        menuHabitacion.add(itemListarHabitaciones);
        
        // Menú Huésped
        JMenu menuHuesped = new JMenu("Huéspedes");
        JMenuItem itemCrearHuesped = new JMenuItem("Crear Huésped");
        JMenuItem itemListarHuespedes = new JMenuItem("Listar Huéspedes");
        
        itemCrearHuesped.addActionListener(e -> mostrarFormularioCrearHuesped());
        itemListarHuespedes.addActionListener(e -> mostrarListaHuespedes());
        
        menuHuesped.add(itemCrearHuesped);
        menuHuesped.add(itemListarHuespedes);
        
        barraMenu.add(menuHotel);
        barraMenu.add(menuHabitacion);
        barraMenu.add(menuHuesped);
        
        setJMenuBar(barraMenu);
    }
    
    // Métodos para Hotel
    private void mostrarFormularioCrearHotel() {
        JDialog dialogo = new JDialog(this, "Crear Hotel", true);
        