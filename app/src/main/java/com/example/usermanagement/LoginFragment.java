package com.example.usermanagement;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.navigation.Navigation;

public class LoginFragment extends Fragment {

    private EditText etUsername, etPassword;
    private TextView tvSignupLink, tvForgetPassLink;
    private Button btnLogin;
    private FirebaseServices fbs;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        fbs = FirebaseServices.getInstance();
        etUsername = getView().findViewById(R.id.etUsernameLogin);
        etPassword = getView().findViewById(R.id.etPasswordLogin);
        btnLogin = getView().findViewById(R.id.btnLoginLogin);
        tvSignupLink = getView().findViewById(R.id.tvSignupLinkLogin);
        tvForgetPassLink = getView().findViewById(R.id.tvForgotPasswordLinkLogin);

        tvSignupLink.setOnClickListener(view -> gotoSignupFragment(view));
        tvForgetPassLink.setOnClickListener(view -> gotoForgetPassword(view));

        btnLogin.setOnClickListener(view -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            if (username.trim().isEmpty() || password.trim().isEmpty()) {
                Toast.makeText(getActivity(), "Please enter both username and password!", Toast.LENGTH_SHORT).show();
                return;
            }

            fbs.getAuth().signInWithEmailAndPassword(username, password)
                    .addOnSuccessListener(authResult -> {
                        Toast.makeText(getActivity(), "Login successful!", Toast.LENGTH_SHORT).show();
                        gotoAddFragment(view);
                    })
                    .addOnFailureListener(e ->
                            Toast.makeText(getActivity(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show()
                    );
        });
    }

    // Use Navigation Component for all navigations!
    private void gotoForgetPassword(View view) {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_forgotPasswordFragment);
    }

    private void gotoAddFragment(View view) {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_chooseSalonTypeFragment);
    }

    private void gotoSignupFragment(View view) {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signupFragment);
    }
}
