package com.example.skyadapters4;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * Created by ttlnisoffice on 12/19/17.
 */

public class FileManager {

    private static final int PERMISSIONS_REQUEST_CODE = 1004;
    private int filePickerRequestCode = 1005;

    private Activity a;

    public FileManager(Activity a) {
        this.a = a;
    }

    public void selectImage(int filePickerRequestCode) {
        this.filePickerRequestCode = filePickerRequestCode;
        checkPermissionsAndOpenFilePicker();
    }

    private void checkPermissionsAndOpenFilePicker() {
        String permission = Manifest.permission.READ_EXTERNAL_STORAGE;

        if (ContextCompat.checkSelfPermission(a, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(a, permission)) {
                showError();
            } else {
                ActivityCompat.requestPermissions(a, new String[]{permission}, PERMISSIONS_REQUEST_CODE);
            }
        } else {
            openFilePicker();
        }
    }

    private void openFilePicker() {
        Intent mediaChooser = new Intent(Intent.ACTION_GET_CONTENT);
        mediaChooser.setType("image/*");
        a.startActivityForResult(mediaChooser, filePickerRequestCode);
    }

    private void showError() {
        Toast.makeText(a, "Allow external storage reading", Toast.LENGTH_SHORT).show();
    }
}
