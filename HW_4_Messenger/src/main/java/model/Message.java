package model;



public class Message {
    private String time;
    private String sender;
    private String recipient;
    private String text;

    public Message() {
    }

    public Message(String time, String sender, String recipient, String text) {
        this.time = time;
        this.sender = sender;
        this.recipient = recipient;
        this.text= text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

