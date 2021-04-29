package sample;

import java.io.Serializable;
import java.util.ArrayList;

class PlanetInfo implements Serializable {
    public int code;
    public String name;
    public int temp;
    public String atm;
    public String life;
    public String satel;
    private ArrayList<Satelite> satelites = new ArrayList<>();
    public PlanetInfo(int temp, String satel){
        this.code = code;
        this.name = name;
        this.atm = atm;
        this.life = life;
        this.temp = temp;
        this.satel = satel;
    }
    public ArrayList<Satelite> getSatelites(){
        return satelites;
    }
    public void addSatelite(Satelite satelite){
        satelites.add(satelite);
    }
    public Satelite findSateliteByCode(int code){
        for(int i = 0; i  < satelites.size(); i++){
            if(satelites.get(i).code == code){
                return satelites.get(i);
            }
        }
        return null;
    }

}
