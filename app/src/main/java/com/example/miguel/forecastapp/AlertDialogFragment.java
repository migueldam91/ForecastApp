package com.example.miguel.forecastapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by 21443251 on 04/12/2015.
 */
public class AlertDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle("Oops, sorry...")
                .setMessage("Try again")
                .setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        return dialog;

    }
}
