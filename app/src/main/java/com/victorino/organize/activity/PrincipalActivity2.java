package com.victorino.organize.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.victorino.organize.R;
import com.victorino.organize.config.FirebaseConfig;
import com.victorino.organize.databinding.ActivityPrincipal2Binding;

public class PrincipalActivity2 extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityPrincipal2Binding binding;
    private MaterialCalendarView calendarView;
    private TextView textCurrentMoney;
    private TextView greet;
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPrincipal2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        recyclerView = findViewById(R.id.recyclerView);
        calendarView = findViewById(R.id.calendar);
        greet = findViewById(R.id.textgreet);
        textCurrentMoney = findViewById(R.id.textSaldo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Organize");
        setSupportActionBar(toolbar);
        configCalendar();
        greetUser();

        /*NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_principal2);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);*/

       /* binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuLogout:
                FirebaseAuth logoutUser = FirebaseConfig.getAuth();
                logoutUser.signOut();
                startActivity(new Intent(this,MainActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void AddExpense(View view){
        startActivity(new Intent(this,DespesaActivity.class));
    }
    public void AddIncome(View view){
        startActivity(new Intent(this,ReceitaActivity.class));
    }
    public void AiSuggestion(View view){
        //Ai snippets
    }
    public void greetUser(){}
    public void configCalendar(){
        String[] month = {"Janeiro","Feverreiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
        calendarView.setTitleMonths(month);
    }


}