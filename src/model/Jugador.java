package model;

import java.time.LocalDate;
import java.util.Date;

public class Jugador {

    private String name;
    private LocalDate birth;
    private int nbaskets;
    private int nassists;
    private int nrebounds;
    private String Position;
    private Equipo team;   

    public Jugador() {
    }

    public Jugador(String name, LocalDate birth, int nbaskets, int nassists, int nrebounds, String Position, Equipo team) {
        this.name = name;
        this.birth = birth;
        this.nbaskets = nbaskets;
        this.nassists = nassists;
        this.nrebounds = nrebounds;
        this.Position = Position;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public int getNbaskets() {
        return nbaskets;
    }

    public void setNbaskets(int nbaskets) {
        this.nbaskets = nbaskets;
    }

    public int getNassists() {
        return nassists;
    }

    public void setNassists(int nassists) {
        this.nassists = nassists;
    }

    public int getNrebounds() {
        return nrebounds;
    }

    public void setNrebounds(int nrebounds) {
        this.nrebounds = nrebounds;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public Equipo getTeam() {
        return team;
    }

    public void setTeam(Equipo team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Jugador{" + "name=" + name + ", birth=" + birth + ", nbaskets=" + nbaskets + ", nassists=" + nassists + ", nrebounds=" + nrebounds + ", Position=" + Position + ", team=" + team.getName() + '}';
    }

}
