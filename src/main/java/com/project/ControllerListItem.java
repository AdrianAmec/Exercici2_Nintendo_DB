package com.project;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javax.swing.border.StrokeBorder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;

public class ControllerListItem{

    @FXML
    private Label title;

    @FXML
    private ImageView img;

    @FXML
    private AnchorPane select;

    
    private Controller mainController;

    public void setParentController(Controller controller) {
        this.mainController = controller;
    }
    

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public String getTitle(){
        return title.getText().toString();
    }
    @FXML
    public void selectOption(MouseEvent event) throws Exception{
        System.out.println(this.getTitle());
        mainController.accion(this.getTitle().toString());
    }
    @FXML
    public void glowOption(MouseEvent event){
        select.setEffect(new DropShadow(10, Color.GRAY));
    }

    @FXML
    public void noGlowOption(MouseEvent event){
        select.setEffect(null);
    }



    public void setImatge(String imagePath) {
        try {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            this.img.setImage(image);
        } catch (NullPointerException e) {
            System.err.println("Error loading image asset: " + imagePath);
            e.printStackTrace();
        }
    }

    

}