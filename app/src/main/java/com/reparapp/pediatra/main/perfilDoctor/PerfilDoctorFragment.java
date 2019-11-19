package com.reparapp.pediatra.main.perfilDoctor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.reparapp.pediatra.R;
import com.reparapp.pediatra.main.MainActivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class PerfilDoctorFragment  extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_perfil_doctor, container, false);

        final Button consultar = root.findViewById(R.id.consultar);
        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogHijos();
            }
        });
        return root;
    }

    private void showDialogHijos() {
        WindowManager.LayoutParams wmlp = createLoginDialogo().getWindow().getAttributes();
        createLoginDialogo().show();

    }
    public AlertDialog createLoginDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_consultar_hijo, null);
        builder.setView(v);

        ImageView registrarHijo = (ImageView) v.findViewById(R.id.registrarHijo);
        Button  continuar=(Button) v.findViewById(R.id.continuar);



       final  AlertDialog alertDialog= builder.create();
        alertDialog.setCancelable(true);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        alertDialog.getWindow().setGravity(Gravity.BOTTOM);

        registrarHijo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showActivity(alertDialog);

                    }
                }
        );

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFormulario(alertDialog);
            }
        });
        return alertDialog;
    }

    private void showFormulario(AlertDialog  alertDialog) {
        alertDialog.dismiss();
        ((MainActivity)getActivity()).showFormulario();
    }

    private void showActivity(AlertDialog alertDialog ) {
        alertDialog.dismiss();
    ((MainActivity)getActivity()).showRegistrarHijo();
    }
}
