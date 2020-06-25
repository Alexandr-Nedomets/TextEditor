package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextEditor extends JFrame {

    public TextEditor() {
        unitUI();
    }

    private void unitUI() {
        setTitle("Text Editor");

        getContentPane().setLayout(new FlowLayout());

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuFile = new JMenu("File");
        menuFile.setName("MenuFile");
        menuFile.setMnemonic(KeyEvent.VK_F);

        JMenuItem menuItemOpen = new JMenuItem("Open");
        menuItemOpen.setName("MenuOpen");

        JMenuItem menuItemSave = new JMenuItem("Save");
        menuItemSave.setName("MenuSave");

        JMenuItem menuItemExit = new JMenuItem("Exit");
        menuItemExit.setName("MenuExit");

        menuBar.add(menuFile);
        menuFile.add(menuItemOpen);
        menuFile.add(menuItemSave);
        menuFile.addSeparator();
        menuFile.add(menuItemExit);

        JMenu menuSearch = new JMenu("Search");
        menuSearch.setName("MenuSearch");
        menuSearch.setMnemonic(KeyEvent.VK_S);

        JMenuItem menuItemStartSearch = new JMenuItem("Start search");
        menuItemStartSearch.setName("MenuStartSearch");

        JMenuItem menuItemPreviousMatch = new JMenuItem("Previous search");
        menuItemPreviousMatch.setName("MenuPreviousMatch");

        JMenuItem menuItemNextMatch = new JMenuItem("Next match");
        menuItemNextMatch.setName("MenuNextMatch");

        JMenuItem menuItemUseRegExp = new JMenuItem("Use regular expressions");
        menuItemUseRegExp.setName("MenuUseRegExp");

        menuBar.add(menuSearch);
        menuSearch.add(menuItemStartSearch);
        menuSearch.add(menuItemPreviousMatch);
        menuSearch.add(menuItemNextMatch);
        menuSearch.add(menuItemUseRegExp);

        Image imageOpenButton = new ImageIcon(getClass().getResource("images/openButton.png"))
                .getImage().getScaledInstance(16, 16,  java.awt.Image.SCALE_SMOOTH);
        Image imageSaveButton = new ImageIcon(getClass().getResource("images/saveButton.png"))
                .getImage().getScaledInstance(16, 16,  java.awt.Image.SCALE_SMOOTH);
        Image imageStartSearchButton = new ImageIcon(getClass().getResource("images/startSearchButton.png"))
                .getImage().getScaledInstance(16, 16,  java.awt.Image.SCALE_SMOOTH);
        Image imagePreviousMatchButton = new ImageIcon(getClass().getResource("images/previousMatchButton.png"))
                .getImage().getScaledInstance(16, 16,  java.awt.Image.SCALE_SMOOTH);
        Image imageNextMatchButton = new ImageIcon(getClass().getResource("images/nextMatchButton.png"))
                .getImage().getScaledInstance(16, 16,  java.awt.Image.SCALE_SMOOTH);

        JButton openButton = new JButton(new ImageIcon(imageOpenButton));
        openButton.setName("OpenButton");
        JButton saveButton = new JButton(new ImageIcon(imageSaveButton));
        saveButton.setName("SaveButton");
        JTextField searchField = new JTextField(28);
        searchField.setName("SearchField");
        JButton startSearchButton = new JButton(new ImageIcon(imageStartSearchButton));
        startSearchButton.setName("StartSearchButton");
        JButton previousMatchButton = new JButton(new ImageIcon(imagePreviousMatchButton));
        previousMatchButton.setName("PreviousMatchButton");
        JButton nextMatchButton = new JButton(new ImageIcon(imageNextMatchButton));
        nextMatchButton.setName("NextMatchButton");
        JCheckBox useRegExCheckbox = new JCheckBox("Use regex");
        useRegExCheckbox.setName("UseRegExCheckbox");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("C:\\Users\\user\\IdeaProjects\\Text Editor\\Text Editor\\task\\src\\"));
        fileChooser.setName("FileChooser");
        fileChooser.setVisible(false);

        JTextArea textArea = new JTextArea(25, 60);
        textArea.setName("TextArea");

        JScrollPane scrollableTextArea = new JScrollPane(textArea);
        scrollableTextArea.setName("ScrollPane");

        ArrayList<Integer> arrayListIndex = new ArrayList<>();
        ArrayList<String> arrayListWords = new ArrayList<>();
        IndexFoundText indexFoundText = new IndexFoundText() ;

        openButton.addActionListener(actionEvent -> {
            openFile(fileChooser, textArea);
        });

        saveButton.addActionListener(actionEvent -> {
            saveFile(fileChooser, textArea);
        });
        menuItemOpen.addActionListener(event -> {
            openFile(fileChooser, textArea);
        });

        menuItemSave.addActionListener(event -> {
            saveFile(fileChooser, textArea);
        });

        menuItemExit.addActionListener(event -> System.exit(0));
        startSearchButton.addActionListener(actionEvent -> {
            startSearch(indexFoundText, searchField, textArea, useRegExCheckbox, arrayListIndex,  arrayListWords);
        });
        previousMatchButton.addActionListener(actionEvent -> {
            previousMatch(indexFoundText, textArea, arrayListIndex, arrayListWords);

        });
        nextMatchButton.addActionListener(actionEvent -> {
            nextMatch(indexFoundText, textArea, arrayListIndex, arrayListWords);
        });
        menuItemStartSearch.addActionListener(event -> {
            startSearch(indexFoundText, searchField, textArea, useRegExCheckbox, arrayListIndex,  arrayListWords);
        });
        menuItemPreviousMatch.addActionListener(event -> {
            previousMatch(indexFoundText, textArea, arrayListIndex, arrayListWords);
        });
        menuItemNextMatch.addActionListener(event -> {
            nextMatch(indexFoundText, textArea, arrayListIndex, arrayListWords);
        });
        menuItemUseRegExp.addActionListener(event -> {
            useRegExCheckbox.setSelected(true);
        });

        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(fileChooser);
        add(openButton);
        add(saveButton);
        add(searchField);
        add(startSearchButton);
        add(previousMatchButton);
        add(nextMatchButton);
        add(useRegExCheckbox);
        add(scrollableTextArea);
        pack();

        setSize(700, 520);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void openFile(JFileChooser fileChooser, JTextArea textArea) {
        fileChooser.setVisible(true);
        fileChooser.setDialogTitle("Open file");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.showOpenDialog(null);
        String strPath = fileChooser.getSelectedFile().getPath();
        String str =  new LoadFile().loadText(strPath);
        textArea.setText(str);
    }

    private void saveFile(JFileChooser fileChooser, JTextArea textArea) {
        fileChooser.setVisible(true);
        fileChooser.setDialogTitle("Save file");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.showSaveDialog(null);
        new SaveFile().saveText(textArea.getText(), fileChooser.getSelectedFile().getPath());
        textArea.setText("");
    }

    private void startSearch(IndexFoundText indexFoundText, JTextField searchField, JTextArea textArea, JCheckBox useRegExCheckbox,
                             ArrayList<Integer> arrayListIndex, ArrayList<String> arrayListWords) {
        Pattern javaPattern = Pattern.compile(searchField.getText().trim());
        Matcher matcher = javaPattern.matcher(textArea.getText());
        arrayListIndex.clear();
        arrayListWords.clear();
        indexFoundText.setI(0);
        if (useRegExCheckbox.isSelected()) {
            while (matcher.find()) {
                arrayListIndex.add(matcher.start());
                arrayListWords.add(matcher.group());
            }
        } else {
            while (matcher.find()) {
                arrayListIndex.add(matcher.start());
                arrayListWords.add(searchField.getText().trim());
            }
        }
        int start = arrayListIndex.get(0)  + arrayListWords.get(0).length();
        textArea.setCaretPosition(start);
        textArea.select(arrayListIndex.get(0), start);
        textArea.grabFocus();
    }
    private void previousMatch(IndexFoundText indexFoundText, JTextArea textArea,
                               ArrayList<Integer> arrayListIndex, ArrayList<String> arrayListWords) {
        if (indexFoundText.getI() > 0) {
            indexFoundText.decrement();
        } else {
            indexFoundText.setI(arrayListIndex.size()-1);
        }
        int start = arrayListIndex.get(indexFoundText.getI()) + arrayListWords.get(indexFoundText.getI()).length();
        textArea.setCaretPosition(start);
        textArea.select(arrayListIndex.get(indexFoundText.getI()), start);
        textArea.grabFocus();
    }
    private void nextMatch(IndexFoundText indexFoundText, JTextArea textArea,
                           ArrayList<Integer> arrayListIndex, ArrayList<String> arrayListWords) {
        if (indexFoundText.getI() < arrayListIndex.size() - 1) {
            indexFoundText.increment();
        }else {
            indexFoundText.setI(0);
        }
        int start = arrayListIndex.get(indexFoundText.getI()) + arrayListWords.get(indexFoundText.getI()).length();
        textArea.setCaretPosition(start);
        textArea.select(arrayListIndex.get(indexFoundText.getI()), start);
        textArea.grabFocus();
    }

}