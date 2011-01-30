/**
 * COPYRIGHT. Harry Wu 2010. ALL RIGHTS RESERVED.
 * Project: ThirdEye
 * Author: Harry Wu <harrywu304@gmail.com>
 * Created On: Oct 13, 2008 4:57:38 PM
 *
*/ 
package org.shaitu.easyphoto.image;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

import org.shaitu.easyphoto.vo.ImageActionVO;




/**
 * Decorator to round image corners
 * @author whx
 *
 */
public class RoundCornerImage extends BaseDecorativeImage {
	/**
	 * log instance
	 */
	private static final Logger logger = Logger.getLogger(RoundCornerImage.class.getName());
	
	/**
	 * RoundCornerImage constructor
	 * @param image image for wrap
	 */
	public RoundCornerImage(DecorativeImage di){
		super(di);
	}

	/* (non-Javadoc)
	 * @see net.dxtop.thirdeye.image.DecorativeImage#decorate()
	 */
	@Override
	public boolean decorate(ImageActionVO vo) {
		di.decorate(vo);
		this.round(vo);
		return true;
	}
	
	/**
	 * make image corners round
	 * @return success return true, otherwise false
	 */
	private boolean round(ImageActionVO vo){
		BufferedImage srcImg = vo.getBufferedImage();
		int srcWidth = srcImg.getWidth();
		int srcHeight = srcImg.getHeight();
		Graphics2D g = srcImg.createGraphics();
		g.setColor(Color.WHITE);
		Area rect = new Area(new Rectangle2D.Double(0,0,srcWidth,srcHeight));
		Area rectRound = new Area(new RoundRectangle2D.Double(0,0,srcWidth,srcHeight,
                vo.getParams().getCornerSize(),vo.getParams().getCornerSize()));
		rect.exclusiveOr(rectRound);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.clip(rect);
		g.fill(g.getClip());
		g.dispose();
		return true;
	}
}
