package org.shaitu.easyphoto.util;

import static org.junit.Assert.fail;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

public class ImageUtilTest {

	@Test
	public void testCreateNewImage() throws IOException {
		long t1 = System.currentTimeMillis();
		BufferedImage srcImg = ImageIO.read(new File("sample/sample01.jpg"));
		BufferedImage outImg = ImageUtil.createNewImage(srcImg, 512);
		long t2 = System.currentTimeMillis();
		System.out.println("crop image cost time:"+(t2-t1)+"ms");
		ImageUtil.storeImage(outImg, new File("sample/sample01_crop.jpg"), 0.85f);
		long t3 = System.currentTimeMillis();
		System.out.println("crop&save image cost time:"+(t3-t1)+"ms");
	}

}
