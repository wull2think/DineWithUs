package cmu.andrew.htay.dinewithus.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import cmu.andrew.htay.dinewithus.R;
import cmu.andrew.htay.dinewithus.UI.MainActivity;
import cmu.andrew.htay.dinewithus.entities.Appointment;


public class AppointmentFragment extends Fragment {

    private LinkedHashMap<String, Appointment> appLHM;
    private ListView appListView;

    public static AppointmentFragment newInstance() {
        Bundle args = new Bundle();

        AppointmentFragment fragment = new AppointmentFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.appointment_layout, parent, false);
        System.out.println("APPOINTMENT VIEW");
        this.appListView = (ListView) v.findViewById(R.id.appointmentListView);

        this.appLHM = Appointment.getAllAppointments(0);
        ArrayList<String> appArrayList = new ArrayList<String>();
        for(String key : this.appLHM.keySet()){
            appArrayList.add(key);
        }
        ArrayAdapter<String> appAdapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, appArrayList);
        appListView.setAdapter(appAdapter);

        appListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String appText = (String) appListView.getItemAtPosition(position);
                int appID = appLHM.get(appText).getAppointmentID();
                System.out.println("ID: " + Integer.toString(appID));
                /*
                String shopName = (String) shopListView.getItemAtPosition(position);
                final StoreDetailsFragment sdfrag =
                        StoreDetailsFragment.newInstance(shopName);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_layout, sdfrag)
                        .addToBackStack(null)//use back to return to previous view
                        .commit();
                */
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
        }

        ArrayAdapter<Button> appAdapter =
                new ArrayAdapter<Button>(getActivity(), android.R.layout.simple_list_item_1, appButtonList);
        appListView.setAdapter(appAdapter);
        */

        return v;
    }
}
