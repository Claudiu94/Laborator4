package ro.pub.cs.systems.eim.lab04.contactsmanager;

import android.content.ContentValues;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ContactsManagerActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);
        Button save = (Button)findViewById(R.id.save);
        Button cancel = (Button)findViewById(R.id.cancel);
        Button hide_show = (Button)findViewById(R.id.button);

        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
        hide_show.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button button = (Button)v;

        if (button.getId() == R.id.button) {
            LinearLayout hiddenLayout = (LinearLayout) findViewById(R.id.hiddenLayout);

            if (button.getText().equals("Show Additional Fields")) {
                hiddenLayout.setVisibility(View.VISIBLE);
                button.setText("Hide Additional Fields");
            } else {
                hiddenLayout.setVisibility(View.GONE);
                button.setText("Show Additional Fields");
            }
        } else if (button.getId() == R.id.save) {
            EditText name = (EditText)findViewById(R.id.name);
            EditText phone = (EditText)findViewById(R.id.phone);
            EditText email = (EditText)findViewById(R.id.email);
            EditText address = (EditText)findViewById(R.id.address);
            EditText company = (EditText)findViewById(R.id.company);
            EditText jobTitle = (EditText)findViewById(R.id.position);
            EditText website = (EditText)findViewById(R.id.site);
            EditText im = (EditText)findViewById(R.id.messenger);
            Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
            intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

            if (name != null) {
                intent.putExtra(ContactsContract.Intents.Insert.NAME, name.getText());
            }
            if (phone != null) {
                intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone.getText());
            }
            if (email != null) {
                intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email.getText());
            }
            if (address != null) {
                intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address.getText());
            }
            if (jobTitle != null) {
                intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle.getText());
            }
            if (company != null) {
                intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company.getText());
            }
            ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
            if (website != null) {
                ContentValues websiteRow = new ContentValues();
                websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
                websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website.getText().toString());
                contactData.add(websiteRow);
            }
            if (im != null) {
                ContentValues imRow = new ContentValues();
                imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
                imRow.put(ContactsContract.CommonDataKinds.Im.DATA, im.getText().toString());
                contactData.add(imRow);
            }
            intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
            startActivity(intent);
        } else if(button.getId() == R.id.cancel) {
            finish();
        }
    }
}
