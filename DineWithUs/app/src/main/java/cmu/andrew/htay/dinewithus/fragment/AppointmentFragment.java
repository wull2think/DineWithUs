package cmu.andrew.htay.dinewithus.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cmu.andrew.htay.dinewithus.R;


public class AppointmentFragment extends Fragment {

    public static AppointmentFragment newInstance() {
        Bundle args = new Bundle();

        AppointmentFragment fragment = new AppointmentFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.appointment_layout, parent, false);
        System.out.println("APPOINTMENT VIEW");


        return v;
    }
}
