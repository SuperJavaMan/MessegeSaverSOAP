package com.example.messegesaver.repository;

import com.messagesaver.messages.Message;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MessageRepository {

    private HashMap<String, LinkedList<Message>> msgRepo;

    public MessageRepository() {
        this.msgRepo = new HashMap<>();

        Message first = new Message();
        first.setAuthor("Oleg");
        first.setBody("Lalala");
        LinkedList<Message> messageList1 = new LinkedList<>();
        messageList1.add(first);

        Message second = new Message();
        second.setAuthor("Li");
        second.setBody("Nanana");
        LinkedList<Message> messageList2 = new LinkedList<>();
        messageList2.add(second);
        msgRepo.put(first.getAuthor(), messageList1);
        msgRepo.put(second.getAuthor(), messageList2);
    }

    public List<Message> getAllMessages() {
        List<Message> allMessages = new LinkedList<>();
        for (List<Message> list : msgRepo.values()) {
            allMessages.addAll(list);
        }
        return allMessages;
    }

    public List<Message> getAllAuthorMessages(String authorName) {
        if (msgRepo.containsKey(authorName)) {
            return msgRepo.get(authorName);
        } else {
            throw new NoSuchElementException();
        }
    }

    public Message addMessage(Message msg) {
        if (msgRepo.containsKey(msg.getAuthor())) {
            LinkedList<Message> messageList = new LinkedList<>();
            messageList.add(msg);
            msgRepo.put(msg.getAuthor(), messageList);
            return msgRepo.get(msg.getAuthor()).getLast();
        } else {
            LinkedList<Message> messageList = msgRepo.get(msg.getAuthor());
            messageList.add(msg);
            return msgRepo.get(msg.getAuthor()).getLast();
        }
    }

    public void deleteAuthorMessages(String author) {
        msgRepo.remove(author);
    }
}
