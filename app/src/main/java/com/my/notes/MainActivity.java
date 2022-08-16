package com.my.notes;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    public Controller db;
    public ListView lisView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action, menu);
        return true;
    }                                                                                                                                                           

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:break;
            case R.id.exit:System.exit(0);break;
            default:return false;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

        db = new Controller(getApplicationContext());
        lisView = findViewById(R.id.listView);

        List<String> opcoes = db.getTarefas();
        //ArrayList opcoes = new ArrayList<String>();
        //for (int i=0;i < 100;i++) opcoes.add("Navegar na Internet "+i);
        ArrayAdapter adaptador = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, opcoes);
        lisView.setAdapter(adaptador);

        lisView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    Snackbar.make(view, "Replace "+position, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                    return false;
                }
            });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                    modal();
                }
            });
    }
    /*
    public void populate(){
        List<String> tarefas = db.getTarefas();
        lisView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tarefas.toArray(new String[] {})));
        
    }*/
    
    
    public void modal(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Agenda ");
        final EditText input = new EditText(this);
        //input.setInputType( InputType.TYPE_CLASS_TEXT );
        builder.setView(input);

        builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() { 
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String txt = input.getText().toString();
                    db.addTarefa(txt);
                }
            });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

        builder.show();
    }
    
    public void snack(View view){
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
    }

}
