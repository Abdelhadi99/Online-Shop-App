package com.example.shop.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shop.Activities.ForgetPassActivity;
import com.example.shop.Activities.HomeActivity;
import com.example.shop.Adapters.LoginSignAdapter;
import com.example.shop.Dao.DaoUser;
import com.example.shop.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    TextView btnLogin;
    EditText edtEmail,edtPass;
    ProgressBar progressBar;

    FirebaseAuth auth;
    DaoUser daoUser;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initData(view);

        TextView btnForgetPass = view.findViewById(R.id.btnForgetPass);

        btnForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ForgetPassActivity.class));
            }
        });


        TextView btnLogin = view.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String password = edtPass.getText().toString();


                if (edtNotEmpty()){
                    progressBar.setVisibility(View.VISIBLE);
                    auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {

                            FirebaseUser user = authResult.getUser();

                            if (user.isEmailVerified()){

                                progressBar.setVisibility(View.GONE);
                                startActivity(new Intent(requireActivity().getApplicationContext(),HomeActivity.class));

                            }else{
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(requireActivity().getApplicationContext(), "Verify your Email", Toast.LENGTH_SHORT).show();
                            }


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(requireActivity().getApplicationContext(), "Something Happen\n"+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

    }

    void initData(View view){
        btnLogin = view.findViewById(R.id.btnLogin);
        edtEmail = view.findViewById(R.id.edtEmailLogin);
        edtPass = view.findViewById(R.id.edtPassLogin);
        auth = FirebaseAuth.getInstance();
        daoUser = new DaoUser();
        progressBar = view.findViewById(R.id.progressBarLogin);

    }

    private boolean edtNotEmpty(){



            if (!edtEmail.getText().toString().isEmpty()){

                if (Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText().toString()).matches()){

                    if (!edtPass.getText().toString().isEmpty()) {

                        if (edtPass.getText().toString().length() >= 6){

                          return true;

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




    }

}