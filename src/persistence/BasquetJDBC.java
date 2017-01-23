package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Equipo;
import java.util.Date;
import model.Jugador;

public class BasquetJDBC {

    private Connection conexion;

    public List<Equipo> selectAllEquipos() throws SQLException {
        List<Equipo> equipos = new ArrayList<>();
        String query = "select * from team";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Equipo c = new Equipo();
            c.setName(rs.getString("name"));
            c.setCity(rs.getString("city"));
            c.setCreation(rs.getDate("creation").toLocalDate());
            equipos.add(c);
        }
        rs.close();
        st.close();
        return equipos;
    }

    public void insertEquipo(Equipo e) throws SQLException {
        String insert = "insert into team values (?, ?, ?);";
        System.out.println(conexion);
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, e.getName());
        ps.setString(2, e.getCity());
        ps.setDate(3, java.sql.Date.valueOf(e.getCreation()));
        ps.executeUpdate();
        ps.close();
    }

    public void modificarEquipo(String jugador, String equipo) throws SQLException {
        String insert = "update player set team = ?" + " where name = ?;";
        System.out.println(conexion);
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, equipo);
        ps.setString(2, jugador);
        ps.executeUpdate();
        ps.close();
    }

    public List<Jugador> selectAllJugador() throws SQLException {
        List<Jugador> jugadores = new ArrayList<>();
        String query = "select * from player";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Jugador c = new Jugador();
            Equipo e = new Equipo();
            c.setName(rs.getString("name"));
            c.setBirth(rs.getDate("birth").toLocalDate());
            c.setNbaskets(rs.getInt("nbaskets"));
            c.setNassists(rs.getInt("nassists"));
            c.setNrebounds(rs.getInt("nrebounds"));
            c.setPosition(rs.getString("Position"));
            e.setName(rs.getString("team"));
            c.setTeam(e);
            jugadores.add(c);
        }
        rs.close();
        st.close();
        return jugadores;
    }

    public void insertJugador(Jugador j) throws SQLException {
        String insert = "insert into player values (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, j.getName());
        ps.setDate(2, java.sql.Date.valueOf(j.getBirth()));
        ps.setInt(3, j.getNbaskets());
        ps.setInt(4, j.getNassists());
        ps.setInt(5, j.getNrebounds());
        ps.setString(6, j.getPosition());
        ps.setString(7, j.getTeam().getName());
        ps.executeUpdate();
        ps.close();
    }

    public void modificarJugador(Jugador j) throws SQLException {
        String update = "update player set name = ?, birth = ?, nbaskets = ?, nassists = ?, nrebounds = ?, position = ?, team = ? " + " WHERE name = ?";
        PreparedStatement ps = conexion.prepareStatement(update);
        ps.setString(1, j.getName());
        ps.setDate(2, java.sql.Date.valueOf(j.getBirth()));
        ps.setInt(3, j.getNbaskets());
        ps.setInt(4, j.getNassists());
        ps.setInt(5, j.getNrebounds());
        ps.setString(6, j.getPosition());
        ps.setString(7, j.getTeam().getName());
        ps.setString(8, j.getName());
        ps.executeUpdate();
        ps.close();
    }

    public void eliminarJugador(Jugador j) throws SQLException {
        String update = "delete from player where name= ?";
        PreparedStatement ps = conexion.prepareStatement(update);
        ps.setString(1, j.getName());
        ps.executeUpdate();
        ps.close();
    }

    public Jugador selectJugadorName(String Nombre) throws SQLException {
        String query = "select * from player where name = '" + Nombre + "';";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        Jugador jugadorBuscado = new Jugador();
        Equipo e = new Equipo();
        while (rs.next()) {
            jugadorBuscado.setName(rs.getString("name"));
            jugadorBuscado.setBirth(rs.getDate("birth").toLocalDate());
            jugadorBuscado.setNbaskets(rs.getInt("nbaskets"));
            jugadorBuscado.setNassists(rs.getInt("nassists"));
            jugadorBuscado.setNrebounds(rs.getInt("nrebounds"));
            jugadorBuscado.setPosition(rs.getString("Position"));
            e.setName(rs.getString("team"));
            jugadorBuscado.setTeam(e);
        }
        rs.close();
        st.close();
        return jugadorBuscado;
    }

    public Jugador selectJugadorNameIncompleto(String Nombre) throws SQLException {
        ArrayList<Jugador> Jugador = new ArrayList<>();
        String query = "select * from player where name like '%" + Nombre + "%';";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        Jugador jugadorBuscado = new Jugador();
        Equipo e = new Equipo();
        while (rs.next()) {
            jugadorBuscado.setName(rs.getString("name"));
            jugadorBuscado.setBirth(rs.getDate("birth").toLocalDate());
            jugadorBuscado.setNbaskets(rs.getInt("nbaskets"));
            jugadorBuscado.setNassists(rs.getInt("nassists"));
            jugadorBuscado.setNrebounds(rs.getInt("nrebounds"));
            jugadorBuscado.setPosition(rs.getString("Position"));
            e.setName(rs.getString("team"));
            jugadorBuscado.setTeam(e);
        }
        rs.close();
        st.close();
        return jugadorBuscado;
    }

    public List selectJugadorMayorIgualCanastas(int Canastas) throws SQLException {
        List<Jugador> Jugador = new ArrayList<>();
        String query = "select * from player where nbaskets >= " + Canastas + ";";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Jugador jugadorBuscado = new Jugador();
            Equipo e = new Equipo();
            jugadorBuscado.setName(rs.getString("name"));
            jugadorBuscado.setBirth(rs.getDate("birth").toLocalDate());
            jugadorBuscado.setNbaskets(rs.getInt("nbaskets"));
            jugadorBuscado.setNassists(rs.getInt("nassists"));
            jugadorBuscado.setNrebounds(rs.getInt("nrebounds"));
            jugadorBuscado.setPosition(rs.getString("Position"));
            e.setName(rs.getString("team"));
            jugadorBuscado.setTeam(e);
            Jugador.add(jugadorBuscado);
        }
        rs.close();
        st.close();
        return Jugador;
    }

    public List selectJugadorEntreAssistencias(int mayor, int menor) throws SQLException {
        List<Jugador> Jugador = new ArrayList<>();
        String query = "select * from player where nassists >= " + menor + " and nassists <= " + mayor;
        System.out.println(query);
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Jugador jugadorBuscado = new Jugador();
            Equipo e = new Equipo();
            jugadorBuscado.setName(rs.getString("name"));
            jugadorBuscado.setBirth(rs.getDate("birth").toLocalDate());
            jugadorBuscado.setNbaskets(rs.getInt("nbaskets"));
            jugadorBuscado.setNassists(rs.getInt("nassists"));
            jugadorBuscado.setNrebounds(rs.getInt("nrebounds"));
            jugadorBuscado.setPosition(rs.getString("Position"));
            e.setName(rs.getString("team"));
            jugadorBuscado.setTeam(e);
            Jugador.add(jugadorBuscado);
        }
        rs.close();
        st.close();
        return Jugador;
    }

    public List selectJugadorPosicion(String Posicion) throws SQLException {
        List<Jugador> Jugador = new ArrayList<>();
        String query = "select * from player where Position like '%" + Posicion + "%';";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        Jugador jugadorBuscado = new Jugador();
        Equipo e = new Equipo();
        while (rs.next()) {
            jugadorBuscado = new Jugador();
            jugadorBuscado.setName(rs.getString("name"));
            jugadorBuscado.setBirth(rs.getDate("birth").toLocalDate());
            jugadorBuscado.setNbaskets(rs.getInt("nbaskets"));
            jugadorBuscado.setNassists(rs.getInt("nassists"));
            jugadorBuscado.setNrebounds(rs.getInt("nrebounds"));
            jugadorBuscado.setPosition(rs.getString("Position"));
            e.setName(rs.getString("team"));
            jugadorBuscado.setTeam(e);
            Jugador.add(jugadorBuscado);

        }
        rs.close();
        st.close();
        return Jugador;
    }

    public List selectJugadorPostFecha(int dia, int mes, int ano) throws SQLException {
        List<Jugador> Jugador = new ArrayList<>();
        String query = "select * from player where birth > '" + ano + "-" + dia + "-" + mes + "'" + ";";
        System.out.println(query);
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        Jugador jugadorBuscado = new Jugador();
        Equipo e = new Equipo();
        while (rs.next()) {
            jugadorBuscado = new Jugador();
            jugadorBuscado.setName(rs.getString("name"));
            jugadorBuscado.setBirth(rs.getDate("birth").toLocalDate());
            jugadorBuscado.setNbaskets(rs.getInt("nbaskets"));
            jugadorBuscado.setNassists(rs.getInt("nassists"));
            jugadorBuscado.setNrebounds(rs.getInt("nrebounds"));
            jugadorBuscado.setPosition(rs.getString("Position"));
            e.setName(rs.getString("team"));
            jugadorBuscado.setTeam(e);
            Jugador.add(jugadorBuscado);

        }
        rs.close();
        st.close();
        return Jugador;
    }

    public List selectPosicionAvgMaxMin() throws SQLException {
        List Jugador = new ArrayList<>();
        String query = "select position, avg(nbaskets), max(nbaskets), min(nbaskets), avg(nassists), max(nassists), min(nassists), avg(nrebounds), max(nrebounds), min(nrebounds) from player group by position;";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        ArrayList Linea = new ArrayList<>();

        while (rs.next()) {
            Linea = new ArrayList<>();
            Linea.add(rs.getString("Position"));
            Linea.add(rs.getInt("avg(nbaskets)"));
            Linea.add(rs.getInt("max(nbaskets)"));
            Linea.add(rs.getInt("min(nbaskets)"));
            Linea.add(rs.getInt("avg(nassists)"));
            Linea.add(rs.getInt("max(nassists)"));
            Linea.add(rs.getInt("min(nassists)"));
            Linea.add(rs.getInt("avg(nrebounds)"));
            Linea.add(rs.getInt("max(nrebounds)"));
            Linea.add(rs.getInt("min(nrebounds)"));
            Jugador.add(Linea);
        }
        rs.close();
        st.close();
        return Jugador;
    }

    public List RankingsJugador() throws SQLException {
        List Jugador = new ArrayList<>();
        String query = "select name,  nbaskets from player order by nbaskets DESC;";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        ArrayList Linea = new ArrayList<>();
        int i = 0;
        while (rs.next()) {
            Linea = new ArrayList<>();
            Linea.add(i);
            Linea.add(rs.getString("name"));
            Linea.add(rs.getInt("nbaskets"));
            Jugador.add(Linea);
            i++;
        }
        rs.close();
        st.close();
        return Jugador;
    }

    public List RankingsJugadorBusqueda(String Nombre) throws SQLException {
        List Jugador = new ArrayList<>();
        String query = "select name,  nbaskets from player order by nbaskets DESC;";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        ArrayList Linea = new ArrayList<>();
        int i = 0;
        while (rs.next()) {
            if (rs.getString("name").equalsIgnoreCase(Nombre)) {
                Linea = new ArrayList<>();
                Linea.add(i);
                Linea.add(rs.getString("name"));
                Linea.add(rs.getInt("nbaskets"));
                Jugador.add(Linea);
            }
            i++;
        }
        rs.close();
        st.close();
        return Jugador;
    }

    public List EquiposPorLocalidad(String Nombre) throws SQLException {
        List<Equipo> equipos = new ArrayList<>();
        String query = "select * from team where city like '" + Nombre + "';";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Equipo c = new Equipo();
            c.setName(rs.getString("name"));
            c.setCity(rs.getString("city"));
            c.setCreation(rs.getDate("creation").toLocalDate());
            equipos.add(c);
        }
        rs.close();
        st.close();
        return equipos;
    }

    public List<Jugador> JugadorSelectEquipo(String Equipo) throws SQLException {
        List<Jugador> jugadores = new ArrayList<>();
        String query = "select * from player where team like '"+Equipo+"';";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Jugador c = new Jugador();
            Equipo e = new Equipo();
            c.setName(rs.getString("name"));
            c.setBirth(rs.getDate("birth").toLocalDate());
            c.setNbaskets(rs.getInt("nbaskets"));
            c.setNassists(rs.getInt("nassists"));
            c.setNrebounds(rs.getInt("nrebounds"));
            c.setPosition(rs.getString("Position"));
            e.setName(rs.getString("team"));
            c.setTeam(e);
            jugadores.add(c);
        }
        rs.close();
        st.close();
        return jugadores;
    }
    public List<Jugador> JugadorSelectEquipoPosicion(String Equipo, String Posicion) throws SQLException {
        List<Jugador> jugadores = new ArrayList<>();
        String query = "select * from player where team like '"+Equipo+"' and Position like '"+Posicion+"';";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Jugador c = new Jugador();
            Equipo e = new Equipo();
            c.setName(rs.getString("name"));
            c.setBirth(rs.getDate("birth").toLocalDate());
            c.setNbaskets(rs.getInt("nbaskets"));
            c.setNassists(rs.getInt("nassists"));
            c.setNrebounds(rs.getInt("nrebounds"));
            c.setPosition(rs.getString("Position"));
            e.setName(rs.getString("team"));
            c.setTeam(e);
            jugadores.add(c);
        }
        rs.close();
        st.close();
        return jugadores;
    }

    public void conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/basket";
        String usr = "root";
        String pass = "";
        conexion = DriverManager.getConnection(url, usr, pass);
    }

    public void desconectar() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
}
