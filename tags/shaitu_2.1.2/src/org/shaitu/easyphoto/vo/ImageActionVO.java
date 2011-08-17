/**
 * COPYRIGHT. Harry Wu 2010. ALL RIGHTS RESERVED.
 * Project: ThirdEye
 * Author: Harry Wu <harrywu304@gmail.com>
 * Created On: Oct 16, 2008 9:05:44 AM
 *
*/ 
package org.shaitu.easyphoto.vo;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * VO for ImageProceed
 * @author whx
 *
 */
public class ImageActionVO {
	/**
	 * ImageActionParamsVO
	 */
	private ImageActionParamsVO params;
	/**
	 * image ready to decorate
	 */
	private File inputImageFile;
	/**
	 * image output after decorated
	 */
	private File outputImageFile;
	/**
	 * bufferred image decorating
	 */
	private BufferedImage bufferedImage;
    
	public File getInputImageFile() {
		return inputImageFile;
	}
	public void setInputImageFile(File inputImage) {
		this.inputImageFile = inputImage;
	}
	public File getOutputImageFile() {
		return outputImageFile;
	}
	public void setOutputImageFile(File outputImage) {
		this.outputImageFile = outputImage;
	}
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}
	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}
	public ImageActionParamsVO getParams() {
		return params;
	}
	public void setParams(ImageActionParamsVO params) {
		this.params = params;
	}
	
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append("inputImageFile:"+inputImageFile.getAbsolutePath()+";");
		result.append("outputImageFile:"+outputImageFile.getAbsolutePath()+";");
		return result.toString();
	}
}
