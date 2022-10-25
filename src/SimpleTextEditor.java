import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

class SimpleTextEditor extends JFrame implements ActionListener {
    JFrame frame;
    JTextArea textArea;

    SimpleTextEditor(){
        // created the frame
        frame = new JFrame("Simple Text Editor");
        //created the textarea
        textArea=new JTextArea();
        //We are giving dimensions to our frame
        frame.setSize(800,800);
        //adding textarea to the frame
        frame.add(textArea);
        frame.setVisible(true);

        //creating menu bar
        JMenuBar menubar = new JMenuBar();

        //creating menu's (File Menu and Edit Menu)

        JMenu FileMenu = new JMenu("File Menu");
        JMenu EditMenu = new JMenu("Edit Menu");

        // adding these to menubar
        menubar.add(FileMenu);
        menubar.add(EditMenu);
        //creating menu items for file menu
        JMenuItem Open = new JMenuItem("Open File");
        JMenuItem Save = new JMenuItem("Save File");
        JMenuItem Print = new JMenuItem("Print File");
        JMenuItem New = new JMenuItem("New File");
        //adding them to file menu
        FileMenu.add(Open);
        FileMenu.add(Save);
        FileMenu.add(Print);
        FileMenu.add(New);
        //adding actiolisteners to the File Menu items
        Save.addActionListener(this);
        Open.addActionListener(this);
        Print.addActionListener(this);
        New.addActionListener(this);
        //creating items to add in Edit menu
        JMenuItem Cut = new JMenuItem("Cut");
        JMenuItem Copy = new JMenuItem("Copy");
        JMenuItem Paste = new JMenuItem("Paste");
        JMenuItem Close = new JMenuItem("Close");
        //adding them to the Edit Menu
        EditMenu.add(Cut);
        EditMenu.add(Copy);
        EditMenu.add(Paste);
        EditMenu.add(Close);
        //adding the functionality to Edit Menu items
        Cut.addActionListener(this);
        Copy.addActionListener(this);
        Paste.addActionListener(this);
        Close.addActionListener(this);
        //adding the menubar to frame
        frame.setJMenuBar(menubar);
        frame.show();


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args)
    {
        SimpleTextEditor mainWindow = new SimpleTextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String s= e.getActionCommand();
        if(s=="Cut")
        {
            textArea.cut();
        }
        else if(s=="Copy")
        {
            textArea.copy();
        }
        else if(s=="Paste")
        {
            textArea.paste();
        }
        else if(s=="Save File")
        {
            JFileChooser jFileChooser= new JFileChooser("D:");
            int ans=jFileChooser.showOpenDialog(null);
            if(ans==jFileChooser.APPROVE_OPTION)
            {
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter(file,false));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writer.write(textArea.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writer.flush();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(s=="Print File")
        {
            try {
                textArea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(s=="Open File")
        {
            JFileChooser jFileChooser=new JFileChooser("C");
            int ans = jFileChooser.showOpenDialog(null);
            if(ans==JFileChooser.APPROVE_OPTION)
            {
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                try{
                    String s1="",s2="";
                    BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
                    s2=bufferedReader.readLine();
                    while((s1=bufferedReader.readLine())!=null)
                    {
                        s2+=s1+"\n";

                    }
                    textArea.setText(s2);
                }catch (IOException ex)
                {
                    throw new RuntimeException(ex);
                }

            }
        }
        else if(s=="New File")
        {
            textArea.setText("");
        }
        else if(s=="Close")
        {
            frame.setVisible(false);
        }
    }
}