package data;

public class Message {
    private String content;
    private String timestamp;

    public Message(String content, String timestamp) {
        this.content = content;
        this.timestamp = timestamp;
    }

    // Getters and setters

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
