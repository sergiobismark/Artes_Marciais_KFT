
package kft2;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


public class teclaspermitidas extends  PlainDocument{

    public void insertString(int offset, String str, javax.swing.text.AttributeSet attr)
    throws BadLocationException{
        super.insertString(offset, str.replaceAll("[^a-z|^A-Z|^ ]",""), attr);
    }
    
    
    public void replace (int offset, String str, javax.swing.text.AttributeSet attr)
    throws BadLocationException{
        super.insertString(offset, str.replaceAll("[^a-z|^A-Z|^ ]",""), attr);
    }

}
