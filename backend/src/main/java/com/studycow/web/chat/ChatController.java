package com.studycow.web.chat;

import com.studycow.dto.chat.ChatMessage;
import com.studycow.repository.studyroom.StudyRoomRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final StudyRoomRepository studyRoomRepository;

    @MessageMapping("/chat/message")
    public void message(ChatMessage message){
        if(ChatMessage.MessageType.ENTER.equals(message.getType()))
            studyRoomRepository.
            message.setMessage(message.getSender()+"님이 입장하셨습니다.");
        System.out.println(message);
        messagingTemplate.convertAndSend("/sub/chat/room"+message.getRoomId(),message);
    }
}
