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

public class EditReviewActivity extends AppCompatActivity {


        private ImageButton btnStar1, btnStar2, btnStar3, btnStar4, btnStar5, btnStar6, btnStar7, btnStar8, btnStar9, btnStar10;
        private MultiAutoCompleteTextView txtNotes;
        private AutoCompleteTextView txtBrand;
        private Button btnEdit;
        private NosePadDbAdapter mDbAdapter;
        private EditText editTextFragrance, editTextReview;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_edit_review);

            txtNotes = (MultiAutoCompleteTextView) findViewById(R.id.txtNotes);
            txtBrand = (AutoCompleteTextView) findViewById(R.id.txtBrand);

            btnEdit = (Button) findViewById(R.id.btnEdit);
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

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    txtBrand.setEnabled(true);
                    editTextFragrance.setEnabled(true);
                    txtNotes.setEnabled(true);
                    editTextReview.setEnabled(true);

                }
            });

            //TODO dodać przycisk zapisu zmian +aktualizcja bazy danych

            Bundle bundle = getIntent().getExtras();

            String sBrand = bundle.get("BRAND").toString();
            String sFragrance = bundle.get("FRAGRANCE").toString();
            String sNotes = bundle.get("NOTES").toString();
            String sReview = bundle.get("REVIEW").toString();

            txtBrand.setText(sBrand);
            txtBrand.setEnabled(false);

            editTextFragrance.setText(sFragrance);
            editTextFragrance.setEnabled(false);
            txtNotes.setText(sNotes);
            txtNotes.setEnabled(false);
            editTextReview.setText(sReview);
            editTextReview.setEnabled(false);

        } // onCreate



    }//class

