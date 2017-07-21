package pl.martapiatek.nosepad;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
/**
 * Created by Marta on 21.07.2017.
 */

public class NosePadSimpleCursorAdapter extends SimpleCursorAdapter{


    public NosePadSimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return super.newView(context, cursor, parent);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);
        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder == null) {
            holder = new ViewHolder();
            holder.colRating = cursor.getColumnIndexOrThrow(NosePadDbAdapter.COL_RATING);


            holder.listTab = view.findViewById(R.id.row_tab);
            view.setTag(holder);
        }
        if (cursor.getInt(holder.colRating) <5) {
            holder.listTab.setBackgroundColor(
                    context.getResources().getColor(R.color.colorAccent));
        } else {
            holder.listTab.setBackgroundColor(
                    context.getResources().getColor(R.color.back2));
        }
    }
    static class ViewHolder{
        //zapamiętanie indeksu kolumny
        int colRating;
        //zapamiętanie widoku
        View listTab;

    }
}
