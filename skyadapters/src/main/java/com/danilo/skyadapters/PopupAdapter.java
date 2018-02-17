package com.danilo.skyadapters;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;


/**
 * Created by ttlnisoffice on 11/20/17.
 */

public class PopupAdapter {

    private PopupMenu popup;

    public PopupAdapter(Context c, Button button) {
        popup = new PopupMenu(c, button);
    }

    public PopupAdapter(Context c, ImageButton imageBtn) {
        popup = new PopupMenu(c, imageBtn);
    }

    public PopupMenu buildPopup(int popup_menu_xml, final PopupClickListener onItemClick) {
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(popup_menu_xml, popup.getMenu());
        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                onItemClick.onItemClick(item.getTitle().toString());
                return true;
            }
        });
        popup.show();//showing popup menu
        return popup;
    }

    public interface PopupClickListener {
        void onItemClick(String popupTitle);
    }
}
