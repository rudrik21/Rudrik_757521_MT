package com.example.rudrik_757521_mt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {

    private Car car = new Car();
    private Spinner spinner;
    private EditText edtPrice;
    private TextView txtDays, txtAmount, txtTotal;
    private CheckBox chkGPS, chkChild, chkUnlimited;
    private AppCompatSeekBar seekBar;
    private RadioGroup radioGroup;
    private RadioButton rbtLess, rbtBetween, rbtAbove;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        spinner = (Spinner) findViewById(R.id.spinner);
        edtPrice = (EditText) findViewById(R.id.edtPrice);
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

        spinner.setOnItemSelectedListener(this);
        ArrayList<String> names = new ArrayList<>();
        for (CarDetail cd: Car.availableCars) {
            names.add(cd.getName());
        }

        ArrayAdapter<String> adpt = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, names);
        spinner.setAdapter(adpt);
        seekBar.setOnSeekBarChangeListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        chkGPS.setOnCheckedChangeListener(this);
        chkChild.setOnCheckedChangeListener(this);
        chkUnlimited.setOnCheckedChangeListener(this);

        car.setAge("LESS");
        toggleCheckBox();
        calculateTotal();
    }

    public void onViewDetails(View view){
//        calculateTotal();
        if (car.getDays()>0 && spinner.getSelectedItemPosition()>0) {
            Intent i = new Intent(this, ResultActivity.class);
            i.putExtra("CAR", car.toString());
            startActivity(i);
            System.out.println(car.toString());
        }else if(car.getDays() == 0){
            Toast.makeText(this, "Car could be rent for atleast 1 day!", Toast.LENGTH_SHORT).show();
        }else if (spinner.getSelectedItemPosition() == 0){
            Toast.makeText(this, "Please select a car to proceed!", Toast.LENGTH_SHORT).show();
        }
    }

    //  SPINNER


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (view != null && position != 0){
            TextView txt = (TextView) view;
            car.setName(txt.getText().toString());
            car.setRent(Car.availableCars.get(position).getPrice());
            edtPrice.setText(String.valueOf(car.getRent()));
        }else{
            edtPrice.setText("Daily rent");
        }
        calculateTotal();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //  SEEKBAR
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        car.setDays(progress);
        if (car.getDays()>0){
            txtDays.setText(String.valueOf(car.getDays()));
        }else{
            txtDays.setText("#Days");
        }
        calculateTotal();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    //  CHECKBOXES
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        toggleCheckBox();
    }

    //  RADIO GROUP
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getCheckedRadioButtonId()){
            case R.id.rbtLess : car.setAge("LESS"); break;
            case R.id.rbtBetween : car.setAge("BETWEEN"); break;
            case R.id.rbtAbove : car.setAge("ABOVE"); break;
        }
        calculateTotal();
    }

    private void toggleCheckBox(){
        car.setGPS(chkGPS.isChecked());
        car.setChildSeat(chkChild.isChecked());
        car.setUnlimited(chkUnlimited.isChecked());
        calculateTotal();
    }

    private void resetTotal(){
        txtAmount.setText("Amount");
        txtTotal.setText("Total Payment");
    }

    private void calculateTotal(){
        if(spinner.getSelectedItemPosition() > 0 && car.getDays() > 0) {
            car.setAmount(car.getRent());

            switch (car.getAge()) {
                case "LESS":
                    car.setAmount(car.getAmount() + 5);
                    addFeatures();
                    break;

                case "BETWEEN":
                    addFeatures();
                    break;

                case "ABOVE":
                    car.setAmount(car.getAmount() - 10);
                    addFeatures();
                    break;
            }
            car.setAmount(car.getAmount() * car.getDays());
            car.setTotal(car.getAmount() * 1.13);

            txtAmount.setText(String.valueOf(car.getAmount()));
            txtTotal.setText(String.valueOf(car.getTotal()));


        }else{
            resetTotal();
        }
    }

    private void addFeatures(){
        if (car.isGPS()){
            car.setAmount(car.getAmount() + 5);
        }

        if (car.isChildSeat()){
            car.setAmount(car.getAmount() + 7);
        }

        if (car.isUnlimited()){
            car.setAmount(car.getAmount() + 10);
        }
    }

}
