package com.reparapp.pediatra.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.reparapp.pediatra.R;
import com.reparapp.pediatra.crearcuenta.CrearCuentaActivity;
import com.reparapp.pediatra.main.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.ingresar)
    Button ingresar;
    @BindView(R.id.registrar)
    TextView registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_NoActionBar);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        registrar.setOnClickListener(this);
        ingresar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ingresar:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
            case R.id.registrar:
                startActivity(new Intent(getApplicationContext(), CrearCuentaActivity.class));
                break;
        }
    }
}
