/**
 Main class for Glossario
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Glossario{
    
    public void SearchMethod(JTextField txt,JTextArea txtAr){
        File inFile = new File("glossario.txt");
        String toSearch = txt.getText();
        txtAr.setText("");
        if(toSearch != null && !toSearch.isEmpty()){
            Pattern p = Pattern.compile(toSearch);
            try(BufferedReader br = new BufferedReader(new FileReader(inFile))){
                String line;
                while((line = br.readLine()) != null){
                    Matcher m = p.matcher(line);
                    if(m.find()){
                        txtAr.append(line + System.getProperty("line.separator"));
                    }
                }
            }catch(IOException ioexc){
                ioexc.printStackTrace();
            }
        }
    }
    
    public void InsertMethod(JTextField txt){
        File inFile = new File("glossario.txt");
        String in_name = txt.getText();
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(inFile,true))){
            bw.write(in_name + System.getProperty("line.separator"));
        }catch(IOException ioexc){
            ioexc.printStackTrace();
        }
    }
    
    public void RemoveMethod(JTextField txt){
        File inFile = new File("glossario.txt");
        File outFile = new File("temp.txt");
        try(BufferedReader br = new BufferedReader(new FileReader(inFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(outFile))){
            String line;
            while((line = br.readLine()) != null){
                if(line.trim().equals(txt.getText())){
                    continue;
                }else{
                    bw.write(line + System.getProperty("line.separator"));
                }
            }
            outFile.renameTo(inFile);
        }catch(IOException ioexc){
            ioexc.printStackTrace();
        }
    }
    
    public void SubstituteMethod(JTextField txt){
        File inFile = new File("glossario.txt");
        File outFile = new File("temp.txt");
        try(BufferedReader br = new BufferedReader(new FileReader(inFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(outFile))){
            String line;
            while((line = br.readLine()) != null){
                if(line.trim().equals(txt.getText())){
                    bw.write("ruccgol'" + System.getProperty("line.separator"));
                }else{
                    bw.write(line + System.getProperty("line.separator"));
                }
            }
            outFile.renameTo(inFile);
        }catch(IOException ioexc){
            ioexc.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        JFrame main_window = new JFrame("GLOSSARIO");
        
        JTextField text = new JTextField("");
        text.setBounds(40,20,720,30);
        JTextArea textArea = new JTextArea();
        textArea.setBounds(20,70,760,360);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20,70,760,360);
        JButton search_button = new JButton("Search");
        search_button.setBounds(155,440,100,30);
        JButton substitute_button = new JButton("Change");
        substitute_button.setBounds(285,440,100,30);
        JButton remove_button = new JButton("Remove");
        remove_button.setBounds(415,440,100,30);
        JButton insert_button = new JButton("Insert");
        insert_button.setBounds(545,440,100,30);
        
        main_window.setResizable(false);
        main_window.setLayout(null);
        main_window.add(text);
        //main_window.add(textArea);
        main_window.add(scrollPane);
        main_window.add(search_button);
        main_window.add(remove_button);
        main_window.add(insert_button);
        main_window.add(substitute_button);
        
        search_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Glossario pb = new Glossario();
                pb.SearchMethod(text,textArea);
            }
        });
        
        remove_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	Glossario pb = new Glossario();
                pb.RemoveMethod(text);
            }
        });
        
        insert_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	Glossario pb = new Glossario();
                pb.InsertMethod(text);
            }
        });
        
        substitute_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	Glossario pb = new Glossario();
                pb.SubstituteMethod(text);
            }
        });
            
        main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_window.setSize(800,500);
        main_window.setVisible(true);
    }
    
}
