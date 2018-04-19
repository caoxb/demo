package xbcao.demo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import xbcao.demo.activity.R;
import xbcao.demo.bean.City;
import xbcao.demo.business.CityBusiness;

public class CityFragment extends ListFragment {
    private CityBusiness cityBusiness;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cityBusiness = new CityBusiness(getActivity());
        CityAdapter adapter = new CityAdapter(cityBusiness.getCities());
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater,container,savedInstanceState);
        ListView listView = v.findViewById(android.R.id.list);
        return v;
    }

    private class CityAdapter extends ArrayAdapter<City>{

        public CityAdapter(List<City> cities){
            super(getActivity(),0,cities);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView==null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_city,null);
            }
            City city = getItem(position);
            TextView title = convertView.findViewById(R.id.tv_title);
            TextView content = convertView.findViewById(R.id.tv_content);
            title.setText(city.getTitle());
            content.setText(city.getContent());
            return convertView;
        }
    }
}
