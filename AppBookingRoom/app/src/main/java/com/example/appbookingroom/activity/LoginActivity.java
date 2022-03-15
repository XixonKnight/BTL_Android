package com.example.appbookingroom.activity;

import android.accounts.Account;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbookingroom.R;
import com.example.appbookingroom.common.CommonUtils;
import com.example.appbookingroom.common.Constants;
import com.example.appbookingroom.common.UserService;
import com.example.appbookingroom.config.LoadingDialog;
import com.example.appbookingroom.model.Response;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.ListConnectionsResponse;
import com.google.api.services.people.v1.model.Person;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {
    private static final Logger logger = Logger.getLogger(String.valueOf(LoginActivity.class));
    //    private static final int RC_SIGN_IN = 9003;
    private static final String TAG = "TAG";
    private GoogleSignInClient mGoogleSignInClient;
    private TextInputEditText txtUsername, txtPassword;
    private TextView txtRegister;
    private SharedPreferences sharedPreferences;
    private LoadingDialog loadingDialog;
    private MaterialCheckBox cbRemember;
    private Button btnLogin, btnLoginForGoogle;


    //Process Google
    // Global instance of the HTTP transport
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    // Global instance of the JSON factory
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private ProgressDialog mProgressDialog;
    private Account mAccount;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        init();
        event();
        processLoginGoogle();


    }

    private void init() {
        loadingDialog = new LoadingDialog(LoginActivity.this);
        btnLogin = findViewById(R.id.btn_login);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtRegister = findViewById(R.id.register);
        cbRemember = findViewById(R.id.cbRemember);
        sharedPreferences = getSharedPreferences(Constants.KEY.ACCOUNT, MODE_PRIVATE);
        btnLoginForGoogle = findViewById(R.id.btnLoginForGoogle);
        loadInfoRemember();
    }

    private void loadInfoRemember() {
        txtUsername.setText(sharedPreferences.getString(Constants.KEY.USERNAME, ""));
        txtPassword.setText(sharedPreferences.getString(Constants.KEY.PASSWORD, ""));
        cbRemember.setChecked(sharedPreferences.getBoolean(Constants.KEY.REMEMBER, false));
    }

        private void event() {
        btnLogin.setOnClickListener(v -> {
            loadingDialog.show();
//            startActivity(new Intent(getApplicationContext(), ActivityParent.class));
        });
        txtRegister.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),ActivityRegister.class));
        });
    }
//    private void event() {
//        btnLogin.setOnClickListener(v -> {
//            configRemember();
//            loadingDialog.show();
//            RequestParams params = new RequestParams();
//            params.put("username", txtUsername.getText());
//            params.put("password", txtPassword.getText());
//            UserService.login(Constants.API.URL_LOGIN, params, new AsyncHttpResponseHandler() {
//                @Override
//                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                    Response response = CommonUtils.convertStringToResponse(new String(responseBody));
//                    if (response.getCode().equals(Constants.RESPONSE_CODE.SUCCESS)) {
//                        CommonUtils.saveValueToSharedPreferences(sharedPreferences, Constants.KEY.TOKEN, response.getData().toString());
//                    } else {
//                        Toast.makeText(getApplicationContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                    Toast.makeText(getApplicationContext(), Constants.MESSAGE.LOGIN_FAIL, Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onFinish() {
//                    loadingDialog.hide();
//                }
//            });
//        });
//    }

    private void configRemember() {
        if (cbRemember.isChecked()) {
            CommonUtils.saveValueToSharedPreferences(sharedPreferences, Constants.KEY.USERNAME, Objects.requireNonNull(txtUsername.getText()).toString());
            CommonUtils.saveValueToSharedPreferences(sharedPreferences, Constants.KEY.PASSWORD, Objects.requireNonNull(txtPassword.getText()).toString());
            CommonUtils.saveValueToSharedPreferences(sharedPreferences, Constants.KEY.REMEMBER, true);
        } else {
            CommonUtils.removeValueFromSharedPreferences(sharedPreferences, Constants.KEY.USERNAME);
            CommonUtils.removeValueFromSharedPreferences(sharedPreferences, Constants.KEY.PASSWORD);
            CommonUtils.removeValueFromSharedPreferences(sharedPreferences, Constants.KEY.REMEMBER);
        }
    }


    //Process google
    private void processLoginGoogle() {
        // [START configure_signin]
        // Configure sign-in to request offline access to the user's ID, basic
        // profile, and Google Drive. The first time you request a code you will
        // be able to exchange it for an access token and refresh token, which
        // you should store. In subsequent calls, the code will only result in
        // an access token. By asking for profile access (through
        // DEFAULT_SIGN_IN) you will also get an ID Token as a result of the
        // code exchange.
        String serverClientId = getString(R.string.server_client_id);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(Scopes.DRIVE_APPFOLDER))
                .requestServerAuthCode(serverClientId)
                .requestEmail()
                .build();
        // [END configure_signin]

        // Build GoogleAPIClient with the Google Sign-In API and the above options.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        // [START build_client]
        // Build a GoogleSignInClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // [END build_client]
        btnLoginForGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
                Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, Constants.GOOGLE.RC_SIGN_IN);
    }

//    protected void onRecoverableAuthException(UserRecoverableAuthIOException recoverableException) {
//        Log.w(TAG, "onRecoverableAuthException", recoverableException);
//        startActivityForResult(recoverableException.getIntent(), Constants.GOOGLE.RC_RECOVERABLE);
//    }

    private void getContacts() {
        if (mAccount == null) {
            Log.w(TAG, "getContacts: null account");
            return;
        }

        showProgressDialog();
        new GetContactsTask(this).execute(mAccount);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);

        if (requestCode == Constants.GOOGLE.RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Log.d(TAG, "onActivityResult:GET_AUTH_CODE:success:" + result.getStatus().isSuccess());

            if (result.isSuccess()) {
                // [START get_auth_code]
                GoogleSignInAccount acct = result.getSignInAccount();
                String authCode = acct.getServerAuthCode();

                Log.d(TAG, authCode);
                // Show signed-in UI.
//                mAuthCodeTextView.setText(getString(R.string.auth_code_fmt, authCode));
//                updateUI(true);

                // TODO(user): send code to server and exchange for access/refresh/ID tokens.
                // [END get_auth_code]
            } else {
                // Show signed-out UI.
//                updateUI(false);
            }
//
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {


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
    }


    private static class GetContactsTask extends AsyncTask<Account, Void, List<Person>> {

        private WeakReference<LoginActivity> mActivityRef;

        public GetContactsTask(LoginActivity activity) {
            mActivityRef = new WeakReference<>(activity);
        }

        @Override
        protected List<Person> doInBackground(Account... accounts) {
            if (mActivityRef.get() == null) {
                return null;
            }

            Context context = mActivityRef.get().getApplicationContext();
            try {
                GoogleAccountCredential credential = GoogleAccountCredential.usingOAuth2(
                        context,
                        Collections.singleton(Constants.GOOGLE.CONTACTS_SCOPE));
                credential.setSelectedAccount(accounts[0]);

                PeopleService service = new PeopleService.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                        .setApplicationName("Google Sign In Quickstart")
                        .build();

                ListConnectionsResponse connectionsResponse = service
                        .people()
                        .connections()
                        .list("people/me")
                        .setFields("names,emailAddresses")
                        .execute();

                return connectionsResponse.getConnections();

            } catch (UserRecoverableAuthIOException recoverableException) {
                if (mActivityRef.get() != null) {
//                    mActivityRef.get().onRecoverableAuthException(recoverableException);
                }
            } catch (IOException e) {
                Log.w(TAG, "getContacts:exception", e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Person> people) {
            super.onPostExecute(people);
            if (mActivityRef.get() != null) {
                mActivityRef.get().onConnectionsLoadFinished(people);
            }
        }
    }

    protected void onConnectionsLoadFinished(@Nullable List<Person> connections) {
        hideProgressDialog();

        if (connections == null) {
            Log.d(TAG, "getContacts:connections: null");
//            mDetailTextView.setText(getString(R.string.connections_fmt, "None"));
            return;
        }

        Log.d(TAG, "getContacts:connections: size=" + connections.size());

        // Get names of all connections
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < connections.size(); i++) {
            Person person = connections.get(i);
            if (person.getNames() != null && person.getNames().size() > 0) {
                msg.append(person.getNames().get(0).getDisplayName());

                if (i < connections.size() - 1) {
                    msg.append(",");
                }
            }
        }

        // Display names
//        mDetailTextView.setText(getString(R.string.connections_fmt, msg.toString()));
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
//                mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }
}