package cmu.andrew.htay.dinewithus.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import cmu.andrew.htay.dinewithus.R;


public class ProfileFragment extends Fragment {

    private EditText like1EditText, like2EditText, like3EditText;
    private EditText dislike1EditText, dislike2EditText, dislike3EditText;
    private EditText phoneEditText, emailEditText;

    private String like1, like2, like3;
    private String dislike1, dislike2, dislike3;
    private String phone, email;

    public static ProfileFragment newInstance() {
        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile_layout, parent, false);
        System.out.println("PROFILE VIEW");

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

        return v;
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
                saveProfile();
            } // end try
            catch (NumberFormatException e)
            {
                like1 = "American"; // default if an exception occurs
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
                saveProfile();
            } // end try
            catch (NumberFormatException e)
            {
                like2 = "American"; // default if an exception occurs
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
                saveProfile();
            } // end try
            catch (NumberFormatException e)
            {
                like3 = "American"; // default if an exception occurs
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
                saveProfile();
            } // end try
            catch (NumberFormatException e)
            {
                dislike1 = "Canadian"; // default if an exception occurs
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
                dislike1 = s.toString();
                saveProfile();
            } // end try
            catch (NumberFormatException e)
            {
                dislike1 = "Canadian"; // default if an exception occurs
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
                dislike1 = s.toString();
                saveProfile();
            } // end try
            catch (NumberFormatException e)
            {
                dislike1 = "Canadian"; // default if an exception occurs
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
                saveProfile();
            } // end try
            catch (NumberFormatException e)
            {
                phone = "412-303-0054"; // default if an exception occurs
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
                saveProfile();
            } // end try
            catch (NumberFormatException e)
            {
                email = "nsundara@andrew.cmu.edu"; // default if an exception occurs
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
