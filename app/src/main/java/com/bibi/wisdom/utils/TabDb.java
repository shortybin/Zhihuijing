package com.bibi.wisdom.utils;


import com.bibi.wisdom.R;
import com.bibi.wisdom.main.discover.DiscoverFragment;
import com.bibi.wisdom.main.history.HistoryFragment;
import com.bibi.wisdom.main.home.HomeFragment;
import com.bibi.wisdom.main.maintain.MaintainFragment;
import com.bibi.wisdom.main.mine.MineFragment;

public class TabDb {


    public static String[] getTabsTxt() {
        String[] tabs = {"控制", "历史", "首页", "维护", "我的"};
        return tabs;
    }

    public static String[] getTabTitle() {
        String[] tabs = {"控制", "历史", "首页", "维护", "我的"};
        return tabs;
    }

    public static int[] getTabsImg() {
        int[] ids = {R.drawable.ic_tab_switch_off, R.drawable.ic_tab_history_off, R.drawable.ic_tab_tool_off, R.drawable.ic_tab_maintain_off, R.drawable.ic_tab_me_off};
        return ids;
    }

    public static int[] getTabsImgLight() {
        int[] ids = {R.drawable.ic_tab_switch_on, R.drawable.ic_tab_history_on, R.drawable.ic_tab_tool_on, R.drawable.ic_tab_maintain_on, R.drawable.ic_tab_me_on};
        return ids;
    }

    public static Class[] getFragments() {
        Class[] clz = {HomeFragment.class, HistoryFragment.class, DiscoverFragment.class, MaintainFragment.class, MineFragment.class};
        return clz;
    }


}
