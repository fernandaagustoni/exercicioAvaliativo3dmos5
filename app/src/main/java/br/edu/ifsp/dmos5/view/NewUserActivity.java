package br.edu.ifsp.dmos5.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.edu.ifsp.dmos5.R;
import br.edu.ifsp.dmos5.dao.UserDaoImpl;
import br.edu.ifsp.dmos5.model.User;

public class NewUserActivity extends AppCompatActivity implements View.OnClickListener{
    private Button saveButton;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText passwordConfirmEditText;
    private User mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findById();
        clickListener();
    }

    private  void findById(){
        saveButton = findViewById(R.id.btn_create_new_user);
        usernameEditText = findViewById(R.id.edittext_new_user);
        passwordEditText = findViewById(R.id.edittext_password_new_user);
        passwordConfirmEditText = findViewById(R.id.edittext_new_password_confirm);
    }

    private  void clickListener(){
        saveButton.setOnClickListener(this);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View view) {
        if(view == saveButton){
            addUser();
        }
    }
    private void addUser(){
        String user = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String passwordConfirm = passwordConfirmEditText.getText().toString();

        if(user.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()){
            Toast.makeText(this, R.string.message_empty_field, Toast.LENGTH_LONG).show();
        }else if (!password.equals(passwordConfirm)){
            Toast.makeText(this, R.string.message_password_not_match, Toast.LENGTH_LONG).show();

        }else{
            if(UserDaoImpl.getInstance().findByUsername(user) == null){
                User u = new User(user, password);
                UserDaoImpl.getInstance().userAdd(u);
                Toast.makeText(this, R.string.message_user_created, Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, R.string.message_user_already_created, Toast.LENGTH_SHORT).show();
            }
        }
    }
}