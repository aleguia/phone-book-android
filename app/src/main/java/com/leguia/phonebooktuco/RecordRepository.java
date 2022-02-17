package com.leguia.phonebooktuco;

import android.app.Application;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.List;
import java.util.stream.Collectors;

public class RecordRepository {
    private RecordDao mRecordDao;
    private LiveData<List<PhoneBookRecord>> fullList;
    private MutableLiveData<String> query;

    public RecordRepository(Application app) {
        RecordDatabase db = RecordDatabase.getDatabase(app);
        mRecordDao = db.recordDao();
        query = new MutableLiveData<>();


        fullList = Transformations.switchMap(query, text -> mRecordDao.getAll(text));
    }

    LiveData<List<PhoneBookRecord>> getAll() {
        filter("");
        return fullList;
    }

    void insert(PhoneBookRecord record) {
        RecordDatabase.databaseWriteExecutor.execute(() -> mRecordDao.insert(record));
    }

    void filter(String text) {
        String newText = "%" + text + "%";
        query.setValue(newText);
    }


}
