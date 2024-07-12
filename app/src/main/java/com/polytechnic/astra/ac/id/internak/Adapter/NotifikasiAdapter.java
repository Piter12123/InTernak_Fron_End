package com.polytechnic.astra.ac.id.internak.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polytechnic.astra.ac.id.internak.API.VO.NotifikasiVO;
import com.polytechnic.astra.ac.id.internak.R;

import java.util.List;

public class NotifikasiAdapter extends RecyclerView.Adapter<NotifikasiAdapter.ViewHolder> {

    private List<NotifikasiVO> notifikasiList;
    public NotifikasiAdapter(List<NotifikasiVO> notifikasiList) {
        this.notifikasiList = notifikasiList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_notifikasi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotifikasiVO notifikasi = notifikasiList.get(position);
        holder.notificationTitle.setText(notifikasi.getNtfJudul());
        holder.notificationMessage.setText(notifikasi.getNtfDeskripsi());

        // Set visibility or icons based on notifikasi properties
        holder.iconNotification.setVisibility(View.VISIBLE);
        holder.iconNotification2.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return notifikasiList != null ? notifikasiList.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconNotification;
        ImageView iconNotification2;
        TextView notificationTitle;
        TextView notificationMessage;
        ImageView iconMore;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconNotification = itemView.findViewById(R.id.icon_notification);
            iconNotification2 = itemView.findViewById(R.id.icon_notification2);
            notificationTitle = itemView.findViewById(R.id.notification_title);
            notificationMessage = itemView.findViewById(R.id.notification_message);
            iconMore = itemView.findViewById(R.id.icon_more);
        }
    }
}
