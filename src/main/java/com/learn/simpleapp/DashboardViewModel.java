package com.learn.simpleapp;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Locators;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.Filedownload;

import com.learn.simpleapp.model.FileDTO;

public class DashboardViewModel {

	private String title;
	private ArrayList<FileDTO> listFile;

	@Init
	public void init() {
		initFile();
		title = "File Manager";
	}

	public void initFile() {
		// URL url =
		// Executions.getCurrent().getDesktop().getWebApp().getResource("/resources/storage/readme.txt");
		URL url = Executions.getCurrent().getDesktop().getWebApp().getResource("/resources/storage");
		File file = new File("");
		listFile = new ArrayList<FileDTO>();
		try {
			file = new File(URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8.name()));
			for (final File fileEntry : file.listFiles()) {
				if (fileEntry.isFile()) {
					String temp_name = fileEntry.getName();
					Double temp_size = (double) fileEntry.length();
					String temp_location = "/resources/storage/" + fileEntry.getName();
					FileDTO itemFile = new FileDTO(temp_name, temp_size, temp_location);
					listFile.add(itemFile);
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Command
	public void btnDownload(@BindingParam("idx") int index) {
		FileDTO tempFile = listFile.get(index);
		InputStream is = Executions.getCurrent().getDesktop().getWebApp().getResourceAsStream(tempFile.getLocation());
		Filedownload.save(is, null, tempFile.getName());
	}
	
	@Command
	public void btnBrowse(@ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event) {
		Media media = event.getMedia();
	}

	public String getTitle() {
		return title;
	}

	public ArrayList<FileDTO> getListFile() {
		return listFile;
	}

}
