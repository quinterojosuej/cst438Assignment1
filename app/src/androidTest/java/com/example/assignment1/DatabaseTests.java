package com.example.assignment1;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.assignment1.db.AppDatabase;
import com.example.assignment1.db.UserDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DatabaseTests {

//    The following is needed to test the database/room.
//    So is the before. The after is there to close the database
    private UserDAO userDAO;
    private AppDatabase db;

    @Before
    public void createDB(){
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        userDAO = db.getUserDAO();
    }

    @After
    public void closeDB() throws IOException{
        db.close();
    }

//    Test to see if we can interact with the database
    @Test
    public void tryInsert() throws Exception{
        User user = new User("Michael", "nonododo");
        userDAO.insert(user);

        boolean temp;
        if(userDAO.userNameCount("Michael") > 0){
            temp = true;
        }
        else{
            temp = false;
        }

        assertTrue(temp);
    }

//    We get the user and determine if the query returns a user, then it exists
    @Test
    public void verifyUser() throws Exception{
        User user = new User("Misael", "Guijarro");
        userDAO.insert(user);

        boolean temp;

        if(userDAO.getUserByUsername("Misael") == null){
            temp = false;
        }
        else{
            temp = true;
        }

        assertTrue(temp);
    }

//    To verify the password we need to know if the user exists, then we
//    can verify the password of the inserted user
    @Test
    public void verifyPassword() throws Exception{
        User user = new User("Miguel", "Espitia");
        userDAO.insert(user);

        boolean temp;

        if(userDAO.getUserByUsername("Miguel").getUserPassword().equals("Espitia")){
            temp = true;
        }
        else{
            temp = false;
        }

        assertTrue(temp);
    }

}
