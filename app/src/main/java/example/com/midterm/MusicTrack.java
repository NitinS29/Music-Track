package example.com.midterm;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Nitin on 6/13/2017.
 */

public class MusicTrack implements Serializable{
    String trackName, genre, artist, album, trackPrice, albumPrice, releaseDate, imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getReleaseDate() {

        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;

        final String outputFormat = "MM-dd-yyyy ";
        final String inputFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";

        try {
            this.releaseDate = new SimpleDateFormat(outputFormat).format(new SimpleDateFormat(inputFormat).parse(this.releaseDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getTrackPrice() {
        return trackPrice;
    }

    public void setTrackPrice(String trackPrice) {
        this.trackPrice = trackPrice;
    }

    public String getAlbumPrice() {
        return albumPrice;
    }

    public void setAlbumPrice(String albumPrice) {
        this.albumPrice = albumPrice;
    }
}
