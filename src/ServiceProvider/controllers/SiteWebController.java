/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceProvider.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Asus
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
        engine.load("http://127.0.0.1:8000");
    }    
    
}
