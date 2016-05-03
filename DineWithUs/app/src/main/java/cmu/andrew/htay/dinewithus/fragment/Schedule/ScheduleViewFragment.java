package cmu.andrew.htay.dinewithus.fragment.schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import cmu.andrew.htay.dinewithus.R;
import cmu.andrew.htay.dinewithus.entities.ScheduleBlock;
import cmu.andrew.htay.dinewithus.fragment.appointment.AppointmentFragmentHolder;
import cmu.andrew.htay.dinewithus.fragment.appointment.AppointmentViewFragment;
import cmu.andrew.htay.dinewithus.intents.ScheduleUpdate;


public class ScheduleViewFragment extends Fragment {
    private TextView appointment_text;
    private TextView restaurant_text;
    private TextView address_text;
    private TextView time_text;
    private TextView with_text;
    private TextView interests_title_text;
    private TextView interests_text;
    private TextView contact_text;
    private CheckBox[] checkTimes;

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
        checkTimes = new CheckBox[12];

        checkTimes[0] = (CheckBox) v.findViewById(R.id.checkTime1);
        checkTimes[1] = (CheckBox) v.findViewById(R.id.checkTime2);
        checkTimes[2] = (CheckBox) v.findViewById(R.id.checkTime3);
        checkTimes[3] = (CheckBox) v.findViewById(R.id.checkTime4);
        checkTimes[4] = (CheckBox) v.findViewById(R.id.checkTime5);
        checkTimes[5] = (CheckBox) v.findViewById(R.id.checkTime6);

        checkTimes[6] = (CheckBox) v.findViewById(R.id.checkTime7);
        checkTimes[7] = (CheckBox) v.findViewById(R.id.checkTime8);
        checkTimes[8] = (CheckBox) v.findViewById(R.id.checkTime9);
        checkTimes[9] = (CheckBox) v.findViewById(R.id.checkTime10);
        checkTimes[10] = (CheckBox) v.findViewById(R.id.checkTime11);
        checkTimes[11] = (CheckBox) v.findViewById(R.id.checkTime12);

        Button saveTimeButton =
                (Button) v.findViewById(R.id.saveTimeButton);
        saveTimeButton.setOnClickListener(saveTimeButtonClicked);

        return v;
    }

    View.OnClickListener saveTimeButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ArrayList<ScheduleBlock> sbList = new ArrayList<ScheduleBlock>();

            for(int i = 0; i < 12; i++){
                if(checkTimes[i].isChecked()){
                    ScheduleBlock sb = new ScheduleBlock();
                    sb.setStartTime(i*2);
                    sb.setEndTime(i*2 + 2);
                    sbList.add(sb);
                }
            }
            ScheduleUpdate sbTask = new ScheduleUpdate("htay", sbList, getContext());
            sbTask.execute();
        }
    };
}
