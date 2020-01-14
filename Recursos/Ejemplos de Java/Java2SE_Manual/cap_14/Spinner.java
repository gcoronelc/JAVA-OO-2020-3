import javax.swing.*;
import javax.swing.event.*;
import java.text.*;
import java.awt.*;
import java.util.*;

public class Spinner {
  public static void main (String args[]) throws Exception {
    JFrame frame = new JFrame("Spinner");
    frame.setDefaultCloseOperation(3);
    String[] months = new DateFormatSymbols().getMonths();
    SpinnerModel model = new SpinnerListModel(months);
    JSpinner spinner = new JSpinner(model);
    frame.getContentPane().add(spinner, BorderLayout.NORTH);

    SpinnerDateModel model2 = new SpinnerDateModel();
    model2.setCalendarField(Calendar.WEEK_OF_MONTH);
    JSpinner spinner2 = new JSpinner(model2);
    JSpinner.DateEditor editor2 = new JSpinner.DateEditor(
      spinner2, "MMMMM dd, yyyy");
    spinner2.setEditor(editor2);
    frame.getContentPane().add(spinner2, BorderLayout.SOUTH);

    SpinnerNumberModel model3 = new SpinnerNumberModel(50, 0, 100, 5);
    JSpinner spinner3 = new JSpinner(model3);
    frame.getContentPane().add(spinner3, BorderLayout.CENTER);

    ChangeListener listener = new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        SpinnerModel source = (SpinnerModel)e.getSource();
        System.out.println("The value is: " + source.getValue());
      }
    };
    model.addChangeListener(listener);
    model2.addChangeListener(listener);
    model3.addChangeListener(listener);

    frame.pack();
    frame.show();
  }
}
