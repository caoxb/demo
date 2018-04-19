package xbcao.demo.business;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import xbcao.demo.bean.Eat;
import xbcao.demo.bean.Shop;
import xbcao.demo.provider.EatDataProvider;
import xbcao.demo.provider.ShopDataProvider;

public class EatBusiness {
    private List<Eat> eats;

    public EatBusiness(Context context){
        eats = new ArrayList<>();
        try{
            eats = EatDataProvider.getInstance(context).getEats();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Eat> getEats() {
        return eats;
    }
}
