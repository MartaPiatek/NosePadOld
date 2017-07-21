package pl.martapiatek.nosepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SearchMenuActivity extends AppCompatActivity {

    private Button btnSearchByName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_menu);



        btnSearchByName = (Button) findViewById(R.id.btnSearchByName);

        btnSearchByName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), SearchActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });


    }
}
