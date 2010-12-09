package org.shaitu.easyphoto.vo;

/**
 * vo for app conf options
 * 
 * @author whx
 */
public class AppOptionsVO {
    /**
     * theme
     */
    private String theme;
    /**
     * always on top
     */
    private boolean onTop;
    /**
     * open output folder after proceed finish
     */
    private boolean autoOpenOutput;
    /**
     * language
     */
    private String language;
    /**
     * no ask next output
     */
    private boolean outputNoAsk;
    
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public boolean isOnTop() {
        return onTop;
    }

    public void setOnTop(boolean onTop) {
        this.onTop = onTop;
    }

    public boolean isAutoOpenOutput() {
        return autoOpenOutput;
    }

    public void setAutoOpenOutput(boolean autoOpenOutput) {
        this.autoOpenOutput = autoOpenOutput;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isOutputNoAsk() {
        return outputNoAsk;
    }

    public void setOutputNoAsk(boolean outputNoAsk) {
        this.outputNoAsk = outputNoAsk;
    }

}
