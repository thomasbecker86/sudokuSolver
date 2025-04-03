/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sudokusolver.gui;

import com.mycompany.sudokusolver.model.SudokuLogic;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Thomas
 */
public class InputFilter extends DocumentFilter {

    private boolean isInternalUpdate = false;
    private SudokuLogic logic;

    public InputFilter(SudokuLogic logic) {
        this.logic = logic;
    }
    
    public void setInternalUpdate(boolean internalUpdate) {
        this.isInternalUpdate = internalUpdate;
    }
    
    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
        StringBuilder sb = new StringBuilder();
        sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
        sb.insert(offset, text);
        if (isInternalUpdate || isValid(fb.getDocument(), sb.toString())) {
            super.insertString(fb, offset, text, attr);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }
    
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attr) throws BadLocationException {
        StringBuilder sb = new StringBuilder();
        sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
        int end = offset + length;
        sb.replace(offset, end, text);
        if (isInternalUpdate || isValid(fb.getDocument(), sb.toString())) {
            super.replace(fb, offset, length, text, attr);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        JTextField field = (JTextField) fb.getDocument().getProperty("owner");
        Integer row = (Integer) field.getClientProperty("row");
        Integer col = (Integer) field.getClientProperty("col");
        logic.removeValue(row, col);
        super.remove(fb, offset, length);
    }
    
    
    
    private boolean isValid(Document doc, String text) {
        System.out.println("isValid wurde aufgerufen!");
        if (text.isEmpty()) {
            return true;
        }
        if (text.length() > 1) {
            return false;
        }
        if (!Character.isDigit(text.charAt(0))) {
            return false;
        }
        
        JTextField field = (JTextField) doc.getProperty("owner");
        Integer candidate = Integer.valueOf(text);
        Integer row = (Integer) field.getClientProperty("row");
        Integer col = (Integer) field.getClientProperty("col");
        System.out.println("Eingabe " +  candidate + " an Position [" + row + ", " + col + "] überprüft.");
        if (!logic.checkRow(row, candidate) || !logic.checkColumn(col, candidate) || !logic.checkSubgrid(row, col, candidate)) {
            System.out.println("Wert schon vorhanden!");
            return false;
        }
        logic.insertValue(row, col, candidate);
        return true;
    }    
}
