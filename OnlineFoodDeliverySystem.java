import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OnlineFoodDeliverySystem {

    static CardLayout cl = new CardLayout();
    static JPanel main = new JPanel(cl);

    static JTextArea cartArea;
    static JLabel totalLabel;

    static JTextField nameField, phoneField, addressField;

    static ArrayList<String> cart = new ArrayList<>();
    static int total = 0;

    public static void main(String[] args) {

        JFrame f = new JFrame("Online Food Delivery System");
        f.setSize(1000,700);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* ---------------- LOGIN PAGE ---------------- */

        JPanel login = new JPanel(null);
        login.setBackground(new Color(20,30,60));

        JLabel t1 = new JLabel("ONLINE FOOD DELIVERY SYSTEM");
        t1.setBounds(220,80,550,35);
        t1.setForeground(Color.WHITE);
        t1.setFont(new Font("Arial",Font.BOLD,28));
        login.add(t1);

        JLabel u1 = new JLabel("Username");
        u1.setBounds(300,220,100,25);
        u1.setForeground(Color.WHITE);
        login.add(u1);

        JTextField user = new JTextField();
        user.setBounds(420,220,220,32);
        login.add(user);

        JLabel p1 = new JLabel("Password");
        p1.setBounds(300,280,100,25);
        p1.setForeground(Color.WHITE);
        login.add(p1);

        JPasswordField pass = new JPasswordField();
        pass.setBounds(420,280,220,32);
        login.add(pass);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(430,360,140,40);
        loginBtn.setBackground(new Color(52,152,219));
        loginBtn.setForeground(Color.WHITE);
        login.add(loginBtn);

        /* ---------------- MENU PAGE ---------------- */

        JPanel menu = new JPanel(null);
        menu.setBackground(Color.WHITE);

        JLabel t2 = new JLabel("FOOD MENU");
        t2.setBounds(390,10,250,35);
        t2.setFont(new Font("Arial",Font.BOLD,28));
        menu.add(t2);

        String items[] = {
                "🍔 Burger ₹99","🍕 Pizza ₹249","🍝 Pasta ₹149",
                "🥤 Coffee ₹99","🍟 Fries ₹79","🥤 Shake ₹129",
                "🥪 Sandwich ₹89","🍜 Noodles ₹159"
        };

        int price[] = {99,249,149,99,79,129,89,159};

        int x = 80, y = 70;

        for(int i=0;i<items.length;i++){

            JButton b = new JButton(items[i]);
            b.setBounds(x,y,260,42);
            b.setBackground(new Color(52,152,219));
            b.setForeground(Color.WHITE);
            menu.add(b);

            String item = items[i];
            int p = price[i];

            b.addActionListener(e -> addItem(item,p));

            x += 300;

            if(x > 650){
                x = 80;
                y += 60;
            }
        }

        JLabel c = new JLabel("YOUR CART");
        c.setBounds(410,290,180,30);
        c.setFont(new Font("Arial",Font.BOLD,24));
        menu.add(c);

        cartArea = new JTextArea();
        JScrollPane sp = new JScrollPane(cartArea);
        sp.setBounds(300,330,380,140);
        menu.add(sp);

        totalLabel = new JLabel("Total = ₹0");
        totalLabel.setBounds(430,480,180,25);
        totalLabel.setFont(new Font("Arial",Font.BOLD,20));
        menu.add(totalLabel);

        JButton next = new JButton("Checkout");
        next.setBounds(420,530,150,38);
        next.setBackground(new Color(46,204,113));
        next.setForeground(Color.WHITE);
        menu.add(next);

        /* ---------------- CHECKOUT PAGE ---------------- */

        JPanel check = new JPanel(null);
        check.setBackground(new Color(245,245,245));

        JLabel t3 = new JLabel("CHECKOUT");
        t3.setBounds(390,40,250,35);
        t3.setFont(new Font("Arial",Font.BOLD,28));
        check.add(t3);

        JLabel n1 = new JLabel("Name");
        n1.setBounds(250,170,100,25);
        check.add(n1);

        nameField = new JTextField();
        nameField.setBounds(380,170,250,32);
        check.add(nameField);

        JLabel ph1 = new JLabel("Phone");
        ph1.setBounds(250,230,100,25);
        check.add(ph1);

        phoneField = new JTextField();
        phoneField.setBounds(380,230,250,32);
        check.add(phoneField);

        JLabel ad1 = new JLabel("Address");
        ad1.setBounds(250,290,100,25);
        check.add(ad1);

        addressField = new JTextField();
        addressField.setBounds(380,290,250,32);
        check.add(addressField);

        JButton order = new JButton("Place Order");
        order.setBounds(420,390,160,40);
        order.setBackground(new Color(231,76,60));
        order.setForeground(Color.WHITE);
        check.add(order);

        /* ---------------- ADD PANEL ---------------- */

        main.add(login,"login");
        main.add(menu,"menu");
        main.add(check,"check");

        f.add(main);
        f.setVisible(true);

        /* ---------------- ACTION ---------------- */

        loginBtn.addActionListener(e -> cl.show(main,"menu"));

        next.addActionListener(e -> {
            nameField.setText(user.getText());
            cl.show(main,"check");
        });

        order.addActionListener(e -> receipt(f));
    }

    static void addItem(String item,int price){

        cart.add(item);
        total += price;

        cartArea.setText("");

        for(String i : cart)
            cartArea.append(i + "\n");

        totalLabel.setText("Total = ₹" + total);
    }

    static void receipt(JFrame f){

        int gst = (int)(total * 0.05);
        int grand = total + gst + 30;

        String bill = "======= RECEIPT =======\n\n";
        bill += "Customer : " + nameField.getText() + "\n";
        bill += "Phone    : " + phoneField.getText() + "\n";
        bill += "Address  : " + addressField.getText() + "\n\n";

        for(String i : cart)
            bill += i + "\n";

        bill += "\nSubtotal : ₹" + total;
        bill += "\nGST      : ₹" + gst;
        bill += "\nDelivery : ₹30";
        bill += "\nTotal    : ₹" + grand;
        bill += "\n\nOrder Confirmed ✅";

        JOptionPane.showMessageDialog(f,bill);
    }
}
