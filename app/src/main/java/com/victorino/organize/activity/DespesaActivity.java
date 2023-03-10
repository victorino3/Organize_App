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

public class DespesaActivity extends AppCompatActivity {
    private EditText moneyPlaceExpense;
    private TextInputEditText datePlaceExpense, categoryPlaceExpense,expense;
    private FloatingActionButton sendToFirebaseExpense;
    private Transaction transaction;
    private DatabaseReference userDetail = FirebaseConfig.getUserConn();
    private FirebaseAuth userAuth = FirebaseConfig.getAuth();
    private Double currentExpense;
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

        getExpenseFromUser();
        sendToFirebaseExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verifyFieldsBeforeSave()){
                    transaction = new Transaction();
                    transaction.setMoney(Double.parseDouble(moneyPlaceExpense.getText().toString()));
                    double expense_1 = Double.parseDouble(moneyPlaceExpense.getText().toString());
                    double expenseToUpdate = expense_1 + currentExpense;

                    updateExpense(expenseToUpdate);

                    transaction.setCategory(categoryPlaceExpense.getText().toString());
                    transaction.setDate(datePlaceExpense.getText().toString());
                    transaction.setDescription(expense.getText().toString());
                    transaction.setType("expense");
                    transaction.saveTransactionExpense(datePlaceExpense.getText().toString());
                }

            }
        });



    }
    public Boolean verifyFieldsBeforeSave(){
        String expenseMoney = moneyPlaceExpense.getText().toString();
        String date = datePlaceExpense.getText().toString();
        String infoExpense = expense.getText().toString();
        String expenseCategory = categoryPlaceExpense.getText().toString();
        if(expenseMoney.isEmpty() || date.isEmpty() || infoExpense.isEmpty() || expenseCategory.isEmpty()){
            Toast.makeText(getApplicationContext(), R.string.makeTestToExpenseBlank,Toast.LENGTH_LONG).show();
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
                currentExpense = userInformation.getExpense();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void updateExpense(double expenseToUpdate){
        String emailToEncode = userAuth.getCurrentUser().getEmail();
        String userId = EncodeB64.encodeMail(emailToEncode);
        DatabaseReference userNode = userDetail.child("Users").child(userId);
        userNode.child("expense").setValue(expenseToUpdate);
    }
}