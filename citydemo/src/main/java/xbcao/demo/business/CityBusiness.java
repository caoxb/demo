package xbcao.demo.business;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import xbcao.demo.bean.City;
import xbcao.demo.provider.CityDataProvider;

public class CityBusiness {
    private List<City> cities;

    public CityBusiness(Context context){
        cities = new ArrayList<>();
        try{
            cities = CityDataProvider.getInstance(context).getCities();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<City> getCities() {
        return cities;
    }
}
