package com.polytechnic.astra.ac.id.internak.ViewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.polytechnic.astra.ac.id.internak.API.Repository.HewanRepository;
import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;
import java.util.List;

public class HewanViewModel extends AndroidViewModel {
    private final HewanRepository repository;
    private final LiveData<List<HewanVO>> hewanListData;

    public HewanViewModel(Application application) {
        super(application);
        repository = new HewanRepository();
        hewanListData = repository.getHewanListData();
    }

    public LiveData<List<HewanVO>> getHewanListData() {
        return hewanListData;
    }

    public void loadHewanData(int idKandang) {
        repository.loadHewanData(idKandang);
    }

    public void createHewan(HewanVO hewan) {
        repository.createHewan(hewan);
    }
}
