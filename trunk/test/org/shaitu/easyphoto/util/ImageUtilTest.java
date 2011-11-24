package org.shaitu.easyphoto.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

public class ImageUtilTest {

	@Test
	public void testCreateNewImage() throws IOException {
		String[] images = { "sample/sample01.jpg", "sample/sample02.jpg" };
		for (String imgFile : images) {
			long t1 = System.currentTimeMillis();
			BufferedImage srcImg = ImageIO
					.read(new File(imgFile));
			BufferedImage outImg = ImageUtil.createNewImage(srcImg, 512);
			long t2 = System.currentTimeMillis();
			System.out.println("crop image [" + imgFile + "] cost time:" + (t2 - t1) + "ms");
			ImageUtil.storeImage(outImg, new File(imgFile+".crop.jpg"),
					0.85f);
			long t3 = System.currentTimeMillis();
		}

	}

}
