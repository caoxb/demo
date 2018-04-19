package xbcao.demo.provider;

import android.content.Context;

import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;

import xbcao.demo.bean.Shop;
import xbcao.demo.utils.JsonUtil;

public class ShopDataProvider {
    private static ShopDataProvider instance;
    private Context mContext;
    private String json;
    private List<Shop> shops;

    private ShopDataProvider(Context context)throws Exception{
        mContext = context;
        json = JsonUtil.getJsonFromStream(context.getAssets().open("shop.json"));
        shops = new ArrayList<>();
        shops = JSONArray.parseArray(json,Shop.class);
    }

    public static ShopDataProvider getInstance(Context context) throws Exception{
        if (instance==null){
            instance = new ShopDataProvider(context);
        }
        return instance;
    }

    public List<Shop> getShops() {
        return shops;
    }
}
