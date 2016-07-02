/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartplanner;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

/**
 *
 * @author viper
 */
public class WebviewController implements Initializable {

    @FXML
    private WebView webview;
    @FXML
    private TextField urlField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    } 
    
   

    @FXML
    private void handleGOAction(ActionEvent event) {
        String url = urlField.getText();
        webview.getEngine().load(url);
    }

    @FXML
    private void handleloAction(ActionEvent event) {
        webview.getEngine().load("https://127.0.0.1/");
    }

    @FXML
    private void HandleBlog1Action(ActionEvent event) {
        webview.getEngine().load("http://blog.naver.com/knq1130");
    }

    @FXML
    private void handleBlog2Action(ActionEvent event) {
        webview.getEngine().load("http://soicem.tistory.com/");
    }
}
