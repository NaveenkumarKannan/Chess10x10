package com.project.naveen.chess10x10.chess10x10empty;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

import com.project.naveen.chess10x10.R;
import com.project.naveen.chess10x10.chess10x10empty.chessCore10x10.cell;
import com.project.naveen.chess10x10.chess10x10empty.chessCore10x10.piece;

import java.util.ArrayList;

public class boardView10x10 extends View
{

    // selected acts as an indicator as to  whether a cell has been selected
    // if so, the next screen event will be the selection of the move to cell
    boolean pieceSelected;
    boolean reset;
    // Yzero is the top point for the board, as in board is placed at [0, Yzero]
    private int fromX, fromY, toX, toY, Yzero, width;
    private chessCore10x10 core;
    public void setCore(chessCore10x10 core)
    {
        if(core != null)
            this.core = core;
    }
    private Resources res;
    private Canvas canvas;

    private int getBoardX(int x)
    {
        //return x * width / 8;
        return x * width / 10;
    }

    private int getBoardY(int y)
    {
        //return y * width / 8 + Yzero;
        return y * width / 10 + Yzero;
    }

    private void drawBoard(cell[][] board) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
        super.invalidate();
        //Drawable boardImg = res.getDrawable(R.drawable.board);
        Drawable boardImg = res.getDrawable(R.drawable.board10x10);
        width =  canvas.getWidth();
        Yzero =  (int)(canvas.getHeight() - width) / 2;
        boardImg.setBounds(0, Yzero, width, width + Yzero);
        boardImg.draw(canvas);
        //for(int x = 0; x < 8; x++)
        for(int x = 0; x < 10; x++)
        {
            //for(int y = 0; y < 8; y++)
            for(int y = 0; y < 10; y++)
            {
                piece piece = board[x][y].getPiece();
                if(piece != null)
                {
                    int id = getResources().getIdentifier(piece.getImageResource(), "drawable", getContext().getPackageName());
                    Drawable pieceBoard = getResources().getDrawable(id);
                    String var = piece.getImageResource();
                    Drawable figure = pieceBoard;
                    //figure.setBounds(getBoardX(x), getBoardY(y), getBoardX(x) + width/8, getBoardY(y) + width /8);
                    figure.setBounds(getBoardX(x), getBoardY(y), getBoardX(x) + width/10, getBoardY(y) + width /10);
                    figure.draw(canvas);
                }
            }
        }
    }

    private void drawAvailableMoves(cell[][] board, int x, int y)
    {
        piece piece = board[x][y].getPiece();
        if(piece != null && piece.getPieceColour() == core.getTurn())
        {
            Drawable selection = res.getDrawable(R.drawable.selected);
            //selection.setBounds(getBoardX(x), getBoardY(y), getBoardX(x) + width/8, getBoardY(y) + width /8);
            selection.setBounds(getBoardX(x), getBoardY(y), getBoardX(x) + width/10, getBoardY(y) + width /10);
            selection.draw(canvas);

            ArrayList<cell> availMoves = piece.getAvailableMoves();
            for(int i = 0; i < availMoves.size(); i ++)
            {
                if(piece.isValidMove(availMoves.get(i)))
                {
                    cell availMove = availMoves.get(i);
                    Drawable circle = res.getDrawable(R.drawable.selectioncircle);
                    //circle.setBounds(getBoardX(availMove.getX()), getBoardY(availMove.getY()), getBoardX(availMove.getX()) + width/8, getBoardY(availMove.getY()) + width /8);
                    circle.setBounds(getBoardX(availMove.getX()), getBoardY(availMove.getY()), getBoardX(availMove.getX()) + width/10, getBoardY(availMove.getY()) + width /10);
                    circle.draw(canvas);
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event.getAction() != MotionEvent.ACTION_DOWN)
            return super.onTouchEvent(event);

        // X and Y have to be integers 0-7
        // so what we need to do for x is x / (full width / 8)
        // y is similar but y - top space / (width of board / 8)
        if(!pieceSelected)
        {
            /*
            fromX = (int)(event.getX() / (width / 8.0));
            fromY = (int)((event.getY() - Yzero) / (width / 8.0));
            if( fromX < 0 || fromX > 7 || fromY < 0 || fromY > 7)
                return true;
            */
            fromX = (int)(event.getX() / (width / 10.0));
            fromY = (int)((event.getY() - Yzero) / (width / 10.0));
            if( fromX < 0 || fromX > 9 || fromY < 0 || fromY > 9)
                return true;
        }
        else
        {
            //toX = (int)(event.getX() / (width / 8.0));
            //toY = (int)((event.getY() - Yzero) / (width / 8.0));
            toX = (int)(event.getX() / (width / 10.0));
            toY = (int)((event.getY() - Yzero) / (width / 10.0));
            // moving to the same place shouldn't be an issue, i don't think
            // TODO: verify this
            //if( toX < 0 || toX > 7 || toY < 0 || toY > 7 || (toX == fromX && toY == fromY));
            if( toX < 0 || toX > 9 || toY < 0 || toY > 9 || (toX == fromX && toY == fromY));
            else
            {
                try
                {
                    core.move(fromX, fromY, toX,toY);
                    pieceSelected = false;
                    return true;
                }catch( Exception ex)
                {
                    pieceSelected = false;
                    return true;
                    // TODO: handle this bizsnatch
                }
            }
        }
        pieceSelected = !pieceSelected;
        return true;
    }

    // Draw Chess board and set up logic connections
    // TODO: probably not here. Set up to and from cell selection
    // this may be related to view available moves.
    @Override
    protected void onDraw(Canvas canvas)
    {
        this.canvas = canvas;
        try {
            this.drawBoard(core.getBoard());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if(pieceSelected)
            drawAvailableMoves(core.getBoard(), fromX, fromY);
    }

    boardView10x10(Context context)
    {
        super(context);

        pieceSelected = false;
        reset = true;
        fromX =-1;
        fromY = -1;
        toX = -1;
        toY = -1;
        res = getResources();

        setFocusable(true);
        setFocusableInTouchMode(true);
    }
}