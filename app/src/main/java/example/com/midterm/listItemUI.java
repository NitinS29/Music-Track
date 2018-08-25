package example.com.midterm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Nitin on 6/8/2017.
 */

public class listItemUI extends LinearLayout {

    public TextView tilte, price, artist, date;
    //public ImageView storyImage;

    public listItemUI(Context context) {
        super(context);
        inflateXML(context);
    }

    private void inflateXML(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.listitem,this);
        this.tilte = (TextView)findViewById(R.id.tvTitle);
        this.artist = (TextView)findViewById(R.id.tvArtist);
        this.price = (TextView)findViewById(R.id.tvPrice);
        this.date = (TextView)findViewById(R.id.tvdate);
        //this.storyImage = (ImageView)findViewById(R.id.imageViewThumbnail);
    }
}
