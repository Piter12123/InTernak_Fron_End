package com.polytechnic.astra.ac.id.internak.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.polytechnic.astra.ac.id.internak.R;

public class DetailKandangFragment extends Fragment {
    private static final String ARG_KANDANG_ID = "kandang_id";
    private static final String ARG_KANDANG_NAMA = "kandang_nama";
    private static final String ARG_KANDANG_JENIS = "kandang_jenis";
    private static final String ARG_KANDANG_KAPASITAS = "kandang_kapasitas";
    private static final String ARG_KANDANG_ALAMAT = "kandang_alamat";
    private static final String ARG_KANDANG_LOKASI = "kandang_titik_lokasi";
    private static final String ARG_KANDANG_SUHU = "kandang_suhu";

    public static DetailKandangFragment newInstance(String nama, String jenis, int kapasitas, String alamat, String titikLokasi, int suhu) {
        DetailKandangFragment fragment = new DetailKandangFragment();
        Bundle args = new Bundle();
        args.putString(ARG_KANDANG_NAMA, nama);
        args.putString(ARG_KANDANG_JENIS, jenis);
        args.putInt(ARG_KANDANG_KAPASITAS, kapasitas);
        args.putString(ARG_KANDANG_ALAMAT, alamat);
        args.putString(ARG_KANDANG_LOKASI, titikLokasi);
        args.putInt(ARG_KANDANG_SUHU, suhu);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_kandang, container, false);

        if (getArguments() != null) {
            String nama = getArguments().getString(ARG_KANDANG_NAMA);
            String jenis = getArguments().getString(ARG_KANDANG_JENIS);
            int kapasitas = getArguments().getInt(ARG_KANDANG_KAPASITAS);
            String alamat = getArguments().getString(ARG_KANDANG_ALAMAT);
            String lokasi = getArguments().getString(ARG_KANDANG_LOKASI);
            int suhu = getArguments().getInt(ARG_KANDANG_SUHU);

            // Bind data to views
            TextView namaTextView = view.findViewById(R.id.nama_kandang);
            TextView jenisTextView = view.findViewById(R.id.jenis_kandang);
            TextView kapasitasTextView = view.findViewById(R.id.kapasitas_kandang);
            TextView alamatTextView = view.findViewById(R.id.alamat_kandang);
            TextView lokasiTextView = view.findViewById(R.id.titik_lokasi_kandang);
            TextView suhuTextView = view.findViewById(R.id.suhukandangid);

            namaTextView.setText(nama);
            jenisTextView.setText(jenis);
            kapasitasTextView.setText(String.valueOf(kapasitas));
            alamatTextView.setText(alamat);
            lokasiTextView.setText(lokasi);
            suhuTextView.setText(String.valueOf(suhu));
        }
        return view;
    }
}
