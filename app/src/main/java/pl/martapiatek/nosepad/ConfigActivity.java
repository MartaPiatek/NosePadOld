package pl.martapiatek.nosepad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ConfigActivity extends AppCompatActivity {

    private NosePadDbAdapter mDbAdapter;
    private Button btnRemove;

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
    }
}
