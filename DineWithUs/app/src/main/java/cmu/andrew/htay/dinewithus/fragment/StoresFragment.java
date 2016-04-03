package cmu.andrew.htay.dinewithus.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import cmu.andrew.htay.dinewithus.R;


public class StoresFragment extends Fragment {

    private TextView currLocationView;
    private CheckBox enableLocationBox;
    private SearchView searchShopView;
    private ListView shopListView;
    private Spinner cuisineDropdownView;
    private Switch cuisineSwitchView;
    private EditText timeStartView;
    private EditText timeEndView;
    private Switch timeSwitchView;
    private Spinner priceDropdownView;
    private Switch priceSwitchView;
    private ArrayList<String> storeNames = new ArrayList<String>();

    public static StoresFragment newInstance() {
        Bundle args = new Bundle();

        StoresFragment fragment = new StoresFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        storeNames.add("TestStore");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.shops_layout, parent, false);
        System.out.println("STORES VIEW");

        currLocationView = (TextView) v.findViewById(R.id.currLocationView);
        enableLocationBox = (CheckBox) v.findViewById(R.id.enableLocationBox);
        searchShopView = (SearchView) v.findViewById(R.id.searchShopView);
        shopListView = (ListView) v.findViewById(R.id.shopListView);
        cuisineDropdownView = (Spinner) v.findViewById(R.id.cuisineDropdownView);
        cuisineSwitchView = (Switch) v.findViewById(R.id.cuisineSwitchView);
        timeStartView = (EditText) v.findViewById(R.id.timeStartView);
        timeEndView = (EditText) v.findViewById(R.id.timeEndView);
        timeSwitchView = (Switch) v.findViewById(R.id.timeSwitchView);
        priceDropdownView = (Spinner) v.findViewById(R.id.priceDropdownView);
        priceSwitchView = (Switch) v.findViewById(R.id.priceSwitchView);


        ArrayAdapter<String> shopsAdapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, storeNames);
        shopListView.setAdapter(shopsAdapter);
        shopListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String shopName = (String)shopListView.getItemAtPosition(position);
                final StoreDetailsFragment sdfrag =
                        StoreDetailsFragment.newInstance(shopName);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_layout, sdfrag)
                        .addToBackStack(null)//use back to return to previous view
                        .commit();

            }
        });

        return v;
    }
}
