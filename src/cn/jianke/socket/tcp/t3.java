package cn.jianke.socket.tcp;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
 
import javax.swing.*;
 
public class t3 extends JFrame{
	
	private static final long serialVersionUID = 1L; 
	
	public t3() {
		setTitle("Hern");
		setBounds(400, 400, 400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JLabel label = new JLabel();
		label.setText("请用鼠标点击");
		label.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {

			}

			@Override
			public void mouseMoved(MouseEvent e) {

				System.out.println("e.getX();"+e.getX());
				System.out.println("e.getY();"+e.getY());
				System.out.println("-----------------------------------------\n\n\n");

			}
		});

		
		
		add(label);
		setVisible(true);
	}
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		t3 test = new t3();
 
	}
 
}
