package zad2;

import java.awt.Component;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class CustomListCellRender extends JLabel implements ListCellRenderer<Future<Integer>> {

	@Override
	public Component getListCellRendererComponent(
			JList<? extends Future<Integer>> list, 
			Future<Integer> value, 
			int index,
			boolean isSelected, 
			boolean cellHasFocus
	) {
		
		Pattern pattern = Pattern.compile("(task\\d+)");
		
		String s = "hey"+value.toString();
		Matcher matcher = pattern.matcher(s);
		matcher.find();
		String state = value.isCancelled() ? "Cancelled" : value.isDone() ? "Done" : "Running";
	    setText("Task" + (index+1) + " state: " + state);
//	    setText(s);
	//        setIcon((s.length() > 10) ? longIcon : shortIcon);
	    if (isSelected) {
	        setBackground(list.getSelectionBackground());
	        setForeground(list.getSelectionForeground());
	    } else {
	        setBackground(list.getBackground());
	        setForeground(list.getForeground());
	    }
	    setEnabled(list.isEnabled());
	    setFont(list.getFont());
	    setOpaque(true);
	    return this;
    
	}

	
	
//	public Component getListCellRendererComponent(
//		       JList<? extends Future<Integer>> list,           // the list
//		       Object value,            // value to display
//		       int index,               // cell index
//		       boolean isSelected,      // is the cell selected
//		       boolean cellHasFocus)    // does the cell have focus
//		     {
//		         String s = value.toString();
//		         setText(s);
////		         setIcon((s.length() > 10) ? longIcon : shortIcon);
//		         if (isSelected) {
//		             setBackground(list.getSelectionBackground());
//		             setForeground(list.getSelectionForeground());
//		         } else {
//		             setBackground(list.getBackground());
//		             setForeground(list.getForeground());
//		         }
//		         setEnabled(list.isEnabled());
//		         setFont(list.getFont());
//		         setOpaque(true);
//		         return this;
//		     }
//}
//	
	

}
