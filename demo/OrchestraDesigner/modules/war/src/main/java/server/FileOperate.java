package server;

import java.io.*;

final class FileOperate {
	public FileOperate() {

	}

	/**
	 *
	 *
	 * @param filePathAndName
	 *            String
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public void deleteFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myDelFile = new File(filePath);
			myDelFile.delete();
		} catch (Exception e) {
			System.out.println("ERROR 1");
			e.printStackTrace();
		}
	}

	/**
	 *
	 *
	 * @param filePathAndName
	 *            String
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public boolean deleteFolder(String folderPath) {
		try {
			deleteAllFile(folderPath);
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete();
			return true;
		} catch (Exception e) {
			System.out.println("ERROR2");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 *
	 *
	 * @param path
	 *            String
	 */
	public void deleteAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				deleteAllFile(path + "/" + tempList[i]);
				deleteFolder(path + "/" + tempList[i]);
			}
		}
	}

	/**
	 * ���Ƶ����ļ�
	 *
	 * @param oldPath
	 *            String
	 * @param newPath
	 *            String
	 * @return boolean
	 */
	public static void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) {
				InputStream inStream = new FileInputStream(oldPath);
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread;
//					System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("ERROR 2");
			e.printStackTrace();
		}
	}
	public boolean copy(String sourceDir, String targetDir){
		String folder = sourceDir.substring(sourceDir.lastIndexOf(File.separator));
		File file = new File(targetDir + folder);
		if(file.exists()){
			copyFolder(sourceDir, targetDir + folder);
			return false;
		}
		else{
			file.mkdir();
			copyFolder(sourceDir, targetDir + folder);
			return true;
		}
	}

	/**
	 *
	 *
	 * @param oldPath
	 *            String
	 * @param newPath
	 *            String
	 * @return boolean
	 */
	public static void copyFolder(String oldPath, String newPath) {

		try {
//			(new File(newPath)).mkdirs();
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					System.out.println(temp.getName());
					(new File(newPath + File.separator + temp.getName())).mkdirs();
					FileOutputStream output = new FileOutputStream(newPath
							+ File.separator + temp.getName());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			System.out.println("ERROR 3");
			e.printStackTrace();

		}

	}

	/**
	 *
	 *
	 * @param oldPath
	 *            String
	 * @param newPath
	 *            String
	 */
	public void moveFile(String oldPath, String newPath) {
		copyFile(oldPath, newPath);
		deleteFile(oldPath);

	}

	/**
	 *
	 *
	 * @param oldPath
	 *            String
	 * @param newPath
	 *            String
	 */
	public void moveFolder(String oldPath, String newPath) {
		copyFolder(oldPath, newPath);
		deleteFolder(oldPath);
	}


	public static void copyDirectiory(String sourceDir, String targetDir)
			throws IOException {

		(new File(targetDir)).mkdirs();

		File[] file = (new File(sourceDir)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {

				File sourceFile = file[i];

				File targetFile = new File(new File(targetDir)
						.getAbsolutePath()
						+ File.separator + file[i].getName());
				copyFile(sourceFile, targetFile);
			}
			if (file[i].isDirectory()) {

				String dir1 = sourceDir + File.separator + file[i].getName();

				String dir2 = targetDir + File.separator + file[i].getName();
				copyDirectiory(dir1, dir2);
			}
		}
	}


	public static void copyFile(File sourceFile, File targetFile)
			throws IOException {

		FileInputStream input = new FileInputStream(sourceFile);
		BufferedInputStream inBuff = new BufferedInputStream(input);


		FileOutputStream output = new FileOutputStream(targetFile);
		BufferedOutputStream outBuff = new BufferedOutputStream(output);


		byte[] b = new byte[1024 * 5];
		int len;
		while ((len = inBuff.read(b)) != -1) {
			outBuff.write(b, 0, len);
		}

		outBuff.flush();

		inBuff.close();
		outBuff.close();
		output.close();
		input.close();
	}

	public static void main(String args[]) {
		FileOperate fileOperate = new FileOperate();
		String sourceDir = "D:\\OrDesignerFiles\\work";
		String targetDir = "D:\\OrDesignerFiles\\test";
//		try {
//			FileOperate.copyDirectiory("D:\\OrDesignerFiles\\work", "D:\\OrDesignerFiles\\test");
//		} catch (IOException e) {
//			// TODO
//			e.printStackTrace();
//		}
		fileOperate.copy(sourceDir, targetDir);
	}
}
