package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Planet implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<PlanetInfo> planets = new ArrayList<>();
    private String name;


    public void addPlanetInfo(int rad, String s, int code, String atm, String life, String name){
        planets.add(new PlanetInfo(code, name));
    }
    public void addPlanetInfo(PlanetInfo planet){ planets.add(planet);}
    public PlanetInfo getAirCompany(int code){
        for(int i = 0;i  < planets.size(); i++){
            if(planets.get(i).code == code){
                return planets.get(i);
            }
        }
        return null;
    }
    public ArrayList<PlanetInfo> getaircompanies(){return planets;}
    public int countPlanets(){
        return planets.size();
    }
    public void deletePlanetInfo(int code) throws Exception{
        PlanetInfo aircompanyToDelete = getAirCompany(code);
        if(aircompanyToDelete == null){
            throw new Exception("PlanetInfo doesnt exist");
        }
        planets.remove(aircompanyToDelete);
    }
    public void addSatelite(int code, String from, String to, int aircompanyCode, int distance) throws Exception{
        PlanetInfo planet = getAirCompany(code);
        if(planet == null){
            throw new Exception("PlanetInfo doesnt exist");
        }
        ArrayList<Satelite> flights= planet.getSatelites();
        for(int i = 0; i < flights.size(); i++){
            if(flights.get(i).code == code){
                throw new Exception("This flight has already exist");
            }
        }
        Satelite flight = new Satelite(code, name, distance);
        planet.addSatelite(flight);
    }

    public ArrayList<PlanetInfo> getPlanetInfo() {
        return null;
    }

    public void getGalactic(String nameG, String planets) {
    }

    public void addSatelite(Satelite satelite) {
    }


}
