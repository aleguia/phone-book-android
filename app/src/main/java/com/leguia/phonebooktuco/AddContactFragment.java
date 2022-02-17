package com.leguia.phonebooktuco;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAddContactBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(PhoneBookViewModel.class);
        viewModel.succes().observe(getViewLifecycleOwner(), event ->
                NavHostFragment.findNavController(AddContactFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment));
        viewModel.error().observe(getViewLifecycleOwner(), error -> {
            switch (error) {
                case FIRSTNAME_EMPTY:
                    binding.firstnameInput.setError(error.getMessage());
                    binding.lastnameInput.setError("");
                    binding.phoneInput.setError("");
                    break;
                case LASTNAME_EMPTY:
                    binding.firstnameInput.setError("");
                    binding.lastnameInput.setError(error.getMessage());
                    binding.phoneInput.setError("");
                    break;
                case PHONE_NUMBER_EMPTY:
                    binding.firstnameInput.setError("");
                    binding.lastnameInput.setError("");
                    binding.phoneInput.setError(error.getMessage());
                    break;
                default:
                    binding.firstnameInput.setError("");
                    binding.lastnameInput.setError("");
                    binding.phoneInput.setError("");
            }
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
        viewModel.reset();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(view1 -> viewModel.add(
                binding.firstnameInput.getEditText().getText().toString(),
                binding.lastnameInput.getEditText().getText().toString(),
                binding.phoneInput.getEditText().getText().toString()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}