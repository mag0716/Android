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

    private static final String KEY_MULTIPLE_SELECTABLE = "isMultipleSelectable";

    public static Bundle createArguments(boolean isMultipleSelectable) {
        final Bundle bundle = new Bundle();
        bundle.putBoolean(KEY_MULTIPLE_SELECTABLE, isMultipleSelectable);
        return bundle;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        boolean isMultipleSelectable = getArguments() != null && getArguments().getBoolean(KEY_MULTIPLE_SELECTABLE, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Title")
                .setPositiveButton("OK", null)
                .setNegativeButton("Cancel", null);

        if (isMultipleSelectable) {
            builder.setMultiChoiceItems(R.array.dialog_items, null, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                }
            });
        } else {
            builder.setSingleChoiceItems(R.array.dialog_items, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
        }
        return builder.create();
    }
}
