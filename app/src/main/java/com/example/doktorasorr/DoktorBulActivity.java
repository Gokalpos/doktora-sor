package com.example.doktorasorr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doktorasorr.Model.Kisiler;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class DoktorBulActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView DoktorBulRecyclerListesi;
    private DatabaseReference kullaniciYolu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doktor_bul);



        DoktorBulRecyclerListesi = findViewById(R.id.doktor_bul_recyler_listesi);
        DoktorBulRecyclerListesi.setLayoutManager(new LinearLayoutManager(this));

        mToolbar=findViewById(R.id.doktor_bul_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Doktor Bul");

        //firebase tanımlama
        kullaniciYolu = FirebaseDatabase.getInstance().getReference().child("Kullanicilar");



    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Kisiler> secenekler=
                new FirebaseRecyclerOptions.Builder<Kisiler>()
                .setQuery(kullaniciYolu,Kisiler.class)
                .build();

        FirebaseRecyclerAdapter<Kisiler,DoktorBulViewHolder> adapter = new FirebaseRecyclerAdapter<Kisiler, DoktorBulViewHolder>(secenekler) {
            @Override
            protected void onBindViewHolder(@NonNull DoktorBulViewHolder holder, int position, @NonNull Kisiler model) {
                holder.kullaniciAdi.setText(model.getAd());
                holder.kullaniciDurumu.setText(model.getDurum());
                Picasso.get().load(model.getResim()).into(holder.profilResmi);

            }

            @NonNull
            @Override
            public DoktorBulViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.kullanici_gosterme_layout,viewGroup,false);
                DoktorBulViewHolder viewHolder = new DoktorBulViewHolder(view);
                return viewHolder;

            }
        };

        DoktorBulRecyclerListesi.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.startListening();


    }

    public static class DoktorBulViewHolder extends RecyclerView.ViewHolder{

        TextView kullaniciAdi,kullaniciDurumu;
        CircleImageView profilResmi;


        public DoktorBulViewHolder(@NonNull View itemView) {
            super(itemView);
            kullaniciAdi = itemView.findViewById(R.id.kullanici_profil_adi);
            kullaniciDurumu = itemView.findViewById(R.id.kullanici_durumu);
            profilResmi = itemView.findViewById(R.id.kullanicilar_profil_resmi);

        }
    }
}
