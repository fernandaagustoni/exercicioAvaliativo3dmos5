package br.edu.ifsp.dmos5.view;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import br.edu.ifsp.dmos5.R;
import br.edu.ifsp.dmos5.dao.ContactsDaoImpl;
import br.edu.ifsp.dmos5.dao.Order;
import br.edu.ifsp.dmos5.model.Contact;
import br.edu.ifsp.dmos5.model.User;
import br.edu.ifsp.dmos5.view.adapter.ContactSpinnerAdapter;


public class ContactsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Spinner mSpinner;
    private TextView mTextView;
    private TextView nTextView;
    private Button buttonCreateNewContact;
    private String user;
    private String password;
    private User mUser;
    private List<User> userList = null;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        findByNickname();
        mSpinner.setOnItemSelectedListener(this);
        populateSpinner();
        buttonCreateNewContact = findViewById(R.id.btn_create_new_contact);
        buttonCreateNewContact.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        user = intent.getStringExtra(getString(R.string.key_user));
        password = intent.getStringExtra(getString(R.string.key_password));


        mSharedPreferences = this.getSharedPreferences(getString(R.string.file_user), MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        getUsers();
        validateUser();
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        showContactDetails((Contact) mSpinner.getItemAtPosition(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        mTextView.setVisibility(View.GONE);
    }

    private void findByNickname(){
        mSpinner = findViewById(R.id.spinner_contacts);
        mTextView = findViewById(R.id.textview_details);
        nTextView = findViewById(R.id.textview_details_phoneNumber);
    }

    private void populateSpinner(){
        List<Contact> dataset = new ContactsDaoImpl().findAll(Order.ALPHABETICALLY);
        ContactSpinnerAdapter adapter = new ContactSpinnerAdapter(this, android.R.layout.simple_spinner_item, dataset);
        mSpinner.setAdapter(adapter);
    }

    private void showContactDetails(Contact contact){
        //Beer beer = (Beer) mSpinner.getSelectedItem();
        mTextView.setVisibility(View.VISIBLE);
        mTextView.setText(contact.getName());
        nTextView.setText(contact.getPhoneNumber());
    }

    @Override
    public void onClick(View view) {
        if (view == buttonCreateNewContact) {
            registerNewContact();
        }
    }

    private void registerNewContact() {
        Intent intent = new Intent(this, NewContactActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void validateUser(){
        boolean found = false;
        if(userList != null) {
            for (User u : userList) {
                if (u.getUsername().equals(user)) {
                    if (u.getPassword().equals(password)) {
                        Toast.makeText(this, getString(R.string.message_welcome), Toast.LENGTH_SHORT).show();
                        found = true;
                    }
                }
            }
        }
        if(!found){
            backHomepage();
            Toast.makeText(this, getString(R.string.message_erro), Toast.LENGTH_SHORT).show();
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

    private void backHomepage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}