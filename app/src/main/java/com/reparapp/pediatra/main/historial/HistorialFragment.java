package com.reparapp.pediatra.main.historial;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reparapp.pediatra.R;
import com.reparapp.pediatra.main.MainActivity;


public class HistorialFragment extends Fragment {
    private HistorialViewModel historialViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        historialViewModel =
                ViewModelProviders.of(this).get(HistorialViewModel.class);
        View root = inflater.inflate(R.layout.fragment_historial, container, false);
        final CardView cardView = root.findViewById(R.id.contenedor);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).showDetalleHistorial();
            }
        });

        return root;
    }
}
