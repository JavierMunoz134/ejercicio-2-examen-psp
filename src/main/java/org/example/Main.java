package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UrlManager urlManager = new UrlManager();
        urlManager.addListener(new DownloaderAndZipper(urlManager));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Introduce una URL (o 'salir' para terminar):");
            String url = scanner.nextLine();

            if ("salir".equalsIgnoreCase(url)) {
                break;
            }
            urlManager.addUrl(url);
        }
        scanner.close();
    }
}