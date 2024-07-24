package com.polytechnic.astra.ac.id.internak.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.polytechnic.astra.ac.id.internak.API.Repository.HewanSakitRepository;
import com.polytechnic.astra.ac.id.internak.API.VO.HewanSakitVO;
import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;

import java.util.List;

public class HewanSakitViewModel extends ViewModel {
    private HewanSakitRepository hewansakitRepository;
    private LiveData<List<HewanSakitVO>> hewansakitListData;

    public HewanSakitViewModel() {
        hewansakitRepository = new HewanSakitRepository();
        hewansakitListData = hewansakitRepository.getHewansakitListData();
    }

    public LiveData<List<HewanSakitVO>> getHewanSakitListData() {
        return hewansakitListData;
    }

    public void loadHewanSakitData(int idUser) {
        hewansakitRepository.loadHewanData(idUser);
    }
    public void createHewan(HewanSakitVO hewan) {
        hewansakitRepository.createHewan(hewan);
    }

    public LiveData<List<Integer>> getHewanIdsByUserId(int idUser) {
        return hewansakitRepository.getHewanIdsByUserId(idUser);
    }
}
