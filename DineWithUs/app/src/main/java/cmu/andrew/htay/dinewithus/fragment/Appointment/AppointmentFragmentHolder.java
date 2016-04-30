package cmu.andrew.htay.dinewithus.fragment.appointment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cmu.andrew.htay.dinewithus.R;
import cmu.andrew.htay.dinewithus.fragment.shared.HolderFragment;


public class AppointmentFragmentHolder extends HolderFragment {

    AppointmentFragment apptfragment;


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
        apptfragment = AppointmentFragment.newInstance(this);
        replaceFragment(apptfragment, false);

        return v;
    }


    public void replaceFragment(Fragment fragment, boolean addToBackstack) {
        if (addToBackstack) {
            getChildFragmentManager().beginTransaction().replace(R.id.appointment_holder, fragment).addToBackStack(null).commit();

        } else {
            getChildFragmentManager().beginTransaction().replace(R.id.appointment_holder, fragment).commit();
        }
    }

    public AppointmentFragment getAppointmentFragment() {
        return apptfragment;
    }


}
