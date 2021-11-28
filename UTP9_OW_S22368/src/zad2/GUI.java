package zad2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;


public class GUI extends JFrame implements ActionListener {


	private ExecutorService executor;
	private DefaultListModel<Future<Integer>> model;
	private int k = 0;
	private final int n = 10;
	private final JList<Future<Integer>> jList;
	private final JLabel label;
	private final Timer timer = new Timer(40, this);

	public GUI() throws HeadlessException {
		executor = Executors.newFixedThreadPool(3);
		setTitle("TaskList");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        
		label = new JLabel("   ");
		label.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        
		jList = new JList<>();
        model = new DefaultListModel<Future<Integer>>();
        JScrollPane scrollPane = new JScrollPane(jList);
        scrollPane.setEnabled(true);
        jList.setModel(model);
        jList.setCellRenderer(new CustomListCellRender());
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.getSelectionModel().addListSelectionListener(e -> {
        	label.setText("Task"+(e.getFirstIndex()+1));
        });
        
        JPanel buttons = new JPanel(new GridLayout(1, 3, 10, 10));
        for (String str: new String[]{"Start", "Check", "Stop"}) {
        	JButton button = new JButton(str);
        	button.setActionCommand(str);
        	button.addActionListener(this);
        	buttons.add(button);
        }
        buttons.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jList.setPreferredSize(new Dimension(buttons.getSize().width, 250));
        add(label, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
        
        pack();
        
        timer.addActionListener(e -> this.repaint());
        
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		try {
			Method m = this.getClass().getDeclaredMethod("task"+command);
			m.invoke(this);
//			System.out.println(m.getName());
		} catch (Exception e1) {} 
	}
	
	public void taskStart() {
		model.addElement(executor.submit(new zad2.SumTask(++k, (int)(5 + Math.random()*10))));
	}
	
	public void taskCheck() {
		int index = jList.getSelectedIndex();
		Future<Integer> selected = model.get(index); 
		if (selected.isCancelled()) {
			label.setText("Task"+(index+1)+" was cancelled");
		} else if (selected.isDone()) {
			try {
				label.setText("Task"+(index+1)+" result: "+selected.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		} else {
			label.setText("Task"+(index+1)+" is still executing");
		}
		
		
	}
	
	public void taskStop() {
		int index = jList.getSelectedIndex();
		Future<Integer> selected = model.get(index);
		selected.cancel(true);
		label.setText("Cancelling Task"+(index+1));
	}
	
	
	
	

}
