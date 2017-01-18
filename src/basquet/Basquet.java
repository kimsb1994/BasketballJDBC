package basquet;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import model.Equipo;
import persistence.BasquetJDBC;
import java.util.Date;
import model.Jugador;

public class Basquet {

    public static void main(String[] args) {
        BasquetJDBC gestor = new BasquetJDBC();
        try {
            System.out.println("Estableciendo conexión con la bbdd...");
            gestor.conectar();
            System.out.println("Conectado a la bbdd Basket");
            
            Equipo Equipo1 = new Equipo("Bronx", "New York", LocalDate.of(1862, 03, 5));
            Equipo Equipo2 = new Equipo("Barcelona", "Catalunya", LocalDate.of(1899, 04, 9));
            Equipo Equipo3 = new Equipo("Madriz", "Espanya", LocalDate.of(1902, 07, 8));
            gestor.insertEquipo(Equipo1);
            gestor.insertEquipo(Equipo2);
            gestor.insertEquipo(Equipo3);
            System.out.println("Equipos dados de alta.");
            
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
        } catch (SQLException ex) {
            System.out.println("Error con la BBDD: " + ex.getMessage());
        }
    }

}
