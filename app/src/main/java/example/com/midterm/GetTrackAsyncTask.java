package example.com.midterm;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Created by Nitin on 6/13/2017.
 */

public class GetTrackAsyncTask extends AsyncTask<RequestParams,Void,ArrayList<MusicTrack>> {

    ProgressDialog progressDialog;
    IAsyncPassTrack iAsyncPassTrack;
        public GetTrackAsyncTask(IAsyncPassTrack iMusicTrack){
        this.iAsyncPassTrack = iMusicTrack;
        }

    public interface IAsyncPassTrack{
        void getArrayList(ArrayList<MusicTrack> trackArrayList);
        Context getContext();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(iAsyncPassTrack.getContext());
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected ArrayList<MusicTrack> doInBackground(RequestParams... params) {
        StringBuilder sb = new StringBuilder();
        try {
            HttpURLConnection con = params[0].createConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line = "";

            while ((line = reader.readLine()) != null){
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONUtil.JSONMusicParser.parse(sb.toString());
    }

    @Override
    protected void onPostExecute(ArrayList<MusicTrack> trackArrayList) {
        super.onPostExecute(trackArrayList);
        iAsyncPassTrack.getArrayList(trackArrayList);
        progressDialog.dismiss();
    }
}
