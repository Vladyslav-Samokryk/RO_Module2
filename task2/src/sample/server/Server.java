package sample.server;

import sample.Planet;
import sample.dao.ConcreteDAO;
import sample.dao.DAO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server {
    private ServerSocket server = null;
    private Socket socket = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    ObjectOutputStream objectOutputStream;
    OutputStream outputStream;
    DAO dao;
    public Server(){

    }
    public void start(int port) throws IOException, SQLException, ClassNotFoundException {
        dao = new ConcreteDAO() {
            @Override
            public void updatePlanetInfo(int code, int newCode, String newName) {

            }

            @Override
            public void updateSatelite(int airCode, int code, int newCode, String newFrom, String newTo) {

            }

            @Override
            public void updatePlanetInfo(int code, String s, String temp, String atm, String life, String name) {

            }

            @Override
            public void updatePlanet(String name, int code, int newCode, String newAtm, String newName, int newSat) {

            }

            @Override
            public void addPlanet(int i, String s, int code, String atm, String life, String name) {

            }

            @Override
            public void deletePlanet(int code) {

            }

            @Override
            public void addGalactic(int name, int planetts) {

            }

            @Override
            public void addGalactic(String name, String planetts) {

            }

            @Override
            public void deleteGalactic(int name) {

            }

            @Override
            public void deleteGalactic(String name) {

            }

            @Override
            public void updateGalactic(int newRad, String newName, int newDistance) {

            }

            @Override
            public void updateGalactic(String newName, int newDistance) {

            }

            @Override
            public void addSatelite(String name, int rad, int distance) {

            }

            @Override
            public void updateSatelite(String newName, int newRad, int newDistance) {

            }

            @Override
            public void updatePlanetInfo(String name, int code, int newCode, String newAtm, String newName, int newSat) {

            }

            @Override
            public void addPlanetInfo(int i, String s, int code, String atm, String life, String name) {

            }
        };
        server = new ServerSocket(port);
        socket = server.accept();
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        outputStream = socket.getOutputStream();
        objectOutputStream = new ObjectOutputStream(outputStream);
        System.out.println("running...");
        while (true) {
            int code = in.readInt();
            switch(code){
                case 0:{
                    getAll();
                    break;
                }
                case 1:{
                    updatePlanet();
                    break;
                }
                case 2:{
                    updateSatelite();
                    break;
                }
                case 3:{
                    updateGalactic();
                    break;
                }
                case 4:{
                    addPlanet();
                    break;
                }
                case 5:{
                    addSatelite();
                    break;
                }
                case 6:{
                    addGalactic();
                    break;
                }
                case 7:{
                    deletePlanet();
                    break;
                }
                case 8:{
                    deleteSatelite();
                    break;
                }case 9:{
                    deleteGalactic();
                    break;
                }

            }
        }
    }
    public void getAll() throws IOException {
        Planet airport = dao.getAll();
        objectOutputStream.writeObject(airport);
    }
    public void updatePlanet() throws IOException {
        int newTemp = in.readInt();
        int newCode = in.readInt();
        String newName = in.readUTF();
        String newAtm = in.readUTF();
        String newLife = in.readUTF();
        int newSat = in.readInt();
        dao.updatePlanet(newName, newCode, newTemp, newAtm, newLife, newSat);
    }

    public void updateSatelite() throws IOException {
        int newRad = in.readInt();
        int newDistance = in.readInt();
        String newName = in.readUTF();
        dao.updateSatelite(newName, newRad, newDistance);

    }
    public void updateGalactic() throws IOException {
        int newDistance = in.readInt();
        String newName = in.readUTF();
        dao.updateGalactic(newName, newDistance);
    }
    public void addPlanet() throws IOException {
        int code = in.readInt();
        String name = in.readUTF();
        int temp = in.readInt();
        String atm = in.readUTF();
        String life = in.readUTF();
        String satel = in.readUTF();
        dao.addPlanet(code, name, temp, atm, life, satel);
    }

    public void addSatelite() throws IOException {
        int rad = in.readInt();
        int distance = in.readInt();
        String name = in.readUTF();
        dao.addSatelite(name, rad, distance);

    }
    public void addGalactic() throws IOException {
        String name = in.readUTF();
        String planetts = in.readUTF();
        dao.addGalactic(name, planetts);
    }
    public void deletePlanet() throws IOException {
        int code = in.readInt();
        dao.deletePlanet(code);
    }

    public void deleteSatelite() throws IOException {
        int rad = in.readInt();
        int distance = in.readInt();
        String name = in.readUTF();
        dao.deleteSatelite(name, rad, distance);
    }
    public void deleteGalactic() throws IOException {
        String name = in.readUTF();
        dao.deleteGalactic(name);
    }
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Server server = new Server();
        server.start(65000);
    }

}
