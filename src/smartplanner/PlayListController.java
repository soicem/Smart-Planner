/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartplanner;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author soicem
 */
public class PlayListController implements Initializable {

    @FXML
    private JFXDatePicker datePicker;
    @FXML
    private JFXComboBox<Label> ComboBox;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ComboBox.getItems().add(new Label("Years"));
        ComboBox.getItems().add(new Label("monthly"));
        ComboBox.getItems().add(new Label("3days"));
        ComboBox.setEditable(false);
        ComboBox.setPromptText("Selection");

        // TODO
        ComboBox.setOnAction((ActionEvent e) -> {
            Label label;
            try {

                label = ComboBox.getValue();
                dbConnect();
                System.out.println(label.getText());
                System.out.println("check");
            } catch (Exception ex) {
                Logger.getLogger(TimeCheckController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        datePicker.setOnAction((ActionEvent e) -> {

            try {
                dbConnect();
                System.out.println(datePicker.getValue());
                System.out.println("check");
            } catch (Exception ex) {
                Logger.getLogger(TimeCheckController.class.getName()).log(Level.SEVERE, null, ex);

            }
        });
    }
                

    private void dbConnect() throws SQLException {

        Connection conn = null;
        Statement stat = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select date from ListView;");

            System.out.println(rs.getString(1));
            stat.close();
            conn.close();
        } catch (SQLException e) {

        }
    }

}
