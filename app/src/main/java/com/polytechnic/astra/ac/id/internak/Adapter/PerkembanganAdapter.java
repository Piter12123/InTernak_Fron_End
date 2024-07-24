package com.polytechnic.astra.ac.id.internak.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.polytechnic.astra.ac.id.internak.API.VO.HewanSakitVO;
import com.polytechnic.astra.ac.id.internak.API.VO.KandangVO;
import com.polytechnic.astra.ac.id.internak.API.VO.PerkembanganVO;
import com.polytechnic.astra.ac.id.internak.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class PerkembanganAdapter extends RecyclerView.Adapter<PerkembanganAdapter.PerkembanganViewHolder>{
    private List<PerkembanganVO> perkembanganList;
    private Context context;
    public interface OnKandangClickListener {
        void onEditKandangClick(KandangVO kandang);
        void onViewKandangClick(KandangVO kandang);
    }

    public PerkembanganAdapter(Context context, List<PerkembanganVO> perkembanganList) {
        this.context = context;
        this.perkembanganList = perkembanganList;
    }

    @NonNull
    @Override
    public PerkembanganAdapter.PerkembanganViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_perkembangan, parent, false);
        return new PerkembanganAdapter.PerkembanganViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PerkembanganAdapter.PerkembanganViewHolder holder, int position) {
        PerkembanganVO perkembangan = perkembanganList.get(position);

        // Format the date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String formattedDate = dateFormat.format(perkembangan.getDtpTimestamp());

        holder.tanggalperkembangan.setText(formattedDate);
        holder.hewanid.setText(perkembangan.getTmoId());
        holder.keluhanid.setText(perkembangan.getDtpDeskripsi());
    }


    @Override
    public int getItemCount() {
        return perkembanganList.size();
    }

    public void filterList(List<PerkembanganVO> filteredList) {
        perkembanganList = filteredList;
        notifyDataSetChanged();
    }
    public static class PerkembanganViewHolder extends RecyclerView.ViewHolder {
        TextView tanggalperkembangan, hewanid, keluhanid;
        CardView cardView;
        ImageView titikTiga;
        Button Hewanview;

        public PerkembanganViewHolder(@NonNull View itemView) {
            super(itemView);
            tanggalperkembangan = itemView.findViewById(R.id.timeid);
            hewanid = itemView.findViewById(R.id.hewanid);
            keluhanid = itemView.findViewById(R.id.keluhanid);
            cardView = itemView.findViewById(R.id.cardView);
            titikTiga = itemView.findViewById(R.id.icon_titik_tiga);
            Hewanview = itemView.findViewById(R.id.btnView);
        }
    }
}
