package com.zenmind.devplugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;

import com.zenmind.base.FileUtils;

public class PluginHelper {

	private File codeGenInputDir;
	private File codeGenTempDir;
	private File codeGenOutputDir;
	
	public static PluginHelper newInstance(){
		return new PluginHelper();
	}
	
	// 返回第一个
	public File prepareCodeGen() throws IOException {
		ISelectionService service = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();

		ISelection selection = service.getSelection();

		if (selection instanceof IStructuredSelection) {
			Object selected = ((IStructuredSelection) selection).getFirstElement();
			IResource selectedFolder = (IResource) Platform.getAdapterManager().getAdapter(selected, IResource.class);

			File workspaceFolder = selectedFolder.getWorkspace().getRoot().getLocation().toFile().getParentFile();
			String prjAbsPath = workspaceFolder.getAbsolutePath();
			File codeGenDir = new File(prjAbsPath + "\\codeGenTmpDir");
			FileUtils.deleteAll(codeGenDir);

			codeGenInputDir = new File(prjAbsPath + "\\codeGenTmpDir\\input");
			codeGenTempDir = new File(prjAbsPath + "\\codeGenTmpDir\\temp");
			codeGenOutputDir = new File(prjAbsPath + "\\codeGenTmpDir\\output");
			
			if (!codeGenDir.exists()) {
				codeGenDir.mkdir();
			}
			if (!codeGenInputDir.exists()) {
				codeGenInputDir.mkdir();
			}
			if (!codeGenTempDir.exists()) {
				codeGenTempDir.mkdir();
			}
			if (!codeGenOutputDir.exists()) {
				codeGenOutputDir.mkdir();
			}

			List<File> selectedFileList = getSelectedFileList();
			copy2CodeGenInputDir(selectedFileList, codeGenInputDir);
			return selectedFileList.get(0);
		}
		return null;
	}

	private void copy2CodeGenInputDir(List<File> selectedFileList, File codeGenInputDir) throws IOException {

		for (File fileTmp : selectedFileList) {
			String fileName = fileTmp.getName();
			String targetFilePath = codeGenInputDir.getAbsolutePath()+"\\"+fileName;
			if(fileTmp.isDirectory()){
				FileUtils.copyDir(fileTmp.getAbsolutePath(), targetFilePath);
			}else{
				FileUtils.copyFile(fileTmp, new File(targetFilePath));
			}
		}

	}

	public List<File> getSelectedFileList() {

		ISelectionService service = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();

		ISelection selection = service.getSelection();
		List<File> targetList = new ArrayList<File>();
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection iStructuredSelection = (IStructuredSelection) selection;

			iStructuredSelection.size();
			@SuppressWarnings("unchecked")
			Iterator<Object> iterator = iStructuredSelection.iterator();
			while (iterator.hasNext()) {
				Object selectedTmp = iterator.next();
				IResource selectedFolder = (IResource) Platform.getAdapterManager().getAdapter(selectedTmp,
						IResource.class);
				File tmpFolder = selectedFolder.getLocation().toFile();
				targetList.add(tmpFolder);
			}

		}
		return targetList;
	}

	public String getClassName(File dir) {
		String absolutePath = dir.getAbsolutePath();
		String className = StringUtils.substringAfterLast(absolutePath, "\\");
		return FileUtils.upFirstChar(className);
	}

	

	public File getCodeGenInputDir() {
		return codeGenInputDir;
	}

	public File getCodeGenTempDir() {
		return codeGenTempDir;
	}

	public File getCodeGenOutputDir() {
		return codeGenOutputDir;
	}

	
	
}
