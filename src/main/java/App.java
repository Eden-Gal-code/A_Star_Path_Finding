import javax.swing.*;
import java.awt.*;

public class App extends JPanel implements Runnable {
    JFrame JF = new JFrame("A* Path Finding");
    Thread t = new Thread(this);
    A_Star_Path_Finding PF;


    public static void main(String[] args) {
        App app = new App();

    }

    public App() {
        super();
        PF = new A_Star_Path_Finding(this);

        this.add(PF.Grid.menu);
        this.addMouseListener(PF.Grid);
        this.addMouseMotionListener(PF.Grid);
        JF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JF.add(this);
        JF.setSize(1200, 800);
        JF.setResizable(false);
        JF.setUndecorated(false);
        JF.setVisible(true);


        t.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.BOLD, 50));
        g.drawString("A* Path Finding", 50, 50);
        PF.Grid.draw(g);
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                System.out.println("Sleep Error");
            }
            if(PF.Grid.menu.begin==BeginP.Begin) {
                PF.Find_Path(PF.Grid.Start, PF.Grid.End, this, t);
            }
            if(PF.Grid.menu.res==Res.Clear){
                PF.Grid.Reset();
                PF.opened.clear();
                PF.closed.clear();
               PF.opened.add( PF.Grid.Start);
            }
            if(PF.Grid.menu.res==Res.Rest){
                PF.Grid.ResetAndKeep();
                PF.opened.clear();
                PF.closed.clear();

            }

            this.repaint();

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                System.out.println("Sleep Error");
            }
        }
    }
}
