package org.openmuc.openiec61850;

import java.util.Calendar;

/**
 * Contains file information received by the GetFileDirectory service
 */
public class FileInformation {

    private final String filename;

    private final long fileSize;

    private final Calendar lastModified;

    public String getFilename() {
        return filename;
    }

    public long getFileSize() {
        return fileSize;
    }

    /**
     * Get the time stamp of last modification. As it is an optional attribute the return value can be null
     * 
     * @return the time stamp of last modification, or null if the time stamp is not present
     */
    public Calendar getLastModified() {
        return lastModified;
    }

    public FileInformation(String filename, long fileSize, Calendar lastModified) {
        super();
        this.filename = filename;
        this.fileSize = fileSize;
        this.lastModified = lastModified;
    }

}
