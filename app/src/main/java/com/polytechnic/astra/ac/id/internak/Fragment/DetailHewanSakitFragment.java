//package com.polytechnic.astra.ac.id.internak.Fragment;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentTransaction;
//import androidx.lifecycle.Observer;
//import androidx.lifecycle.ViewModelProvider;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.TextView;
//
//import com.google.gson.Gson;
//import com.polytechnic.astra.ac.id.internak.API.VO.HewanSakitVO;
//import com.polytechnic.astra.ac.id.internak.API.VO.PerkembanganVO;
//import com.polytechnic.astra.ac.id.internak.Adapter.HewanSakitAdapter;
//import com.polytechnic.astra.ac.id.internak.Adapter.PerkembanganAdapter;
//import com.polytechnic.astra.ac.id.internak.Model.UserModel;
//import com.polytechnic.astra.ac.id.internak.R;
//import com.polytechnic.astra.ac.id.internak.ViewModel.HewanSakitViewModel;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class DetailHewanSakitFragment extends Fragment {
//
//    private static final String ARG_HEWAN_ID = "hewan_id";
//    private static final String ARG_HEWAN_NAMA = "hewan_nama";
//
//    public static DetailHewanFragment newInstance(int id, String nama, int usia, float berat, String masuk, String status) {
//        DetailHewanFragment fragment = new DetailHewanFragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_HEWAN_ID, id);
//        args.putString(ARG_HEWAN_NAMA, nama);
//        args.putInt(ARG_HEWAN_USIA, usia);
//        args.putFloat(ARG_HEWAN_BERAT, berat);
//        args.putString(ARG_HEWAN_MASUK, masuk);
//        args.putString(ARG_HEWAN_STATUS, status);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_detail_hewan, container, false);
//
//        if (getArguments() != null) {
//            int id = getArguments().getInt(ARG_HEWAN_ID);
//            String nama = getArguments().getString(ARG_HEWAN_NAMA);
//            int usia = getArguments().getInt(ARG_HEWAN_USIA);
//            float berat = getArguments().getFloat(ARG_HEWAN_BERAT);
//            String masuk = getArguments().getString(ARG_HEWAN_MASUK);
//            String status = getArguments().getString(ARG_HEWAN_STATUS);
//
//            // Bind data to views
//            TextView idTextView = view.findViewById(R.id.id_hewan);
//            TextView namaTextView = view.findViewById(R.id.nama_hewan);
//            TextView usiaTextView = view.findViewById(R.id.usia_hewan);
//            TextView beratTextView = view.findViewById(R.id.berat_hewan);
//            TextView statusTextView = view.findViewById(R.id.status_hewan);
//
//            idTextView.setText(String.valueOf(id));
//            namaTextView.setText(nama);
//            usiaTextView.setText(String.valueOf(usia));
//            beratTextView.setText(String.valueOf(berat));
//            statusTextView.setText(status);
//        }
//
//        return view;
//    }
//}