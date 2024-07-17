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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;
import com.polytechnic.astra.ac.id.internak.API.VO.KandangVO;
import com.polytechnic.astra.ac.id.internak.Fragment.HewanFragment;
import com.polytechnic.astra.ac.id.internak.Fragment.KandangFragment;
import com.polytechnic.astra.ac.id.internak.Fragment.RegisterFragment;
import com.polytechnic.astra.ac.id.internak.R;
import com.polytechnic.astra.ac.id.internak.ViewModel.HewanViewModel;
import com.polytechnic.astra.ac.id.internak.ViewModel.KandangViewModel;

import java.util.List;

public class KandangAdapter extends RecyclerView.Adapter<KandangAdapter.KandangViewHolder> {

    private List<KandangVO> kandangList;
    private Button lihatkdg;
    private Context context;
    private KandangAdapter.OnKandangClickListener listener, viewKandang;

    private Fragment fragment;

    public interface OnKandangClickListener {
        void onEditKandangClick(KandangVO kandang);
        void onViewKandangClick(KandangVO kandang);
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
        holder.lokasi.setText(kandang.getKdgAlamat());
        holder.kapasitas.setText(String.valueOf(kandang.getKdgKapasitas()) + " Ekor");
        holder.suhu.setText(String.valueOf(kandang.getKdgSuhu()) + "°C");

        holder.titikTiga.setOnClickListener(v -> showPopupMenu(holder.titikTiga, position));
        holder.Kandang.setOnClickListener(v -> viewKandang.onViewKandangClick(kandang));
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
        private int position;

        public MyMenuItemClickListener(int position) {
            this.position = position;
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int itemId = item.getItemId();
            KandangVO kandang = kandangList.get(position);
            if (itemId == R.id.action_edit) {
                listener.onEditKandangClick(kandang);
                return true;
            } else if (itemId == R.id.action_delete) {
                MutableLiveData<Boolean> deleteResult = new MutableLiveData<>();
                deleteResult.observe((LifecycleOwner) context, isDeleted -> {
                    if (isDeleted) {
                        kandangList.remove(position);
                        notifyItemRemoved(position);
                        Toast.makeText(context, "Kandang berhasil dihapus", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Gagal menghapus kandang", Toast.LENGTH_SHORT).show();
                    }
                });
                KandangViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(KandangViewModel.class);
                viewModel.deleteKandang(kandang.getKdgId(), deleteResult);
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public int getItemCount() {
        return kandangList.size();
    }

    public void filterList(List<KandangVO> filteredList) {
        kandangList = filteredList;
        notifyDataSetChanged();
    }
    private void navigateToFragment(Fragment targetFragment) {
        FragmentManager fragmentManager = fragment.getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_login, targetFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void showPopupMenu(View view, int position) {
        PopupMenu popup = new PopupMenu(context, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_options, popup.getMenu());
        popup.setOnMenuItemClickListener(new KandangAdapter.MyMenuItemClickListener(position));
        popup.show();
    }

    public static class KandangViewHolder extends RecyclerView.ViewHolder {
        TextView namaKandang, lokasi, kapasitas, suhu;
        CardView cardView;
        ImageView titikTiga;
        Button Kandang;

        public KandangViewHolder(@NonNull View itemView) {
            super(itemView);
            namaKandang = itemView.findViewById(R.id.namakandangid);
            lokasi = itemView.findViewById(R.id.lokasikandangid);
            kapasitas = itemView.findViewById(R.id.kapasitaskandangid);
            suhu = itemView.findViewById(R.id.suhukandangid);
            cardView = itemView.findViewById(R.id.cardView);
            titikTiga = itemView.findViewById(R.id.titik_tiga);
            Kandang = itemView.findViewById(R.id.btnView);
        }
    }
}
