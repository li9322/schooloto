package com.li.dto;

import java.io.InputStream;

/**
 * @ClassName: ImageHolder
 * @Description:
 * @author: libl
 * @date: 2019/06/10 15:50
 */
public class ImageHolder {
    private InputStream ins;
    private String fileName;

    /**
     * @Description: 构造函数
     * @Param: ins
     * @Param: fileName
     */
    public ImageHolder(InputStream ins, String fileName) {
        this.ins = ins;
        this.fileName = fileName;
    }

    public InputStream getIns() {
        return ins;
    }

    public void setIns(InputStream ins) {
        this.ins = ins;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
