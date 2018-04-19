package xbcao.demo.provider;

import android.content.Context;

import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;

import xbcao.demo.bean.Eat;
import xbcao.demo.utils.JsonUtil;

public class EatDataProvider {
    private static EatDataProvider instance;
    private Context mContext;
    private String json;
    private List<Eat> eats;

    private EatDataProvider(Context context)throws Exception{
        mContext = context;
        json = JsonUtil.getJsonFromStream(context.getAssets().open("eat.json"));
        eats = new ArrayList<>();
        eats = JSONArray.parseArray(json,Eat.class);
    }

    public static EatDataProvider getInstance(Context context) throws Exception{
        if (instance==null){
            instance = new EatDataProvider(context);
        }
        return instance;
    }

    public List<Eat> getEats() {
        return eats;
    }
}
