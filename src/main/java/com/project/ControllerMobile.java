package com.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControllerMobile {


    //cada Json en su propia variable
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
