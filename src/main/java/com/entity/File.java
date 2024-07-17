package com.entity;

import java.util.Arrays;
import java.util.Base64;

public class File {
    private int id;
    private String filename;
    private byte[] filedata;

    public File() {
    }

    public File(String filename, byte[] filedata) {
        this.filename = filename;
        this.filedata = filedata;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getFiledata() {
        if (filedata.length > 0) {
            return filedata;
        }
        return new byte[0];
    }

    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }

    public String getBase64String () {
        return Base64.getEncoder().encodeToString(this.filedata);
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", filedata=" + Arrays.toString(filedata) +
                '}';
    }
}

