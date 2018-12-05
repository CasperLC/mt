/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mytunes.be.Playlist;

/**
 * FXML Controller class
 *
 * @author andre
 */
public class CreatePlayListController implements Initializable
{

    private MyTunesModel model;

    @FXML
    private TextField txtCreateplayListName;
    @FXML
    private Button BTNCreatPlay;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void CreatPlayList(ActionEvent event)
    {
        String playlistname = this.txtCreateplayListName.getText();
        try
        {
            model.createPlaylist(playlistname);
            Stage stage = (Stage) BTNCreatPlay.getScene().getWindow();
            stage.close();

        } catch (SQLException ex)
        {
            Logger.getLogger(CreatePlayListController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void setModel(MyTunesModel model)
    {
        this.model = model;
    }

}
