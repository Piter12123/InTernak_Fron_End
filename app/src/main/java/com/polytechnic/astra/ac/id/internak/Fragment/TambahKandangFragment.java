package com.polytechnic.astra.ac.id.internak.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.polytechnic.astra.ac.id.internak.API.VO.KandangVO;
import com.polytechnic.astra.ac.id.internak.R;
import com.polytechnic.astra.ac.id.internak.ViewModel.KandangViewModel;

public class TambahKandangFragment extends Fragment {

    private static final String TAG = "TambahKandangFragment";
    private KandangViewModel kandangViewModel;
    private EditText edtNamaKandang, edtJenis, edtAlamat, edtKapasitas, edtLuas, edtTitikLokasi;
    private Button btnSimpan;
    private Integer id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tambah_kandang, container, false);

        kandangViewModel = new ViewModelProvider(this).get(KandangViewModel.class);

        edtNamaKandang = view.findViewById(R.id.nama_kandang);
        edtJenis = view.findViewById(R.id.jenis_kandang);
        edtAlamat = view.findViewById(R.id.alamat_kandang);
        id = 1;
        edtKapasitas = view.findViewById(R.id.kapasitas_kandang);
        edtLuas = view.findViewById(R.id.luas_kandang);
        edtTitikLokasi = view.findViewById(R.id.titik_lokasi_kandang);
        btnSimpan = view.findViewById(R.id.save_button);

        btnSimpan.setOnClickListener(v -> createHewan());

        kandangViewModel.getKandangListData().observe(getViewLifecycleOwner(), kandang -> {
            if (kandang != null) {
                Clear();
                navigateToNextActivity();
                Toast.makeText(getContext(), "Tambah Data Kandang Berhasil", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Tambah Data Kandang Gagal", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void createHewan() {
        String namakandang = edtNamaKandang.getText().toString().trim();
        String jenisStr = edtJenis.getText().toString().trim();
        String alamatStr = edtAlamat.getText().toString().trim();
        String kapasitasStr = edtKapasitas.getText().toString().trim();
        String luasStr = edtLuas.getText().toString().trim();
        String titikLokasiStr = edtTitikLokasi.getText().toString().trim();

        Log.d("TambahKandangFragment", "Nama Kandang: " + namakandang);
        Log.d("TambahKandangFragment", "Jenis: " + jenisStr);
        Log.d("TambahKandangFragment", "Alamat: " + alamatStr);
        Log.d("TambahKandangFragment", "Kapasitas: " + kapasitasStr);
        Log.d("TambahKandangFragment", "Luas: " + luasStr);
        Log.d("TambahKandangFragment", "Titik Lokasi: " + titikLokasiStr);

        if (TextUtils.isEmpty(namakandang)) {
            edtNamaKandang.setError("Nama Kandang wajib Di isi");
            edtNamaKandang.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(jenisStr)) {
            edtJenis.setError("Jenis Kandang wajib Di isi");
            edtJenis.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(alamatStr)) {
            edtAlamat.setError("Alamat Kandang wajib Di isi");
            edtAlamat.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(kapasitasStr)) {
            edtKapasitas.setError("Kapsitas Kandang wajib Di isi");
            edtKapasitas.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(luasStr)) {
            edtLuas.setError("Luas Kandang wajib Di isi");
            edtLuas.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(titikLokasiStr)) {
            edtTitikLokasi.setError("Titik Lokasi Kandang wajib Di isi");
            edtTitikLokasi.requestFocus();
            return;
        }
        Integer luas;
        Integer kapasitas;
        try {
            luas = Integer.parseInt(luasStr);
            kapasitas = Integer.parseInt(kapasitasStr);
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Luas dan Kapasitas harus berupa angka", Toast.LENGTH_SHORT).show();
            return;
        }
        KandangVO kandang = new KandangVO(
                null,
                id,
                namakandang,
                jenisStr,
                Integer.parseInt(kapasitasStr),
                Integer.parseInt(luasStr),
                alamatStr,
                titikLokasiStr,
                null,
                null,
                null
        );
        Log.d("TambahKandangFragment", "Creating kandang: " + kandang.toString());
        Clear();
        navigateToNextActivity();
        kandangViewModel.createKandang(kandang);
        Log.d("TambahKandangFragment", "createKandang() called in ViewModel");
        Clear();
    }

    private void Clear(){
        edtNamaKandang.setText("");
        edtJenis.setText("");
        edtLuas.setText("");
        edtKapasitas.setText("");
        edtAlamat.setText("");
        edtTitikLokasi.setText("");
    }
    private void navigateToNextActivity() {
        try {
            KandangFragment kandangFragment = new KandangFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_login, kandangFragment)
                    .addToBackStack(null)
                    .commit();
        } catch (Exception e) {
            Log.e(TAG, "Error starting TambahKandangFragment", e);
        }
    }
}
