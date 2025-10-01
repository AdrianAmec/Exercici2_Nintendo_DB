package com.project;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class ControllerDesktop implements Initializable{

    private JSONArray jsonInfoCharacters;
    private JSONArray jsonInfoConsoles;
    private JSONArray jsonInfoGames;

    String options[] = { "Characters","Games" ,"Consoles"};

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private AnchorPane container;
    @FXML
    private VBox vBoxR, vBoxL, yPane;
    @FXML
    private HBox vBoxC;
    @FXML
    private ScrollPane sPane;
    @FXML
    private ImageView gameImage;
    @FXML
    private Circle circle;


    @FXML
    private Text gameTitulo, gameInfo;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //choiceBox.getItems().clear();
        choiceBox.getItems().addAll(options);
        choiceBox.setValue(options[0]);

        try {
            // Obtenir el recurs del template .fxml
            // URL resource = this.getClass().getResource("/assets/layout_desktop.fxml");

            // Obtenir la llista


            //cambiar para q obtenga el resto
            URL jsonFileURL = getClass().getResource("/data/characters.json");
            Path path = Paths.get(jsonFileURL.toURI());
            String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            jsonInfoCharacters = new JSONArray(content);

            // Actualitza la UI amb els valors inicials de les estacions
            setOption();
            choiceBox.setOnAction((event) -> {

                if(choiceBox.getValue().equals(options[0])){
                    try {
                        setOption();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                
            });

            }        


        catch (Exception e) {
            // TODO: handle exception
        }
        
        
    
    }

    public void setTitle(String title) {
        this.gameTitulo.setText(title);
    }

    public void setInfo(String subtitle) {
        this.gameInfo.setText(subtitle);
    }

    public void setImatge(String imagePath) {
        try {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            this.gameImage.setImage(image);
        } catch (NullPointerException e) {
            System.err.println("Error loading image asset: " + imagePath);
            e.printStackTrace();
        }
    }

    public void setCircleColor(String color) {
        circle.setStyle("-fx-fill: " + color);
    }


    public void setOption()throws Exception{
         // Obtenir el recurs del template .fxml
        URL resource = this.getClass().getResource("/assets/listitem.fxml");

        // Esborrar la llista anterior
        yPane.getChildren().clear();

        // Generar la nova llista a partir de 'jsonInfo'
        for (int i = 0; i < jsonInfoCharacters.length(); i++) {
            // Obtenir l'objecte JSON individual (animal)
            JSONObject character = jsonInfoCharacters.getJSONObject(i);

            // Extreure la informació necessària del JSON
            String name = character.getString("name");
            String image = character.getString("image");

            // Carregar el template de 'listItem.fxml'
            FXMLLoader loader = new FXMLLoader(resource);
            Parent itemTemplate = loader.load();
            ControllerListItem itemController = loader.getController();

            // Assignar els valors als controls del template
            itemController.setTitle(name);
            itemController.setImatge("/data/images/"+image);
            itemController.setParentController(this);
            
            // Afegir el nou element a 'yPane'
            yPane.getChildren().add(itemTemplate);
        }
    }

    public void setCharacters(String object) throws Exception{
         // Obtenir el recurs del template .fxml
        //URL resource = this.getClass().getResource("/assets/listItem.fxml");


        // Generar la nova llista a partir de 'jsonInfo'
        JSONObject character=null;
        for (int i = 0; i < jsonInfoCharacters.length(); i++) {
            if(!jsonInfoCharacters.getJSONObject(i).getString("name").equals(object)){
                continue;
            }
            character = jsonInfoCharacters.getJSONObject(i);

        }
        // Extreure la informació necessària del JSON
        String name = character.getString("name");
        String color = character.getString("color");
        String game = character.getString("game");
        String image = character.getString("image");

        // Carregar el template de 'listItem.fxml'
        //FXMLLoader loader = new FXMLLoader(resource);
        //Parent itemTemplate = loader.load();
        //ControllerListItem itemController = loader.getController();

        // Assignar els valors als controls del template
        setTitle(name);
        setInfo(game);
        setImatge("/data/images/" + image);
        setCircleColor(color);

        // Afegir el nou element a 'yPane'
        //yPane.getChildren().add(itemTemplate);
    
    }

    public void accion(String name) throws Exception{
        if (choiceBox.getValue().equals(options[0])){
            setCharacters(name);
        }
    }
}