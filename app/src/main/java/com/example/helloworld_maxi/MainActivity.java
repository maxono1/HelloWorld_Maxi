package com.example.helloworld_maxi;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Canvas ozjung;
    private Bitmap bitmap;
    private Paint hwite;
    private int hoeheBitmap = 800;
    private final int breiteBitmap = 800;
    private final int textSize = 50;

    private Timer timer = new Timer();
    private int grenzeLinks = 30;
    private int grenzeRechts = 770;
    private int grenzeOben = 400;
    private int grenzeUnten = 770;

    private int ballRadius = 20;
    private float ballX = 100f;
    private float ballY = 700f;
    private float velociteX = 0.3f;
    private float velociteY = 4.5f;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bitmap = Bitmap.createBitmap(this.breiteBitmap,this.hoeheBitmap,Bitmap.Config.ARGB_8888);
        this.ozjung = new Canvas(this.bitmap);
        this.imageView = new ImageView(this);
        this.imageView.setImageBitmap(this.bitmap);
        this.hwite = new Paint();

        setContentView(imageView);

        this.ozjung.drawColor(Color.argb(255,0,0,255));
        this.hwite.setTextSize(textSize);
        this.halloWelt();
        this.halloNachbarn();
        this.zeichneSmiley(200);

        this.timer.schedule(
                new TimerTask(){
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void run(){
                        derSpringendePunkt();
                    }
                }
        ,0,17);
    }

    private void halloWelt(){
        String text = "Hallo Welt";
        float textWidth = this.hwite.measureText(text);
        this.hwite.setColor(Color.WHITE);
        this.ozjung.drawText(text, breiteBitmap/2 - textWidth/2,100,this.hwite);
    }

    private void textZentrieren(String text, int y){
        float textWidth = this.hwite.measureText(text);
        this.hwite.setColor(Color.WHITE);
        this.ozjung.drawText(text, breiteBitmap/2 - textWidth/2,y,this.hwite);
    }

    private void halloNachbarn(){
        String text = "Hallo Tim und Abdurrachman";
        textZentrieren(text,100+textSize);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void zeichneSmiley(int radius){
        this.hwite.setColor(Color.GREEN);
        int eyeRadius = 35;
        int mouthRadius = 60;

        ozjung.drawCircle(breiteBitmap/2,(100+textSize*2)+300,radius,hwite);
        this.hwite.setColor(Color.BLACK);
        ozjung.drawCircle(breiteBitmap/2,500+radius/2,mouthRadius+10,hwite);
        this.hwite.setColor(Color.GREEN);
        ozjung.drawCircle(breiteBitmap/2,500,mouthRadius+50,hwite);

        this.hwite.setColor(Color.BLACK);
        ozjung.drawCircle(breiteBitmap/2-radius/3-15,(100+textSize*2)+300-radius/3,eyeRadius,hwite);
        ozjung.drawCircle(breiteBitmap/2+radius/3+15,(100+textSize*2)+300-radius/3,eyeRadius,hwite);
        //ozjung.drawArc(50,50,100,100,20,60,false,hwite);


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void derSpringendePunkt(){
        /*
        Log.i("MainActivity", LocalDateTime.now()
                + ": der springende Punkt");*/
        zeichneSmiley(200);

        this.hwite.setColor(Color.BLUE);
        ozjung.drawCircle(ballX,ballY,ballRadius,hwite);

        if(ballX>grenzeRechts || ballX < grenzeLinks){
            velociteX*=-1;
        }
        if(ballY>grenzeUnten || ballY <grenzeOben){
            velociteY*=-1;
        }

        ballX += velociteX;
        ballY += velociteY;

        this.hwite.setColor(Color.RED);
        ozjung.drawCircle(ballX,ballY,ballRadius,hwite);

        this.imageView.invalidate();

    }
}
