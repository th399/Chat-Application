package chat;

import javax.swing.*;

import utils.Login;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Client
{
	private String hostname;
	private int port;
	private PrintWriter writer;
	private JFrame frame;
	private JButton sendButton;
	private JTextArea textArea;
	private JTextField textField;
	private JPanel panel;
	public Client(String hostname, int port)
	{
		this.hostname = hostname;
		this.port = port;
		exe();
	}
	
	public void exe()
	{
		try
		{
			Socket socket = new Socket(hostname, port);
			System.out.println("Connected to the chat server");
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			OutputStream output = socket.getOutputStream();
			writer = new PrintWriter(output,true);
			frame = new JFrame(Login.currentUser.getName());
			sendButton = new JButton("Send");
			textArea = new JTextArea();
			textArea.setEditable(false);
			textField = new JTextField(20);
			panel = new JPanel();
			panel.add(textField);
			panel.add(sendButton);
			frame.add(textArea);
			frame.add(BorderLayout.SOUTH,panel);
			frame.setSize(400,400);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ChatListener listener = new ChatListener(textField,writer,Login.currentUser.getName());
			sendButton.addActionListener(listener);
			textField.addActionListener(listener);
			String str = reader.readLine();
	        while(!str.equals("End")){
	            textArea.append(str + "\n" );
	        }
	        textArea.append("Client Signing Off");
	        socket.close();
	        Thread.sleep(1000);
	        System.exit(0);
			
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
        } catch (InterruptedException ex) {
        	System.out.println("Interrupted Error: " + ex.getMessage());
        }
	}
}
class ChatListener implements ActionListener{
	JTextField text ;
	PrintWriter writer;
	String username;
	public ChatListener(JTextField text,PrintWriter writer,String username){
		this.text = text;
	    this.writer = writer;
	    this.username = username;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String str  = text.getText();
		writer.println(username+" : "+str);
		text.setText("");
		}
}
