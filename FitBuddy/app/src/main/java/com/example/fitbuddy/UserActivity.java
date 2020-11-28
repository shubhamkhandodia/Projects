package com.example.fitbuddy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.fitbuddy.ui.main.SectionsPagerAdapter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import javax.net.ssl.SSLEngineResult;

public class UserActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_ACTIVITY_RECOGNITION = 1;
    private FloatingActionButton signoutbutton;
    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInAccount myaccount;
    Fragment bmi,nutrition,exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        signoutbutton = findViewById(R.id.signoutbutton);

        signoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    // ...
                    case R.id.signoutbutton:
                        signOut();
                        break;
                    // ...
                }

            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        myaccount = GoogleSignIn.getLastSignedInAccount(getApplicationContext());

        // Permission is not granted so asking for permission

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACTIVITY_RECOGNITION , Manifest.permission.ACCESS_FINE_LOCATION , Manifest.permission.BODY_SENSORS},
                    MY_PERMISSIONS_REQUEST_ACTIVITY_RECOGNITION);

        }


        ViewPager2 viewPager2 = findViewById(R.id.view_pager);
        viewPager2.setAdapter(new SectionsPagerAdapter(this,this));

        TabLayout tabLayout = findViewById(R.id.tabs);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch(position)
                {
                    case 0:{
                        tab.setText("BMI calculator");
                        tab.setIcon(R.drawable.bmi);
                        bmi = new BmiFragment();
                        break;
                    }
                    case 1:{
                        tab.setText("Nutrition");
                        tab.setIcon(R.drawable.diet);
                        exercise = new exerciseFragment();
                        break;
                    }
                    case 2:{
                        tab.setText("Workout");
                        tab.setIcon(R.drawable.stretching);
                        nutrition = new nutritionFragment();
                        break;
                    }
                }
            }
        }
        );
        tabLayoutMediator.attach();
    }

    private void signOut()
    {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(UserActivity.this,"Sign out successful",Toast.LENGTH_LONG).show();

                        startActivity(new Intent(UserActivity.this,MainActivity.class));
                    }
                });
    }


}