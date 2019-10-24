package com.bibi.wisdom.utils;


import com.bibi.wisdom.R;
import com.bibi.wisdom.main.history.HistoryFragment;
import com.bibi.wisdom.main.home.HomeFragment;
import com.bibi.wisdom.main.maintain.MaintainFragment;
import com.bibi.wisdom.main.mine.MineFragment;
import com.bibi.wisdom.main.tool.ToolFragment;

public class TabDb {


    public static String[] getTabsTxt() {
        String[] tabs = {"开关", "历史", "工具", "维护","我的"};
        return tabs;
    }
    public static String[] getTabTitle() {
        String[] tabs = {"开关", "历史记录", "工具", "公告","我"};
        return tabs;
    }

    public static int[] getTabsImg() {
        int[] ids = {R.drawable.ic_tab_switch_off, R.drawable.ic_tab_history_off, R.drawable.ic_tab_tool_off,R.drawable.ic_tab_maintain_off, R.drawable.ic_tab_me_off};
        return ids;
    }

    public static int[] getTabsImgLight() {
        int[] ids = {R.drawable.ic_tab_switch_on, R.drawable.ic_tab_history_on, R.drawable.ic_tab_tool_on,R.drawable.ic_tab_maintain_on, R.drawable.ic_tab_me_on};
        return ids;
    }

    public static Class[] getFragments() {
        Class[] clz = {HomeFragment.class, HistoryFragment.class, ToolFragment.class, MaintainFragment.class, MineFragment.class};
        return clz;
    }


}
