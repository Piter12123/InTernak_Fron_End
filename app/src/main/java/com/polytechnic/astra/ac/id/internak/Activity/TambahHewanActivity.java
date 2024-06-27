    package com.polytechnic.astra.ac.id.internak.Activity;
    import com.polytechnic.astra.ac.id.internak.R;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.lifecycle.ViewModelProvider;

    import android.app.DatePickerDialog;
    import android.os.Bundle;
    import android.text.TextUtils;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.ImageView;
    import android.widget.Toast;

    import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;
    import com.polytechnic.astra.ac.id.internak.ViewModel.HewanViewModel;

    import java.util.Calendar;

    public class TambahHewanActivity extends AppCompatActivity {

        private HewanViewModel hewanViewModel;
        private EditText edtNamahewan, edtUsia, edtBerat, edtTanggalMasuk;
        private Button btnSimpan;
        private Integer id;
        private ImageView calendarIcon;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tambah_hewan);

            hewanViewModel = new ViewModelProvider(this).get(HewanViewModel.class);

            edtNamahewan = findViewById(R.id.nama_hewan);
            edtUsia = findViewById(R.id.usia_hewan);
            edtBerat = findViewById(R.id.berat_hewan);
            edtTanggalMasuk = findViewById(R.id.tanggal_masuk_hewan);
            id = 1;
            btnSimpan = findViewById(R.id.save_button);
            calendarIcon = findViewById(R.id.calendar_icon);

            btnSimpan.setOnClickListener(v -> createHewan());
            calendarIcon.setOnClickListener(v -> showDatePickerDialog());

            hewanViewModel.getHewanData().observe(this, hewan -> {
                if (hewan != null) {
                    Toast.makeText(this, "Tambah Data Hewan Berhasil", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Tambah Data Hewan Gagal", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void showDatePickerDialog() {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, monthOfYear, dayOfMonth) -> {
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
                Toast.makeText(this, "Usia dan Berat harus berupa angka", Toast.LENGTH_SHORT).show();
                return;
            }

            HewanVO hewan = new HewanVO(null,id, namaHewan, usia, berat, tanggalMasuk, null);
            hewanViewModel.createHewan(hewan);
        }
    }
