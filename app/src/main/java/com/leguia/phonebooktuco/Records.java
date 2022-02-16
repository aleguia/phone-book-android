package com.leguia.phonebooktuco;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Records  {

    private List<PhoneBookRecord> list;

    public Records() {
        this.list = new ArrayList<>();
    }

    private Records (List<PhoneBookRecord> list) {
        this();
        this.list.addAll(list);
        this.sort();
    }

    private void sort() {
        list.sort(new Comparator<PhoneBookRecord>() {
            @Override
            public int compare(PhoneBookRecord a, PhoneBookRecord b) {
                if (a.getFirstName().charAt(0) > b.getFirstName().charAt(0)) {
                    return 1;
                } else if (a.getFirstName().charAt(0) < b.getFirstName().charAt(0)) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
    }

    public PhoneBookRecord get(int at){
        return list.get(at);
    }

    public void add(PhoneBookRecord record){
        list.add(record);
    }

    public void addAll(List<PhoneBookRecord> records) {
        list.addAll(records);
        sort();
    }

    public Records filter(String text) {
       List<PhoneBookRecord> filtered =  list.stream()
                .filter(record -> record.match(text))
//                .sorted()
                .collect(Collectors.toList());

        return new Records(filtered);
    }

    public int size() {
        return list.size();
    }


}
