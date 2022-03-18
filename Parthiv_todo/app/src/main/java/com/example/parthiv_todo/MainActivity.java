package com.example.parthiv_todo;
/* import */
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
/* for arraylist */
import java.util.ArrayList;
/* Main activity class*/
public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemLongClickListener {

    /*properties for textview, button and listview*/
    private EditText txt_ed;
    private Button add_btn;
    private ListView item_list;
    //ArrayList takes the user input
    private ArrayList<String> values=new ArrayList<String>();
    // ArrayAdapter is used to insert a value in ListView
    private ArrayAdapter<String> adapter;


    /*oncreate method to store values(unique id like,todo_edit, add_button, etc. ) in variable*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_ed = findViewById(R.id.todo_edit);
        add_btn = findViewById(R.id.add_button);
        item_list = findViewById(R.id.listview);

        add_btn.setOnClickListener(this);
        item_list.setOnItemLongClickListener(this);
    }
    /*onclick method to check entered value is exist or not*/
    /*If entered value is already exist in the list then it will show the message else it will added in the list*/
    public void onClick(View view) {
        String add_item=txt_ed.getText().toString();
        if(values.contains(add_item))
        {
            Toast.makeText(getBaseContext(),"Item Already Exist", Toast.LENGTH_LONG).show();
        }
        else
        {
            /*value added in the list*/
            values.add(add_item);
            adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,values);
            item_list.setAdapter(adapter);
            txt_ed.setText("");
            Toast.makeText(getBaseContext(),"Done", Toast.LENGTH_LONG).show();
        }
    }

    /* onitemlongclick method is use to delete any entry from the list*/
    /* If user want to delete any entry from the list then user will hold on that entry and then they will get a dialogue box */
    /*If user click on OK then that entry will delete from the list.*/
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
        final int removing_item=position;
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this); // Ask the user to get the confirmation before deleting an item from the listView
        builder.setMessage("Do you want to delete").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                values.remove(removing_item);
                adapter.notifyDataSetChanged();
                Toast.makeText(getBaseContext(), "Item Deleted", Toast.LENGTH_LONG).show();
            }
        }).setNegativeButton("Cancel", null).show();
        return true;
    }
}