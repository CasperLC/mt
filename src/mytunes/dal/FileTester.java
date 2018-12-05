/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import mytunes.be.Playlist;
import java.util.ArrayList;
import mytunes.be.Song;

/**
 *
 * @author Caspe
 */
public class FileTester
{

    public static void main(String[] args) throws IOException, SQLServerException, SQLException, UnsupportedTagException, InvalidDataException
    {
        SongDAO songDAO = new SongDAO();
        mp3toDB mp3todb = new mp3toDB();
        PlaylistDAO pldao = new PlaylistDAO();        
        
        

//        mp3todb.mp3songToDBTable(new File("Data/BenJamin_Banger_-_01_-_Bobby_Drake.mp3"));
//        songDAO.createSong("TestSong", "TestArtist", 150, "TestType");
//        songDAO.deleteSong(new Song(2, "", "", 3, ""));
//        songDAO.updateSong(new Song(3,"TESTUPDATE","ARTISTUPDATE",999,"UPDATED"));
//        for (Song song : songDAO.getAllSongs())
//        {
//            System.out.println(""+song.toString());
//        }
//        songDAO.getSong(3);


//        mp3todb.mp3songToDBTable(new Mp3File("Data/BenJamin_Banger_-_01_-_Bobby_Drake.mp3"));
//        Mp3File mp3file = new Mp3File("Data/BenJamin_Banger_-_01_-_Bobby_Drake.mp3");
//        System.out.println("Length: " + mp3file.getLengthInSeconds());
//        System.out.println("" + (mp3file.hasId3v1Tag() ? "YES" : "NO"));
//        System.out.println("" + (mp3file.hasId3v2Tag() ? "YES" : "NO"));
//        if (mp3file.hasId3v2Tag())
//        {
//            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
//            System.out.println("Track: " + id3v2Tag.getTrack());
//            System.out.println("Artist: " + id3v2Tag.getArtist());
//            System.out.println("Title: " + id3v2Tag.getTitle());
//            System.out.println("Album: " + id3v2Tag.getAlbum());
//            System.out.println("Year: " + id3v2Tag.getYear());
//            System.out.println("Genre: " + id3v2Tag.getGenre() + " (" + id3v2Tag.getGenreDescription() + ")");
//            System.out.println("Comment: " + id3v2Tag.getComment());
//            System.out.println("Lyrics: " + id3v2Tag.getLyrics());
//            System.out.println("Composer: " + id3v2Tag.getComposer());
//            System.out.println("Publisher: " + id3v2Tag.getPublisher());
//            System.out.println("Original artist: " + id3v2Tag.getOriginalArtist());
//            System.out.println("Album artist: " + id3v2Tag.getAlbumArtist());
//            System.out.println("Copyright: " + id3v2Tag.getCopyright());
//            System.out.println("URL: " + id3v2Tag.getUrl());
//            System.out.println("Encoder: " + id3v2Tag.getEncoder());
//            byte[] albumImageData = id3v2Tag.getAlbumImage();
//            if (albumImageData != null)
//            {
//                System.out.println("Have album image data, length: " + albumImageData.length + " bytes");
//                System.out.println("Album image mime type: " + id3v2Tag.getAlbumImageMimeType());
//            }
//        }


    }

}
