package com.example.bundlelistview;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //request code and Result code
    public static final int NEW_EMPLOYEE = 113;
    public static final int EDIT_EMPLOYEE = 114;
    public static final int SAVE_NEW_EMPLOYEE = 115;
    public static final int SAVE_EDIT_EMPLOYEE = 116;

    //Variables
    int posSelected = -1;
    ListView lvnhanvien;
    ArrayList<Person> list = new ArrayList<Person>();
    ArrayAdapter<Person> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getWidget();
        adapter = new ArrayAdapter<Person>(this, android.R.layout.simple_list_item_1, list);
        lvnhanvien.setAdapter(adapter);

        //----------------them nhan vien vao list------------------------------------------------
        list.add(new Person(1, "An"));
        list.add(new Person(2, "Binh"));
        list.add(new Person(3, "Cuong"));
        //----------------long click listview va register context menu cho listview--------------
        lvnhanvien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                posSelected = i;
                return false;
            }
        });
        registerForContextMenu(lvnhanvien);





    }

    public void getWidget() {
        lvnhanvien = (ListView) findViewById(R.id.lvnhanvien);
    }

    //------------------tao contextmenu
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.mymenucontext, menu);

    }

    public boolean onContextItemSelected(android.view.MenuItem item) {
        if(item.getItemId() == R.id.menunew){
            doStartNew();
        }
        if(item.getItemId() == R.id.menuedit){
            doStartEdit();
        }
        if(item.getItemId() == R.id.menudelete){
            doDelete();
        }
        return super.onContextItemSelected(item);
    }

    //-------------------Ham click item trong context menu
    public void doStartNew(){
        Intent intentNew = new Intent(this, NewActivity.class);
        startActivityForResult(intentNew, MainActivity.NEW_EMPLOYEE);
    }
    public void doStartEdit(){
        Intent intentEdit = new Intent(this, EditActivity.class);
        Person person = list.get(posSelected);
        //------Tao Bundle
        Bundle bundle = new Bundle();
        bundle.putSerializable("person", person);
        intentEdit.putExtra("DATA",bundle);
        startActivityForResult(intentEdit, MainActivity.EDIT_EMPLOYEE);

    }
    public void doDelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thong bao");
        builder.setMessage("Ban co muon xoa khong?");
        builder.setPositiveButton("Co", (dialog, which) -> {
            list.remove(posSelected);
            adapter.notifyDataSetChanged();
        });
        builder.setNegativeButton("Khong", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.create().show();
    }
    //-------------------Get data from sub activity callback

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == this.NEW_EMPLOYEE) {
            if (resultCode == this.SAVE_NEW_EMPLOYEE) {
                Bundle bundle = data.getExtras();
                Person person = (Person) bundle.getSerializable("person");
                list.add(person);
                adapter.notifyDataSetChanged();
            }
        }

        if(requestCode == this.EDIT_EMPLOYEE) {
            if (resultCode == this.SAVE_EDIT_EMPLOYEE) {
                Bundle bundle = data.getExtras();
                Person person = (Person) bundle.getSerializable("person");
                list.set(posSelected, person);
                adapter.notifyDataSetChanged();
            }
        }
    }
}