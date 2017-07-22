package pl.martapiatek.nosepad;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements Runnable{


    private Handler handler;
    private Dialog splashDialog;
    private Button btnAddReview, btnReviews, btnContact, btnAbout, btnConfig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        showSplashScreen();
        handler = new Handler();
        AsyncTask.execute(this);

        btnAddReview = (Button) findViewById(R.id.btnAddReview);

        btnAddReview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), AddProductActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });


        btnReviews = (Button) findViewById(R.id.btnReviews);

        btnReviews.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), SearchMenuActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        btnConfig = (Button) findViewById(R.id.btnConfig);

        btnConfig.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ConfigActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
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
