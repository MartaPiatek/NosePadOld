package pl.martapiatek.nosepad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marta on 21.07.2017.
 */

public class NosePadDbAdapter {


    //nazwy kolumn
    public static final String COL_ID = "_id";
    public static final String COL_BRAND = "brand";
    public static final String COL_FRAGRANCE = "fragrance";
    public static final String COL_NOTES = "notes";
    public static final String COL_REVIEW = "reviewDescription";
    public static final String COL_RATING = "rating";


    //indeksy

    public static final int INDEX_ID = 0;
    public static final int INDEX_BRAND = INDEX_ID + 1;
    public static final int INDEX_FRAGRANCE = INDEX_ID + 2;
    public static final int INDEX_NOTES = INDEX_ID + 3;
    public static final int INDEX_REVIEW = INDEX_ID + 4;
    public static final int INDEX_RATING = INDEX_ID + 5;

    //dziennik zdarzeń
    public static final String TAG = "NosePadDbAdapter";

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "dba_nosepad";
    private static final String TABLE_NAME = "tbl_nosepad";
    private static final int DATABASE_VERSION = 1;

    private Context mCtx;

    //SQL do utworzenia bazy danych
    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + TABLE_NAME + " ( " +
                    COL_ID + " INTEGER PRIMARY KEY autoincrement, " +
                    COL_BRAND + " TEXT, " +
                    COL_FRAGRANCE + " TEXT, " +
                    COL_NOTES + " TEXT, " +
                    COL_REVIEW + " TEXT, " +
                    COL_RATING + " INTEGER );";


    public NosePadDbAdapter(Context ctx) {
        mCtx = ctx;
    }

    // otwarcie
    public void open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
    }

    // zamknięcie
    public void close() {
        if(mDbHelper != null){
            mDbHelper.close();
        }
    }




    private static class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(Context context) {
            super(context,DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            Log.w(TAG, DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.w(TAG, "Aktualizacja bazy danych z wersji "+ oldVersion + " do wersji " +
                    newVersion + " , co powoduje wyczyszczenie zawartości bazy danych.");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    // TWORZENIE
    public void createReview(String brand, String fragrance, String notes, String review, int rating){
        ContentValues values = new ContentValues();
        values.put(COL_BRAND, brand);
        values.put(COL_FRAGRANCE, fragrance);
        values.put(COL_NOTES, notes);
        values.put(COL_REVIEW, review);
        values.put(COL_RATING, rating);
        mDb.insert(TABLE_NAME, null, values);
    }

    //przeciążanie pobierające obiekt review
    public long createReview(Review review){
        ContentValues values = new ContentValues();
        values.put(COL_BRAND, review.getBrand());
        values.put(COL_FRAGRANCE, review.getFragrance());
        values.put(COL_NOTES, review.getNotes());
        values.put(COL_REVIEW, review.getReviewDescription());
        values.put(COL_RATING, review.getRating());
        return mDb.insert(TABLE_NAME, null, values);
    }


    // ODCZYT
    public Review fetchReviewById(int id) {

        Cursor cursor = mDb.query(TABLE_NAME, new String[]{COL_ID,
                        COL_BRAND, COL_FRAGRANCE, COL_NOTES, COL_REVIEW, COL_RATING}, COL_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return new Review(
                cursor.getInt(INDEX_ID),
                cursor.getString(INDEX_BRAND),
                cursor.getString(INDEX_FRAGRANCE),
                cursor.getString(INDEX_NOTES),
                cursor.getString(INDEX_REVIEW),
                cursor.getInt(INDEX_RATING)
        );
    }


    public Cursor fetchReviewByBrand(String brand){

        List<Review> list;
        list = new ArrayList<>();


        Cursor cursor = mDb.query(TABLE_NAME, new String[]{COL_ID,
                        COL_BRAND, COL_FRAGRANCE, COL_NOTES, COL_REVIEW, COL_RATING}, COL_BRAND + "=?",
                new String[]{String.valueOf(brand)}, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();

     /*   while (cursor.moveToNext()) {
            Review r = new Review(
                    cursor.getInt(INDEX_ID),
                    cursor.getString(INDEX_BRAND),
                    cursor.getString(INDEX_FRAGRANCE),
                    cursor.getString(INDEX_NOTES),
                    cursor.getString(INDEX_REVIEW),
                    cursor.getInt(INDEX_RATING)
            );

            list.add(r);
        }
        cursor.close();
        return list;*/
        return cursor;
    }



    public Cursor fetchReviewByNotes(String note){

        Cursor cursor = mDb.query(TABLE_NAME, new String[]{COL_ID,
                        COL_BRAND, COL_FRAGRANCE, COL_NOTES, COL_REVIEW, COL_RATING}, COL_NOTES + "=?",
                new String[]{String.valueOf(note)}, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();

        return cursor;
    }

    public Cursor fetchReviewByRating(int rating){

       Cursor cursor = mDb.query(TABLE_NAME, new String[]{COL_ID,
                        COL_BRAND, COL_FRAGRANCE, COL_NOTES, COL_REVIEW, COL_RATING}, COL_RATING + "=?",
              new String[]{String.valueOf(rating)}, null, null, null, null);
        if(cursor != null)
                  cursor.moveToFirst();






        return cursor;
    }


    public Cursor fetchAllReviews(){
        Cursor mCursor = mDb.query(TABLE_NAME, new String[]{COL_ID,
                        COL_BRAND, COL_FRAGRANCE, COL_NOTES, COL_REVIEW, COL_RATING},
                null, null, null, null, COL_BRAND);
        if(mCursor != null)
            mCursor.moveToFirst();

        return mCursor;
    }

    // AKTUALIZACJA
    public void updateReview(Review review){
        ContentValues values = new ContentValues();
        values.put(COL_BRAND, review.getBrand());
        values.put(COL_FRAGRANCE, review.getFragrance());
        values.put(COL_NOTES, review.getNotes());
        values.put(COL_REVIEW, review.getReviewDescription());
        values.put(COL_RATING, review.getRating());
        mDb.update(TABLE_NAME, values,
                COL_ID + "=?", new String[]{String.valueOf(review.getId())});
    }

    // USUNIĘCIE
    public void deleteReviewById(int nId){
        mDb.delete(TABLE_NAME, COL_ID + "=?", new String[]{String.valueOf(nId)});
    }

    public void deleteAllReminders(){
        mDb.delete(TABLE_NAME, null, null);
    }
}
