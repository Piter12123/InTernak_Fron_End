package com.polytechnic.astra.ac.id.internak.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.polytechnic.astra.ac.id.internak.API.VO.HewanSakitVO;
import com.polytechnic.astra.ac.id.internak.Model.UserModel;
import com.polytechnic.astra.ac.id.internak.R;
import com.polytechnic.astra.ac.id.internak.ViewModel.HewanSakitViewModel;




public class TambahHewanSakitFragment extends Fragment {
    private static final String TAG = "TambahHewanSakitFragment";
    private HewanSakitViewModel hewansakitViewModel;
    private EditText keluhan, deskripsi;
    private Spinner hewanid, periodeid;
    private Button btnSimpan;
    private Integer id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tambah_hewan_sakit, container, false);

        hewansakitViewModel = new ViewModelProvider(this).get(HewanSakitViewModel.class);
        hewanid = view.findViewById(R.id.hewanid);
        keluhan = view.findViewById(R.id.keluhanid);
        periodeid = view.findViewById(R.id.PeriodeId);
        deskripsi = view.findViewById(R.id.DeskripsiId);
        btnSimpan = view.findViewById(R.id.save_button);
        id = getUserIdFromSession();

        ImageButton btnBack = view.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.periode_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        periodeid.setAdapter(adapter);

        btnSimpan.setOnClickListener(v -> createHewan());

        hewansakitViewModel.getHewanSakitListData().observe(getViewLifecycleOwner(), hewan -> {
            if (hewan != null) {
                Clear();
                navigateToNextActivity();
                Toast.makeText(getContext(), "Tambah Data Hewan Berhasil", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Tambah Data Hewan Gagal", Toast.LENGTH_SHORT).show();
                Clear();
            }
        });

        loadHewanIds();

        return view;
    }
    private Integer getUserIdFromSession() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginSession", Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString("dataUser", null);
        if (userJson != null) {
            Gson gson = new Gson();
            UserModel userModel = gson.fromJson(userJson, UserModel.class);
            return userModel.getUsrId();
        }
        return null;
    }

    private void loadHewanIds() {
        hewansakitViewModel.getHewanIdsByUserId(id).observe(getViewLifecycleOwner(), hewanIds -> {
            if (hewanIds != null && !hewanIds.isEmpty()) {
                ArrayAdapter<Integer> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, hewanIds);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                hewanid.setAdapter(adapter);
            } else {
                Toast.makeText(getContext(), "Failed to load Hewan IDs", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createHewan() {
        String hewanIdStr = hewanid.getSelectedItem().toString().trim();
        String keluhanCreate = keluhan.getText().toString().trim();
        String periodeCreate = periodeid.getSelectedItem().toString().trim();
        String deskripsiCreate = deskripsi.getText().toString().trim();

        Integer HewanID;
        try {
            HewanID = Integer.parseInt(hewanIdStr);
        } catch (NumberFormatException e) {
            Log.e(TAG, "Invalid hewanId: " + hewanIdStr, e);
            HewanID = null;
        }

        Log.d(TAG, "Hewan ID: " + HewanID);
        Log.d(TAG, "Keluhan: " + keluhanCreate);
        Log.d(TAG, "Periode: " + periodeCreate);
        Log.d(TAG, "Deskripsi: " + deskripsiCreate);

        if (HewanID == null || keluhanCreate.isEmpty() || periodeCreate.isEmpty() || deskripsiCreate.isEmpty()) {
            Log.e(TAG, "Validation failed. One or more fields are empty or invalid.");
            Toast.makeText(getContext(), "Validation failed. Please fill all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        HewanSakitVO hewansakit = new HewanSakitVO(null, id, keluhanCreate, deskripsiCreate, periodeCreate, null, null, null,HewanID);
        Log.d(TAG, "Creating hewan: " + hewansakit.toString());
        hewansakitViewModel.createHewan(hewansakit);
    }

    private void Clear() {
        hewanid.setSelection(0);
        keluhan.setText("");
        deskripsi.setText("");
        periodeid.setSelection(0);
    }

    private void navigateToNextActivity() {
        try {
            HewanSakitFragment hewanFragment = new HewanSakitFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_login, hewanFragment)
                    .addToBackStack(null)
                    .commit();
        } catch (Exception e) {
            Log.e(TAG, "Error navigating to next activity", e);
        }
    }
}
