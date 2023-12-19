package com.example.shop.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shop.Activities.ForgetPassActivity;
import com.example.shop.Activities.HomeActivity;
import com.example.shop.Dao.DaoUser;
import com.example.shop.Model.User;
import com.example.shop.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {

    TextView btnSignUp;
    EditText edtName,edtEmail,edtPass,edtRePass;
    ProgressBar progressBar;

    FirebaseAuth auth;
    DaoUser daoUser;

    public SignUpFragment() {
        // Required empty public constructor
    }

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData(view);

        btnSignUp.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPass.getText().toString();



                if (edtNotEmpty()){
                    progressBar.setVisibility(View.VISIBLE);
                    auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user = auth.getCurrentUser();

                            user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    User user1 = new User(name,email);
                                    daoUser.add(user1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {

                                            progressBar.setVisibility(View.GONE);
                                            Toast.makeText(requireActivity().getApplicationContext(), "SignedUp Successfully\nplease verify your Email.", Toast.LENGTH_SHORT).show();
                                            edtName.setText("");
                                            edtEmail.setText("");
                                            edtPass.setText("");
                                            edtRePass.setText("");

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            progressBar.setVisibility(View.GONE);
                                            Toast.makeText(requireActivity().getApplicationContext(), "Something happen "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(requireActivity().getApplicationContext(), "Error : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("Error SignUp",e.getMessage());


                        }
                    });
                }

            }
        });

    }

    void initData(View view){
        btnSignUp = view.findViewById(R.id.btnSignUp);
        edtName = view.findViewById(R.id.edtNameSignUp);
        edtEmail = view.findViewById(R.id.edtEmailSignUp);
        edtPass = view.findViewById(R.id.edtPassSignUp);
        edtRePass = view.findViewById(R.id.edtRePassSignUp);
        progressBar = view.findViewById(R.id.progressBarSignUp);

        auth = FirebaseAuth.getInstance();
        daoUser = new DaoUser();
    }

    private boolean edtNotEmpty(){
        if (!edtName.getText().toString().isEmpty()){

            if (!edtEmail.getText().toString().isEmpty()){

                if (Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText().toString()).matches()){

                    if (!edtPass.getText().toString().isEmpty()) {

                        if (edtPass.getText().toString().length() >= 6){

                            if (!edtRePass.getText().toString().isEmpty()) {

                                if (edtRePass.getText().toString().matches(edtPass.getText().toString())) {

                                    return true;

                                } else {
                                    Toast.makeText(requireActivity().getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                                    return false;
                                }


                            } else {
                                Toast.makeText(requireActivity().getApplicationContext(), "Re-enter password", Toast.LENGTH_SHORT).show();
                                return false;
                            }

                    } else{
                            Toast.makeText(requireActivity().getApplicationContext(), "Password should at least 6 numbers", Toast.LENGTH_LONG).show();
                            return false;
                        }


                    }else{
                        Toast.makeText(requireActivity().getApplicationContext(), "Enter password", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                }else{
                    Toast.makeText(requireActivity().getApplicationContext(), "Invalid email", Toast.LENGTH_SHORT).show();
                    return false;
                }

            }else{
                Toast.makeText(requireActivity().getApplicationContext(), "Enter email", Toast.LENGTH_SHORT).show();
                return false;
            }

        }else{
            Toast.makeText(requireActivity().getApplicationContext(), "Enter full name", Toast.LENGTH_SHORT).show();
            return false;
        }



    }


}