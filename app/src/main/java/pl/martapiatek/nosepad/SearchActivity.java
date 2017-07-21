package pl.martapiatek.nosepad;

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
        if (savedInstanceState == null) {
            // wyczyść wszystkie dane
           // mDbAdapter.deleteAllReminders();
            // dodaj przykładowe dane
            insertSomeReviews();
        }

        Cursor cursor = mDbAdapter.fetchAllReviews();

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

        // cursorAdapter (kontroler) aktualizuje listView (widok)
        // danymi z bazy danych (model)
        mListView.setAdapter(mCursorAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int masterListPosition, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
                ListView modeListView = new ListView(SearchActivity.this);
                String[] modes = new String[]{"Edycja recenzji", "Usunięcie recenzji"};

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
                    /*    if (position == 0) {
                            int nId = getIdFromPosition(masterListPosition);
                            Review review = mDbAdapter.fetchReviewById(nId);
                            fireCustomDialog(review);
                            // usunięcie przypomnienia
                        } else {
                            mDbAdapter.deleteReminderById(getIdFromPosition(masterListPosition));
                            mCursorAdapter.changeCursor(mDbAdapter.fetchAllReminders());
                        }*/
                        dialog.dismiss();
                    }
                });

            }
        });



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
