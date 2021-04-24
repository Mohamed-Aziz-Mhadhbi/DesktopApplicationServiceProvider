/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Mohamed Aziz Mhadhbi
 */
public class SiteWebController implements Initializable {

    @FXML
    private WebView SiteWeb;
    
    private WebEngine engine;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        engine = SiteWeb.getEngine();
        engine.load("https://www.google.com/");
    }    
    
}
