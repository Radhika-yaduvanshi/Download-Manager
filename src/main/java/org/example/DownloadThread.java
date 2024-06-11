package org.example;

import org.example.models.FileInfo;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DownloadThread extends Thread {

    private final FileInfo file;
    DownloadManager manager;

    public DownloadThread(FileInfo file,DownloadManager manager)
    {
        this.file=file;
        this.manager=manager;
    }
    @Override
    public void run() {

        //download logic

        this.file.setStatus("DOWNLOADING");
        this.manager.updateUI(this.file);

        //logic
        try
        {
            Files.copy(new URL(this.file.getUrl()).openStream(), Paths.get(this.file.getPath()));
            this.file.setStatus(("Done"));
        }
        catch (IOException e)
        {
//            throw new RuntimeException(e);
            this.file.setStatus("Failed");
            System.out.println("Downloading error");
            e.printStackTrace();
        }
        this.manager.updateUI(this.file);


    }
}
