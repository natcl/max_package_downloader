import com.cycling74.max.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
 
public class package_downloader extends MaxObject
{     
  public void unzip(String zipfile, String outputfolder, String packagename, String relativepath) 
  {
    String rootFolder = unZipIt(zipfile, outputfolder);
    File from = null;
    
    //rename package
    if (relativepath.equals("None"))
    {
      from = new File(outputfolder + File.separator + rootFolder);
    }
    else
    {
      from = new File(outputfolder + File.separator + rootFolder + File.separator + relativepath);
    }
    
    File to = new File(outputfolder + File.separator + packagename);
    
    //if package was already there, delete it
    if (!rootFolder.equals(packagename + "/"))
    {
        if (to.exists())
        {
          deleteFolder(to);
        }
        from.renameTo(to);
    }


    //if package was in subfolder, delete parent folder
    if (!relativepath.equals("None"))
    {
      File dir_to_delete = new File(outputfolder + File.separator + rootFolder);
      deleteFolder(dir_to_delete);
    }

    //delete zip file
    File to_delete = new File(zipfile);
    to_delete.delete();
  }

  public static void deleteFolder(File folder) 
  {
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

  public String unZipIt(String zipFile, String outputFolder)
  {
 
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
 
           //System.out.println("file unzip : "+ newFile.getAbsoluteFile());
 
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
 
        System.out.println("Unzipped " + zipFile + " package to " + outputFolder);
        outlet(0,"bang");

    }catch(IOException ex){
       ex.printStackTrace(); 
    }
   return rootFolder;  
  }  
}




































































































