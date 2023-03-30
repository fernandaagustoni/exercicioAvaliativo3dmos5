package br.edu.ifsp.dmos5.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import br.edu.ifsp.dmos5.R;

public class NewContactActivity extends AppCompatActivity implements View.OnClickListener{

    private Button saveButton;
    private EditText nicknameEditText;
    private EditText fullnameEditText;
    private EditText phonenumberEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        saveButton = findViewById(R.id.btn_create_new_user);
    }

    @Override
    public void onClick(View view) {
        if(view == saveButton){
            saveContact();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveContact() {
    }

}