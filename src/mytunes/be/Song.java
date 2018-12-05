/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.be;

import com.mpatric.mp3agic.Mp3File;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Melchertsen
 */
public class Song
{

    private int songID;         // songID for the database
    private String title;       // title of the song
    private String artist;      //the song's artist
    private int time;           //song's duration
    private String category;    //song's catergory
    private String filePath;    //the mp3 file's current filepath

    public Song(int songID, String title, String artist, int time, String category, String filePath)
    {
        this.songID = songID;
        this.title = title;
        this.artist = artist;
        this.time = time;
        this.category = category;
        this.filePath = filePath;

    }

    /**
     * gets the songID of current song
     *
     * @return the songID variable
     */
    public int getSongID()
    {
        return songID;
    }

    /**
     * Gets the song title
     *
     * @return title of song
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * set song title to given parameter
     *
     * @param title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * @return artist of song
     */
    public String getArtist()
    {
        return artist;
    }

    /**
     * sets the artist of the song to the given parameter
     *
     * @param artist
     */
    public void setArtist(String artist)
    {
        this.artist = artist;
    }

    /**
     *
     * @return the time/duration of the song
     */
    public int getTime()
    {
        return time;
    }

    public void setTime(int time)
    {
        this.time = time;
    }

    /**
     *
     * @return category of the song
     */
    public String getCategory()
    {
        return category;
    }

    /**
     * sets the category of the song to the given parameter
     *
     * @param category
     */
    public void setCategory(String category)
    {
        this.category = category;
    }

    /**
     *
     * @return the filepath of the song
     */
    public String getFilePath()
    {
        return filePath;
    }

    /**
     * sets the filepath of the song to the given parameter
     *
     * @param filePath
     */
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    /**
     * Uses the time variable which is in seconds and changes it into a more
     * readable and display-friendly format for the user.(minutes:seconds)
     *
     * @return string that looks like: "mintues"+":"+"seconds"
     */
    public String displayTime()
    {
        int tempTime = 0;
        int tempMinutes = 0;
        String temp = "";
        tempTime = time;
        while (tempTime >= 60)
        {
            tempTime = tempTime - 60;
            tempMinutes++;
        }
        if (tempTime < 10)
        {
            temp = "0" + tempTime;
        } else
        {
            temp = ""+tempTime;
        }
        String displayTime = tempMinutes + ":" + temp;
        return displayTime;

    }

    @Override
    public String toString()
    {
        return "Title: " + title
                + " Artist: " + artist
                + " Song Duration: " + displayTime()
                + " Genre: " + category;
    }
}
