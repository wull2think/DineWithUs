package cmu.andrew.htay.dinewithus.fragment.appointment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cmu.andrew.htay.dinewithus.R;
import cmu.andrew.htay.dinewithus.UI.MainActivity;
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
    private Button confirmButton;
    private Button denyButton;
    private static final String APPOINTMENT_ID = "APPOINTMENT_ID";
    private Appointment appointment;
    private int pos;


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

        String[] memberNames = appointment.memberNames;
        if(memberNames[0] == ((MainActivity) getActivity()).username){
            pos = 0;
        }
        else{
            pos = 1;
        }
        confirmButton = (Button) v.findViewById(R.id.confirm_button);
        denyButton  = (Button) v.findViewById(R.id.deny_button);

        confirmButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String[] statuses = appointment.getStatus();
                if(pos == 0){
                    appointment.setStatus("CONFIRMED", statuses[1]);
                    statuses[0] = "CONFIRMED";
                }
                else{
                    appointment.setStatus(statuses[0], "CONFIRMED");
                    statuses[1] = "CONFIRMED";
                }
                String[] nameArgs = appointment.getName().split("|");
                String status;
                if(statuses[0] == "DENIED" || statuses[1] == "DENIED"){
                    status = "DENIED";
                }
                else if(statuses[0] == "PENDING" || statuses[1] == "PENDING"){
                    status = "PENDING";
                }
                else{
                    status = "CONFIMED";
                }
                String newName = String.format("%s | %s | %s | %s", nameArgs[0], nameArgs[1], nameArgs[2], status);
                appointment.setName(newName);
                getActivity().onBackPressed();
            }
        });

        denyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String[] statuses = appointment.getStatus();
                if(pos == 0){
                    appointment.setStatus("DENIED", statuses[1]);
                }
                else{
                    appointment.setStatus(statuses[0], "DENIED");
                }
                String[] nameArgs = appointment.getName().split("|");
                String newName = String.format("%s | %s | %s | %s", nameArgs[0], nameArgs[1], nameArgs[2], "DENIED");
                appointment.setName(newName);
                getActivity().onBackPressed();
            }
        });


        return v;
    }
}
