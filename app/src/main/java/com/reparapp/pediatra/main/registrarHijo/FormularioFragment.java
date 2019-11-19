package com.reparapp.pediatra.main.registrarHijo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reparapp.pediatra.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class FormularioFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_registro_formulario, container, false);
        return root;
    }
}
