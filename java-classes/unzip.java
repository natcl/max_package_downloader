import com.cycling74.max.*;
import java.io.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
 
public class unzip extends MaxObject {
        
  public void unzip(String zipfile, String outputfolder, String packagename) {
    UnZip_main unZip = new UnZip_main();
    String rootFolder = unZip.unZipIt(zipfile, outputfolder);
    
    //rename package
    File from = new File(outputfolder + File.separator + rootFolder);
    File to = new File(outputfolder + File.separator + packagename);
    //if package was already there, delete it
    if (to.exists()){
      deleteFolder(to);
    }
    from.renameTo(to);

    //delete zip file
    File to_delete = new File(zipfile);
    to_delete.delete();


 
  }

  public static void deleteFolder(File folder) {
    File[] files = folder.listFiles();
    if(files!=null) { //some JVMs return null for empty dirs
        for(File f: files) {
            if(f.isDirectory()) {
                deleteFolder(f);
            } else {
                f.delete();
            }
        }
    }
    folder.delete();
  }
}

class UnZip_main
{
    /**
     * Unzip it
     * @param zipFile input zip file
     * @param output zip file output folder
     */
    public String unZipIt(String zipFile, String outputFolder){
 
     byte[] buffer = new byte[1024];
     String rootFolder = "None";
 
     try{
 
        //create output directory is not exists
        File folder = new File(outputFolder);
        if(!folder.exists()){
            folder.mkdir();
        }
 
        //get the zip file content
        ZipInputStream zis = 
            new ZipInputStream(new FileInputStream(zipFile));
        //get the zipped file list entry
        ZipEntry ze = zis.getNextEntry();
        rootFolder = ze.getName();

        while(ze!=null){
 
           String fileName = ze.getName();
           File newFile = new File(outputFolder + File.separator + fileName);
 
           System.out.println("file unzip : "+ newFile.getAbsoluteFile());
 
            //create all non exists folders
            //else you will hit FileNotFoundException for compressed folder
 
           if(ze.isDirectory()) 
           {
               new File(newFile.getParent()).mkdirs();
           }
           else
           {
            FileOutputStream fos = null;
 
            new File(newFile.getParent()).mkdirs();
 
            fos = new FileOutputStream(newFile);             
 
            int len;
            while ((len = zis.read(buffer)) > 0) 
            {
            fos.write(buffer, 0, len);
            }
 
            fos.close();   
           }
 
           ze = zis.getNextEntry();
        }
 
        zis.closeEntry();
        zis.close();
 
        System.out.println("Done");

    }catch(IOException ex){
       ex.printStackTrace(); 
    }
   return rootFolder;  
   }  
}

















































