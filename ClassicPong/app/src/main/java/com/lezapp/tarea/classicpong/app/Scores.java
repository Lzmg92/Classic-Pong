package com.lezapp.tarea.classicpong.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


public class Scores extends ActionBarActivity {

    TextView txtscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        txtscore = (TextView)findViewById(R.id.txtscore);
        leer();

    }

    private void leer(){
        try {
            InputStreamReader archivo = new InputStreamReader(openFileInput("scores.txt"));
            BufferedReader br = new BufferedReader(archivo);

            String linea;
            StringBuilder texto = new StringBuilder();
            while((linea = br.readLine()) != null)
            {
                texto.append(linea);
                texto.append("\n");
            }

            txtscore.setText(texto.toString());
            br.close();
        }catch (Exception e){}}





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.scores, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
