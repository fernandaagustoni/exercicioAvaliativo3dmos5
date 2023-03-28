package br.edu.ifsp.dmos5.view;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import java.util.List;
import br.edu.ifsp.dmos5.R;
import br.edu.ifsp.dmos5.dao.ContactsDaoImpl;
import br.edu.ifsp.dmos5.dao.Order;
import br.edu.ifsp.dmos5.model.Contact;
import br.edu.ifsp.dmos5.view.adapter.ContactSpinnerAdapter;
import br.edu.ifsp.dmos5.view.constant.Constant;


public class ContactsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        findById();
        mSpinner.setOnItemSelectedListener(this);
        populateSpinner();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void findById(){
        mSpinner = findViewById(R.id.spinner_contacts);
    }
    private void populateSpinner(){
        List<Contact> dataset = new ContactsDaoImpl().findAll(Order.ALPHABETICALLY);
        dataset.add(0, null);
        ContactSpinnerAdapter adapter = new ContactSpinnerAdapter(this, android.R.layout.simple_spinner_item, dataset);
        mSpinner.setAdapter(adapter);
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

    }

    private void openDetailsActivity(Contact contact){
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.CONTACT_ID, contact.getId());

        Intent intent = new Intent(this, ContactsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}