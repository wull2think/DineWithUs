package cmu.andrew.htay.dinewithus.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

import cmu.andrew.htay.dinewithus.R;


public class StoresFragmentHolder extends HolderFragment {


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
        StoresFragment fragment = StoresFragment.newInstance(this);
        replaceFragment(fragment, false);

        return v;
    }


    public void replaceFragment(Fragment fragment, boolean addToBackstack) {
        if (addToBackstack) {
            getChildFragmentManager().beginTransaction().replace(R.id.shop_holder, fragment).addToBackStack(null).commit();

        } else {
            getChildFragmentManager().beginTransaction().replace(R.id.shop_holder, fragment).commit();
        }
    }



}
