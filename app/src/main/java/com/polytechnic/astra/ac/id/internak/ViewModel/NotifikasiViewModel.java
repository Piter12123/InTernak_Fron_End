package com.polytechnic.astra.ac.id.internak.ViewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.polytechnic.astra.ac.id.internak.API.Repository.NotifikasiRepository;
import com.polytechnic.astra.ac.id.internak.API.VO.NotifikasiVO;

import java.util.List;

public class NotifikasiViewModel extends AndroidViewModel {
    private final NotifikasiRepository repository;
    private final LiveData<List<NotifikasiVO>> notifikasiListData;

    public NotifikasiViewModel(Application application) {
        super(application);
        repository = new NotifikasiRepository();
        notifikasiListData = repository.getNotifikasiList();
    }

    public LiveData<List<NotifikasiVO>> getNotifikasiListData() {
        return notifikasiListData;
    }
}
