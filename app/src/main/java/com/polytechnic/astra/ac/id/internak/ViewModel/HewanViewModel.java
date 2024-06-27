package com.polytechnic.astra.ac.id.internak.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.polytechnic.astra.ac.id.internak.API.Repository.HewanRepository;
import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;

public class HewanViewModel extends AndroidViewModel {
    private final HewanRepository repository;
    private final LiveData<HewanVO> hewanData;

    public HewanViewModel(Application application) {
        super(application);
        repository = new HewanRepository();
        hewanData = repository.getHewanData();
    }

    public LiveData<HewanVO> getHewanData() {
        return hewanData;
    }

    public void createHewan(HewanVO hewan) {
        repository.createHewan(hewan);
    }
}
