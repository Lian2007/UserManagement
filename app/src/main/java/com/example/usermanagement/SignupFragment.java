package com.example.usermanagement;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;

public class SignupFragment extends Fragment {

    private EditText etUsername, etPassword;
    private Button btnSignup;
    private FirebaseServices fbs;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public SignupFragment() {}

    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
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
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        fbs = FirebaseServices.getInstance();
        etUsername = getView().findViewById(R.id.etUsernameSignup);
        etPassword = getView().findViewById(R.id.etPasswordSignup);
        btnSignup = getView().findViewById(R.id.btnSignupSignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                if(username.trim().isEmpty() || password.trim().isEmpty()){
                    Toast.makeText(getActivity(), "Some fields are empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                fbs.getAuth().createUserWithEmailAndPassword(username, password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(getActivity(), "Success!", Toast.LENGTH_SHORT).show();
                                gotoAddFragment(view);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }

    private void gotoAddFragment(View view) {
        // Use navigation component to go to AddTreatmentFragment
        Navigation.findNavController(view).navigate(R.id.action_signupFragment_to_loginFragment);
    }
}
