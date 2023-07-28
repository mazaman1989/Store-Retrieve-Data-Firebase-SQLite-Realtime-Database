package com.zamansoftwaresolutions.datamangeusingfirebasesqllite.Firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.zamansoftwaresolutions.datamangeusingfirebasesqllite.MainActivity;
import com.zamansoftwaresolutions.datamangeusingfirebasesqllite.R;

public class DetailActivity extends AppCompatActivity {
    TextView detailAddress, detailName, detailEmail,detailMobile;
    ImageView detailImage;
    FloatingActionButton deleteButton, editButton;
    String key = "";
    String imageUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailName = findViewById(R.id.detailName);
        detailImage = findViewById(R.id.detailImage);
        detailAddress = findViewById(R.id.detailAddress);
        detailEmail = findViewById(R.id.detailEmail);
        detailMobile = findViewById(R.id.detailMobile);


        deleteButton = findViewById(R.id.deleteButton);
        editButton = findViewById(R.id.editButton);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailName.setText(bundle.getString("Name"));
            detailEmail.setText(bundle.getString("Email"));
            detailMobile.setText(bundle.getString("Mobile"));
            detailAddress.setText(bundle.getString("Address"));
            key = bundle.getString("Key");
            imageUrl = bundle.getString("Image");
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }



        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User Registration");
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageReference = storage.getReferenceFromUrl(imageUrl);
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(key).removeValue();
                        Toast.makeText(DetailActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                });
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, UpdateActivity.class)
                        .putExtra("Name",detailName.getText().toString())
                        .putExtra("Email",detailEmail.getText().toString())
                        .putExtra("Image",imageUrl)
                        .putExtra("Mobile",detailMobile.getText().toString())
                        .putExtra("Address",detailAddress.getText())
                        .putExtra("Key",key);

                startActivity(intent);

            }
        });

    }
}