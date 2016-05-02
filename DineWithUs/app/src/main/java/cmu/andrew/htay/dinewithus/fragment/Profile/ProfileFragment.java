package cmu.andrew.htay.dinewithus.fragment.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

import cmu.andrew.htay.dinewithus.R;
import cmu.andrew.htay.dinewithus.entities.Profile;
import cmu.andrew.htay.dinewithus.intents.ProfileGet;
import cmu.andrew.htay.dinewithus.intents.ProfileUpdate;
import cmu.andrew.htay.dinewithus.intents.ScheduleGet;
import cmu.andrew.htay.dinewithus.intents.ScheduleUpdate;


public class ProfileFragment extends Fragment {

    private EditText like1EditText, like2EditText, like3EditText;
    private EditText dislike1EditText, dislike2EditText, dislike3EditText;
    private EditText phoneEditText, emailEditText;

    private ImageButton btnCamera;

    private ArrayList<String> likes;
    private ArrayList<String> dislikes;
    private String like1, like2, like3;
    private String dislike1, dislike2, dislike3;
    private String phone, email;
    private ProfileGet profTask;
    private Profile myProfile;

    public static ProfileFragment newInstance() {
        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myProfile = new Profile();
        //Populate array list with three dummy values
        likes = myProfile.getLikes();
        likes.add(0, "");
        likes.add(1, "");
        likes.add(2, "");
        dislikes = myProfile.getDislikes();
        dislikes.add(0, "");
        dislikes.add(1, "");
        dislikes.add(2, "");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile_layout, parent, false);
        System.out.println("PROFILE VIEW");

        btnCamera = (ImageButton) v.findViewById(R.id.imageButton);

        like1EditText = (EditText) v.findViewById(R.id.like1EditText);
        like2EditText = (EditText) v.findViewById(R.id.like2EditText);
        like3EditText = (EditText) v.findViewById(R.id.like3EditText);

        dislike1EditText = (EditText) v.findViewById(R.id.dislike1EditText);
        dislike2EditText = (EditText) v.findViewById(R.id.dislike2EditText);
        dislike3EditText = (EditText) v.findViewById(R.id.dislike3EditText);

        phoneEditText = (EditText) v.findViewById(R.id.phoneEditText);
        emailEditText = (EditText) v.findViewById(R.id.emailEditText);

        like1EditText.addTextChangedListener(likeWatcher1);
        like2EditText.addTextChangedListener(likeWatcher2);
        like3EditText.addTextChangedListener(likeWatcher3);

        dislike1EditText.addTextChangedListener(dislikeWatcher1);
        dislike2EditText.addTextChangedListener(dislikeWatcher2);
        dislike3EditText.addTextChangedListener(dislikeWatcher3);

        phoneEditText.addTextChangedListener(phoneWatcher);
        emailEditText.addTextChangedListener(emailWatcher);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });

        getUpdate();
        return v;
    }

    public void sendUpdate() {
        ProfileUpdate profTask = new ProfileUpdate("htay", myProfile);
        profTask.execute();

    }

    public void getUpdate() {
        ProfileGet profTask = new ProfileGet("htay", myProfile, this);
        profTask.execute();
    }

    public void updateAllFields() {

    }


    //Source: https://www.simplifiedcoding.net/android-camera-app-tutorial-create-a-simple-camera-app/
    //We
    private void openCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == getActivity().RESULT_OK) {
            Bitmap bp = (Bitmap) data.getExtras().get("data");
            btnCamera.setImageBitmap(bp);
            btnCamera.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }
    }

    private void saveProfile() {
        return;
    }

    private TextWatcher likeWatcher1 = new TextWatcher()
    {
        // called when the user edits like1 field
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count)
        {
            try
            {
                like1 = s.toString();
                likes.add(0, like1);
                saveProfile();
            } // end try
            catch (NumberFormatException e)
            {
                like1 = "American"; // default if an exception occurs
                likes.add(0, like1);
                saveProfile();
            } // end catch
        } // end method onTextChanged

        @Override
        public void afterTextChanged(Editable s)
        {
        } // end method afterTextChanged

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after)
        {
        } // end method beforeTextChanged
    };

    private TextWatcher likeWatcher2 = new TextWatcher()
    {
        // called when the user edits like2 field
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count)
        {
            try
            {
                like2 = s.toString();
                likes.add(1, like2);
                saveProfile();
            } // end try
            catch (NumberFormatException e)
            {
                like2 = "American"; // default if an exception occurs
                likes.add(1, like2);
                saveProfile();
            } // end catch
        } // end method onTextChanged

        @Override
        public void afterTextChanged(Editable s)
        {
        } // end method afterTextChanged

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after)
        {
        } // end method beforeTextChanged
    };

    private TextWatcher likeWatcher3 = new TextWatcher()
    {
        // called when the user edits like3 field
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count)
        {
            try
            {
                like3 = s.toString();
                likes.add(2, like3);
                saveProfile();
            } // end try
            catch (NumberFormatException e)
            {
                like3 = "American"; // default if an exception occurs
                likes.add(2, like3);
                saveProfile();
            } // end catch
        } // end method onTextChanged

        @Override
        public void afterTextChanged(Editable s)
        {
        } // end method afterTextChanged

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after)
        {
        } // end method beforeTextChanged
    };

    private TextWatcher dislikeWatcher1 = new TextWatcher()
    {
        // called when user edits the dislike1 field
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count)
        {
            try
            {
                dislike1 = s.toString();
                dislikes.add(0, dislike1);
                saveProfile();
            } // end try
            catch (NumberFormatException e)
            {
                dislike1 = "Canadian"; // default if an exception occurs
                dislikes.add(0, dislike1);
                saveProfile();
            } // end catch
        } // end method onTextChanged

        @Override
        public void afterTextChanged(Editable s)
        {
        } // end method afterTextChanged

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after)
        {
        } // end method beforeTextChanged
    };

    private TextWatcher dislikeWatcher2 = new TextWatcher()
    {
        // called when user edits the dislike2 field
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count)
        {
            try
            {
                dislike2 = s.toString();
                dislikes.add(1, dislike2);
                saveProfile();
            } // end try
            catch (NumberFormatException e)
            {
                dislike2 = "Canadian"; // default if an exception occurs
                dislikes.add(1, dislike2);
                saveProfile();
            } // end catch
        } // end method onTextChanged

        @Override
        public void afterTextChanged(Editable s)
        {
        } // end method afterTextChanged

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after)
        {
        } // end method beforeTextChanged
    };

    private TextWatcher dislikeWatcher3 = new TextWatcher()
    {
        // called when user edits the dislike3 field
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count)
        {
            try
            {
                dislike3 = s.toString();
                dislikes.add(2, dislike3);
                saveProfile();
            } // end try
            catch (NumberFormatException e)
            {
                dislike3 = "Canadian"; // default if an exception occurs
                dislikes.add(2, dislike3);
                saveProfile();
            } // end catch
        } // end method onTextChanged

        @Override
        public void afterTextChanged(Editable s)
        {
        } // end method afterTextChanged

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after)
        {
        } // end method beforeTextChanged
    };

    private TextWatcher phoneWatcher = new TextWatcher()
    {
        // called when user edits the dislike3 field
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count)
        {
            try
            {
                phone = s.toString();
                myProfile.setPhone(phone);
                saveProfile();
            } // end try
            catch (NumberFormatException e)
            {
                phone = "412-303-0054"; // default if an exception occurs
                myProfile.setPhone(phone);
                saveProfile();
            } // end catch
        } // end method onTextChanged

        @Override
        public void afterTextChanged(Editable s)
        {
        } // end method afterTextChanged

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after)
        {
        } // end method beforeTextChanged
    };

    private TextWatcher emailWatcher = new TextWatcher()
    {
        // called when user edits the dislike3 field
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count)
        {
            try
            {
                email = s.toString();
                myProfile.setEmail(email);
                saveProfile();
            } // end try
            catch (NumberFormatException e)
            {
                email = "nsundara@andrew.cmu.edu"; // default if an exception occurs
                myProfile.setEmail(email);
                saveProfile();
            } // end catch
        } // end method onTextChanged

        @Override
        public void afterTextChanged(Editable s)
        {
        } // end method afterTextChanged

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after)
        {
        } // end method beforeTextChanged
    };
}
