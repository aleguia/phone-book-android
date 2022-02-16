package com.leguia.phonebooktuco;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PhoneBookViewModel extends ViewModel {
    private final Records fullList = new Records();
    private Records filteredList = new Records();
    private final MutableLiveData<Records> liveRecords = new MutableLiveData<>(fullList);
    private MutableLiveData<Void> succesfulAdded = new MutableLiveData<>();

    public LiveData<Records> getList()  { return liveRecords; }

    public void tempSet() {

        fullList.addAll(Arrays.asList(
                new PhoneBookRecord("micaela", "leguia", "999-999-9999"),
                new PhoneBookRecord("fernando", "leguia", "999-999-9999"),
                new PhoneBookRecord("katia", "torres", "999-999-9999")
        ));
        liveRecords.postValue(fullList);
    }

    public LiveData<Void> succes(){
        return succesfulAdded;
    }

    public void add(String firstName, String lastName, String phoneNumber) {

        if (!validateRecord(firstName,lastName,phoneNumber)) {
            return;
        }

        PhoneBookRecord record = new PhoneBookRecord(firstName,
                lastName,
                phoneNumber);
        fullList.add(record);
        liveRecords.setValue(fullList);
        succesfulAdded.setValue(null);
        succesfulAdded = new MutableLiveData<>();
    }

    private boolean validateRecord(String firstName, String lastName, String phoneNumber){
        return true;
    }

    public void filter(String text) {
        System.out.println("Trying to filter: " + text);

        if (text.isEmpty()) {
            liveRecords.setValue(fullList);
        } else {
            filteredList = liveRecords.getValue().filter(text);

            liveRecords.setValue(filteredList);
        }





    }
}
