package cmu.andrew.htay.dinewithus.fragment.schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ListView;

import cmu.andrew.htay.dinewithus.R;
import cmu.andrew.htay.dinewithus.fragment.appointment.AppointmentFragmentHolder;
import cmu.andrew.htay.dinewithus.fragment.appointment.AppointmentViewFragment;


public class ScheduleFragment extends Fragment {

    private ScheduleFragmentHolder fragHolder;
    private CalendarView calendarView;

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

    }

    public void getUpdate() {

    }
}
