package com.elegion.recyclerviewdemo;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by stepangoncarov on 03/07/14.
 */
public class RecyclerCursorAdapter
        extends RecyclerView.Adapter<RecyclerCursorAdapter.ViewHolder>
        implements View.OnClickListener {

    private int itemLayout;
    private final int textViewId;
    private final String columnName;
    private Cursor cursor;

    private int columnIndex;
    private OnItemClickListener listener;

    public RecyclerCursorAdapter(@Nullable Cursor cursor,
                                 int itemLayout,
                                 int textViewId,
                                 @NonNull String columnName) {
        this.cursor = cursor;
        this.itemLayout = itemLayout;
        this.textViewId = textViewId;
        this.columnName = columnName;
        init();
    }

    private void init() {
        if (cursor != null) {
            columnIndex = cursor.getColumnIndex(columnName);
        } else {
            columnIndex = -1;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v, textViewId);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (cursor == null) return;
        cursor.moveToPosition(position);
        holder.text.setText(cursor.getString(columnIndex));
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        if (cursor == null) return 0;
        return cursor.getCount();
    }

    public void swapCursor(Cursor data) {
        cursor = data;
        init();
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onItemClick((RecyclerView.ViewHolder) v.getTag());
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;

        public ViewHolder(View itemView, int textViewId) {
            super(itemView);
            itemView.setTag(this);
            text = (TextView) itemView.findViewById(textViewId);
        }

    }

    public interface OnItemClickListener{
        void onItemClick(RecyclerView.ViewHolder view);
    }
}
