package util;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;

import constants.ProjectConstants;
import pojo.Student;


public class UtilityClass
{
	public static String getTime()
	{
		Date d = new Date(System.currentTimeMillis());
		return d.toLocaleString();
	}

	
	public static File getUploadedResume(Student stud) throws FileNotFoundException
	{
		String fileName = stud.getFirstName() + "_" + stud.getMiddleName() + "_" + stud.getLastName();

		String directoryPath = ProjectConstants.resumeUploadDirectory + stud.getDept() + "\\" + stud.getYear();
		File[] fileNameArray = new File(directoryPath).listFiles();

		if (fileNameArray == null)
		{
			throw new FileNotFoundException();
		}
		else
		{
			for (File file : fileNameArray)
			{
				String extendedFilename = file.getName();
				System.out.println(extendedFilename);
				System.out.println(fileName);
				if (extendedFilename.contains(fileName))
					fileName = extendedFilename;
			}

			File uploadedFile = new File(directoryPath + "\\" + fileName);
			if (uploadedFile.exists())
				return uploadedFile;
			else
				throw new FileNotFoundException();
		}
	}
	

	public static String getFileStatus(Student stud)
	{
		try
		{
			File uploadedResume = getUploadedResume(stud);
			Date lastModified = new Date(uploadedResume.lastModified());
			return "Resume was uploaded on " + lastModified.toLocaleString();
		}
		catch (FileNotFoundException e)
		{
			return "No saved resume found, Please upload your resume ASAP";
		}
	}

	public static String getCapitalized(String str)
	{
		return String.valueOf(str.charAt(0)).toUpperCase() + str.substring(1);
	}
}
