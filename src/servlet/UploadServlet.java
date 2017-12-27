package servlet;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import constants.ProjectConstants;
import pojo.Student;


@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Part filePart = request.getPart("uploadResume");
		InputStream fileContentInputStream = filePart.getInputStream();
		String fileName = filePart.getSubmittedFileName();

		String[] split = fileName.split("\\.");
		String fileFormat = split[split.length - 1];
		Student stud = (Student) request.getSession().getAttribute(ProjectConstants.studentSession);
		fileName = stud.getFirstName() + "_" + stud.getMiddleName() + "_" + stud.getLastName() + "." + fileFormat;

		String directoryPath = ProjectConstants.resumeUploadDirectory + stud.getDept() +"\\" + stud.getYear();
		File directory = new File(directoryPath);
		
		if (directory.exists() == false)
		{
			System.out.println("1");
			boolean status = directory.mkdirs();
			System.out.println("file bana ? "+status);
			System.out.println("2");
		}

		File file = new File(directoryPath +"\\" +fileName);

		OutputStream outputStream = new FileOutputStream(file);
		IOUtils.copy(fileContentInputStream, outputStream);
		outputStream.close();
		
		request.setAttribute("uploadStatus", "File uploaded successfully");
		request.getRequestDispatcher("profile.jsp").include(request, response);
	}

}

