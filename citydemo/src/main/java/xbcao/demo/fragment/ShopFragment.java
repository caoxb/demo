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

import xbcao.demo.bean.Shop;
import xbcao.demo.business.ShopBusiness;

public class ShopFragment extends ListFragment {
    private ShopBusiness shopBusiness;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shopBusiness = new ShopBusiness(getActivity());
        ShopAdapter adapter = new ShopAdapter(shopBusiness.getShops());
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater,container,savedInstanceState);
        ListView listView = v.findViewById(android.R.id.list);
        return v;
    }

    private class ShopAdapter extends ArrayAdapter<Shop> {

        public ShopAdapter(List<Shop> shops){
            super(getActivity(),0,shops);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView==null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_shop,null);
            }
            Shop shop = getItem(position);
            TextView title = convertView.findViewById(R.id.tv_title);
            TextView content = convertView.findViewById(R.id.tv_content);
            title.setText(shop.getTitle());
            content.setText(shop.getContent());
            return convertView;
        }
    }

}
