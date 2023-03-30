package br.edu.ifsp.dmos5.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dmos5.R;
import br.edu.ifsp.dmos5.dao.UserDao;
import br.edu.ifsp.dmos5.dao.UserDaoImpl;
import br.edu.ifsp.dmos5.model.User;

public class NewUserActivity extends AppCompatActivity implements View.OnClickListener{

    private Button saveButton;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText passwordConfirmEditText;
    private User mUser;
    private List<User> userList = null;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        saveButton = findViewById(R.id.btn_create_new_user);
        usernameEditText = findViewById(R.id.edittext_new_user);
        passwordEditText = findViewById(R.id.edittext_password_new_user);
        passwordConfirmEditText = findViewById(R.id.edittext_new_password_confirm);

        saveButton.setOnClickListener(this);

        mSharedPreferences = this.getSharedPreferences(getString(R.string.file_user), MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        getUsers();
    }

    @Override
    public void onClick(View view) {
        if(view == saveButton){
            String usuario;
            String senha;
            String confirma;
            usuario = usernameEditText.getText().toString();
            senha = passwordEditText.getText().toString();
            confirma = passwordConfirmEditText.getText().toString();
            if(!senha.equals(confirma)){
                Toast.makeText(this, getString(R.string.message_password_not_match), Toast.LENGTH_SHORT).show();
                passwordEditText.setText("");
                passwordConfirmEditText.setText("");
                return;
            }
            mUser = new User(usuario, senha);
            addUser();
            finish();
        }
    }

    private void getUsers(){
        String users = mSharedPreferences.getString(getString(R.string.key_users_db), "");
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray;
        userList = new ArrayList<>();
        try{
            jsonArray = new JSONArray(users);
            for(int i=0; i<jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                mUser = new User(jsonObject.getString("username"), jsonObject.getString("password"));
                userList.add(mUser);
            }
        } catch (JSONException ex){
            userList = null;
        }
        if (userList != null) {
            for (User u : userList) {
                Log.i(getString(R.string.tag), u.toString());
            }
        }
    }

    private void addUser(){
        JSONObject jsonObject;
        JSONArray jsonArray = new JSONArray();
        if(userList == null){
            userList = new ArrayList<>(1);
        }
        userList.add(mUser);
        for(User u : userList){
            jsonObject = new JSONObject();
            try {
                jsonObject.put("username", u.getUsername());
                jsonObject.put("password", u.getPassword());
                jsonArray.put(jsonObject);
            }catch (JSONException e){
                Log.e(getString(R.string.tag), getString(R.string.message_erro));
            }
        }
        String users = jsonArray.toString();
        mEditor.putString(getString(R.string.key_users_db), users);
        mEditor.commit();
    }

}

