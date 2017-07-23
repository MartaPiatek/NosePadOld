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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchByRatingActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView mListView;
    private NosePadDbAdapter mDbAdapter;
    private NosePadSimpleCursorAdapter mCursorAdapter;
    private Button btnSearchRating;
    private RadioGroup  radioGroup;
    private RadioButton radioButton;
    String radioCategory, rating1, rating2;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_rating);

        btnSearchRating = (Button) findViewById(R.id.btnSearchRating);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);

        mListView = (ListView) findViewById(R.id.reviewsBrand_list_view);

        // sprawdź które radio z  grupy wybrane
        mListView.setDivider(null);
        mDbAdapter = new NosePadDbAdapter(this);
        mDbAdapter.open();

        btnSearchRating.setOnClickListener(this);

       mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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
                        // edycja
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

                            // usunięcie
                        } else {
                            mDbAdapter.deleteReviewById(getIdFromPosition(masterListPosition));

                            String[] ratings = chechRadioButton();
                            mCursorAdapter.changeCursor(mDbAdapter.fetchReviewByRating(ratings[0],ratings[1]));
                        }
                        dialog.dismiss();
                    }
                });
            }
        });

    }

    @Override
    public void onClick(View view) {

        String[] ratings = chechRadioButton();

        switch (view.getId()) {

           case R.id.btnSearchRating:

               cursor = mDbAdapter.fetchReviewByRating(ratings[0],ratings[1]);

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
                       SearchByRatingActivity.this,
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

               mListView.setAdapter(mCursorAdapter);

               Toast.makeText(getApplicationContext(), "Wyszukiwanie", Toast.LENGTH_SHORT).show();
               break;
       }

    }

    private String[] chechRadioButton() {
        int selectedId = radioGroup.getCheckedRadioButtonId();

        radioButton = (RadioButton) findViewById(selectedId);

        radioCategory = (String) radioButton.getText();

        switch (radioCategory) {

            case "0-2":
                rating1 = "0";
                rating2 = "2";
                break;

            case "3-5":
                rating1 = "3";
                rating2 = "5";
                break;

            case "6-8":
                rating1 = "6";
                rating2 = "8";
                break;

            case "9-10":
                rating1 = "9";
                rating2 = "10";
                break;
        }
        String[] ratings= {rating1, rating2};

        return ratings;
    }


    private int getIdFromPosition(int nC)
    {
        return (int)mCursorAdapter.getItemId(nC);
    }

}