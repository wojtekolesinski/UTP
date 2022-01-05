package zad1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.List;
import java.util.ResourceBundle;

public class GUI extends JFrame {
    private final Database db;
    private DefaultTableModel model;
    private JTable table;

    public GUI(Database db) throws HeadlessException {
        this.db = db;
    }

    public void init() {
        setTitle("Travel Offers Data");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setPreferredSize(new Dimension(800, 300));
        setLayout(new BorderLayout());

        Locale[] langs = {
                Locale.UK,
                Locale.forLanguageTag("pl-PL")
        };
        JComboBox<Locale> comboBox = new JComboBox<>(langs);
        comboBox.setSelectedIndex(0);
        comboBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(comboBox, BorderLayout.NORTH);

        model = initModel(langs[comboBox.getSelectedIndex()]);
        JTable table = new JTable(model);
        table.getTableHeader().setBackground(Color.GREEN);
//        table.get
        table.setDragEnabled(false);
        table.setPreferredScrollableViewportSize(new Dimension(750, 200));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(scrollPane, BorderLayout.CENTER);

        updateData(langs[comboBox.getSelectedIndex()]);

        comboBox.addActionListener(e -> {
            while (model.getRowCount() > 0) model.removeRow(model.getRowCount()-1);
            updateData(langs[comboBox.getSelectedIndex()]);
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private DefaultTableModel initModel(Locale locale) {
        ResourceBundle headers = ResourceBundle.getBundle("zad1.locales.headers", locale);

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn(headers.getString("Country"));
        model.addColumn(headers.getString("Departure Date"));
        model.addColumn(headers.getString("Return Date"));
        model.addColumn(headers.getString("Place"));
        model.addColumn(headers.getString("Price"));
        model.addColumn(headers.getString("Currency"));

        return model;
    }

    private void updateData(Locale locale) {
        ResourceBundle headers = ResourceBundle.getBundle("zad1.locales.headers", locale);

//        model = initModel(locale);
        model.setColumnIdentifiers(new Object[] {
            headers.getString("Country"),
            headers.getString("Departure Date"),
            headers.getString("Return Date"),
            headers.getString("Place"),
            headers.getString("Price"),
            headers.getString("Currency")
        });

        for (List<Object> row: db.getData(locale)) {
            model.addRow(row.toArray());
            validate();
        }
        pack();

    }
}
