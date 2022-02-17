package com.leguia.phonebooktuco;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;


public class PhoneBookRecyclerAdapter extends RecyclerView.Adapter<PhoneBookRecyclerAdapter.ViewHolder> {

    private List<PhoneBookRecord> records = new ArrayList<>();

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView firstName;
        private final TextView lastName;
        private final TextView phoneNumber;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            firstName = (TextView) view.findViewById(R.id.firstname);
            lastName = (TextView) view.findViewById(R.id.lastname);
            phoneNumber = (TextView) view.findViewById(R.id.phone);
        }

        public TextView getFirstName() {
            return firstName;
        }

        public TextView getLastName() {
            return lastName;
        }

        public TextView getPhoneNumber() {
            return phoneNumber;
        }
    }

    public PhoneBookRecyclerAdapter() {    }

    public void setRecords(List<PhoneBookRecord> records) {
        this.records = records;
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.phone_book_record, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        PhoneBookRecord item = records.get(position);

        viewHolder.getFirstName().setText(item.getFirstName());
        viewHolder.getLastName().setText(item.getLastName());
        viewHolder.getPhoneNumber().setText(item.getPhoneNumber());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (records == null ) {
            return 0;
        }
        return records.size();
    }
}

