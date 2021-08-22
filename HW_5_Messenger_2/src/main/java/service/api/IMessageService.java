package service.api;

import model.Message;
import storage.MessageStorage;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IMessageService {
    boolean addMessage(HttpServletRequest request);
    List<Message> getMessage(HttpServletRequest request);

}
