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

        // Dummy data; replace with Firestore as needed
        treatmentList.add(new Treatment("Test Treatment", 100, "Test Description", 1.5, "https://your-url.com/test.jpg"));

        // Treatment click navigates to DatePickerFragment with treatment data in bundle
        TreatmentAdapter.OnTreatmentClickListener listener = new TreatmentAdapter.OnTreatmentClickListener() {
            @Override
            public void onTreatmentClick(Treatment treatment) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("selectedTreatment", treatment);
                // If you have salonType, add: bundle.putString("salonType", ...);
                Navigation.findNavController(view).navigate(R.id.action_treatmentListFragment_to_datePickerFragment, bundle);
            }
        };

        treatmentAdapter = new TreatmentAdapter(treatmentList, listener);
        recyclerView.setAdapter(treatmentAdapter);

        return view;
    }
}
