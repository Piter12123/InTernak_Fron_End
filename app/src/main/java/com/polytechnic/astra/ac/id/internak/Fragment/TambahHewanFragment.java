package com.polytechnic.astra.ac.id.internak.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;
import com.polytechnic.astra.ac.id.internak.R;
import com.polytechnic.astra.ac.id.internak.ViewModel.HewanViewModel;

import java.util.Calendar;

public class TambahHewanFragment extends Fragment {
    private static final String TAG = "TambahHewanFragment";
    private HewanViewModel hewanViewModel;
    private EditText edtNamahewan, edtUsia, edtBerat, edtTanggalMasuk;
    private Button btnSimpan;
    private Integer id;
    private ImageView calendarIcon;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tambah_hewan, container, false);

        hewanViewModel = new ViewModelProvider(this).get(HewanViewModel.class);

        edtNamahewan = view.findViewById(R.id.nama_hewan);
        edtUsia = view.findViewById(R.id.usia_hewan);
        edtBerat = view.findViewById(R.id.berat_hewan);
        edtTanggalMasuk = view.findViewById(R.id.tanggal_masuk_hewan);
        id = 1;
        btnSimpan = view.findViewById(R.id.save_button);
        calendarIcon = view.findViewById(R.id.calendar_icon);

        btnSimpan.setOnClickListener(v -> createHewan());
        calendarIcon.setOnClickListener(v -> showDatePickerDialog());
        hewanViewModel.getHewanListData().observe(getViewLifecycleOwner(), hewan -> {
            if (hewan != null) {
                Clear();
                navigateToNextActivity();
                Toast.makeText(getContext(), "Tambah Data Hewan Berhasil", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Tambah Data Hewan Gagal", Toast.LENGTH_SHORT).show();
                Clear();
            }
        });

        return view;
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (view, year1, monthOfYear, dayOfMonth) -> {
            String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1;
            edtTanggalMasuk.setText(selectedDate);
        }, year, month, day);
        datePickerDialog.show();
    }

    private void createHewan() {
        String namaHewan = edtNamahewan.getText().toString().trim();
        String usiaStr = edtUsia.getText().toString().trim();
        String beratStr = edtBerat.getText().toString().trim();
        String tanggalMasuk = edtTanggalMasuk.getText().toString().trim();

        Log.d("TambahHewanFragment", "Nama Hewan: " + namaHewan);
        Log.d("TambahHewanFragment", "Usia: " + usiaStr);
        Log.d("TambahHewanFragment", "Berat: " + beratStr);
        Log.d("TambahHewanFragment", "Tanggal Masuk: " + tanggalMasuk);

        if (TextUtils.isEmpty(namaHewan)) {
            edtNamahewan.setError("Nama Hewan wajib Di isi");
            edtNamahewan.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(usiaStr)) {
            edtUsia.setError("Usia Hewan wajib Di isi");
            edtUsia.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(beratStr)) {
            edtBerat.setError("Berat Hewan wajib Di isi");
            edtBerat.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(tanggalMasuk)) {
            edtTanggalMasuk.setError("Tanggal Masuk Hewan wajib Di isi");
            edtTanggalMasuk.requestFocus();
            return;
        }

        Integer usia;
        Integer berat;
        try {
            usia = Integer.parseInt(usiaStr);
            berat = Integer.parseInt(beratStr);
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Usia dan Berat harus berupa angka", Toast.LENGTH_SHORT).show();
            return;
        }

        HewanVO hewan = new HewanVO(null, id, namaHewan, usia, berat, tanggalMasuk, null);
        Log.d("TambahHewanFragment", "Creating hewan: " + hewan.toString());
        Clear();
        navigateToNextActivity();
        hewanViewModel.createHewan(hewan);
        Log.d("TambahHewanFragment", "createHewan() called in ViewModel");
        Clear();
    }
    private void Clear(){
        edtNamahewan.setText("");
        edtUsia.setText("");
        edtBerat.setText("");
        edtTanggalMasuk.setText("");
    }
    private void navigateToNextActivity() {
        try {
            HewanFragment hewanFragment = new HewanFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_login, hewanFragment)
                    .addToBackStack(null)
                    .commit();
        } catch (Exception e) {
            Log.e(TAG, "Error starting TambahHewanFragment", e);
        }
    }
}
