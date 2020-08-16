package ru.geekbrains.net;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
    public static class Message {
        String nickName;
        String message;
        String messageType;

        // Обязательно нужен пустой конструктор
        public Message() {}

        public Message(String nickName, String message, String messageType) {
            this.nickName = nickName;
            this.message = message;
            this.messageType = messageType;
        }

        public String getNickName() {
            return nickName;
        }
        public void setNickName(String nickName) {
            this.nickName = nickName;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
        public String getMessageType() {
            return messageType;
        }
        public void setMessageType(String messageType) {
            this.messageType = messageType;
        }

        @Override
        public String toString() {
            return "Message{" + "nickName='" + nickName + '\'' +
                    ", message='" + message + '\'' +
                    ", messageType='" + messageType + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) throws JsonProcessingException {
        Message msg = new Message("someNickName", "sdaflkngsjndfjsndf", "BROADCAST");
        String json = new ObjectMapper().writeValueAsString(msg);
        System.out.println(json); // -> {"nickName":"someNickName","message":"sdaflkngsjndfjsndf","messageType":"BROADCAST"}

        Message msg2 = new ObjectMapper().readValue(json, Message.class);
        System.out.println(msg2); // -> Message{nickName='someNickName', message='sdaflkngsjndfjsndf', messageType='BROADCAST'}
    }
}
