package sample.dao;

import sample.Planet;

public interface DAO {
    Planet getAll();
    void updatePlanetInfo(int code, int newCode, String newName);
    void updateSatelite(int airCode, int code, int newCode, String newFrom, String newTo);
    void addPlanetInfo(int code, String s, String temp, String atm, String life, String name);
    void updatePlanetInfo(int code, String s, String temp, String atm, String life, String name);
    void addSatelite(int airCode, int code, String from, String to);
    void deletePlanetInfo(int code);
    void deleteSatelite(String name, int airCode, int code);

    void updatePlanet(String name, int code, int newCode, String newAtm, String newName, int newSat);

    void addPlanet(int i, String s, int code, String atm, String life, String name);

    void deletePlanet(int code);

    void addGalactic(int name, int planetts);

    void addGalactic(String name, String planetts);

    void deleteGalactic(int name);

    void deleteGalactic(String name);

    void updateGalactic(int newRad, String newName, int newDistance);

    void updateGalactic(String newName, int newDistance);

    void addSatelite(String name, int rad, int distance);

    void updatePlanetInfo(int code, int rad, String newName, int temp, String atm, String life, String satel);

    void updateSatelite(String newName, int newRad, int newDistance);
}
