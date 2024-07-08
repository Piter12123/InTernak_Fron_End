package com.polytechnic.astra.ac.id.internak.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;
import com.polytechnic.astra.ac.id.internak.R;
import com.polytechnic.astra.ac.id.internak.ViewModel.HewanViewModel;

import java.util.List;

public class HewanAdapter extends RecyclerView.Adapter<HewanAdapter.HewanViewHolder> {
    private List<HewanVO> hewanList;
    private Context context;
    private OnHewanClickListener listener, viewHewan;

    public interface OnHewanClickListener {
        void onEditHewanClick(HewanVO hewan);
        void onViewHewanClick(HewanVO hewan);
    }

    public HewanAdapter(Context context, List<HewanVO> hewanList, OnHewanClickListener listener, OnHewanClickListener viewHewan) {
        this.context = context;
        this.hewanList = hewanList;
        this.listener = listener;
        this.viewHewan = viewHewan;
    }

    @NonNull
    @Override
    public HewanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_hewan, parent, false);
        return new HewanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HewanViewHolder holder, int position) {
        HewanVO hewan = hewanList.get(position);
        holder.idhewan.setText("ID: " + hewan.getHwnId());
        holder.namahewan.setText(hewan.getHwnNama());
        holder.status.setText(hewan.getHwnStatus());

        holder.titikTiga.setOnClickListener(v -> showPopupMenu(holder.titikTiga, position));
        holder.Hewan.setOnClickListener(v -> viewHewan.onViewHewanClick(hewan));
    }

    private void showPopupMenu(View view, int position) {
        PopupMenu popup = new PopupMenu(context, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_options, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(position));
        popup.show();
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
        private int position;

        public MyMenuItemClickListener(int position) {
            this.position = position;
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int itemId = item.getItemId();
            HewanVO hewan = hewanList.get(position);
            if (itemId == R.id.action_edit) {
                listener.onEditHewanClick(hewan);
                return true;
            } else if (itemId == R.id.action_delete) {
                MutableLiveData<Boolean> deleteResult = new MutableLiveData<>();
                deleteResult.observe((LifecycleOwner) context, isDeleted -> {
                    if (isDeleted) {
                        hewanList.remove(position);
                        notifyItemRemoved(position);
                        Toast.makeText(context, "Hewan berhasil dihapus", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Gagal menghapus hewan", Toast.LENGTH_SHORT).show();
                    }
                });
                HewanViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(HewanViewModel.class);
                viewModel.deleteHewan(hewan.getHwnId(), deleteResult);
                return true;
            } else {
                return false;
            }
        }
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
        ImageView titikTiga;
        Button Hewan;

        public HewanViewHolder(@NonNull View itemView) {
            super(itemView);
            idhewan = itemView.findViewById(R.id.id_hewan);
            namahewan = itemView.findViewById(R.id.nama_hewanid);
            status = itemView.findViewById(R.id.status_hewanid);
            cardView = itemView.findViewById(R.id.cardView);
            titikTiga = itemView.findViewById(R.id.titik_tiga);
            Hewan = itemView.findViewById(R.id.btnView);
        }
    }
}
