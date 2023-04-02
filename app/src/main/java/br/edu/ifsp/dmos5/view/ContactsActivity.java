package br.edu.ifsp.dmos5.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import br.edu.ifsp.dmos5.R;
import br.edu.ifsp.dmos5.dao.ContactsDaoImpl;
import br.edu.ifsp.dmos5.dao.Order;
import br.edu.ifsp.dmos5.dao.UserDaoImpl;
import br.edu.ifsp.dmos5.model.Contact;
import br.edu.ifsp.dmos5.model.User;
import br.edu.ifsp.dmos5.view.adapter.ContactSpinnerAdapter;


public class ContactsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private Spinner mSpinner;
    private TextView mTextView;
    private TextView nTextView;
    private Button buttonCreateNewContact;
    private User user = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findById();
        clickListener();
        getUsers();
        populateSpinner();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSpinner.setSelection(0);
    }

    private void findById(){
        mSpinner = findViewById(R.id.spinner_contacts);
        mTextView = findViewById(R.id.textview_details_fullname);
        nTextView = findViewById(R.id.textview_details_phoneNumber);
        buttonCreateNewContact = findViewById(R.id.btn_create_new_contact);
    }
    private  void clickListener(){
        mSpinner.setOnItemSelectedListener(this);
        buttonCreateNewContact.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view == buttonCreateNewContact) {
            registerNewContact();
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Contact contact = (Contact) mSpinner.getItemAtPosition(position);
        if(contact != null){
            openDetailsActivity(contact);
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        mTextView.setVisibility(View.GONE);
    }

    private void populateSpinner(){
        List<Contact> dataset = new ContactsDaoImpl().findAll(Order.ALPHABETICALLY);
        dataset.add(0, null);
        ContactSpinnerAdapter adapter = new ContactSpinnerAdapter(this, android.R.layout.simple_spinner_item, dataset);
        mSpinner.setAdapter(adapter);
    }

    private void openDetailsActivity(Contact contact){
        Contact contacts = (Contact) mSpinner.getSelectedItem();
        mTextView.setVisibility(View.VISIBLE);
        mTextView.setText(contact.getName());
        nTextView.setText(contact.getPhoneNumber());
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getUsers(){
        String username = getIntent().getStringExtra("username");
        String password = getIntent().getStringExtra("password");

        if (UserDaoImpl.getInstance().validateUser(username, password)){
            user = UserDaoImpl.getInstance().findByUsername(username);
            Toast.makeText(this, R.string.message_welcome, Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, R.string.message_user_not_found, Toast.LENGTH_LONG).show();
            finish();
        }
    }
    private void registerNewContact() {
        Intent intent = new Intent(this, NewContactActivity.class);
        startActivity(intent);
    }
}
