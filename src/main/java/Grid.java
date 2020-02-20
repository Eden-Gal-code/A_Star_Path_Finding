import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Grid implements MouseMotionListener, MouseListener {
    protected Box[][] grid;
    protected Box Start, End;
    protected int x, y;
    protected Menu menu = new Menu();
    App app;


    public Grid(int x, int y, App app) {
        this.x = x;
        this.y = y;
        this.app = app;
        grid = new Box[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                grid[i][j] = new Box(i, j, 0, 999999);
            }
        }
        Start = grid[0][0];
        End = grid[0][0];


    }

    public void Reset() {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                grid[i][j] = new Box(i, j, 0, 999999);
            }
        }
        Start = grid[0][0];
        End = grid[0][0];
        menu.res = null;
        menu.begin = null;
        menu.BS = Box_Selection.Start;

    }
    public void ResetAndKeep() {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if(grid[i][j].getState()!=2&&grid[i][j]!=Start&&grid[i][j]!=End) {
                    grid[i][j] = new Box(i, j, 0, 999999);
                }
            }
        }
        menu.res = null;
        menu.begin = null;
        menu.BS = Box_Selection.Start;
        End.setValue(9999999);

    }


    public void setStart(Box start) {
        Start = start;
    }

    public void setEnd(Box end) {
        End = end;
    }

    public void draw(Graphics g) {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                grid[i][j].draw(g);
            }
        }
    }


    public void Mouse(MouseEvent e) {
        Point locSc = e.getLocationOnScreen();
        Point locJF = app.JF.getLocation();
        Point locOnArr = new Point((locSc.x - locJF.x) / grid[0][0].Len, ((locSc.y - locJF.y) / grid[0][0].Len) - 8);
        if (locOnArr.x >= 0 && locOnArr.y >= 0) {
            if (menu.BS == Box_Selection.Start) {
                Start.setStartOrEnd("");
                Start.setState(0);
                Start = grid[locOnArr.x][locOnArr.y];
                Start.setStartOrEnd("S ");
                Start.setState(1);
            }
            if (menu.BS == Box_Selection.End) {
                End.setStartOrEnd("");
                End.setState(0);
                End = grid[locOnArr.x][locOnArr.y];
                End.setStartOrEnd("E ");
                End.setState(1);
            }
            if (menu.BS == Box_Selection.Wall) {
                if (grid[locOnArr.x][locOnArr.y].getState() != 1) {
                    grid[locOnArr.x][locOnArr.y].setState(2);
                }
            }
        }
        app.repaint();
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        this.Mouse(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.Mouse(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
