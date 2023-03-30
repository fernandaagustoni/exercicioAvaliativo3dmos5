package br.edu.ifsp.dmos5.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dmos5.R;
import br.edu.ifsp.dmos5.dao.UserDao;
import br.edu.ifsp.dmos5.model.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private CheckBox rememberCheckBox;
    private Button buttonSignup ;
    private Button buttonSignIn;
    private String user;
    private String password;
    private User mUser;
    private List<User> userList = null;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.edittext_user);
        passwordEditText = findViewById(R.id.edittext_password);
        buttonSignIn = findViewById(R.id.btn_login);
        buttonSignup = findViewById(R.id.btn_new_user);
        rememberCheckBox = findViewById(R.id.checkbox_remember);

        buttonSignup.setOnClickListener(this);
        buttonSignIn.setOnClickListener(this);

        mSharedPreferences = this.getPreferences(MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

    }


    @Override
    protected void onStart() {
        Log.i(getString(R.string.tag), "Classe: " + getClass().getSimpleName() +  "| Método : onStart()");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.i(getString(R.string.tag), "Classe: " + getClass().getSimpleName() +  "| Método : onRestart()");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i(getString(R.string.tag), "Classe: " + getClass().getSimpleName() +  "| Método : onResume()");

        checkPreferences();

        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(getString(R.string.tag), "Classe: " + getClass().getSimpleName() +  "| Método : onPause()");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(getString(R.string.tag), "Classe: " + getClass().getSimpleName() +  "| Método : onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(getString(R.string.tag), "Classe: " + getClass().getSimpleName() +  "| Método : onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        if(view == buttonSignIn){

            user = usernameEditText.getText().toString();
            password = passwordEditText.getText().toString();
            if(user.isEmpty() || password.isEmpty()){
                Toast.makeText(this, R.string.message_empty_field, Toast.LENGTH_SHORT).show();
                return;
            }

            savePreferences();
            openContacts();

            return;
        }

        if(view == buttonSignup){
            Intent in = new Intent(this, NewUserActivity.class);
            startActivity(in);
            return;
        }

    }

    private void openContacts(){
        Intent in = new Intent(this, ContactsActivity.class);
        Bundle args = new Bundle();
        args.putString(getString(R.string.key_user), user);
        args.putString(getString(R.string.key_password), password);
        in.putExtras(args);
        startActivity(in);
    }

    private void savePreferences(){
        if(rememberCheckBox.isChecked()){
            mEditor.putString(getString(R.string.key_user), user);
            mEditor.commit();

            mEditor.putString(getString(R.string.key_password), password);
            mEditor.commit();

            mEditor.putBoolean(getString(R.string.key_remember), true);
            mEditor.commit();
        }else{
            mEditor.putString(getString(R.string.key_user), "");
            mEditor.commit();

            mEditor.putString(getString(R.string.key_password), "");
            mEditor.commit();

            mEditor.putBoolean(getString(R.string.key_remember), false);
            mEditor.commit();
        }

    }

    /*
    Aqui recuperamos as preferências do usuário, e caso existam (boolean lembrar) atualizamos
    os dados na tela da activity.
     */
    private void checkPreferences() {
        user = mSharedPreferences.getString(getString(R.string.key_user), "");
        password = mSharedPreferences.getString(getString(R.string.key_password), "");
        boolean remember = mSharedPreferences.getBoolean(getString(R.string.key_remember), false);

        if(remember){
            usernameEditText.setText(user);
            passwordEditText.setText(password);
            rememberCheckBox.setChecked(true);
        }
    }
}

