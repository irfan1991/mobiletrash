package andara.heatcliff.yuclean;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;

import andara.heatcliff.yuclean.Login.LoginActivity;
//import andara.heatcliff.yuclean.Login.LoginActivityTest;

/**
 * Created by Isfahani on 10-Agu-16.
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SQLiteHandler sqLiteHandler = new SQLiteHandler(this);
        final HashMap<String, String> user = sqLiteHandler.getUserDetails();

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (!user.toString().equals("{}")) {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}
