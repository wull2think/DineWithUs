package cmu.andrew.htay.dinewithus.fragment.stores;

import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import cmu.andrew.htay.dinewithus.R;
import cmu.andrew.htay.dinewithus.UI.MainActivity;
import cmu.andrew.htay.dinewithus.entities.Store;
import cmu.andrew.htay.dinewithus.entities.StoreSet;
import cmu.andrew.htay.dinewithus.intents.AppointmentGet;
import cmu.andrew.htay.dinewithus.intents.StoreGet;
import cmu.andrew.htay.dinewithus.ws.local.GPSUtil;


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
    private Button searchButton;
    private ArrayList<String> storeNames;
    private GPSUtil gps;
    private static String FRAG_HOLDER_ID = "FRAG_HOLDER_ID";
    private String request;

    private StoresFragmentHolder fragHolder;
    private StoreSet storeSet;
    private StoreGet getShopsTask;
    private ArrayAdapter<String> shopsAdapter;
    private ArrayAdapter<CharSequence> cuisineAdapter;
    private ArrayAdapter<CharSequence> priceAdapter;

    public static StoresFragment newInstance(StoresFragmentHolder fragHolder) {
        Bundle args = new Bundle();


        StoresFragment fragment = new StoresFragment();
        fragment.setArguments(args);
        fragment.setFragHolder(fragHolder);

        return fragment;
    }


    public void setFragHolder(StoresFragmentHolder fragHolder) {
        this.fragHolder = fragHolder;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storeSet = new StoreSet();
        storeNames = new ArrayList<String>();
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
        searchButton = (Button) v.findViewById(R.id.searchButton);

        request = "GET Stores * * * * 0 0 -1";

        shopsAdapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, storeNames);
        shopListView.setAdapter(shopsAdapter);

        cuisineAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.cuisine_array, android.R.layout.simple_spinner_item);
        cuisineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cuisineDropdownView.setAdapter(cuisineAdapter);

        priceAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.price_array, android.R.layout.simple_spinner_item);
        priceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priceDropdownView.setAdapter(priceAdapter);

        shopListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String shopName = (String) shopListView.getItemAtPosition(position);
                Store s = storeSet.getStore(shopName);
                final StoreDetailsFragment sdfrag =
                        StoreDetailsFragment.newInstance(s);
                fragHolder.replaceFragment(sdfrag, true);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                double longitude = 0;
                double latitude = 0;
                double range = -1;
                if(StoresFragment.this.enableLocationBox.isChecked()){
                    longitude = gps.getLongitude();
                    latitude = gps.getLatitude();
                    if(longitude == -1 || latitude == -1){
                        //TODO error log here
                    }
                    else{
                        range = 10;
                        //TODO format search request
                    }
                }
                String cuisine, price, startTime, endTime;
                if(cuisineSwitchView.isChecked()){
                    cuisine = cuisineDropdownView.getSelectedItem().toString();
                }else{
                    cuisine = "*";
                }
                if(priceSwitchView.isChecked()){
                    price = priceDropdownView.getSelectedItem().toString();
                }else{
                    price = "*";
                }
                if(timeSwitchView.isChecked()){
                    startTime = timeStartView.getText().toString();
                    endTime = timeEndView.getText().toString();
                }else{
                    startTime = "*";
                    endTime = "*";
                }
                request = String.format("GET Stores %s %s %s %s %f %f %f", cuisine, price, startTime, endTime, longitude, latitude, range);
                System.out.println(request);
            }
        });

        enableLocationBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (enableLocationBox.isChecked()) {
                    LocationManager locManager = (LocationManager) getActivity().getSystemService(getActivity().LOCATION_SERVICE);
                    gps = new GPSUtil(locManager, getActivity());
                }
            }
        });

        getUpdate();

        return v;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            boolean hasPermissions = true;
        } else {
            boolean hasPermissions = false;
        }
    }
    public void getUpdate() {
        getShopsTask = new StoreGet(storeNames, storeSet, this, request);
        getShopsTask.execute();
    }

    public void updateAllFields() {
        shopsAdapter.notifyDataSetChanged();
    }
}
