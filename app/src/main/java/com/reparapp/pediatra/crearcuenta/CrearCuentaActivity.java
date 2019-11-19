package com.reparapp.pediatra.crearcuenta;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.reparapp.pediatra.R;
import com.reparapp.pediatra.crearcuenta.seccionDos.SeccionTarjeta;
import com.reparapp.pediatra.crearcuenta.seccionUno.SeccionCrear;
import com.reparapp.pediatra.crearcuenta.viewPager.SectionsPagerAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CrearCuentaActivity extends AppCompatActivity implements SeccionCrear.OnFragmentInteractionListener, SeccionTarjeta.OnFragmentInteractionListener {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.cont_puntos)
    LinearLayout contPuntos;
    private TextView[] puntosSlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        ButterKnife.bind(this);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
      //  ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        listenerIniciar();
        config();
        agregarPuntosViewPager(0);
    }

    private void config() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //change color of status bar
            Window window = this.getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimaryDark));
        }
    }


    private void agregarPuntosViewPager(int posicion) {
        puntosSlide= new TextView[2];
        contPuntos.removeAllViews();
        for(int i=0;i<puntosSlide.length;i++){
            puntosSlide[i]=new TextView(this);
            puntosSlide[i].setText(Html.fromHtml("&#8226;"));
            puntosSlide[i].setTextSize(50);
            puntosSlide[i].setTextColor(getResources().getColor(R.color.grey_1));
            contPuntos.addView(puntosSlide[i]);
        }
        if(puntosSlide.length>0 ){
            puntosSlide[posicion].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        }
        if(puntosSlide.length==(posicion+1) ){
            puntosSlide[posicion].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            puntosSlide[posicion-1].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }

    }
    private void listenerIniciar() {

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                agregarPuntosViewPager(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}