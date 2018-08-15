/**
 Main class for Glossario
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.text.BadLocationException;
//import javax.swing.text.Utilities;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Glossario{
    
    public void SearchMethod(String file_name,String lsep,JTextField txt,JTextArea txtAr){
        File inFile = new File(file_name);
        String toSearch = txt.getText().trim().toLowerCase();
        txtAr.setText("");
        if(toSearch != null && !toSearch.isEmpty()){
            Pattern p = Pattern.compile(toSearch);
            try(BufferedReader br = new BufferedReader(new FileReader(inFile))){
                String line;
                while((line = br.readLine()) != null){
                    Matcher m = p.matcher(line.toLowerCase());
                    if(m.find()){
                    	String cin = line.split(lsep)[0];
                    	String ita = line.split(lsep)[1];
                        txtAr.append(String.format("%-25s\t--\t%-50s",cin,ita) + System.getProperty("line.separator"));
                    }
                }
            }catch(IOException ioexc){
                ioexc.printStackTrace();
            }
        }
    }
    
    public void InsertMethod(String file_name,String lsep){
    	JFrame win = new JFrame("Insert");
    	win.setSize(500,400);
    	win.setVisible(true);
    	win.setResizable(false);
    	win.setLayout(null);
    	JLabel label1 = new JLabel("Chinese");
    	label1.setBounds(20,20,50,30);
    	JTextField text1 = new JTextField("");
    	text1.setBounds(20,60,460,100);
    	JLabel label2 = new JLabel("Italian");
    	label2.setBounds(20,180,50,30);
    	JTextField text2 = new JTextField("");
    	text2.setBounds(20,220,460,100);
    	JButton button1 = new JButton("Cancel");
        button1.setBounds(135,350,100,30);
        JButton button2 = new JButton("Save");
        button2.setBounds(265,350,100,30);
        win.add(label1);
        win.add(text1);
        win.add(label2);
        win.add(text2);
        win.add(button1);
        win.add(button2);
        button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	if((text1.getText() != null && !text1.getText().isEmpty()) && (text2.getText() != null && !text2.getText().isEmpty())){
            		File inFile = new File(file_name);
                    try(BufferedWriter bw = new BufferedWriter(new FileWriter(inFile,true))){
                        bw.write(text1.getText().trim() + lsep + text2.getText().trim() + System.getProperty("line.separator"));
                        win.dispatchEvent(new WindowEvent(win,WindowEvent.WINDOW_CLOSING));
            			//message di riuscita inserzione
                    }catch(IOException ioexc){
                        ioexc.printStackTrace();
                    }
            	}//else messagebox con scritto text not inserted
            }
        });
        button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	win.dispatchEvent(new WindowEvent(win,WindowEvent.WINDOW_CLOSING));
            }
        });
    }
    
    public void RemoveMethod(String file_name,String lsep,JTextArea txtAr){
    	String lsel = txtAr.getSelectedText();
    	if(lsel != null){
    		String chin_txt = lsel.split("--")[0].trim();
        	String it_txt = lsel.split("--")[1].trim();
        	File inFile = new File(file_name);
        	File outFile = new File("temp.txt");
        	try(BufferedReader br = new BufferedReader(new FileReader(inFile));
        		BufferedWriter bw = new BufferedWriter(new FileWriter(outFile))){
        		String line;
        		while((line = br.readLine()) != null){
        			if(line.trim().equals(chin_txt + lsep + it_txt)){
        				continue;
        			}else{
        				bw.write(line + System.getProperty("line.separator"));
        			}
        		}
        		outFile.renameTo(inFile);
        		//message di riuscita
        	}catch(IOException ioexc){
        		ioexc.printStackTrace();
        	}
    	}//else messagebox dicendo di selezionare prima il testo
    }
    
    public void SubstituteMethod(String file_name,String lsep,JTextField txt,JTextArea txtAr){
    	String lsel = txtAr.getSelectedText();
    	if(lsel != null){
    		String chin_txt = lsel.split("--")[0].trim();
        	String it_txt = lsel.split("--")[1].trim();
    		JFrame win = new JFrame("Change");
        	win.setSize(500,400);
        	win.setVisible(true);
        	win.setResizable(false);
        	win.setLayout(null);
        	JLabel label1 = new JLabel("Chinese");
        	label1.setBounds(20,20,50,30);
        	JTextField text1 = new JTextField(chin_txt);
        	text1.setBounds(20,60,460,100);
        	JLabel label2 = new JLabel("Italian");
        	label2.setBounds(20,180,50,30);
        	JTextField text2 = new JTextField(it_txt);
        	text2.setBounds(20,220,460,100);
        	JButton button1 = new JButton("Cancel");
            button1.setBounds(135,350,100,30);
            JButton button2 = new JButton("Save");
            button2.setBounds(265,350,100,30);
            win.add(label1);
            win.add(text1);
            win.add(label2);
            win.add(text2);
            win.add(button1);
            win.add(button2);
            button2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                	if((text1.getText() != null && !text1.getText().isEmpty()) && (text2.getText() != null && !text2.getText().isEmpty())){
                		File inFile = new File(file_name);
                		File outFile = new File("temp.txt");
                		try(BufferedReader br = new BufferedReader(new FileReader(inFile));
                			BufferedWriter bw = new BufferedWriter(new FileWriter(outFile))){
                			String line;
                			while((line = br.readLine()) != null){
                				if(line.trim().equals(chin_txt + lsep + it_txt)){
                					bw.write(text1.getText() + lsep + text2.getText() + System.getProperty("line.separator"));
                				}else{
                					bw.write(line + System.getProperty("line.separator"));
                				}
                			}
                			outFile.renameTo(inFile);
                			win.dispatchEvent(new WindowEvent(win,WindowEvent.WINDOW_CLOSING));
                			//message di riuscita sostituzione
                		}catch(IOException ioexc){
                			ioexc.printStackTrace();
                		}
                	}//else messagebox con scritto text not inserted
                }
            });
            button1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                	win.dispatchEvent(new WindowEvent(win,WindowEvent.WINDOW_CLOSING));
                }
            });
    	}
    }
    
    public static void main(String[] args){
    	
    	//setting variables
    	String file_name = "glossario.txt";
    	String lsep = " cinita ";
    	
        JFrame main_window = new JFrame("GLOSSARIO");
        
        JTextField text = new JTextField("");
        text.setBounds(40,20,720,30);
        
        JTextArea textArea = new JTextArea();
        textArea.setBounds(20,70,760,360);
        textArea.setEditable(false);
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
               try{
                  int rowStart = Utilities.getRowStart(textArea,offset);
                  int rowEnd = Utilities.getRowEnd(textArea,offset);
                  String selectedLine = textArea.getText().substring(rowStart,rowEnd);
                  text.setText(selectedLine.split("--")[0].trim() + selectedLine.split("--")[1].trim());
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
        
        main_window.setResizable(false);
        main_window.setLayout(null);
        main_window.add(text);
        main_window.add(scrollPane);
        main_window.add(search_button);
        main_window.add(remove_button);
        main_window.add(insert_button);
        main_window.add(substitute_button);
        
        search_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Glossario gl = new Glossario();
                gl.SearchMethod(file_name,lsep,text,textArea);
            }
        });
        
        remove_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	Glossario gl = new Glossario();
                gl.RemoveMethod(file_name,lsep,textArea);
            }
        });
        
        insert_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	Glossario gl = new Glossario();
                gl.InsertMethod(file_name,lsep);
            }
        });
        
        substitute_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	Glossario gl = new Glossario();
                gl.SubstituteMethod(file_name,lsep,text,textArea);
            }
        });
            
        main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_window.setSize(800,500);
        main_window.setVisible(true);
    }
    
}

//cercare tutto lowercase
//bind search button con enter
//icona
//inserire uno sfondo
//bottoni colorati