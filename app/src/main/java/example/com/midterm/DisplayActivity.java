package example.com.midterm;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Button btnFinish = (Button)findViewById(R.id.buttonFinish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView dTrackName = (TextView)findViewById(R.id.textViewDTitle);
        TextView dGenre = (TextView)findViewById(R.id.textViewDgenre);
        TextView dArtist = (TextView)findViewById(R.id.textViewDArtist);
        TextView dAlbum = (TextView)findViewById(R.id.textViewDAlbum);
        TextView dTrackPrice = (TextView)findViewById(R.id.textViewDTrackPrice);
        TextView dAlbumPrice = (TextView)findViewById(R.id.textViewDAlbumPrice);
        img = (ImageView)findViewById(R.id.imageViewTrack);
        img.setImageResource(0);

        if(getIntent().getExtras() != null){

            MusicTrack track = (MusicTrack) getIntent().getExtras().getSerializable(MainActivity.TRACK_KEY);

            dTrackName.setText("Track: " + track.getTrackName());
            dGenre.setText("Genre: " + track.getGenre());
            dArtist.setText("Artist: " + track.getArtist());
            dAlbum.setText("Album: " + track.getAlbum());
            dTrackPrice.setText("Track Price: " + track.getTrackPrice() + "$");
            dAlbumPrice.setText("Album Price: " + track.getAlbumPrice() + "$");



            if(!track.getImageUrl().equals("") ){
                new DownloadImage(new DownloadImage.AsyncImage() {
                    @Override
                    public void getImage(Bitmap image) {
                        img.setImageBitmap(image);
                    }

                    @Override
                    public Context getContext() {
                        return DisplayActivity.this;
                    }
                }).execute(track.getImageUrl());
            }

        }
    }
}
