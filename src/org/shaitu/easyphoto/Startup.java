/**
 * COPYRIGHT. www.dxtop.net 2008. ALL RIGHTS RESERVED.
 * Project: EasyPhotoSimple
 * Author: Harry Wu <harrywu304@gmail.com>
 * Created On: Jun 28, 2008 5:12:21 PM
 *
 */
package org.shaitu.easyphoto;

import java.io.File;
import java.util.Locale;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.shaitu.easyphoto.config.Config;
import org.shaitu.easyphoto.ui.EasyPhoto;
import org.shaitu.easyphoto.util.FileUtil;
import org.shaitu.easyphoto.util.StringUtil;


/**
 * Starter of EasyPhotoSimple
 * @author whx
 */
public class Startup {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	//run EasyPhotoSimple
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	long begin = System.currentTimeMillis();
                //initial language
                if(!StringUtil.isNullOrBlank(Config.getInstance().getValue("language"))
                        && !Locale.getDefault().getLanguage().equalsIgnoreCase(
                            Config.getInstance().getValue("language"))){
                        Locale.setDefault(new Locale(Config.getInstance().getValue("language")));
                }
            	//initial look and feel
            	javax.swing.JFrame.setDefaultLookAndFeelDecorated(true);
                javax.swing.JDialog.setDefaultLookAndFeelDecorated(true);
                try {
                        String theme = Config.getInstance().getValue("theme");
                        if(StringUtil.isNullOrBlank(theme)){
                            theme = AppConstants.DEFAULT_THEME;
                        }
                        UIManager.setLookAndFeel(theme);
                } catch (Exception e) {
                        Logger.getLogger(Startup.class.getName()).severe("Initial look and feel fail!!");
                }
                new EasyPhoto().setVisible(true);
                Logger.getLogger(Startup.class.getName()).info("EP startup cost time:"
                		+(System.currentTimeMillis()-begin)+"ms");
                
            }
        });
        //add shutdown hook for auto update
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    //get app install dir
                    String installDir = FileUtil.getWorkingDir();
                    File curJarFile = new File(installDir + "EasyPhoto.jar");
                    File updateJarFile = new File(installDir + "EasyPhoto.jar.update");
                    File bakJarFile = new File(installDir + "EasyPhoto.jar.bak");
                    //check if update jar file exist
                    if(updateJarFile.exists()){
                        //backup current jar file
                        FileUtil.copyFile(curJarFile, bakJarFile);
                        //replace current jar file with update jar file
                        FileUtil.copyFile(updateJarFile, curJarFile);
                        //remove update jar file
                        updateJarFile.delete();
                        Logger.getLogger(Startup.class.getName()).fine("App updated!!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
