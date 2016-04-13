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

import cmu.andrew.htay.dinewithus.R;


public class AppointmentFragment extends Fragment {

    private AppointmentFragmentHolder fragHolder;
    private ListView appointmentListView;
    private ArrayList<String> appointmentList = new ArrayList<String>();

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
        appointmentList.add("Friday | April 1 | 8 PM | Orient-Express");
        appointmentList.add("Sunday | April 3 | 11 AM | Bagel Factory");
        appointmentList.add("Monday | April 4 | 7 PM | Lulu&apos;s Noodles");
    }


    public void setFragHolder(AppointmentFragmentHolder fragHolder) {
        this.fragHolder = fragHolder;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.appointment_layout, parent, false);
        System.out.println("APPOINTMENT VIEW");

        appointmentListView = (ListView) v.findViewById(R.id.appointmentListView);
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


        return v;
    }
}
