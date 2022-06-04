// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Upload.java

package com.dcc.ProjectManagementSystem.utils;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class Upload
{

    public Upload()
    {
    }

    public static void upLoadFile(MultipartFile img, String filePath, String fileName)
    {
        String newFilePath;
        File file = new File(filePath);
        if(!file.exists())
            file.mkdirs();
        System.out.println(new StringBuilder(filePath).append(fileName).toString());
        newFilePath =(new StringBuilder(filePath).append(fileName).toString());
        try
        {
            img.transferTo(new File(newFilePath));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return;
    }
}
