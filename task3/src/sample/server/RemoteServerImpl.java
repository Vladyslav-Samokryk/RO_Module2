package sample.server;

import sample.Planet;
import sample.dao.ConcreteDAO;
import sample.dao.DAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class RemoteServerImpl extends UnicastRemoteObject implements RemoteServer {

    DAO dao;
    public RemoteServerImpl() throws RemoteException, SQLException, ClassNotFoundException {
        super();
        dao = new ConcreteDAO();
    }


    public Planet getAll(){
        Planet planet = dao.getAll();
        return planet;
    }

    public void updatePlanetInfo(int code, int newCode, String newName) {
        dao.updatePlanetInfo(code, newCode, newName);
    }
    public void updateSatelite(int airCode, int code, int newCode, String newFrom, String newTo)  {
        dao.updateSatelite(airCode, code, newCode, newFrom, newTo);
    }


    public void addPlanetInfo(int code, String name)  {
        dao.addPlanetInfo(code, name);
    }
    public void addSatelite(int code,String name,int distance)  {
        dao.addSatelite(code, name, distance);
    }
    public void deletePlanetInfo(int code) {
        dao.deletePlanetInfo(code);
    }

    public void deleteSatelite(int airCode, int code)  {
        dao.deleteSatelite(airCode, code);
    }

    @Override
    public void updatePlanetInfo(int code, String newName, String newAtm, String newLife, String newTemp) throws RemoteException {

    }

    @Override
    public void updateGalactic(String newName, String newPlanets) {

    }

    @Override
    public void addGalactic(String name, String planetts) {

    }

    @Override
    public void deleteSatelite(Integer valueOf) {

    }

    @Override
    public void addSatelite(Integer rad, int newRad, String newName, String newDistance) {

    }


}
