/**
 * COPYRIGHT. www.dxtop.net 2008. ALL RIGHTS RESERVED.
 * Config.java
 * Project: wRounder
 * Author: Harry Wu <harrywu304@gmail.com>
 * Created On: Jun 28, 2008 5:41:24 PM
 *
 */
package org.shaitu.easyphoto.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Properties;

import org.shaitu.easyphoto.AppConstants;
import org.shaitu.easyphoto.util.StringUtil;


/**
 * configuration info
 * 
 * @author whx
 * 
 */
public class Config {

    /**
     * runtime referred properties
     */
    private Properties appProp = null;
    /**
     * Config instance
     */
    private static Config config = new Config();
    /**
     * conf file about app
     */
    private File appConfFile = new File(AppConstants.CONFIG_FILE);
    /**
     * conf file about app of my
     */
    private File appMyConfFile = new File(AppConstants.MY_CONFIG_FILE);
    
    /**
     * make Config to be singleton
     */
    private Config() {
        loadConf();
    }

    /**
     * get Config singleton instance
     *
     * @return Config instance
     */
    public static Config getInstance() {
        return config;
    }

    /**
     * load config properties from files
     */
    public void loadConf() {
        appProp = new Properties();
        try {
            if(appMyConfFile.exists()){
                appProp.load(new InputStreamReader(new FileInputStream(appMyConfFile),"UTF8"));
            } else{
                appProp.load(new InputStreamReader(new FileInputStream(appConfFile),"UTF8"));
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * store app conf to files
     */
    public void storeConf() {
        try {
            appProp.store(new OutputStreamWriter(new FileOutputStream(appMyConfFile),"UTF8"),
                    "configuration for EasyPhoto");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get property value of app conf
     * @param name property name
     * @return property value
     */
    public String getValue(String name) {
        return getValue(name,"");
    }

    /**
     * get property value of app conf
     * @param name property name
     * @param defaultValue default value if null or empty
     * @return property value
     */
    public String getValue(String name,String defaultValue) {
        String result = appProp.getProperty(name);
        if(StringUtil.isNullOrBlank(result)){
            result =  defaultValue;
        }
        return result;
    }

    /**
     * get property value of app conf
     * @param name property name
     * @return property value
     */
    public int getIntValue(String name) {
        return getIntValue(name,-1);
    }

     /**
     * get property value of app conf
     * @param name property name
     * @param defaultValue default value if null or empty
     * @return property value
     */
    public int getIntValue(String name,int defaultValue) {
        if(StringUtil.isNullOrBlank(name)){
            return defaultValue;
        }
        return Integer.valueOf(appProp.getProperty(name));
    }

    /**
     * get property value of app conf
     * @param name property name
     * @return property value
     */
    public boolean getBooleanValue(String name){
        return getBooleanValue(name,false);
    }

    /**
     * get property value of app conf
     * @param name property name
     * @param defaultValue default value if null or empty
     * @return property value
     */
    public boolean getBooleanValue(String name,boolean defaultValue){
        if(StringUtil.isNullOrBlank(name)){
            return defaultValue;
        }
        return Boolean.parseBoolean(appProp.getProperty(name));
    }

    /**
     * set app conf value
     * @param name property name
     * @param value property value
     */
    public void setValue(String name, String value) {
        appProp.setProperty(name, value);
    }

     /**
     * set app conf value
     * @param name property name
     * @param value property value
     */
    public void setValue(String name,int value){
       appProp.setProperty(name, String.valueOf(value));
    }
    
    /**
     * set app conf value
     * @param name property name
     * @param value property value
     */
    public void setValue(String name,boolean value){
       appProp.setProperty(name, String.valueOf(value));
    }
}
