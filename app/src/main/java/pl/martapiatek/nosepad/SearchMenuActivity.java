package pl.martapiatek.nosepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SearchMenuActivity extends AppCompatActivity {

    private Button btnSearchByName;
    private Button btnSearchByNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_menu);



        btnSearchByName = (Button) findViewById(R.id.btnSearchByName);

        btnSearchByName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(), SearchActivity.class);
                startActivity(Intent);
            }
        });


        btnSearchByNotes = (Button) findViewById(R.id.btnSearchByNotes);

        btnSearchByNotes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(), SearchByBrandActivity.class);
                startActivity(Intent);
            }
        });


    }
}
