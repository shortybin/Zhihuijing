package com.bibi.wisdom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bibi.wisdom.adapter.FifiteenWeahterAdatper;
import com.bibi.wisdom.bean.FifteenWeahterBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FifteenWeahterActivity extends AppCompatActivity {

    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private FifiteenWeahterAdatper fifiteenWeahterAdatper;

    private FifteenWeahterBean.DataBean data;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifteen_weather);
        ButterKnife.bind(this);
        tvTopTitle.setText("未来15天天气");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fifiteenWeahterAdatper = new FifiteenWeahterAdatper(this);
        recyclerView.setAdapter(fifiteenWeahterAdatper);
        data = getIntent().getParcelableExtra("data");
        data.getForecast().remove(0);
        fifiteenWeahterAdatper.addData(data.getForecast());
    }
}
