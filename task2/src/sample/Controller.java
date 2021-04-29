package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.client.Client;
import sample.dao.ConcreteDAO;
import sample.dao.DAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {
    @FXML
    private TreeView treeView;
    @FXML
    private Button addPlanett;
    @FXML
    private Button addSatelite;
    @FXML
    private Button updatePlanett;
    @FXML
    private Button updateSatelite;
    @FXML
    private Button deletePlanett;
    @FXML
    private Button deleteSatelite;
    @FXML
    private Button deleteGalactic;
    @FXML
    private Button addGalactic;
    @FXML
    private Button updateGalactic;
    @FXML
    private Button save;
    @FXML
    private TextField codePlanett;
    @FXML
    private TextField namePlanett;
    @FXML
    private TextField tempPlanett;
    @FXML
    private TextField atmPlanett;
    @FXML
    private TextField lifePlanett;
    @FXML
    private TextField satelPlanett;
    @FXML
    private TextField raddSatelite;
    @FXML
    private TextField fromSatelite;
    @FXML
    private TextField distanceSatelite;
    @FXML
    private TextField nameGalactic;
    @FXML
    private TextField planetsGalactic;
    @FXML
    private Label information;

    DAO dao;
    Client client;
    @FXML
    public void initialize() {
        try {
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
                public void updatePlanetInfo(String name, int code, int newCode, String newAtm, String newName, int newSat) {

                }

                @Override
                public void addPlanetInfo(int i, String s, int code, String atm, String life, String name) {

                }

                @Override
                public void deletePlanetInfo(int code) {

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
            };

            client = new Client("localhost", 65000);
            printToTreeView();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    public void printToTreeView() throws IOException, ClassNotFoundException {
        treeView.setRoot(null);
        treeView.setRoot(null);
        ArrayList<PlanetInfo> planets = dao.getAll().getplanets();
        //ArrayList<TreeItem<String>> planetsTree = new ArrayList<>();
        TreeItem<String> root = new TreeItem<>("Galactica");

        for(int i = 0; i < planets.size(); i++){
            String info;
            info = "Code: " + planets.get(i).code + " Name: " + planets.get(i).name + "Temperature" + planets.get(i).temp + "Atmosphere" + planets.get(i).atm + "Life" + planets.get(i).life + "Satelite" + planets.get(i).satel;
            ArrayList<Satelite> satelites = planets.get(i).getSatelites();
            TreeItem<String> planetInfo = new TreeItem<>(info);
            for(int j = 0; j < satelites.size(); j++){
                String flightInfo = satelites.get(j).code + " " + satelites.get(j).name + satelites.get(j).distance;
                TreeItem<String> satelite = new TreeItem<>(flightInfo);
                planetInfo.getChildren().add(satelite);
            }
            // planetsTree.add(planetInfo);
            root.getChildren().add(planetInfo);
        }
        treeView.setRoot(root);
    }
    @FXML
    private void addPlanett(ActionEvent event) throws IOException, ClassNotFoundException {
        String code = codePlanett.getText();
        String name = namePlanett.getText();
        String temp = tempPlanett.getText();
        String atm = atmPlanett.getText();
        String life = lifePlanett.getText();
        String satel = satelPlanett.getText();
        //PlanetInfo planetInfo = new PlanetInfo(Integer.valueOf(code), name);
        dao.addPlanetInfo(Integer.valueOf(code), name, temp, atm, life, satel);
        printToTreeView();
    }
    @FXML
    private void addSatelite(ActionEvent event) throws IOException, ClassNotFoundException {
        String rad = raddSatelite.getText();
        String name = fromSatelite.getText();
        String distance = distanceSatelite.getText();
        TreeItem<String> selectedItem = (TreeItem<String>)treeView.getSelectionModel().getSelectedItem();
        if(selectedItem == null){
            information.setText("Not selected planetInfo");
            return;
        }
        String codePlanetInfo = selectedItem.getValue().split(" ")[1];
        //PlanetInfo planetInfo = xmlWorker.findPlanetInfoByCode(Integer.valueOf(codePlanetInfo));
        //xmlWorker.addSatelite(Integer.valueOf(codePlanetInfo), new Satelite(Integer.valueOf(code), from, to));
        client.addSatelite(Integer.valueOf(codePlanetInfo), Integer.valueOf(rad), name, distance);
        printToTreeView();

    }
    @FXML
    private void addGalactic(ActionEvent event) throws IOException, ClassNotFoundException {
        String name = nameGalactic.getText();
        String planetts = planetsGalactic.getText();
        TreeItem<String> selectedItem = (TreeItem<String>)treeView.getSelectionModel().getSelectedItem();
        if(selectedItem == null){
            information.setText("Not selected planetInfo");
            return;
        }
        String codePlanetInfo = selectedItem.getValue().split(" ")[1];
        //PlanetInfo planetInfo = xmlWorker.findPlanetInfoByCode(Integer.valueOf(codePlanetInfo));
        //xmlWorker.addSatelite(Integer.valueOf(codePlanetInfo), new Satelite(Integer.valueOf(code), from, to));

        client.addGalactic(name, planetts);
        printToTreeView();
    }
    @FXML
    private void updateGalactic(ActionEvent event) throws IOException, ClassNotFoundException {
        String newName = nameGalactic.getText();
        String newPlanets = planetsGalactic.getText();

        TreeItem<String> selectedItem = (TreeItem<String>)treeView.getSelectionModel().getSelectedItem();
        if(selectedItem == null){
            information.setText("Not selected planetInfo");
            return;
        }
        String codePlanetInfo = selectedItem.getValue().split(" ")[1];
        String namePlanetInfo = selectedItem.getValue().split(" ")[3];
        //PlanetInfo planetInfo = xmlWorker.findPlanetInfoByCode(Integer.valueOf(codePlanetInfo));
        if(newName.equals("")){
            newName = codePlanetInfo;
        }
        if(newPlanets.equals("")){
            newPlanets = namePlanetInfo;
        }
        client.updateGalactic(newName, newPlanets);
        printToTreeView();
    }
    @FXML
    private void updatePlanett(ActionEvent event) throws IOException, ClassNotFoundException {
        String newCode = codePlanett.getText();
        String newName = namePlanett.getText();
        String newTemp = tempPlanett.getText();
        String newAtm = atmPlanett.getText();
        String newLife = lifePlanett.getText();
        String newSat = satelPlanett.getText();
        TreeItem<String> selectedItem = (TreeItem<String>)treeView.getSelectionModel().getSelectedItem();
        if(selectedItem == null){
            information.setText("Not selected planetInfo");
            return;
        }
        String codePlanetInfo = selectedItem.getValue().split(" ")[1];
        String namePlanetInfo = selectedItem.getValue().split(" ")[3];
        //PlanetInfo planetInfo = xmlWorker.findPlanetInfoByCode(Integer.valueOf(codePlanetInfo));
        if(newCode.equals("")){
            newCode = codePlanetInfo;
        }
        if(newName.equals("")){
            newName = namePlanetInfo;
        }
        client.updatePlanetInfo(Integer.valueOf(codePlanetInfo), Integer.valueOf(newCode), newName, newTemp, newAtm, newLife, newSat);
        printToTreeView();
    }
    @FXML
    public void updateSatelite(ActionEvent event) throws IOException, ClassNotFoundException {
        String newRad = raddSatelite.getText();
        String newName = fromSatelite.getText();
        String newDistance = distanceSatelite.getText();
        TreeItem<String> selectedItem =(TreeItem<String>)treeView.getSelectionModel().getSelectedItem();
        if(selectedItem == null){
            information.setText("Not selected planetInfo");
            return;
        }
        String codePlanetInfo = selectedItem.getParent().getValue().split(" ")[1];
        String codeSatelite = selectedItem.getValue().split(" ")[0];
        String fromSatelite = selectedItem.getValue().split(" ")[1];
        String toSatelite = selectedItem.getValue().split(" ")[3];
        if(newRad.equals("")){
            newRad = codeSatelite;
        }
        if(newName.equals("")){
            newName = fromSatelite;
        }
        if(newDistance.equals("")){
            newDistance = toSatelite;
        }
        client.updateSatelite(Integer.valueOf(codePlanetInfo), Integer.valueOf(codeSatelite),
                Integer.valueOf(newRad), newName, newDistance);
        printToTreeView();
    }
    @FXML
    public void deleteGalactic(ActionEvent event) throws IOException, ClassNotFoundException {
        TreeItem<String> selectedItem =(TreeItem<String>)treeView.getSelectionModel().getSelectedItem();
        if(selectedItem == null){
            information.setText("Not selected planetInfo");
            return;
        }
        String codePlanetInfo = selectedItem.getValue().split(" ")[1];
        client.deleteGalactic(Integer.valueOf(codePlanetInfo));
        printToTreeView();
    }
    @FXML
    public void deletePlanett(ActionEvent event) throws IOException, ClassNotFoundException {
        TreeItem<String> selectedItem =(TreeItem<String>)treeView.getSelectionModel().getSelectedItem();
        if(selectedItem == null){
            information.setText("Not selected planetInfo");
            return;
        }
        String codePlanetInfo = selectedItem.getValue().split(" ")[1];
        client.deletePlanetInfo(Integer.valueOf(codePlanetInfo));
        printToTreeView();
    }
    @FXML
    public void deleteSatelite(ActionEvent event) throws IOException, ClassNotFoundException {
        TreeItem<String> selectedItem =(TreeItem<String>)treeView.getSelectionModel().getSelectedItem();
        if(selectedItem == null){
            information.setText("Not selected planetInfo");
            return;
        }
        String codePlanetInfo = selectedItem.getParent().getValue().split(" ")[1];
        String codeSatelite = selectedItem.getValue().split(" ")[0];
        client.deleteSatelite(Integer.valueOf(codePlanetInfo), Integer.valueOf(codeSatelite));
        printToTreeView();
    }

}
