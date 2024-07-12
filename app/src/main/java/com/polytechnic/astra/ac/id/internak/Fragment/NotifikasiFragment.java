package com.polytechnic.astra.ac.id.internak.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.polytechnic.astra.ac.id.internak.Adapter.NotifikasiAdapter;
import com.polytechnic.astra.ac.id.internak.R;
import com.polytechnic.astra.ac.id.internak.ViewModel.NotifikasiViewModel;

public class NotifikasiFragment extends Fragment {

    private NotifikasiViewModel notifikasiViewModel;
    private RecyclerView recyclerView;
    private NotifikasiAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notifikasiViewModel = new ViewModelProvider(this).get(NotifikasiViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifikasi, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_notifikasi);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        notifikasiViewModel.getNotifikasiListData().observe(getViewLifecycleOwner(), notifikasiList -> {
            if (notifikasiList != null && !notifikasiList.isEmpty()) {
                Log.d("NotifikasiFragment", "Data received: " + notifikasiList.size() + " items");
                adapter = new NotifikasiAdapter(notifikasiList);
                recyclerView.setAdapter(adapter);
            } else {
                Log.d("NotifikasiFragment", "No data received");
            }
        });

        return view;
    }
}