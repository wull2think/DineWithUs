package cmu.andrew.htay.dinewithus.fragment.schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cmu.andrew.htay.dinewithus.R;
import cmu.andrew.htay.dinewithus.fragment.appointment.AppointmentFragment;
import cmu.andrew.htay.dinewithus.fragment.shared.HolderFragment;


public class ScheduleFragmentHolder extends HolderFragment {


    public static ScheduleFragmentHolder newInstance() {
        Bundle args = new Bundle();

        ScheduleFragmentHolder fragment = new ScheduleFragmentHolder();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.schedule_holder, parent, false);
        ScheduleFragment fragment = ScheduleFragment.newInstance(this);
        replaceFragment(fragment, false);

        return v;
    }


    public void replaceFragment(Fragment fragment, boolean addToBackstack) {
        if (addToBackstack) {
            getChildFragmentManager().beginTransaction().replace(R.id.schedule_holder, fragment).addToBackStack(null).commit();

        } else {
            getChildFragmentManager().beginTransaction().replace(R.id.schedule_holder, fragment).commit();
        }
    }



}
