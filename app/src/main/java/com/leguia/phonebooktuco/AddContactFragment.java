package com.leguia.phonebooktuco;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.leguia.phonebooktuco.databinding.FragmentAddContactBinding;

public class AddContactFragment extends Fragment {

    private FragmentAddContactBinding binding;
    private PhoneBookViewModel viewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAddContactBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(PhoneBookViewModel.class);
        viewModel.succes().observe(getViewLifecycleOwner(), event -> {
                            NavHostFragment.findNavController(AddContactFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
        });
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().invalidateOptionsMenu();
    }

    @Override
    public void onPause() {
        super.onPause();
        View view = requireActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewModel.add(binding.firstnameInput.getEditText().getText().toString(),
                                binding.lastnameInput.getEditText().getText().toString(),
                                binding.phoneInput.getEditText().getText().toString());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}