package java2typescript;

import java.io.File;

import com.zenmind.base.FileUtils;
import com.zenmind.base.PathHelper;
import com.zenmind.codeGenHelper.J2TSGenHelper;

public class _Main {
	public static void main(String[] args) throws Exception {
		String sourcePath = "F:\\honkon\\zhimeitimes\\saas-java\\chainStore\\code\\java\\storePrj\\chainRestClient\\src\\main\\java\\com\\hq\\chainClient\\service\\areaCode";
		String module = PathHelper.getFileName(sourcePath);

		String inputPath = "F:\\codeGenTmpDir\\input";
		String outputPath = "F:\\codeGenTmpDir\\output";

		FileUtils.deleteAll(new File(inputPath));
		FileUtils.copyDir(sourcePath, PathHelper.joinPaths(inputPath, module));
		
		J2TSGenHelper.newGenHelper(new File(inputPath), new File(outputPath), module).doGen();
	}
}
