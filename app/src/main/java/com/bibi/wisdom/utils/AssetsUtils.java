package com.bibi.wisdom.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AssetsUtils {
    public static String getAssetsString(Context context,String assetsName){
        String Result="";
        try {
            AssetManager assetManager = context.getAssets(); //获得assets资源管理器（assets中的文件无法直接访问，可以使用AssetManager访问）
            InputStreamReader inputStreamReader = new InputStreamReader(assetManager.open(assetsName),"UTF-8"); //使用IO流读取json文件内容
            BufferedReader br = new BufferedReader(inputStreamReader);//使用字符高效流
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = br.readLine())!=null){
                builder.append(line);
            }
            br.close();
            inputStreamReader.close();

            Result=builder.toString();
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result;
    }
}
