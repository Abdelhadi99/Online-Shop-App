package com.example.shop.Fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shop.Dao.DaoProfile;
import com.example.shop.Model.Profile;
import com.example.shop.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    private static final int PICK_IMAGE = 1;
    ImageView imageView;
    TextView btnChange;
    Button btnSave;
    EditText edtName, edtEmail, edtPhoneNumber, edtAddress;
    Uri selectedImage;
    String strImgUri = "";

    FirebaseAuth auth;
    FirebaseUser user;
    String userID;
    DaoProfile daoProfile;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData(view);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String phoneNum = edtPhoneNumber.getText().toString();
                String address = edtAddress.getText().toString();


                if (!edtName.getText().toString().isEmpty()) {

                    if (!edtEmail.getText().toString().isEmpty()) {

                        if (!edtPhoneNumber.getText().toString().isEmpty()) {

                            if (!edtAddress.getText().toString().isEmpty()) {


                                if (selectedImage != null) {


                                    StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("profile_images");
                                    StorageReference imgfilePath = storageRef.child(selectedImage.getLastPathSegment());

                                    imgfilePath.putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                            imgfilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {

                                                    strImgUri = uri.toString();


                                                    Profile profile = new Profile(userID, name, email, phoneNum, address, strImgUri);

                                                    daoProfile.add(profile).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {
                                                            Toast.makeText(requireActivity().getApplicationContext(), "Saved successfully", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(requireActivity().getApplicationContext(), "1. ERROR\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                        }
                                                    });

                                                }
                                            });

                                        }
                                    });


                                } else {

                                    Profile profile = new Profile(userID, name, email, phoneNum, address, strImgUri);

                                    daoProfile.add(profile).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(requireActivity().getApplicationContext(), "Saved successfully\nwithout change on the picture", Toast.LENGTH_LONG).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(requireActivity().getApplicationContext(), "2. ERROR\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }


                            } else {
                                Toast.makeText(requireActivity().getApplicationContext(), "Enter your Address", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(requireActivity().getApplicationContext(), "Enter your Phone number", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(requireActivity().getApplicationContext(), "Enter your Email", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(requireActivity().getApplicationContext(), "Enter your Name", Toast.LENGTH_SHORT).show();
                }


            }
        });

        setProfileData();
    }

    private void initData(View view) {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userID = user.getUid();
        daoProfile = new DaoProfile();

        imageView = view.findViewById(R.id.imageView);
        btnChange = view.findViewById(R.id.btnChange);

        btnSave = view.findViewById(R.id.btnSaveProfile);
        edtName = view.findViewById(R.id.edtNameProfile);
        edtEmail = view.findViewById(R.id.edtEmailProfile);
        edtPhoneNumber = view.findViewById(R.id.edtPhoneNumberProfile);
        edtAddress = view.findViewById(R.id.edtAddressProfile);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            selectedImage = data.getData();

            try {
                InputStream inputStream = requireActivity().getContentResolver().openInputStream(selectedImage);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setProfileData() {
        daoProfile.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    Profile profile = data.getValue(Profile.class);
                    if (profile != null) {
                        if (profile.getUserID().equals(userID)) {

                            edtName.setText(profile.getName());
                            edtEmail.setText(profile.getEmail());
                            edtPhoneNumber.setText(profile.getPhoneNum());
                            edtAddress.setText(profile.getAddress());

                            if (isAdded()){
                                if (!profile.getStrImgUri().isEmpty()) {

                                    Glide.with(requireActivity().getApplicationContext()).load(profile.getStrImgUri()).into(imageView);

                                }
                            }


                            break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}