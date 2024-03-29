package pl.martapiatek.nosepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static pl.martapiatek.nosepad.R.drawable.star1;
import static pl.martapiatek.nosepad.R.drawable.star2;


public class AddProductActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btnStar1, btnStar2, btnStar3, btnStar4, btnStar5, btnStar6, btnStar7, btnStar8, btnStar9, btnStar10;
    private MultiAutoCompleteTextView txtNotes;
    private AutoCompleteTextView txtBrand;
    private Button btnSave;
    private NosePadDbAdapter mDbAdapter;
    private EditText editTextFragrance, editTextReview;

    private int ratingStar=0;

     ArrayList<ImageButton> myButtons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        txtNotes = (MultiAutoCompleteTextView) findViewById(R.id.txtNotes);
        txtBrand = (AutoCompleteTextView) findViewById(R.id.txtBrand);
        btnStar1 = (ImageButton) findViewById(R.id.star1);
        btnStar2 = (ImageButton) findViewById(R.id.star2);
        btnStar3 = (ImageButton) findViewById(R.id.star3);
        btnStar4 = (ImageButton) findViewById(R.id.star4);
        btnStar5 = (ImageButton) findViewById(R.id.star5);
        btnStar6 = (ImageButton) findViewById(R.id.star6);
        btnStar7 = (ImageButton) findViewById(R.id.star7);
        btnStar8 = (ImageButton) findViewById(R.id.star8);
        btnStar9 = (ImageButton) findViewById(R.id.star9);
        btnStar10 = (ImageButton) findViewById(R.id.star10);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        editTextFragrance = (EditText) findViewById(R.id.editTextFragrance);
        editTextReview = (EditText) findViewById(R.id.editTextReview);

        myButtons.add(btnStar1);
        myButtons.add(btnStar2);
        myButtons.add(btnStar3);
        myButtons.add(btnStar4);
        myButtons.add(btnStar5);
        myButtons.add(btnStar6);
        myButtons.add(btnStar7);
        myButtons.add(btnStar8);
        myButtons.add(btnStar9);
        myButtons.add(btnStar10);

        for(ImageButton button :myButtons){
            button.setOnClickListener(this);
            button.setTag(Integer.valueOf(star2));
        }

        mDbAdapter = new NosePadDbAdapter(this);

        // obsługa multiAutoCompleteTextView
        String[] notesArr = getResources().getStringArray(R.array.notes_list);
        List<String> notesList = Arrays.asList(notesArr);

        ArrayAdapter<String> adapterNotes = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, notesList);

        txtNotes.setThreshold(1); // liczba znaków do podpowiedzi
        txtNotes.setAdapter(adapterNotes);
        txtNotes.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer()); // ustawienie separatora

        // obsługa AutoCompleteTextView
        String[] brandsArr = getResources().getStringArray(R.array.brand_list); //pobranie listy
        List<String> brandsList = Arrays.asList(brandsArr);

        ArrayAdapter<String> adapterBrand = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, brandsList);

        txtBrand.setThreshold(1); // liczba znaków do podpowiedzi
        txtBrand.setAdapter(adapterBrand);

    } // onCreate



    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.star1:
                rating(1);
                ratingStar=1;
                break;

            case R.id.star2:
                rating(2);
                ratingStar=2;
                break;

            case R.id.star3:
                rating(3);
                ratingStar=3;
                break;

            case R.id.star4:
                rating(4);
                ratingStar=4;
                break;

            case R.id.star5:
                rating(5);
                ratingStar=5;
                break;

            case R.id.star6:
                rating(6);
                ratingStar=6;
                break;

            case R.id.star7:
                rating(7);
                ratingStar=7;
                break;

            case R.id.star8:
                rating(8);
                ratingStar=8;
                break;

            case R.id.star9:
                rating(9);
                ratingStar=9;
                break;

            case R.id.star10:
                rating(10);
                ratingStar=10;
                break;

            case R.id.btnSave:
                mDbAdapter.open();
                //  mDbAdapter.createReview("Chanel", "No 8", "cytruski", "Spoko",3);
                mDbAdapter.createReview(txtBrand.getText().toString(),
                        editTextFragrance.getText().toString(),
                        txtNotes.getText().toString(),
                        editTextReview.getText().toString(),
                        ratingStar);
                Toast.makeText(getApplicationContext(), "Zapis", Toast.LENGTH_LONG).show();
                mDbAdapter.close();

                break;

        }
    }

    private void rating(int n){
    int j=0;
        for(int i=0;i<n;i++){


            int drawable = (Integer) myButtons.get(i).getTag();
            switch (drawable) {

                case star1:
                    for(j=0;j<=i;j++){
                        myButtons.get(j).setImageResource(star2);
                        myButtons.get(j).setTag(Integer.valueOf(star2));
                    }


                    break;

                case star2:
                    for(j=0;j<=i;j++) {
                        myButtons.get(j).setImageResource(star1);
                        myButtons.get(j).setTag(Integer.valueOf(star1));
                    }
                    break;

            }

        }

    }



}//class
