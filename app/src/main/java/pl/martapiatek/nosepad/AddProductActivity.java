package pl.martapiatek.nosepad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.MultiAutoCompleteTextView;

import java.util.Arrays;
import java.util.List;

public class AddProductActivity extends AppCompatActivity {

    private ImageButton btnStar1, btnStar2, btnStar3, btnStar4, btnStar5, btnStar6, btnStar7, btnStar8, btnStar9, btnStar10;
    private MultiAutoCompleteTextView txtNotes;
    private AutoCompleteTextView txtBrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);



        // obsługa multiAutoCompleteTextView
        String[] notesArr = getResources().getStringArray(R.array.notes_list);
        List<String> notesList = Arrays.asList(notesArr);

        ArrayAdapter<String> adapterNotes = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, notesList);

        txtNotes = (MultiAutoCompleteTextView) findViewById(R.id.txtNotes);

        txtNotes.setThreshold(1); // liczba znaków do podpowiedzi
        txtNotes.setAdapter(adapterNotes);
        txtNotes.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer()); // ustawienie separatora


        // obsługa AutoCompleteTextView
        String[] brandsArr = getResources().getStringArray(R.array.brand_list); //pobranie listy
        List<String> brandsList = Arrays.asList(brandsArr);

        ArrayAdapter<String> adapterBrand = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, brandsList);

        txtBrand = (AutoCompleteTextView) findViewById(R.id.txtBrand);
        txtBrand.setThreshold(1); // liczba znaków do podpowiedzi
        txtBrand.setAdapter(adapterBrand);




    }




}
