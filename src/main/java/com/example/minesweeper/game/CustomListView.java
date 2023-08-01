package com.example.minesweeper.game;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.minesweeper.MainActivity;
import com.example.minesweeper.game.enums.Difficulties;

import java.util.ArrayList;

public class CustomListView extends ListView {

    public CustomListView(Context context) {
        super(context);
        this.init();
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.init();
    }

    private void init() {
        ArrayList<String> diffs = new ArrayList<>();

        for (Difficulties diff : Difficulties.values())
            diffs.add(diff.toString());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, diffs);
        this.setAdapter(adapter);
    }

    public Difficulties getSelected() {
        return Difficulties.values()[this.getSelectedItemPosition()];
    }
}
