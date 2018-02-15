package com.danilo.skyadapters;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;

/**
 * Created by ttlnisoffice on 11/20/17.
 */

public class AlertAdapter {

    private Activity a;
    private SharedPreferences sharedPreferences;

    public AlertAdapter(Activity a) {
        this.a = a;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(a);
    }

    public void buildAlertDialog(String title, String message, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(a);
        alertBuilder.setTitle(title);
        alertBuilder.setMessage(message);
        alertBuilder.setCancelable(false);
        //alertBuilder.setIcon(R.drawable.ic_alert);
        alertBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertBuilder.setPositiveButton("ok", onClickListener);

        //add additional views here

        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

    public void buildRadioDialog(final String sharedPref, final String alertTitle,
                                 final CharSequence[] radioButtonsText, final boolean restartActivity,
                                 final Integer resultCode) {

        final String savedTheme = PreferenceManager.getDefaultSharedPreferences(a).getString(sharedPref,
                radioButtonsText[0].toString());

        int selectedInt = 0;

        for (int i = 0; i < radioButtonsText.length; i++){
            if (radioButtonsText[i].toString().contains(savedTheme)) {
                selectedInt = i;
            }
        }
        final int[] selectedRadioBtn = {selectedInt};

        //final int[] selectedRadioBtn = {sharedPreferences.getInt("RadioBtnPosition", 0)}; //will give you null exception if you put null

        final String[] themeToSave = {radioButtonsText[selectedRadioBtn[0]].toString()};
        final AlertDialog.Builder builder = new AlertDialog.Builder(a);
        builder .setTitle(alertTitle)
                .setSingleChoiceItems(radioButtonsText, selectedRadioBtn[0],
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                themeToSave[0] = radioButtonsText[i].toString();
                                selectedRadioBtn[0] = i;
                            }
                        })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (!themeToSave[0].contains(savedTheme)){
                            sharedPreferences.edit().putString(sharedPref, themeToSave[0]).apply();
                            sharedPreferences.edit().putInt("RadioBtnPosition", selectedRadioBtn[0]).apply();
                            if (resultCode != null) {
                                a.setResult(resultCode);
                            }
                            if (restartActivity) {
                                a.recreate();
                            }
                        }
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public String getResult(String sharedPref) {
        return sharedPreferences.getString(sharedPref, "Error");
    }

}
