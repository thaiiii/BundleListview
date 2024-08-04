package com.example.bundlelistview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditActivity extends AppCompatActivity {

    EditText txtid, txtname;
    Button btnsave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new);
        getWidget();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("DATA");
        Person person = (Person) bundle.getSerializable("person");
        txtid.setEnabled(false);

        //onCLick cua btnsave
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person.setName(txtname.getText().toString());
                setResult(MainActivity.SAVE_EDIT_EMPLOYEE, intent);
                finish();
            }
        });
    }

    public void getWidget(){
        txtid = (EditText) findViewById(R.id.txtid);
        txtname = (EditText) findViewById(R.id.txtname);
        btnsave = (Button) findViewById(R.id.btnsave);
    }
}