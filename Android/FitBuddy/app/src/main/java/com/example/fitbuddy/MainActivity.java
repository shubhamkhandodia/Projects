package com.example.fitbuddy;

import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {

    protected static final int RC_SIGN_IN = 1;
    SignInButton googlesigninbutton;
    GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private ImageView frontwallpaper;
    protected String client_id = "541689175052-fl96h0nd8r1cdj20gfnomd4pu4j1o24g.apps.googleusercontent.com";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frontwallpaper = findViewById(R.id.imageView);
        googlesigninbutton = findViewById(R.id.sign_in_button);

        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT+6"));
        int hour = cal.get(Calendar.HOUR_OF_DAY);

        if(hour<=16 && hour>=4)
        {
            Log.w("Status of the day","It's morning so you got this image");
            frontwallpaper.setImageResource(R.drawable.good_morning_img);
        }
        else
        {
            Log.w("Status of the day","It's night so you got this image");
            frontwallpaper.setImageResource(R.drawable.good_night_img);
        }

        googlesigninbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                switch (view.getId())
                {
                    case R.id.sign_in_button:
                        signintask();
                        break;
                    // ...
                }

            }
        });

    }

    public void signintask()
    {
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentfirebaseUser = mAuth.getCurrentUser();

        if(currentfirebaseUser!=null)
        {
            openAccount();
        }
        else
        {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(client_id)
                    .requestEmail()
                    .build();
            // Build a GoogleSignInClient with the options specified by gso.
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
            signIn();
        }


    }

    protected void signIn()
    {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    protected void openAccount()
    {
        Intent intent = new Intent(this,UserActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("TAG", "HERE GOOGLE SIGN IN IS SUCCESSFUL_____!!!!!!" + account.getDisplayName());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e);
                // ...
            }

        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "FIREBASE SIGN IN : success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d("TAG", "HERE IS THE FIREBASE ACCOUNT DETAIL : "+user.getDisplayName());
                            openAccount();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "FIREBASE SIGN IN : failure", task.getException());
                            Snackbar.make(findViewById(android.R.id.content).getRootView(), "Authentication Failed.", Snackbar.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });
    }

}