package com.sametsisman.kitaptakas.view.akis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;
import com.sametsisman.kitaptakas.R;
import com.sametsisman.kitaptakas.databinding.ActivityFeedBinding;

public class FeedActivity extends AppCompatActivity {
    private ActivityFeedBinding binding;
    private FragmentTransaction transaction;
    private AnaFragment anaFragment;
    private PostFragment postFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        anaFragment = new AnaFragment();
        postFragment = new PostFragment();

        fragmentAyarla(anaFragment);

        binding.bottomView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.home){
                    fragmentAyarla(anaFragment);
                }else if (item.getItemId() == R.id.add){
                    fragmentAyarla(postFragment);
                }else if (item.getItemId() == R.id.message){

                }
                return false;
            }
        });
    }


    public void fragmentAyarla(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }

}