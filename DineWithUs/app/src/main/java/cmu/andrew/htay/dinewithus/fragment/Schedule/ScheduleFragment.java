package cmu.andrew.htay.dinewithus.fragment.schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import java.util.ArrayList;

import cmu.andrew.htay.dinewithus.R;
import cmu.andrew.htay.dinewithus.entities.ScheduleBlock;
import cmu.andrew.htay.dinewithus.intents.ScheduleGet;
import cmu.andrew.htay.dinewithus.intents.ScheduleUpdate;


public class ScheduleFragment extends Fragment {

    private ScheduleFragmentHolder fragHolder;
    private CalendarView calendarView;
    private ArrayList<ScheduleBlock> sbList;

    public static ScheduleFragment newInstance(ScheduleFragmentHolder fragHolder) {
        Bundle args = new Bundle();

        ScheduleFragment fragment = new ScheduleFragment();
        fragment.setArguments(args);
        fragment.setFragHolder(fragHolder);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.sbList = new ArrayList<>();

    }


    public void setFragHolder(ScheduleFragmentHolder fragHolder) {
        this.fragHolder = fragHolder;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.schedule_layout, parent, false);
        System.out.println("SCHEDULE VIEW");

        calendarView = (CalendarView) v.findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {

                final ScheduleViewFragment avfrag =
                        ScheduleViewFragment.newInstance("<insert date here>");

                fragHolder.replaceFragment(avfrag, true);

            }
        });

        return v;
    }

    public void sendUpdate() {
        ScheduleUpdate sbTask = new ScheduleUpdate("htay", sbList);
        sbTask.execute();

    }

    public void getUpdate() {
        ScheduleGet profTask = new ScheduleGet("htay", sbList, this);
        profTask.execute();
    }

    public void updateAllFields() {

    }
}
