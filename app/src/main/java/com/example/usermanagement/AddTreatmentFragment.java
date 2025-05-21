package com.example.usermanagement;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;

public class AddTreatmentFragment extends Fragment{
    private static final int GALLERY_REQUEST_CODE = 123;
    private Utils utils;


    /**
     * A simple {@link Fragment} subclass.
     * Use the {@link com.example.usermanagement.AddFragment#newInstance} factory method to
     * create an instance of this fragment.
     */
//        private EditText etPhoneNum;
//        private TextView tvSalonName, tvWebsite, tvAddress;
//        private Button btnAdd;
        private  FirebaseServices fbs;
        private ImageView img;
        private TextInputEditText etName, etPrice, etDesc,etTime;
        private MaterialButton btnAdd;




        // TODO: Rename parameter arguments, choose names that match

        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        // TODO: Rename and change types of parameters
        private String mParam1;
        private String mParam2;

        public AddTreatmentFragment() {
            // Required empty public constructor
        }

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddFragment.
         */
        // TODO: Rename and change types and number of parameters
        public static com.example.usermanagement.AddFragment newInstance(String param1, String param2) {
            com.example.usermanagement.AddFragment fragment = new com.example.usermanagement.AddFragment();
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

            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.add_treatment_fragment, container, false);

        }

        @Override
        public void onStart(){
            super.onStart();
            connectComponents();
        }
        private void connectComponents() {
            if (getView() == null) {
                Log.e("SalonFragment", "View is null, cannot initialize components");
                return;
            }

            fbs = FirebaseServices.getInstance();
            etName = getView().findViewById(R.id.editTextName);
            etPrice = getView().findViewById(R.id.editTextPrice);
            etDesc = getView().findViewById(R.id.editTextDescription);
            etTime = getView().findViewById(R.id.editTextTime);
            img = getView().findViewById(R.id.imageViewTreatment);
            btnAdd = getView().findViewById(R.id.buttonAddTreatment);
            utils = Utils.getInstance();
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openGallery();
                }
            });


            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("the button is clicked");
//                    String SalonName, Phone, Website, Address;
                    String treatName,desc,priceText,timeText;
                    double time;
                    int price;

//                    SalonName = tvSalonName.getText().toString();
//                    Phone = etPhoneNum.getText().toString();
//                    Website = tvWebsite.getText().toString();
//                    Address = tvAddress.getText().toString();

                    treatName = etName.getText().toString();
                    desc=etDesc.getText().toString();
                    priceText=etPrice.getText().toString().trim();
                    timeText=etTime.getText().toString().trim();

                    if (treatName.trim().isEmpty() || desc.trim().isEmpty() ||
                           priceText.isEmpty()|| timeText.isEmpty() || fbs.getSelectedImageURL()==null) {
                        Toast.makeText(getActivity(), "  some fields are empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    price=0;
                    try {
                        price = Integer.parseInt(priceText);
                    } catch (NumberFormatException e) {
                        e.printStackTrace(); // Handle invalid number
                        Toast.makeText(getContext(), "Please enter a valid number", Toast.LENGTH_SHORT).show();
                    }
                    time=0.0;
                    try {
                        time = Double.parseDouble(timeText);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "Please enter a valid time", Toast.LENGTH_SHORT).show();
                    }
                    Treatment treatment = new Treatment(treatName,price,desc,time,fbs.getSelectedImageURL().toString());
                    //System.out.println("salon:"+salon);
                    fbs.getFire().collection("treatments").add(treatment).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                                                           @Override
                                                                                           public void onSuccess(DocumentReference documentReference) {
                                                                                               Toast.makeText(getActivity(), "Successfully added your salon!", Toast.LENGTH_SHORT).show();

                                                                                           }
                                                                                       }
                    ).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("Failure Add salon: ", e.getMessage());

                        }

                    });

                }
            });
            ;;}

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == getActivity().RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            img.setImageURI(selectedImageUri);
            utils.uploadImage(getActivity(), selectedImageUri);
        }
    }



    }

