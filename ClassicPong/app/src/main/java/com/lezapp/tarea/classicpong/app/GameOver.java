package com.lezapp.tarea.classicpong.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.OutputStreamWriter;


public class GameOver extends ActionBarActivity {

    Button btnguardar;
    TextView txtscore;
    EditText txtnombre;
    String punteo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game_over);
        txtnombre = (EditText)findViewById(R.id.txtnombre);
        txtscore = (TextView)findViewById(R.id.txtscore);
        punteo = getIntent().getExtras().getString("nose");
        txtscore.setText("Lograste "+punteo+" Puntos");



        btnguardar = (Button)findViewById(R.id.btnguardar);
        btnguardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                comprobar();
                String conca = "\n" + txtnombre.getText() + "\t\t\t\t\t" + punteo;
                actualizarArchivo(conca);
                Toast toast = Toast.makeText(getApplicationContext(), "Datos Guardados \n Puedes Volver", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }




    private void comprobar() {
        // Comprobamos si existe el archivo
        if (existsFile("scores.txt")) {

        } else {
            crearArchivo();
        }}


    private void crearArchivo() {
        try {

            OutputStreamWriter outSWMensaje = new OutputStreamWriter(
                    openFileOutput("scores.txt", Context.MODE_PRIVATE));
            outSWMensaje.write("******** SCORES ********");
            outSWMensaje.close();
        } catch (Exception e) {
        }
    }


    public boolean existsFile(String fileName) {
        for (String tmp : fileList()) {
            if (tmp.equals(fileName))
                return true;
        }
        return false;
    }



    private void actualizarArchivo(String punteo) {
        try {
            OutputStreamWriter outSWMensaje = new OutputStreamWriter(
                    openFileOutput("scores.txt", Context.MODE_APPEND));
            outSWMensaje.write("\n"+punteo);
            outSWMensaje.close();
        } catch (Exception e) {

        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game_over, menu);
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
