package com.example.cakeslk.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cakeslk.MainActivity;
import com.example.cakeslk.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private Button loginbtn;
    private EditText email, password;
    private String admin_Email, admin_Password, Email, Password, Position;
    public static String sessionEmail;
    private static boolean sessionAdmin;
    public static int userId;
    private UserDbHelper userDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userDbHelper= new UserDbHelper(this);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginbtn = findViewById(R.id.loginBtn);
        setListeners();

    }

    private void setListeners() {
        loginbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                checkLogin();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }


    private void checkLogin() {

        Email = email.getText().toString();
        Password = password.getText().toString();

        String regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
        Pattern p = Pattern.compile(regEx);
        Matcher m1 = p.matcher(Email);


        if (Email.isEmpty() || Password.isEmpty()) {
            Toast msg = Toast.makeText(Login.this, "Enter Both credentials.", Toast.LENGTH_SHORT);
            msg.show();
        } else if (!m1.find()) {
            Toast msg = Toast.makeText(Login.this, "Your Email Id is Invalid.", Toast.LENGTH_SHORT);
            msg.show();
        } else if (Password.length() < 8) {
            Toast msg = Toast.makeText(Login.this, "Invalid Password Length", Toast.LENGTH_SHORT);
            msg.show();
        } else {
            User user = userDbHelper.getAdminData();
            Position = user.getPosition();
            admin_Email = user.getEmail();
            admin_Password = user.getPassword();
            userId = userDbHelper.getUserId(Email);

            if (Position.equalsIgnoreCase("Admin") && Email.equals(admin_Email) && Password.equals(admin_Password)) {
                sessionEmail = Email;
                sessionAdmin = true;
                Intent i = new Intent(Login.this, MainActivity.class);
                startActivity(i);

                Toast msg = Toast.makeText(Login.this, "Login Successful !!!", Toast.LENGTH_SHORT);
                msg.show();

                finish();

            } else {
                Toast msg = Toast.makeText(Login.this, "Invalid Email or Password", Toast.LENGTH_SHORT);
                msg.show();
            }
        }
    }

}



