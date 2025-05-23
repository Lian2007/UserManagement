package com.example.usermanagement;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Utils {
    public static ArrayList<Salon> getAllSalonsByType(String type) {
        ArrayList<Salon> salons = new ArrayList<>();
        ArrayList<Treatment> maleTreatments = new ArrayList<>();
        maleTreatments.add(new Treatment("Men's Haircut", 50, "Basic men's cut", 0.5, ""));
        maleTreatments.add(new Treatment("Beard Trim", 30, "Trim and shape beard", 0.2, ""));


        ArrayList<Treatment> femaleTreatments = new ArrayList<>();
        femaleTreatments.add(new Treatment("Women's Haircut", 70, "Basic women's cut", 0.7, ""));
        femaleTreatments.add(new Treatment("Hair Coloring", 120, "Full color service", 1.2, ""));

        if ("men".equals(type)) {
            salons.add(new Salon("Men Salon 1", "050-0000000", "men", maleTreatments));
            salons.add(new Salon("Men Salon 2", "050-1111111", "men", maleTreatments));
        } else {
            salons.add(new Salon("Women Salon 1", "050-2222222", "women", femaleTreatments));
            salons.add(new Salon("Women Salon 2", "050-3333333", "women", femaleTreatments));
        }
        return salons;
    }

    // ---- Add this method for image upload ----
    public static void uploadImage(Context context, Uri imageUri, UploadImageCallback callback) {
        if (imageUri == null) {
            Toast.makeText(context, "No image selected!", Toast.LENGTH_SHORT).show();
            if (callback != null) callback.onFailure(new Exception("No image selected"));
            return;
        }
        FirebaseStorage storage = FirebaseStorage.getInstance();
        String fileName = "treatments/" + System.currentTimeMillis() + ".jpg";
        StorageReference storageRef = storage.getReference().child(fileName);
        storageRef.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    // Optionally set the download URL in FirebaseServices:
                    FirebaseServices.getInstance().setSelectedImageURL(uri);
                    if (callback != null) callback.onSuccess(uri.toString());
                }))
                .addOnFailureListener(e -> {
                    Toast.makeText(context, "Upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    if (callback != null) callback.onFailure(e);
                });
    }

    public interface UploadImageCallback {
        void onSuccess(String downloadUrl);
        void onFailure(Exception e);
    }
}
