package org.shaitu.easyphoto.image;

import java.awt.AlphaComposite;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import org.shaitu.easyphoto.AppConstants;
import org.shaitu.easyphoto.util.StringUtil;
import org.shaitu.easyphoto.vo.ImageActionVO;



public class WatermarkImage extends BaseDecorativeImage {

    /**
     * RoundCornerImage constructor
     * 
     * @param image
     *            image for wrap
     */
    public WatermarkImage(DecorativeImage di) {
        super(di);
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.dxtop.thirdeye.image.DecorativeImage#decorate()
     */
    @Override
    public boolean decorate(ImageActionVO vo) {
        di.decorate(vo);
        this.pressWatermark(vo);
        return true;
    }

    /**
     * press watermark to image
     * 
     * @param vo
     */
    private void pressWatermark(ImageActionVO vo) {
        // press text watermark
        if (vo.getParams().isApplyWatermarkText()) {
            pressWatermarkText(vo);
        }
    }

    /**
     * press text watermark
     * @param vo
     * @return
     */
    private boolean pressWatermarkText(ImageActionVO vo) {
        try {
            String text = vo.getParams().getWatermarkText();
            if (StringUtil.isNullOrBlank(text)) {
                return true;
            }
            float alpha = (100-vo.getParams().getWatermarkTextAlpha())*1.0f/100;
            BufferedImage image = vo.getBufferedImage();
            int sw = image.getWidth();
            int sh = image.getHeight();
            Graphics2D g = image.createGraphics();
            g.setColor(vo.getParams().getWatermarkColor());
            g.setFont(vo.getParams().getWatermarkTextFont());
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    alpha));
    		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
    				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            FontMetrics metrics = g.getFontMetrics();
            int infoWidth = metrics.stringWidth(text);
            int infoDescent = metrics.getDescent();
            if(AppConstants.WATERMARK_STYLE_CROSS.equalsIgnoreCase(vo.getParams().getWatermarkTextStyle())){
                //cross text watermark style
                g.rotate(Math.toRadians(-45),sw*1.0f/2,sh*1.0f/2);
                g.drawString(vo.getParams().getWatermarkText(), (sw- infoWidth)/2, sh/2);
            } else if(AppConstants.WATERMARK_STYLE_TINY.equalsIgnoreCase(vo.getParams().getWatermarkTextStyle())){
                g.drawString(vo.getParams().getWatermarkText(), sw - infoWidth - 10, sh - infoDescent-10);  
            }
            g.dispose();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
