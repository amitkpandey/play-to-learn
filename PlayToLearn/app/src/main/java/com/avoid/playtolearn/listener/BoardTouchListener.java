package com.avoid.playtolearn.listener;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import com.avoid.playtolearn.util.Controller;
import com.avoid.playtolearn.widget.BoardTileButton;
import com.avoid.playtolearn.widget.BoardTileLayout;

import java.io.Serializable;

public class BoardTouchListener implements View.OnTouchListener, Serializable {
    private Context context;

    private float mDownX;
    private float mDownY;
    private final float SCROLL_THRESHOLD = 10;
    private boolean isOnClick;

    public BoardTouchListener(Context context) {
        this.context = context;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                isOnClick = true;
                break;
            case MotionEvent.ACTION_UP:
                if (isOnClick) {
                    BoardTileLayout boardTileLayout;
                    if (v instanceof BoardTileLayout) {
                        boardTileLayout = (BoardTileLayout) v;
                        if (boardTileLayout.getBoardTile().getQuestion() != null) {
                            Controller.showQuestionDialog(context, boardTileLayout.getBoardTile().getQuestion().getName(),
                                    boardTileLayout.getBoardTile().getQuestion().getDescription());
                        }
                    } else if (v instanceof BoardTileButton) {
                        boardTileLayout = (BoardTileLayout) v.getParent();
                        Controller.showQuestionDialog(context, boardTileLayout.getBoardTile().getQuestion().getName(),
                                boardTileLayout.getBoardTile().getQuestion().getDescription());
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (v instanceof BoardTileButton) {
                    if (isOnClick && (Math.abs(mDownX - event.getX()) > SCROLL_THRESHOLD || Math.abs(mDownY - event.getY()) > SCROLL_THRESHOLD)) {
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(null, shadowBuilder, v, 0);
                        isOnClick = false;
                    }
                }
                break;
            default:
                break;
        }

        return true;
    }
}
