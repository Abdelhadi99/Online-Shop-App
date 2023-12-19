package com.example.shop.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shop.Activities.ForgetPassActivity;
import com.example.shop.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ForgetPassFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForgetPassFragment extends Fragment {
    FirebaseAuth auth ;

    public ForgetPassFragment() {
        // Required empty public constructor
    }

    public static ForgetPassFragment newInstance() {
        return new ForgetPassFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forget_pass, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView btnSendCode = view.findViewById(R.id.btnSendCode);
        EditText edtEmail = view.findViewById(R.id.edtEmailForgetPass);
        auth = FirebaseAuth.getInstance();



        btnSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                if (!edtEmail.getText().toString().isEmpty()) {
                    if (Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText().toString()).matches()) {

                        auth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                requireActivity().finish();
                                Toast.makeText(requireActivity().getApplicationContext(), "Check Your Email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(requireActivity().getApplicationContext(), "Error\n"+e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });

                    } else {
                        Toast.makeText(requireActivity().getApplicationContext(), "Invalid email", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(requireActivity().getApplicationContext(), "Enter your email", Toast.LENGTH_SHORT).show();
                }




            }
        });

    }

    public static class ForgetPassAdapter extends FragmentStatePagerAdapter {

        public ForgetPassAdapter(@NonNull FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return ForgetPassFragment.newInstance();
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Return the title for each tab
            return "Forget Password";
        }
    }


}