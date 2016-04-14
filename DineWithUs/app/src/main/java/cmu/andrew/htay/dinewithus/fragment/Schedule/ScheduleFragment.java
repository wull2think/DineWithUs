package cmu.andrew.htay.dinewithus.fragment.schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cmu.andrew.htay.dinewithus.R;


public class ScheduleFragment extends Fragment {

    public static ScheduleFragment newInstance() {
        Bundle args = new Bundle();

        ScheduleFragment fragment = new ScheduleFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.schedule_layout, parent, false);
        System.out.println("SCHEDULE VIEW");


        return v;
    }
}
