package chatzy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class client extends JFrame implements ActionListener {

    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea ta1;

    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;

    client() {

        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 470, 65);
        add(p1);

        // back arrow icon
        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image img2 = img1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        JLabel l1 = new JLabel(img3);
        l1.setBounds(5, 13, 30, 30);
        p1.add(l1);

        // to close the program
        l1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }

        });

        //user profile pic
        ImageIcon img4 = new ImageIcon(ClassLoader.getSystemResource("icons/client.png"));
        Image img5 = img4.getImage().getScaledInstance(57, 57, Image.SCALE_DEFAULT);
        ImageIcon img6 = new ImageIcon(img5);
        JLabel l2 = new JLabel(img6);
        l2.setBounds(40, 4, 57, 57);
        p1.add(l2);

        //user name
        JLabel l3 = new JLabel("User2");
        l3.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        l3.setForeground(Color.WHITE);
        l3.setBounds(110, 15, 100, 18);
        p1.add(l3);

        //user status
        JLabel l4 = new JLabel("Online");
        l4.setFont(new Font("SAN_SERIF", Font.PLAIN, 13));
        l4.setForeground(Color.WHITE);
        l4.setBounds(110, 35, 100, 20);
        p1.add(l4);

        //text area where messages are shown
        ta1 = new JTextArea();
        ta1.setBounds(7, 70, 457, 475);
        ta1.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        ta1.setEditable(false);
        ta1.setLineWrap(true);
        ta1.setWrapStyleWord(true);

        add(ta1);

        // where we can write the message we want to send
        t1 = new JTextField();
        t1.setBounds(7, 555, 330, 40);
        t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(t1);

        // send button which sends the text
        b1 = new JButton("Send");
        b1.setBounds(345, 555, 118, 40);
        b1.setBackground(new Color(7, 94, 84));
        b1.setForeground(Color.white);
        b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        b1.addActionListener(this);
        add(b1);

        //frame
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setSize(470, 600);
        setLocation(720, 20);
        setUndecorated(true);
        setVisible(true);

    }

    //defining the method how we read and get our messages
    public void actionPerformed(ActionEvent ae) {
        try {
            String out = t1.getText();
            ta1.setText(ta1.getText() + "\n\t\t\t" + out);
            dout.writeUTF(out);
            t1.setText("");
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {

        new client().setVisible(true);

        try {

            InetAddress ip= InetAddress.getLocalHost();  //gets the ip address of your local host (ipv4)

            // sending and receiving the messages using socket programming
            String msginput = "";
            s = new Socket(ip, 111); // NOTE: the port number in client and server class should be same otherwise it wouldnt work
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            while (!msginput.equals("exit")) {
                msginput = din.readUTF();
                ta1.setText(ta1.getText() + "\n User1: " + msginput);
            }
        } catch (Exception e) {
        }

    }
}
