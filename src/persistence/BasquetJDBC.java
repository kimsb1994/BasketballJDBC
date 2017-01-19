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
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, e.getName());
        ps.setString(2, e.getCity());
        ps.setDate(3, java.sql.Date.valueOf(e.getCreation()));
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
        ps.setString(1,j.getName());
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
        String insert = "update into player values (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1,j.getName());
        ps.setDate(2, java.sql.Date.valueOf(j.getBirth()));
        ps.setInt(3, j.getNbaskets());
        ps.setInt(4, j.getNassists());
        ps.setInt(5, j.getNrebounds());
        ps.setString(6, j.getPosition());
        ps.setString(7, j.getTeam().getName());
        ps.executeUpdate();
        ps.close();
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
