package com.alivelife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthCredential;

import java.util.Collections;

public class SignInActivity extends AppCompatActivity {
    private String etiqueta = "Estoy en: ";
    DriveServiceHelper driveServiceHelper;

    //###   [     Google OAuth2 variables     ]
    private static final int RC_SIGN_IN = 1;
    private GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        addListenerOnButton();
        if(GoogleSignIn.getLastSignedInAccount(this) != null ) goToSecondActivity();    //###   Log in directly if already logged
        //###   [     Google OAuth2: request users ID and basic profile information     ]
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestScopes(new Scope(DriveScopes.DRIVE_FILE))
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);        //### Google Sign In Client.
    }
    //###   [     onStart method:     ]
    @Override
    protected void onStart(){
        super.onStart();
        //GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        //updateUI(account);
    }
    @Override
    protected void onResume(){
        super.onResume();
        if(GoogleSignIn.getLastSignedInAccount(this) != null) goToSecondActivity();
        //GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        //updateUI(account);
    }
    //###   [     Sign In listener button:  Send to Gmail login when triggered    ]
    public void addListenerOnButton() {
        ImageButton signinBttn = findViewById(R.id.sign_in_button);
        signinBttn.setOnClickListener(v -> {
            switch (v.getId()) {
                case R.id.sign_in_button:
                    signIn(v);      //###       Call signIn() method after pressing the button.
                    break;
            }
        });
    }
    public void signIn(View v) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);       //###     Request code

        System.out.println("*************************************");
        System.out.println("***** "+GoogleSignIn.getLastSignedInAccount(this));

        if(GoogleSignIn.getLastSignedInAccount(this) != null) {
            driveCode();
            goToSecondActivity();
        }
        //goToSecondActivity(); //Maria change it *******************************************************************
    }

    //###   [     Go to the second activity if Gmail login was successful    ]
    public void goToSecondActivity(){
        //uploadJPGFile();
        Intent i = new Intent(SignInActivity.this, UserView.class);
        startActivity(i);

        /* System.out.println("Estoy en la segunda actividad *******************************************************************");
        System.out.println(String.valueOf(GoogleSignIn.getLastSignedInAccount(this)));
        Log.d(etiqueta, String.valueOf(GoogleSignIn.getLastSignedInAccount(this)));
        System.out.println(String.valueOf(GoogleSignIn.getLastSignedInAccount(this).getId()));
        System.out.println(String.valueOf(GoogleSignIn.getLastSignedInAccount(this).getEmail()));
        System.out.println(String.valueOf(GoogleSignIn.getLastSignedInAccount(this).getAccount()));
        System.out.println(String.valueOf(GoogleSignIn.getLastSignedInAccount(this).getIdToken()));
         */
    }

    private void driveCode(){
        GoogleAccountCredential credential = GoogleAccountCredential.
                usingOAuth2(SignInActivity.this, Collections.singleton(DriveScopes.DRIVE_FILE)).
                setSelectedAccount(GoogleSignIn.getLastSignedInAccount(this).getAccount());

        Drive googleDriveService = new Drive.Builder(
                AndroidHttp.newCompatibleTransport(),
                new GsonFactory(),
                credential)
                .setApplicationName("AliveLife")
                .build();

        driveServiceHelper = new DriveServiceHelper(googleDriveService);
    }

    public void uploadJPGFile(){
        String filePath = "/storage/self/primary/DCIM/Camera/IMG_20201211_120241.jpg";
        driveServiceHelper.createFileImg(filePath).addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String s) {
                Toast.makeText(getApplicationContext(),"Uploaded successfully",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Check your API key",Toast.LENGTH_LONG).show();
            }
        });
    }
}