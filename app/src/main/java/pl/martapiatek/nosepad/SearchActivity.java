package pl.martapiatek.nosepad;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.app.Dialog;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;

public class SearchActivity extends AppCompatActivity {

    private ListView mListView;
    private NosePadDbAdapter mDbAdapter;
    private NosePadSimpleCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mListView = (ListView) findViewById(R.id.reminders_list_view);
        mListView.setDivider(null);
        mDbAdapter = new NosePadDbAdapter(this);
        mDbAdapter.open();

        Cursor cursor = mDbAdapter.fetchAllReviews();

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
                SearchActivity.this,
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

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int masterListPosition, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
                ListView modeListView = new ListView(SearchActivity.this);
                String[] modes = new String[]{"Zobacz recenzję", "Usuń recenzję"};

                ArrayAdapter<String> modeAdapter = new ArrayAdapter<>(SearchActivity.this,
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
                            mCursorAdapter.changeCursor(mDbAdapter.fetchAllReviews());
                        }
                        dialog.dismiss();
                    }
                });

            }
        });

    }

    private int getIdFromPosition(int nC) {
        return (int)mCursorAdapter.getItemId(nC);
    }

}
