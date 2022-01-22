package com.madhumankatha.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edMsg;
    private Button btnAdd;
    private TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edMsg = findViewById(R.id.edMsg);
        btnAdd = findViewById(R.id.btnAdd);
        tvDisplay = findViewById(R.id.tvDisplay);

        display();

        btnAdd.setOnClickListener(v -> {
            String msg = edMsg.getText().toString();

            if (msg.isEmpty()){
                Toast.makeText(MainActivity.this, "Please enter a message!!", Toast.LENGTH_SHORT).show();
            }else {
                DBClient.getInstance(MainActivity.this)
                        .getAppDatabase()
                        .todoDao()
                        .insert(new Todo(msg));

                display();
            }
        });

    }

    private void display(){
        StringBuilder result = new StringBuilder();

        DBClient.getInstance(MainActivity.this)
                .getAppDatabase()
                .todoDao()
                .getAll()
                .forEach(data -> {
                    if (data.getId() == 1){
                        Todo update = data;
                        update.setMsg("Good Afternoon!!");

                        DBClient.getInstance(MainActivity.this).getAppDatabase()
                                .todoDao().update(update);
                    }

                    if(data.getId() == 4){
                        Todo delete = data;

                        DBClient.getInstance(MainActivity.this).getAppDatabase().todoDao().delete(delete);
                    }


                    result.append(data.getId()).append(" ");
                    result.append(data.getMsg());
                    result.append("\n");
                });



        tvDisplay.setText(result);
    }
}