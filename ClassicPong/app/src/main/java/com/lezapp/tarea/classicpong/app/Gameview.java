package com.lezapp.tarea.classicpong.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Leslie on 08/01/2015.
 */
public class Gameview extends View {

    private static long RATE = 50;
    private Bitmap bmp, bmpf, bmp2, bmp21, pelotita, fondo, jugador1, jugador2, raya, raya1;
    private float x;
    private float y;
    private int ancho, alto, xspeed, yspeed, yjugador, yjugador2;
    int movsensor;
    int movjug2;
    int score1 = 0;
    int score2 = 0;
    Paint pinta = new Paint();
    Paint pinta2 = new Paint();
    Paint pintaGameO = new Paint();
    int repitio = 0;
    int numeros[] = {2,4,6,8,10};

    Gameplay n = new Gameplay();

    public Gameview(Context context) {
        super(context);

        bmpf = BitmapFactory.decodeResource(getResources(), R.drawable.fondito);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        bmp2 = BitmapFactory.decodeResource(getResources(), R.drawable.player);
        bmp21 = BitmapFactory.decodeResource(getResources(), R.drawable.player);
        raya1 = BitmapFactory.decodeResource(getResources(), R.drawable.midline);

        yjugador = 5;
        yjugador2 = 5;
        xspeed = 10;
        yspeed = 10;

        x = 100;
        y = 100;
    }

    public static int Aleatorio(int minimo, int maximo){

        int num=(int)Math.floor(Math.random()*(minimo-(maximo+1))+(maximo+1));
        return num;
    }

    @Override
    public void onSizeChanged(int a, int b, int c, int d){
        ancho = getWidth()/12;
        alto = getHeight()/9;

        raya = Bitmap.createScaledBitmap(raya1, raya1.getWidth(), alto*10, true);
        fondo = Bitmap.createScaledBitmap(bmpf, ancho*14, alto*11,true);
        pelotita = Bitmap.createScaledBitmap(bmp, ancho, alto, true);
        jugador1 = Bitmap.createScaledBitmap(bmp2, ancho/2, alto * 3, true);
        jugador2 = Bitmap.createScaledBitmap(bmp2, ancho/2, alto * 3, true);


    }
    @Override
    protected void onDraw(Canvas canvas){

        if (score1 == 3){
            RATE = 50;
            n.score = score2;
            canvas.drawBitmap(fondo, -1, -1, null);
            canvas.drawBitmap(raya, ((getWidth()/2)-(raya.getWidth()/2)), -1, null);

            canvas.drawBitmap(jugador1, 2 , yjugador ,null);
            canvas.drawBitmap(jugador2, getWidth()-2-jugador2.getWidth() , yjugador2 ,null);

            pinta.setColor(Color.LTGRAY);
            pinta.setTextSize(50);
            pinta.setTextAlign(Paint.Align.LEFT);

            canvas.drawText(Integer.toString(score1), (getWidth()/2 + 50), 50, pinta);

            pinta2.setColor(Color.LTGRAY);
            pinta2.setTextSize(50);
            pinta2.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(Integer.toString(score2), (getWidth()/2 - 50), 50, pinta2);

            pinta2.setColor(Color.YELLOW);
            pinta2.setTextSize(90);
            pinta2.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("Game   Over", (getWidth()/2), (getHeight() /2), pinta2);
        } else {
        Update();
            n.score = score2;

        canvas.drawBitmap(fondo, -1, -1, null);
        canvas.drawBitmap(raya, ((getWidth()/2)-(raya.getWidth()/2)), -1, null);

        canvas.drawBitmap(pelotita, x, y, null);

        canvas.drawBitmap(jugador1, 2 , yjugador ,null);
        canvas.drawBitmap(jugador2, getWidth()-2-jugador2.getWidth() , yjugador2 ,null);

        pinta.setColor(Color.LTGRAY);
        pinta.setTextSize(50);
        pinta.setTextAlign(Paint.Align.LEFT);

        canvas.drawText(Integer.toString(score1), (getWidth()/2 + 50), 50, pinta);

        pinta2.setColor(Color.LTGRAY);
        pinta2.setTextSize(50);
        pinta2.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(Integer.toString(score2), (getWidth()/2 - 50), 50, pinta2);

        postInvalidateDelayed(RATE);}
    }


    private void Update(){

        //////////////////////////////// jugador1

        float ac = n.val;

        if (ac >= 0){movsensor = 10;
        } else {movsensor = -10; }

        if(yjugador+movsensor > (getHeight() - jugador1.getHeight())){
            yjugador = getHeight() - jugador1.getHeight();
        } else if (yjugador+movsensor < 5){
            yjugador = 5;
        } else {yjugador = yjugador + movsensor; }


        /////////////////////////////// jugador2
        movjug2 = 5;

        if (yspeed<0){ movjug2 = -movjug2;}


        if(yjugador2+movjug2 > (getHeight() - jugador2.getHeight())){
            yjugador2 = getHeight() - jugador2.getHeight();
        } else if (yjugador2+movjug2 < 5){
            yjugador2 = 5;
        } else {yjugador2 = yjugador2 + movjug2; }


        //////////////////////// pelotita


        if(y > getHeight() - pelotita.getHeight() || (y<0)){
            yspeed = -yspeed; }
        y = y+yspeed;


        if ((x<=2+(jugador1.getWidth()/2) && (y > (yjugador-pelotita.getHeight()) && y <= (yjugador+jugador1.getHeight()))) || (x>getWidth()-(jugador2.getWidth()/2)-pelotita.getWidth() && (y>(yjugador2-(pelotita.getHeight()/2)) && y<=(yjugador2+jugador2.getHeight()))) ){
            repitio++;
            if(repitio > 1){
                x = x+xspeed;
            } else {
                xspeed = -xspeed ;
                x = x+xspeed;
            }

            }

        else if (x<2)
        {   x = getWidth()/2;
            y = Aleatorio(5, getHeight()-5);
            xspeed = -xspeed;
            x = x+xspeed;
            score1++;
            repitio = 0;


        }
        else if (x>getWidth()-pelotita.getWidth() ){
            {   x = getWidth()/2;
                y = Aleatorio(5, getHeight()-5);
                xspeed = -xspeed;
                x = x+xspeed;
                score2++;

                if(RATE < 0){
                    RATE = 0;
                } else {
                RATE = RATE - 5;}

                repitio = 0;

            }
        }

        else {
            x = x+xspeed;
            repitio = 0;}
    }



}
