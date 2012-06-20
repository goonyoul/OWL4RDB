import javax.swing.*;
import javax.swing.filechooser.*;

import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class MindMap extends JFrame{
	JMenuBar MenuBar = new JMenuBar();
	JToolBar ToolBar = new JToolBar();
	JPanel AttributePane = new JPanel();
	JPanel SettingPane = new JPanel();
	JPanel ButtonPane = new JPanel();
	JPanel MindMapPane = new JPanel();
	Node node = new Node();
	static MindMap MM;

	JTextField start_x = new JTextField("");
	JTextField start_y= new JTextField("");
	JTextField width= new JTextField("");
	JTextField height= new JTextField("");
	JTextField text= new JTextField(20);
	JButton add = new JButton("�߰�");
	JButton change = new JButton("����");
	JButton delete = new JButton("����");

	String fileName = null;
	String pathName = null;

	private void MenuBar(){		
		setJMenuBar(MenuBar);
		JMenu filemenu = new JMenu("����");
		MenuBar.add(filemenu);
		JMenuItem newMindMap = new JMenuItem("���� �����");
		JMenuItem Open = new JMenuItem("����");
		JMenuItem Save = new JMenuItem("����");
		JMenuItem SaveAs= new JMenuItem("�ٸ� �̸����� ����");
		JMenuItem Close = new JMenuItem("�ݱ�");
		filemenu.add(newMindMap);
		filemenu.add(Open);
		filemenu.add(Save);
		filemenu.add(SaveAs);
		filemenu.add(Close);
		//��ư Ŭ����
		newMindMap.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				newMindMap();
			}
		});
		Save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				save();
			}
		});
		SaveAs.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				saveas();
			}
		});
		Open.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				open();
			}
		});
		Close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				close();
			}
		});
	}
	private void newMindMap(){
		setVisible(false);
		MM = new MindMap();
	}
	private void save(){
		if(fileName == null){
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("txt","TXT");
			chooser.setFileFilter(filter);
			int ret = chooser.showSaveDialog(null);
			if(ret == JFileChooser.APPROVE_OPTION) {
				pathName = chooser.getSelectedFile().getPath();
				fileName = chooser.getSelectedFile().getName();
			}
			try{
				FileWriter fw = new FileWriter(pathName+".txt");
				BufferedWriter bw = new BufferedWriter(fw);
				String data = node.pack();
				bw.write(data);
				bw.close();
				fw.close();
			}catch(Exception ee){}
		}
		else{
			try{
				FileWriter fw = new FileWriter(pathName+".txt");
				BufferedWriter bw = new BufferedWriter(fw);
				String data = node.pack();
				bw.write(data);
				bw.close();
				fw.close();
			}catch(Exception ee){}
		}
	}
	private void saveas(){
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("txt","TXT");
		chooser.setFileFilter(filter);
		int ret = chooser.showSaveDialog(null);
		if(ret == JFileChooser.APPROVE_OPTION) {
			pathName = chooser.getSelectedFile().getPath();
			fileName = chooser.getSelectedFile().getName();
		}
		try{
			FileWriter fw = new FileWriter(pathName+".txt");
			BufferedWriter bw = new BufferedWriter(fw);
			String data = node.pack();
			bw.write(data);
			bw.close();
			fw.close();
		}catch(Exception ee){}
	}
	private void open(){
		String pathName2=null;
		String fileName2=null;
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("txt","TXT");
		chooser.setFileFilter(filter);
		int ret = chooser.showOpenDialog(null);
		if(ret == JFileChooser.APPROVE_OPTION) {
			pathName2 = chooser.getSelectedFile().getPath();
			fileName2 = chooser.getSelectedFile().getName();
		}
		if(pathName2!=null){
			try{
				FileReader fr = new FileReader(pathName2);
				BufferedReader br = new BufferedReader(fr);
				node.rootNode.removeAllChildren();
				for(String data = br.readLine();data!=null;data = br.readLine()){
					node.unpack(data);
				}
				br.close();
				fr.close();
			}catch(IOException ee){}
		}
		fileName=fileName2;
		pathName=pathName2;		
		node.repaint();
		MindMapPane.updateUI();
	}
	private void close(){
		setVisible(false);
	}
	private void ToolBar(){
		JButton newMindMap_T = new JButton(new ImageIcon("images/new.gif"));
		JButton Open_T= new JButton(new ImageIcon("images/open.gif"));
		JButton Save_T = new JButton(new ImageIcon("images/save.gif"));
		JButton SaveAs_T = new JButton(new ImageIcon("images/saveas.gif"));
		ToolBar.add(newMindMap_T);
		ToolBar.add(Open_T);
		ToolBar.add(Save_T);
		ToolBar.add(SaveAs_T);
		newMindMap_T.setToolTipText("���� �����");
		Open_T.setToolTipText("����");
		Save_T.setToolTipText("����");
		SaveAs_T.setToolTipText("�ٸ� �̸����� ����");
		getContentPane().add(ToolBar,BorderLayout.NORTH);
		newMindMap_T.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				newMindMap();
			}
		});
		Save_T.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				save();
			}
		});
		SaveAs_T.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				saveas();
			}
		});
		Open_T.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				open();
			}
		});
	}
	private void AttributePane(){
		GridLayout grid1 = new GridLayout(5,2);
		GridLayout grid2 = new GridLayout(1,3);
		grid1.setVgap(5);

		SettingPane.setPreferredSize(new Dimension(250,200));
		AttributePane.add(SettingPane,BorderLayout.CENTER);
		AttributePane.add(ButtonPane,BorderLayout.SOUTH);
		SettingPane.setLayout(grid1);
		SettingPane.add(new JLabel(" ����  x��ǥ :"));
		SettingPane.add(start_x);
		SettingPane.add(new JLabel(" ����  y��ǥ :"));
		SettingPane.add(start_y);
		SettingPane.add(new JLabel(" �ʺ� :"));
		SettingPane.add(width);
		SettingPane.add(new JLabel(" ���� :"));
		SettingPane.add(height);
		SettingPane.add(new JLabel(" �ؽ�Ʈ :"));
		SettingPane.add(text);
		ButtonPane.setLayout(grid2);
		ButtonPane.add(add);
		ButtonPane.add(change);
		ButtonPane.add(delete);

		//��ư Ŭ����
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String st = text.getText();
				node.addNode(st);
				MindMapPane.updateUI();
			}
		});
		change.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String st = text.getText();
				node.setNode(st);
				MindMapPane.updateUI();
			}
		});
		delete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				node.removeNode();
				MindMapPane.updateUI();
			}
		});
		AttributePane.setPreferredSize(new Dimension(250,500));
	}
	public void setSettingPane(){
		text.setText(node.getNodeName());
	}
	MindMap() {
		setTitle("MindMap");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//MenuBar
		MenuBar();

		//ToolBar
		ToolBar();

		//AttributePane
		AttributePane();

		//////////////////////////////////////
		getContentPane().add(AttributePane,BorderLayout.WEST);
		getContentPane().add(MindMapPane,BorderLayout.CENTER);
		MindMapPane.add(node.tree);
		node.tree.addMouseListener(new nodeMouseListener());
		//////////////////////////////////////
		setSize(600,600);
		setVisible(true);
	}
	public static void main(String[] args) {
		MM = new MindMap();
	}

	class nodeMouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			setSettingPane();
			MindMapPane.updateUI();
		}
	}
}