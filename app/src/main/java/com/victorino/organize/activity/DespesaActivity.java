package com.victorino.organize.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.victorino.organize.R;
import com.victorino.organize.helper.DateCustom;
import com.victorino.organize.model.Transaction;

public class DespesaActivity extends AppCompatActivity {
    private EditText moneyPlaceExpense;
    private TextInputEditText datePlaceExpense, categoryPlaceExpense,expense;
    private FloatingActionButton sendToFirebaseExpense;
    private Transaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        moneyPlaceExpense = findViewById(R.id.moneyPlaceExpense);
        datePlaceExpense = findViewById(R.id.datePlaceExpense);
        categoryPlaceExpense = findViewById(R.id.categoryExpense);
        expense = findViewById(R.id.expence);
        datePlaceExpense.setText(DateCustom.dateUtil());

        sendToFirebaseExpense = findViewById(R.id.floatingActionButtonExpense);
        sendToFirebaseExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = new Transaction();
                transaction.setMoney(Double.parseDouble(moneyPlaceExpense.getText().toString()));
                transaction.setCategory(categoryPlaceExpense.getText().toString());
                transaction.setDate(datePlaceExpense.getText().toString());
                transaction.setDescription(expense.getText().toString());
                transaction.saveTransactionExpense(datePlaceExpense.getText().toString());
            }
        });
    }
}