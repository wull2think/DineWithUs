package cmu.andrew.htay.dinewithus.fragment.schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import cmu.andrew.htay.dinewithus.R;
import cmu.andrew.htay.dinewithus.fragment.appointment.AppointmentFragmentHolder;
import cmu.andrew.htay.dinewithus.fragment.appointment.AppointmentViewFragment;


public class ScheduleViewFragment extends Fragment {
    private TextView appointment_text;
    private TextView restaurant_text;
    private TextView address_text;
    private TextView time_text;
    private TextView with_text;
    private TextView interests_title_text;
    private TextView interests_text;
    private TextView contact_text;
    private RadioButton radioTime1;
    private RadioButton radioTime2;
    private RadioButton radioTime3;
    private RadioButton radioTime4;
    private RadioButton radioTime5;
    private RadioButton radioTime6;
    private RadioButton radioTime7;
    private RadioButton radioTime8;
    private RadioButton radioTime9;
    private RadioButton radioTime10;
    private RadioButton radioTime11;
    private RadioButton radioTime12;

    private ScheduleFragmentHolder fragHolder;

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

        radioTime1 = (RadioButton) v.findViewById(R.id.radioTime1);
        radioTime2 = (RadioButton) v.findViewById(R.id.radioTime2);
        radioTime3 = (RadioButton) v.findViewById(R.id.radioTime3);
        radioTime4 = (RadioButton) v.findViewById(R.id.radioTime4);
        radioTime5 = (RadioButton) v.findViewById(R.id.radioTime5);
        radioTime6 = (RadioButton) v.findViewById(R.id.radioTime6);

        radioTime7 = (RadioButton) v.findViewById(R.id.radioTime7);
        radioTime8 = (RadioButton) v.findViewById(R.id.radioTime8);
        radioTime9 = (RadioButton) v.findViewById(R.id.radioTime9);
        radioTime10 = (RadioButton) v.findViewById(R.id.radioTime10);
        radioTime11 = (RadioButton) v.findViewById(R.id.radioTime11);
        radioTime12 = (RadioButton) v.findViewById(R.id.radioTime12);


        /* Needs to return from database a list of possible appointments for each day.
           This will appropriately set the radio buttons. */
        radioTime1.setChecked(true);
        radioTime2.setChecked(false);
        radioTime2.setClickable(false);

        /*radioTime1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String appointmentString = "LOL";
                final AppointmentViewFragment avfrag =
                        AppointmentViewFragment.newInstance(appointmentString);

                fragHolder.replaceFragment(avfrag, true);
            }
        });*/
        return v;
    }
}
