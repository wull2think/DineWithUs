package cmu.andrew.htay.dinewithus.fragment.appointment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

    private static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 1;
    private static final int MY_PERMISSION_SEND_SMS = 2;
    private boolean enableLocMgr = true;
    private boolean enableSMS = true;


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
        Button saveTimeButton =
                (Button) v.findViewById(R.id.smsAppointmentButton);
        saveTimeButton.setOnClickListener(smsAppointmentButtonClicked);

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

    View.OnClickListener smsAppointmentButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sendSMSMessage();
        }
    };

    protected void sendSMSMessage() {
        {
            Log.i("Send SMS", "");
            String phoneNumber = contact_text.getText().toString();
            String message = "HEY GOOD LOOKING!!";
            //check for SMS permissions
            if (ContextCompat.checkSelfPermission(getContext(),
                    android.Manifest.permission.SEND_SMS) !=
                    PackageManager.PERMISSION_GRANTED) {

                //request if no permission allowed
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSION_SEND_SMS);
                System.out.println("DASDS");
            }

            if (enableSMS) {
                System.out.println("D123ASDS");
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNumber, null, message, null, null);
                    Toast.makeText(getActivity(), "SMS successfully sent", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "SMS failed, please try again later", Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    @Override
    //handler for requester permissions
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted
                    enableLocMgr = true;

                } else {

                    // permission denied, log the error
                    enableLocMgr = false;
                }
                return;
            }
            case MY_PERMISSION_SEND_SMS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted
                    enableSMS = true;

                } else {

                    // permission denied, log the error
                    enableSMS = false;
                }
                return;
            }

        }
    }
}
