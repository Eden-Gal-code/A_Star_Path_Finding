import javax.swing.*;
import java.util.ArrayList;

public class A_Star_Path_Finding {
    protected Grid Grid;

    final int LENGTH = 80, WIDTH = 45;
    ArrayList<Box> opened = new ArrayList<Box>();
    ArrayList<Box> closed = new ArrayList<Box>();


    public A_Star_Path_Finding(App a) {
        Grid = new Grid(WIDTH, LENGTH,a);

    }

    public int Shortest_H_cost(Box start, Box end) {
        int H = 0;
        int difX, difY;
        difX = Math.abs((int) (end.getLocation().getX() - start.getLocation().getX()));
        difY = Math.abs((int) (end.getLocation().getY() - start.getLocation().getY()));
        while (difX > 0 || difY > 0) {
            if (difY > 0 && difX > 0) {
                H += 14;
                difY--;
                difX--;
            } else {
                if (difY > 0 || difX > 0) {
                    H += 10;
                    difX--;
                    difY--;

                }
            }
        }
        return H;
    }

    public void CalcNeighbors(Box box, JPanel JP, Thread t) {
        // goes over every neighbor and calculates its F cost.
        //
        int y = (int) box.getLocation().getY();
        int x = (int) box.getLocation().getX();
        //up
        if ( y - 1 >= 0) {
            this.boxchecks(y - 1, x, box, 7);
        }
        //down
        if (y + 1 < WIDTH) {
            this.boxchecks(y + 1, x, box, 2);
        }
        //right
        if (x + 1 < LENGTH) {
            this.boxchecks(y, x + 1, box, 4);
        }
        //left
        if (x - 1 >= 0) {
            this.boxchecks(y, x - 1, box, 5);

        }
        //up-left
        if (x - 1 >= 0 && y - 1 >= 0) {
            this.boxchecksD(y - 1, x - 1, box, 8);
        }
        //up-right
        if (x + 1 < LENGTH && y - 1 >= 0) {
            this.boxchecksD(y - 1, x + 1, box, 6);
        }
        //right down
        if (x + 1 < LENGTH && y + 1 < WIDTH) {
            this.boxchecksD(y + 1, x + 1, box, 1);
        }
        //down left
        if (x - 1 >= 0 && y + 1 < WIDTH) {
            this.boxchecksD(y + 1, x - 1, box, 3);
        }
        if (box.getState() != 1) {
            box.setState(4);
        }
        opened.remove(box);
        closed.add(box);

        JP.repaint();
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            System.out.println("Sleep Error");
        }

    }

    public Box Find_Min_Value_in_Opened() {
        Box min = opened.get(0);
        for (int i = 1; i < opened.size(); i++) {
            if (opened.get(i).getValue() < min.getValue()) {
                min = opened.get(i);

            }
            if (opened.get(i).getValue() == min.getValue()) {
                if (opened.get(i).getH_cost() < min.getH_cost()) {
                    min = opened.get(i);
                }
            }
        }
        return min;
    }


    public void Find_Path(Box start, Box end, JPanel JP, Thread t) {
        start.setValue(Shortest_H_cost(start, end));
        start.setH_cost(Shortest_H_cost(start, end));
        opened.add(start);
        Box current = start;
        while (current.getH_cost() != 0 && !opened.isEmpty()) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                System.out.println("Sleep Error");
            }
            CalcNeighbors(current, JP, t);
            if (opened.isEmpty() == false) {
                current = Find_Min_Value_in_Opened();

            }
            JP.repaint();

        }
        Get_Back_to_the_Start(end, start, JP);
        JP.repaint();

    }

    public void Get_Back_to_the_Start(Box end, Box start, JPanel JP) {
        Box current = end;


        while (current != start && current.previousDirectione != 0) {
            current.setState(1);
            current = helpWithDirection(current, current.previousDirectione);
            JP.repaint();

        }

    }

    public Box helpWithDirection(Box current, int dir) {
        int x = current.location.x;
        int y = current.location.y;
        if (dir == 1) {
            return Grid.grid[x - 1][y - 1];
        } else if (dir == 2) {
            return Grid.grid[x][y - 1];
        } else if (dir == 3) {
            return Grid.grid[x + 1][y - 1];
        } else if (dir == 4) {

            return Grid.grid[x - 1][y];
        } else if (dir == 5) {
            return Grid.grid[x + 1][y];
        } else if (dir == 6) {
            return Grid.grid[x - 1][y + 1];
        } else if (dir == 7) {
            return Grid.grid[x ][y+1];
        } else {
            return Grid.grid[x + 1][y + 1];
        }

    }

    public void boxchecks(int x, int y, Box box, int pD) {
        //checks if the neighbor's value is less than the calculated and if its not closed.
        //if it is not  it updates the box checked, adds it to opened and set the state to 3
        //sets the previous direction.
        int tempV = Shortest_H_cost(Grid.grid[y][x], Grid.End) + box.getG_cost() + 10;
        if (tempV < Grid.grid[y][x].getValue() && Grid.grid[y][x].getState() != 4 && Grid.grid[y][x].getState() != 2) {
            Grid.grid[y][x].setH_cost(Shortest_H_cost(Grid.grid[y][x], Grid.End));
            Grid.grid[y][x].setG_cost(box.getG_cost() + 10);
            Grid.grid[y][x].setValue();
            opened.add(Grid.grid[y][x]);
            Grid.grid[y][x].setState(3);
            Grid.grid[y][x].setPreviousDirectione(pD);
        }

    }

    public void boxchecksD(int x, int y, Box box, int pD) {
        //checks if the neighbor's value is less than the calculated and if its not closed.
        //if it is not  it updates the box checked, adds it to opened and set the state to 3
        //sets the previous direction.
        int tempV = Shortest_H_cost(Grid.grid[y][x], Grid.End) + box.getG_cost() + 14;
        if (tempV < Grid.grid[y][x].getValue() && Grid.grid[y][x].getState() != 4 && Grid.grid[y][x].getState() != 2) {
            Grid.grid[y][x].setH_cost(Shortest_H_cost(Grid.grid[y][x], Grid.End));
            Grid.grid[y][x].setG_cost(box.getG_cost() + 14);
            Grid.grid[y][x].setValue();
            opened.add(Grid.grid[y][x]);
            Grid.grid[y][x].setState(3);
            Grid.grid[y][x].setPreviousDirectione(pD);

        }

    }


}
