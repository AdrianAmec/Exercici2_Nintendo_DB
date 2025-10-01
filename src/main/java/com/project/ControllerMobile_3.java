package com.project;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EventListener;
import java.util.Objects;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class ControllerMobile_3 extends Controller implements Initializable {


    //cada Json en su propia variable
    private JSONArray jsonInfoCharacters;
    private JSONArray jsonInfoConsoles;
    private JSONArray jsonInfoGames;
    private String[] options = {"Characters","Games","Consoles"}; 
    private String opcion="";

    @FXML
    private ImageView img;

    @FXML 
    private Text textMobile3;

    @FXML
    private Text titulo,info;
    @FXML
    private Circle circle;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Obtenir el recurs del template .fxml
            // URL resource = this.getClass().getResource("/assets/layout_desktop.fxml");

            // Obtenir la llista


            //cambiar para q obtenga el resto
            //JSON Characters init

            for (String nombre : options) {
                URL jsonFileURL = getClass().getResource("/data/"+nombre.toLowerCase()+".json");
                Path path = Paths.get(jsonFileURL.toURI());
                String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
                if(nombre.equals(options[0])){
                    jsonInfoCharacters = new JSONArray(content);
                }
                if(nombre.equals(options[1])){
                    jsonInfoGames = new JSONArray(content);
                }

                if(nombre.equals(options[2])){
                    jsonInfoConsoles = new JSONArray(content);
                }
            }
    
            
        }catch(Exception e){
            System.out.println(e.getMessage());

        }


        
    }

    public void setCharacters(String nombre) throws Exception{
         // Obtenir el recurs del template .fxml
        //URL resource = this.getClass().getResource("/assets/listItem.fxml");


        // Generar la nova llista a partir de 'jsonInfo'
        JSONObject character=null;
        for (int i = 0; i < jsonInfoCharacters.length(); i++) {
            if(!jsonInfoCharacters.getJSONObject(i).getString("name").equals(nombre)){
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

    public void setConsoles(String nombre) throws Exception{
         // Obtenir el recurs del template .fxml
        //URL resource = this.getClass().getResource("/assets/listItem.fxml");

        

        // Generar la nova llista a partir de 'jsonInfo'
        JSONObject character=null;
        for (int i = 0; i < jsonInfoConsoles.length(); i++) {
            if(!jsonInfoConsoles.getJSONObject(i).getString("name").equals(nombre)){
                continue;
            }
            character = jsonInfoConsoles.getJSONObject(i);

        }
        //"name": "Nintendo Switch",
        //"date": "2017-3-3",
        //"procesador": "4xARM Cortex-A57",
        //"color": "black",
        //"units_sold": 70000000,
        //"image": "nintendo_switch.png"

        // Extreure la informació necessària del JSON
        String name = character.getString("name");
        String date = character.getString("date");
        String procesador = character.getString("procesador");
        String color = character.getString("color");
        int units_sold = character.getInt("units_sold");
        String image = character.getString("image");

        // Carregar el template de 'listItem.fxml'
        //FXMLLoader loader = new FXMLLoader(resource);
        //Parent itemTemplate = loader.load();
        //ControllerListItem itemController = loader.getController();

        // Assignar els valors als controls del template
        setTitle(name);
        String info = "Fecha de salida: "+date+"\nProcesador: "+procesador+"\nUnidades vendidas: "+units_sold;
        setInfo(info);
        setImatge("/data/images/" + image);
        setCircleColor(color);

        // Afegir el nou element a 'yPane'
        //yPane.getChildren().add(itemTemplate);
    
    }

    public void setGames(String nombre) throws Exception{
         // Obtenir el recurs del template .fxml
        //URL resource = this.getClass().getResource("/assets/listItem.fxml");

        

        // Generar la nova llista a partir de 'jsonInfo'
        JSONObject character=null;
        for (int i = 0; i < jsonInfoGames.length(); i++) {
            if(!jsonInfoGames.getJSONObject(i).getString("name").equals(nombre)){
                continue;
            }
            character = jsonInfoGames.getJSONObject(i);

        }
       //"name": "Super Mario Bros",
        //"year": 1985,
        //"type": "Plataformes",
        //"plot": "Super Mario Bros. és un icònic joc de plataformes creat per Nintendo. El jugador assumeix el paper de Mario o Luigi, i ha de recórrer nivells plens d'enemics i obstacles per rescatar la princesa Peach del malvat Bowser.",
        //"image": "game_smb.png"


        // Extreure la informació necessària del JSON
        String name = character.getString("name");
        int year = character.getInt("year");
        String type = character.getString("type");
        String plot = character.getString("plot");
        String image = character.getString("image");

        // Carregar el template de 'listItem.fxml'
        //FXMLLoader loader = new FXMLLoader(resource);
        //Parent itemTemplate = loader.load();
        //ControllerListItem itemController = loader.getController();

        // Assignar els valors als controls del template
        setTitle(name);
        String info = plot+"\nFecha de salida: "+year+"\nCategoria: "+type;
        setInfo(info);
        setImatge("/data/images/" + image);
        setCircleColor(null);

        // Afegir el nou element a 'yPane'
        //yPane.getChildren().add(itemTemplate);
    
    }


    public void accion(String name) throws Exception{
        System.out.println(name+" Viendo que cosa le envia");
        opcion=((ControllerMobile_2)UtilsViews.getController("Mobile_2")).getOption();

        if (opcion.equals(options[0])){
            setCharacters(name);
        }
        if (opcion.equals(options[1])){
            setGames(name);
        }
        if (opcion.equals(options[2])){
            setConsoles(name);
        }
        textMobile3.setText(name);
        UtilsViews.setView("Mobile_3");
    }
    

    //cambiar titulo
    public void setTitle(String title) {
        this.titulo.setText(title);
    }
    //cambiar info
    public void setInfo(String subtitle) {
        this.info.setText(subtitle);
    }
    //cambiar imagen
    public void setImatge(String imagePath) {
        try {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            this.img.setImage(image);
        } catch (NullPointerException e) {
            System.err.println("Error loading image asset: " + imagePath);
            e.printStackTrace();
        }
    }
    //cambia color del circulo
    public void setCircleColor(String color) {
        circle.setStyle("-fx-fill: " + color);
    }
}
