package com.polytechnic.astra.ac.id.internak.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.polytechnic.astra.ac.id.internak.API.VO.KandangVO;
import com.polytechnic.astra.ac.id.internak.Model.UserModel;
import com.polytechnic.astra.ac.id.internak.R;
import com.polytechnic.astra.ac.id.internak.ViewModel.KandangViewModel;

public class TambahKandangFragment extends Fragment implements OnMapReadyCallback {

    private static final String TAG = "TambahKandangFragment";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;
    private MapView mMapView;
    private GoogleMap mGoogleMap;
    private KandangViewModel kandangViewModel;
    private EditText edtNamaKandang, edtAlamat, edtKapasitas, edtLuas, edtSuhu;
    private EditText edtLatitude, edtLongitude;

    private Spinner jenisKandangSpinner;
    private Button btnSimpan;
    private Integer id;
    private int kandangId;
    private double latitude, longitude;
    private FusedLocationProviderClient fusedLocationClient;
    private String[] jenisKandangArray = {"Peranakan", "Pembesaran", "Karantina"};
    private KandangViewModel mKandangViewModel;

    public TambahKandangFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tambah_kandang, container, false);

        kandangViewModel = new ViewModelProvider(this).get(KandangViewModel.class);
        edtNamaKandang = view.findViewById(R.id.nama_kandang);
        jenisKandangSpinner = view.findViewById(R.id.jenis_kandang);
        edtAlamat = view.findViewById(R.id.alamat_kandang);
        edtKapasitas = view.findViewById(R.id.kapasitas_kandang);
        edtLuas = view.findViewById(R.id.luas_kandang);
        edtSuhu = view.findViewById(R.id.suhu_kandang);
        edtLatitude = view.findViewById(R.id.latitude_kandang);
        edtLongitude = view.findViewById(R.id.longitude_kandang);

        btnSimpan = view.findViewById(R.id.fabAddKandang);

        mMapView = view.findViewById(R.id.maps_view);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this::onMapReady);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, jenisKandangArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jenisKandangSpinner.setAdapter(adapter);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        UserModel loggedInUser = getLoggedInUser();
        if (loggedInUser != null) {
            id = loggedInUser.getUsrId();
        } else {
            Log.d(TAG, "Tidak ada pengguna yang sedang login.");
        }

        btnSimpan.setOnClickListener(v -> createKandang());

        kandangViewModel.getKandangListData().observe(getViewLifecycleOwner(), kandang -> {
            if (kandang != null) {
                Clear();
                navigateToNextActivity();
                Toast.makeText(getContext(), "Tambah Data Kandang Berhasil", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Tambah Data Kandang Gagal", Toast.LENGTH_SHORT).show();
            }
        });

        // Panggil getCurrentLocation() di sini
        getCurrentLocation();

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

    private void createKandang() {
        String namakandang = edtNamaKandang.getText().toString().trim();
        String jenisStr = jenisKandangSpinner.getSelectedItem().toString();
        String alamatStr = edtAlamat.getText().toString().trim();
        String kapasitasStr = edtKapasitas.getText().toString().trim();
        String luasStr = edtLuas.getText().toString().trim();
        String suhuStr = edtSuhu.getText().toString().trim();

        Log.d("TambahKandangFragment", "Nama Kandang: " + namakandang);
        Log.d("TambahKandangFragment", "Jenis: " + jenisStr);
        Log.d("TambahKandangFragment", "Alamat: " + alamatStr);
        Log.d("TambahKandangFragment", "Kapasitas: " + kapasitasStr);
        Log.d("TambahKandangFragment", "Luas: " + luasStr);
        Log.d("TambahKandangFragment", "Latitude: " + latitude);
        Log.d("TambahKandangFragment", "Longitude: " + longitude);
        Log.d("TambahKandangFragment", "Suhu: " + suhuStr);

        if (TextUtils.isEmpty(namakandang)) {
            edtNamaKandang.setError("Nama Kandang wajib Di isi");
            edtNamaKandang.requestFocus();
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
        if (TextUtils.isEmpty(suhuStr)) {
            edtLuas.setError("Suhu Kandang wajib Di isi");
            edtLuas.requestFocus();
            return;
        }
        Integer suhu;
        Integer luas;
        Integer kapasitas;
        try {
            suhu = Integer.parseInt(suhuStr);
            luas = Integer.parseInt(luasStr);
            kapasitas = Integer.parseInt(kapasitasStr);
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Luas dan Kapasitas harus berupa angka", Toast.LENGTH_SHORT).show();
            return;
        }

        KandangVO kandang = new KandangVO(
                id,
                namakandang,
                jenisStr,
                luas,
                kapasitas,
                alamatStr,
                latitude,
                longitude,
                suhu
        );
        Log.d("TambahKandangFragment", "Creating kandang: " + kandang.toString());
        Clear();
        navigateToNextActivity();
        kandangViewModel.createKandang(kandang);
        Log.d("TambahKandangFragment", "createKandang() called in ViewModel");
        Clear();
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(), location -> {
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        Log.d(TAG, "Latitude: " + latitude + ", Longitude: " + longitude);
                        edtLatitude.setText(String.valueOf(latitude));
                        edtLongitude.setText(String.valueOf(longitude));
                        if (mGoogleMap != null) {
                            LatLng currentLocation = new LatLng(latitude, longitude);
                            mGoogleMap.clear();
                            mGoogleMap.addMarker(new MarkerOptions().position(currentLocation).title("Current Location"));
                            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));
                        }
                    } else {
                        Log.e(TAG, "Lokasi saat ini tidak tersedia");
                    }
                });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
                Toast.makeText(getContext(), "Izin lokasi ditolak", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mGoogleMap = googleMap;
        LatLng sydney = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Your Location"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private void navigateToNextActivity() {
        Fragment nextFragment = new KandangFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_login, nextFragment)
                .commit();
    }

    private void Clear() {
        edtNamaKandang.setText("");
        edtAlamat.setText("");
        edtKapasitas.setText("");
        edtLuas.setText("");
        edtLatitude.setText("");
        edtLongitude.setText("");
        edtSuhu.setText("");
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
