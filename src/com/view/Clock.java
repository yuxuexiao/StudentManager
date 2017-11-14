package com.view;  

import java.awt.BorderLayout;  
import java.awt.Color;  
import java.awt.Font;  
import java.awt.Graphics;  
import java.util.Calendar;  
import java.util.GregorianCalendar;  
import java.util.TimerTask;  

import javax.swing.*;  

/** 
 * ʱ���� 
 */  
public class Clock extends JPanel {  
    // ��ʱ�ӵ����  
    private paintPanel clock = new paintPanel();  
    // ��ʱ��  
    private java.util.Timer timer = new java.util.Timer();  
    // ��ʾʱ���label  
    JLabel messageLabel = new JLabel("", SwingConstants.CENTER);  

    public Clock() {  
        
        setVisible(true);  
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
//         this.setLocationRelativeTo(null);  
//        setSize(400, 400);  

        add(clock);  
        messageLabel.setForeground(Color.RED);  
        messageLabel.setFont(new Font("Courier", Font.BOLD, 18));  
        add(messageLabel, BorderLayout.SOUTH);  

        // ��ʱ��ִ������  
        timer.schedule(new TimerTask() {  
            @Override  
            public void run() {  
                clock.setCurrentTime();// ����Ϊ��ǰʱ��  
                messageLabel.setText(clock.getHour() + ":" + clock.getMinute() + ":" + clock.getSecond() + '\n');  
                repaint();  
            }  
        }, 0, 1000);  

    }  

//    public static void main(String[] args) {  
//        new Clock();  
//    }  

    public class paintPanel extends JPanel {  
        private int hour, minute, second;  

        public paintPanel() {  
            setCurrentTime();  
        }  

        // ����ʱ��Ϊ��ǰʱ��  
        public  void setCurrentTime() {  
            Calendar calendar = new GregorianCalendar();  
            hour = calendar.get(Calendar.HOUR_OF_DAY);  
            minute = calendar.get(Calendar.MINUTE);  
            second = calendar.get(Calendar.SECOND);  
        }  

        @Override  
        public void paintComponent(Graphics g) {  
            super.paintComponent(g);  
            int xCenter = getWidth() / 2;  
            int yCenter = getHeight() / 2;  

            // ����뾶  
            int radius = (int) (Math.min(this.getWidth(), this.getHeight()) * 0.8 * 0.5);  
            // ��Բ  
            g.drawOval(xCenter - radius, yCenter - radius, radius * 2, radius * 2);  

            // ����������ʾ������  
            g.drawString("12", xCenter - 6, yCenter - radius + 12);  
            g.drawString("3", xCenter + radius - 12, yCenter + 4);  
            g.drawString("6", xCenter - 4, yCenter + radius - 8);  
            g.drawString("9", xCenter - radius + 4, yCenter + 6);  

            // ��ʱ�롢���롢����  
            g.drawLine(xCenter, yCenter, (int) (xCenter + radius * 0.8 * Math.sin(second * 2 * Math.PI / 60)), (int) (yCenter - radius * 0.8 * Math.cos(second * 2 * Math.PI / 60)));  
            g.drawLine(xCenter, yCenter, (int) (xCenter + radius * 0.6 * Math.sin(minute * 2 * Math.PI / 60)), (int) (yCenter - radius * 0.6 * Math.cos(minute * 2 * Math.PI / 60)));  
            g.drawLine(xCenter, yCenter, (int) (xCenter + radius * 0.4 * Math.sin((hour + minute / 60.0) * 2 * Math.PI / 12)), (int) (yCenter - radius * 0.4  
                    * Math.cos((hour + minute / 60.0) * 2 * Math.PI / 12)));  
        }  

        public int getHour() {  
            return hour;  
        }  

        public int getMinute() {  
            return minute;  
        }  

        public int getSecond() {  
            return second;  
        }  
    }  
}  