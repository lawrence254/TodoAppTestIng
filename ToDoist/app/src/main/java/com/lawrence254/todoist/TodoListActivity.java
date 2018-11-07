package com.lawrence254.todoist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodoListActivity extends AppCompatActivity {
    @BindView(R.id.tvTodoItem)TextView mItem;
    @BindView(R.id.lvTodos)ListView mlist;
    public static final String MyPREFERENCES = "todos";
    ArrayList<String> storeTodos = new ArrayList<>();
    ArrayList<String> savedTodos = new ArrayList<>();
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        ButterKnife.bind(this);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Intent intent = getIntent();
        String submitted = intent.getStringExtra("todo");

        storeTodos.add(submitted);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,storeTodos);
//
        mlist.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        mlist.setAdapter(adapter);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        if (sharedpreferences.contains(MyPREFERENCES)){
            loadSelection();
        }
    }

    private void SaveSelections() {
        SharedPreferences.Editor prefEditor = sharedpreferences.edit();
        String savedItems = getSavedItems();
        prefEditor.putString(MyPREFERENCES.toString(), savedItems);
        prefEditor.commit();
    }

    private String getSavedItems() {
        String savedItems = "";
        int count = this.mlist.getAdapter().getCount();
        for (int i = 0; i < count; i++) {
            if (this.mlist.isItemChecked(i)) {
                if (savedItems.length() > 0) {
                    savedItems += "," + this.mlist.getItemAtPosition(i);
                } else {
                    savedItems += this.mlist.getItemAtPosition(i);
                }
            }
        }
        return savedItems;
    }


    private void loadSelection() {
        if (sharedpreferences.contains(MyPREFERENCES.toString())) {
            String savedItems = sharedpreferences.getString(MyPREFERENCES.toString(), "");
            savedTodos.addAll(Arrays.asList(savedItems.split(",")));
            int count = this.mlist.getAdapter().getCount();
            for (int i = 0; i < count; i++) {
                String currentItem = (String) mlist.getAdapter()
                        .getItem(i);
                if (savedTodos.contains(currentItem)) {
                    mlist.setItemChecked(i, true);
                    Toast.makeText(getApplicationContext(),
                            "Curren Item: " + currentItem,
                            Toast.LENGTH_LONG).show();
                } else {
                    mlist.setItemChecked(i, false);
                }
            }
        }
    }

}
