package com.chatapp.Chat.App.websocket;

import com.chatapp.Chat.App.security.jwt.JwtUtils;
import com.chatapp.Chat.App.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AuthorizationSocketInterceptor implements ChannelInterceptor {
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public Message<?> preSend(final Message<?> message, final MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (accessor == null) throw new AccessDeniedException("Failed to read message header.");

        List<StompCommand> stompCommands = Arrays.asList(StompCommand.CONNECT, StompCommand.SUBSCRIBE,
                StompCommand.SEND, StompCommand.MESSAGE);


        if (stompCommands.contains(accessor.getCommand())) {
            validateAuthHeader(accessor);
        }

        return message;
    }

    private void validateAuthHeader(StompHeaderAccessor accessor) {
        String authHeader = accessor.getFirstNativeHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println(accessor.getCommand() + " failed");
            throw new AccessDeniedException("Unauthorized connection.");
        }

        String token = authHeader.substring("Bearer ".length());
        if (!jwtUtils.validateJwtToken(token)) {
            System.out.println(accessor.getCommand() + " failed");
            throw new AccessDeniedException("Unauthorized connection.");
        }

        String username = jwtUtils.getUserNameFromJwtToken(token);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        accessor.setUser(authentication);
    }
}