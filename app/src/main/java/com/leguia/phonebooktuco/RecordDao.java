package com.leguia.phonebooktuco;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(PhoneBookRecord record);

    @Query("SELECT * FROM record where first_name like :textToMatch or last_name like :textToMatch or phone_number like :textToMatch  ORDER BY first_name COLLATE NOCASE ASC")
    LiveData<List<PhoneBookRecord>> getAll(String textToMatch);


}
