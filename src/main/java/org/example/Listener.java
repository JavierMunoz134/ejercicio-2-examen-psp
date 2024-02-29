package org.example;

public interface Listener {
    // Este método se ejecutará cuando el observable notifique a los observadores
    void update(String url, String randomString);
}
