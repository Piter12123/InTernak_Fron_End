package com.polytechnic.astra.ac.id.internak.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.Manifest;

import com.google.gson.Gson;
import com.polytechnic.astra.ac.id.internak.API.VO.KandangVO;
import com.polytechnic.astra.ac.id.internak.Model.UserModel;
import com.polytechnic.astra.ac.id.internak.R;
import com.polytechnic.astra.ac.id.internak.ViewModel.KandangViewModel;

public class DetailKandangFragment extends Fragment implements OnMapReadyCallback {
    private static final String TAG = "DetailKandangFragment";
    private static final String ARG_KANDANG_ID = "kandang_id";
    private static final String ARG_KANDANG_NAMA = "kandang_nama";
    private static final String ARG_KANDANG_JENIS = "kandang_jenis";
    private static final String ARG_KANDANG_KAPASITAS = "kandang_kapasitas";
    private static final String ARG_KANDANG_ALAMAT = "kandang_alamat";
    private static final String ARG_KANDANG_SUHU = "kandang_suhu";
    private static final int REQUEST_LOCATION_SETTINGS = 1001;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private FusedLocationProviderClient mFusedLocationClient;
    private MapView mMapView;
    private GoogleMap mGoogleMap;
    private KandangViewModel mKandangViewModel;
    private double latitude;
    private double longtitude;
    private int kandangId;

    public DetailKandangFragment() {

    }

    public static DetailKandangFragment newInstance(int kandangId, String nama, String jenis, int kapasitas, String alamat, int suhu) {
        DetailKandangFragment fragment = new DetailKandangFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_KANDANG_ID, kandangId);
        args.putString(ARG_KANDANG_NAMA, nama);
        args.putString(ARG_KANDANG_JENIS, jenis);
        args.putInt(ARG_KANDANG_KAPASITAS, kapasitas);
        args.putString(ARG_KANDANG_ALAMAT, alamat);
        args.putInt(ARG_KANDANG_SUHU, suhu);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_kandang, container, false);

        if (getArguments() != null) {
            kandangId = getArguments().getInt(ARG_KANDANG_ID);
            String nama = getArguments().getString(ARG_KANDANG_NAMA);
            String jenis = getArguments().getString(ARG_KANDANG_JENIS);
            int kapasitas = getArguments().getInt(ARG_KANDANG_KAPASITAS);
            String alamat = getArguments().getString(ARG_KANDANG_ALAMAT);
            int suhu = getArguments().getInt(ARG_KANDANG_SUHU);

            mKandangViewModel = new ViewModelProvider(this).get(KandangViewModel.class);

            // Get logged-in user
            UserModel loggedInUser = getLoggedInUser();
            if (loggedInUser != null) {
                // Load Kandang data for the logged-in user
                mKandangViewModel.loadKandangData(loggedInUser.getUsrId());
            } else {
                Log.d(TAG, "Tidak ada pengguna yang sedang login.");
            }

            // Bind data to views
            TextView namaTextView = view.findViewById(R.id.nama_kandang);
            TextView jenisTextView = view.findViewById(R.id.jenis_kandang);
            TextView kapasitasTextView = view.findViewById(R.id.kapasitaskandangid);
            TextView alamatTextView = view.findViewById(R.id.alamat_kandang);
            TextView suhuTextView = view.findViewById(R.id.suhukandangid);
            TextView namaPeternakTextView = view.findViewById(R.id.nama_peternak);

            mMapView = view.findViewById(R.id.maps_view);
            mMapView.onCreate(savedInstanceState);
            mMapView.getMapAsync(this);

            namaTextView.setText(nama);
            jenisTextView.setText(jenis);
            kapasitasTextView.setText(String.valueOf(kapasitas));
            alamatTextView.setText(alamat);
            suhuTextView.setText(String.valueOf(suhu));

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginSession", Context.MODE_PRIVATE);
            String userJson = sharedPreferences.getString("dataUser", null);
            if (userJson != null) {
                Gson gson = new Gson();
                UserModel userModel = gson.fromJson(userJson, UserModel.class);
                namaPeternakTextView.setText(userModel.getUsrNama());
            }

        }
        return view;
    }

    private UserModel getLoggedInUser() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginSession", Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString("dataUser", null);
        if (userJson != null) {
            Gson gson = new Gson();
            return gson.fromJson(userJson, UserModel.class);
        }
        return null;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.mGoogleMap = googleMap;
        mKandangViewModel.getKandangListData().observe(getViewLifecycleOwner(), kandangList -> {
            if (kandangList != null) {
                for (KandangVO kandang : kandangList) {
                    if (kandang.getKdgId() == kandangId) {
                        LatLng location = new LatLng(kandang.getKdgLattitude(), kandang.getKdgLongtitude());
                        googleMap.addMarker(new MarkerOptions().position(location));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 18));
                        break;
                    }
                }
            }
        });
    }
}
