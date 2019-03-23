package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

import controleur.Main;
import modele.Modele;
public class VueConnexion extends JFrame implements ActionListener,KeyListener {
	
	private JPanel unPanel= new JPanel();
	private JButton btAnnuler=new JButton("Annuler");
	private JButton btSeConnecter=new JButton("Se Connecter");
	private JTextField txtLogin=new JTextField();
	private JPasswordField txtMdp=new JPasswordField();
	
	public VueConnexion(){
		this.setTitle("Administration des interventions");
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setBounds(200,200,600,400);
		this.getContentPane().setBackground(Color.GRAY);
		
		//construction du pannel
		this.unPanel.setBounds(50, 200, 500, 150);
		this.unPanel.setBackground(Color.WHITE);
		this.unPanel.setLayout(new GridLayout(3, 2));//3ligne 2colonne
		this.unPanel.add(new JLabel("Login :"));
		this.unPanel.add(txtLogin);
		this.unPanel.add(new JLabel("MDP :"));
		this.unPanel.add(txtMdp);
		this.unPanel.add(btAnnuler);
		this.unPanel.add(btSeConnecter);
		this.add(this.unPanel);

		this.btAnnuler.setBackground(Color.WHITE);
		this.btSeConnecter.setBackground(Color.WHITE);
		this.txtLogin.setBackground(Color.WHITE);
		this.txtMdp.setBackground(Color.WHITE);
		
		
		this.btAnnuler.addActionListener(this);
		this.btSeConnecter.addActionListener(this);
		this.txtLogin.addKeyListener(this);
		this.txtMdp.addKeyListener(this);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==this.btAnnuler){
			this.txtLogin.setText("");
			this.txtMdp.setText("");
		}else if (e.getSource()==this.btSeConnecter){
			traitement();
		}
	}
	
	public void traitement(){
		try{
			String login=this.txtLogin.getText();
			String mdp=new String (this.txtMdp.getPassword());
			//verification des identifiant dans la BDD
			String droits=Modele.verifConnexion(login, mdp);
			if(droits.equals("")){
				JOptionPane.showMessageDialog(this, "Veuillez verifier vos identifiant de connexion");
				
			}else {
				JOptionPane.showMessageDialog(this, "Bienvenue\nVos droits sont :"+droits);
				Main.rendreVisible(false);
			}
		}catch(NullPointerException exp){
			int retour = JOptionPane.showConfirmDialog(this, "Impossible de se connecter au serveur\n Voulez vous reessayer ? ");
			if(retour==0) {
				traitement();
			}
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyChar()==KeyEvent.VK_ENTER){
			traitement();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
