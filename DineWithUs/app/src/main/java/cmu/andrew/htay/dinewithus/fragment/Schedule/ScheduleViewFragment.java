package cmu.andrew.htay.dinewithus.fragment.schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
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
    private CheckBox checkTime1;
    private CheckBox checkTime2;
    private CheckBox checkTime3;
    private CheckBox checkTime4;
    private CheckBox checkTime5;
    private CheckBox checkTime6;
    private CheckBox checkTime7;
    private CheckBox checkTime8;
    private CheckBox checkTime9;
    private CheckBox checkTime10;
    private CheckBox checkTime11;
    private CheckBox checkTime12;

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

        checkTime1 = (CheckBox) v.findViewById(R.id.checkTime1);
        checkTime2 = (CheckBox) v.findViewById(R.id.checkTime2);
        checkTime3 = (CheckBox) v.findViewById(R.id.checkTime3);
        checkTime4 = (CheckBox) v.findViewById(R.id.checkTime4);
        checkTime5 = (CheckBox) v.findViewById(R.id.checkTime5);
        checkTime6 = (CheckBox) v.findViewById(R.id.checkTime6);

        checkTime7 = (CheckBox) v.findViewById(R.id.checkTime7);
        checkTime8 = (CheckBox) v.findViewById(R.id.checkTime8);
        checkTime9 = (CheckBox) v.findViewById(R.id.checkTime9);
        checkTime10 = (CheckBox) v.findViewById(R.id.checkTime10);
        checkTime11 = (CheckBox) v.findViewById(R.id.checkTime11);
        checkTime12 = (CheckBox) v.findViewById(R.id.checkTime12);

        Button saveTimeButton =
                (Button) v.findViewById(R.id.saveTimeButton);
        saveTimeButton.setOnClickListener(saveTimeButtonClicked);


        /* Needs to return from database a list of possible appointments for each day.
           This will appropriately set the radio buttons. */

        /*checkTime1.setOnClickListener(new View.OnClickListener() {

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

    View.OnClickListener saveTimeButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Save Schedule Data
        }
    };
}
