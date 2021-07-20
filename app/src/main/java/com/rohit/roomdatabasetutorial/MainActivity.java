package com.rohit.roomdatabasetutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rohit.roomdatabasetutorial.Room.MyDatabase;
import com.rohit.roomdatabasetutorial.Room.Student;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText first,last,className,updateName,updateId,deleteId;
    Button btnInsert,update,delete;
    MyDatabase myDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        first=findViewById(R.id.first);
        last=findViewById(R.id.last);
        className=findViewById(R.id.className);
        updateName=findViewById(R.id.updatename);
        updateId=findViewById(R.id.updateid);
        btnInsert=findViewById(R.id.Insert);
        update=findViewById(R.id.update);
        deleteId=findViewById(R.id.deleteid);
        delete=findViewById(R.id.delete);
        setUpData();
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student=new Student(first.getText().toString(),last.getText().toString(),className.getText().toString());


                myDatabase.dao().studentInsertion(student);
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabase.dao().update(updateName.getText().toString(), Integer.parseInt(updateId.getText().toString()));
                Toast.makeText(MainActivity.this, "updated", Toast.LENGTH_SHORT).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabase.dao().delete(Integer.parseInt(deleteId.getText().toString()));
                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void setUpData(){
        myDatabase= Room.databaseBuilder(MainActivity.this,MyDatabase.class,"StudentDb")
                .allowMainThreadQueries().build();
    }

    public void read(View view) {
        List<Student> stuData=myDatabase.dao().getStudent();
        for (int i=0;i<stuData.size();i++){
            Log.i("Student_data",String.valueOf(stuData.get(i).getStuId()+": "+
                    stuData.get(i).getStuFirstName()+":"+
                    stuData.get(i).getStuLastName()+":"+
                    stuData.get(i).getStuClass()));
        }
    }
}
