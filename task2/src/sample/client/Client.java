package sample.client;

import sample.Planet;

import java.io.*;
import java.net.Socket;

import static sample.OperationCodes.*;


public class Client {
    private Socket socket = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    ObjectInputStream  objectInputStream;
    public Client(String ip, int port) throws IOException
    {
        socket = new Socket(ip, port);
        in = new DataInputStream(socket.getInputStream( ));
        out = new DataOutputStream(socket.getOutputStream());
        InputStream inputStream = socket.getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);
    }
    public Planet getAll() throws IOException, ClassNotFoundException {
        out.writeInt(GET_ALL);
        return(Planet)objectInputStream.readObject();
    }
    public void updatePlanetInfo(Integer integer, int code, String name, String newTemp, String newAtm, String newLife, String newName) throws IOException {
        out.writeInt(UPDATE_PLANET);
        out.writeInt(code);
        out.writeInt(Integer.parseInt(newName));
        out.writeInt(Integer.parseInt(newTemp));
        out.writeInt(Integer.parseInt(newAtm));
        out.writeInt(Integer.parseInt(newLife));

    }
    public void updateGalactic(String newName, String newPlanets) throws IOException {
        out.writeInt(UPDATE_GALACTIC);
        out.writeInt(Integer.parseInt(newName));
        out.writeInt(Integer.parseInt(newPlanets));

    }
    public void updateSatelite(Integer rad, Integer integer, int newRad, String newName, String newDistance) throws IOException {
        out.writeInt(UPDATE_SATELITE);
        out.writeInt(newRad);
        out.writeUTF(newName);
        out.writeUTF(newDistance);
    }
    public void addPlanetInfo(int code, String name) throws IOException {
        out.writeInt(ADD_PLANET);
        out.writeInt(code);
        out.writeUTF(name);
    }
    public void addGalactic(String name, String planetts) throws IOException {
        out.writeInt(ADD_GALACTIC);
        out.writeInt(Integer.parseInt(name));
        out.writeUTF(planetts);
    }

    public void addSatelite(Integer rad, Integer integer, int newRad, String newName, String newDistance) throws IOException {
        out.writeInt(ADD_SATELITE);
        out.writeInt(rad);
        out.writeInt(newRad);
        out.writeUTF(newName);
        out.writeUTF(newDistance);
    }
    public void deletePlanetInfo(int code) throws IOException {
        out.writeInt(DELETE_PLANET);
        out.writeInt(code);
    }
    public void deleteSatelite(int airCode, int code) throws IOException {
        out.writeInt(DELETE_SATELITE);
        out.writeInt(airCode);
        out.writeInt(code);
    }

    public void deleteGalactic(Integer valueOf) throws IOException {
        out.writeInt(DELETE_GALACTIC);
        out.writeInt(valueOf);

    }
    public void addSatelite(Integer valueOf, Integer valueOf1, String name, String distance) {
    }
}
