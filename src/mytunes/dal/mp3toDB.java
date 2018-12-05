/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import mytunes.be.Song;

/**
 *
 * @author Casper
 */
public class mp3toDB {

    private DBConnectionProvider dbConnect;

    public mp3toDB() {
        dbConnect = new DBConnectionProvider();
    }

    public Song mp3songToDBTable(File file) throws IOException, UnsupportedTagException, InvalidDataException {

        String sql = "INSERT INTO Song(title, artist, time, category, filePath) VALUES(?,?,?,?,?);";
        Mp3File mp3file = new Mp3File(file);

        ID3v2 id3v2Tag = mp3file.getId3v2Tag();
        
        try (Connection con = dbConnect.getConnection()) {
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            st.setString(1, id3v2Tag.getTitle());
            st.setString(2, id3v2Tag.getArtist());
            st.setInt(3, Math.toIntExact(mp3file.getLengthInSeconds()));
            st.setString(4, id3v2Tag.getGenreDescription());
            st.setString(5, "Data/"+file.getName());

            int rowsAffected = st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            Song song = new Song(id, id3v2Tag.getTitle(), id3v2Tag.getArtist(), Math.toIntExact(mp3file.getLengthInSeconds()), id3v2Tag.getGenreDescription(),"Data/"+file.getName());
            System.out.println("" + song.toString());
            return song;

        } catch (SQLException ex) {
            //nothing
        }
        return null;

    }

}
