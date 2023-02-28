package com.victorino.organize.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.victorino.organize.R;
import com.victorino.organize.helper.DateCustom;
import com.victorino.organize.model.Transaction;

public class ReceitaActivity extends AppCompatActivity {
    private EditText moneyPlace;
    private TextInputEditText datePlace, categoryIncome,income;
    private FloatingActionButton sendToFirebase;
    private Transaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        moneyPlace = findViewById(R.id.moneyPlaceIncome);
        datePlace = findViewById(R.id.datePlaceInc);
        categoryIncome = findViewById(R.id.categoryInc);
        income = findViewById(R.id.income);
        datePlace.setText(DateCustom.dateUtil());
        sendToFirebase = findViewById(R.id.floatingActionButtonIncome);
        sendToFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"Work",Toast.LENGTH_LONG).show();
                transaction = new Transaction();
                transaction.setMoney(Double.parseDouble(moneyPlace.getText().toString()));
                transaction.setCategory(categoryIncome.getText().toString());
                transaction.setDate(datePlace.getText().toString());
                transaction.setDescription(income.getText().toString());
                transaction.saveTransactionExpense(datePlace.getText().toString());
            }
        });


    }
}