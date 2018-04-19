package xbcao.demo.provider;

import android.content.Context;

import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;

import xbcao.demo.bean.City;
import xbcao.demo.utils.JsonUtil;

public class CityDataProvider {
    private static CityDataProvider instance;
    private Context mContext;
    private String json;
    private List<City> cities;

    private CityDataProvider(Context context)throws Exception{
        mContext = context;
        json = JsonUtil.getJsonFromStream(context.getAssets().open("city.json"));
        cities = new ArrayList<>();
        cities = JSONArray.parseArray(json,City.class);
    }

    public static CityDataProvider getInstance(Context context) throws Exception{
        if (instance==null){
            instance = new CityDataProvider(context);
        }
        return instance;
    }

    public List<City> getCities() {
        return cities;
    }
}
