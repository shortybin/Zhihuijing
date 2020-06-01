package com.bibi.wisdom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bibi.wisdom.adapter.CityListAdapter;
import com.bibi.wisdom.adapter.ListenerWithPosition;
import com.bibi.wisdom.utils.CityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CityListActivity extends AppCompatActivity {

    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private CityListAdapter cityListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        ButterKnife.bind(this);
        tvTopTitle.setText("城市列表");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cityListAdapter = new CityListAdapter(this);
        recyclerView.setAdapter(cityListAdapter);
        cityListAdapter.setOnClickListener(new ListenerWithPosition.OnClickWithPositionListener() {
            @Override
            public void onClick(View v, int position, Object holder) {
                Intent intent = new Intent();
                intent.putExtra("cityName",cityListAdapter.getData().get(position).getCity());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        cityListAdapter.addData(CityUtils.getCityList(this));
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
