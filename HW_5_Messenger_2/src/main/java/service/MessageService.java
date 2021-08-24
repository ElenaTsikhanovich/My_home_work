package service;

import model.Message;
import model.User;
import service.api.IMessageService;
import storage.FileMessageStorage;
import storage.MessageStorage;
import storage.api.IMessageStorage;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class MessageService implements IMessageService {

    private static String PARAM_NAME_RECIPIENT="recipient";
    private static String PARAM_NAME_TEXT="text";

    private static MessageService instance=new MessageService();
    private IMessageStorage messageStorage;
    private UserService userService;

    private MessageService() {
        this.userService=UserService.getInstance();
    }

    @Override
    public void storageChoose(String storage) {
        if (storage.equalsIgnoreCase("file")){
            this.messageStorage=FileMessageStorage.getInstance();
        } else this.messageStorage=MessageStorage.getInstance();
    }

    @Override
    public boolean addMessage(HttpServletRequest request) {
        String recipient = request.getParameter(PARAM_NAME_RECIPIENT);
        boolean b = userService.loginExist(recipient);
        if(b) {
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy"));
            User user = (User) request.getSession().getAttribute("user");
            String sender = user.getLogin();
            String text = request.getParameter(PARAM_NAME_TEXT);
            Message message = new Message(time, sender, recipient, text);
            this.messageStorage.putMessage(message);
            return true;
        } else return false;
    }

    @Override
    public List<Message> getMessage(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String login = user.getLogin();
        List<Message> collectMessages = this.messageStorage.getAllMessages().stream()
                .filter(x -> x.getRecipient().equalsIgnoreCase(login)).collect(Collectors.toList());

        return collectMessages;
    }

    public static MessageService getInstance() {
        return instance;
    }
}
