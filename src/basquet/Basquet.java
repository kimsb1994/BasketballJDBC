package basquet;

import static Utilidades.EntradaDatos.pedirCadena;
import static Utilidades.EntradaDatos.pedirEntero;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
        /*
        Equipo Equipo1 = new Equipo("Barcelona", "Catalunya", LocalDate.of(1899, 06, 14));
        Equipo Equipo2 = new Equipo("Real de Madriz", "Espanya", LocalDate.of(1902, 8, 21));
        gestor.insertEquipo(Equipo1);
        gestor.insertEquipo(Equipo2);   
        
        System.out.println("Equipos dados de alta");
        System.out.println("...");
        List<Equipo> todosEquipos = gestor.selectAllEquipos();
        System.out.println("Listado de Equipos");
        for (Equipo e : todosEquipos) {
            System.out.println(e);
        }        
        Jugador jugador1 = new Jugador("Navarro", LocalDate.of(1987, 07, 25), 450, 65, 21, "Escolta", Equipo1);
        Jugador jugador2 = new Jugador("Dollman", LocalDate.of(1986, 12, 27), 650, 420, 201, "Ala-Pivot", Equipo1);
        Jugador jugador3 = new Jugador("Koponen", LocalDate.of(1992, 10, 15), 250, 122, 211, "alero", Equipo1);
        Jugador jugador4 = new Jugador("Rice", LocalDate.of(1987, 07, 25), 1250, 765, 21, "Base", Equipo1);
        Jugador jugador5 = new Jugador("Dorsey", LocalDate.of(1987, 07, 25), 750, 25, 721, "Pivot", Equipo1);
        
        Jugador jugador6 = new Jugador("doncic", LocalDate.of(1988, 04, 14), 325, 124, 126, "escolta", Equipo2);
        Jugador jugador7 = new Jugador("hunter", LocalDate.of(1985, 06, 27), 650, 420, 201, "Ala-Pivot", Equipo2);
        Jugador jugador8 = new Jugador("randolph", LocalDate.of(1992, 9, 15), 250, 122, 211, "alero", Equipo2);
        Jugador jugador9 = new Jugador("llull", LocalDate.of(1987, 07, 10), 235, 665, 221, "Base", Equipo2);
        Jugador jugador10 = new Jugador("ayón", LocalDate.of(1987, 11, 1), 750, 225, 421, "Pivot", Equipo2);
        gestor.insertJugador(jugador1);gestor.insertJugador(jugador2);gestor.insertJugador(jugador3);gestor.insertJugador(jugador4);gestor.insertJugador(jugador5);gestor.insertJugador(jugador6);gestor.insertJugador(jugador7);gestor.insertJugador(jugador8);gestor.insertJugador(jugador9);gestor.insertJugador(jugador10);
        System.out.println("Jugadores dados de alta");
        System.out.println("...");
*/
        List<Jugador> todosJugadores = gestor.selectAllJugador();
        System.out.println("Listado de jugadores");
        for (Jugador j : todosJugadores) {
            System.out.println(j);
        }
        System.out.println("Lista completa");
        System.out.println("...");   
        /*
        System.out.println("Modificar Canastas,Rebotes y asistencias de un jugador (Jugador1).");
        jugador1.setNassists(85);
        jugador1.setNbaskets(4222);
        jugador1.setNrebounds(111);
        gestor.modificarJugador(jugador1);
*/
        System.out.println("Modificado correctamente");
        System.out.println("...");
        System.out.println("Modificar Equipo de un jugador (Jugador2).");
        gestor.modificarEquipo("llull", "Barcelona");        
        System.out.println("Modificado correctamente");
        System.out.println("...");
        /*
        System.out.println("eliminando un jugador (Jugador7).");
        gestor.eliminarJugador(jugador7);
        System.out.println("eliminado correctamente");
        */
        System.out.println("...");        
        System.out.println("Buscando objecto del jugador (llull).");        
        Jugador jugadorBuscado = gestor.selectJugadorName("llull");
        System.out.println(jugadorBuscado);
        System.out.println("...");        
        System.out.println("Buscando objecto del jugador (Nav).");        
        Jugador jugadorBuscadoIncompleto = gestor.selectJugadorNameIncompleto("Nav");
        System.out.println(jugadorBuscadoIncompleto);
        System.out.println("...");        
        System.out.println("Buscando jugadores con canastas mayor o igual a 100.");        
        List <Jugador> JugadorMayorIgualCanastas = gestor.selectJugadorMayorIgualCanastas(100);
        for (Jugador j : JugadorMayorIgualCanastas) {
            System.out.println(j);
        }        
        System.out.println("...");        
        System.out.println("Busc,ando jugadores con canastas entre 50 y 300.");        
        List <Jugador> JugadorIntervaloAsiste = gestor.selectJugadorEntreAssistencias(300,50);
        for (Jugador j : JugadorIntervaloAsiste) {
            System.out.println(j);
        }        
        System.out.println("...");        
        System.out.println("Buscando jugadores por posicion (Base).");        
        List <Jugador> JugadorPosicion = gestor.selectJugadorPosicion("base");
        for (Jugador j : JugadorPosicion) {
            System.out.println(j);
        }                
        System.out.println("...");        
        System.out.println("Buscando jugadores por una fecha posterior a 10/5/1989.");        
        List <Jugador> JugadorPostFecha = gestor.selectJugadorPostFecha(10,05,1989);
        for (Jugador j : JugadorPostFecha) {
            System.out.println(j);
        }                      
        System.out.println("...");        
        System.out.println("Posiciones mostrando AVG, MAX i MIN.");        
        List PosicionAvgMinMax = gestor.selectPosicionAvgMaxMin();
        for(int i =0; i<PosicionAvgMinMax.size();i++){
        System.out.println(PosicionAvgMinMax.get(i));
        }
        System.out.println("...");        
        System.out.println("Posiciones mostrando AVG, MAX i MIN.");        
        List Rankings = gestor.RankingsJugador();
        for(int i =0; i<Rankings.size();i++){
        System.out.println(Rankings.get(i));
        }        
        System.out.println("...");        
        System.out.println("Posiciones mostrando AVG, MAX i MIN.");        
        List RankingsBusqueda = gestor.RankingsJugadorBusqueda("Dollman");
        for(int i =0; i<RankingsBusqueda.size();i++){
        System.out.println(RankingsBusqueda.get(i));
        }             
        System.out.println("...");        
        System.out.println("Posiciones mostrando AVG, MAX i MIN.");        
        List RankingsBusquedaPosicion = gestor.RankingsJugadorBusqueda("Dollman");
        for(int i =0; i<RankingsBusquedaPosicion.size();i++){
        System.out.println(RankingsBusquedaPosicion.get(i));
        }
        
        

    }

}
