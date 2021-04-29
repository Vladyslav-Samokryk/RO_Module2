package sample.dao;

import sample.Planet;
import sample.Satelite;

public interface DAO {
    Planet getAll();
    void updatePlanetInfo(int code, int newCode, String newName);
    void updateSatelite(int airCode, int code, int newCode, String newFrom, String newTo);
    void addPlanetInfo(int code, String name);

    void updatePlanetInfo(int code, int rad, String newName, int temp, String atm, String life, String satel);

    void updateSatelite(String newName, int newRad, int newDistance);

    void addPlanetInfo(int code, String s, String temp, String atm, String life, String name);

    void addSatelite(int airCode, int code, String from, String to);
    void deletePlanetInfo(int code);
    void deleteSatelite(int airCode, int code);

    void addSatelite(Satelite satelite);

    void addSatelite(int code, String name, int distance);

    void addSatelite();

    void deleteSatelite(String name, int rad, int distance);
}
