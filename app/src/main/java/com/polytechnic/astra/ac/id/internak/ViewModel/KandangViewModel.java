    package com.polytechnic.astra.ac.id.internak.ViewModel;

    import android.app.Application;

    import androidx.lifecycle.AndroidViewModel;
    import androidx.lifecycle.LiveData;
    import androidx.lifecycle.MutableLiveData;

    import com.polytechnic.astra.ac.id.internak.API.Repository.KandangRepository;
    import com.polytechnic.astra.ac.id.internak.API.VO.HewanVO;
    import com.polytechnic.astra.ac.id.internak.API.VO.KandangVO;

    import java.util.List;

    public class KandangViewModel extends AndroidViewModel {

        private final KandangRepository repository;
        private final LiveData<List<KandangVO>> kandangListData;

        public KandangViewModel(Application application) {
            super(application);
            repository = new KandangRepository();
            kandangListData = repository.getKandangListData();
        }

        public void loadKandangData(int idUser) {
            repository.loadKandangData(idUser);
        }
        public LiveData<List<KandangVO>> getKandangListData() {
            return kandangListData;
        }

        public void createKandang(KandangVO kandang) {
            repository.createKandang(kandang);
        }
        public void updateKandang(KandangVO kandang) {
            repository.updateKandang(kandang);
        }
        public void deleteKandang(Integer idKandang, MutableLiveData<Boolean> deleteResult) {
            repository.deleteKandang(idKandang, deleteResult);
        }
    }
