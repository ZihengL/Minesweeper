package com.example.minesweeper.game.objects;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.minesweeper.MainActivity;
import com.example.minesweeper.R;
import com.example.minesweeper.game.enums.Difficulties;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<Difficulties> {

    private final Difficulties[] difficulties = Difficulties.values();
    private Context context;
    private Dialog dialog;

    public CustomListAdapter(Context context, Dialog dialog) {
        super(context, R.layout.item_settings_list);
        this.context = context;
        this.dialog = dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_settings_list, parent, false);
        }

        TextView difficulty = convertView.findViewById(R.id.item_list_difficulty);
        TextView attributes = convertView.findViewById(R.id.item_list_attributes);

        Difficulties diff = difficulties[position];
        String name = diff.name().toLowerCase(),
                attr = diff.rows + " " + diff.columns + " (" + diff.mines + " mines)";
        difficulty.setText(name);
        attributes.setText(attr);

    //    convertView.setOnClickListener(this);
    //    convertView.setTag(position);

        return convertView;
    }

/*    @Override
    public void onClick(View v) {
        MainActivity main = (MainActivity) context;
        Difficulties difficulty = difficulties[(int) v.getTag()];

        main.setSettings(difficulty);
        main.startGame();
        dialog.dismiss();
    }*/
}
