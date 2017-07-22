package pl.martapiatek.nosepad;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchByNotesActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView mListView;
    private NosePadDbAdapter mDbAdapter;
    private NosePadSimpleCursorAdapter mCursorAdapter;
    private MultiAutoCompleteTextView txtNotes;
    private Button btnSearchNotes;
    Cursor cursor;
    String stext="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_notes);

        txtNotes = (MultiAutoCompleteTextView) findViewById(R.id.txtNotes);
        btnSearchNotes = (Button)findViewById(R.id.btnSearchNotes);

        btnSearchNotes.setOnClickListener(this);
        mListView = (ListView) findViewById(R.id.reviewsBrand_list_view);

// obsługa multiAutoCompleteTextView
        String[] notesArr = getResources().getStringArray(R.array.notes_list);
        List<String> notesList = Arrays.asList(notesArr);

        ArrayAdapter<String> adapterNotes = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, notesList);

        txtNotes.setThreshold(1); // liczba znaków do podpowiedzi
        txtNotes.setAdapter(adapterNotes);
        txtNotes.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer()); // ustawienie separatora


        mListView.setDivider(null);
        mDbAdapter = new NosePadDbAdapter(this);
        mDbAdapter.open();



        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int masterListPosition, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SearchByNotesActivity.this);
                ListView modeListView = new ListView(SearchByNotesActivity.this);
                String[] modes = new String[]{"Zobacz recenzję", "Usuń recenzję"};

                ArrayAdapter<String> modeAdapter = new ArrayAdapter<>(SearchByNotesActivity.this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, modes);

                modeListView.setAdapter(modeAdapter);
                builder.setView(modeListView);

                final Dialog dialog = builder.create();
                dialog.show();

                modeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // edycja przypomnienia
                        if (position == 0) {

                            Review review =  mDbAdapter.fetchReviewById(getIdFromPosition(masterListPosition));

                            Intent myIntent = new Intent(view.getContext(), EditReviewActivity.class);
                            myIntent.putExtra("ID", review.getId());
                            myIntent.putExtra("BRAND", review.getBrand().toString());
                            myIntent.putExtra("FRAGRANCE", review.getFragrance().toString());
                            myIntent.putExtra("NOTES", review.getNotes().toString());
                            myIntent.putExtra("REVIEW", review.getReviewDescription().toString());
                            myIntent.putExtra("RATING", review.getRating());
                            startActivity(myIntent);

                            // usunięcie przypomnienia
                        } else {
                            mDbAdapter.deleteReviewById(getIdFromPosition(masterListPosition));
                            stext = txtNotes.getText().toString();
                            mCursorAdapter.changeCursor(mDbAdapter.fetchReviewByNotes(stext));
                        }
                        dialog.dismiss();
                    }
                });

            }
        });





    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnSearchNotes:

                stext = txtNotes.getText().toString();
                cursor = mDbAdapter.fetchReviewByNotes(stext);

                // z kolumn zdefiniowanych w bazie danych
                String[] from = new String[]{
                        NosePadDbAdapter.COL_BRAND, NosePadDbAdapter.COL_FRAGRANCE, NosePadDbAdapter.COL_NOTES,
                        NosePadDbAdapter.COL_REVIEW, NosePadDbAdapter.COL_RATING

                };

                // do identyfikatorów widoków w układzie graficznym
                int[] to = new int[]{
                        R.id.row_text,
                        R.id.row_fragrance
                };

                mCursorAdapter = new NosePadSimpleCursorAdapter(
                        // kontekst
                        SearchByNotesActivity.this,
                        // układ graficzny wiersza
                        R.layout.reviews_row,
                        // kursor
                        cursor,
                        // z kolumn zdefiniowanych w bazie danych
                        from,
                        // do identyfikatorów widoków w układzie graficznym
                        to,
                        // znacznik - nieużywany
                        0);

                // cursorAdapter (kontroler) aktualizuje listView (widok)
                // danymi z bazy danych (model)


                mListView.setAdapter(mCursorAdapter);
                Toast.makeText(getApplicationContext(), "Wyszukiwanie", Toast.LENGTH_SHORT).show();
                break;


        }
    }


    private int getIdFromPosition(int nC) {
        return (int)mCursorAdapter.getItemId(nC);
    }


}