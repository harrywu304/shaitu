/**
 * COPYRIGHT. Harry Wu 2010. ALL RIGHTS RESERVED.
 * Project: ThirdEye
 * Author: Harry Wu <harrywu304@gmail.com>
 * Created On: Oct 13, 2008 4:57:38 PM
 *
*/
package org.shaitu.easyphoto.ui;

import java.awt.Desktop;
import java.net.URI;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import org.shaitu.easyphoto.action.UpdateAction;
import org.shaitu.easyphoto.util.AppUtil;


/**
 * SwingWorker used to connect EDT and work threads
 * 
 * @author whx
 */
public class UpdateSwingWorker extends SwingWorker {
	/**
	 * log instance
	 */
	private static final Logger logger = Logger
			.getLogger(ImageSwingWorker.class.getName());
	/**
	 * refer to EasyPhoto instance
	 */
	private EasyPhoto main;
	/**
	 * update action instance
	 */
	private UpdateAction action;
	/**
	 * action type in background
	 */
	private String actionType = "checkUpdate";
	/**
	 * cycle check setting by app, now it is dayly
	 */
	private boolean isCycleCheck;

	public UpdateSwingWorker(EasyPhoto main) {
		this.main = main;
		this.action = UpdateAction.getInstance();
	}

	@Override
	protected Object doInBackground() throws Exception {
		try {
			if ("checkUpdate".equals(actionType)) {
				// check update from server
				action.checkUpdate();
			} else if ("autoUpdate".equals(actionType)) {
				// auto update
				action.autoUpdate();
				while (!action.isUpdateDone()) {
					Thread.sleep(5000);
				}
				actionType = "restartNotify";
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	protected void done() {
		// declaration update dialog
		String dialogInfo = "";
		if ("checkUpdate".equals(actionType)) {
			// check if update check done
			if (!action.isCheckDone()) {
				if(!isCycleCheck){
					dialogInfo = AppUtil.getResourceString("dialog.update.checkfail");
					JOptionPane.showMessageDialog(main, dialogInfo);
				}
				return;
			}

			// show tips no update available and return
			if (!action.isUpdateValid()) {
				if(!isCycleCheck){
					dialogInfo = AppUtil.getResourceString("dialog.update.noupdate");
					JOptionPane.showMessageDialog(main, dialogInfo);
				}
				return;
			}

			// auto update invalid
			if (action.isAutoUpdateValid()) {
				dialogInfo = MessageFormat.format(AppUtil.getResourceString("dialog.update.autoupdate"), action
						.getLatestVersion());
				int n = JOptionPane.showConfirmDialog(main, dialogInfo,
						AppUtil.getResourceString("dialog.update.title"),
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
				if (n == JOptionPane.OK_OPTION) {
					actionType = "autoUpdate";
					firePropertyChange("actionType", "checkUpdate",
							"autoUpdate");
				}
				return;
			}
			// show tips re-install needed
			dialogInfo = MessageFormat.format(AppUtil.getResourceString("dialog.update.needreinstall"), action
					.getLatestVersion());
			int n = JOptionPane.showConfirmDialog(main, dialogInfo,
					AppUtil.getResourceString("dialog.update.title"),
					JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
			if (n == JOptionPane.OK_OPTION) {
				// download bin install file
				try {
					Desktop.getDesktop().browse(
							new URI(action.getPublishURL()));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			return;
		} else if ("restartNotify".equals(actionType)) {
			int n = JOptionPane.showConfirmDialog(main, AppUtil.getResourceString("dialog.update.restart"), AppUtil.getResourceString("dialog.update.title"),
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (n == JOptionPane.OK_OPTION) {
				// remember user operation params
				AppUtil.rememberOperParams(main.getImageActionParamsVO());
				// remember app conf
				AppUtil.rememberAppConf(main.getAppOptionsVO());
				// release resources
				main.dispose();
				// restart app
				AppUtil.restartApp();
			}
		}
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public boolean isCycleCheck() {
		return isCycleCheck;
	}

	public void setCycleCheck(boolean isCycleCheck) {
		this.isCycleCheck = isCycleCheck;
	}

}
