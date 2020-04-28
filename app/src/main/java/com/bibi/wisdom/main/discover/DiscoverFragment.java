package com.bibi.wisdom.main.discover;

import com.bibi.wisdom.R;
import com.bibi.wisdom.mvp.MVPBaseFragment;

/**
 * Created by shortybin
 * on 2020/4/16
 */
public class DiscoverFragment extends MVPBaseFragment<DiscoverContract.View, DisvcoverPresenter> implements DiscoverContract.View {
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.discover_fragment;
    }

    @Override
    protected void init() {

    }
}
