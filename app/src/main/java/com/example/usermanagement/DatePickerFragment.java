package com.example.usermanagement;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.navigation.Navigation;
import java.util.Calendar;

public class DatePickerFragment extends Fragment {
    private TextView tvDate;
    private Button btnNext;
    private String selectedDate = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_picker, container, false);
        tvDate = view.findViewById(R.id.tvDate);
        btnNext = view.findViewById(R.id.btnNext);

        tvDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                    (view1, year, month, dayOfMonth) -> {
                        selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        tvDate.setText(selectedDate);
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });

        btnNext.setOnClickListener(v -> {
            if (selectedDate.isEmpty()) {
                Toast.makeText(getActivity(), "Please select a date!", Toast.LENGTH_SHORT).show();
                return;
            }
            // Pass along ALL previous data
            Bundle bundle = getArguments() != null ? new Bundle(getArguments()) : new Bundle();
            bundle.putString("selectedDate", selectedDate);
            Navigation.findNavController(view).navigate(R.id.action_datePickerFragment_to_timePickerFragment, bundle);
        });

        return view;
    }
}
