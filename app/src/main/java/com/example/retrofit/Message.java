package com.example.retrofit;

import android.content.Context;
import android.widget.Toast;

public class Message {
    public static void message(Context context,String data)
    {
        Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
    }
}
