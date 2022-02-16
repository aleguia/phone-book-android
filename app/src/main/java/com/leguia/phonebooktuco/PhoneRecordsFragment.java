package com.leguia.phonebooktuco;

import static com.leguia.phonebooktuco.R.drawable.recycler_separator;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leguia.phonebooktuco.databinding.FragmentPhoneRecordsBinding;

import java.util.ArrayList;

public class PhoneRecordsFragment extends Fragment {

    private FragmentPhoneRecordsBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentPhoneRecordsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PhoneBookRecyclerAdapter adapter = new PhoneBookRecyclerAdapter();

        binding.recordsRecycler.setAdapter(adapter);
        binding.recordsRecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        DividerItemDecoration decoration = new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(this.getContext().getDrawable(R.drawable.recycler_separator));
        binding.recordsRecycler.addItemDecoration(decoration);

        PhoneBookViewModel viewModel = new ViewModelProvider(requireActivity()).get(PhoneBookViewModel.class);

        viewModel.getList().observe(getViewLifecycleOwner(), phoneBookRecords -> {
            adapter.setRecords(phoneBookRecords);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().invalidateOptionsMenu();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}