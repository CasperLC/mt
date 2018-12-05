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
import java.io.File;
import java.io.IOException;
import static java.lang.System.out;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.NativeRegExp.source;
import mytunes.be.Song;

/**
 * FXML Controller class
 *
 * @author Casper
 */
public class CreateSongWindowController implements Initializable {

    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtArtist;
    @FXML
    private TextField txtCategory;
    @FXML
    private TextField txtTime;
    @FXML
    private TextField txtFilepath;
    @FXML
    private Button btnChooseFile;
    @FXML
    private Button btnSaveChanges;
    @FXML
    private Button btnCancelChanges;
    @FXML
    private Label lblWindowTitle;
    private String fileDest;
    private MyTunesModel model;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void chooseMp3File() throws IOException, UnsupportedTagException, InvalidDataException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open an mp3");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Mp3File", "*.mp3"));
        File newSong = fileChooser.showOpenDialog(null);
        setTextFromMp3File(newSong);
    }

    public void setTextFromMp3File(File newSong) throws IOException, UnsupportedTagException, InvalidDataException {
        Mp3File themp3 = new Mp3File(newSong);
        ID3v2 fixFields = themp3.getId3v2Tag();
        txtTitle.setText(fixFields.getTitle());
        txtArtist.setText(fixFields.getArtist());
        txtCategory.setText(fixFields.getGenreDescription());
        txtTime.setText(Long.toString(themp3.getLengthInSeconds()));
        txtFilepath.setText("" + newSong.getAbsolutePath());
        fileDest = newSong.getName();
    }

    public void fileCopy(File from, File to) throws IOException {
        Files.copy(from.toPath(), to.toPath());
    }

    @FXML
    private void handleChooseFile(ActionEvent event) throws IOException, UnsupportedTagException, InvalidDataException {
        chooseMp3File();
    }

    @FXML
    private void handleSaveFile(ActionEvent event) throws IOException, UnsupportedTagException, InvalidDataException {
        File dirFrom = new File(txtFilepath.getText());
        File dirTo = new File("data/" + fileDest);
        fileCopy(dirFrom, dirTo);
        Mp3File toDB = new Mp3File(dirTo);
        ID3v2 readFile = toDB.getId3v2Tag();
        model.createSong(readFile.getTitle(), readFile.getArtist(), Math.toIntExact(toDB.getLengthInSeconds()), readFile.getGenreDescription(), "data/" + fileDest);
        lblWindowTitle.setText(dirTo.getName());
        Stage stage = (Stage) btnCancelChanges.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancelbutton(ActionEvent event) {
        Stage stage = (Stage) btnCancelChanges.getScene().getWindow();
        stage.close();
    }
    
    void setModel(MyTunesModel model) {
        this.model = model;
    }    

}
