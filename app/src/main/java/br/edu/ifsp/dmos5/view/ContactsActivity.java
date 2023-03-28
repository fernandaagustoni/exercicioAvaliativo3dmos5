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

import java.util.List;
import br.edu.ifsp.dmos5.R;
import br.edu.ifsp.dmos5.dao.ContactsDaoImpl;
import br.edu.ifsp.dmos5.dao.Order;
import br.edu.ifsp.dmos5.model.Contact;
import br.edu.ifsp.dmos5.view.adapter.ContactSpinnerAdapter;
import br.edu.ifsp.dmos5.view.constant.Constant;


public class ContactsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Spinner mSpinner;
    private TextView mTextView;
    private TextView nTextView;
    private Button buttonCreateNewContact;

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
}