package com.lbyt.client.bean;

import java.io.File;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class CustomerImportJsonBean extends FileUploadJsonBean {

	private static final long serialVersionUID = 7136919029242968412L;
	
	private File file;

	@JsonIgnore
	public File getFile() {
		return file;
	}

	@JsonProperty("file")
	public void setFile(final File file) {
		this.file = file;
	}

}
