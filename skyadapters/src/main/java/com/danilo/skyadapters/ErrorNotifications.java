package com.danilo.skyadapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by ttlnisoffice on 1/15/18.
 */

public class ErrorNotifications {

    public void displayError(Context c, String message) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(c);
        alertBuilder.setTitle("Error");
        alertBuilder.setMessage(message);
        alertBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        AlertDialog alert = alertBuilder.create();
        alert.show();
    }
}
