/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mytunes.be.Playlist;
import mytunes.be.Song;

/**
 *
 * @author Melchertsen
 */
public class SongDAO
{

    private DBConnectionProvider dbConnect;

    public SongDAO()
    {
        dbConnect = new DBConnectionProvider();
    }

    /**
     * Creates a song with the given parameters and automatic ID in the
     * database. Uses PreparedStatement to avoid unwanted SQL insertions
     *
     * @param title of song
     * @param artist of song
     * @param time of song
     * @param category of song
     * @param filePath of song
     * @return the created song as object
     * @throws SQLServerException
     */
    public Song createSong(String title, String artist, int time, String category, String filePath) throws SQLServerException
    {
        String sql = "INSERT INTO Song(title, artist, time, category, filePath) VALUES(?,?,?,?,?);";

        try (Connection con = dbConnect.getConnection())
        {
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            st.setString(1, title);
            st.setString(2, artist);
            st.setInt(3, time);
            st.setString(4, category);
            st.setString(5, filePath);

            int rowsAffected = st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            int id = 0;
            if (rs.next())
            {
                id = rs.getInt(1);
            }
            Song song = new Song(id, title, artist, time, category, filePath);
            return song;

        } catch (SQLException ex)
        {
            //nothing
        }
        return null;
    }

    /**
     * Deletes a song from the database with the given parameter's songID.
     *
     * @param song
     */
    public void deleteSong(Song song)
    {

        String sql = "DELETE FROM Song WHERE id=" + song.getSongID() + ";";

        try (Connection con = dbConnect.getConnection())
        {
            Statement statement = con.createStatement();

            statement.execute(sql);

        } catch (SQLException ex)
        {
            //nothing
        }
    }

    /**
     * Updates a song already in the playlist, can make changes to the title,
     * artist and category. The new title/artist/category are given in the
     * parameter
     *
     * @param song
     */
    public void updateSong(Song song)
    {
        String sql = "UPDATE Song SET title = ?, artist = ?, time = ?, category = ?, filePath = ? WHERE id = " + song.getSongID();

        try (Connection con = dbConnect.getConnection())
        {
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, song.getTitle());
            st.setString(2, song.getArtist());
            st.setInt(3, song.getTime());
            st.setString(4, song.getCategory());
            st.setString(5, song.getFilePath());

            st.executeUpdate();
            st.close();

        } catch (SQLException ex)
        {
            //nothing
        }
    }

    /**
     * Gets all of the songs in the Song database.
     *
     * @return an ArrayList of all the songs in the database
     * @throws SQLException
     */
    public ArrayList<Song> getAllSongs() throws SQLException
    {
        ArrayList<Song> allSongs = new ArrayList<>();

        try (Connection con = dbConnect.getConnection())
        {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Song");
            while (rs.next())
            {
                int id = rs.getInt("id");
                String title = rs.getNString("title");
                String artist = rs.getNString("artist");
                int time = rs.getInt("time");
                String category = rs.getNString("category");
                String filePath = rs.getNString("filePath");
                allSongs.add(new Song(id, title, artist, time, category, filePath));
            }
        } catch (SQLException ex)
        {
            //nothing
        }
        return allSongs;
    }

    /**
     * Searches the database for a specific song with the given parameter
     *
     * @param songID
     * @return a Song with the given parameter if found
     * @throws SQLException
     */
    public Song getSong(int songID) throws SQLException
    {
        Song theSong = new Song(0, "", "", 0, "", "");

        try (Connection con = dbConnect.getConnection())
        {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Song WHERE id=" + songID);
            while (rs.next())
            {
                int id = rs.getInt("id");
                String title = rs.getNString("title");
                String artist = rs.getNString("artist");
                int time = rs.getInt("time");
                String category = rs.getNString("category");
                String filePath = rs.getNString("filePath");
                theSong = new Song(id, title, artist, time, category, filePath);
                System.out.println("" + theSong.toString());
            }

        } catch (SQLException ex)
        {
            //nothing
        }
        return theSong;

    }

    /**
     * Searches all songs for songs that contain the given parameter in either
     * the song title or artist.
     *
     * @param searchWord
     * @return an ArrayList of the songs containing the parameter
     * @throws SQLServerException
     * @throws SQLException
     */
    public ArrayList<Song> SearchSong(String searchWord) throws SQLServerException, SQLException
    {
        String sql = "SELECT * FROM Song WHERE title LIKE '%" + searchWord + "%' OR artist LIKE '%" + searchWord + "%'";
        ArrayList<Song> allSongsFounded = new ArrayList<>();

        try (Connection con = dbConnect.getConnection())
        {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next())
            {
                int id = rs.getInt("id");
                String title = rs.getNString("title");
                String artist = rs.getNString("artist");
                int time = rs.getInt("time");
                String category = rs.getNString("category");
                String filePath = rs.getNString("filePath");
                Song SongFounded = new Song(id, title, artist, time, category, filePath);
                allSongsFounded.add(SongFounded);
            }
        }
        return allSongsFounded;
    }
}
