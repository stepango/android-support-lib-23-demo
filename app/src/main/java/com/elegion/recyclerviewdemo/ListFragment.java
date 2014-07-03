package com.elegion.recyclerviewdemo;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.activeandroid.content.ContentProvider;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private RecyclerView recycler;
    private RecyclerCursorAdapter adapter;
    private LinearLayoutManager layoutManager;

    public ListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fmt_main, container, false);
        recycler = (RecyclerView) rootView.findViewById(R.id.list);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recycler.setHasFixedSize(true);
        adapter = new RecyclerCursorAdapter(null, R.layout.li_text, R.id.text, MyData.NAME);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(layoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        adapter.setOnItemClickListener(new RecyclerCursorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder view) {
                Toast.makeText(getActivity(), String.valueOf(view.getPosition()), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), ImagePaletteActivity.class));
            }
        });

        getLoaderManager().initLoader(R.id.demo_loader, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(),
                ContentProvider.createUri(MyData.class, null),
                null, null, null, null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (loader.getId() == R.id.demo_loader) {
            adapter.swapCursor(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
