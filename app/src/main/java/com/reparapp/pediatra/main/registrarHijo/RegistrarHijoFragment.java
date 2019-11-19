package com.reparapp.pediatra.main.registrarHijo;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.reparapp.pediatra.R;
import com.reparapp.pediatra.main.MainActivity;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RegistrarHijoFragment extends Fragment implements View.OnClickListener{
    private static final String CERO = "0";
    private static final String BARRA = "/";
    public final Calendar c = Calendar.getInstance();

    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);
    TextView nacimiento;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_registrar_hijo, container, false);
        Button registrar=(Button)root.findViewById(R.id.registrar);
        nacimiento=(TextView)root.findViewById(R.id.nacimiento);
        ImageView calendar=(ImageView)root.findViewById(R.id.calendar);
        registrar.setOnClickListener(this);
        calendar.setOnClickListener(this);
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registrar:
                ((MainActivity)getActivity()).showFormulario();
                break;
            case R.id.calendar:
                obtenerFecha();
                break;
        }
    }
    private void obtenerFecha(){
        DatePickerDialog recogerFecha = new DatePickerDialog(getContext(), R.style.DialogTheme,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final int mesActual = month + 1;

                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);

                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);

                nacimiento.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


            }
        },anio, mes, dia);
        recogerFecha.show();

    }
}
