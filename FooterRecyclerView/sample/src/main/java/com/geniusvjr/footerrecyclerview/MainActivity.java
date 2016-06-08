package com.geniusvjr.footerrecyclerview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final Class<?>[] ACTIVITY = {LinearLayoutActivity.class, EndlessLinearLayoutActivity.class, EndlessGridLayoutActivity.class, EndlessStaggeredGridLayoutActivity.class};
    private static final String[] TITLE = {"LinearLayoutSample", "EndlessLinearLayoutActivity", "EndlessGridLayoutActivity", "EndlessStaggeredGridLayoutActivity"};

    private RecyclerView mRecyclerView = null;

    private DataAdapter mDataAdapter = null;
    private ArrayList<ListItem> mDataList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mDataList = new ArrayList<>();

        /**
         * 给ListItem属性赋值
         */
        for (int i = 0; i < TITLE.length; i++) {

            ListItem item = new ListItem();
            item.title = TITLE[i];
            item.activity = ACTIVITY[i];
            mDataList.add(item);
        }
        mDataAdapter = new DataAdapter(this);
        mDataAdapter.setData(mDataList);
        mRecyclerView.setAdapter(mDataAdapter);
    }

    private static class ListItem{
        public String title;
        public Class<?> activity;
    }

    private class DataAdapter extends RecyclerView.Adapter{

        private LayoutInflater mLayoutInflater;
        private ArrayList<ListItem> mDataList = new ArrayList<>();

        public DataAdapter(Context context) {
            mLayoutInflater = LayoutInflater.from(context);
        }

        public void setData(ArrayList<ListItem> list) {
            this.mDataList = list;
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(mLayoutInflater.inflate(R.layout.sample_item_text, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ListItem listItem = mDataList.get(position);

            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.textView.setText(listItem.title);
        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }

        private class ViewHolder extends RecyclerView.ViewHolder{

            private TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.info_text);

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }
    }
}
