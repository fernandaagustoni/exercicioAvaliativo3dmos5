package br.edu.ifsp.dmos5.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.edu.ifsp.dmos5.R;
import br.edu.ifsp.dmos5.model.Cryptography;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button buttonSignup ;
    private Button buttonSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findById();
        clickListener();
    }

    @Override
    public void onClick(View view) {
        if(view == buttonSignIn){
            openContacts();
        }
        if(view == buttonSignup){
            addNewUser();
        }
    }
    private  void findById(){
        usernameEditText = findViewById(R.id.edittext_user);
        passwordEditText = findViewById(R.id.edittext_password);
        buttonSignIn = findViewById(R.id.btn_login);
        buttonSignup = findViewById(R.id.btn_new_user);
    }
    private  void clickListener(){
        buttonSignup.setOnClickListener(this);
        buttonSignIn.setOnClickListener(this);
    }
    private void openContacts(){
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String passwordMD5 = Cryptography.getHashMd5(password);

        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        bundle.putString("password", username);

        if(username.matches("") || password.matches("")){
            Toast.makeText(this, R.string.message_empty_field, Toast.LENGTH_LONG).show();
        }else{
            Intent intent = new Intent(this, ContactsActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
    private void addNewUser(){
        Intent intent = new Intent(this, NewUserActivity.class);
        startActivity(intent);
    }

}