package com.example.messegesaver.endpoits;

import com.example.messegesaver.repository.MessageRepository;
import com.messagesaver.messages.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class MessageSaverEndpoint {

    public static final String NAMESPACE_URI = "http://www.messagesaver.com/messages";

    private MessageRepository messageRepository;

    @Autowired
    public MessageSaverEndpoint(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllMessagesRequest")
    @ResponsePayload
    public GetAllMessagesResponse getAllMessages() {
        GetAllMessagesResponse response = new GetAllMessagesResponse();
        List<Message> messages = messageRepository.getAllMessages();
        response.getMessage().addAll(messages);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllAuthorMessagesRequest")
    @ResponsePayload
    public GetAllAuthorMessagesResponse getAllAuthorMessages(@RequestPayload GetAllAuthorMessagesRequest request) {
        GetAllAuthorMessagesResponse response = new GetAllAuthorMessagesResponse();
        List<Message> messages = messageRepository.getAllAuthorMessages(request.getAuthorName());
        response.getMessage().addAll(messages);
        return  response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AddMessageRequest")
    @ResponsePayload
    public AddMessageResponse addMessage(@RequestPayload AddMessageRequest request) {
        AddMessageResponse response = new AddMessageResponse();
        response.setMessage(messageRepository.addMessage(request.getMessage()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeleteMessagesByAuthorRequest")
    public void deleteAuthorMessages(@RequestPayload DeleteMessagesByAuthorRequest request) {
        messageRepository.deleteAuthorMessages(request.getAuthorName());
    }
}
