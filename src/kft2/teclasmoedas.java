/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kft2;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Sergio
 */
public class teclasmoedas extends PlainDocument{
    
    public void insertString(int offset, String str, javax.swing.text.AttributeSet attr)
    throws BadLocationException{
        super.insertString(offset, str.replaceAll("[^0-9|^.|^,|^R|^$]",""), attr);
    }
    
    
    public void replace (int offset, String str, javax.swing.text.AttributeSet attr)
    throws BadLocationException{
        super.insertString(offset, str.replaceAll("[^0-9|^.|^,|^R|^$]",""), attr);
    }

}
