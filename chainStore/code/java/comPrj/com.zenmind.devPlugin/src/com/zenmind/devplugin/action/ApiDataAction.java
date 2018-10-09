package com.zenmind.devplugin.action;

import java.io.File;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.zenmind.base.PathHelper;
import com.zenmind.codeGenHelper.J2TSGenHelper;
import com.zenmind.devplugin.PluginHelper;


public class ApiDataAction implements IObjectActionDelegate {

	private Shell shell;
	
	/**
	 * Constructor for Action1.
	 */
	public ApiDataAction() {
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
			String module = PathHelper.getFileName(targetDir.getAbsolutePath());
			
			File inputDir = pluginHelper.getCodeGenInputDir();
			File outputDir = pluginHelper.getCodeGenOutputDir();
			
			J2TSGenHelper.newGenHelper(inputDir, outputDir, module).doGen();
//			H5ApiDataGenHelper.newGenHelper(inputDir, outputDir).doGen();
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
