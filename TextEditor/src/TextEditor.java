import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    //declaring properties of texteditor
    JFrame frame;
    JMenuBar menuBar;
    JTextArea textArea;
    //adding menu to menubar
    JMenu file,edit;
    //menu item for file
    JMenuItem newFile, openFile, saveFile;
    //menu item for edit
    JMenuItem cut, copy, paste, selectAll, close;
    TextEditor() {
        //initialize the frame
        frame = new JFrame();

        //initialize the menubar
        menuBar = new JMenuBar();
        //initalize the textArea
        textArea = new JTextArea();

        file = new JMenu("File");
        edit = new JMenu("Edit");
        //intialize the file item
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        //adding actionlistener to file menu item
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);


        //adding file menu item to file
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //initiaize the edit item
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        //adding action listener to edit menu item
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);


        //adding edit menu item to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);


        menuBar.add(file);
        menuBar.add(edit);
        //adding menubar to frame
        frame.setJMenuBar(menuBar);
        //create content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        panel.add(textArea, BorderLayout.CENTER);
        //create scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scroll pane to panel
        panel.add(scrollPane);
        //add panel to frame
        frame.add(panel);

        //giving window default height and width
        // x=0 and y=0 means window appear in top left where pixel coordinate is 0 ,0
        frame.setBounds(0, 0, 400, 400);
        //setting title to window
        frame.setTitle("Text Editor");
        //setting frame visible
        frame.setVisible(true);
        ;
        //turning off default layout of class
        frame.setLayout(null);
    }

        @Override
        public void actionPerformed(ActionEvent actionEvent){
            if(actionEvent.getSource()==cut){
                //perform cut operation
                textArea.cut();
            }
            if(actionEvent.getSource()==copy){
                //perform copy operation
                textArea.copy();
            }
            if(actionEvent.getSource()==paste){
                //perform paste operation
                textArea.paste();
            }
            if(actionEvent.getSource()==selectAll){
                //perform select all operation
                textArea.selectAll();
            }
            if(actionEvent.getSource()==close){
                //perform close editor operation
                System.exit(0);
            }
            if(actionEvent.getSource()==openFile){
                //open file choose
                JFileChooser fileChooser = new JFileChooser("C:");
                int chooseOption = fileChooser.showOpenDialog(null);
                //if we have clicked open button
                if(chooseOption==JFileChooser.APPROVE_OPTION){
                    //getting selected file
                    File file = fileChooser.getSelectedFile();
                    //get path of selected file
                    String filePath = file.getPath();
                    try{
                        //intialize file reader
                        FileReader fileReader = new FileReader(filePath);
                        //initialize buffer reader
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        String intermediate = "" , output = "";
                        while((intermediate=bufferedReader.readLine())!=null){
                            output +=intermediate+"\n";
                        }
                        //set output string to textarea
                        textArea.setText(output);
                    }
                    catch (FileNotFoundException fileNotFoundException){
                        fileNotFoundException.printStackTrace();
                    }
                    catch (IOException ioException){
                        ioException.printStackTrace();
                    }

                }
            }
            if(actionEvent.getSource()==saveFile){
                //pick file picker
                JFileChooser fileChooser = new JFileChooser("C:");
                //get choose option from filechoose
                int chooseOption = fileChooser.showSaveDialog(null);
                //if we have clicked save button
                if(chooseOption==JFileChooser.APPROVE_OPTION){
                    //create a new file with choosen directory path and file name
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+"txt");
                    try{
                        //initialize file writer
                        FileWriter fileWriter = new FileWriter(file);
                        //initialize buffer writer
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        //write content of text area to file
                        textArea.write(bufferedWriter);
                        bufferedWriter.close();
                    }
                    catch (IOException ioException){
                        ioException.printStackTrace();
                    }
                }

            }
            if(actionEvent.getSource()==newFile){
                TextEditor newTextEditor = new TextEditor();
            }
        }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}