/**
 * COPYRIGHT. Harry Wu 2010. ALL RIGHTS RESERVED.
 * Project: ThirdEye
 * Author: Harry Wu <harrywu304@gmail.com>
 * Created On: Oct 13, 2008 4:57:38 PM
 *
*/ 
package org.shaitu.easyphoto.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.shaitu.easyphoto.AppConstants;
import org.shaitu.easyphoto.action.ImageAction;
import org.shaitu.easyphoto.config.Config;
import org.shaitu.easyphoto.util.AppUtil;
import org.shaitu.easyphoto.util.CollectionsUtil;
import org.shaitu.easyphoto.util.FileUtil;
import org.shaitu.easyphoto.util.ImageUtil;
import org.shaitu.easyphoto.util.UIUtil;
import org.shaitu.easyphoto.vo.AppOptionsVO;
import org.shaitu.easyphoto.vo.ImageActionParamsVO;
import org.shaitu.easyphoto.vo.ImageActionVO;
import org.shaitu.easyphoto.vo.OptionBean;

import say.swing.JFontChooser;

/**
 *
 * @author  harry
 */
public class EasyPhoto extends javax.swing.JFrame {   
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 465237250302109674L;
	
	/** Creates new form EasyPhoto */
    public EasyPhoto() { 
        initComponents();
        initApp();
        setLocationRelativeTo(null);
        pack();
    }
    
    /**
     * load app conf data
     */
    public void initApp(){
        isInitialing = true;
        //initial frame icon
        javax.swing.ImageIcon titleIcon16 = new javax.swing.ImageIcon(EasyPhoto.class.getResource("/resources/icon/title_logo_16.png"));
        javax.swing.ImageIcon titleIcon32 = new javax.swing.ImageIcon(EasyPhoto.class.getResource("/resources/icon/image_icon_32.png"));
        icons.add(titleIcon16.getImage());
        icons.add(titleIcon32.getImage());
        this.setIconImages(icons);
        fmPreview.setIconImage(titleIcon16.getImage());
        fmOptions.setIconImage(titleIcon16.getImage());
        //reset app conf
        AppOptionsVO optionsVO = AppUtil.retrieveAppConf();
        cbTheme.setSelectedItem(new OptionBean("",optionsVO.getTheme()));
        cbLanguage.setSelectedItem(new OptionBean("",optionsVO.getLanguage()));
        //set always on top action
        cbOnTop.setSelected(optionsVO.isOnTop());
        cbOnTopActionPerformed(null);
        cbAutoOpen.setSelected(optionsVO.isAutoOpenOutput());
        cbOutputNoAsk.setSelected(optionsVO.isOutputNoAsk());
        //reset image operation param
        ImageActionParamsVO paramsVO = AppUtil.retrieveOperParams();
        if(paramsVO != null){
            //reset image choose
            if(paramsVO.getOutputFolder() != null){
                outputFolder = paramsVO.getOutputFolder();
            }
            cbOverride.setSelected(paramsVO.isOverride());
            //image export
            sdQuality.setValue(paramsVO.getQuality());
            tfQuality.setText(String.valueOf(sdQuality.getValue()));
            sdSize.setValue(paramsVO.getResizeIndex());
            javax.swing.JLabel lbSize = (javax.swing.JLabel)sdSize.getLabelTable().get(paramsVO.getResizeIndex());
            tfSize.setText(lbSize.getText());
            //corner round
            cbApplyRound.setSelected(paramsVO.isApplyRound());
            sdCornerSize.setValue(paramsVO.getCornerSize());
            //exif
            cbApplyExif.setSelected(paramsVO.isApplyExif());
            UIUtil.retrieveButtonGroup(btgStyle, paramsVO.getExifStyle());
            if(paramsVO.getExifFont() != null){
                exifFont = paramsVO.getExifFont();
            }
            tfExifSign.setText(paramsVO.getSign());
            cbExifCamera.setSelected(paramsVO.isAppendCamera());
            cbExifApt.setSelected(paramsVO.isAppendApt());
            cbExifDate.setSelected(paramsVO.isAppendDate());
            cbExifExp.setSelected(paramsVO.isAppendExp());
            cbExifFocal.setSelected(paramsVO.isAppendFocal());
            cbExifIso.setSelected(paramsVO.isAppendIso());
            //text watermark
            cbApplyTextWm.setSelected(paramsVO.isApplyWatermarkText());
            UIUtil.retrieveButtonGroup(btgWmStyle, paramsVO.getWatermarkTextStyle());
            tfWmText.setText(paramsVO.getWatermarkText());
            if(paramsVO.getWatermarkTextFont() != null){
                wmTextFont = paramsVO.getWatermarkTextFont();
            }
            if(paramsVO.getWatermarkColor() != null){
                wmTextColor = paramsVO.getWatermarkColor();
            }
            sdWmTextOpaque.setValue(paramsVO.getWatermarkTextAlpha());
        }
        //init preview image list
        //setInputFiles(new File[]{new File("sample")});
        //auto check update
        if(AppUtil.isUpdateCheckNeed()){
            doCheckUpdateAction(true);
            AppUtil.rememberUpdateCheck();           
        }
        isInitialing = false;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fcOutput = new javax.swing.JFileChooser();
        fcOutput.setFileView(new ImageFileView());
        fcOutput.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fcOutput.setMultiSelectionEnabled(false);
        btgStyle = new javax.swing.ButtonGroup();
        btgWmStyle = new javax.swing.ButtonGroup();
        fmPreview = new EscapeFrame();
        lbPreviewBig = new javax.swing.JLabel();
        fmOptions = new javax.swing.JFrame();
        panOptions = new javax.swing.JTabbedPane();
        panExif = new javax.swing.JPanel();
        lbExifStyle = new javax.swing.JLabel();
        rbExifStyleDouble = new javax.swing.JRadioButton();
        lbExifStyleDouble = new javax.swing.JLabel();
        rbExifStyleSingle = new javax.swing.JRadioButton();
        lbExifStyleSingle = new javax.swing.JLabel();
        rbExifStyleRound = new javax.swing.JRadioButton();
        lbExifStyleRound = new javax.swing.JLabel();
        lbExifFont = new javax.swing.JLabel();
        btExifFont = new javax.swing.JButton();
        lbExifContent = new javax.swing.JLabel();
        cbExifCamera = new javax.swing.JCheckBox();
        cbExifExp = new javax.swing.JCheckBox();
        cbExifApt = new javax.swing.JCheckBox();
        cbExifFocal = new javax.swing.JCheckBox();
        cbExifDate = new javax.swing.JCheckBox();
        cbExifIso = new javax.swing.JCheckBox();
        tfExifSign = new javax.swing.JTextField();
        lbSign = new javax.swing.JLabel();
        panTextWm = new javax.swing.JPanel();
        lbWmText = new javax.swing.JLabel();
        tfWmText = new javax.swing.JTextField();
        lbWmTextStyle = new javax.swing.JLabel();
        rbWmTextStyleTiny = new javax.swing.JRadioButton();
        rbWmTextStyleCross = new javax.swing.JRadioButton();
        lbWmTextStyleTiny = new javax.swing.JLabel();
        lbWmTextStyleCross = new javax.swing.JLabel();
        btWmTextColor = new javax.swing.JButton();
        lbWmTextOpaque = new javax.swing.JLabel();
        sdWmTextOpaque = new javax.swing.JSlider();
        lbWmTextFont = new javax.swing.JLabel();
        btWmTextFont = new javax.swing.JButton();
        panRound = new javax.swing.JPanel();
        sdCornerSize = new javax.swing.JSlider();
        lbCornerSize = new javax.swing.JLabel();
        panExport = new javax.swing.JPanel();
        lbResize = new javax.swing.JLabel();
        lbQuality = new javax.swing.JLabel();
        tfSize = new javax.swing.JTextField();
        lbPixel = new javax.swing.JLabel();
        tfOutput = new javax.swing.JTextField();
        cbOverride = new javax.swing.JCheckBox();
        sdSize = new javax.swing.JSlider();
        sdQuality = new javax.swing.JSlider();
        tfQuality = new javax.swing.JTextField();
        lbPercent = new javax.swing.JLabel();
        cbOutputNoAsk = new javax.swing.JCheckBox();
        lbOutputFolder = new javax.swing.JLabel();
        panApp = new javax.swing.JPanel();
        lbTheme = new javax.swing.JLabel();
        lbOnTop = new javax.swing.JLabel();
        cbOnTop = new javax.swing.JCheckBox();
        cbTheme = new javax.swing.JComboBox();
        lbAutoOpen = new javax.swing.JLabel();
        cbAutoOpen = new javax.swing.JCheckBox();
        lbCheckUpdate = new javax.swing.JLabel();
        btCheckUpdate = new javax.swing.JButton();
        lbLanguage = new javax.swing.JLabel();
        cbLanguage = new javax.swing.JComboBox();
        btUpdatePreview = new javax.swing.JButton();
        btConfirm = new javax.swing.JButton();
        dgAbout = new javax.swing.JDialog();
        panAbout = new javax.swing.JPanel();
        lbAboutLogo = new javax.swing.JLabel();
        epAboutInfo = new javax.swing.JEditorPane();
        panMain = new javax.swing.JPanel();
        lbPreviewSmall = new javax.swing.JLabel();
        panImageNavigate = new javax.swing.JPanel();
        lbNext = new javax.swing.JButton();
        lbPrevious = new javax.swing.JButton();
        lbPreviewInfo = new javax.swing.JLabel();
        panControl = new javax.swing.JPanel();
        btOptions = new javax.swing.JButton();
        btAbout = new javax.swing.JButton();
        btCancle = new javax.swing.JButton();
        btCancle.setVisible(false);
        btExport = new javax.swing.JButton();
        btPreviewBig = new javax.swing.JButton();
        cbApplyExif = new javax.swing.JCheckBox();
        cbApplyRound = new javax.swing.JCheckBox();
        cbApplyTextWm = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();

        fcOutput.setDialogTitle(messageMapping.getString("dialog.title.output")); // NOI18N

        fmPreview.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        fmPreview.setName("previewFrame"); // NOI18N
        fmPreview.setResizable(false);
        fmPreview.getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        lbPreviewBig.setToolTipText(messageMapping.getString("tooltip.preview")); // NOI18N
        lbPreviewBig.setDoubleBuffered(true);
        lbPreviewBig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                previewHandler(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                previewMoursePressHandler(evt);
            }
        });
        lbPreviewBig.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                previewDragHandler(evt);
            }
        });
        fmPreview.getContentPane().add(lbPreviewBig);

        fmOptions.setTitle(messageMapping.getString("dialog.options.title")); // NOI18N
        fmOptions.setResizable(false);

        lbExifStyle.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbExifStyle.setText(messageMapping.getString("label.style")); // NOI18N

        btgStyle.add(rbExifStyleDouble);
        rbExifStyleDouble.setSelected(true);
        rbExifStyleDouble.setActionCommand("double"); // NOI18N

        lbExifStyleDouble.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/style01.png"))); // NOI18N
        lbExifStyleDouble.setLabelFor(rbExifStyleDouble);

        btgStyle.add(rbExifStyleSingle);
        rbExifStyleSingle.setActionCommand("single"); // NOI18N
        rbExifStyleSingle.setIconTextGap(10);

        lbExifStyleSingle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/style02.png"))); // NOI18N
        lbExifStyleSingle.setLabelFor(rbExifStyleSingle);
        lbExifStyleSingle.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btgStyle.add(rbExifStyleRound);
        rbExifStyleRound.setActionCommand("round"); // NOI18N

        lbExifStyleRound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/style03.png"))); // NOI18N
        lbExifStyleRound.setLabelFor(rbExifStyleRound);

        lbExifFont.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbExifFont.setText(messageMapping.getString("label.font")); // NOI18N

        btExifFont.setText(messageMapping.getString("button.setfont")); // NOI18N
        btExifFont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExifFontActionPerformed(evt);
            }
        });

        lbExifContent.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbExifContent.setText(messageMapping.getString("label.content")); // NOI18N

        cbExifCamera.setSelected(true);
        cbExifCamera.setText(messageMapping.getString("label.exif.model")); // NOI18N

        cbExifExp.setSelected(true);
        cbExifExp.setText(messageMapping.getString("label.exif.exposuretime")); // NOI18N

        cbExifApt.setSelected(true);
        cbExifApt.setText(messageMapping.getString("label.exif.fnumber")); // NOI18N

        cbExifFocal.setSelected(true);
        cbExifFocal.setText(messageMapping.getString("label.exif.focallength")); // NOI18N

        cbExifDate.setText(messageMapping.getString("label.exif.date")); // NOI18N

        cbExifIso.setText(messageMapping.getString("label.exif.iso")); // NOI18N

        tfExifSign.setColumns(10);

        lbSign.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbSign.setLabelFor(tfExifSign);
        lbSign.setText(messageMapping.getString("label.sign")); // NOI18N

        javax.swing.GroupLayout panExifLayout = new javax.swing.GroupLayout(panExif);
        panExif.setLayout(panExifLayout);
        panExifLayout.setHorizontalGroup(
            panExifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panExifLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panExifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbSign, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbExifContent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbExifFont, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                    .addComponent(lbExifStyle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panExifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panExifLayout.createSequentialGroup()
                        .addComponent(rbExifStyleDouble)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbExifStyleDouble)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbExifStyleSingle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbExifStyleSingle)
                        .addGap(11, 11, 11)
                        .addComponent(rbExifStyleRound)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbExifStyleRound, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btExifFont)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panExifLayout.createSequentialGroup()
                        .addGroup(panExifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfExifSign, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                            .addGroup(panExifLayout.createSequentialGroup()
                                .addGroup(panExifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbExifCamera, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                    .addComponent(cbExifExp))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panExifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbExifApt, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbExifFocal))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panExifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbExifDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbExifIso, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(104, 104, 104))
        );
        panExifLayout.setVerticalGroup(
            panExifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panExifLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panExifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbExifStyle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbExifStyleDouble)
                    .addComponent(lbExifStyleDouble)
                    .addComponent(lbExifStyleSingle)
                    .addComponent(rbExifStyleSingle)
                    .addComponent(rbExifStyleRound)
                    .addComponent(lbExifStyleRound))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panExifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbExifFont, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btExifFont, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panExifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cbExifCamera)
                    .addComponent(lbExifContent, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbExifApt)
                    .addComponent(cbExifDate))
                .addGroup(panExifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panExifLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbExifExp))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panExifLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panExifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(cbExifFocal)
                            .addComponent(cbExifIso))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panExifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfExifSign, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSign, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        panOptions.addTab(messageMapping.getString("tab.exif"), panExif); // NOI18N

        lbWmText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbWmText.setText(messageMapping.getString("label.content")); // NOI18N

        tfWmText.setText(messageMapping.getString("EasyPhoto.tfWmText.text")); // NOI18N

        lbWmTextStyle.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbWmTextStyle.setText(messageMapping.getString("EasyPhoto.lbWmTextStyle.text")); // NOI18N

        btgWmStyle.add(rbWmTextStyleTiny);
        rbWmTextStyleTiny.setSelected(true);
        rbWmTextStyleTiny.setActionCommand(AppConstants.WATERMARK_STYLE_TINY);

        btgWmStyle.add(rbWmTextStyleCross);
        rbWmTextStyleCross.setActionCommand(AppConstants.WATERMARK_STYLE_CROSS);

        lbWmTextStyleTiny.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/wm_style_tiny.png"))); // NOI18N

        lbWmTextStyleCross.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/wm_style_cross.png"))); // NOI18N

        btWmTextColor.setText(messageMapping.getString("EasyPhoto.btWmTextColor.text")); // NOI18N
        btWmTextColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btWmTextColorActionPerformed(evt);
            }
        });

        lbWmTextOpaque.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbWmTextOpaque.setText(messageMapping.getString("EasyPhoto.lbWmTextOpaque.text")); // NOI18N

        sdWmTextOpaque.setValue(0);

        lbWmTextFont.setText(messageMapping.getString("label.font")); // NOI18N

        btWmTextFont.setText(messageMapping.getString("button.setfont")); // NOI18N
        btWmTextFont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btWmTextFontActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panTextWmLayout = new javax.swing.GroupLayout(panTextWm);
        panTextWm.setLayout(panTextWmLayout);
        panTextWmLayout.setHorizontalGroup(
            panTextWmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panTextWmLayout.createSequentialGroup()
                .addGroup(panTextWmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panTextWmLayout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(rbWmTextStyleTiny)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbWmTextStyleTiny)
                        .addGap(10, 10, 10)
                        .addComponent(rbWmTextStyleCross)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbWmTextStyleCross))
                    .addGroup(panTextWmLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(panTextWmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panTextWmLayout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(btWmTextFont)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btWmTextColor, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbWmTextFont)))
                    .addGroup(panTextWmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panTextWmLayout.createSequentialGroup()
                            .addComponent(lbWmText)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfWmText, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panTextWmLayout.createSequentialGroup()
                            .addGroup(panTextWmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbWmTextOpaque, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbWmTextStyle, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(sdWmTextOpaque, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(160, Short.MAX_VALUE))
        );
        panTextWmLayout.setVerticalGroup(
            panTextWmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panTextWmLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panTextWmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbWmTextStyleCross)
                    .addComponent(rbWmTextStyleCross)
                    .addComponent(lbWmTextStyleTiny)
                    .addComponent(rbWmTextStyleTiny)
                    .addComponent(lbWmTextStyle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panTextWmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btWmTextFont)
                    .addComponent(lbWmTextFont)
                    .addComponent(btWmTextColor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panTextWmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbWmText)
                    .addComponent(tfWmText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(panTextWmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbWmTextOpaque)
                    .addComponent(sdWmTextOpaque, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        panOptions.addTab(messageMapping.getString("EasyPhoto.panTextWm.TabConstraints.tabTitle"), panTextWm); // NOI18N

        sdCornerSize.setMinimum(10);
        sdCornerSize.setValue(30);

        lbCornerSize.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbCornerSize.setText(messageMapping.getString("EasyPhoto.lbCornerSize.text")); // NOI18N

        javax.swing.GroupLayout panRoundLayout = new javax.swing.GroupLayout(panRound);
        panRound.setLayout(panRoundLayout);
        panRoundLayout.setHorizontalGroup(
            panRoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panRoundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbCornerSize, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sdCornerSize, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(149, Short.MAX_VALUE))
        );
        panRoundLayout.setVerticalGroup(
            panRoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panRoundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panRoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(sdCornerSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCornerSize, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(188, Short.MAX_VALUE))
        );

        panOptions.addTab(messageMapping.getString("tab.cornerround"), panRound); // NOI18N

        lbResize.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbResize.setText(messageMapping.getString("label.resize")); // NOI18N

        lbQuality.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbQuality.setText(messageMapping.getString("label.quality")); // NOI18N

        tfSize.setColumns(5);
        tfSize.setText(String.valueOf(AppConstants.DEFAULT_RESIZE));

        lbPixel.setText(messageMapping.getString("label.pixel")); // NOI18N
        lbPixel.setPreferredSize(new java.awt.Dimension(30, 21));
        lbPixel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        tfOutput.setEditable(false);
        tfOutput.setToolTipText(messageMapping.getString("tooltip.outputfield")); // NOI18N
        tfOutput.setName("imgoutput"); // NOI18N
        tfOutput.setTransferHandler(new DndTransferHandler(this));

        cbOverride.setText(messageMapping.getString("EasyPhoto.lbOverride.text")); // NOI18N

        sdSize.setMajorTickSpacing(1);
        sdSize.setMaximum(7);
        sdSize.setMinimum(1);
        sdSize.setPaintLabels(true);
        sdSize.setPaintTicks(true);
        sdSize.setSnapToTicks(true);
        sdSize.setValue(AppConstants.DEFAULT_RESIZE_INDEX);
        Hashtable rsLabelTable = new Hashtable();
        rsLabelTable.put( new Integer( 1 ), new JLabel(messageMapping.getString("label.initial")) );
        rsLabelTable.put( new Integer( 2 ), new JLabel("480") );
        rsLabelTable.put( new Integer( 3 ), new JLabel("640") );
        rsLabelTable.put( new Integer( 4 ), new JLabel("800") );
        rsLabelTable.put( new Integer( 5 ), new JLabel("1024") );
        rsLabelTable.put( new Integer( 6 ), new JLabel("1200") );
        rsLabelTable.put( new Integer( 7 ), new JLabel("1600") );
        sdSize.setLabelTable( rsLabelTable );
        sdSize.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sdSizeHandler(evt);
            }
        });

        sdQuality.setValue(AppConstants.DEFAULT_QUALITY);
        sdQuality.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sdQualityHandler(evt);
            }
        });

        tfQuality.setColumns(3);
        tfQuality.setText(String.valueOf(sdQuality.getValue()));

        lbPercent.setText(messageMapping.getString("EasyPhoto.lbPercent.text")); // NOI18N

        cbOutputNoAsk.setText(messageMapping.getString("EasyPhoto.lbOutputNoAsk.text")); // NOI18N

        lbOutputFolder.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbOutputFolder.setText(messageMapping.getString("EasyPhoto.lbOutputFolder.text")); // NOI18N

        javax.swing.GroupLayout panExportLayout = new javax.swing.GroupLayout(panExport);
        panExport.setLayout(panExportLayout);
        panExportLayout.setHorizontalGroup(
            panExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panExportLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbQuality, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(lbResize, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(lbOutputFolder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbOutputNoAsk)
                    .addComponent(cbOverride)
                    .addComponent(tfOutput, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sdSize, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addComponent(sdQuality, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfQuality, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tfSize, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbPercent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbPixel, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(291, 291, 291))
        );
        panExportLayout.setVerticalGroup(
            panExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panExportLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbQuality, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(sdQuality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPercent)
                    .addComponent(tfQuality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbResize, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(sdSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPixel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbOutputFolder))
                .addGap(8, 8, 8)
                .addComponent(cbOverride)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbOutputNoAsk)
                .addGap(44, 44, 44))
        );

        panOptions.addTab(messageMapping.getString("tab.export"), panExport); // NOI18N

        lbTheme.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTheme.setText(messageMapping.getString("label.theme")); // NOI18N

        lbOnTop.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbOnTop.setText(messageMapping.getString("label.ontop")); // NOI18N

        cbOnTop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOnTopActionPerformed(evt);
            }
        });

        cbTheme.setModel(new javax.swing.DefaultComboBoxModel(CollectionsUtil.getThemeList()));
        cbTheme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbThemeActionPerformed(evt);
            }
        });

        lbAutoOpen.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbAutoOpen.setText(messageMapping.getString("label.autoopen")); // NOI18N

        lbCheckUpdate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbCheckUpdate.setText(messageMapping.getString("label.checkupdate")); // NOI18N

        btCheckUpdate.setText(messageMapping.getString("button.update")); // NOI18N
        btCheckUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCheckUpdateActionPerformed(evt);
            }
        });

        lbLanguage.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbLanguage.setText(messageMapping.getString("EasyPhoto.lbLanguage.text")); // NOI18N
        lbLanguage.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        cbLanguage.setModel(new javax.swing.DefaultComboBoxModel(CollectionsUtil.getLangList()));
        cbLanguage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLanguageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panAppLayout = new javax.swing.GroupLayout(panApp);
        panApp.setLayout(panAppLayout);
        panAppLayout.setHorizontalGroup(
            panAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panAppLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTheme, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(lbAutoOpen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbOnTop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(lbLanguage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(lbCheckUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btCheckUpdate)
                    .addComponent(cbLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTheme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbAutoOpen)
                    .addComponent(cbOnTop))
                .addGap(500, 500, 500))
        );
        panAppLayout.setVerticalGroup(
            panAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panAppLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbOnTop, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbOnTop))
                .addGap(8, 8, 8)
                .addGroup(panAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbAutoOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbAutoOpen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTheme, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTheme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cbLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbCheckUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCheckUpdate))
                .addGap(241, 241, 241))
        );

        panOptions.addTab(messageMapping.getString("tab.app"), panApp); // NOI18N

        btUpdatePreview.setText(messageMapping.getString("button.preview")); // NOI18N
        btUpdatePreview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUpdatePreviewActionPerformed(evt);
            }
        });

        btConfirm.setText(messageMapping.getString("EasyPhoto.btConfirm.text")); // NOI18N
        btConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fmOptionsLayout = new javax.swing.GroupLayout(fmOptions.getContentPane());
        fmOptions.getContentPane().setLayout(fmOptionsLayout);
        fmOptionsLayout.setHorizontalGroup(
            fmOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fmOptionsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(fmOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panOptions, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fmOptionsLayout.createSequentialGroup()
                        .addComponent(btUpdatePreview)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btConfirm)))
                .addContainerGap())
        );
        fmOptionsLayout.setVerticalGroup(
            fmOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fmOptionsLayout.createSequentialGroup()
                .addComponent(panOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(fmOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btConfirm)
                    .addComponent(btUpdatePreview))
                .addContainerGap())
        );

        dgAbout.setTitle(messageMapping.getString("dialog.about.title")); // NOI18N
        dgAbout.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        dgAbout.setModal(true);
        dgAbout.setResizable(false);

        lbAboutLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAboutLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon/big_logo_128.png"))); // NOI18N
        lbAboutLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbAboutLogoMouseClicked(evt);
            }
        });

        epAboutInfo.setBackground(new java.awt.Color(212, 208, 200));
        epAboutInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        epAboutInfo.setContentType("text/html"); // NOI18N
        epAboutInfo.setEditable(false);
        epAboutInfo.setFont(new java.awt.Font("宋体", 1, 12));
        epAboutInfo.setText(messageMapping.getString("dialog.about.info")); // NOI18N
        epAboutInfo.setOpaque(false);
        epAboutInfo.addHyperlinkListener(new javax.swing.event.HyperlinkListener() {
            public void hyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {
                linkClickHandler(evt);
            }
        });

        javax.swing.GroupLayout panAboutLayout = new javax.swing.GroupLayout(panAbout);
        panAbout.setLayout(panAboutLayout);
        panAboutLayout.setHorizontalGroup(
            panAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panAboutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbAboutLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(epAboutInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        panAboutLayout.setVerticalGroup(
            panAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panAboutLayout.createSequentialGroup()
                .addGroup(panAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(epAboutInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                    .addComponent(lbAboutLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout dgAboutLayout = new javax.swing.GroupLayout(dgAbout.getContentPane());
        dgAbout.getContentPane().setLayout(dgAboutLayout);
        dgAboutLayout.setHorizontalGroup(
            dgAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panAbout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dgAboutLayout.setVerticalGroup(
            dgAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dgAboutLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panAbout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(messageMapping.getString("main.title")); // NOI18N
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("frame"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                appCloseHandler(evt);
            }
        });

        panMain.setBackground(new java.awt.Color(255, 255, 255));
        panMain.setVerifyInputWhenFocusTarget(false);

        lbPreviewSmall.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPreviewSmall.setText(messageMapping.getString("EasyPhoto.lbDndImage")); // NOI18N
        lbPreviewSmall.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        lbPreviewSmall.setDoubleBuffered(true);
        lbPreviewSmall.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbPreviewSmall.setTransferHandler(new DndTransferHandler(this));

        lbNext.setFont(new java.awt.Font("Wingdings 3", 0, 12));
        lbNext.setText(messageMapping.getString("EasyPhoto.lbNext.text")); // NOI18N
        lbNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbNextActionPerformed(evt);
            }
        });

        lbPrevious.setFont(new java.awt.Font("Wingdings 3", 0, 12));
        lbPrevious.setText(messageMapping.getString("EasyPhoto.lbPrevious.text")); // NOI18N
        lbPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbPreviousActionPerformed(evt);
            }
        });

        lbPreviewInfo.setFont(new java.awt.Font("宋体", 1, 12));
        lbPreviewInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPreviewInfo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panImageNavigateLayout = new javax.swing.GroupLayout(panImageNavigate);
        panImageNavigate.setLayout(panImageNavigateLayout);
        panImageNavigateLayout.setHorizontalGroup(
            panImageNavigateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panImageNavigateLayout.createSequentialGroup()
                .addComponent(lbPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbPreviewInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lbNext, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panImageNavigateLayout.setVerticalGroup(
            panImageNavigateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panImageNavigateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lbPreviewInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lbNext)
                .addComponent(lbPrevious))
        );

        btOptions.setText(messageMapping.getString("EasyPhoto.btOptions.text")); // NOI18N
        btOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOptionsActionPerformed(evt);
            }
        });

        btAbout.setText(messageMapping.getString("EasyPhoto.btAbout.text")); // NOI18N
        btAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAboutActionPerformed(evt);
            }
        });

        btCancle.setText(messageMapping.getString("button.cancle")); // NOI18N
        btCancle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCancle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancleActionPerformed(evt);
            }
        });

        btExport.setText(messageMapping.getString("EasyPhoto.btExport.text")); // NOI18N
        btExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExportActionPerformed(evt);
            }
        });

        btPreviewBig.setText(messageMapping.getString("EasyPhoto.btPreviewBig.text")); // NOI18N
        btPreviewBig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPreviewBigActionPerformed(evt);
            }
        });

        cbApplyExif.setText(messageMapping.getString("EasyPhoto.cbApplyExif.text")); // NOI18N
        cbApplyExif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbApplyExifActionPerformed(evt);
            }
        });

        cbApplyRound.setText(messageMapping.getString("EasyPhoto.cbApplyRound.text")); // NOI18N
        cbApplyRound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbApplyRoundActionPerformed(evt);
            }
        });

        cbApplyTextWm.setText(messageMapping.getString("EasyPhoto.cbApplyTextWm.text")); // NOI18N
        cbApplyTextWm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbApplyTextWmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panControlLayout = new javax.swing.GroupLayout(panControl);
        panControl.setLayout(panControlLayout);
        panControlLayout.setHorizontalGroup(
            panControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panControlLayout.createSequentialGroup()
                .addGroup(panControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panControlLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(btPreviewBig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(btExport, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(btCancle, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(btOptions, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(btAbout, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panControlLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(cbApplyTextWm, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(cbApplyRound, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(cbApplyExif, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panControlLayout.setVerticalGroup(
            panControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panControlLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(cbApplyExif)
                .addGap(6, 6, 6)
                .addComponent(cbApplyRound)
                .addGap(6, 6, 6)
                .addComponent(cbApplyTextWm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btPreviewBig)
                .addGap(10, 10, 10)
                .addComponent(btExport)
                .addGap(9, 9, 9)
                .addComponent(btCancle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btOptions)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btAbout)
                .addContainerGap(182, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panMainLayout = new javax.swing.GroupLayout(panMain);
        panMain.setLayout(panMainLayout);
        panMainLayout.setHorizontalGroup(
            panMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panControl, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panImageNavigate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbPreviewSmall, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE))
                .addContainerGap())
        );
        panMainLayout.setVerticalGroup(
            panMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panControl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panMainLayout.createSequentialGroup()
                        .addComponent(lbPreviewSmall, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panImageNavigate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void lbAboutLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAboutLogoMouseClicked
        String[] imgs = {"big_logo_128.png","h.png","d.jpg"};
        ImageIcon img = null;
        if(evt.getButton()== evt.BUTTON3 && evt.getClickCount() == 2){
            img = new ImageIcon(EasyPhoto.class.getResource("/resources/icon/" + imgs[eggIndex]));
            lbAboutLogo.setIcon(img);
            eggIndex++;
            if(eggIndex > imgs.length-1){
                eggIndex = 0;
            }
        }
}//GEN-LAST:event_lbAboutLogoMouseClicked

    private void sdSizeHandler(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sdSizeHandler
        javax.swing.JSlider sd = (javax.swing.JSlider)evt.getSource();
        javax.swing.JLabel lb = (javax.swing.JLabel)sd.getLabelTable().get(sd.getValue());
        tfSize.setText(lb.getText());
}//GEN-LAST:event_sdSizeHandler

    private void linkClickHandler(javax.swing.event.HyperlinkEvent evt) {//GEN-FIRST:event_linkClickHandler
        if (evt.getEventType() == javax.swing.event.HyperlinkEvent.EventType.ACTIVATED) {
            try {
                Desktop.getDesktop().browse(evt.getURL().toURI());
            } catch (Exception ex) {
                Logger.getLogger(EasyPhoto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_linkClickHandler

    private void sdQualityHandler(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sdQualityHandler
        javax.swing.JSlider sd = (javax.swing.JSlider)evt.getSource();
        tfQuality.setText(String.valueOf(sd.getValue()));
    }//GEN-LAST:event_sdQualityHandler

    private void btExifFontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExifFontActionPerformed
       if(fontChooser == null){
           fontChooser = new JFontChooser();
       }
       fontChooser.setSelectedFont(exifFont);
       int result = fontChooser.showDialog(this);
       if (result == JFontChooser.OK_OPTION)
       {
           exifFont = fontChooser.getSelectedFont();
       }
    }//GEN-LAST:event_btExifFontActionPerformed

    private void previewDragHandler(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previewDragHandler
        java.awt.Point ptMouseNew = evt.getPoint();
        java.awt.Point ptPreNow = fmPreview.getLocation();
        fmPreview.setLocation(ptPreNow.x+ptMouseNew.x-ptMouseOrigin.x,
                ptPreNow.y+ptMouseNew.y-ptMouseOrigin.y);
    }//GEN-LAST:event_previewDragHandler

    private void previewMoursePressHandler(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previewMoursePressHandler
    	ptMouseOrigin = evt.getPoint();
    }//GEN-LAST:event_previewMoursePressHandler

    private void previewHandler(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previewHandler
        if(evt.getButton() == evt.BUTTON1){
            //left click to get pre image
            previewIndex--;
            if(previewIndex < 0){
               previewIndex =  previewImageList.size()-1;
            }
        } else if(evt.getButton() == evt.BUTTON3){
            //right click to get next image           
            previewIndex++;
        }  
        showBigPreview();        
        showSmallPreview();
    }//GEN-LAST:event_previewHandler

    private void cbThemeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbThemeActionPerformed
        //avoid action trigger if just for initial but user manual operation
        if(isInitialing){
            return;
        }
        try {
            //get look and feel class name
            String laf = ((OptionBean)cbTheme.getSelectedItem()).getValue();
            Config.getInstance().setValue("theme", laf);
            Config.getInstance().storeConf();         
            UIManager.setLookAndFeel(laf);
            //refresh UI components
            SwingUtilities.updateComponentTreeUI(this);
            SwingUtilities.updateComponentTreeUI(fmOptions);
            SwingUtilities.updateComponentTreeUI(fmPreview);
            SwingUtilities.updateComponentTreeUI(fcOutput);
            this.pack();
        } catch (Exception ex) {
            Logger.getLogger(EasyPhoto.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }//GEN-LAST:event_cbThemeActionPerformed

    private void btCheckUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCheckUpdateActionPerformed
    	doCheckUpdateAction(false);
    }//GEN-LAST:event_btCheckUpdateActionPerformed

    /**
     * do concrete check update action
     * @param isCycleCheck app setting cycle check flag
     */
    public void doCheckUpdateAction(boolean isCycleCheck){
    	workerUpdate = new UpdateSwingWorker(this);
    	workerUpdate.setCycleCheck(isCycleCheck);
    	workerAutoUpdate = new UpdateSwingWorker(this);
    	workerUpdate.addPropertyChangeListener(new PropertyChangeListener(){
            public  void propertyChange(PropertyChangeEvent evt) {
                if ("actionType".equals(evt.getPropertyName())) {
                	workerAutoUpdate.setActionType((String)evt.getNewValue());
                	workerAutoUpdate.execute();
                }
            }
    	});
    	workerUpdate.execute();    	
    }
    
    private void cbOnTopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOnTopActionPerformed
        setAlwaysOnTop(cbOnTop.isSelected());
        fmOptions.setAlwaysOnTop(cbOnTop.isSelected());
    }//GEN-LAST:event_cbOnTopActionPerformed

    private void btCancleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancleActionPerformed
        // stop all running thread,then
        worker.stop();
        btCancle.setVisible(false);
        btExport.setVisible(true);
    }//GEN-LAST:event_btCancleActionPerformed

    private void lbPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbPreviousActionPerformed
        //left click to get pre image
        previewIndex--;
        if(previewIndex < 0){
           previewIndex =  previewImageList.size()-1;
        }
        showSmallPreview();
    }//GEN-LAST:event_lbPreviousActionPerformed

    private void lbNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbNextActionPerformed
        previewIndex++;
        showSmallPreview();        
    }//GEN-LAST:event_lbNextActionPerformed

    private void appCloseHandler(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_appCloseHandler
        //remember user operation params
        AppUtil.rememberOperParams(this.getImageActionParamsVO());
        //remember app conf
        AppUtil.rememberAppConf(this.getAppOptionsVO());
        //release all binding resources
        this.dispose();
        //exit
        System.exit(1);
    }//GEN-LAST:event_appCloseHandler

    private void btUpdatePreviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUpdatePreviewActionPerformed
        showSmallPreview(); 
}//GEN-LAST:event_btUpdatePreviewActionPerformed

    private void btWmTextColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btWmTextColorActionPerformed
        Color newColor = javax.swing.JColorChooser.showDialog(this, "", Color.WHITE);
        if(newColor != null){
            wmTextColor = newColor;
        }
    }//GEN-LAST:event_btWmTextColorActionPerformed

    private void cbLanguageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLanguageActionPerformed
        //avoid action trigger if just for initial but user manual operation
        if(isInitialing){
            return;
        }
        //remember app conf
        AppUtil.rememberAppConf(this.getAppOptionsVO());
        int n = JOptionPane.showConfirmDialog(this, messageMapping
                .getString("dialog.language.restart"), messageMapping
                .getString("dialog.update.title"),
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (n == JOptionPane.OK_OPTION) {
            AppUtil.restartApp();
        }
    }//GEN-LAST:event_cbLanguageActionPerformed

    private void btWmTextFontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btWmTextFontActionPerformed
       if(fontChooser == null){
           fontChooser = new JFontChooser();
       }
       fontChooser.setSelectedFont(wmTextFont);
        int result = fontChooser.showDialog(this);
       if (result == JFontChooser.OK_OPTION)
       {
           wmTextFont = fontChooser.getSelectedFont();
       }
    }//GEN-LAST:event_btWmTextFontActionPerformed

    private void btAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAboutActionPerformed
        dgAbout.pack();
        dgAbout.setLocationRelativeTo(null);
        dgAbout.setVisible(true);
    }//GEN-LAST:event_btAboutActionPerformed

    private void btOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOptionsActionPerformed
        fmOptions.pack();
        fmOptions.setLocationRelativeTo(null);
        fmOptions.setVisible(true);
    }//GEN-LAST:event_btOptionsActionPerformed

    private void btExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExportActionPerformed
        try{
            boolean isReady = checkIfReadyToDo();
            if(isReady){
                worker = new ImageSwingWorker(this);
                worker.addPropertyChangeListener(
                        new PropertyChangeListener() {
                    public  void propertyChange(PropertyChangeEvent evt) {
                        if ("progress".equals(evt.getPropertyName())) {
                            lbPreviewSmall.setText((Integer)evt.getNewValue()+"%");
                            //pbProgress.setValue((Integer)evt.getNewValue());
                        }
                    }
                });
                worker.execute();
                //disable action button and enable cancel button in processing
                lbPreviewSmall.setIcon(new ImageIcon(getClass().getResource(
					"/resources/icon/loader.gif")));
                lbPreviewSmall.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                btExport.setVisible(false);
                btCancle.setVisible(true);
            }
        }catch(Exception e){
            e.printStackTrace();
            resetAfterExport();
//            btCancle.setVisible(false);
//            btExport.setVisible(true);
        }
    }//GEN-LAST:event_btExportActionPerformed

    private void btPreviewBigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPreviewBigActionPerformed
        showBigPreview();
    }//GEN-LAST:event_btPreviewBigActionPerformed

    private void cbApplyExifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbApplyExifActionPerformed
        showSmallPreview();
    }//GEN-LAST:event_cbApplyExifActionPerformed

    private void cbApplyRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbApplyRoundActionPerformed
        showSmallPreview();
    }//GEN-LAST:event_cbApplyRoundActionPerformed

    private void cbApplyTextWmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbApplyTextWmActionPerformed
        showSmallPreview();
    }//GEN-LAST:event_cbApplyTextWmActionPerformed

    private void btConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConfirmActionPerformed
        showSmallPreview();
        fmOptions.setVisible(false);
    }//GEN-LAST:event_btConfirmActionPerformed

    /**
     * check if ready to proceed images
     */
    public boolean checkIfReadyToDo() {
        if(inputFiles == null || inputFiles.length < 1){
            JOptionPane.showMessageDialog(this, messageMapping.getString("error.no.input"), "Message",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if ((!tfOutput.getText().isEmpty() && cbOutputNoAsk.isSelected())
                || cbOverride.isSelected()) {
            return true;
        }
         //reset filechooser selected file
        if(outputFolder != null){
            fcOutput.setSelectedFile(outputFolder);
        }
        //show dialog
        int rtValue = fcOutput.showOpenDialog(this);
        if (rtValue == javax.swing.JFileChooser.APPROVE_OPTION) {
            setOutputFolder(fcOutput.getSelectedFile());
            tfOutput.setText(outputFolder.getAbsolutePath());
            return true;
        }
        return false;
    }
    /**
     * reset main frame, make it ready for next task
     */
    public void resetAfterExport(){
        //tfInput.setText("");
        btExport.setVisible(true);
        btCancle.setVisible(false);
        lbPreviewSmall.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        lbPreviewSmall.setText("");
        showSmallPreview();
    }
    
    /**
     * show small preview
     */
    public void showSmallPreview(){
        if(previewImageList.size() < 1){
            return;
        }
        int index = previewIndex%previewImageList.size();   
        lbPreviewSmall.setText("");
        lbPreviewSmall.setIcon(new ImageIcon(getPreviewImage(previewImageList.get(index),true)));
        String previewInfo = messageMapping.getString("label.previewinfo");
        previewInfo = MessageFormat.format(previewInfo,index+1,previewImageList.size());
        lbPreviewInfo.setText(previewInfo);
        //modify window size to fit preview image size
        this.pack();
    }
    
    /**
     * show big preview
     */
    public void showBigPreview(){
        if(previewImageList.size() < 1){
            return;
        }        
        int index = previewIndex%previewImageList.size();   
        lbPreviewBig.setIcon(new ImageIcon(getPreviewImage(previewImageList.get(index),false)));
        fmPreview.setTitle("~"+FileUtil.getFileSizeDescription(previewImageSize)+"~");
        fmPreview.pack();
        if(!fmPreview.isVisible()){
            fmPreview.setLocationRelativeTo(null);
            fmPreview.setVisible(true);        
        }        
    }
    
    /**
     * get preview image
     * @param direction -1:previous image,1:next image
     * @return image after decorative
     */
    public Image getPreviewImage(File imageFile,boolean isSmall){
        //create ImageActionVO
        ImageActionVO vo = new ImageActionVO();
        vo.setParams(this.getImageActionParamsVO());
        vo.setInputImageFile(imageFile);
        if(isSmall){
            vo.getParams().setApplyResize(true);
            vo.getParams().setResize(AppConstants.PREVIEW_SIZE_SMALL);
        }
        //decorate image
        ImageAction.decorateImage(vo);
        //get swap image file
        File outputFile = new File(AppConstants.SWAP_IMAGE_BASENAME+FileUtil.getExtName(imageFile));
        outputFile.delete();
        BufferedImage previewImage = ImageUtil.storeAndRetrieveImage(vo.getBufferedImage(),outputFile,
                sdQuality.getValue()/100.0f);
        if(!isSmall){
            previewImageSize = outputFile.length();
        }
        return previewImage;
        //return vo.getBufferedImage();
    }
    
    /*************get and set methods for process conditions->***********/ 
    /**
     * set inputFiles
     */
    public void setInputFiles(File[] inputFiles){
        this.inputFiles = inputFiles;
        //update preview image list content when input file or folder changed
        previewImageList.clear();
        ImageUtil.findImagesRecusive(previewImageList, inputFiles);
        showSmallPreview();
    }

    /**
     * set output folder
     */
    public void setOutputFolder(File outputFolder){
    	this.outputFolder = outputFolder;
    }
    
    /**
     * get image decorative params
     * @return ImageActionParamsVO istance
     */
    public ImageActionParamsVO getImageActionParamsVO(){
        ImageActionParamsVO vo = new ImageActionParamsVO();
        //image 
        vo.setInputFiles(inputFiles);
        vo.setOutputFolder(outputFolder);
        vo.setOverride(cbOverride.isSelected());
        //export options
        vo.setQuality(sdQuality.getValue());
        vo.setResizeIndex(sdSize.getValue());
        if(sdSize.getValue() == 1){
            vo.setApplyResize(false);
            vo.setResize(0);
        } else {
            vo.setApplyResize(true);
            try{
                vo.setResize(Integer.parseInt(tfSize.getText()));  
            }catch(Exception e){
                vo.setResize(AppConstants.DEFAULT_RESIZE); 
            }           
        }
        //corner round
        vo.setApplyRound(cbApplyRound.isSelected());
        vo.setCornerSize(sdCornerSize.getValue());
        //exif
        vo.setApplyExif(cbApplyExif.isSelected());
        vo.setExifStyle(UIUtil.getSelectedValue(btgStyle));
        vo.setAppendDate(cbExifDate.isSelected());
        vo.setAppendCamera(cbExifCamera.isSelected());
        vo.setAppendApt(cbExifApt.isSelected());
        vo.setAppendExp(cbExifExp.isSelected());
        vo.setAppendFocal(cbExifFocal.isSelected());
        vo.setAppendIso(cbExifIso.isSelected());
        vo.setSign(tfExifSign.getText());
        vo.setExifFont(exifFont);
        //text watermark
        vo.setApplyWatermarkText(cbApplyTextWm.isSelected());
        vo.setWatermarkTextFont(wmTextFont);
        vo.setWatermarkColor(wmTextColor);
        vo.setWatermarkTextStyle(UIUtil.getSelectedValue(btgWmStyle));
        vo.setWatermarkText(tfWmText.getText());
        vo.setWatermarkTextAlpha(sdWmTextOpaque.getValue());
        return vo;
    }
    
    /**
     * get app conf options
     * @return
     */
    public AppOptionsVO getAppOptionsVO(){
        AppOptionsVO vo = new AppOptionsVO();
        vo.setTheme(UIUtil.getSelectedValue(cbTheme));
        vo.setOnTop(cbOnTop.isSelected());
        vo.setAutoOpenOutput(cbAutoOpen.isSelected());
        vo.setLanguage(UIUtil.getSelectedValue(cbLanguage));
        vo.setOutputNoAsk(cbOutputNoAsk.isSelected());
        return vo;
    }
    
    /*************<-get and set methods for process conditions***********/ 
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAbout;
    private javax.swing.JButton btCancle;
    private javax.swing.JButton btCheckUpdate;
    private javax.swing.JButton btConfirm;
    private javax.swing.JButton btExifFont;
    private javax.swing.JButton btExport;
    private javax.swing.JButton btOptions;
    private javax.swing.JButton btPreviewBig;
    private javax.swing.JButton btUpdatePreview;
    private javax.swing.JButton btWmTextColor;
    private javax.swing.JButton btWmTextFont;
    private javax.swing.ButtonGroup btgStyle;
    private javax.swing.ButtonGroup btgWmStyle;
    private javax.swing.JCheckBox cbApplyExif;
    private javax.swing.JCheckBox cbApplyRound;
    private javax.swing.JCheckBox cbApplyTextWm;
    private javax.swing.JCheckBox cbAutoOpen;
    private javax.swing.JCheckBox cbExifApt;
    private javax.swing.JCheckBox cbExifCamera;
    private javax.swing.JCheckBox cbExifDate;
    private javax.swing.JCheckBox cbExifExp;
    private javax.swing.JCheckBox cbExifFocal;
    private javax.swing.JCheckBox cbExifIso;
    private javax.swing.JComboBox cbLanguage;
    private javax.swing.JCheckBox cbOnTop;
    private javax.swing.JCheckBox cbOutputNoAsk;
    private javax.swing.JCheckBox cbOverride;
    private javax.swing.JComboBox cbTheme;
    private javax.swing.JDialog dgAbout;
    private javax.swing.JEditorPane epAboutInfo;
    private javax.swing.JFileChooser fcOutput;
    private javax.swing.JFrame fmOptions;
    private javax.swing.JFrame fmPreview;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbAboutLogo;
    private javax.swing.JLabel lbAutoOpen;
    private javax.swing.JLabel lbCheckUpdate;
    private javax.swing.JLabel lbCornerSize;
    private javax.swing.JLabel lbExifContent;
    private javax.swing.JLabel lbExifFont;
    private javax.swing.JLabel lbExifStyle;
    private javax.swing.JLabel lbExifStyleDouble;
    private javax.swing.JLabel lbExifStyleRound;
    private javax.swing.JLabel lbExifStyleSingle;
    private javax.swing.JLabel lbLanguage;
    private javax.swing.JButton lbNext;
    private javax.swing.JLabel lbOnTop;
    private javax.swing.JLabel lbOutputFolder;
    private javax.swing.JLabel lbPercent;
    private javax.swing.JLabel lbPixel;
    private javax.swing.JLabel lbPreviewBig;
    private javax.swing.JLabel lbPreviewInfo;
    private javax.swing.JLabel lbPreviewSmall;
    private javax.swing.JButton lbPrevious;
    private javax.swing.JLabel lbQuality;
    private javax.swing.JLabel lbResize;
    private javax.swing.JLabel lbSign;
    private javax.swing.JLabel lbTheme;
    private javax.swing.JLabel lbWmText;
    private javax.swing.JLabel lbWmTextFont;
    private javax.swing.JLabel lbWmTextOpaque;
    private javax.swing.JLabel lbWmTextStyle;
    private javax.swing.JLabel lbWmTextStyleCross;
    private javax.swing.JLabel lbWmTextStyleTiny;
    private javax.swing.JPanel panAbout;
    private javax.swing.JPanel panApp;
    private javax.swing.JPanel panControl;
    private javax.swing.JPanel panExif;
    private javax.swing.JPanel panExport;
    private javax.swing.JPanel panImageNavigate;
    private javax.swing.JPanel panMain;
    private javax.swing.JTabbedPane panOptions;
    private javax.swing.JPanel panRound;
    private javax.swing.JPanel panTextWm;
    private javax.swing.JRadioButton rbExifStyleDouble;
    private javax.swing.JRadioButton rbExifStyleRound;
    private javax.swing.JRadioButton rbExifStyleSingle;
    private javax.swing.JRadioButton rbWmTextStyleCross;
    private javax.swing.JRadioButton rbWmTextStyleTiny;
    private javax.swing.JSlider sdCornerSize;
    private javax.swing.JSlider sdQuality;
    private javax.swing.JSlider sdSize;
    private javax.swing.JSlider sdWmTextOpaque;
    private javax.swing.JTextField tfExifSign;
    private javax.swing.JTextField tfOutput;
    private javax.swing.JTextField tfQuality;
    private javax.swing.JTextField tfSize;
    private javax.swing.JTextField tfWmText;
    // End of variables declaration//GEN-END:variables
    /**
     * SwingWorker for image proceed
     */
    private ImageSwingWorker worker;
    /**
     * SwingWorker for update check
     */
    private UpdateSwingWorker workerUpdate;
    /**
     * SwingWorker for auto update proceed
     */
    private UpdateSwingWorker workerAutoUpdate;
    /**
     * MessageMapping for i18n
     */
    public static ResourceBundle messageMapping = ResourceBundle.getBundle("resources/i18n/MessageMapping");
    /**
     * image list for preview conventions
     */
    private List<File> previewImageList = new ArrayList();
    /**
     * image index in preview image list;
     */
    private int previewIndex;
    /**
     * preview image size
     */
    private long previewImageSize;
    /**
     * preview window location
     */
    private java.awt.Point ptMouseOrigin;
    /**
     * font chooser
     */
    private JFontChooser fontChooser;
    /**
     * Font for exif info
     */
    private Font exifFont = Font.decode(AppConstants.DEFAULT_EXIF_FONT);
    /**
     * Font for text watermark
     */
    private Font wmTextFont = Font.decode(AppConstants.DEFAULT_WATERMARK_FONT);
    /**
     * text watermark color
     */
    private Color wmTextColor = Color.WHITE;
    /**
     * indicated if app is initialing
     */
    private boolean isInitialing = false;
    /**
     * input images
     */
    private File[] inputFiles;
    /**
     * output folder
     */
    private File outputFolder;
    /**
     * index for color egg
     */
    private int eggIndex;
    /**
     * icons for frames
     */
    private static List<Image> icons = new ArrayList<Image>();
}
