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

import com.polytechnic.astra.ac.id.internak.API.VO.HewanSakitVO;
import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;
import com.polytechnic.astra.ac.id.internak.Fragment.HewanSakitFragment;
import com.polytechnic.astra.ac.id.internak.R;
import com.polytechnic.astra.ac.id.internak.ViewModel.HewanViewModel;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class HewanSakitAdapter extends RecyclerView.Adapter<HewanSakitAdapter.HewanSakitViewHolder> {

    private List<HewanSakitVO> hewansakitList;
    private Context context;

    public HewanSakitAdapter(Context context, List<HewanSakitVO> hewansakitList) {
        this.context = context;
        this.hewansakitList = hewansakitList;
    }

    @NonNull
    @Override
    public HewanSakitAdapter.HewanSakitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_hewan_sakit, parent, false);
        return new HewanSakitAdapter.HewanSakitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HewanSakitAdapter.HewanSakitViewHolder holder, int position) {
        HewanSakitVO hewansakit = hewansakitList.get(position);

        // Format the date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String formattedDate = dateFormat.format(hewansakit.getTmoTanggalMulai());

        holder.timeid.setText(formattedDate);
        holder.hewanid.setText("ID Hewan: " + hewansakit.getHwnId());
        holder.keluhanid.setText(hewansakit.getTmoKeluhan());
    }


    @Override
    public int getItemCount() {
        return hewansakitList.size();
    }

    public void filterList(List<HewanSakitVO> filteredList) {
        hewansakitList = filteredList;
        notifyDataSetChanged();
    }

    public static class HewanSakitViewHolder extends RecyclerView.ViewHolder {
        TextView timeid, hewanid, keluhanid;
        CardView cardView;
        ImageView titikTiga;

        public HewanSakitViewHolder(@NonNull View itemView) {
            super(itemView);
            timeid = itemView.findViewById(R.id.timeid);
            hewanid = itemView.findViewById(R.id.hewanid);
            keluhanid = itemView.findViewById(R.id.keluhanid);
            cardView = itemView.findViewById(R.id.cardView);
            titikTiga = itemView.findViewById(R.id.icon_titik_tiga);
        }
    }
}
