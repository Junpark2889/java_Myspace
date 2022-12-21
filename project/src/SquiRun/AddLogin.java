package SquiRun;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AddLogin extends JFrame implements ActionListener {
	Button lgBtn = new Button("Login");
	Button signBtn = new Button("Signup");
	JPanel titPanel = new JPanel();
	JPanel idPanel = new JPanel();
	JPanel pwPanel = new JPanel();
	JLabel l0; 
	JLabel l1;
	JLabel l2;
	JTextField id;
	JPasswordField passwd;
	Font font = new Font("맑은 고딕",Font.BOLD,45);
	
	public AddLogin () {
		createUI();
	}
	
	public void createUI() {
		setLayout(null);

		

		
		l0 = new JLabel("오징어게임");
		l0.setFont(font);
		l0.setForeground(Color.RED);
		l1 = new JLabel("아이디 : ");
		l2 = new JLabel("비밀번호 : ");
		id = new JTextField(15);
		passwd = new JPasswordField(15);
		
		titPanel.add(l0);
		idPanel.add(l1);
		idPanel.add(id);
		pwPanel.add(l2);
		pwPanel.add(passwd);
		
		titPanel.setBounds(0,70,360,100);
		idPanel.setBounds(0,200,360,50);
		pwPanel.setBounds(-7,250,360,50);
		
		lgBtn.setBounds(0,300,360,50);
		signBtn.setBounds(0,380,360,50);
		lgBtn.setBackground(Color.lightGray);
		signBtn.setBackground(Color.lightGray);

		add(lgBtn);
		add(signBtn);
		add(titPanel);
		add(idPanel);
		add(pwPanel);
		setBounds(0,0,360,480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		lgBtn.addActionListener(this);
		signBtn.addActionListener(this);
		
	}
	
	
	public static void main(String[] args) {
		new AddLogin();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MemDAO dao = new MemDAO();
		if (e.getSource() == lgBtn) {
			loginCheck();

		}
		if (e.getSource() == signBtn) {
			if (id.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "ID를 입력해주세요");
				id.requestFocus();
			}
			else {
				if (dao.getIdByCheck(id.getText()))
					insertMember();
			
			else {
				JOptionPane.showMessageDialog(this, id.getText()+"는 중복입니다.");
				id.setText("");
				passwd.setText("");
				id.requestFocus();
			}
			
		}
		}
		
	}
	
	public void insertMember() {
		MemDTO dto = getViewData();
		MemDAO dao = new MemDAO();
		boolean ok = dao.insertMember(dto);
		
		if (ok) {
			JOptionPane.showMessageDialog(this, "가입이 완료되었습니다.");
		}
		else {
			JOptionPane.showMessageDialog(this, "가입이 정상적으로 처리되지 않았습니다.");
		}
	}
	
	public void loginCheck() {
		MemDTO dto = getViewData();
		MemDAO dao = new MemDAO();
		boolean result = dao.loginMemberCheck(id.getText(), passwd.getText());
		
		
		if (result) {
			JOptionPane.showMessageDialog(this, "로그인되었습니다.");
			dispose();
			new RunMain();
		}
		else {
			JOptionPane.showMessageDialog(this, "등록되지 않은 정보입니다.");
		}
	}
	
	public MemDTO getViewData() {
		MemDTO dto = new MemDTO();
		String uid = id.getText();
		String pwd = passwd.getText();
		
		dto.setId(uid);
		dto.setPwd(pwd);
		
		return dto;
		
	}
}
