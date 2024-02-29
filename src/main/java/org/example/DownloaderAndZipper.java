package org.example;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DownloaderAndZipper implements Listener {
    private final UrlManager urlManager;

    // Constructor
    public DownloaderAndZipper(UrlManager urlManager) {
        this.urlManager = urlManager;
    }

    @Override
    public void update(String url, String randomString) {
        if (url.isEmpty()) {
            // Si la URL está vacía, se procede a descargar y comprimir los ficheros
            System.out.println("Se va a proceder a descargar y comprimir los ficheros");
            CompletableFuture.runAsync(this::downloadFiles)
                    .thenRun(this::compressFiles);
        } else {
            // Si la URL no está vacía, se encola la URL con un nombre aleatorio
            System.out.println(url + " encolado como " + randomString);
        }
    }

    // Este método descarga los ficheros
    private void downloadFiles() {
        for (Map.Entry<String, String> entry : urlManager.getUrls().entrySet()) {
            String url = entry.getKey();
            String filename = entry.getValue();
            if (url == null || url.isEmpty() || !url.contains("://")) {
                System.out.println("Invalid URL: " + url);
                continue;
            }
            try (ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(url).openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
                fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
// Este método comprime los ficheros descargados en un archivo ZIP llamado "compressed.zip"
    private void compressFiles() {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream("compressed.zip"))) {
            for (String filename : urlManager.getUrls().values()) {
                File file = new File(filename);
                if (!file.exists()) {
                    System.out.println("File does not exist: " + filename);
                    continue;
                }
                zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                try (FileInputStream fileInputStream = new FileInputStream(file)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fileInputStream.read(buffer)) > 0) {
                        zipOutputStream.write(buffer, 0, length);
                    }
                }
                zipOutputStream.closeEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
