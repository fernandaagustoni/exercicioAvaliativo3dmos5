package br.edu.ifsp.dmos5.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifsp.dmos5.R;
import br.edu.ifsp.dmos5.dao.UserDao;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button buttonSignup ;
    private Button buttonSignIn;
    private UserDao mUserDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserDao = new UserDao(this);

        usernameEditText = findViewById(R.id.edittext_user);
        passwordEditText = findViewById(R.id.edittext_password);

        buttonSignIn = findViewById(R.id.btn_login);
        buttonSignIn.setOnClickListener(this);

        buttonSignup = findViewById(R.id.btn_new_user);
        buttonSignup.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        if (v == buttonSignup) {
            registerNewUser();
        } else if (v == buttonSignIn) {
            loginIn();
        }

    }

    private void loginIn() {
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }


    private void registerNewUser() {
        Intent intent = new Intent(this, NewUserActivity.class);
        startActivity(intent);
    }

}

