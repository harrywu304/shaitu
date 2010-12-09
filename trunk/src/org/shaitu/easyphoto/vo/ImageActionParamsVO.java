/**
 * COPYRIGHT. www.dxtop.net 2008. ALL RIGHTS RESERVED.
 * Project: ThirdEye
 * Author: Harry Wu <harrywu304@gmail.com>
 * Created On: Oct 16, 2008 9:05:44 AM
 *
 */
package org.shaitu.easyphoto.vo;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

/**
 * VO for ImageProceed user params
 * 
 * @author whx
 * 
 */
public class ImageActionParamsVO {
	/**
	 * output folder
	 */
	private File outputFolder;
	/**
	 * input files or folders
	 */
	private File[] inputFiles;

	/**
	 * directly override
	 */
	private boolean override;
	/**
	 * output image quality
	 */
	private int quality;
	/**
	 * apply resize function
	 */
	private boolean applyResize;
	/**
	 * apply round corner function
	 */
	private boolean applyRound;
	/**
	 * apply exif into append function
	 */
	private boolean applyExif;
	/**
	 * resize size
	 */
	private int resize;
	/**
	 * exif info append style
	 */
	private String exifStyle;
	/**
	 * append date
	 */
	private boolean appendDate;
	/**
	 * append camera model info
	 */
	private boolean appendCamera;
	/**
	 * append aperture
	 */
	private boolean appendApt;
	/**
	 * append exposure time
	 */
	private boolean appendExp;
	/**
	 * append focal length
	 */
	private boolean appendFocal;
	/**
	 * append iso info
	 */
	private boolean appendIso;
	/**
	 * append user custom sign info
	 */
	private String sign;
	/**
	 * exif info font
	 */
	private Font exifFont;
	/**
	 * apply text watermark
	 */
	private boolean applyWatermarkText;
	/**
	 * text watermark style tiny; cross
	 */
	private String watermarkTextStyle;
    /**
     * text watermark font
     */
    private Font watermarkTextFont;
	/**
	 * text watermark color
	 */
	private Color watermarkColor;
	/**
	 * watermark text content
	 */
	private String watermarkText;
	/**
	 * text watermark transparent 0.0f~1.0f
	 */
	private int watermarkTextAlpha;
	/**
	 * resize component value index
	 */
	private int resizeIndex;
    /**
     * round corner size
     */
	private int cornerSize;

	public File getOutputFolder() {
		return outputFolder;
	}

	public void setOutputFolder(File outputFolder) {
		this.outputFolder = outputFolder;
	}

	public File[] getInputFiles() {
		return inputFiles;
	}

	public void setInputFiles(File[] inputFiles) {
		this.inputFiles = inputFiles;
	}

	public boolean isOverride() {
		return override;
	}

	public void setOverride(boolean override) {
		this.override = override;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public boolean isApplyResize() {
		return applyResize;
	}

	public void setApplyResize(boolean applyResize) {
		this.applyResize = applyResize;
	}

	public boolean isApplyRound() {
		return applyRound;
	}

	public void setApplyRound(boolean applyRound) {
		this.applyRound = applyRound;
	}

	public boolean isApplyExif() {
		return applyExif;
	}

	public void setApplyExif(boolean applyExif) {
		this.applyExif = applyExif;
	}

	public int getResize() {
		return resize;
	}

	public void setResize(int resize) {
		this.resize = resize;
	}

	public String getExifStyle() {
		return exifStyle;
	}

	public void setExifStyle(String exifStyle) {
		this.exifStyle = exifStyle;
	}

	public boolean isAppendDate() {
		return appendDate;
	}

	public void setAppendDate(boolean appendDate) {
		this.appendDate = appendDate;
	}

	public boolean isAppendCamera() {
		return appendCamera;
	}

	public void setAppendCamera(boolean appendCamera) {
		this.appendCamera = appendCamera;
	}

	public boolean isAppendApt() {
		return appendApt;
	}

	public void setAppendApt(boolean appendApt) {
		this.appendApt = appendApt;
	}

	public boolean isAppendExp() {
		return appendExp;
	}

	public void setAppendExp(boolean appendExp) {
		this.appendExp = appendExp;
	}

	public boolean isAppendFocal() {
		return appendFocal;
	}

	public void setAppendFocal(boolean appendFocal) {
		this.appendFocal = appendFocal;
	}

	public boolean isAppendIso() {
		return appendIso;
	}

	public void setAppendIso(boolean appendIso) {
		this.appendIso = appendIso;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Font getExifFont() {
		return exifFont;
	}

	public void setExifFont(Font exifFont) {
		this.exifFont = exifFont;
	}

	public int getResizeIndex() {
		return resizeIndex;
	}

	public void setResizeIndex(int resizeIndex) {
		this.resizeIndex = resizeIndex;
	}
	
	public boolean isApplyWatermarkText() {
		return applyWatermarkText;
	}

	public void setApplyWatermarkText(boolean applyWatermarkText) {
		this.applyWatermarkText = applyWatermarkText;
	}

	public String getWatermarkTextStyle() {
		return watermarkTextStyle;
	}

	public void setWatermarkTextStyle(String watermarkTextStyle) {
		this.watermarkTextStyle = watermarkTextStyle;
	}

	public Color getWatermarkColor() {
		return watermarkColor;
	}

	public void setWatermarkColor(Color watermarkColor) {
		this.watermarkColor = watermarkColor;
	}

	public String getWatermarkText() {
		return watermarkText;
	}

	public void setWatermarkText(String watermarkText) {
		this.watermarkText = watermarkText;
	}

	public int getWatermarkTextAlpha() {
		return watermarkTextAlpha;
	}

	public void setWatermarkTextAlpha(int watermarkTextAlpha) {
		this.watermarkTextAlpha = watermarkTextAlpha;
	}

    public Font getWatermarkTextFont() {
        return watermarkTextFont;
    }

    public void setWatermarkTextFont(Font watermarkTextFont) {
        this.watermarkTextFont = watermarkTextFont;
    }

    public int getCornerSize() {
        return cornerSize;
    }

    public void setCornerSize(int cornerSize) {
        this.cornerSize = cornerSize;
    }
    

	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("outputFolder:" + outputFolder.getAbsolutePath() + ";");
		result.append("inputFiles:");
		for (int i = 0; i < inputFiles.length; i++) {
			result.append(inputFiles[i].getAbsolutePath() + ",");
		}
		result.append(";");
		result.append("override:" + override + ";");
		result.append("quality:" + quality + ";");
		result.append("applyResize:" + applyResize + ";");
		result.append("applyRound:" + applyRound + ";");
		result.append("applyExif:" + applyExif + ";");
		result.append("resize:" + resize + ";");
		result.append("exifStyle:" + exifStyle + ";");
		result.append("sign:" + sign + ";");
		result.append("exifFont:" + exifFont.toString() + ";");
		result.append("applyWatermarkText:" + applyWatermarkText + ";");
        result.append("watermarkTextFont:" + watermarkTextFont + ";");
        result.append("watermarkColor:" + watermarkColor + ";");
		result.append("watermarkText:" + watermarkText + ";");
		result.append("watermarkTextStyle:" + watermarkTextStyle + ";");
		result.append("watermarkTextAlpha"+watermarkTextAlpha+";");
        result.append("cornerSize:"+cornerSize+";");
		return result.toString();
	}

}
