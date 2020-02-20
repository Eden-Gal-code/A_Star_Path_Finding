import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


enum Box_Selection {
    Start,
    End,
    Wall
}

enum Res {
    Clear,
    Rest
}

enum BeginP {
    Begin,
    Not
}

public class Menu extends JPanel implements ActionListener {
    JMenuBar MB;
    JMenu Box_Select;
    JMenu Restart;
    JMenu Begin;
    JMenuItem Start, End, Wall, Clear, restart, BeginPath;

    Box_Selection BS = Box_Selection.Start;
    Res res;
    BeginP begin= BeginP.Not;

    public Menu() {
        super();
        MB = new JMenuBar();
        Box_Select = new JMenu("Box select");
        Start = new JMenuItem("Start Point");
        End = new JMenuItem("End Point");
        Wall = new JMenuItem("Wall");
        Start.addActionListener(this);
        End.addActionListener(this);
        Wall.addActionListener(this);
        Box_Select.add(Start);
        Box_Select.add(End);
        Box_Select.add(Wall);
        Restart = new JMenu("Restart");
        Clear = new JMenuItem("Clear Board");
        restart = new JMenuItem("Restart Path Finding");
        Clear.addActionListener(this);
        restart.addActionListener(this);
        this.Restart.add(Clear);
        this.Restart.add(restart);
        this.Begin = new JMenu("Begin");
        BeginPath = new JMenuItem("Begin Path Finding");
        BeginPath.addActionListener(this);
        this.Begin.add(BeginPath);
        MB.add(Box_Select);
        MB.add(this.Restart);
        MB.add(Begin);

        this.add(MB);
        this.setLocation(10, 80);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

            BS=Box_Selection.Start;

        if (e.getSource() ==End){
            BS=Box_Selection.End;
        }
        if (e.getSource() ==Wall){
            BS=Box_Selection.Wall;
        }
        if (e.getSource() ==Clear){
            res=Res.Clear;
        }
        if (e.getSource() ==restart){
            res=Res.Rest;
        }
        if(e.getSource()==BeginPath){
            begin=BeginP.Begin;
        }
    }
}
