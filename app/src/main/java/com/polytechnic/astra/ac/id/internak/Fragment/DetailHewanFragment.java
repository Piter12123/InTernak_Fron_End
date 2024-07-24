package com.polytechnic.astra.ac.id.internak.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;
import com.polytechnic.astra.ac.id.internak.Model.UserModel;
import com.polytechnic.astra.ac.id.internak.R;
import com.polytechnic.astra.ac.id.internak.ViewModel.HewanViewModel;

public class DetailHewanFragment extends Fragment {
    private static final String ARG_HEWAN_ID = "hewan_id";
    private static final String ARG_HEWAN_NAMA = "hewan_nama";
    private static final String ARG_HEWAN_USIA = "hewan_usia";
    private static final String ARG_HEWAN_BERAT = "hewan_berat";
    private static final String ARG_HEWAN_MASUK = "hewan_masuk";
    private static final String ARG_HEWAN_STATUS = "hewan_status";

    public static DetailHewanFragment newInstance(int id, String nama, int usia, float berat, String masuk, String status) {
        DetailHewanFragment fragment = new DetailHewanFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_HEWAN_ID, id);
        args.putString(ARG_HEWAN_NAMA, nama);
        args.putInt(ARG_HEWAN_USIA, usia);
        args.putFloat(ARG_HEWAN_BERAT, berat);
        args.putString(ARG_HEWAN_MASUK, masuk);
        args.putString(ARG_HEWAN_STATUS, status);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_hewan, container, false);

        if (getArguments() != null) {
            int id = getArguments().getInt(ARG_HEWAN_ID);
            String nama = getArguments().getString(ARG_HEWAN_NAMA);
            int usia = getArguments().getInt(ARG_HEWAN_USIA);
            float berat = getArguments().getFloat(ARG_HEWAN_BERAT);
            String masuk = getArguments().getString(ARG_HEWAN_MASUK);
            String status = getArguments().getString(ARG_HEWAN_STATUS);

            // Bind data to views
            TextView idTextView = view.findViewById(R.id.id_hewan);
            TextView namaTextView = view.findViewById(R.id.nama_hewan);
            TextView usiaTextView = view.findViewById(R.id.usia_hewan);
            TextView beratTextView = view.findViewById(R.id.berat_hewan);
            TextView statusTextView = view.findViewById(R.id.status_hewan);
            TextView namaPeternakTextView = view.findViewById(R.id.nama_peternak);

            idTextView.setText(String.valueOf(id));
            namaTextView.setText(nama);
            usiaTextView.setText(String.valueOf(usia));
            beratTextView.setText(String.valueOf(berat));
            statusTextView.setText(status);

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginSession", Context.MODE_PRIVATE);
            String userJson = sharedPreferences.getString("dataUser", null);
            if (userJson != null) {
                Gson gson = new Gson();
                UserModel userModel = gson.fromJson(userJson, UserModel.class);
                namaPeternakTextView.setText(userModel.getUsrNama());
            }

            Button editButton = view.findViewById(R.id.edit_button);
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditHewanFragment editHewanFragment = EditHewanFragment.newInstance(id, nama, usia, (int) berat, masuk);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_login, editHewanFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });
        }

        return view;
    }
}
