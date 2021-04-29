package sample.dao;

import sample.Planet;

public interface DAO {
    Planet getAll();
    void updatePlanetInfo(int code, int newCode, String newName);
    void updateSatelite(int airCode, int code, int newCode, String newFrom);
    void addPlanetInfo(int code, String name);

    void updateSatelite(int newRad, int newDistance);

    void updateSatelite(String newName, int newRad, int newDistance);

    void addPlanetInfo(int code, String s, String temp, String atm, String life, String name);

    void addSatelite(int airCode, int code, String from, String to);
    void deletePlanetInfo(int code);
    void deleteSatelite(int airCode, int code);
}
