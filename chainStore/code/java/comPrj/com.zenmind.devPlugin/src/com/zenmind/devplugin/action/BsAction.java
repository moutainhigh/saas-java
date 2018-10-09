package com.zenmind.devplugin.action;

import java.io.File;
import java.io.IOException;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.zenmind.base.FileUtils;
import com.zenmind.codeGenHelper.CodeGenHelper;
import com.zenmind.devplugin.PluginHelper;

public class BsAction implements IObjectActionDelegate {

	private Shell shell;

	/**
	 * Constructor for Action1.
	 */
	public BsAction() {
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
			
			String tempPojoClassName = FileUtils.upFirstChar(className);
			String tempPojoName = FileUtils.lowFirstChar(className);
			String initValue = tempPojoClassName+" - "+tempPojoName+" -> targetPojoClass - targetPojo";
			InputDialog inputDialog = new InputDialog(this.shell, "代码生成", "把TargetClass换成需要替换成的类名", initValue, null);
			if (Window.OK == inputDialog.open()) {
				String inputValue = inputDialog.getValue();
				
				String[] split = inputValue.split("->");
				String[] tempClass = split[0].split("-");
				String[] bsClass = split[1].split("-");
				File inputDir = pluginHelper.getCodeGenInputDir();
				File tempDir = pluginHelper.getCodeGenTempDir();
				File outputDir = pluginHelper.getCodeGenOutputDir();
				
				CodeGenHelper.newInstance(inputDir , tempDir, outputDir)
				.withTempClass(tempClass[0].trim(),tempClass[1].trim())
				.withBsClass(bsClass[0].trim(),bsClass[1].trim()).doGenBS();
			}
		} catch (IOException e) {
			e.printStackTrace();
			MessageDialog.openInformation(shell, "Error", "出错." + e.getMessage());
		}

	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

}
