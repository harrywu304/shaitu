/**
 * COPYRIGHT. Harry Wu 2010. ALL RIGHTS RESERVED.
 * Project: ThirdEye
 * Author: Harry Wu <harrywu304@gmail.com>
 * Created On: Oct 16, 2008 9:05:44 AM
 *
*/ 
package org.shaitu.easyphoto.vo;

/**
 * bean about option in drop down list
 * @author harry
 */
public class OptionBean {
    /**
     * name of option
     */
    private String name;
    /**
     * value of option
     */
    private String value;

    public OptionBean(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public OptionBean(String name, Object value) {
        this.name = name;
        this.value = value.toString();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    @Override
    public String toString(){
        return name;
    }

    @Override
    public int hashCode(){
        int result = 17;
        result +=37*result + value.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
       if(!(obj instanceof OptionBean)){
            return false;
       }
       return this.hashCode() == ((OptionBean)obj).hashCode();
    }
    
}
