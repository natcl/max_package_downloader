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
        
  public void unzip(String zipfile, String outputfolder) {
    UnZip_main unZip = new UnZip_main();
    unZip.unZipIt(zipfile, outputfolder);
  }
}

class UnZip_main
{
    /**
     * Unzip it
     * @param zipFile input zip file
     * @param output zip file output folder
     */
    public void unZipIt(String zipFile, String outputFolder){
 
     byte[] buffer = new byte[1024];
 
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
        File to_delete = new File(zipFile);
        to_delete.delete();
 
    }catch(IOException ex){
       ex.printStackTrace(); 
    }
   }    
}


























