package com.example.usermanagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TreatmentListFragment extends Fragment {

    private RecyclerView recyclerView;
    private TreatmentAdapter treatmentAdapter;
    private ArrayList<Treatment> treatmentList = new ArrayList<>();

    public TreatmentListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_treatment_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewTreatmentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // استقبال كائن الصالون من الـ Bundle القادم من الشاشة السابقة
        Bundle args = getArguments();
        if (args != null && args.containsKey("salon")) {
            Salon selectedSalon = (Salon) args.getSerializable("salon");
            if (selectedSalon != null && selectedSalon.getTreatments() != null) {
                treatmentList.addAll(selectedSalon.getTreatments());
            }
        }

        // مستمع الضغط على العلاج
        TreatmentAdapter.OnTreatmentClickListener listener = new TreatmentAdapter.OnTreatmentClickListener() {
            @Override
            public void onTreatmentClick(Treatment treatment) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("selectedTreatment", treatment);
                // يمكنك أيضاً تمرير الصالون لو احتجته لاحقاً
                if (args != null && args.containsKey("salon")) {
                    bundle.putSerializable("salon", args.getSerializable("salon"));
                }
                Navigation.findNavController(view).navigate(R.id.action_treatmentListFragment_to_datePickerFragment, bundle);
            }
        };

        treatmentAdapter = new TreatmentAdapter(treatmentList, listener);
        recyclerView.setAdapter(treatmentAdapter);

        return view;
    }
}
