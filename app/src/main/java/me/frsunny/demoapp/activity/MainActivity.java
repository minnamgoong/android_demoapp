package me.frsunny.demoapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import me.frsunny.demoapp.R;
import me.frsunny.demoapp.adapter.ContentAdapter;

public class MainActivity extends AppCompatActivity {
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private static final int COLUMN_COUNT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateUi();
    }

    private void populateUi() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(COLUMN_COUNT, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(mStaggeredGridLayoutManager);

        ContentAdapter contentAdapter = new ContentAdapter(this);
        recyclerView.setAdapter(contentAdapter);
    }
}
