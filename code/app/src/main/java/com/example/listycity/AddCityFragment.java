package com.example.listycity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


public class AddCityFragment extends DialogFragment {
    interface AddCityDialogListener {
        void addCity(City city);

        void updateCity(int position, City city);
    }

    private AddCityDialogListener listener;
    private int position = -1;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof AddCityDialogListener) {
            listener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }


    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null);
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);

        Bundle args = getArguments();
        if (args != null) {
            String cityName = args.getString("name");
            String provinceName = args.getString("province");
            position = args.getInt("position", -1);

            if (cityName != null) editCityName.setText(cityName);
            if (provinceName != null) editProvinceName.setText(provinceName);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        if (args != null) {
            String cityName = args.getString("name");
            String provinceName = args.getString("province");
            position = args.getInt("position", -1);

            if (cityName != null) editCityName.setText(cityName);
            if (provinceName != null) editProvinceName.setText(provinceName);
        }

        return builder
                .setView(view)
                .setTitle(position == -1 ? "Add a city" : "Edit city")
                .setNegativeButton("Cancel", null)
                .setPositiveButton(position == -1 ? "Add" : "Save", (dialog, which) -> {
                    String cityName = editCityName.getText().toString();
                    String provinceName = editProvinceName.getText().toString();
                    City city = new City(cityName, provinceName);

                    if (position == -1) {
                        listener.addCity(city);
                    } else {
                        listener.updateCity(position, city);
                    }
                })
                .create();





    }
}

