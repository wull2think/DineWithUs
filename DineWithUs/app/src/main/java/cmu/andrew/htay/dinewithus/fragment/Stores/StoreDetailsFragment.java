package cmu.andrew.htay.dinewithus.fragment.stores;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import cmu.andrew.htay.dinewithus.R;


public class StoreDetailsFragment extends Fragment {

    private ImageView shopPhotoView;
    private TextView shopNameView;
    private TextView shopAddressView;
    private TextView shopContactView;
    private TextView shopHoursView;
    private TextView websiteView;
    private TextView menuView;
    private RatingBar ratingView;
    private TextView descriptionView;

    private String shopName;
    private static String SHOP_NAME_ID = "SHOP_NAME_ID";

    public static StoreDetailsFragment newInstance(String shopName) {
        Bundle args = new Bundle();

        StoreDetailsFragment fragment = new StoreDetailsFragment();
        args.putSerializable(SHOP_NAME_ID, shopName);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shopName = (String)getArguments().getSerializable(SHOP_NAME_ID);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.shop_details, parent, false);
        System.out.println("STORE DETAILS VIEW");

        shopPhotoView = (ImageView) v.findViewById(R.id.shopPhotoView);
        shopNameView = (TextView) v.findViewById(R.id.shopNameView);
        shopAddressView = (TextView) v.findViewById(R.id.addressView);
        shopContactView = (TextView) v.findViewById(R.id.contactView);
        shopHoursView = (TextView) v.findViewById(R.id.hoursView);
        websiteView = (TextView) v.findViewById(R.id.websiteView);
        menuView = (TextView) v.findViewById(R.id.menuView);
        ratingView = (RatingBar) v.findViewById(R.id.ratingBar);
        descriptionView = (TextView) v.findViewById(R.id.descriptionView);

        shopNameView.setText(shopName);
        return v;
    }


}