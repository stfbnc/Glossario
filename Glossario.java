/**
 Main class for Glossario
 */
import classes.GlossMeth;

import java.awt.Font;
//import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
//import javax.swing.text.BadLocationException;
//import javax.swing.text.DefaultHighlighter;
//import javax.swing.text.Utilities;


public class Glossario{
    
    public void GlossGUI(String fn,String s){
        
        JFrame main_window = new JFrame("GLOSSARIO");
        main_window.setContentPane(new JLabel(new ImageIcon("chin_back.jpg")));
        
        JTextField text = new JTextField("");
        text.setBounds(40,20,720,30);
        
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("monospaced",Font.PLAIN,12));
        textArea.setBounds(20,70,760,360);
        textArea.setEditable(false);
        //textArea.setLineWrap(true);
        /*textArea.addMouseListener(new MouseAdapter(){
        	@Override
        	public void mouseClicked(MouseEvent e){
        		if(e.getButton() != MouseEvent.BUTTON1){
        			return;
        		}
        		if(e.getClickCount() != 1){
        			return;
        		}
        		int offset = textArea.viewToModel2D(e.getPoint());
        		DefaultHighlighter highlighter =  (DefaultHighlighter)textArea.getHighlighter();
                DefaultHighlighter.DefaultHighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.red);
                highlighter.setDrawsLayeredHighlights(false);
        		try{
        			int rowStart = Utilities.getRowStart(textArea,offset);
        			int rowEnd = Utilities.getRowEnd(textArea,offset);
        			highlighter.addHighlight(rowStart,rowEnd,painter);
        			//String selectedLine = textArea.getText().substring(rowStart,rowEnd);
        		}catch(BadLocationException e1){
        			e1.printStackTrace();
        		}
        	}
         });*/
        
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
        
        main_window.add(text);
        main_window.add(scrollPane);
        main_window.add(search_button);
        main_window.add(remove_button);
        main_window.add(insert_button);
        main_window.add(substitute_button);
        main_window.getRootPane().setDefaultButton(search_button);
        
        search_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                GlossMeth gm = new GlossMeth();
                gm.SearchMethod(fn,s,text,textArea);
            }
        });
        
        remove_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                GlossMeth gm = new GlossMeth();
                gm.RemoveMethod(fn,s,textArea);
            }
        });
        
        insert_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                GlossMeth gm = new GlossMeth();
                gm.InsertMethod(fn,s);
            }
        });
        
        substitute_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                GlossMeth gm = new GlossMeth();
                gm.SubstituteMethod(fn,s,text,textArea);
            }
        });
        
        main_window.setResizable(false);
        main_window.setLayout(null);
        main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_window.setSize(800,500);
        main_window.setVisible(true);
    }
    
    public static void main(String[] args){
    	String file_name = "glossario.txt";
    	String lsep = " cinita ";
        Glossario gl = new Glossario();
        gl.GlossGUI(file_name,lsep);
    }
    
}
