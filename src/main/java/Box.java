import java.awt.*;

public class Box {

    Point location;
    private int state;//0- empty 1-path 2- wall 3- open 4-closed
    Color[] colors = {Color.white, Color.blue, Color.BLACK, Color.green, Color.red};
    int Len = 15;
    int previousDirectione;
    /*
    1 2 3
    4   5
    6 7 8 -- the direction it came from
     */
    private int value;
    private int G_cost, H_cost;
    private String StartOrEnd = "";

    public void setValue(int value) {
        this.value = value;
    }

    public void setStartOrEnd(String startOrEnd) {
        StartOrEnd = startOrEnd;
    }

    public int getState() {
        return state;
    }

    public int getValue() {
        return value;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setValue() {
        this.value = G_cost + H_cost;
    }

    public Box(int x, int y, int state, int value) {
        location = new Point(x, y);

        this.state = state;
        this.value = value;


    }


    public void setG_cost(int g_cost) {
        G_cost = g_cost;
    }

    public void setH_cost(int h_cost) {
        H_cost = h_cost;
    }

    public int getG_cost() {
        return G_cost;
    }

    public int getH_cost() {
        return H_cost;
    }

    public int getPreviousDirectione() {
        return previousDirectione;
    }

    public void setPreviousDirectione(int previousDirectione) {
        this.previousDirectione = previousDirectione;
    }

    public void draw(Graphics g) {

        g.setColor(colors[state]);
        g.fillRect(location.x * Len, 100 + location.y * Len, Len, Len);
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.drawString(StartOrEnd + "", location.x * Len + 5, 100 + location.y * Len + Len / 2 + 10);
        g.setColor(Color.black);
        g.drawRect(location.x * Len, 100 + location.y * Len, Len, Len);
    }
}
