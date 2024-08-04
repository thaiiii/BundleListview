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

public class NewActivity extends AppCompatActivity {
    //variables
    EditText txtid, txtname;
    Button btnsave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new);
        getWidget();

        //onCLick cua btnsave
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tempId = Integer.parseInt(txtid.getText().toString());
                String tempName = txtname.getText().toString();
                Person person = new Person(tempId, tempName);
                //------Tao Bundle cho vao Intent
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("person", person);
                intent.putExtra("DATA",bundle);
                setResult(MainActivity.SAVE_NEW_EMPLOYEE, intent);
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