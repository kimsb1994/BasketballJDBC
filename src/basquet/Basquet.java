package basquet;

import static Utilidades.EntradaDatos.pedirCadena;
import static Utilidades.EntradaDatos.pedirEntero;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import model.Equipo;
import persistence.BasquetJDBC;
import java.util.Date;
import model.Jugador;

public class Basquet {

    public static void main(String[] args) throws SQLException {
        BasquetJDBC gestor = new BasquetJDBC();
            System.out.println("Estableciendo conexión con la bbdd...");
            gestor.conectar();
            System.out.println("Conectado a la bbdd Basket");
            int opcion = 0;
            do {
            mostrarMenu();
            opcion = pedirEntero("Escoge una opción");
            switch (opcion) {
                case 1:
                    InsertarEquipo();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 0:
                    System.out.println("Bye!!");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        } while (opcion != 0);
            
            
            List<Equipo> todosEquipos = gestor.selectAllEquipos();
            System.out.println("Listado de Equipos");
            for (Equipo e : todosEquipos) {
                System.out.println(e);
            }
            
            Jugador jugador1 = new Jugador("ShitNigga",LocalDate.of(1862, 03, 5),10,10,10,"Base", Equipo1);
            gestor.insertJugador(jugador1);            
            System.out.println("Jugadores dados de alta.");
            List<Jugador> todosJugadores = gestor.selectAllJugador();
            System.out.println("Listado de jugadores");
            for (Jugador j : todosJugadores) {
                System.out.println(j);
            }
            
            
            
            
            gestor.desconectar();
            System.out.println("Cerrada la conexión con la bbdd.");
    }
        private static void mostrarMenu() {
        System.out.println("*** OPTIONS ***");
        System.out.println("1. Insertar un equipo nuevo");        
        System.out.println("2. Mostrar equipos");
        System.out.println("3. Insertar un jugador nuevo");   
        System.out.println("4. Mostrar jugadores");
        System.out.println("0. Salir");
    }

    private static void InsertarEquipo() throws SQLException {        
        BasquetJDBC gestor = new BasquetJDBC();
        String name = pedirCadena("Nombre del equipo:");        
        String location = pedirCadena("Nombre de la localidad:");        
        int dia = pedirEntero("Dia de creacion(0-31):");
        int mes = pedirEntero("mes de creacion(0-12):");
        int ano = pedirEntero("año de creacion(1800-2017):");
        Equipo Equipo1 = new Equipo(name, location, LocalDate.of(ano, mes, dia));
        gestor.insertEquipo(Equipo1);
        System.out.println("Equipo dado de alta.");
    }
    
        private static void InsertarJugador() throws SQLException {        
        BasquetJDBC gestor = new BasquetJDBC();
        String name = pedirCadena("Nombre del equipo:");        
        String location = pedirCadena("Nombre de la localidad:");        
        int dia = pedirEntero("Dia de creacion(0-31):");
        int mes = pedirEntero("mes de creacion(0-12):");
        int ano = pedirEntero("año de creacion(1800-2017):");
        Equipo Equipo1 = new Equipo(name, location, LocalDate.of(ano, mes, dia));
        gestor.insertEquipo(Equipo1);
        System.out.println("Equipo dado de alta.");
    }
    
        

}
