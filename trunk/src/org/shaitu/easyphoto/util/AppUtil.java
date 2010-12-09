/**
 * COPYRIGHT. www.dxtop.net 2008. ALL RIGHTS RESERVED.
 * Project: EasyPhoto
 * Author: Harry Wu <harrywu304@gmail.com>
 * Created On: Jan 10, 2009 6:15:21 PM
 *
 */
package org.shaitu.easyphoto.util;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.shaitu.easyphoto.AppConstants;
import org.shaitu.easyphoto.config.Config;
import org.shaitu.easyphoto.vo.AppOptionsVO;
import org.shaitu.easyphoto.vo.ImageActionParamsVO;



/**
 * tools about app
 * 
 * @author whx
 */
public class AppUtil {

    /**
     * remember operation params in last image proceed action
     * @param vo
     */
    public static void rememberOperParams(ImageActionParamsVO vo) {
        try{
            Config config = Config.getInstance();
            //image choose
            if(vo.getInputFiles() != null && vo.getInputFiles().length > 0){
                config.setValue("input_files", vo.getInputFiles()[0].getAbsolutePath());
            }
            if(vo.getOutputFolder() != null){
                config.setValue("output_folder", vo.getOutputFolder().getAbsolutePath());
            }
            config.setValue("override", vo.isOverride());
            //export options
            config.setValue("quality", vo.getQuality());
            config.setValue("apply_resize", vo.isApplyResize());
            config.setValue("resize", vo.getResize());
            config.setValue("resize_index", vo.getResizeIndex());
            //corner round
            config.setValue("apply_round", vo.isApplyRound());
            config.setValue("corner_size", vo.getCornerSize());
            //exif info
            config.setValue("apply_exif", vo.isApplyExif());
            config.setValue("exif_style", vo.getExifStyle());
            config.setValue("append_camera", vo.isAppendCamera());
            config.setValue("append_date", vo.isAppendDate());
            config.setValue("append_apt", vo.isAppendApt());
            config.setValue("append_exp", vo.isAppendExp());
            config.setValue("append_focal", vo.isAppendFocal());
            config.setValue("append_iso", vo.isAppendIso());
            config.setValue("sign", vo.getSign());
            config.setValue("exif_font", UIUtil.getFontDescription(vo.getExifFont()));
            //text watermark
            config.setValue("apply_text_watermark", vo.isApplyWatermarkText());
            config.setValue("text_watermark_style", vo.getWatermarkTextStyle());
            config.setValue("text_watermark", vo.getWatermarkText());
            config.setValue("text_watermakr_font", UIUtil.getFontDescription(vo.getWatermarkTextFont()));
            config.setValue("text_watermakr_color", vo.getWatermarkColor().getRGB());
            config.setValue("text_watermark_alpha", vo.getWatermarkTextAlpha());
            config.storeConf();
        }catch(Exception e){
            e.printStackTrace();;
        }
    }
    /**
     * retrieve user last operations params
     * @return
     */
    public static ImageActionParamsVO retrieveOperParams(){
        try{
            Config config = Config.getInstance();
            ImageActionParamsVO vo = new ImageActionParamsVO();
            //image choose
            if(!StringUtil.isNullOrBlank(config.getValue("input_files"))){
                File[] inputFiles = {new File(config.getValue("input_files"))};
                vo.setInputFiles(inputFiles);
            }
            if(!StringUtil.isNullOrBlank(config.getValue("output_folder"))){
                vo.setOutputFolder(new File(config.getValue("output_folder")));
            }
            vo.setOverride(config.getBooleanValue("override"));
            //export
            vo.setQuality(config.getIntValue("quality",AppConstants.DEFAULT_QUALITY));
            vo.setApplyResize(config.getBooleanValue("apply_resize"));
            vo.setResize(config.getIntValue("resize",AppConstants.DEFAULT_RESIZE));
            vo.setResizeIndex(config.getIntValue("resize_index",AppConstants.DEFAULT_RESIZE_INDEX));
            //corner round
            vo.setApplyRound(Boolean.valueOf(config.getValue("apply_round")));
            vo.setCornerSize(config.getIntValue("corner_size",AppConstants.DEFAULT_CORNER_SIZE));
            //exif info
            vo.setApplyExif(Boolean.valueOf(config.getValue("apply_exif")));
            vo.setExifStyle(config.getValue("exif_style"));
            vo.setAppendApt(Boolean.valueOf(config.getValue("append_apt")));
            vo.setAppendCamera(Boolean.valueOf(config.getValue("append_camera")));
            vo.setAppendDate(Boolean.valueOf(config.getValue("append_date")));
            vo.setAppendExp(Boolean.valueOf(config.getValue("append_exp")));
            vo.setAppendFocal(Boolean.valueOf(config.getValue("append_focal")));
            vo.setAppendIso(Boolean.valueOf(config.getValue("append_iso")));
            String sign = config.getValue("sign");
            vo.setSign((sign==null)?"":sign);
            if(!StringUtil.isNullOrBlank(config.getValue("exif_font"))){
                vo.setExifFont(Font.decode(config.getValue("exif_font")));
            }
            //text watermark
            vo.setApplyWatermarkText(config.getBooleanValue("apply_text_watermark"));
            vo.setWatermarkTextStyle(config.getValue("text_watermark_style"));
            vo.setWatermarkText(config.getValue("text_watermark"));
            if(!StringUtil.isNullOrBlank(config.getValue("text_watermakr_font"))){
                vo.setWatermarkTextFont(Font.decode(config.getValue("text_watermakr_font")));
            }
            if(!StringUtil.isNullOrBlank(config.getValue("text_watermakr_color"))){
                vo.setWatermarkColor(Color.decode(config.getValue("text_watermakr_color")));
            }
            vo.setWatermarkTextAlpha(config.getIntValue("text_watermark_alpha",0));
            return vo;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
	
    /**
     * remember app conf options
     * @param vo
     */
    public static void rememberAppConf(AppOptionsVO vo) {
        try{
            Config config = Config.getInstance();
            config.setValue("theme", vo.getTheme());
            config.setValue("ontop", vo.isOnTop());
            config.setValue("auto_open_output", vo.isAutoOpenOutput());
            config.setValue("language", vo.getLanguage());
            config.setValue("output_no_ask", vo.isOutputNoAsk());
            config.storeConf();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * retrieve app conf
     * @return
     */
    public static AppOptionsVO retrieveAppConf(){
        try{
            Config config = Config.getInstance();
            AppOptionsVO vo = new AppOptionsVO();
            vo.setOnTop(config.getBooleanValue("ontop"));
            vo.setAutoOpenOutput(config.getBooleanValue("auto_open_output"));
            vo.setTheme(config.getValue("theme"));
            if(StringUtil.isNullOrBlank(config.getValue("language"))){
                vo.setLanguage(Locale.getDefault().getLanguage());
            } else {
                vo.setLanguage(config.getValue("language"));
            }
            vo.setOutputNoAsk(config.getBooleanValue("output_no_ask"));
            return vo;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * check if update check needed
     * @return 
     */
    public static boolean isUpdateCheckNeed(){
        Config config = Config.getInstance();
        String lastCheckUpdate = config.getValue("last_check_update");
        String todayStr = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
        if(todayStr.equals(lastCheckUpdate)){
            return false;
        }   
        return true;
    }
    
    /**
     * remember this update check action
     */
    public static void rememberUpdateCheck(){
        Config config = Config.getInstance();
        String todayStr = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
        config.setValue("last_check_update", todayStr);
        config.storeConf();
    }

    /**
     * restart app
     */
    public static void restartApp(){
    	try {
    		ProcessBuilder pb=new ProcessBuilder("EasyPhoto");
    		pb.directory(new File(System.getProperty("user.dir")));
			pb.start();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
