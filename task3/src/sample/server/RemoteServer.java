package sample.server;

import sample.Planet;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteServer extends Remote {
    Planet getAll() throws RemoteException;

    void updateSatelite(int airCode, int code, int newCode, String newFrom, String newTo) throws RemoteException;
    void addPlanetInfo(int rad, String name) throws RemoteException;
    void addSatelite(int code, String name,int distance) throws RemoteException;
    void deletePlanetInfo(int code) throws RemoteException;
    void deleteSatelite(int airCode, int code) throws RemoteException;
    void updatePlanetInfo(int code, String newName, String newAtm, String newLife, String newTemp) throws RemoteException;;

    void updateGalactic(String newName, String newPlanets);

    void addGalactic(String name, String planetts);

    void deleteSatelite(Integer valueOf);

    void addSatelite(Integer rad, int newRad, String newName, String newDistance);
}
