package ru.otus.ioservice.example.swing;

import lombok.RequiredArgsConstructor;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@RequiredArgsConstructor
public class PollMainForm extends JFrame {

    private final JPanel contentPane;
    private final JLabel inLabel;
    private final JTextField outTextField;
    private final JButton outBtn;

    private final MessageSystem ms;
    private final AtomicBoolean runFlag;
    private final ExecutorService uiFlow;

    public PollMainForm(MessageSystem ms) throws HeadlessException {
        super("Poll");
        this.ms = ms;

        FontUIResource tahoma16 = new FontUIResource("Tahoma", Font.BOLD, 16);
        UIManager.put("Label.font", tahoma16);
        UIManager.put("TextField.font", tahoma16);
        UIManager.put("Button.font", tahoma16);

        runFlag = new AtomicBoolean(true);
        uiFlow = Executors.newSingleThreadExecutor();

        GridLayout layout = new GridLayout(3, 1);
        contentPane = new JPanel(layout);
        contentPane.setBackground(Color.decode("#546E7A"));
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inLabel = new JLabel("");
        inLabel.setForeground(Color.decode("#FFAB40"));
        contentPane.add(inLabel);

        outTextField = new JTextField(15);
        outTextField.setColumns(20);
        outTextField.setForeground(Color.decode("#388E3C"));
        contentPane.add(outTextField);

        outBtn = new JButton("Ответить");
        outBtn.addActionListener(e -> ms.putToPollQueue(outTextField.getText()));
        contentPane.add(outBtn);
        setOnCloseHandler();
        setSize(450, 180);
    }

    void init() {
        setLocationRelativeTo(null);
        setVisible(true);
        startUiFlow();
    }

    private void startUiFlow() {
        uiFlow.execute(() -> {
            while (runFlag.get()) {
                try {
                    String msg = ms.takeFromUiQueue();
                    synchronized (inLabel) {
                        inLabel.setText(msg);
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }

        });
    }

    private void setOnCloseHandler() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                runFlag.set(false);
                ms.putToUiQueue("exit");
                uiFlow.shutdown();
                e.getWindow().dispose();
            }
        });
    }


}
