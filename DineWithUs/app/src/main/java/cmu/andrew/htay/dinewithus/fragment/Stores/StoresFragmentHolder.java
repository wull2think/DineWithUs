package cmu.andrew.htay.dinewithus.fragment.stores;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cmu.andrew.htay.dinewithus.R;
import cmu.andrew.htay.dinewithus.fragment.shared.HolderFragment;


public class StoresFragmentHolder extends HolderFragment {

    private StoresFragment storefragment;

    public static StoresFragmentHolder newInstance() {
        Bundle args = new Bundle();

        StoresFragmentHolder fragment = new StoresFragmentHolder();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.shop_holder, parent, false);
        storefragment = StoresFragment.newInstance(this);
        replaceFragment(storefragment, false);

        return v;
    }


    public void replaceFragment(Fragment fragment, boolean addToBackstack) {
        if (addToBackstack) {
            getChildFragmentManager().beginTransaction().replace(R.id.shop_holder, fragment).addToBackStack(null).commit();

        } else {
            getChildFragmentManager().beginTransaction().replace(R.id.shop_holder, fragment).commit();
        }
    }

    public StoresFragment getStoresFragment() {
        return storefragment;
    }



}
