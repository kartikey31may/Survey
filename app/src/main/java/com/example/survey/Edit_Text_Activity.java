package com.example.survey;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Edit_Text_Activity extends AppCompatActivity {

    TextView questionText;
    Switch integer_switch;
    String value,s1;
    Button btn_image;
    boolean imag;
    int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__text_);
        integer_switch = findViewById(R.id.switch1);
        //btn_image = findViewById(R.id.btn_add_image_edit_text);
        imag = false;
        integer_switch.setChecked(false);
        final EditText question = findViewById(R.id.question_edit_text);
        questionText = findViewById(R.id.question);

        question.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                questionText.setText("Q. "+ question.getText().toString()+" ?");
                s1=question.getText().toString();
                if(s1.isEmpty()){
                    flag=0;
                }
                else{flag=1;}

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        integer_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!imag){
                    imag = true;
                    //question.setText("");
                    //question.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

                }else{
                    //question.setText("");
                    //question.setInputType(InputType.TYPE_CLASS_TEXT);
                    imag = false;
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder al=new AlertDialog.Builder(this);
        al.setTitle("Exit");
        al.setMessage("Saving data or not");

        al.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(flag==1){
                    try {
                        returnResult();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }}
                else{
                    Toast.makeText(Edit_Text_Activity.this, "Enter your Question", Toast.LENGTH_SHORT).show();
                }
            }
        });
        al.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        al.setCancelable(true);
        al.show();
    }

    public void returnResult() throws JSONException {
        Intent intent = new Intent();
        value = questionText.getText().toString();

        intent.putExtra("question", value);
        JSONObject obj = new JSONObject();

        obj.put("type", "EditText");
        obj.put("value", imag);
        obj.put("question", value);

        intent.putExtra("image", imag);
        intent.putExtra("json", obj.toString());

        setResult(201,intent);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_option:
                if(flag==1){
                try {
                    returnResult();
                } catch (JSONException e) {
                    e.printStackTrace();
                }}
                else{
                    Toast.makeText(this, "Enter your Question", Toast.LENGTH_SHORT).show();
                }
                break;

        }
        return true;
    }
}
