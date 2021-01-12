package com.evil.mvp.dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InputDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private ResultCallback resultCallback;

    public InputDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        if (resultCallback != null){
            String all = textField1.getText().trim().replaceAll("\n", "").replaceAll(" ","");
            resultCallback.onResult(all);
        }
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public void setResultCallback(ResultCallback resultCallback) {
        this.resultCallback = resultCallback;
    }

    public static void main(String[] args) {
        InputDialog dialog = new InputDialog();
        dialog.setTitle("Please input mvp model name!");
        dialog.setMinimumSize(new Dimension(300, 50));
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        dialog.setBounds(size.width / 2 - 150, size.height / 2 - 25, 300, 50);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public interface ResultCallback {
        void onResult(String modelName);
    }
}
