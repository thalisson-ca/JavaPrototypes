package zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Class to help with zip files
 * @author Thalisson Christano de Almeida
 *
 */
public class Zip {

	/**
	 * Unzip a zip file to a directory and returns a list of files that was unzipped
	 * @author Thalisson Christano de Almeida
	 * @param pathZip Path to zip file
	 * @param destPath Path to destination directory
	 * @return a list of the unzipped files
	 * @throws FileNotFoundException if pathZip is not found
	 * @throws IOException if others IO problems happens
	 */
	public static List<File> unzipFile(String pathZip, String destPath) throws FileNotFoundException,IOException{
		byte[] buffer = new byte[2048];
		File destDir = new File(destPath);
		ArrayList<File> files = new ArrayList<File>();

		try(ZipInputStream zis = new ZipInputStream(new FileInputStream(pathZip))){
			ZipEntry zipEntry = zis.getNextEntry();
			while (zipEntry != null) {
				if(zipEntry.isDirectory()){
					newDirectory(destDir, zipEntry);
				}else{	
					File newFile = newFile(destDir, zipEntry);
					
					try(FileOutputStream fos = new FileOutputStream(newFile)){
						int len;
						while ((len = zis.read(buffer)) > 0) {
							fos.write(buffer, 0, len);
						}
						fos.close();
					}
					files.add(newFile);
				}
				zipEntry = zis.getNextEntry();
			}
			zis.closeEntry();

		} catch (IOException e) {
			throw e;
		}
		return files;
	}

	private static void newDirectory(File destinationDir, ZipEntry zipEntry) throws IOException{
		File destFile = new File(destinationDir, zipEntry.getName());
		if(!destFile.exists()){
			Files.createDirectories(Paths.get(destFile.getCanonicalPath()));
			
		}
	}
	
	private static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
		File destFile = new File(destinationDir, zipEntry.getName());

		String destDirPath = destinationDir.getCanonicalPath();
		String destFilePath = destFile.getCanonicalPath();

		if (!destFilePath.startsWith(destDirPath + File.separator)) {
			throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
		}

		return destFile;
	}


}
