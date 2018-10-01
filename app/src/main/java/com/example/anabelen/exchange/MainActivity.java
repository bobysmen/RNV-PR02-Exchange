package com.example.anabelen.exchange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    RadioGroup rgFrom;
    RadioGroup rgTo;
    RadioButton rbFromE;
    RadioButton rbFromD;
    RadioButton rbFromP;
    RadioButton rbToE;
    RadioButton rbToD;
    RadioButton rbToP;
    ImageView imgF;
    ImageView imgT;
    Button btnExChange;
    final double ETOD=1.16;
    final double ETOP=0.88;
    final double DTOE=0.86;
    final double DTOP=0.76;
    final double PTOE=1.12;
    final double PTOD=1.30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();

        rgFrom.setOnCheckedChangeListener((group,checkedId)->settingCurrencyFrom());
        rgTo.setOnCheckedChangeListener(((group, checkedId) -> settingCurrencyTo()));

        btnExChange.setOnClickListener(v->change());
        amount.setOnClickListener(v->selectAllText());
    }

    private void selectAllText() {
        amount.selectAll();
    }

    private void change() {
        double result= 0;
        String actuallyCurrency="";
        try {
            result = Double.parseDouble(String.valueOf(amount.getText()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if(rbFromE.isChecked()){
            if(rbToD.isChecked()){
                result=result*ETOD;
                actuallyCurrency=getString(R.string.main_textDollar);
            }
            if(rbToP.isChecked()){
                result=result*ETOP;
                actuallyCurrency=getString(R.string.main_textPound);
            }
        }else if(rbFromD.isChecked()){
            if(rbToE.isChecked()){
                result=result*DTOE;
                actuallyCurrency=getString(R.string.main_textEuro);
            }
            if(rbToP.isChecked()){
                result=result*DTOP;
                actuallyCurrency=getString(R.string.main_textPound);
            }
        }else{
            if(rbToE.isChecked()){
                result=result*PTOE;
                actuallyCurrency=getString(R.string.main_textEuro);
            }
            if(rbToD.isChecked()){
                result=result*PTOD;
                actuallyCurrency=getString(R.string.main_textDollar);
            }
        }

        Toast.makeText(this, String.format("%.2f %s",result,actuallyCurrency),Toast.LENGTH_SHORT).show();
    }

    private void setupView() {
        amount=findViewById(R.id.txtAmount);
        rgFrom=findViewById(R.id.rgCoinFrom);
        rgTo=findViewById(R.id.rgCoinTo);
        rbFromE=findViewById(R.id.rbFromEuro);
        rbFromD=findViewById(R.id.rbFromDolar);
        rbFromP=findViewById(R.id.rbFromPound);
        rbToE=findViewById(R.id.rbToEuro);
        rbToD=findViewById(R.id.rbToDolar);
        rbToP=findViewById(R.id.rbToPound);
        imgF=findViewById(R.id.imgFrom);
        imgT=findViewById(R.id.imgTo);
        btnExChange=findViewById(R.id.btnExchange);
        settingCurrencyFrom();
        settingCurrencyTo();
    }

    private void settingCurrencyTo() {
        if(rgTo.getCheckedRadioButtonId()==rbToE.getId()){
            imgT.setImageResource(R.drawable.ic_euro);
            rbFromE.setEnabled(false);
            rbFromD.setEnabled(true);
            rbFromP.setEnabled(true);
        }else if(rgTo.getCheckedRadioButtonId()==rbToD.getId()){
            imgT.setImageResource(R.drawable.ic_dollar);
            rbFromD.setEnabled(false);
            rbFromE.setEnabled(true);
            rbFromP.setEnabled(true);
        }else{
            imgT.setImageResource(R.drawable.ic_pound);
            rbFromP.setEnabled(false);
            rbFromD.setEnabled(true);
            rbFromE.setEnabled(true);
        }
    }

    private void settingCurrencyFrom() {
        if(rgFrom.getCheckedRadioButtonId()==rbFromE.getId()){
            imgF.setImageResource(R.drawable.ic_euro);
            rbToE.setEnabled(false);
            rbToD.setEnabled(true);
            rbToP.setEnabled(true);
        }else if(rgFrom.getCheckedRadioButtonId()==rbFromD.getId()){
            imgF.setImageResource(R.drawable.ic_dollar);
            rbToD.setEnabled(false);
            rbToE.setEnabled(true);
            rbToP.setEnabled(true);
        }else{
            imgF.setImageResource(R.drawable.ic_pound);
            rbToP.setEnabled(false);
            rbToD.setEnabled(true);
            rbToE.setEnabled(true);
        }

    }

}
