/* package com.example.usermanagement;

//import android.content.Intent;
//import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

//**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
/*public class AddFragment extends Fragment {
    private EditText etPhoneNum;
    private TextView tvSalonName, tvWebsite, tvAddress;
    private Button btnAdd;
    private  FirebaseServices fbs;
    ImageView img;



    // TODO: Rename parameter arguments, choose names that match

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddFragment() {
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
   /* public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
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
        return inflater.inflate(R.layout.add_fragment, container, false);

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
        tvSalonName = getView().findViewById(R.id.tvSalonName);
        etPhoneNum = getView().findViewById(R.id.etPhoneNum);
        tvWebsite = getView().findViewById(R.id.tvWebsite);
        tvAddress = getView().findViewById(R.id.tvAddress);
        img = getView().findViewById(R.id.IVPreviewImage);
        btnAdd = getView().findViewById(R.id.btnAdd);

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
                String SalonName, Phone, Website, Address;

                SalonName = tvSalonName.getText().toString();
                Phone = etPhoneNum.getText().toString();
                Website = tvWebsite.getText().toString();
                Address = tvAddress.getText().toString();



                if (SalonName.trim().isEmpty() || Phone.trim().isEmpty() ||
                        Address.trim().isEmpty() ) {
                    Toast.makeText(getActivity(), "  some fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                Salon salon = new Salon(SalonName, Address, Phone, Website);
                //System.out.println("salon:"+salon);
                fbs.getFire().collection("salons").add(salon).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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

    private  void openGallery(){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent,123);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123 && resultCode == getActivity().RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                img.setImageURI(selectedImageUri);

            }
        }
    }
} */


