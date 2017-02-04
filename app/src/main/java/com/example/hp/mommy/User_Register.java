package com.example.hp.mommy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Hp on 03-02-2017.
 */
public class User_Register extends AppCompatActivity{
    private UserLoginTask register=null;
    private EditText Name;
    private EditText Password;
    private EditText Age;
    private EditText Address;
    private EditText Mobile;
    private Button Reg;
    private String name,password,age,address,mobile;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        register= new UserLoginTask(this);
        Name= (EditText) findViewById(R.id.name);
        Password= (EditText) findViewById(R.id.password);
        Age= (EditText) findViewById(R.id.age);
        Address= (EditText) findViewById(R.id.address);
        Mobile= (EditText) findViewById(R.id.mobile);
        Reg= (Button) findViewById(R.id.register);
        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Attempregister();
            }
        });
    }

    private void Attempregister() {
        name=Name.getText().toString();
        password=Password.getText().toString();
        age=Age.getText().toString();

        address=Address.getText().toString();
        mobile=Mobile.getText().toString();
        register=new UserLoginTask(User_Register.this);
        register.execute("Register",name,password,age,address,mobile);
    }
}
