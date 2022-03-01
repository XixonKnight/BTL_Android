package com.example.appbookingroom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.appbookingroom.R;
import com.example.appbookingroom.common.Constants;
import com.example.appbookingroom.common.UserService;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.logging.Logger;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {
    private static final Logger logger = Logger.getLogger(String.valueOf(LoginActivity.class));
    private static final int RC_SIGN_IN = 100;
    GoogleSignInClient mGoogleSignInClient;
    private TextInputEditText txtEmail,txtPassword;

    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        processLoginGoogle();
        init();
        event();
    }

    private void init(){
        btnLogin = findViewById(R.id.btn_login);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
    }

    private void event(){
        btnLogin.setOnClickListener(v->{
            RequestParams params = new RequestParams();
            params.put("username",txtEmail.getText());
            params.put("password",txtPassword.getText());
            UserService.login(Constants.API.URL_LOGIN, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                   logger.info(responseBody.toString());
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    logger.warning(responseBody.toString());
                }
            });
//            startActivity(new Intent(getApplicationContext(),ActivityParent.class));
        });
    }

    private void processLoginGoogle(){
        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        // Set the dimensions of the sign-in button.
//        SignInButton signInButton = findViewById(R.id.btn_login);
//        signInButton.setSize(SignInButton.SIZE_STANDARD);
//
//        signInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                signIn();
//                Toast.makeText(getApplicationContext(),"Ok",Toast.LENGTH_LONG);
//            }
//        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();
            }
            // Signed in successfully, show authenticated UI.
//            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("signInResult:failed code=", e.getMessage());
//            updateUI(null);
        }
    }
}