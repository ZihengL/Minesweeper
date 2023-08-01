package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.minesweeper.game.CustomListView;
import com.example.minesweeper.game.enums.Difficulties;
import com.example.minesweeper.game.objects.CustomListAdapter;
import com.example.minesweeper.game.objects.Difficulty;
import com.example.minesweeper.game.objects.Minesweeper;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private Minesweeper game;
    private boolean gameActive;
    private CustomTimertask timertask;
    private CustomGridLayout gridlayout;
    private ImageButton smileyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Minesweeper(Difficulties.EXPERT);

        // CustomGridLayout
        gridlayout = findViewById(R.id.custom_grid);
        gridlayout.setReferences(this, game);

        // CustomTimerTask
        CustomTimertask.label = findViewById(R.id.label_timer_value);

        // SmileyButton
        smileyButton = (ImageButton) findViewById(R.id.button_smiley);
        smileyButton.setOnClickListener(v -> {
            timertask.stop();
            startGame();
        });

        this.startGame();
    }

    public void setSettings(Difficulties difficulty) {
        game.setDifficulty(difficulty);
    }

    public void setSettings(int rows, int columns) {
        game.setDifficulty(rows, columns);
    }

    public boolean isGameActive() {
        return this.gameActive;
    }

    public void startGame() {
        gameActive = true;
        game.getGridManager().updateGrid();
        gridlayout.setupGrid();
        smileyButton.setBackgroundResource(R.drawable.ic_smiley_normal);
        ((TextView) findViewById(R.id.label_minescount)).setText(String.valueOf(Difficulty.getMines()));
        timertask = new CustomTimertask();
    }

    public void stopGame(boolean isWon) {
        timertask.stop();
        gameActive = false;
        smileyButton.setBackgroundResource(isWon ? R.drawable.ic_smiley_happy : R.drawable.ic_smiley_dead);
        this.openPostgame(getString(isWon ? R.string.win : R.string.lose));
    }

    public void openPostgame(String status) {
        final Dialog dialog = new Dialog(this);
        String time = getString(R.string.stats_time) + "\t" + timertask.getElapsedTime(),
                swept = getString(R.string.stats_swept) + "\t" + game.getGridManager().getSweptCount();

        dialog.setContentView(R.layout.layout_postgame);
        ((TextView) dialog.findViewById(R.id.postgame_status)).setText(status);
        ((TextView) dialog.findViewById(R.id.postgame_time)).setText(time);
        ((TextView) dialog.findViewById(R.id.postgame_swept)).setText(swept);

        ((Button) dialog.findViewById(R.id.button_playagain)).setOnClickListener(v -> {
            this.startGame();
            dialog.dismiss();
        });

        dialog.show();
    }

    // TODO: FINISH THIS LAYOUT_OPTIONS
    public void openSettings(View view) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_settings);
        timertask.stop();

        CustomListView listview = dialog.findViewById(R.id.settings_list);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Difficulty.set(Difficulties.values()[i]);
                startGame();
                dialog.dismiss();
            }
        });

        Pattern pattern = Pattern.compile("[0-9]+");
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                Matcher matcher = pattern.matcher(source);
                if (!matcher.matches())
                    return "";

                return null;
            }
        };

        EditText rowsInput = (EditText) dialog.findViewById(R.id.settings_rows_input);
        EditText colsInput = (EditText) dialog.findViewById(R.id.settings_columns_input);

        rowsInput.setFilters(new InputFilter[]{filter});
        colsInput.setFilters(new InputFilter[]{filter});

        ((Button) dialog.findViewById(R.id.button_save)).setOnClickListener(view1 -> {
            int rows = Integer.parseInt(rowsInput.getText().toString()),
                cols = Integer.parseInt(colsInput.getText().toString());

            Difficulty.set(rows, cols);
            this.startGame();
            dialog.dismiss();
        });

        dialog.setOnCancelListener(dialogInterface -> {
            int elapsedTime = timertask.getElapsedTime();
            timertask = new CustomTimertask(elapsedTime);
        });

        dialog.show();
    }
}