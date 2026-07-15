package com.example.agile.services;

import com.example.agile.tools.TaskTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;


@Service
public class ChatService {
    private final ChatClient chatClient;

    public ChatService(OllamaChatModel chatModel, TaskTool taskTool) {
        chatClient = ChatClient.builder(chatModel)
                .defaultSystem("You are an Agile expert." +
                        "You have access to the task database (User Task)." +
                        "When a user asks about tasks" +
                        "use the available functions to obtain up-to-date information.")
                .defaultTools(taskTool)
                .build();
    }


    public String sendMessage(String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }
}