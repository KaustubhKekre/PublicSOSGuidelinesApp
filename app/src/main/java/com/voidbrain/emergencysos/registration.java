package com.voidbrain.emergencysos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class registration extends AppCompatActivity {
    private EditText pnumber;
    private Button button;
    private FirebaseAuth mAuth;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        pnumber=(EditText)findViewById(R.id.phonenumber);

        button=(Button)findViewById(R.id.button);
        mAuth=FirebaseAuth.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone=pnumber.getText().toString();
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        phone,        // Phone number to verify
                        60,                 // Timeout duration
                        TimeUnit.SECONDS,   // Unit of timeout
                        registration.this,               // Activity (for callback binding)
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted( PhoneAuthCredential phoneAuthCredential) {

                                signInWithPhoneAuthCredential(phoneAuthCredential);

                            }

                            @Override
                            public void onVerificationFailed(FirebaseException e) {
                                Toast.makeText(registration.this,"ERROR VERIFICATION",Toast.LENGTH_LONG).show();

                            }
                            @Override
                            public void onCodeSent(String verificationId,
                                                   PhoneAuthProvider.ForceResendingToken token) {

                                // Save verification ID and resending token so we can use them later
                                mVerificationId = verificationId;
                                mResendToken = token;

                                button.setVisibility(View.GONE);
                                Toast.makeText(registration.this,"Please wait while code is being verified..",Toast.LENGTH_LONG).show();

                                // ...
                            }
                        });

            };        // OnVerificationStateChangedCallbacks

        });
       /* String verification_code=vcode.getText().toString();
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(mVerificationId,verification_code);
        signInWithPhoneAuthCredential(credential);
*/
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information


                            FirebaseUser user = task.getResult().getUser();
                            Intent back=new Intent(registration.this,MainActivity.class);
                            startActivity(back);
                            finish();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            Toast.makeText(registration.this,"ERROR LOGGING IN",Toast.LENGTH_LONG).show();
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }
}