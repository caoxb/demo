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
import xbcao.demo.bean.Eat;

import xbcao.demo.business.EatBusiness;

public class EatFragment extends ListFragment {
    private EatBusiness eatBusiness;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eatBusiness = new EatBusiness(getActivity());
        EatAdapter adapter = new EatAdapter(eatBusiness.getEats());
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater,container,savedInstanceState);
        ListView listView = v.findViewById(android.R.id.list);
        return v;
    }

    private class EatAdapter extends ArrayAdapter<Eat> {

        public EatAdapter(List<Eat> eats){
            super(getActivity(),0,eats);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView==null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_eat,null);
            }
            Eat eat = getItem(position);
            TextView title = convertView.findViewById(R.id.tv_title);
            TextView content = convertView.findViewById(R.id.tv_content);
            title.setText(eat.getTitle());
            content.setText(eat.getContent());
            return convertView;
        }
    }
}
