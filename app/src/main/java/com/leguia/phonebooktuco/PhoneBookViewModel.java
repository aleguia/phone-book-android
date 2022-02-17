package com.leguia.phonebooktuco;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PhoneBookViewModel extends AndroidViewModel {
    private RecordRepository mRepository ;
    private final LiveData<List<PhoneBookRecord>> liveRecords;
    private MutableLiveData<Void> succesfulAdded = new MutableLiveData<>();
    private MutableLiveData<AddContactValidationError> error = new MutableLiveData<>();

    private String firstName = "", lastName = "", phoneNumber = "";

    public LiveData<List<PhoneBookRecord>> getList()  { return liveRecords; }

    public PhoneBookViewModel (Application application) {
        super(application);
        mRepository = new RecordRepository(application);
        liveRecords = mRepository.getAll();
    }

    public LiveData<AddContactValidationError> error() {
        return error;
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

        mRepository.insert(record);
        succesfulAdded.setValue(null);
        succesfulAdded = new MutableLiveData<>();
    }

    public void reset(){
        error.setValue(AddContactValidationError.NO_ERROR);
        succesfulAdded = new MutableLiveData<>();
    }

    private boolean validateRecord(String firstName, String lastName, String phoneNumber){
        if (firstName.isEmpty()){
            error.setValue(AddContactValidationError.FIRSTNAME_EMPTY);
            return false;
        } else if (lastName.isEmpty()){
            error.setValue(AddContactValidationError.LASTNAME_EMPTY);
            return false;
        }else if (phoneNumber.isEmpty()) {
            error.setValue(AddContactValidationError.PHONE_NUMBER_EMPTY);
            return false;
        }

        error.setValue(AddContactValidationError.NO_ERROR);
        return true;
    }

    public void filter(String text) {
        System.out.println("Trying to filter: " + text);

        mRepository.filter(text);







    }
}
