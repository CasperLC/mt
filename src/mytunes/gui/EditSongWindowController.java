/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.IOException;
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
import mytunes.be.Song;

/**
 * FXML Controller class
 *
 * @author Richart hansen
 */
public class EditSongWindowController implements Initializable
{

    @FXML
    private TextField lblestitle;
    @FXML
    private TextField lbledcatagory;
    @FXML
    private TextField lbledartist;
    @FXML
    private Button handleedcancel;
    @FXML
    private Button handleedsave;
    private MyTunesModel model;
    @FXML
    private Button btnCurrentInfo;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }

    public void setModel(MyTunesModel model)
    {
        this.model = model;
    }

    public void setTextFields(Song editSong) throws IOException, UnsupportedTagException, InvalidDataException
    {
        Mp3File theSong = new Mp3File(editSong.getFilePath());
        ID3v2 tags = theSong.getId3v2Tag();
        lblestitle.setText(tags.getTitle());
        lbledartist.setText(tags.getArtist());
        lbledcatagory.setText(tags.getGenreDescription());

    }

    @FXML
    private void handleShowCurrentInfo(ActionEvent event) throws IOException, UnsupportedTagException, InvalidDataException
    {
        setTextFields(model.getSelectedSong());
    }

    @FXML
    private void handleSaveEdit(ActionEvent event) throws SQLException
    {
        createEditedSong();
        Stage stage = (Stage) handleedcancel.getScene().getWindow();
        stage.close();
    }

    public void createEditedSong() throws SQLException
    {
        Song oldSong = model.getSelectedSong();
        Song editedSong = new Song(model.getSelectedSong().getSongID(),
                lblestitle.getText(),
                lbledartist.getText(),
                model.getSelectedSong().getTime(),
                lbledcatagory.getText(),
                model.getSelectedSong().getFilePath());
        model.updateSong(editedSong, oldSong);
    }

    @FXML
    private void handleCancelEdit(ActionEvent event)
    {
        Stage stage = (Stage) handleedcancel.getScene().getWindow();
        stage.close();
    }
}
