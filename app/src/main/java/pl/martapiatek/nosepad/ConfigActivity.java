package pl.martapiatek.nosepad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ConfigActivity extends AppCompatActivity {

    private NosePadDbAdapter mDbAdapter;
    private Button btnRemove, btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        mDbAdapter = new NosePadDbAdapter(this);
        btnRemove = (Button)findViewById(R.id.btnRemove);

        btnRemove.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                mDbAdapter.open();
                mDbAdapter.deleteAllReminders();
                mDbAdapter.close();

                Toast.makeText(getApplicationContext(), "Usunięto", Toast.LENGTH_SHORT).show();
            }
        });


        btnAdd = (Button)findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                mDbAdapter.open();
                insertSomeReviews();
                mDbAdapter.close();

                Toast.makeText(getApplicationContext(), "Dodano", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void insertSomeReviews() {
        mDbAdapter.createReview("Prada", "Pradowska", "Cytryna", "Spoko",3);
        mDbAdapter.createReview("Armani", "Pierdani", "Limonka", "OK",10);
        mDbAdapter.createReview("Armani", "Siedzani", "Cytryna", "Spoko",1);
        mDbAdapter.createReview("Chanel", "No 5", "cytruski", "Spoko",8);
        mDbAdapter.createReview("Penhaligon's", "Vaara", "smrodki mojej Klejnotki", "OK",5);
        mDbAdapter.createReview("Armani", "Mani", "słodziaki pierdziaki", "Super",9);

    }
}
