package com.polytechnic.astra.ac.id.internak.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;
import com.polytechnic.astra.ac.id.internak.R;
import com.polytechnic.astra.ac.id.internak.ViewModel.HewanViewModel;

import java.util.Calendar;

public class EditHewanFragment extends Fragment {
    private static final String TAG = "EditHewanFragment";


    private static final String ARG_HEWAN_ID = "hewan_id";
    private static final String ARG_HEWAN_NAMA = "hewan_nama";
    private static final String ARG_HEWAN_USIA = "hewan_usia";
    private static final String ARG_HEWAN_BERAT = "hewan_berat";
    private static final String ARG_HEWAN_TANGGAL_MASUK = "hewan_tanggal_masuk";

    private int hewanId;
    private String hewanNama;
    private int hewanUsia;
    private int hewanBerat;
    private String hewanTanggalMasuk;

    private EditText namaHewanEditText;
    private EditText usiaHewanEditText;
    private EditText beratHewanEditText;
    private EditText tanggalMasukHewanEditText;
    private HewanViewModel hewanViewModel;
    private ImageView calendarIcon;
    private Button btSimpan;



    public EditHewanFragment() {
    }

    public static EditHewanFragment newInstance(int hewanId, String hewanNama, int hewanUsia, int hewanBerat, String hewanTanggalMasuk) {
        EditHewanFragment fragment = new EditHewanFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_HEWAN_ID, hewanId);
        args.putString(ARG_HEWAN_NAMA, hewanNama);
        args.putInt(ARG_HEWAN_USIA, hewanUsia);
        args.putInt(ARG_HEWAN_BERAT, hewanBerat);
        args.putString(ARG_HEWAN_TANGGAL_MASUK, hewanTanggalMasuk);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            hewanId = getArguments().getInt(ARG_HEWAN_ID);
            hewanNama = getArguments().getString(ARG_HEWAN_NAMA);
            hewanUsia = getArguments().getInt(ARG_HEWAN_USIA);
            hewanBerat = getArguments().getInt(ARG_HEWAN_BERAT);
            hewanTanggalMasuk = getArguments().getString(ARG_HEWAN_TANGGAL_MASUK);
        }
        hewanViewModel = new ViewModelProvider(this).get(HewanViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_hewan, container, false);

        namaHewanEditText = view.findViewById(R.id.nama_hewan);
        usiaHewanEditText = view.findViewById(R.id.usia_hewan);
        beratHewanEditText = view.findViewById(R.id.berat_hewan);
        tanggalMasukHewanEditText = view.findViewById(R.id.tanggal_masuk_hewan);
        btSimpan = view.findViewById(R.id.save_button);
        calendarIcon = view.findViewById(R.id.calendar_icon);


        // Set the EditText fields with the received data
        namaHewanEditText.setText(hewanNama);
        usiaHewanEditText.setText(String.valueOf(hewanUsia));
        beratHewanEditText.setText(String.valueOf(hewanBerat));
        tanggalMasukHewanEditText.setText(hewanTanggalMasuk);

        btSimpan.setOnClickListener(v -> simpanData());
        calendarIcon.setOnClickListener(v -> showDatePickerDialog());
        return view;
    }
    private void simpanData() {
        String namaBaru = namaHewanEditText.getText().toString();
        int usiaBaru = Integer.parseInt(usiaHewanEditText.getText().toString());
        int beratBaru = Integer.parseInt(beratHewanEditText.getText().toString());
        String tanggalMasukBaru = tanggalMasukHewanEditText.getText().toString();

        // Lakukan validasi dan simpan data baru
        if (namaBaru.isEmpty() || usiaBaru <= 0 || beratBaru <= 0 || tanggalMasukBaru.isEmpty()) {
            Toast.makeText(getContext(), "Data tidak valid. Harap periksa kembali.", Toast.LENGTH_SHORT).show();
            return;
        }

        HewanVO hewanBaru = new HewanVO();
        hewanBaru.setHwnId(hewanId);
        hewanBaru.setHwnNama(namaBaru);
        hewanBaru.setHwnUsia(usiaBaru);
        hewanBaru.setHwnBerat(beratBaru);
        hewanBaru.setHwnMasuk(tanggalMasukBaru);
        hewanBaru.setHwnStatus("Sehat");

        hewanViewModel.updateHewan(hewanBaru);

        Toast.makeText(getContext(), "Data berhasil disimpan.", Toast.LENGTH_SHORT).show();
        navigateToNextActivity();
        namaHewanEditText.setText("");
        usiaHewanEditText.setText("");
        beratHewanEditText.setText("");
        tanggalMasukHewanEditText.setText("");
    }
    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (view, year1, monthOfYear, dayOfMonth) -> {
            String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1;
            tanggalMasukHewanEditText.setText(selectedDate);
        }, year, month, day);
        datePickerDialog.show();
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
