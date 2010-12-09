/**
 * COPYRIGHT. www.dxtop.net 2008. ALL RIGHTS RESERVED.
 * Project: EasyPhoto
 * Author: Harry Wu <harrywu304@gmail.com>
 * Created On: Oct 14, 2008 11:44:03 AM
 *
*/ 
package org.shaitu.easyphoto.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.regex.Matcher;

import org.shaitu.easyphoto.config.Config;
import org.shaitu.easyphoto.image.BaseDecorativeImage;
import org.shaitu.easyphoto.image.CameraInfoImage;
import org.shaitu.easyphoto.image.DecorativeImage;
import org.shaitu.easyphoto.image.ResizeImage;
import org.shaitu.easyphoto.image.RoundCornerImage;
import org.shaitu.easyphoto.image.WatermarkImage;
import org.shaitu.easyphoto.util.ImageUtil;
import org.shaitu.easyphoto.util.StringUtil;
import org.shaitu.easyphoto.vo.ImageActionParamsVO;
import org.shaitu.easyphoto.vo.ImageActionVO;



/**
 * control and monitor the whole images proceeding
 * @author whx
 *
 */
public class ImageAction {
	/**
	 * log instance
	 */
	private static final Logger logger = Logger.getLogger(ImageAction.class.getName());
	
	/**
	 * thread pool of task thread
	 */
	private ExecutorService threadPool;
	/**
	 * collection of proceed fail images
	 */
	private Vector<String> failImageList = new Vector<String>();
	/**
	 * amount of images ready to proceed
	 */
	private int imgCount = Integer.MAX_VALUE;
	/**
	 * amount of images proceeded
	 */
	private AtomicInteger imgDoneCount ;
	
	/**
	 * get singleton ImageProceedController instance
	 * @return ImageProceedController instance
	 */
	public static ImageAction getInstance(){
		return new ImageAction();
	}
	
	/**
	 * make ImageProceedController can't instanced outside this class 
	 */
	private ImageAction() {
		init();		
	}
	
	/**
	 * initial controller
	 */
	private void init() {
		//get config instance
		Config config = Config.getInstance();
		//initial instance members
		threadPool = Executors.newFixedThreadPool(Integer.parseInt(config.getValue("thread_pool_size")));
		failImageList = new Vector<String>();
		imgDoneCount = new AtomicInteger(0);	}
	
	/**
	 * execute ImageProceedTask
	 * @param doOverride directly override incoming images or not
	 * @param outputDir	folder to locate the output images
	 * @param input	images files or folders
	 */
	public void handle(ImageActionParamsVO params) {
		try{
            String iroot = "";
            if(params.getInputFiles()[0].getParentFile() != null){
                iroot = params.getInputFiles()[0].getParentFile().getAbsolutePath();
            }
			List<File> todoImageList = new ArrayList();
            ImageUtil.findImagesRecusive(todoImageList, params.getInputFiles());
            imgCount = todoImageList.size();
			//iterator images
			for (File ifile : todoImageList) {
				ImageActionVO vo = new ImageActionVO();
				vo.setParams(params);
				File ofile = null;
				//get output file
				if (params.isOverride()) {
					//doOverride is true, just use input file as output file
					ofile = ifile;
				} else {
					//doOverride is false, save images to specified output folder
					String absPath = "";
                    if(!StringUtil.isNullOrBlank(iroot)){
                        absPath = ifile.getAbsolutePath().replaceFirst(Matcher.quoteReplacement(iroot),
                               Matcher.quoteReplacement(vo.getParams().getOutputFolder().getAbsolutePath()));
                    } else {
                        absPath = vo.getParams().getOutputFolder().getAbsolutePath() + File.separator + ifile.getName();
                    }
					ofile = new File(absPath);
				}	
				//logger.info("Input image:"+ifile.getAbsolutePath());
				//logger.info("Output image:"+ofile.getAbsolutePath());
				vo.setInputImageFile(ifile);
				vo.setOutputImageFile(ofile);
				//execute ImageDecorateTask
				threadPool.execute(new ImageDecorateTask(vo));
				Thread.sleep(10);
                //release binding resources
                System.gc();
			}
			threadPool.shutdown();
		}catch(Exception e){
            e.printStackTrace();
		}
	}
	
	/**
	 * shutdown threadPool immediately
	 */
	public void stop() {
		threadPool.shutdownNow();
	}

	/**
	 * get images that proceed fail
	 * @return images name list
	 */
	public Vector<String> getFailImageList() {
		return failImageList;
	}

	/**
	 * check how many images ready to proceed
	 * @return amount of images ready to proceed
	 */
	public int getImgCount() {
		return imgCount;
	}

	/**
	 * check how many images proceeded already
	 * @return amount of images proceeded 
	 */
	public int getImgDoneCount() {
		return imgDoneCount.get();
	}

	/**
	 * check if all images proceeded
	 * @return if all images proceeded return true, otherwise false
	 */
	public boolean isDone() {
		return threadPool.isTerminated();
	}	
	
	/**
	 * kernel image decorate method
	 * @param vo ImageActionVO instance
	 * @return DecorativeImage instance
	 */
	public static DecorativeImage decorateImage(ImageActionVO vo){
		//construct DecorativeImage instance according to requirement 
		DecorativeImage image = new BaseDecorativeImage();
		//apply resize
		if(vo.getParams().isApplyResize()){
			image = new ResizeImage(image);
		}
		//apply corner round
		if(vo.getParams().isApplyRound()){
			image = new RoundCornerImage(image);
		}
        //apply text watermark
        if(vo.getParams().isApplyWatermarkText()){
                image = new WatermarkImage(image);
        }
		//apply exif append
		if(vo.getParams().isApplyExif()){
			image = new CameraInfoImage(image);
		}
		//execute decorative action
		image.decorate(vo);
		return image;
	}

	/**
	 * image proceed thread
	 * @author whx
	 */
	public class ImageDecorateTask implements Runnable {
		/**
		 * vo about proceed conditions
		 */
		ImageActionVO vo = null;

		ImageDecorateTask(ImageActionVO vo) {
			this.vo = vo;
		}

		public void run() {
			try {
				//decorate image
				DecorativeImage image = ImageAction.decorateImage(vo);
				//store image to output file
				image.store(vo);
				imgDoneCount.incrementAndGet();
				logger.info(vo.getInputImageFile().getName() + " decorate Successful :`)");
				image = null;
				vo = null;
			} catch (Exception e) {
				e.printStackTrace();
				//add image name to fail list
				failImageList.add(vo.getInputImageFile().getName());
				logger.warning(vo.getInputImageFile().getName() + " process fail!");
			}
		}
		
	}	
	
}
