package com.example.usermanagement;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.navigation.Navigation;

public class ChooseSalonTypeFragment extends Fragment {
    public ChooseSalonTypeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_salon_type, container, false);

        Button btnMen = view.findViewById(R.id.btnMenSalon);
        Button btnWomen = view.findViewById(R.id.btnWomenSalon);

        btnMen.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("salonType", "men");
            Navigation.findNavController(view).navigate(R.id.action_chooseSalonTypeFragment_to_salonListFragment, bundle);
        });
        btnWomen.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("salonType", "women");
            Navigation.findNavController(view).navigate(R.id.action_chooseSalonTypeFragment_to_salonListFragment, bundle);
        });
        return view;
    }
}
