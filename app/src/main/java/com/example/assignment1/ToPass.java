package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.assignment1.db.AppDatabase;
import com.example.assignment1.db.UserDAO;

public class ToPass extends AppCompatActivity {

    public static final String TO_PASS_ID = "com.example.cst438_project1.ToPass";
    UserDAO myUserDAO;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_pass);

        myUserDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getUserDAO();

        get_screen();
    }

    public String get_user(int id){
        User temp = myUserDAO.getUserById(id);
        return temp.getUserName();
    }

//    To get the activity attributes
    public void get_screen(){
        textView = findViewById(R.id.textView2);

        Intent incoming = getIntent();
        int id = incoming.getIntExtra(TO_PASS_ID, -1);

        String temp = get_user(id);

        textView.setText("WELCOME! " + temp);

    }


//    Originally was passing an integer, found it too difficult, but
//    its harmless and left it
    public static Intent ToPassIntent(Context context, int userId){
        Intent intent = new Intent(context, ToPass.class);
        intent.putExtra(TO_PASS_ID, userId);
        return intent;
    }

//    The intent that is being tested is this more simplified one
    public static Intent BasicToPassIntent(Context context){
        Intent intent = new Intent(context, ToPass.class);
        return intent;
    }
}