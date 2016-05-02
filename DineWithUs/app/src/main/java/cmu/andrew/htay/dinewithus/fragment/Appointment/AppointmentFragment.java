package cmu.andrew.htay.dinewithus.fragment.appointment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import java.util.LinkedHashMap;

import cmu.andrew.htay.dinewithus.R;
import cmu.andrew.htay.dinewithus.intents.AppointmentGet;
import cmu.andrew.htay.dinewithus.entities.Appointment;
import cmu.andrew.htay.dinewithus.intents.AppointmentUpdate;


public class AppointmentFragment extends Fragment {

    private AppointmentFragmentHolder fragHolder;
    private ListView appointmentListView;
    private LinkedHashMap<String, Appointment> appLHM;
    private ArrayList<String> appointmentList;
    ArrayAdapter<String> appointmentAdapter;

    public static AppointmentFragment newInstance(AppointmentFragmentHolder fragHolder) {
        Bundle args = new Bundle();

        AppointmentFragment fragment = new AppointmentFragment();
        fragment.setArguments(args);
        fragment.setFragHolder(fragHolder);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /*appointmentList.add("Friday | April 1 | 8 PM | Orient-Express");
        appointmentList.add("Sunday | April 3 | 11 AM | Bagel Factory");
        appointmentList.add("Monday | April 4 | 7 PM | Lulu&apos;s Noodles");*/

        this.appLHM = new LinkedHashMap<String, Appointment>();
        appointmentList = new ArrayList<String>();

    }


    public void setFragHolder(AppointmentFragmentHolder fragHolder) {
        this.fragHolder = fragHolder;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.appointment_layout, parent, false);
        System.out.println("APPOINTMENT VIEW");

        appointmentListView = (ListView) v.findViewById(R.id.appointmentListView);


        appointmentAdapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, appointmentList);
        appointmentListView.setAdapter(appointmentAdapter);
        appointmentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                String appointmentString = (String) appointmentListView.getItemAtPosition(position);
                Appointment appt = appLHM.get(appointmentString);
                final AppointmentViewFragment avfrag =
                        AppointmentViewFragment.newInstance(appt);

                fragHolder.replaceFragment(avfrag, true);
            }
        });
        getUpdate();

        return v;
    }

    public void sendUpdate() {
        ArrayList<Appointment> apptList = new ArrayList<>();
        for(Appointment appt : appLHM.values()) {
            apptList.add(appt);
        }
        AppointmentUpdate apptTask = new AppointmentUpdate("htay", apptList);
        apptTask.execute();

    }

    public void getUpdate() {
        AppointmentGet apptTask = new AppointmentGet("htay", appointmentList, appLHM, this);
        apptTask.execute();
    }

    public void updateAllFields() {
        appointmentAdapter.notifyDataSetChanged();
    }
}
