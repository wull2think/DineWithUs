package cmu.andrew.htay.dinewithus.fragment.appointment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cmu.andrew.htay.dinewithus.R;
import cmu.andrew.htay.dinewithus.entities.Appointment;
import cmu.andrew.htay.dinewithus.fragment.appointment.AppointmentViewFragment;


public class AppointmentViewFragment extends Fragment {
    private TextView appointment_text;
    private TextView restaurant_text;
    private TextView address_text;
    private TextView time_text;
    private TextView with_text;
    private TextView interests_title_text;
    private TextView interests_text;
    private TextView contact_text;
    private static final String APPOINTMENT_ID = "APPOINTMENT_ID";
    private Appointment appointment;


    public static AppointmentViewFragment newInstance(Appointment appt) {
        Bundle args = new Bundle();

        AppointmentViewFragment fragment = new AppointmentViewFragment();
        fragment.setArguments(args);
        args.putSerializable(APPOINTMENT_ID, appt);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appointment = (Appointment)getArguments().getSerializable(APPOINTMENT_ID);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.appointment_view_layout, parent, false);
        System.out.println("Appointment View");
        appointment_text = (TextView) v.findViewById(R.id.appointment_text);
        restaurant_text = (TextView) v.findViewById(R.id.restaurant_text);
        address_text =  (TextView) v.findViewById(R.id.address_text);
        time_text = (TextView) v.findViewById(R.id.time_text);
        with_text = (TextView) v.findViewById(R.id.with_text);
        interests_title_text = (TextView) v.findViewById(R.id.interests_title_text);
        interests_text = (TextView) v.findViewById(R.id.interests_text);
        contact_text =  (TextView) v.findViewById(R.id.contact_text);

        appointment_text.setText(appointment.getName());


        return v;
    }
}
