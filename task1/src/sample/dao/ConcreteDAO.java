package sample.dao;

import sample.Planet;
import sample.Satelite;

import java.sql.*;

public class ConcreteDAO implements DAO {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/planet?serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "root";

    Connection conn = null;
    Statement stmt = null;
    public ConcreteDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
    }

    @Override
    public Planet getAll() {
        Planet planet = new Planet();
        try {

            stmt = conn.createStatement();
            String sql;
            sql = "SELECT *  FROM planetInfo";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int rad = rs.getInt("rad");
                String name = rs.getString("name");
                int temp = rs.getInt("temperature");
                String atm = rs.getString("Atmosphere");
                String life = rs.getString("Life");
                String satel = rs.getString("Satelites");
                planet.addPlanetInfo(rad, name, temp, atm, life, satel);
            }


            sql = "SELECT *  FROM satelite";
                rs = stmt.executeQuery(sql);
                while(rs.next()) {
                    int airCode = rs.getInt("Distance to Earth");
                    int code = rs.getInt("code");
                    String name = rs.getString("Name");
                    int distance = rs.getInt("distance");
                    planet.getPlanetInfo(airCode).addSatelite(new Satelite(code, name, distance));
                }
                    sql = "SELECT *  FROM galactic";
                    rs = stmt.executeQuery(sql);
                    while(rs.next()){
                        String nameG = rs.getString("Galactic name");
                        String planets = rs.getString("List of planets");
                        planet.getGalactic(nameG, planets);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planet;
    }

    @Override
    public void updatePlanetInfo(int code, int newCode, String newName) {
        try {
            stmt = conn.createStatement();
            String sql;
            sql = " update planetInfo set code=" + newCode + ", name=\"" + newName + "\" where code=" + code + ";";
            int status = stmt.executeUpdate(sql);
            if(status < 0){
                System.out.println("Error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSatelite(int airCode, int code, int newCode, String newFrom) {

    }

    @Override
    public void addPlanetInfo(int code, String name) {

    }

    @Override
    public void updateSatelite(int newRad, int newDistance) {

    }

    @Override
    public void updateSatelite(String newName, int newRad, int newDistance){
        try {
            stmt = conn.createStatement();
            String sql;
            sql = " update satelite set name=" + newName + ", Rad=\"" + newRad +
                    "\" where newDistance=" + newDistance+";";
            System.out.println(sql);
            int status = stmt.executeUpdate(sql);
            if(status < 0){
                System.out.println("Error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPlanetInfo(int code, String s, String temp, String atm, String life, String name) {
        try {
            stmt = conn.createStatement();
            String sql;
            sql = "insert into planetInfo values (" + code + ", \"" + name + temp+ ", \""+ atm+ ", \""+ life+ "\");";
            int status = stmt.executeUpdate(sql);
            if(status < 0){
                System.out.println("Error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addSatelite(int airCode, int name, String rad, String distance) {
        try {
            stmt = conn.createStatement();
            String sql;
            sql = "insert into satelite values (" + airCode + ", " + name + ",\"" + rad + "\",\"" + distance + "\");";
            int status = stmt.executeUpdate(sql);
            if(status < 0){
                System.out.println("Error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePlanetInfo(int code) {
        try {
            stmt = conn.createStatement();
            String sql;
            sql = "delete from planetInfo where code = " + code + ";";
            int status = stmt.executeUpdate(sql);
            if(status < 0){
                System.out.println("Error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSatelite(int airCode, int code) {
        try {
            stmt = conn.createStatement();
            String sql;
            sql = "delete from satelite where code = " + code + " and airCode=" + airCode + ";";
            int status = stmt.executeUpdate(sql);
            if(status < 0){
                System.out.println("Error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
