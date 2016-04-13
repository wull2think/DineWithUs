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

    private String like1, like2, like3;
    private String dislike1, dislike2, dislike3;

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

        like1EditText.addTextChangedListener(likeWatcher1);
        like2EditText.addTextChangedListener(likeWatcher2);
        like3EditText.addTextChangedListener(likeWatcher3);

        dislike1EditText.addTextChangedListener(dislikeWatcher1);
        dislike2EditText.addTextChangedListener(dislikeWatcher2);
        dislike3EditText.addTextChangedListener(dislikeWatcher3);

        return v;
    }

    private TextWatcher likeWatcher1 = new TextWatcher()
    {
        // called when the user enters a number
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count)
        {
            // convert billEditText's text to a double
            try
            {
                like1 = s.toString();
            } // end try
            catch (NumberFormatException e)
            {
                like1 = "American"; // default if an exception occurs
            } // end catch

            // update the standard and custom tip EditTexts
            //hiEditText.setText(String.format("%d", quiz2));
            // updateStandard(); // update the 10, 15 and 20% EditTexts
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
    }; // end quiz1Watcher

    private TextWatcher likeWatcher2 = new TextWatcher()
    {
        // called when the user enters a number
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count)
        {
            // convert billEditText's text to a double
            try
            {
                like2 = s.toString();
            } // end try
            catch (NumberFormatException e)
            {
                like2 = "American"; // default if an exception occurs
            } // end catch

            // update the standard and custom tip EditTexts
            //hiEditText.setText(String.format("%d", quiz2));
            // updateStandard(); // update the 10, 15 and 20% EditTexts
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
    }; // end quiz1Watcher

    private TextWatcher likeWatcher3 = new TextWatcher()
    {
        // called when the user enters a number
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count)
        {
            // convert billEditText's text to a double
            try
            {
                like3 = s.toString();
            } // end try
            catch (NumberFormatException e)
            {
                like3 = "American"; // default if an exception occurs
            } // end catch

            // update the standard and custom tip EditTexts
            //hiEditText.setText(String.format("%d", quiz2));
            // updateStandard(); // update the 10, 15 and 20% EditTexts
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
    }; // end quiz1Watcher
}
