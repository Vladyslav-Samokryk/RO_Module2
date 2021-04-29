package sample.client;

import sample.Planet;
import sample.server.RemoteServer;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static sample.OperationCodes.ADD_GALACTIC;
import static sample.OperationCodes.DELETE_GALACTIC;


public class Client {
    RemoteServer server = null;
    public Client() throws RemoteException, NotBoundException, MalformedURLException {
        String url = "//localhost:65000/Server";
        server = (RemoteServer) Naming.lookup(url);
        System.out.println("RMI object found");
    }
    public Planet getAll() throws RemoteException{
        return server.getAll();
    }
    public void updatePlanetInfo(int code,  String newTemp, String newAtm, String newLife, String newName) throws RemoteException  {
        server.updatePlanetInfo(code, newName, newAtm, newLife, newTemp);
    }
    public void updateGalactic(String newName, String newPlanets) throws IOException {
        server.updateGalactic(newName, newPlanets);
    }

    public void updateSatelite(Integer rad, Integer integer, int newRad, String newName, String newDistance) throws RemoteException {
        server.updateSatelite(rad, integer, newRad, newName, newDistance);
    }
    public void addPlanetInfo(int code, String name)  throws RemoteException {
        server.addPlanetInfo(code, name);
    }
    public void addGalactic(String name, String planetts) throws IOException {
        server.addGalactic(name, planetts);
    }
    public void addSatelite(Integer rad, Integer integer, int newRad, String newName, String newDistance) throws RemoteException {
        server.addSatelite(rad, newRad, newName, newDistance);
    }
    public void deletePlanetInfo(int code) throws RemoteException{
        server.deletePlanetInfo(code);
    }
    public void deleteSatelite(int airCode, int code) throws RemoteException {
        server.deleteSatelite(airCode, code);
    }
    public void deleteGalactic(Integer valueOf) throws IOException {
        server.deleteSatelite(valueOf);
    }
    public void addSatelite(Integer valueOf, Integer valueOf1, String name, String distance) {
    }

    public void updatePlanet(Integer valueOf, Integer valueOf1, String newName, String newTemp, String newAtm, String newLife, String newSat) {
    }

    public void deletePlanet(Integer valueOf) {
    }
}
