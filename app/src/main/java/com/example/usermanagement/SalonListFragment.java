package com.example.usermanagement;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.Navigation;
import java.util.ArrayList;

public class SalonListFragment extends Fragment implements SalonAdapter.OnSalonClickListener {
    private RecyclerView recyclerView;
    private SalonAdapter salonAdapter;
    private ArrayList<Salon> salonList = new ArrayList<>();

    public SalonListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_salon_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewSalon);

        String salonType = getArguments() != null ? getArguments().getString("salonType", "men") : "men";
        salonList = Utils.getAllSalonsByType(salonType); // Implement this in Utils as a static function

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        salonAdapter = new SalonAdapter(getContext(), salonList, this);
        recyclerView.setAdapter(salonAdapter);

        return view;
    }

    @Override
    public void onSalonClick(Salon salon) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("salon", salon);
        Navigation.findNavController(requireView()).navigate(R.id.action_salonListFragment_to_treatmentListFragment, bundle);
    }
}
