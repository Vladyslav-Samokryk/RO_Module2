package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.dao.ConcreteDAO;
import sample.dao.DAO;

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
    private TextField codeSatelite;
    @FXML
    private TextField fromSatelite;
    @FXML
    private TextField toSatelite;
    @FXML
    private TextField nameGalactic;
    @FXML
    private TextField planetsGalactic;
    @FXML
    private Label information;

    DAO dao;
    @FXML
    public void initialize() {
        try {
            dao = new ConcreteDAO();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        printToTreeView();


    }
    public void printToTreeView(){
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
    private void addPlanett(ActionEvent event) {
        String code = codePlanett.getText();
        String name = namePlanett.getText();
        String temp = tempPlanett.getText();
        String atm = atmPlanett.getText();
        String life = lifePlanett.getText();
        String satel = satelPlanett.getText();
        //PlanetInfo planetInfo = new PlanetInfo(Integer.valueOf(code), name);
        dao.addPlanetInfo(Integer.valueOf(code), name);
        printToTreeView();
    }
    @FXML
    private void addSatelite(ActionEvent event) {
        String code = codeSatelite.getText();
        String from = fromSatelite.getText();
        String to = toSatelite.getText();
        TreeItem<String> selectedItem = (TreeItem<String>)treeView.getSelectionModel().getSelectedItem();
        if(selectedItem == null){
            information.setText("Not selected planetInfo");
            return;
        }
        String codePlanetInfo = selectedItem.getValue().split(" ")[1];
        //PlanetInfo planetInfo = xmlWorker.findPlanetInfoByCode(Integer.valueOf(codePlanetInfo));
        //xmlWorker.addSatelite(Integer.valueOf(codePlanetInfo), new Satelite(Integer.valueOf(code), from, to));

        dao.addSatelite(Integer.valueOf(codePlanetInfo), Integer.valueOf(code), from, to);
        printToTreeView();

    }
    @FXML
    private void addGalactic(ActionEvent event) {
        String name = nameGalactic.getText();
        String planetts = planetsGalactic.getText();
        String to = toSatelite.getText();
        TreeItem<String> selectedItem = (TreeItem<String>)treeView.getSelectionModel().getSelectedItem();
        if(selectedItem == null){
            information.setText("Not selected planetInfo");
            return;
        }
        String codePlanetInfo = selectedItem.getValue().split(" ")[1];
        //PlanetInfo planetInfo = xmlWorker.findPlanetInfoByCode(Integer.valueOf(codePlanetInfo));
        //xmlWorker.addSatelite(Integer.valueOf(codePlanetInfo), new Satelite(Integer.valueOf(code), from, to));


    }
    @FXML
    private void updatePlanett(ActionEvent event) {
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
        dao.updatePlanetInfo(Integer.valueOf(codePlanetInfo), Integer.valueOf(newCode), newName);
        printToTreeView();
    }
    @FXML
    public void updateSatelite(ActionEvent event){
        String newCode = codeSatelite.getText();
        String newFrom = fromSatelite.getText();
        String newTo = toSatelite.getText();
        TreeItem<String> selectedItem =(TreeItem<String>)treeView.getSelectionModel().getSelectedItem();
        if(selectedItem == null){
            information.setText("Not selected planetInfo");
            return;
        }
        String codePlanetInfo = selectedItem.getParent().getValue().split(" ")[1];
        String codeSatelite = selectedItem.getValue().split(" ")[0];
        String fromSatelite = selectedItem.getValue().split(" ")[1];
        String toSatelite = selectedItem.getValue().split(" ")[3];
        if(newCode.equals("")){
            newCode = codeSatelite;
        }
        if(newFrom.equals("")){
            newFrom = fromSatelite;
        }
        if(newTo.equals("")){
            newTo = toSatelite;
        }
        dao.updateSatelite(Integer.valueOf(codePlanetInfo), Integer.valueOf(codeSatelite),
                Integer.valueOf(newCode), newFrom);
        printToTreeView();
    }
    @FXML
    public void deletePlanett(ActionEvent event){
        TreeItem<String> selectedItem =(TreeItem<String>)treeView.getSelectionModel().getSelectedItem();
        if(selectedItem == null){
            information.setText("Not selected planetInfo");
            return;
        }
        String codePlanetInfo = selectedItem.getValue().split(" ")[1];
        dao.deletePlanetInfo(Integer.valueOf(codePlanetInfo));
        printToTreeView();
    }
    @FXML
    public void deleteSatelite(ActionEvent event){
        TreeItem<String> selectedItem =(TreeItem<String>)treeView.getSelectionModel().getSelectedItem();
        if(selectedItem == null){
            information.setText("Not selected planetInfo");
            return;
        }
        String codePlanetInfo = selectedItem.getParent().getValue().split(" ")[1];
        String codeSatelite = selectedItem.getValue().split(" ")[0];
        dao.deleteSatelite(Integer.valueOf(codePlanetInfo), Integer.valueOf(codeSatelite));
        printToTreeView();
    }

    public void deleteGalactic(ActionEvent actionEvent) {
        TreeItem<String> selectedItem =(TreeItem<String>)treeView.getSelectionModel().getSelectedItem();
        if(selectedItem == null){
            information.setText("Not selected planetInfo");
            return;
        }
        String codePlanetInfo = selectedItem.getValue().split(" ")[1];
        dao.updateSatelite(Integer.valueOf(codePlanetInfo), Integer.valueOf(String.valueOf(deleteSatelite)));
        printToTreeView();
    }

    public void updateGalactic(ActionEvent actionEvent) {
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
        dao.updateSatelite(Integer.valueOf(codePlanetInfo), Integer.valueOf(String.valueOf(updateGalactic)),
                Integer.valueOf(newName), newPlanets);
        printToTreeView();
    }
}
