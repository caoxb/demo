package xbcao.demo.business;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import xbcao.demo.bean.Shop;
import xbcao.demo.provider.ShopDataProvider;

public class ShopBusiness {
    private List<Shop> shops;

    public ShopBusiness(Context context){
        shops = new ArrayList<>();
        try{
            shops = ShopDataProvider.getInstance(context).getShops();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Shop> getShops() {
        return shops;
    }
}
