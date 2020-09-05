package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment1.db.AppDatabase;
import com.example.assignment1.db.UserDAO;

public class MainActivity extends AppCompatActivity {

    UserDAO myUserDAO;

    TextView textView;
    EditText userName;
    EditText passWord;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myUserDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getUserDAO();


        check_users();
        get_screen();
    }

//    Used just in case
    public void toast_maker(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

//    Need to make sure the user is real
    public void check_users(){
        int count = myUserDAO.userNameCount("din_djarin");
        if(count == 0){
            User user = new User("din_djarin", "baby_yoda_ftw");
            myUserDAO.insert(user);
        }
    }
//    Make sure the user login attempt works
    public boolean user_exists(String str){
        int count = myUserDAO.userNameCount(str);
        if(count == 1){
            return true;
        }
        return false;
    }
//    Makes sure the user's password works
    public int user_id(String userName, String password){
        User user = myUserDAO.getUserByUsername(userName);
        if(user.getUserPassword().equals(password)){
            return user.getId();
        }
        else{
            return -1;
        }
    }
//    Original intent factory
    public void to_pass(int userId){
        Intent intent = ToPass.ToPassIntent(MainActivity.this, userId);
        Log.i("Moving view", "From Main to ToPass");
        startActivity(intent);
    }
//    New intent factory
    public void to_pass_basic(){
        Intent intent = ToPass.BasicToPassIntent(MainActivity.this);
        Log.i("Moving view", "From Main to ToPass");
        startActivity(intent);
    }
//    Get screen attributes and button activations
    void get_screen(){
        textView = findViewById(R.id.main1Txt);
        userName = findViewById(R.id.editTextTextPersonName);
        passWord = findViewById(R.id.editTextTextPassword);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user_exists(userName.getText().toString())){
                    userName.setBackgroundColor(Color.TRANSPARENT);
                    int temp = user_id(userName.getText().toString(), passWord.getText().toString());
                    if(temp > 0){
                        toast_maker("Welcome!");
//                        to_pass(temp);
                        to_pass_basic();
                    }
                    else{
                        passWord.setBackgroundColor(Color.parseColor("#b53c68"));
                        toast_maker("Password was Incorrect!");
                    }
                }
                else{
                    userName.setBackgroundColor(Color.parseColor("#b53c68"));
                    toast_maker("Username was Incorrect!");
                }
            }
        });
    }
}