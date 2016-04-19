package cmu.andrew.htay.dinewithus.fragment.schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cmu.andrew.htay.dinewithus.R;


public class ScheduleViewFragment extends Fragment {
    private TextView appointment_text;
    private TextView restaurant_text;
    private TextView address_text;
    private TextView time_text;
    private TextView with_text;
    private TextView interests_title_text;
    private TextView interests_text;
    private TextView contact_text;
    public static ScheduleViewFragment newInstance(String appointmentString) {
        Bundle args = new Bundle();

        ScheduleViewFragment fragment = new ScheduleViewFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.schedule_view_layout, parent, false);


        return v;
    }
}
