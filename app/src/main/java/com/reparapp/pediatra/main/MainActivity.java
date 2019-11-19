package com.reparapp.pediatra.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.reparapp.pediatra.R;
import com.reparapp.pediatra.login.LoginActivity;
import com.reparapp.pediatra.main.detalleHistorial.DetalleHistorialFragment;
import com.reparapp.pediatra.main.historial.HistorialFragment;
import com.reparapp.pediatra.main.home.HomeFragment;
import com.reparapp.pediatra.main.listener.HistorialListener;
import com.reparapp.pediatra.main.metodoPago.MetodoPagoFragment;
import com.reparapp.pediatra.main.micuenta.CuentaFragment;
import com.reparapp.pediatra.main.perfilDoctor.PerfilDoctorFragment;
import com.reparapp.pediatra.main.registrarHijo.FormularioFragment;
import com.reparapp.pediatra.main.registrarHijo.RegistrarHijoFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.ui.AppBarConfiguration;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener , View.OnClickListener {

    @BindView(R.id.btnBuscar)
    ImageView btnBuscar;
    @BindView(R.id.titulo)
    TextView titulo;
    @BindView(R.id.btnMenu)
    ImageView btnMenu;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    private AppBarConfiguration mAppBarConfiguration;
    TextView cuenta;
//    ConstraintLayout  headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        setToolbar();

        View headerView = navigationView.getHeaderView(0);
        cuenta = headerView.findViewById(R.id.cuenta);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_principal,
//                R.id.nav_historial, R.id.nav_pago, R.id.nav_cerrar_sesion)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);


        if (navigationView != null) setupDrawerContent();
        onclicks();

    }

    private void onclicks() {
        cuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Abrir cuenta ", Toast.LENGTH_SHORT).show();

                showFragment(new CuentaFragment());
                drawer.closeDrawers();
                setTitleToolbar("Mi cuenta");
            }
        });
        btnBuscar.setOnClickListener(this);
        btnMenu.setOnClickListener(this);

    }

    private void setupDrawerContent() {
        navigationView.setNavigationItemSelectedListener(this);
        MenuItem menuItem = navigationView.getMenu().getItem(0);
        onNavigationItemSelected(menuItem);


    }

    private void selectItem(MenuItem menuItem) {
        String title = menuItem.getTitle().toString();
        int id = menuItem.getItemId();

        switch (id) {
            case R.id.nav_historial:
                setTitleToolbar(title);
                showFragment(new  HistorialFragment());
                break;
            case R.id.nav_pago:
                setTitleToolbar(title);
                showFragment(new MetodoPagoFragment());
                break;
            case R.id.nav_cerrar_sesion:
//                Toast.makeText(getApplicationContext(), "Cerrar Sesión ", Toast.LENGTH_SHORT).show();
                mostrarConfirmacion().show();
                break;
            default://buscar pediatra- home
                setTitleToolbar(title);
                showFragment(new HomeFragment());
                break;
        }
        drawer.closeDrawers();


    }

    private AlertDialog mostrarConfirmacion() {

//        return new AlertDialog.Builder(this)
//                .setTitle("¿Seguro desea cerra sesión?")
//                .setPositiveButton("Si",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//                                // do something...
//                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//                            }
//                        }
//                )
//                .setNegativeButton("No",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//                                dialog.dismiss();
//                            }
//                        }
//                )
//                .create();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_cerrar_sesion, null);
        builder.setView(v);
        TextView salir= (TextView) v.findViewById(R.id.salir);
        TextView cancelar= (TextView) v.findViewById(R.id.cancelar);

        AlertDialog alertDialog= builder.create();
        alertDialog.setCancelable(true);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                salir.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
                alertDialog.dismiss();
            }
        });
        return alertDialog;

    }

    private void setTitleToolbar(String title) {
        titulo.setText(title);
    }

    public void showFragment(Fragment fragment) {
        // Bundle args = new Bundle();
        // args.putString(fragment.ARG_SECTION_TITLE, title);

        // Fragment fragment = PlaceholderFragment.newInstance(title);
        // fragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit();
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
    private void setToolbar() {
        setSupportActionBar(toolbar);
//        final ActionBar ab = getSupportActionBar();
//        if (ab != null) {
//            ab.setHomeAsUpIndicator(R.drawable.ic_buscar);
//            ab.setDisplayHomeAsUpEnabled(true);
//
//        }
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.action_settings:
//                drawer.openDrawer(GravityCompat.START);
//                return true;
//            default:
//                Toast.makeText(getApplicationContext(), "buscar ", Toast.LENGTH_SHORT).show();
//                return true;
//        }
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        Toast.makeText(getApplicationContext(), "menuItem", Toast.LENGTH_SHORT).show();
        menuItem.setChecked(true);
        selectItem(menuItem);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBuscar:
                Toast.makeText(getApplicationContext(), "Buscar ", Toast.LENGTH_SHORT).show();break;
            case R.id.btnMenu:
                drawer.openDrawer(GravityCompat.START);
                break;

        }
    }


//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }

    public void showDetalleHistorial(){
        setTitleToolbar("Historial Detalle");
        showFragment(new DetalleHistorialFragment());
    }
    public void showPerfilDoctor(){
        setTitleToolbar("Perfil Doctor");
        showFragment(new PerfilDoctorFragment());
    }

    public void showRegistrarHijo() {
        setTitleToolbar("Registrar hijo");
        showFragment(new RegistrarHijoFragment());
    }

    public void showFormulario() {
        setTitleToolbar("Formulario");
        showFragment(new FormularioFragment());
    }
}
