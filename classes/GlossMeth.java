/**
 Main class for Glossario
 */
package classes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GlossMeth{
    
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
        win.getRootPane().setDefaultButton(button2);
        button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	if((text1.getText() != null && !text1.getText().isEmpty()) && (text2.getText() != null && !text2.getText().isEmpty())){
            		File inFile = new File(file_name);
                    try(BufferedWriter bw = new BufferedWriter(new FileWriter(inFile,true))){
                        bw.write(text1.getText().trim() + lsep + text2.getText().trim() + System.getProperty("line.separator"));
                        win.dispatchEvent(new WindowEvent(win,WindowEvent.WINDOW_CLOSING));
            			JOptionPane.showMessageDialog(null,"New term correctly inserted!","",JOptionPane.INFORMATION_MESSAGE);
                    }catch(IOException ioexc){
                        ioexc.printStackTrace();
                    }
            	}else{
            		JOptionPane.showMessageDialog(null,"Missing inserted text!","",JOptionPane.ERROR_MESSAGE);
            	}
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
        		JOptionPane.showMessageDialog(null,"Term correctly removed!","",JOptionPane.INFORMATION_MESSAGE);
        	}catch(IOException ioexc){
        		ioexc.printStackTrace();
        	}
    	}else{
    		JOptionPane.showMessageDialog(null,"Text not selected!","",JOptionPane.ERROR_MESSAGE);
    	}
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
            win.getRootPane().setDefaultButton(button2);
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
                			JOptionPane.showMessageDialog(null,"Term correctly changed!","",JOptionPane.INFORMATION_MESSAGE);
                		}catch(IOException ioexc){
                			ioexc.printStackTrace();
                		}
                	}else{
                		JOptionPane.showMessageDialog(null,"Missing inserted text!","",JOptionPane.ERROR_MESSAGE);
                	}
                }
            });
            button1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                	win.dispatchEvent(new WindowEvent(win,WindowEvent.WINDOW_CLOSING));
                }
            });
    	}else{
    		JOptionPane.showMessageDialog(null,"Text not selected!","",JOptionPane.ERROR_MESSAGE);
    	}
    }
    
}
