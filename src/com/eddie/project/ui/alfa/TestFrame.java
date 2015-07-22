package com.eddie.project.ui.alfa;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



//import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.eddie.project.backend.ReversePolishNotation;


public class TestFrame {
	
	

	public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					new GUI();
				}
			});
	}

}

class GUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6400470106767960240L;
	//contentPane
	private JPanel contentPane;
	//top center and bottom
	private JPanel panelInput;
	private JPanel panelOutRPN;
	private JPanel panelOutResult;
	//top
	private JLabel lblInput;
	private JTextField textFieldInput;
	private JButton btnRun;
	//center
	private JLabel lblOutRPNText;
	private JLabel lblOutRPNResult;
	private JButton btnClearText;
	//bottom
	private JLabel lblOutText;
	private JLabel lblOutResult;
	//font
	Font font = new Font("Serif", Font.PLAIN, 20);
	
	public GUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setSize(500, 150);
		init();
		pack();
	}
	
	private  void init(){
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());

		FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
		
		//top
		panelInput = new JPanel(flowLayout);
		panelInput.setSize(200, 50);
		lblInput = new JLabel("Input expression :");
		textFieldInput = new JTextField(20);
		
		btnRun = new JButton("RUN");
		btnRun.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] str = ReversePolishNotation.getResult(noSpaceAtExpression());
				lblOutRPNResult.setText(str[0]);
				lblOutResult.setText(str[1]);
				
			}
		});
		btnClearText = new JButton("Clear");
		btnClearText.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				textFieldInput.setText("");
				lblOutResult.setText("");
				lblOutRPNResult.setText("");
				
			}
		});
		
		lblInput.setFont(font);
		textFieldInput.setFont(font);
		btnRun.setFont(font);
		btnClearText.setFont(font);
		
		panelInput.add(lblInput);
		panelInput.add(textFieldInput);
		panelInput.add(btnRun);
		panelInput.add(btnClearText);
		contentPane.add(panelInput, BorderLayout.NORTH);
		
		//center
		panelOutRPN = new JPanel(flowLayout);
		panelOutRPN.setSize(200, 50);
		lblOutRPNText = new JLabel("Output relative   :");
		lblOutRPNResult = new JLabel();
		lblOutRPNResult.setForeground(Color.magenta);
		
		lblOutRPNText.setFont(font);
		lblOutRPNResult.setFont(font);
		
		panelOutRPN.add(lblOutRPNText);
		panelOutRPN.add(lblOutRPNResult);
		
		contentPane.add(panelOutRPN, BorderLayout.CENTER);
		
		//bottom
		panelOutResult = new JPanel(flowLayout);
		panelOutResult.setSize(200, 50);
		lblOutText = new JLabel("Result evaluate   :");
		lblOutText.setBackground(Color.green);
		lblOutResult = new JLabel();
		lblOutResult.setForeground(Color.green.darker());
		
		lblOutText.setFont(font);
		lblOutResult.setFont(font);
		
		panelOutResult.add(lblOutText);
		panelOutResult.add(lblOutResult);
		contentPane.add(panelOutResult, BorderLayout.SOUTH);
		
	}
	
	private String noSpaceAtExpression(){
		String input = textFieldInput.getText();
		StringBuilder res = new StringBuilder(input);
		boolean wait = true;
		while(wait){
			int index = res.indexOf(" ");
			if(index != -1) res.deleteCharAt(index);
			else wait = false;
		}
		return res.toString();
	}
	
}
