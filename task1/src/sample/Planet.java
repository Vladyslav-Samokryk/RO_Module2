package sample;

import java.util.ArrayList;

public class Planet {
    private ArrayList<PlanetInfo> planets = new ArrayList<>();
    private String name;
    private int distance;

    public void addPlanetInfo(int rad, String satel, int temp, String life, String name, String atm){
        planets.add(new PlanetInfo(rad, name, temp, atm, life, satel));
    }
    public void addPlanetInfo(PlanetInfo planetInfo){ planets.add(planetInfo);}
    public PlanetInfo getPlanetInfo(int code){
        for(int i = 0;i  < planets.size(); i++){
            if(planets.get(i).code == code){
                return planets.get(i);
            }
        }
        return null;
    }
    public ArrayList<PlanetInfo> getplanets(){return planets;}
    public int countPlanets(){
        return planets.size();
    }
    public void deletePlanetInfo(int code) throws Exception{
        PlanetInfo airplanettToDelete = getPlanetInfo(code);
        if(airplanettToDelete == null){
            throw new Exception("PlanetInfo doesnt exist");
        }
        planets.remove(airplanettToDelete);
    }
    public void addSatelite(int code, String from, String to, int airplanettCode) throws Exception{
        PlanetInfo planetInfo = getPlanetInfo(code);
        if(planetInfo == null){
            throw new Exception("PlanetInfo doesnt exist");
        }
        ArrayList<Satelite> satelites= planetInfo.getSatelites();
        for(int i = 0; i < satelites.size(); i++){
            if(satelites.get(i).code == code){
                throw new Exception("This satelite has already exist");
            }
        }
        Satelite satelite = new Satelite(code, name, distance);
        planetInfo.addSatelite(satelite);
    }

    public void getGalactic(String nameG, String planets) {
    }
}
