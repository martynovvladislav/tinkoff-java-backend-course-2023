package edu.project3.logHandlers;

import java.time.LocalDateTime;

public record LogRecord(String remoteAddress, String remoteUser, LocalDateTime timeLocal,
                        String request, String address, String http, int status, int bodyBytesSent,
                        String httpReferrer, String userAgent) {}
