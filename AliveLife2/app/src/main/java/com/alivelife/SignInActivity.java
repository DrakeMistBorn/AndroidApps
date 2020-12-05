package com.alivelife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {

    //### START [     Google OAuth2 variables     ]
    private static final int RC_SIGN_IN = 1;
    private GoogleSignInClient mGoogleSignInClient;
    //### END   [     Google OAuth2 variables     ]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        addListenerOnButton();

        //### START  [     Google OAuth2: request users ID and basic profile information     ]
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);        //### Google Sign In Client.

        //### END    [     Google OAuth2: request users ID and basic profile information     ]
    }

    //### START     [     onStart method:     ]
    @Override
    protected void onStart(){
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        //updateUI(account);
    }

    //### END       [     onStart method:     ]

    //### START     [     Sign In listener button:  Send to Gmail login when triggered    ]
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
    //### END     [     Sign In listener button:  Send to Gmail login when triggered    ]


    public void signIn(View v) {
        Intent i = new Intent(SignInActivity.this, UserView.class);
        startActivity(i);

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);       //###     Request code


    }


}