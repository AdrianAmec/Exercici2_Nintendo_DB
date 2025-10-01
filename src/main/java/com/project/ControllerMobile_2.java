package com.project;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ControllerMobile_2 extends Controller implements Initializable {


    //cada Json en su propia variable
    private String name;
    private JSONArray jsonInfoCharacters;
    private JSONArray jsonInfoConsoles;
    private JSONArray jsonInfoGames;
    private String[] options = {"Characters","Games","Consoles"}; 
    private String opcion="";

    @FXML
    private VBox yPane2;

    @FXML
    private Button buttonBack;
    
    @FXML 
    private Text textMobile2;

    @FXML
    public void back(ActionEvent event){
        UtilsViews.setView("Mobile_1");
    }
    public void updateDatos(){
        
        opcion=((ControllerMobile)UtilsViews.getController("Mobile_1")).getOption();
        
        //System.out.println("Selecionado "+opcion);
        textMobile2.setText(opcion);
        
        try {
            //System.out.println("asdasdasdTEst");
            setOption(opcion);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        UtilsViews.setView("Mobile_2");
    }

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

    //cambiar la lista dependiendo dela opcion
    public void setOption(String dato)throws Exception{


        JSONArray jsonDatos = null;
        if(dato.equals(options[0])){
            jsonDatos=jsonInfoCharacters;
        }
        if(dato.equals(options[1])){
            jsonDatos=jsonInfoGames;
        }
        if(dato.equals(options[2])){
            jsonDatos=jsonInfoConsoles;
        }

         // Obtenir el recurs del template .fxml
        URL resource = this.getClass().getResource("/assets/listitem.fxml");

        // Esborrar la llista anterior
        System.out.println("Error?");
        yPane2.getChildren().clear();
        System.out.println("SISISISI");

        // Generar la nova llista a partir de 'jsonInfo'
        for (int i = 0; i < jsonDatos.length(); i++) {
            // Obtenir l'objecte JSON individual (animal)

            JSONObject character = jsonDatos.getJSONObject(i);

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
            itemController.setParentController((ControllerMobile_3)UtilsViews.getController("Mobile_3"));
            
            // Afegir el nou element a 'yPane'
            yPane2.getChildren().add(itemTemplate);
        }
    }


    public String getOption(){
        return opcion;
    }
    public String getName(){
        return name;
    }
}
