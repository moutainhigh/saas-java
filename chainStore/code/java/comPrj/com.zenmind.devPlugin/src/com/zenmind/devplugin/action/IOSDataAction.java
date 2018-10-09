package com.zenmind.devplugin.action;

import java.io.File;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.zenmind.codeGenHelper.IOSCodeGenHelper;
import com.zenmind.devplugin.PluginHelper;

public class IOSDataAction implements IObjectActionDelegate {

	private Shell shell;
	
	/**
	 * Constructor for Action1.
	 */
	public IOSDataAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		try {
			PluginHelper pluginHelper = PluginHelper.newInstance();
			File targetDir = pluginHelper.prepareCodeGen();
			String className = pluginHelper.getClassName(targetDir);
			
			File inputDir = pluginHelper.getCodeGenInputDir();
			File tempDir = pluginHelper.getCodeGenTempDir();
			File outputDir = pluginHelper.getCodeGenOutputDir();
			
			IOSCodeGenHelper.newInstance(inputDir, tempDir, outputDir, className).doGen();
		} catch (Exception e) {
			MessageDialog.openInformation(shell, "Error", "出错." + e.getMessage());
		}
	}
	
	
	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

}
