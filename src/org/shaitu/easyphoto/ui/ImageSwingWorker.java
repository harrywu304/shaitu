/**
 * COPYRIGHT. Harry Wu 2010. ALL RIGHTS RESERVED.
 * Project: ThirdEye
 * Author: Harry Wu <harrywu304@gmail.com>
 * Created On: Oct 13, 2008 4:57:38 PM
 *
*/ 
package org.shaitu.easyphoto.ui;

import java.awt.Desktop;
import java.net.URI;
import java.text.MessageFormat;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import org.shaitu.easyphoto.action.ImageAction;
import org.shaitu.easyphoto.vo.ImageActionParamsVO;




/**
 * SwingWorker used to connect EDT and work threads
 * 
 * @author whx
 */
public class ImageSwingWorker extends SwingWorker {
	/**
	 * log instance
	 */
	private static final Logger logger = Logger
			.getLogger(ImageSwingWorker.class.getName());

	/**
	 * refer to EasyPhoto instance
	 */
	private EasyPhoto main;
	/**
	 * ImageProceedController instance
	 */
	private ImageAction imageAction;
    /**
     * auto open output after proceed
     */
    private boolean autoOpenOutput;
    /**
     * output folder
     */
    private URI outputFolder;

	/**
	 * ImageProceedSwingWorker constructor
	 * 
	 * @param mainframe
	 *            ThirdEye instance
	 */
	public ImageSwingWorker(EasyPhoto main) {
		this.main = main;
		this.imageAction = ImageAction.getInstance();
	}

	@Override
	protected Object doInBackground() throws Exception {
		try {
            autoOpenOutput = main.getAppOptionsVO().isAutoOpenOutput();
			// generate ImageProceedVO instance
			ImageActionParamsVO vo = main.getImageActionParamsVO();
	        outputFolder = vo.getOutputFolder().toURI();
	        if(vo.isOverride()){
	        	outputFolder = vo.getInputFiles()[0].toURI();
	        }
			//print action vo info for debug
			logger.info(vo.toString());
			imageAction.handle(vo);
			// monitor progress status
			while (!imageAction.isDone()) {
				Thread.sleep(100);
				// publish(controller.getImgDoneCount());
				setProgress(100 * imageAction.getImgDoneCount()
						/ imageAction.getImgCount());
			}
			setProgress(100);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	protected void done() {
        // show result info
        String resultInfo = EasyPhoto.messageMapping
                .getString("dialog.successinfo");
        resultInfo = MessageFormat.format(resultInfo, imageAction
                .getImgCount(), imageAction.getImgDoneCount(), imageAction
                .getImgCount() - imageAction.getImgDoneCount());
        JOptionPane.showMessageDialog(main, resultInfo, "Message",
                JOptionPane.INFORMATION_MESSAGE);
                                // proceed success, reset main frame
        main.resetAfterExport();
        if(autoOpenOutput){
        	//auto open image output folder
            try{
                Desktop.getDesktop().browse(outputFolder);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
	}

	/**
	 * stop image proceed task immediately
	 */
	public void stop() {
		imageAction.stop();
	}
        

}
