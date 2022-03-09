package com.sametsisman.kitaptakas.view.giriskayit;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sametsisman.kitaptakas.databinding.FragmentGirisBinding;
import com.sametsisman.kitaptakas.view.akis.FeedActivity;

public class GirisFragment extends Fragment {
    private FirebaseAuth auth;
    private FragmentGirisBinding binding;
    private String kullaniciAdi;
    private String sifre;


    public GirisFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();

        FirebaseUser user = auth.getCurrentUser();

        if(user != null){
            Intent intentToFeed = new Intent(getContext(), FeedActivity.class);
            startActivity(intentToFeed);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGirisBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                girisToKayit(view);
            }
        });

        binding.buttonGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                giris(view);
            }
        });
    }

    public void giris(View view){
        kullaniciAdi = binding.editTextKullaniciadi.getText().toString();
        sifre = binding.editTextPassword.getText().toString();
        if(kullaniciAdi != null || sifre != null ){
            auth.signInWithEmailAndPassword(kullaniciAdi,sifre).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intentToFeed = new Intent(getContext(),FeedActivity.class);
                    startActivity(intentToFeed);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }else{
            Toast.makeText(getContext(),"Kullanıcı adı veya şifre boş olamaz.",Toast.LENGTH_LONG).show();
        }

    }

    public void girisToKayit(View view){
        NavDirections action = GirisFragmentDirections.actionGirisFragmentToKayitFragment();
        Navigation.findNavController(view).navigate(action);
    }
}