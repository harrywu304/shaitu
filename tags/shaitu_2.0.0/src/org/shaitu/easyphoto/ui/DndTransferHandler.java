/**
 * COPYRIGHT. Harry Wu 2010. ALL RIGHTS RESERVED.
 * Project: ThirdEye
 * Author: Harry Wu <harrywu304@gmail.com>
 * Created On: Oct 13, 2008 4:57:38 PM
 *
*/ 
package org.shaitu.easyphoto.ui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

/**
 * TransferHandler used to support DnD, 
 * filter incoming data and output incoming data
 * @author whx
 */
public class DndTransferHandler extends TransferHandler {
	/**
	 * log instance
	 */
	private static final Logger logger = Logger.getLogger(DndTransferHandler.class.getName());    
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4945752422953802410L;
	
	private EasyPhoto main;
    
    public DndTransferHandler(EasyPhoto main){
        this.main = main;
    }

    /**
     * filter incoming data 
     * @see javax.swing.TransferHandler#canImport(javax.swing.JComponent,
     *      java.awt.datatransfer.DataFlavor[])
     */
    public boolean canImport(JComponent arg0, DataFlavor[] arg1) {
        for (int i = 0; i < arg1.length; i++) {
            DataFlavor flavor = arg1[i];
            //only capture file flavor
            if (flavor.equals(DataFlavor.javaFileListFlavor)) {
                return true;
            }
        }
        return false;
    }

    /**
     * import data
     * @see javax.swing.TransferHandler#importData(javax.swing.JComponent,
     *      java.awt.datatransfer.Transferable)
     */
    public boolean importData(JComponent comp, Transferable t) {
        DataFlavor[] flavors = t.getTransferDataFlavors();
        for (int i = 0; i < flavors.length; i++) {
            try {
                DataFlavor flavor = flavors[i];
                if (flavor.equals(DataFlavor.javaFileListFlavor)) {
                	//get file list
                    List<File> fileList = (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);
                    if(comp instanceof javax.swing.JLabel){
                        //image input as target
	                    main.setInputFiles((File[])fileList.toArray());
                    } else if(comp instanceof javax.swing.JTextField){
                        JTextField tfTarget = (JTextField) comp;
                    	//image output as target
                    	java.io.File file = fileList.get(0);
                    	if(!file.isDirectory()){
                    		return false;
                    	}
                        //set textfield target text
                        tfTarget.setText(file.getAbsolutePath());
	                    //reset output file chooser
	                    main.setOutputFolder(file);
                    }
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
