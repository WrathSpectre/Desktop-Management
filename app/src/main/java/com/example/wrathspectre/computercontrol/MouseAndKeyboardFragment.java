package com.example.wrathspectre.computercontrol;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class MouseAndKeyboardFragment extends Fragment{
    ConnectionManagement instance = ConnectionManagement.getInstance("192.168,1,132", 8080);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mouse_and_keyboard_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int action = motionEvent.getAction();
                switch (action & MotionEvent.ACTION_MASK) {

                    //Click action
                    case MotionEvent.ACTION_DOWN: {
                        instance.getMessage(Float.toString(motionEvent.getX())+"\n");
                        Log.d("D", Float.toString(motionEvent.getX()));
                        Log.d("D", Float.toString(motionEvent.getY()));
                        break;
                    }

                    //Move action
                    case MotionEvent.ACTION_MOVE: {
                        Log.d("D", Float.toString(motionEvent.getX()));
                        Log.d("D", Float.toString(motionEvent.getY()));
                        break;
                    }
                }
                return true;

            }
        });
    }

    private void addTouchListener() {

    }
}

