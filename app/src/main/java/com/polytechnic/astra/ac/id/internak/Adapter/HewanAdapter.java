package com.polytechnic.astra.ac.id.internak.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;
import com.polytechnic.astra.ac.id.internak.R;

import java.util.List;

public class HewanAdapter extends RecyclerView.Adapter<HewanAdapter.HewanViewHolder> {
    private List<HewanVO> hewanList;

    public HewanAdapter(List<HewanVO> hewanList) {
        this.hewanList = hewanList;
    }

    @NonNull
    @Override
    public HewanAdapter.HewanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_hewan, parent, false);
        return new HewanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HewanViewHolder holder, int position) {
        HewanVO hewan = hewanList.get(position);
        holder.idhewan.setText("ID: " + hewan.getHwnId());
        holder.namahewan.setText(hewan.getHwnNama());
        holder.status.setText(hewan.getHwnStatus());
    }

    @Override
    public int getItemCount() {
        return hewanList.size();
    }

    public void filterList(List<HewanVO> filteredList) {
        hewanList = filteredList;
        notifyDataSetChanged();
    }

    public static class HewanViewHolder extends RecyclerView.ViewHolder {
        TextView idhewan, namahewan, status;
        CardView cardView;

        public HewanViewHolder(@NonNull View itemView) {
            super(itemView);
            idhewan = itemView.findViewById(R.id.id_hewan);
            namahewan = itemView.findViewById(R.id.nama_hewanid);
            status = itemView.findViewById(R.id.status_hewanid);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
