package com.example.wrathspectre.computercontrol;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ConnectFragment extends Fragment {
    Button connectButton, disconnectbutton;
    EditText nameInput, ipInput, portInput;

    String ip_number, port_number;

    ConnectionManagement instance;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.connect, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameInput = view.findViewById(R.id.name_input);
        ipInput = view.findViewById(R.id.ip_input);
        portInput = view.findViewById(R.id.port_input);

        connectButton = view.findViewById(R.id.connect_button);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ip_number = ipInput.getText().toString();
                port_number = portInput.getText().toString();

                instance = ConnectionManagement.getInstance(ip_number, Integer.valueOf(port_number));
                instance.start();
            }
        });

        disconnectbutton = view.findViewById(R.id.disconnect_button);
        disconnectbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instance.getMessage("KIATSO\n");
            }
        });
    }
}
