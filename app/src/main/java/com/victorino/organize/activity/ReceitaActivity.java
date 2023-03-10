package com.victorino.organize.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.victorino.organize.R;
import com.victorino.organize.config.FirebaseConfig;
import com.victorino.organize.helper.DateCustom;
import com.victorino.organize.helper.EncodeB64;
import com.victorino.organize.model.Transaction;
import com.victorino.organize.model.UserInformation;

public class ReceitaActivity extends AppCompatActivity {
    private EditText moneyPlace;
    private TextInputEditText datePlace, categoryIncome,income;
    private FloatingActionButton sendToFirebase;
    private Transaction transaction;
    private DatabaseReference userDetail = FirebaseConfig.getUserConn();
    private FirebaseAuth userAuth = FirebaseConfig.getAuth();
    private Double currentIncome;
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

        getExpenseFromUser();

        sendToFirebase = findViewById(R.id.floatingActionButtonIncome);
        sendToFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"Work",Toast.LENGTH_LONG).show();
                if(verifyFieldsBeforeSave()) {
                    transaction = new Transaction();
                    transaction.setMoney(Double.parseDouble(moneyPlace.getText().toString()));
                    double income_1 = Double.parseDouble(moneyPlace.getText().toString());
                    double expenseToUpdate = income_1 + currentIncome;
                    updateExpense(expenseToUpdate);
                    transaction.setCategory(categoryIncome.getText().toString());
                    transaction.setDate(datePlace.getText().toString());
                    transaction.setDescription(income.getText().toString());
                    transaction.saveTransactionExpense(datePlace.getText().toString());
                }
            }
        });


    }
    public Boolean verifyFieldsBeforeSave(){
        String expenseMoney = moneyPlace.getText().toString();
        String date = datePlace.getText().toString();
        String infoExpense = income.getText().toString();
        String expenseCategory = categoryIncome.getText().toString();
        if(expenseMoney.isEmpty() || date.isEmpty() || infoExpense.isEmpty() || expenseCategory.isEmpty()){
            Toast.makeText(getApplicationContext(), R.string.makeTestToIncomeBlank,Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public void getExpenseFromUser(){
        String emailToEncode = userAuth.getCurrentUser().getEmail();
        String userId = EncodeB64.encodeMail(emailToEncode);
        DatabaseReference userNode = userDetail.child("Users").child(userId);
        userNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserInformation userInformation = snapshot.getValue(UserInformation.class);
                currentIncome = userInformation.getIncome();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void updateExpense(double incomeToUpdate){
        String emailToEncode = userAuth.getCurrentUser().getEmail();
        String userId = EncodeB64.encodeMail(emailToEncode);
        DatabaseReference userNode = userDetail.child("Users").child(userId);
        userNode.child("income").setValue(incomeToUpdate);
    }
}