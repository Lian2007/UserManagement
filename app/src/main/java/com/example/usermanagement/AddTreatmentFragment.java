package com.example.usermanagement;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class AddTreatmentFragment extends Fragment {
    private static final int GALLERY_REQUEST_CODE = 123;

    private FirebaseServices fbs;
    private ImageView img;
    private TextInputEditText etName, etPrice, etDesc, etTime;
    private MaterialButton btnAdd;
    private Uri selectedImageUri = null;

    public AddTreatmentFragment() {}

    public static AddTreatmentFragment newInstance(String param1, String param2) {
        AddTreatmentFragment fragment = new AddTreatmentFragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_treatment_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        connectComponents();
    }

    private void connectComponents() {
        if (getView() == null) {
            Log.e("AddTreatmentFragment", "View is null, cannot initialize components");
            return;
        }

        fbs = FirebaseServices.getInstance();
        etName = getView().findViewById(R.id.editTextName);
        etPrice = getView().findViewById(R.id.editTextPrice);
        etDesc = getView().findViewById(R.id.editTextDescription);
        etTime = getView().findViewById(R.id.editTextTime);
        img = getView().findViewById(R.id.imageViewTreatment);
        btnAdd = getView().findViewById(R.id.buttonAddTreatment);

        img.setOnClickListener(view -> openGallery());

        btnAdd.setOnClickListener(view -> {
            String treatName = etName.getText().toString();
            String desc = etDesc.getText().toString();
            String priceText = etPrice.getText().toString().trim();
            String timeText = etTime.getText().toString().trim();

            if (treatName.trim().isEmpty() || desc.trim().isEmpty() ||
                    priceText.isEmpty() || timeText.isEmpty() || fbs.getSelectedImageURL() == null) {
                Toast.makeText(getActivity(), "Some fields are empty or image not uploaded", Toast.LENGTH_SHORT).show();
                return;
            }

            int price;
            double time;
            try {
                price = Integer.parseInt(priceText);
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Please enter a valid price", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                time = Double.parseDouble(timeText);
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Please enter a valid time", Toast.LENGTH_SHORT).show();
                return;
            }

            Treatment treatment = new Treatment(treatName, price, desc, time, fbs.getSelectedImageURL().toString());

            fbs.getFire().collection("treatments").add(treatment)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(getActivity(), "Successfully added your treatment!", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Log.e("Failure Add treatment: ", e.getMessage());
                    });
        });
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == getActivity().RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            img.setImageURI(selectedImageUri);

            // Upload image and save URL in FirebaseServices
            Utils.uploadImage(getActivity(), selectedImageUri, new Utils.UploadImageCallback() {
                @Override
                public void onSuccess(String downloadUrl) {
                    fbs.setSelectedImageURL(Uri.parse(downloadUrl));
                    Toast.makeText(getActivity(), "Image uploaded!", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onFailure(Exception e) {
                    Toast.makeText(getActivity(), "Failed to upload image", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
