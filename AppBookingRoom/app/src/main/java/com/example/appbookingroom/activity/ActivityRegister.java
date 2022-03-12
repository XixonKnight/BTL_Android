package com.example.appbookingroom.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.appbookingroom.R;
import com.example.appbookingroom.common.CommonUtils;
import com.example.appbookingroom.common.Constants;
import com.example.appbookingroom.common.UserService;
import com.example.appbookingroom.config.LoadingDialog;
import com.example.appbookingroom.model.Response;
import com.example.appbookingroom.model.User;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.loopj.android.http.AsyncHttpResponseHandler;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class ActivityRegister extends AppCompatActivity {
    @SuppressLint("SimpleDateFormat")
    private final SimpleDateFormat format = new SimpleDateFormat(Constants.FORMAT_DATE);

    private TextInputLayout slpGender, lyUsername, lyPassword, lyEmail, lyDob, lyAddress;
    private TextInputEditText txtUsername, txtPassword, txtEmail, txtDateOfBirth, txtAddress;
    private AutoCompleteTextView txtGender;
    private AppCompatButton btnRegister;
    private User user;
    private boolean isSubmit = true;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        init();
        even();
    }

    private void init() {
        loadingDialog = new LoadingDialog(ActivityRegister.this);
        slpGender = findViewById(R.id.slpGender);
        txtGender = findViewById(R.id.txtGender);
        btnRegister = findViewById(R.id.btnRegister);
        lyAddress = findViewById(R.id.lyAddress);
        lyDob = findViewById(R.id.lyDob);
        lyEmail = findViewById(R.id.lyEmail);
        lyPassword = findViewById(R.id.lyPassword);
        lyUsername = findViewById(R.id.lyUsername);
        txtAddress = findViewById(R.id.txtAddress);
        txtPassword = findViewById(R.id.txtPassword);
        txtUsername = findViewById(R.id.txtUsername);
        txtEmail = findViewById(R.id.txtEmail);
        txtDateOfBirth = findViewById(R.id.txtDateOfBirth);
        configCbbGender();
    }

    private void configCbbGender() {
        String[] genders = getResources().getStringArray(R.array.items_gender);
        ArrayAdapter<String> arrGender = new ArrayAdapter<String>(this, R.layout.item_gender, genders);
        txtGender.setAdapter(arrGender);
    }

    private void even() {
        txtDateOfBirth.setOnClickListener(v -> {
            evenGetDate();
        });

        btnRegister.setOnClickListener(v -> {
            evenRegister();
        });

    }

    private void evenGetDate() {
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        builder.setSelection(MaterialDatePicker.todayInUtcMilliseconds());
        MaterialDatePicker<Long> materialDatePicker = builder.build();
        materialDatePicker.show(getSupportFragmentManager(), "Date Of Birth");
        materialDatePicker.addOnPositiveButtonClickListener(selection -> {
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            calendar.setTimeInMillis(selection);
            String formattedDate = format.format(calendar.getTime());
            txtDateOfBirth.setText(formattedDate);
        });
    }

    private void evenRegister() {
        boolean validate = validateForm();
        if (validate) {
            loadingDialog.show();
            StringEntity body = CommonUtils.convertObjectToStringEntity(user);
            UserService.register(getApplicationContext(), Constants.API.URL_CREATE_USER, body, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    Response response = CommonUtils.convertStringToResponse(new String(responseBody));
                    if (response.getCode().equals(Constants.RESPONSE_CODE.SUCCESS)) {
                        Toast.makeText(getApplicationContext(), Constants.MESSAGE.REGISTER_SUCCESS, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Toast.makeText(getApplicationContext(), Constants.MESSAGE.REGISTER_FAIL, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFinish() {
                    loadingDialog.hide();
                }
            });

        }

    }

    private boolean validateForm() {
        boolean flag = true;
        user = new User();
        if (!txtUsername.getText().toString().isEmpty()) {
            user.setUsername(txtUsername.getText().toString());
        } else {
            lyUsername.setError(Constants.MESSAGE.ERROR_USERNAME);
            flag = false;
        }

        if (!txtPassword.getText().toString().isEmpty()) {
            user.setPassword(txtPassword.getText().toString());
        } else {
            lyPassword.setError(Constants.MESSAGE.ERROR_PASSWORD);
            flag = false;
        }

        if (!txtEmail.getText().toString().isEmpty()) {
            user.setGmail(txtEmail.getText().toString());
        } else {
            lyEmail.setError(Constants.MESSAGE.ERROR_EMAIL);
            flag = false;
        }

        if (!txtGender.getText().toString().isEmpty()) {
            user.setGender(txtGender.getText().toString());
        } else {
            slpGender.setError(Constants.MESSAGE.ERROR_GENDER);
            flag = false;
        }

        if (!txtAddress.getText().toString().isEmpty()) {
            user.setAddress(txtAddress.getText().toString());
        } else {
            lyAddress.setError(Constants.MESSAGE.ERROR_ADDRESS);
            flag = false;
        }
        if (!txtDateOfBirth.getText().toString().isEmpty()) {
//            try {
//                Date dob = format.parse();
            user.setStrDateOfBirth(txtDateOfBirth.getText().toString());
//            } catch (ParseException e) {
//                Log.e/("ERROR get dob", e.getMessage(), e);
//            }
        } else {
            lyDob.setError(Constants.MESSAGE.ERROR_EMAIL);
            flag = false;
        }


        return flag;
    }
}