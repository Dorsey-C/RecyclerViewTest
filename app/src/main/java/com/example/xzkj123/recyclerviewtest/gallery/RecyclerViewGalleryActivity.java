package com.example.xzkj123.recyclerviewtest.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.xzkj123.recyclerviewtest.MainActivity;
import com.example.xzkj123.recyclerviewtest.R;
import com.example.xzkj123.recyclerviewtest.adapter.GalleryAdapter;
import com.example.xzkj123.recyclerviewtest.customview.CustomerImageViewActivity;
import com.example.xzkj123.recyclerviewtest.customview.CustomerViewActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewGalleryActivity extends AppCompatActivity {

    @BindView(R.id.id_recyclerview_horizontal)
    CopyOfMyRecyclerView idRecyclerviewHorizontal;
    @BindView(R.id.id_content)
    ImageView idContent;
    private GalleryAdapter mAdapter;
    private List<Integer> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_gallery);
        ButterKnife.bind(this);
        initDatas();
        //得到控件  
        idRecyclerviewHorizontal = (CopyOfMyRecyclerView) findViewById(R.id.id_recyclerview_horizontal);
        //设置布局管理器  
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        idRecyclerviewHorizontal.setLayoutManager(linearLayoutManager);
        //设置适配器  
        mAdapter = new GalleryAdapter(this, mDatas);
        idRecyclerviewHorizontal.setOnItemScrollChangeListener(new CopyOfMyRecyclerView.OnItemScrollChangeListener()
        {
            @Override
            public void onChange(View view, int position)
            {
                idContent.setImageResource(mDatas.get(position));
            };
        });
        idRecyclerviewHorizontal.setOnItemScrollChangeListener(new CopyOfMyRecyclerView.OnItemScrollChangeListener()
        {
            @Override
            public void onChange(View view, int position)
            {
                idContent.setImageResource(mDatas.get(position));
            };
        });
        mAdapter.setOnItemClickLitener(new GalleryAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                idContent.setImageResource(mDatas.get(position));
                Toast.makeText(RecyclerViewGalleryActivity.this, position + "", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        idRecyclerviewHorizontal.setAdapter(mAdapter);
        idContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(RecyclerViewGalleryActivity.this, CustomerViewActivity.class));
                startActivity(new Intent(RecyclerViewGalleryActivity.this, CustomerImageViewActivity.class));
            }
        });
    }

    private void initDatas() {
        mDatas = new ArrayList<Integer>(Arrays.asList(R.mipmap.a,
                R.mipmap.b, R.mipmap.c, R.mipmap.d, R.mipmap.e,
                R.mipmap.f, R.mipmap.g, R.mipmap.h, R.mipmap.l));
    }
}
