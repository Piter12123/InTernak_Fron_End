package com.polytechnic.astra.ac.id.internak.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;
import com.polytechnic.astra.ac.id.internak.Model.UserModel;
import com.polytechnic.astra.ac.id.internak.R;
import com.polytechnic.astra.ac.id.internak.ViewModel.HewanViewModel;

import java.util.ArrayList;
import java.util.Calendar;

public class TambahHewanFragment extends Fragment {
    private static final String TAG = "TambahHewanFragment";
    private HewanViewModel hewanViewModel;
    private EditText edtNamahewan, edtUsia, edtBerat, edtTanggalMasuk;
    private Button btnSimpan;
    private ImageView calendarIcon;
    private Spinner kandangSpinner;
    private ArrayAdapter<Integer> kandangAdapter;
    private Integer selectedKandangId;
    private UserModel loggedInUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tambah_hewan, container, false);

        hewanViewModel = new ViewModelProvider(this).get(HewanViewModel.class);

        edtNamahewan = view.findViewById(R.id.nama_hewan);
        edtUsia = view.findViewById(R.id.usia_hewan);
        edtBerat = view.findViewById(R.id.berat_hewan);
        edtTanggalMasuk = view.findViewById(R.id.tanggal_masuk_hewan);
        btnSimpan = view.findViewById(R.id.save_button);
        calendarIcon = view.findViewById(R.id.calendar_icon);
        kandangSpinner = view.findViewById(R.id.kandangid);

        kandangAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, new ArrayList<>());
        kandangAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kandangSpinner.setAdapter(kandangAdapter);

        loadLoggedInUserData();//load data login sesion

        btnSimpan.setOnClickListener(v -> createHewan());
        calendarIcon.setOnClickListener(v -> showDatePickerDialog());

        hewanViewModel.getKandangListData().observe(getViewLifecycleOwner(), kandangIds -> {
            if (kandangIds != null && !kandangIds.isEmpty()) {
                kandangAdapter.clear();
                kandangAdapter.addAll(kandangIds);
                kandangAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), "Gagal memuat data kandang", Toast.LENGTH_SHORT).show();
            }
        });

        hewanViewModel.getHewanListData().observe(getViewLifecycleOwner(), hewan -> {
            if (hewan != null) {
                clearFields();
                navigateToNextActivity();
                Toast.makeText(getContext(), "Tambah Data Hewan Berhasil", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Tambah Data Hewan Gagal", Toast.LENGTH_SHORT).show();
                clearFields();
            }
        });

        if (loggedInUser != null) {
            hewanViewModel.loadKandangData(loggedInUser.getUsrId());
        } else {
            Toast.makeText(getContext(), "Gagal memuat data pengguna", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    private void loadLoggedInUserData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginSession", Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString("dataUser", null);
        if (userJson != null) {
            Gson gson = new Gson();
            loggedInUser = gson.fromJson(userJson, UserModel.class);
            Log.d(TAG, "Logged in user: " + loggedInUser.toString());
        }
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
        selectedKandangId = (Integer) kandangSpinner.getSelectedItem();

        Log.d("TambahHewanFragment", "Nama Hewan: " + namaHewan);
        Log.d("TambahHewanFragment", "Usia: " + usiaStr);
        Log.d("TambahHewanFragment", "Berat: " + beratStr);
        Log.d("TambahHewanFragment", "Tanggal Masuk: " + tanggalMasuk);
        Log.d("TambahHewanFragment", "Kandang ID: " + selectedKandangId);

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

        if (loggedInUser != null) {
            HewanVO hewan = new HewanVO(null, selectedKandangId, namaHewan, usia, berat, tanggalMasuk, null,loggedInUser.getUsrId());
            Log.d("TambahHewanFragment", "Creating hewan: " + hewan.toString());
            clearFields();
            navigateToNextActivity();
            hewanViewModel.createHewan(hewan);
            Log.d("TambahHewanFragment", "createHewan() called in ViewModel");
            clearFields();
        } else {
            Toast.makeText(getContext(), "Gagal mendapatkan data pengguna. Harap login kembali.", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
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
