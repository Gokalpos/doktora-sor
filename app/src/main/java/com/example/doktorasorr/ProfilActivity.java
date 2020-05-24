package com.example.doktorasorr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PlayGamesAuthCredential;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfilActivity extends AppCompatActivity {

    private  String alinanKullaniciId;
    private CircleImageView kullaniciprofilresmi;
    private TextView kullaniciprofiladi,kullaniciprofildurumu;
    private Button mesajgondermetalebibutonu;
    //Firebase
    private DatabaseReference KullaniciYolu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        alinanKullaniciId=getIntent().getExtras().get("tıklanan_kullanici_id_goster").toString();//alındı


        kullaniciprofilresmi = findViewById(R.id.profil_resmi_ziyaret);
        kullaniciprofiladi = findViewById(R.id.kullanici_adı_ziyaret);
        kullaniciprofildurumu = findViewById(R.id.kullanici_profil_durumu_ziyaret);


        KullaniciYolu = FirebaseDatabase.getInstance().getReference().child("Kullanicilar");


        kullaniciBilgisiAl();

    }

    private void kullaniciBilgisiAl() {

        KullaniciYolu.child(alinanKullaniciId).addValueEventListener(new ValueEventListener() {//veri cekme
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {//Veritemsili
               if((dataSnapshot.exists()) && (dataSnapshot.hasChild("resim"))){
                   String kullaniciResmi = dataSnapshot.child("resim").getValue().toString();
                   String kullaniciAdi = dataSnapshot.child("ad").getValue().toString();
                   String kullaniciDurumu = dataSnapshot.child("durum").getValue().toString();

                   Picasso.get().load(kullaniciResmi).placeholder(R.drawable.profil).into(kullaniciprofilresmi);
                   kullaniciprofiladi.setText(kullaniciAdi);
                   kullaniciprofildurumu.setText(kullaniciDurumu);




               }
               else{
                   String kullaniciAdi = dataSnapshot.child("ad").getValue().toString();
                   String kullaniciDurumu = dataSnapshot.child("durum").getValue().toString();

                   kullaniciprofiladi.setText(kullaniciAdi);
                   kullaniciprofildurumu.setText(kullaniciDurumu);


               }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {




            }
        });

    }






}
