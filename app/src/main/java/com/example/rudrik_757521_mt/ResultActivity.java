package com.example.rudrik_757521_mt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private Car car = new Car();
    private Spinner spinner;
    private EditText edtPrice;
    private TextView txtTitle, txtDays, txtAmount, txtTotal;
    private CheckBox chkGPS, chkChild, chkUnlimited;
    private AppCompatSeekBar seekBar;
    private RadioGroup radioGroup;
    private RadioButton rbtLess, rbtBetween, rbtAbove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        car = new Gson().fromJson(getIntent().getStringExtra("CAR"),Car.class);
        System.out.println(car.toString());
        init();
    }

    private void init(){

        Button btnDetails = ((Button)findViewById(R.id.btnViewDetails));
        btnDetails.setVisibility(View.GONE);
        ((TextView)findViewById(R.id.txtDaysTitle)).setText("Days");

        spinner = (Spinner) findViewById(R.id.spinner);
        edtPrice = (EditText) findViewById(R.id.edtPrice);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtDays = (TextView) findViewById(R.id.txtDays);
        seekBar = (AppCompatSeekBar) findViewById(R.id.seekBar);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rbtLess = (RadioButton) findViewById(R.id.rbtLess);
        rbtBetween = (RadioButton) findViewById(R.id.rbtBetween);
        rbtAbove = (RadioButton) findViewById(R.id.rbtAbove);
        chkGPS = (CheckBox) findViewById(R.id.chkGPS);
        chkChild = (CheckBox) findViewById(R.id.chkChild);
        chkUnlimited = (CheckBox) findViewById(R.id.chkUnlimited);
        txtAmount = (TextView) findViewById(R.id.txtAmount);
        txtTotal = (TextView) findViewById(R.id.txtTotal);

        txtTitle.setText(car.getName());

        spinner.setVisibility(View.GONE);

        txtDays.setText(String.valueOf(car.getDays()));
        seekBar.setProgress(car.getDays());
        seekBar.setEnabled(false);
        edtPrice.setText(String.valueOf(car.getRent()));

        rbtLess.setEnabled(false);
        rbtBetween.setEnabled(false);
        rbtAbove.setEnabled(false);

        switch (car.getAge()){
            case "LESS": rbtLess.setChecked(true);
            case "BETWEEN": rbtBetween.setChecked(true);
            case "ABOVE": rbtAbove.setChecked(true);
        }

        if (car.isGPS()){
            chkGPS.setEnabled(true);
        }

        if (car.isChildSeat()){
            chkChild.setEnabled(true);
        }

        if (car.isUnlimited()){
            chkUnlimited.setEnabled(true);
        }
        chkGPS.setEnabled(false);
        chkChild.setEnabled(false);
        chkUnlimited.setEnabled(false);

        txtAmount.setText(String.valueOf(car.getAmount()));
        txtTotal.setText(String.valueOf(car.getTotal()));

    }
}
