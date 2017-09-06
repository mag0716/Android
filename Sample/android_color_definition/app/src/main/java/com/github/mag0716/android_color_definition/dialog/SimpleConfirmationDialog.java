package com.github.mag0716.android_color_definition.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.github.mag0716.android_color_definition.R;

/**
 * Created by mag0716 on 2017/09/06.
 */

public class SimpleConfirmationDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle("Title")
                .setSingleChoiceItems(R.array.dialog_items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton("OK", null)
                .setNegativeButton("Cancel", null)
                .create();
    }
}
