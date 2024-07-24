package com.polytechnic.astra.ac.id.internak.ViewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.polytechnic.astra.ac.id.internak.API.Repository.HewanRepository;
import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;
import java.util.List;

public class HewanViewModel extends AndroidViewModel {
    private final HewanRepository repository;
    private final LiveData<List<HewanVO>> hewanListData;
    private final LiveData<List<Integer>> kandangListData;

    public HewanViewModel(Application application) {
        super(application);
        repository = new HewanRepository();
        hewanListData = repository.getHewanListData();
        kandangListData = repository.getKandangListData();
    }

    public LiveData<List<HewanVO>> getHewanListData() {
        return hewanListData;
    }

    public void loadHewanData(int idUser) {
        repository.loadHewanData(idUser);
    }

    public void createHewan(HewanVO hewan) {
        repository.createHewan(hewan);
    }

    public void updateHewan(HewanVO hewan) {
        repository.updateHewan(hewan);
    }
    public void deleteHewan(Integer idHewan, MutableLiveData<Boolean> deleteResult) {
        repository.deleteHewan(idHewan, deleteResult);
    }
    public void loadKandangData(int userId) {
        repository.loadKandangData(userId);
    }

    public LiveData<List<Integer>> getKandangListData() {
        return kandangListData;
    }
}
