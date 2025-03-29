/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sudokusolver.gui;

import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Thomas
 */
public class InputFilter extends DocumentFilter {

    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
        
        StringBuilder sb = new StringBuilder();
        sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
        sb.insert(offset, text);
        if (isValid(sb.toString())) {
            super.insertString(fb, offset, text, attr);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
        /*
        boolean isValidInteger = true;
        /*
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isDigit(string.charAt(i))) {
                isValidInteger = false;
                break;
            }
        }
    ////    
        if (string.length() > 1) {
            isValidInteger = false;
        }
        if (!Character.isDigit(string.charAt(0))) {
            isValidInteger = false;
        }
        if (isValidInteger) {
            super.insertString(fb, offset, string, attr);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
        */
    }
    
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attr) throws BadLocationException {
        
        StringBuilder sb = new StringBuilder();
        sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
        int end = offset + length;
        sb.replace(offset, end, text);
        if (isValid(sb.toString())) {
            super.replace(fb, offset, length, text, attr);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
            
        /*
        boolean isValidInteger = true;
        
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isDigit(string.charAt(i))) {
                isValidInteger = false;
                break;
            }
        }
        
        if (string.length() > 1) {
            isValidInteger = false;
        }
        if (!Character.isDigit(string.charAt(0))) {
            isValidInteger = false;
        }
        if (isValidInteger) {
            super.replace(fb, offset, length, string, attr);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
        */
    }
    
    private boolean isValid(String text) {
        if (text.isEmpty()) {
            return true;
        }
        if (text.length() > 1) {
            return false;
        }
        return Character.isDigit(text.charAt(0));
        /*
        int intValue = 0;
        try {
        intValue = Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
        return false;
        }        
        if (intValue < 1 || intValue > 9) {
        return false;
        }
        return true;
         */        

    }
}
