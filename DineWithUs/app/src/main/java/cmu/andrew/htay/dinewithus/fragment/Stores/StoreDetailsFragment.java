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
import cmu.andrew.htay.dinewithus.entities.Store;
import cmu.andrew.htay.dinewithus.intents.WebImage;


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

    private Store store;
    private static String SHOP_ID = "SHOP_ID";

    public static StoreDetailsFragment newInstance(Store store) {
        Bundle args = new Bundle();

        StoreDetailsFragment fragment = new StoreDetailsFragment();
        args.putSerializable(SHOP_ID, store);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        store = (Store)getArguments().getSerializable(SHOP_ID);

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

        shopNameView.setText(store.getName());
        shopAddressView.setText(store.getAddress());
        shopContactView.setText(store.getPhone());
        ratingView.setNumStars(store.getRating());
        descriptionView.setText(store.getDescription());
        websiteView.setText(store.getWebsiteURL());
        menuView.setText(store.getMenuURL());

        int start = store.getOpeningTime();
        int end = store.getClosingTime();

        String hours = start + " to " + end;
        shopHoursView.setText(hours);


        WebImage setImageTask = new WebImage(shopPhotoView, store.getShopPictureURL(), getContext());
        setImageTask.execute();

        return v;
    }



}
