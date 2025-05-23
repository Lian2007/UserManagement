package com.example.usermanagement;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AllSalon extends Fragment {

    private FirebaseServices fbs;
    private ArrayList<Salon> salonArrayList;
    private RecyclerView rvSalon;
    private SalonAdapter adapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public AllSalon() {
        // Required empty public constructor
    }

    public static AllSalon newInstance(String param1, String param2) {
        AllSalon fragment = new AllSalon();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_salon, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        fbs = FirebaseServices.getInstance();
        salonArrayList = new ArrayList<>();
        rvSalon = getView().findViewById(R.id.rvSalonFragment);

        // Listener for salon click events
        SalonAdapter.OnSalonClickListener listener = new SalonAdapter.OnSalonClickListener() {
            @Override
            public void onSalonClick(Salon salon) {
                // Handle the click. Example: show a Toast.
                Toast.makeText(getActivity(), "Clicked: " + salon.getName(), Toast.LENGTH_SHORT).show();
                // Or navigate to another fragment/activity with this salon.
            }
        };

        adapter = new SalonAdapter(getActivity(), salonArrayList, listener);
        rvSalon.setAdapter(adapter);
        rvSalon.setHasFixedSize(true);
        rvSalon.setLayoutManager(new LinearLayoutManager(getActivity()));

        fbs.getFire().collection("salon").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot dataSnapshot : queryDocumentSnapshots.getDocuments()) {
                    Salon sal = dataSnapshot.toObject(Salon.class);
                    salonArrayList.add(sal);
                }
                adapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "No data available", Toast.LENGTH_SHORT).show();
                Log.e("AllSalonFragment", e.getMessage());
            }
        });
    }
}
