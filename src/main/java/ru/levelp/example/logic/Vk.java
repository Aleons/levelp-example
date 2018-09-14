package ru.levelp.example.logic;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("VK")
public class Vk implements SocialNetwork {
    @Override
    public void postMessage(String title, String text) {
        System.out.println("Post to VK");
    }
}
