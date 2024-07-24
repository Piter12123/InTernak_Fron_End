package com.polytechnic.astra.ac.id.internak.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.polytechnic.astra.ac.id.internak.API.Repository.HewanSakitRepository;
import com.polytechnic.astra.ac.id.internak.API.Repository.PerkembanganRepository;
import com.polytechnic.astra.ac.id.internak.API.VO.HewanSakitVO;
import com.polytechnic.astra.ac.id.internak.API.VO.PerkembanganVO;

import java.util.List;

public class PerkembanganViewModel extends AndroidViewModel{
    private final PerkembanganRepository repository;
    private final LiveData<List<PerkembanganVO>> perkembanganListData;

    public PerkembanganViewModel(Application application) {
        super(application);
        repository = new PerkembanganRepository();
        perkembanganListData = repository.getPerkembanganListData();
    }

    public LiveData<List<PerkembanganVO>> getPerkembanganListData() {
        return perkembanganListData;
    }

    public void loadHewanData(int idTmo) {
        repository.loadHewanData(idTmo);
    }
}
