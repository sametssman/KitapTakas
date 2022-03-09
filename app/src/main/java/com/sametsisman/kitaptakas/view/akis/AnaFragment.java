package com.sametsisman.kitaptakas.view.akis;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.sametsisman.kitaptakas.R;
import com.sametsisman.kitaptakas.databinding.FragmentAnaBinding;
import com.sametsisman.kitaptakas.view.giriskayit.MainActivity;

import java.util.ArrayList;
import java.util.Map;

import adapter.KitapAdapter;
import model.Ilan;

public class AnaFragment extends Fragment {
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private ArrayList<Ilan> ilanArrayList;
    KitapAdapter kitapAdapter;
    FragmentAnaBinding binding;

    public AnaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        getData();


        ilanArrayList = new ArrayList<>();

        kitapAdapter = new KitapAdapter(ilanArrayList);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAnaBinding.inflate(getLayoutInflater());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(kitapAdapter);
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add_post){

        }else if(item.getItemId() == R.id.cikis){
            auth.signOut();

            Intent intentToGiris = new Intent(getActivity(), MainActivity.class);
            startActivity(intentToGiris);
        }
        return super.onOptionsItemSelected(item);
    }



    private void getData(){
        firebaseFirestore.collection("Posts").orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Toast.makeText(getContext(), error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
                if(value != null){

                    for(DocumentSnapshot snapshot : value.getDocuments()){
                        Map<String,Object> data = snapshot.getData();

                        String kitapIsmı = (String) data.get("kitapIsmi");
                        String yazarIsmı = (String) data.get("yazarIsmi");
                        String url = (String) data.get("downloadurl");
                        String optional = (String) data.get("optional");

                        Ilan ilan = new Ilan(kitapIsmı,yazarIsmı,optional,url);
                        ilanArrayList.add(ilan);
                    }

                    kitapAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}