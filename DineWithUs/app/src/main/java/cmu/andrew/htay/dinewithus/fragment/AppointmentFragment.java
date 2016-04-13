package cmu.andrew.htay.dinewithus.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import cmu.andrew.htay.dinewithus.R;
import cmu.andrew.htay.dinewithus.UI.MainActivity;
import cmu.andrew.htay.dinewithus.entities.Appointment;


public class AppointmentFragment extends Fragment {

    private AppointmentFragmentHolder fragHolder;
    private ListView appointmentListView;
    private LinkedHashMap<String, Appointment> appLHM;

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
    }


    public void setFragHolder(AppointmentFragmentHolder fragHolder) {
        this.fragHolder = fragHolder;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.appointment_layout, parent, false);
        System.out.println("APPOINTMENT VIEW");

        appointmentListView = (ListView) v.findViewById(R.id.appointmentListView);

        this.appLHM = Appointment.getAllAppointments(0);
        ArrayList<String> appointmentList = new ArrayList<String>();
        for(String key : this.appLHM.keySet()){
            appointmentList.add(key);
        }

        ArrayAdapter<String> appointmentAdapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, appointmentList);
        appointmentListView.setAdapter(appointmentAdapter);
        appointmentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                String appointmentString = (String) appointmentListView.getItemAtPosition(position);
                final AppointmentViewFragment avfrag =
                        AppointmentViewFragment.newInstance(appointmentString);

                fragHolder.replaceFragment(avfrag, true);
            }
        });



        /*
        ArrayList<Appointment> appArrayList = Appointment.getAllAppointments(0); //TODO: Implement getAllAppointments
        ArrayList<Button> appButtonList = new ArrayList<Button>();
        for(int i = 0; i < appArrayList.size(); i++){
            final Appointment A = appArrayList.get(i);
            Button B = new Button(getActivity());
            //B.setId(A.getAppointmentID());
            String date = A.getDateString();
            String restName = A.getRestaurantName();
            B.setText(date + "|" + restName);
            B.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int appID = A.getAppointmentID();
                    Toast.makeText(this, Integer.toString(appID),
                            Toast.LENGTH_SHORT).show();
                    //TODO: Write code to make this go to an AppointmentViewFragment
                }
            });
            appButtonList.add(B);
        }*/


        return v;
    }
}
