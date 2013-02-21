package server;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.URL;
import java.util.logging.Logger;

public class Navigator {
	FileOperate fileOperate = new FileOperate();
	DocumentBuilderFactory factory = null;
    DocumentBuilder builder  = null;
    Document doc = null;
    Element root = null;
    String filePosition = "E:\\OrDesignerFiles";
    String fileMessage = "E:\\test.xml";
    int ID = 1000;
	  private static Logger theLogger =
	      Logger.getLogger(Navigator.class.getName());

	public Navigator(){
		String dir = this.getAppPath(this.getClass());

		System.out.println(" ****************************************************dir = "+dir);
		theLogger.info("testtttttttttttttttttttttttttttttttttttttttt");

		dir = dir.replace('/', File.separatorChar);
		//System.out.println(" ****************************************************dir 2 = "+dir);
		dir = dir.substring(0, dir.lastIndexOf(File.separator));
		//System.out.println(" ****************************************************dir 3 = "+dir);
		dir = dir.substring(0, dir.lastIndexOf(File.separator));
		//System.out.println(" ****************************************************dir 4 = "+dir);

		filePosition = dir + File.separator + "OrchestraDesignerFiles";
		//System.out.println(" ****************************************************filePosition = "+filePosition);
		fileMessage = dir + File.separator + "file.xml";
		//System.out.println(" ****************************************************fileMessage = "+fileMessage);
		File file = new File(filePosition);
		if(!file.exists())
			file.mkdir();
		file = new File(fileMessage);
		if(!file.exists())
			try {
				boolean f = file.createNewFile();
				//System.out.println(" **************************************************** Good create file  : "+f);
			} catch (IOException e) {
				e.printStackTrace();
			}
        factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
			doc = builder.newDocument();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
        root = doc.createElement("Root");
        root.setAttribute("name", "root");
        doc.appendChild(root);

		this.getDirectory(filePosition, root, 1);
		this.saveXml(fileMessage);
	}

	private void getDirectory(String parentPath, Element parentElement, int levels){
		File pathName = new File(parentPath);
		//System.out.println("****************************************************pathName.getName() = "+pathName.getName());
		String[] fileNames = pathName.list();
		String value = null, thisValue = null;
		for(int i = 0; i<fileNames.length; i++){
			//System.out.println("**************************************************** fileNames[i] = "+ fileNames[i]);
			File f = new File(pathName.getPath(), fileNames[i]);
			value = f.getPath().substring(19, f.getPath().length());
			//System.out.println("**************************************************** value = "+ value);
			thisValue = value.substring(value.lastIndexOf(File.separator)+1, value.length());
			//System.out.println("**************************************************** thisValue = "+ thisValue);
			if(!f.isHidden())
			{
				if( f.isDirectory() ){

					//value = f.getPath().substring(19, f.getPath().length());
					Element folder = doc.createElement("folder");
					folder.setAttribute("name", thisValue);
					if(levels == 1)
						folder.setAttribute("type", "project");
					else
						folder.setAttribute("type", "folder");
					parentElement.appendChild(folder);
					getDirectory(f.getPath(), folder, levels+1);
				}
				else{
					if( f.getName().endsWith("xml") || f.getName().endsWith("bpel") || f.getName().endsWith("bpmn") ){
						Element file = doc.createElement("file");
						file.setAttribute("name", thisValue);
						ID--;
						file.setAttribute("ID", "File-" + String.valueOf(ID));
						if(f.getName().endsWith("xml"))
							file.setAttribute("type", "Figure_Editor_Type");
						else if(f.getName().endsWith("bpel"))
							file.setAttribute("type", "Bpel_Editor_Type");
						parentElement.appendChild(file);
					}
				}
			}
		}
	}

	private void saveXml(String fileName){
		 TransformerFactory transFactory=TransformerFactory.newInstance();
		 Transformer transformer = null;
		try {
			transformer = transFactory.newTransformer();
			transformer.setOutputProperty("indent", "yes");
	        DOMSource source=new DOMSource();
	        source.setNode(doc);
	        StreamResult result=new StreamResult();
	        FileOutputStream fileOutputStream =  new FileOutputStream(fileName);
	        result.setOutputStream(fileOutputStream);
			transformer.transform(source, result);
			fileOutputStream.close();

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (TransformerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


    /**
	 * getAppPath
	 * Java
	 *
	 * @param
	 * @return
	 */
	private String getAppPath(Class cls) {

		if (cls == null)
			throw new IllegalArgumentException("ERROR 1");
		ClassLoader loader = cls.getClassLoader();

		String clsName = cls.getName() + ".class";

		Package pack = cls.getPackage();
		String path = "";

		if (pack != null) {
			String packName = pack.getName();

			if (packName.startsWith("java.") || packName.startsWith("javax."))
				throw new IllegalArgumentException("ERROR 2");

			clsName = clsName.substring(packName.length() + 1);

			if (packName.indexOf(".") < 0)
				path = packName + "/";
			else {
				int start = 0, end = 0;
				end = packName.indexOf(".");
				while (end != -1) {
					path = path + packName.substring(start, end) + "/";
					start = end + 1;
					end = packName.indexOf(".", start);
				}
				path = path + packName.substring(start) + "/";
			}
		}

		URL url = loader.getResource(path + clsName);

		String realPath = url.getPath();

		int pos = realPath.indexOf("file:");
		if (pos > -1)
			realPath = realPath.substring(pos + 5);

		pos = realPath.indexOf(path + clsName);
		realPath = realPath.substring(0, pos - 1);

		if (realPath.endsWith("!"))
			realPath = realPath.substring(0, realPath.lastIndexOf("/"));

		try {
			realPath = java.net.URLDecoder.decode(realPath, "utf-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return realPath;
	}

	public String getXml(){
		String xml = null;
		BufferedReader br;
        String read = "";
        try {
            File file = new File(fileMessage);
            FileReader fileread = new FileReader(file);
            br = new BufferedReader(fileread);
            while ((read = br.readLine()) != null) {
                xml = xml + read;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return xml.substring(4);
	}
	/**
	 * @param projectName
	 * @return
	 */
	public boolean createNewProject(String projectName){
		File file = new File(filePosition + File.separator + projectName);
		if(file.exists()){
			return false;
		}
		else{
			file.mkdir();
			return true;
		}
	}
	/**
	 * @param folderName
	 * @param parentPath
	 * @return
	 */
	public boolean createNewFolder(String folderName, String parentPath){

		File file = new File(filePosition + File.separator + parentPath + File.separator +folderName);
		if(file.exists()){
			return false;
		}
		else{
			file.mkdir();
			return true;
		}
	}
	/**
	 * @param fileName
	 * @param parentPath
	 * @return ID, if fail to create file return -1
	 */
	public int createNewFile(String fileName, String parentPath) {
		File file = new File(filePosition + File.separator + parentPath + File.separator +fileName);
		if(file.exists()) {
			return -1;
		}
		else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return -1;
			}
			ID--;
			return ID;
		}
	}

	public String openFile(String path){

		path = this.getStringWithFileSeparator(path);

		String xml = "";
		BufferedReader br;
        String read = "";
        try {
            File file = new File(filePosition + File.separator + path);
            FileReader fileread = new FileReader(file);
            br = new BufferedReader(fileread);
            while ((read = br.readLine()) != null) {
                xml = xml + read;
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
		return xml;
	}

	/**
	 * Change string path with correct file separator
	 * @param path
	 * @return
	 */
	private String getStringWithFileSeparator(String path){
		String s="";
		for(int i=0; i<path.length(); i++)
			if(path.charAt(i)=='\\'){
				s+=File.separator;
			}else{
				s+=path.charAt(i);
			}

		return s;
	}

	public boolean saveFile(String path, String xml){

		String newxml = "";
		if(path.endsWith("xml"))
			newxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + xml;
		else
			newxml = xml;
		System.out.println("************** filePosition  = "+filePosition);
		System.out.println("**************  File.separator = "+File.separator);
		System.out.println("************** path = "+path);
		System.out.println("**************  = "+"\\");
		filePosition.replace("\\",File.separator);

		path = this.getStringWithFileSeparator(path);

		System.out.println("apres replace************** filePosition  = "+filePosition);
		System.out.println("apres replace************** path = "+path);

		//File file = new File(filePosition + File.separator + path);
		File file = new File(filePosition + File.separator + path);
		try {
			DataOutputStream outs = new DataOutputStream(new FileOutputStream(file));
			outs.write(newxml.getBytes());
			outs.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean deleteFile(String path){

		File file = new File(filePosition + File.separator + path);
		if(file.isFile() && file.exists()){
			file.delete();
			return true;
		}
		else if(file.isDirectory()){
			this.deleteFolder(filePosition + File.separator + path);
			return true;
		}
		return false;
	}
	private void deleteFolder(String path) {
		File file = new File(path);
		String [] fileList = file.list();
		File temp = null;
		for(int i = 0; i < fileList.length; i++){
			temp = new File(path + File.separator + fileList[i]);
			if(temp.isFile())
				temp.delete();
			if(temp.isDirectory())
				this.deleteFolder(path + File.separator + fileList[i]);
		}
		file.delete();
	}
	/**
	 *
	 * @param sourcePath
	 * @param targetPath
	 * @return
	 */
	public void copyFile(String sourcePath, String targetPath){
		File source = new File(filePosition + File.separator + sourcePath);
		if(source.isFile()){
			File target = new File(filePosition + File.separator + targetPath + File.separator + source.getName());
			if(!target.exists())
				try {
					target.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			this.fileCopy(filePosition + File.separator + sourcePath,
					filePosition + File.separator + targetPath + File.separator + target.getName());
		}
		else if(source.isDirectory()){
			File target = new File(filePosition + File.separator + targetPath + File.separator + source.getName());
			if(!target.exists()){
				target.mkdir();
			}
			this.fileCopy(filePosition + File.separator + sourcePath,
					filePosition + File.separator + targetPath + File.separator + source.getName());
		}
	}
	public boolean cutFile(String sourcePath, String targetPath){
		copyFile(sourcePath, targetPath);
		deleteFile(sourcePath);
		return true;
	}
	private void fileCopy(String a, String b) {
		File file = new File(a);
		if (!file.exists()) {
			System.out.println(a + "  Not Exists. ");
			return;
		}
		File fileb = new File(b);
		if (file.isFile()) {
			FileInputStream fis = null;
			FileOutputStream fos = null;
			try {
				fis = new FileInputStream(file);
				fos = new FileOutputStream(fileb);
				int bytesum = 0, byteread = 0;
				byte[] buffer = new byte[1444];
				while ((byteread = fis.read(buffer)) != -1) {
					bytesum += byteread;
					fos.write(buffer, 0, byteread);
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fis.close();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else if (file.isDirectory()) {
			if (!fileb.exists()) {
				fileb.mkdir();
			}
			String[] fileList;
			fileList = file.list();
			for (int i = 0; i < fileList.length; i++) {
				fileCopy(a + File.separator + fileList[i], b + File.separator + fileList[i]);
			}
		}
	}
	public void renameFile(String sourcePath, String newName){
		sourcePath = filePosition + File.separator + sourcePath;
		String targetPath = sourcePath.substring(0, sourcePath.lastIndexOf(File.separator)) + File.separator + newName;
		File source = new File(sourcePath);
		File target = new File(targetPath);
		source.renameTo(target);
	}


	public static void main(String[] args){
		Navigator navigator = new Navigator();
		navigator.createNewProject("aaa");
		navigator.createNewFile("houssem", "aaa");
		navigator.saveFile("aaa\\houssem", "houssemm");
		String xml = navigator.getXml();
		System.out.println("getXML : "+xml);
		//navigator.
//		navigator.createNewFolder("bbb", "aaa");
//		navigator.createNewFile("ccc.xml", "aaa");
//		navigator.saveFile("908", "<Process/>");
//		navigator.saveFile("aaa", "aaa/aaa", "adf");
//		navigator.deleteFile("aaa\\bbb\\aaa.xml");
//		System.out.println("over");
//		navigator.openFile("905");
//		navigator.getXml();
//		navigator.renameFile("aab\\bbb\\bbc.xml", "aab.xml");\
//		navigator.sayHelloTo("my");
	}
}