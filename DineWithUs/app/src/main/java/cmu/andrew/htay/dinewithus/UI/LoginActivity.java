package cmu.andrew.htay.dinewithus.UI;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import cmu.andrew.htay.dinewithus.R;

/**
 * Created by HuiJun on 4/3/16.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;

    private String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        usernameEditText.addTextChangedListener(usernameWatcher);
        passwordEditText.addTextChangedListener(passwordWatcher);

        Button saveStudentDataButton =
                (Button) findViewById(R.id.loginButton);
        saveStudentDataButton.setOnClickListener(loginButtonClicked);
    }

    private TextWatcher usernameWatcher = new TextWatcher()
    {
        // called when the user edits username field
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count)
        {
            try
            {
                username = s.toString();
            } // end try
            catch (NumberFormatException e)
            {
                username = "default"; // default if an exception occurs
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

    private TextWatcher passwordWatcher = new TextWatcher()
    {
        // called when the user edits password field
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count)
        {
            try
            {
                password = s.toString();
            } // end try
            catch (NumberFormatException e)
            {
                password = ""; // default if an exception occurs
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

    private void login() {
        return;
    }

    View.OnClickListener loginButtonClicked = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            if ((username.length() != 0))
            {
                AsyncTask<Object, Object, Object> saveStudentDataTask =
                        new AsyncTask<Object, Object, Object>()
                        {
                            @Override
                            protected Object doInBackground(Object... params)
                            {
                                login(); // use username and password to authenticate via database
                                return null;
                            } // end method doInBackground

                            @Override
                            protected void onPostExecute(Object result)
                            {
                                finish(); // return to the previous Activity
                            } // end method onPostExecute
                        }; // end AsyncTask

                // save the contact to the database using a separate thread
                saveStudentDataTask.execute((Object[]) null);
            } // end if
            else
            {
                // create a new AlertDialog Builder
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(LoginActivity.this);

                // set dialog title & message, and provide Button to dismiss
                builder.setTitle(R.string.errorLoginTitle);
                builder.setMessage(R.string.errorLoginMessage);
                builder.setPositiveButton(R.string.errorLoginButton, null);
                builder.show(); // display the Dialog
            } // end else
        } // end method onClick
    }; // end OnClickListener saveStudentDataButtonClicked

}
