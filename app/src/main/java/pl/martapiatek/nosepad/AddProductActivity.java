package pl.martapiatek.nosepad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MultiAutoCompleteTextView;

import java.util.Arrays;
import java.util.List;


import static pl.martapiatek.nosepad.R.drawable.star1;
import static pl.martapiatek.nosepad.R.drawable.star2;


public class AddProductActivity extends AppCompatActivity {

    private ImageButton btnStar1, btnStar2, btnStar3, btnStar4, btnStar5, btnStar6, btnStar7, btnStar8, btnStar9, btnStar10;
    private MultiAutoCompleteTextView txtNotes;
    private AutoCompleteTextView txtBrand;
    private Button btnSave;
    private NosePadDbAdapter mDbAdapter;
    private EditText editTextFragrance, editTextReview;

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
        editTextFragrance = (EditText) findViewById(R.id.editTextFragrance);
        editTextReview = (EditText) findViewById(R.id.editTextReview);


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

        btnStar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnStar1.setImageResource(star1);

            }
        });


        btnStar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnStar1.setImageResource(star1);
                btnStar2.setImageResource(star1);


                if(btnStar2.isSelected()){
                    btnStar1.isSelected();
                }

            }
        });

        btnStar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnStar1.setImageResource(star1);
                btnStar2.setImageResource(star1);
                btnStar3.setImageResource(star1);
            }
        });

        btnStar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnStar1.setImageResource(star1);
                btnStar2.setImageResource(star1);
                btnStar3.setImageResource(star1);
                btnStar4.setImageResource(star1);
            }
        });

        btnStar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnStar1.setImageResource(star1);
                btnStar2.setImageResource(star1);
                btnStar3.setImageResource(star1);
                btnStar4.setImageResource(star1);
                btnStar5.setImageResource(star1);
            }
        });

        btnStar6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnStar1.setImageResource(star1);
                btnStar2.setImageResource(star1);
                btnStar3.setImageResource(star1);
                btnStar4.setImageResource(star1);
                btnStar5.setImageResource(star1);
                btnStar6.setImageResource(star1);
            }
        });

        btnStar7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnStar1.setImageResource(star1);
                btnStar2.setImageResource(star1);
                btnStar3.setImageResource(star1);
                btnStar4.setImageResource(star1);
                btnStar5.setImageResource(star1);
                btnStar6.setImageResource(star1);
                btnStar7.setImageResource(star1);
            }
        });

        btnStar8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnStar1.setImageResource(star1);
                btnStar2.setImageResource(star1);
                btnStar3.setImageResource(star1);
                btnStar4.setImageResource(star1);
                btnStar5.setImageResource(star1);
                btnStar6.setImageResource(star1);
                btnStar7.setImageResource(star1);
                btnStar8.setImageResource(star1);
            }
        });

        btnStar9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnStar1.setImageResource(star1);
                btnStar2.setImageResource(star1);
                btnStar3.setImageResource(star1);
                btnStar4.setImageResource(star1);
                btnStar5.setImageResource(star1);
                btnStar6.setImageResource(star1);
                btnStar7.setImageResource(star1);
                btnStar8.setImageResource(star1);
                btnStar9.setImageResource(star1);
            }
        });

        btnStar10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    btnStar1.setImageResource(star1);
                    btnStar2.setImageResource(star1);
                    btnStar3.setImageResource(star1);
                    btnStar4.setImageResource(star1);
                    btnStar5.setImageResource(star1);
                    btnStar6.setImageResource(star1);
                    btnStar7.setImageResource(star1);
                    btnStar8.setImageResource(star1);
                    btnStar9.setImageResource(star1);
                    btnStar10.setImageResource(star1);
            }
        });



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDbAdapter.open();

                mDbAdapter.createReview("Chanel", "No 8", "cytruski", "Spoko",3);
                mDbAdapter.createReview(txtBrand.getText().toString(),
                        editTextFragrance.getText().toString(),
                        txtNotes.getText().toString(),
                        editTextReview.getText().toString(),6);
            }
        });


    } // onCreate





}//class
