package cmu.andrew.htay.dinewithus.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cmu.andrew.htay.dinewithus.R;


public class AppointmentFragmentHolder extends HolderFragment {


    public static AppointmentFragmentHolder newInstance() {
        Bundle args = new Bundle();

        AppointmentFragmentHolder fragment = new AppointmentFragmentHolder();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.appointment_holder, parent, false);
        AppointmentFragment fragment = AppointmentFragment.newInstance(this);
        replaceFragment(fragment, false);

        return v;
    }


    public void replaceFragment(Fragment fragment, boolean addToBackstack) {
        if (addToBackstack) {
            getChildFragmentManager().beginTransaction().replace(R.id.appointment_holder, fragment).addToBackStack(null).commit();

        } else {
            getChildFragmentManager().beginTransaction().replace(R.id.appointment_holder, fragment).commit();
        }
    }



}
