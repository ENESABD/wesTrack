package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class signinout extends AppCompatActivity {
    //public DocumentReference mDoc = FirebaseFirestore.getInstance().document("users/totalCount");
    DocumentReference docRef = FirebaseFirestore.getInstance().collection("users").document("totalCount");
    Boolean isCheckedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        final String message = intent.getStringExtra(MapsActivity.EXTRA_MESSAGE);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override

            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    TextView count = findViewById(R.id.count);
                    count.setText(Integer.toString(document.getLong(message).intValue()));
                    System.out.println(document.getLong(message).intValue());

                    if (document.exists()) {
                        System.out.println( "DocumentSnapshot data: " + document.getData());
                    } else {
                        System.out.println("No such document");
                    }
                } else {
                    System.out.println("get failed with " + task.getException());
                }
            }
        });


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signinout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final Button button = (Button) findViewById(R.id.check);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCheckedIn) {
                    button.setText("Check In");
                    docRef.update(message, FieldValue.increment(-1));
                    isCheckedIn = false;
                }
                else {
                    button.setText("Check Out");
                    docRef.update(message, FieldValue.increment(1));
                    isCheckedIn = true;
                }


                docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot snapshot,
                                        @Nullable FirebaseFirestoreException e) {
                        TextView count = findViewById(R.id.count);
                        count.setText(Integer.toString(snapshot.getLong(message).intValue()));
                        if (e != null) {
                            System.out.println(("Listen failed." + e));
                            return;
                        }

                        if (snapshot != null && snapshot.exists()) {
                            System.out.println("Current data: " + snapshot.getData());
                        } else {
                            System.out.println("Current data: null");
                        }
                    }
                });


            }
        });

    }
}