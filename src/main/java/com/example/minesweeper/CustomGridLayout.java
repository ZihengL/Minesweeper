package com.example.minesweeper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.example.minesweeper.game.objects.Cell;
import com.example.minesweeper.game.objects.GridManager;
import com.example.minesweeper.game.objects.Minesweeper;

import java.util.logging.Handler;

public class CustomGridLayout extends GridLayout {

    private MainActivity main;
    private Minesweeper game;
    private GridManager manager;
    private Cell[] grid;

    // CONSTRUCTORS

    public CustomGridLayout(Context context) {
        super(context);
    }

    public CustomGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomGridLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    // GETTERS & SETTERS

    public void setReferences(MainActivity main, Minesweeper game) {
        this.main = main;
        this.game = game;
        this.manager = this.game.getGridManager();
    }

    public void setupGrid() {
        this.grid = manager.getGrid1D();
        this.removeAllViews();
        this.setRowCount(manager.rows());
        this.setColumnCount(manager.columns());

        for (Cell cell : grid) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_cell, this, false);
            this.setView((Button) view, cell);
            this.addView((Button) view);
        }
    }

    private void setView(Button button, Cell cell) {
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.rowSpec = GridLayout.spec(cell.x);
        params.columnSpec = GridLayout.spec(cell.y);
        button.setLayoutParams(params);

        button.setOnClickListener(v -> {
            int result = game.sweepCell(cell);

            if (!main.isGameActive() || result == Cell.ERR_VALUE)
                return;
            else if (result == Cell.MINE_VALUE) {
                this.refreshAllCells();
                button.setBackground(getRes(R.drawable.ic_mine_r));
                main.stopGame(false);
            } else {
                if (result == Cell.DEF_VALUE)
                    this.refreshAllCells();
                else
                    this.refreshCell(button, cell);

                if (game.isWon())
                    main.stopGame(true);
            }
        });

        button.setOnLongClickListener(v -> {
            if (!main.isGameActive() || !cell.isHidden())
                return false;
            else {
                TextView countlabel = (TextView) main.findViewById(R.id.label_minescount);
                int currentcount = Integer.parseInt(countlabel.getText().toString());

                cell.switchMarking();
                this.refreshCell(button, cell);
                countlabel.setText(String.valueOf(cell.isMarked() ? currentcount - 1 : currentcount + 1));
            }

            return true;
        });
    }

    private void refreshAllCells() {
        for (int i = 0; i < grid.length; i++)
            this.refreshCell((Button) this.getChildAt(i), grid[i]);
    }

    private void refreshCell(Button button, Cell cell) {
        int id, value = cell.getValue();

        if (cell.isHidden())
            id = cell.isMarked() ? R.drawable.ic_marked : R.drawable.ic_hidden;
        else if (value == Cell.MINE_VALUE)
            id = R.drawable.ic_mine_b;
        else {
            button.setText(value == Cell.DEF_VALUE ? "" : String.valueOf(value));
            id = R.drawable.ic_sweeped;
        }

        button.setBackground(getRes(id));
    }

    private Drawable getRes(int id) {
        return ResourcesCompat.getDrawable(getResources(), id, null);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }
}
