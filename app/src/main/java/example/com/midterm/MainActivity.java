package example.com.midterm;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    final static String TRACK_KEY = "track";
    int seekBarMin = 10;
    EditText serchTerm;
    ScrollView svmain;
    ArrayList<MusicTrack> musicTrackArrayList;
    View itemView;
    LinearLayout container;
    Switch sortingSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serchTerm = (EditText)findViewById(R.id.editTextSearchTerm);
        Button btnSearch = (Button)findViewById(R.id.buttonSearch);
        Button btnReset = (Button)findViewById(R.id.buttonReset);
        sortingSwitch = (Switch)findViewById(R.id.switchSorting);
        final TextView seekValue = (TextView)findViewById(R.id.textViewLimitValue);
        final SeekBar seekBar = (SeekBar)findViewById(R.id.seekBarLimit);
        seekBar.setMax(20);
        svmain = (ScrollView)findViewById(R.id.sv_main);
        container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        musicTrackArrayList = new ArrayList<MusicTrack>();

        sortingSwitch.setChecked(true);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekValue.setText((seekBarMin +  progress) + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(serchTerm.getText().toString() != null && serchTerm.getText().toString().length() != 0){

                    RequestParams params = new RequestParams("GET","https://itunes.apple.com/search");
                    params.addParams("term",serchTerm.getText().toString());
                    params.addParams("limit",(seekBarMin + seekBar.getProgress()) + "");

                    svmain.removeAllViews();
                    container.removeAllViews();

                    new GetTrackAsyncTask(new GetTrackAsyncTask.IAsyncPassTrack() {
                        @Override
                        public void getArrayList(ArrayList<MusicTrack> trackArrayList) {
                            musicTrackArrayList = trackArrayList;
                            sortList();
                            for (MusicTrack track: musicTrackArrayList) {
                                final listItemUI listItem = new listItemUI(MainActivity.this);
                                itemView = (View) listItem;
                                listItem.tilte.setText("Track: " + track.getTrackName());
                                listItem.artist.setText("Artist: " + track.getArtist());
                                listItem.price.setText("Price: " + track.getTrackPrice() + "$");
                                listItem.date.setText("Date: " + track.getReleaseDate());


                                itemView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(MainActivity.this,DisplayActivity.class);
                                        intent.putExtra(TRACK_KEY,musicTrackArrayList.get(((ViewGroup)container).indexOfChild(v)));
                                        startActivity(intent);
                                    }
                                });


                                container.addView(itemView);

                            }



                        }

                        @Override
                        public Context getContext() {
                            return MainActivity.this;
                        }
                    }).execute(params);

                    svmain.addView(container);

                }else{
                    Toast.makeText(MainActivity.this,"Please enter a search term",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svmain.removeAllViews();
                serchTerm.setText("");
                seekBar.setProgress(0);
            }
        });

        sortingSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(serchTerm.getText().toString() != null && serchTerm.getText().toString().length() != 0){

                    RequestParams params = new RequestParams("GET","https://itunes.apple.com/search");
                    params.addParams("term",serchTerm.getText().toString());
                    params.addParams("limit",(seekBarMin + seekBar.getProgress()) + "");

                    svmain.removeAllViews();
                    container.removeAllViews();

                    new GetTrackAsyncTask(new GetTrackAsyncTask.IAsyncPassTrack() {
                        @Override
                        public void getArrayList(ArrayList<MusicTrack> trackArrayList) {
                            musicTrackArrayList = trackArrayList;
                            sortList();
                            for (MusicTrack track: musicTrackArrayList) {
                                final listItemUI listItem = new listItemUI(MainActivity.this);
                                itemView = (View) listItem;
                                listItem.tilte.setText("Track: " + track.getTrackName());
                                listItem.artist.setText("Artist: " + track.getArtist());
                                listItem.price.setText("Price: " + track.getTrackPrice() + "$");
                                listItem.date.setText("Date: " + track.getReleaseDate());


                                itemView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(MainActivity.this,DisplayActivity.class);
                                        intent.putExtra(TRACK_KEY,musicTrackArrayList.get(((ViewGroup)container).indexOfChild(v)));
                                        startActivity(intent);
                                    }
                                });


                                container.addView(itemView);

                            }



                        }

                        @Override
                        public Context getContext() {
                            return MainActivity.this;
                        }
                    }).execute(params);

                    svmain.addView(container);

                }else{
                    Toast.makeText(MainActivity.this,"Please enter a search term",Toast.LENGTH_LONG).show();
                }


            }
        });
    }



    public void sortList(){

        if(sortingSwitch.isChecked()){
            Collections.sort(musicTrackArrayList, new Comparator<MusicTrack>() {
                @Override
                public int compare(MusicTrack o1, MusicTrack o2) {
                    int returnValue = 0;
                    try {
                        if(new SimpleDateFormat("MM-dd-yyyy").parse(o1.releaseDate).before(new SimpleDateFormat("MM-dd-yyyy").parse(o2.releaseDate)))// > 0 ? 1 : 0;
                        {returnValue = -1;}
                        else if(new SimpleDateFormat("MM-dd-yyyy").parse(o1.releaseDate).after(new SimpleDateFormat("MM-dd-yyyy").parse(o2.releaseDate)))// > 0 ? 1 : 0;
                        {returnValue = 1; }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    return returnValue;
                }
            });



        }        else{
            Collections.sort(musicTrackArrayList, new Comparator<MusicTrack>() {
                @Override
                public int compare(MusicTrack o1, MusicTrack o2) {
                    //return Double.parseDouble(o1.getTrackPrice()) - Double.parseDouble(o2.getTrackPrice());

                    if(Double.parseDouble(o1.getTrackPrice())> Double.parseDouble(o2.getTrackPrice()))

                    return 1;
                    else
                        return -1;
                }
            });
        }

    }
}
