package android.example.recorderapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.View.OnClickListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private Button play, stop, record;
    Button List;
    private MediaRecorder myAudioRecorder;
//    private String outputFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        play = (Button) findViewById(R.id.play);
        stop = (Button) findViewById(R.id.stop);
        record = (Button) findViewById(R.id.record);
        stop.setEnabled(false);
//        play.setEnabled(false);
//        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";
        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(getFilename());


            record.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        try {
                            myAudioRecorder.prepare();
                            myAudioRecorder.start();
                        } catch (IllegalStateException ise) {
                            // make something ...
                        } catch (IOException ioe) {
                            // make something
                        }
                        record.setEnabled(false);
                        stop.setEnabled(true);
                        Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();
                    }

            });

            stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        myAudioRecorder.stop();
                        myAudioRecorder.release();
                        myAudioRecorder = null;
                        record.setEnabled(true);
                        stop.setEnabled(false);
//                    play.setEnabled(true);
                        Toast.makeText(getApplicationContext(), "Audio Recorded successfully", Toast.LENGTH_LONG).show();
                    }

            });

//        play.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MediaPlayer mediaPlayer = new MediaPlayer();
//                try {
//                    mediaPlayer.setDataSource(getFilename());
//                    mediaPlayer.prepare();
//                    mediaPlayer.start();
//                    Toast.makeText(getApplicationContext(), "Playing Audio", Toast.LENGTH_LONG).show();
//                } catch (Exception e) {
//                    // make something
//                }
//            }
//        });


    }
    private String getFilename()
    {
        String filepath = Environment.getExternalStorageDirectory().getPath();
        File file = new File(filepath,"Recorder");

        if(!file.exists()){
            file.mkdirs();
        }

        return (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".3gp");
    }



}

