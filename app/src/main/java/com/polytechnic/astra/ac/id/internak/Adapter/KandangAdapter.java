package com.polytechnic.astra.ac.id.internak.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;
import com.polytechnic.astra.ac.id.internak.API.VO.KandangVO;
import com.polytechnic.astra.ac.id.internak.Fragment.HewanFragment;
import com.polytechnic.astra.ac.id.internak.Fragment.KandangFragment;
import com.polytechnic.astra.ac.id.internak.Fragment.RegisterFragment;
import com.polytechnic.astra.ac.id.internak.R;

import java.util.List;

public class KandangAdapter extends RecyclerView.Adapter<KandangAdapter.KandangViewHolder> {

    private List<KandangVO> kandangList;
    private Button lihatkdg;
    private Context context;
    private KandangAdapter.OnKandangClickListener listener, viewKandang;

    private Fragment fragment;

    public interface OnKandangClickListener {
    }

    public KandangAdapter(Fragment fragment, Context context, List<KandangVO> kandangList, KandangAdapter.OnKandangClickListener listener, KandangAdapter.OnKandangClickListener viewKandang) {
        this.context = context;
        this.kandangList = kandangList;
        this.listener = listener;
        this.viewKandang = viewKandang;
        this.fragment = fragment;
    }


    public KandangAdapter(List<KandangVO> kandangList) {
        this.kandangList = kandangList;
    }

    @NonNull
    @Override
    public KandangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_kandang, parent, false);
        lihatkdg = view.findViewById(R.id.btnView);

        lihatkdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment(new KandangFragment());
            }
        });

        return new KandangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KandangViewHolder holder, int position) {
        KandangVO kandang = kandangList.get(position);
        holder.namaKandang.setText(kandang.getKdgNama());
        holder.lokasiKandang.setText(kandang.getKdgAlamat());
        holder.kapasitasKandang.setText(String.valueOf(kandang.getKdgKapasitas()) + " Ekor");
        holder.suhuKandang.setText(String.valueOf(kandang.getKdgSuhu()) + "°C");
    }

    @Override
    public int getItemCount() {
        return kandangList.size();
    }

    public void filterList(List<KandangVO> filteredList) {
        kandangList = filteredList;
        notifyDataSetChanged();
    }
    public static class KandangViewHolder extends RecyclerView.ViewHolder {

        TextView namaKandang, lokasiKandang, kapasitasKandang, suhuKandang;
        CardView cardView;

        public KandangViewHolder(@NonNull View itemView) {
            super(itemView);
            namaKandang = itemView.findViewById(R.id.namakandangid);
            lokasiKandang = itemView.findViewById(R.id.lokasikandangid);
            kapasitasKandang = itemView.findViewById(R.id.kapasitaskandangid);
            suhuKandang = itemView.findViewById(R.id.suhukandangid);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
    private void navigateToFragment(Fragment targetFragment) {
        FragmentManager fragmentManager = fragment.getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_login, targetFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
