package com.example.demo.service;

import org.apache.commons.vfs2.*;
import org.apache.commons.vfs2.impl.DefaultFileSystemManager;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;
import org.springframework.stereotype.Service;

import static com.sun.tools.doclint.Entity.quot;

@Service
public class ReadFileService {

    public void read() throws Exception {

        FileSystemOptions fsOptions = new FileSystemOptions();
        SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(fsOptions, "no");

        SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(fsOptions, false);
        FileSystemManager fsManager = VFS.getManager();

        String uri = "sftp://ftpuser:ftp123@krishnachaitanyak.local/Users/krishnachaitanya/Documents/";
        FileObject fo = fsManager.resolveFile(uri, fsOptions);

        FileObject appFolder = null;

        appFolder = fo.resolveFile("FtpIncoming", NameScope.DESCENDENT_OR_SELF);

        if (appFolder.isReadable()) {
            System.out.println("n Get Children of appFolder");
            FileObject[] children = appFolder.getChildren();
            for (int i = 0; i < children.length; i++) {
                System.out.println(children[i].getName().getBaseName());
            }
        }

        fo.close();
        ((DefaultFileSystemManager) fsManager).close();

    }
}

