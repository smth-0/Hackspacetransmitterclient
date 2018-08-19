package com.example.lunar.hackspace;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button justlogin;
    Button loginviagoto;
    private String SAVED_TEXT = "TEGFORSHAREDPREFERENCESAPP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        justlogin=findViewById(R.id.buttonjustlogin);
        loginviagoto=findViewById(R.id.loginButton);

        if(loadText()=="ALREADY_LOGGED_IN"){
            DataSingleton.getInstance().setLoggedIn(true);
        } else if(loadText()=="NOT_ALREADY_LOGGED_IN"){
            DataSingleton.getInstance().setLoggedIn(false);
        }

        Log.d("LOGTAG","LOG:"+DataSingleton.getInstance().getIsLoggedIn());

        ///////////////////////////////////////////////////////
        loginviagoto.setEnabled(!   DataSingleton.getInstance().getIsLoggedIn());
        justlogin.setEnabled(DataSingleton.getInstance().getIsLoggedIn());


        ///////////////////////////////////////////////////////


        final Intent intent = new Intent(this,loginedActivity.class);

        if (DataSingleton.getInstance().getIsLoggedIn()) {
            startActivity(intent);
        }

        if(DataSingleton.getInstance().getIsLoggedIn()){
            justlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);
                }
            });
        }



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(DataSingleton.getInstance().getIsLoggedIn()){
            saveText("ALREADY_LOGGED_IN");
        } else {
            saveText("NOT_ALREADY_LOGGED_IN");
        }

    }

    void saveText(String text) {
        SharedPreferences sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, text);
        ed.commit();
    }

    String loadText() {
        SharedPreferences sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        return savedText;
    }

}
