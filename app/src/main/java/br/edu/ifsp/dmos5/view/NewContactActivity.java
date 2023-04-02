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
import br.edu.ifsp.dmos5.dao.ContactsDaoImpl;
import br.edu.ifsp.dmos5.model.Contact;

public class NewContactActivity extends AppCompatActivity implements View.OnClickListener{
    private Button saveButton;
    private EditText nicknameEditText;
    private EditText fullnameEditText;
    private EditText phonenumberEditText;
    private Contact mContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findById();
        clickListener();
    }

    private  void findById(){
        saveButton = findViewById(R.id.btn_create_new_user);
        nicknameEditText = findViewById(R.id.edittext_new_contact_nickname);
        fullnameEditText = findViewById(R.id.edittext_new_contact_fullname);
        phonenumberEditText = findViewById(R.id.edittext_new_contact_phonenumber);
    }
    private  void clickListener(){
        saveButton.setOnClickListener(this);
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
        String nickname = nicknameEditText.getText().toString();
        String fullName = fullnameEditText.getText().toString();
        String phoneNumber = phonenumberEditText.getText().toString();

        if(ContactsDaoImpl.getInstance().findByNickname(nickname) == null){
            Contact c = new Contact(nickname, fullName, phoneNumber);
            ContactsDaoImpl.getInstance().addContacts(c);
            Toast.makeText(this, R.string.message_contact_created, Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, R.string.message_contact_already_created, Toast.LENGTH_SHORT).show();
        }

    }
}
