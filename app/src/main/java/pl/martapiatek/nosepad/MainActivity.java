package pl.martapiatek.nosepad;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Runnable{


    private Handler handler;
    private Dialog splashDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        showSplashScreen();
        handler = new Handler();
        AsyncTask.execute(this);




    }

    @Override
    protected void onPause() {
        super.onPause();
        dismissSplashScreen();
    }

    private void showSplashScreen() {
        splashDialog = new Dialog(this, R.style.splash_screen);
        splashDialog.setContentView(R.layout.activity_splash);
        splashDialog.setCancelable(false);
        splashDialog.show();
    }

    private void dismissSplashScreen() {
        if (splashDialog != null) {
            splashDialog.dismiss();
            splashDialog = null;
        }
    }
    @Override
    public void run() {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    dismissSplashScreen();

                }
            }, 5000
                );
    }
}
