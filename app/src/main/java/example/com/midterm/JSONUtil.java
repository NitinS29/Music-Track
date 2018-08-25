package example.com.midterm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Nitin on 6/13/2017.
 */

public class JSONUtil {

    static class JSONMusicParser{
        static public ArrayList<MusicTrack> parse(String input){
            ArrayList<MusicTrack> trackList = new ArrayList<MusicTrack>();
            try {
                JSONObject root = new JSONObject(input);
                JSONArray listJSONArray = root.getJSONArray("results");
                for(int i=0; i< listJSONArray.length(); i++){
                    JSONObject js = listJSONArray.getJSONObject(i);
                    MusicTrack track = new MusicTrack();
                    track.setTrackName(js.getString("trackName"));
                    track.setGenre(js.getString("primaryGenreName"));
                    track.setArtist(js.getString("artistName"));
                    if(js.has("collectionName")) {
                        track.setAlbum(js.getString("collectionName"));
                    }else{
                        track.setAlbum("Unknown");
                    }
                    if(js.has("artworkUrl100")){
                        track.setImageUrl(js.getString("artworkUrl100"));
                    }
                    else {
                        track.setImageUrl("");
                    }
                    track.setTrackPrice(js.getString("trackPrice"));
                    track.setAlbumPrice(js.getString("collectionPrice"));
                    track.setReleaseDate(js.getString("releaseDate"));

                    trackList.add(track);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return trackList;
        }
    }
}
