package com.sametsisman.kitaptakas.view.giriskayit;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sametsisman.kitaptakas.databinding.FragmentKayitBinding;

public class KayitFragment extends Fragment {
    private FragmentKayitBinding binding;
    private FirebaseAuth auth;
    private String kullaniciAdi;
    private String sifre;

    public KayitFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentKayitBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonKayitTamam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kayitTamam(view);
            }
        });
    }

    public void kayitTamam(View view){
        kullaniciAdi = binding.editTextKullaniciadi.getText().toString();
        sifre = binding.editTextPassword.getText().toString();
        if(kullaniciAdi != null || sifre != null ){
            auth.createUserWithEmailAndPassword(kullaniciAdi,sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    NavDirections action = KayitFragmentDirections.actionKayitFragmentToGirisFragment();
                    Navigation.findNavController(view).navigate(action);
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
}