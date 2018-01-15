package com.danilo.skyadapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.danilo.skyadapters.recycler.SkyDrawerRecycler;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {

    private Activity a;
    private int color;
    private Menu menu;
    private SkyDrawerRecycler rv;
    private Intent[] intents;


    public RvAdapter(final Activity a, Intent[] intents,
                     RecyclerView.LayoutManager layoutManager, int color, Integer drawerRvID) {
        this.a              = a;
        this.intents        = intents;
        this.color          = color;

        rv = (SkyDrawerRecycler) a.findViewById(drawerRvID);

        this.menu = new PopupMenu(a, null).getMenu();
        a.getMenuInflater().inflate(rv.getMenuID(), this.menu);

        rv.setLayoutManager(rv.getLayoutManager());
        if (rv.getDrawerCustomRow() == 0) {
            new ErrorNotifications().displayError(a, "Add app:drawerCustomRow= into SkyDrawerRecycler xml");
        } else {
            rv.setAdapter(this);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt;
        ImageView img;

        ViewHolder(View itemView) {
            super(itemView);
            ViewGroup vg = (ViewGroup) a.getLayoutInflater().inflate(rv.getDrawerCustomRow(),null);
            for (int i = 0; i < vg.getChildCount(); i++) {
                if (vg.getChildAt(i) instanceof TextView) {
                    txt = (TextView) itemView.findViewById(vg.getChildAt(i).getId());
                }
                if (vg.getChildAt(i) instanceof ImageView) {
                    img = (ImageView) itemView.findViewById(vg.getChildAt(i).getId());
                }
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(a).inflate(rv.getDrawerCustomRow(), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final MenuItem menuItem = menu.getItem(position);
        holder.txt.setText(menuItem.getTitle());
        if (holder.img != null) holder.img.setImageDrawable(menuItem.getIcon());

        holder.itemView.setBackgroundColor(color);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.startActivity(intents[holder.getAdapterPosition()]);
            }
        });
        /*if (intents != null) {
            a.startActivity(intents[position]);
        } else {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (activities[holder.getAdapterPosition()].getSimpleName().contains("Setting")) {
                        a.startActivityForResult(new Intent(a, activities[holder.getAdapterPosition()]), 0);
                    } else {
                        a.startActivity(new Intent(a, activities[holder.getAdapterPosition()]));
                    }
                }
            });
        }*/
    }

    @Override
    public int getItemCount() {
        return menu.size();
    }
}
