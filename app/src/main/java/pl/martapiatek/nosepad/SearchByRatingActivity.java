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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchByRatingActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView mListView;
    private NosePadDbAdapter mDbAdapter;
    private NosePadSimpleCursorAdapter mCursorAdapter;
    private Button btnSearchRating;

    private AutoCompleteTextView txtRating;
    String stext="";
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_rating);

        txtRating = (AutoCompleteTextView) findViewById(R.id.txtRating);
        btnSearchRating = (Button) findViewById(R.id.btnSearchRating);

        mListView = (ListView) findViewById(R.id.reviewsBrand_list_view);

        // obsługa autocomplete
        String[] brandsArr = getResources().getStringArray(R.array.ratings_list); //pobranie listy
        List<String> brandsList = Arrays.asList(brandsArr);

        ArrayAdapter<String> adapterBrand = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, brandsList);

        txtRating.setThreshold(1); // liczba znaków do podpowiedzi
        txtRating.setAdapter(adapterBrand);



        mListView.setDivider(null);
        mDbAdapter = new NosePadDbAdapter(this);
        mDbAdapter.open();

        // cursor = mDbAdapter.fetchAllReviews();



        btnSearchRating.setOnClickListener(this);


      /*  mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int masterListPosition, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SearchByRatingActivity.this);
                ListView modeListView = new ListView(SearchByRatingActivity.this);
                String[] modes = new String[]{"Zobacz recenzję", "Usuń recenzję"};

                ArrayAdapter<String> modeAdapter = new ArrayAdapter<>(SearchByRatingActivity.this,
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
                            stext = txtRating.getText().toString();
                      //      mCursorAdapter.changeCursor(mDbAdapter.fetchReviewByRating(stext));
                        }
                        dialog.dismiss();
                    }
                });

            }
        });*/





    }


    @Override
    public void onClick(View view) {

     /*   switch (view.getId()) {

            case R.id.btnSearchBrand:

                stext = txtBrand.getText().toString();
                cursor = mDbAdapter.fetchReviewByBrand(stext);

                // z kolumn zdefiniowanych w bazie danych
                String[] from = new String[]{
                        NosePadDbAdapter.COL_BRAND, NosePadDbAdapter.COL_FRAGRANCE, NosePadDbAdapter.COL_NOTES,
                        NosePadDbAdapter.COL_REVIEW, NosePadDbAdapter.COL_RATING

                };

                // do identyfikatorów widoków w układzie graficznym
                int[] to = new int[]{
                        R.id.row_text
                };

                mCursorAdapter = new NosePadSimpleCursorAdapter(
                        // kontekst
                        SearchByBrandActivity.this,
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


        }*/
    }


    private int getIdFromPosition(int nC) {
        return (int)mCursorAdapter.getItemId(nC);
    }

    private void insertSomeReviews() {
        mDbAdapter.createReview("Chanel", "No 5", "cytruski", "Spoko",3);
        mDbAdapter.createReview("Penhaligon's", "Vaara", "smrodki mojej Klejnotki", "OK",5);
        mDbAdapter.createReview("Armani", "Mani", "słodziaki pierdziaki", "Super",10);

    }
}