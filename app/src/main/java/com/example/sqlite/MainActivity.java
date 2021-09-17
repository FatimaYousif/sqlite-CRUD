package com.example.sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, rollno;
    Button add,edit, delete,list, search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("student db - SQLite");

        name=(EditText)findViewById(R.id.name);
        rollno=(EditText)findViewById(R.id.rollno);

        edit=(Button)findViewById(R.id.edit);
        delete=(Button)findViewById(R.id.delete);
        list=(Button)findViewById(R.id.list);
        search=(Button)findViewById(R.id.search);
        add=(Button)findViewById(R.id.add);

        database db=new database(MainActivity.this);

        //add
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String name1=name.getText().toString();
            String rollno1=rollno.getText().toString();

            Boolean checkInsertData=db.insertData(name1, rollno1);

            if(checkInsertData==true)
            {
                Toast.makeText(MainActivity.this, "data inserted",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(MainActivity.this, "Insert failed",Toast.LENGTH_LONG).show();
            }

            }
        });


        //edit
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1=name.getText().toString();
                String rollno1=rollno.getText().toString();

                Boolean checkUpdateData=db.updateData(name1, rollno1);

                if(checkUpdateData==true)
                {
                    Toast.makeText(MainActivity.this, "data updated",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "update failed",Toast.LENGTH_LONG).show();
                }

            }
        });

        //delete
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1=name.getText().toString();

                Boolean checkDeleteData=db.deleteData(name1);

                if(checkDeleteData==true)
                {
                    Toast.makeText(MainActivity.this, "data deleted",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "delete failed",Toast.LENGTH_LONG).show();
                }

            }
        });

        //list
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cur=db.getData();
                if(cur.getCount()==0)
                {
                    Toast.makeText(MainActivity.this, "NO ENTRY",Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer stringBuffer=new StringBuffer();
                while(cur.moveToNext())
                {
                   stringBuffer.append("name: "+cur.getString(0)+"\n");
                   stringBuffer.append("rollno: "+cur.getString(1)+"\n");
                }

                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Student entries");
                builder.setMessage(stringBuffer.toString());
                builder.show();

            }
        });

        //search
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cur=db.searchData(name.getText().toString());

                if(cur.getCount()==0)
                {
                    Toast.makeText(MainActivity.this, "No results",Toast.LENGTH_LONG).show();
                    return;
                }


                StringBuffer stringBuffer=new StringBuffer();
                while(cur.moveToNext())
                {
                    stringBuffer.append("name: "+cur.getString(0)+"\n");
                    stringBuffer.append("rollno: "+cur.getString(1)+"\n");
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Student found");
                builder.setMessage(stringBuffer.toString());
                builder.show();

            }
        });

    }
}