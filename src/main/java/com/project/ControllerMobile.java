package com.project;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EventListener;
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

public class ControllerMobile {


    //cada Json en su propia variable
    
    private String[] options = {"Characters","Games","Consoles"}; 
    private String opcion="";

    @FXML
    private Button character,games,consoles;

    @FXML
    public void changeView2(ActionEvent event){
        Button boton =((Button)(event.getSource()));
        opcion=boton.getText().toString();
        ((ControllerMobile_2)UtilsViews.getController("Mobile_2")).updateDatos();
        
        System.out.println("FIN SELECT ------------------------------");
    }

    public String getOption(){
        return opcion;
    }


        
    

    

    
}
