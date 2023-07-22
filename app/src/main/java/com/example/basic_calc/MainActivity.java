package com.example.basic_calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv,solTv;
    MaterialButton btnC,open_Brac,close_Brac;
    MaterialButton divi,mult,subt,addi,equals;
    MaterialButton b0,b1,b2,b3,b4,b5,b6,b7,b8,b9;
    MaterialButton clear,point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv=findViewById(R.id.result_tv);
        solTv=findViewById(R.id.sol_tv);

        assignId(btnC,R.id.btnC);
        assignId(open_Brac,R.id.openBrac);
        assignId(close_Brac,R.id.closeBraC);
        assignId(divi,R.id.div);
        assignId(mult,R.id.mul);
        assignId(subt,R.id.sub);
        assignId(addi,R.id.add);
        assignId(equals,R.id.equal);
        assignId(b0,R.id.btn0);
        assignId(b1,R.id.btn1);
        assignId(b2,R.id.btn2);
        assignId(b3,R.id.btn3);
        assignId(b4,R.id.btn4);
        assignId(b5,R.id.btn5);
        assignId(b6,R.id.btn6);
        assignId(b7,R.id.btn7);
        assignId(b8,R.id.btn8);
        assignId(b9,R.id.btn9);
        assignId(clear,R.id.ac);
        assignId(point,R.id.dot);

    }

    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton) view;
        String buttonText=button.getText().toString();
        String dCal=solTv.getText().toString();

        if(buttonText.equals("AC")){
            solTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solTv.setText((resultTv.getText()));
            return;
        }
        if(buttonText.equals("C")){
            if(dCal.length()>0){
                dCal=dCal.substring(0,dCal.length()-1);
            }
        }
        else{
            dCal=dCal+buttonText;
        }
        solTv.setText(dCal);

        String finalRe=getResult(dCal);

        if(!finalRe.equals("Error")){
            resultTv.setText(finalRe);
        }
    }

    String getResult(String data){
        if(data.isEmpty()){
            return "0";
        }
        try{
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initSafeStandardObjects();
            String finalRe=context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalRe.endsWith(".0")){
                finalRe=finalRe.replace(".0","");
            }
            return finalRe;
        }catch(Exception e){
            return "Error";
        }

    }
}