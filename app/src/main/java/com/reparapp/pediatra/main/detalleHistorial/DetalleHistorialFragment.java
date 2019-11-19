package com.reparapp.pediatra.main.detalleHistorial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reparapp.pediatra.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class DetalleHistorialFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_detalle_historial, container, false);
        return root;
    }
}
