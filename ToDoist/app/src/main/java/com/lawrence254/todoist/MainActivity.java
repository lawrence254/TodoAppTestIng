package com.lawrence254.todoist;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.etTodo)EditText mTodo;
    @BindView(R.id.fab)FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(this, TodoListActivity.class);
        String todoItem = String.valueOf(mTodo.getText());
        intent.putExtra("todo",todoItem);
        Toast.makeText(this, "Your todo: "+todoItem, Toast.LENGTH_LONG).show();
        startActivity(intent);
    }
}
