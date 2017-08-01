package com.weststein.infrastructure.logger;

import com.weststein.repository.AuditRecord;
import com.weststein.repository.AuditRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

@Slf4j
@Component
public class WeststeinLoggingFilter extends AbstractRequestLoggingFilter {

    private AuditRecordRepository auditRecordRepository;

    public WeststeinLoggingFilter(AuditRecordRepository auditRecordRepository) {
        this.auditRecordRepository = auditRecordRepository;
    }

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        return true;
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        log.info(message);
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        log.info(message);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        boolean isFirstRequest = !isAsyncDispatch(request);
        HttpServletRequest requestToUse = request;

        if (isIncludePayload() && isFirstRequest && !(request instanceof ContentCachingRequestWrapper)) {
            requestToUse = new ContentCachingRequestWrapper(request, getMaxPayloadLength());
        }

        boolean shouldLog = shouldLog(requestToUse);
        try {
            filterChain.doFilter(requestToUse, response);
        } finally {
            if (shouldLog && !isAsyncStarted(requestToUse)) {
                afterRequest(requestToUse, createMessage(requestToUse, "after request", "" + response.getStatus()));
            }
        }
    }

    @Override
    protected String createMessage(HttpServletRequest request, String prefix, String status) {
        AuditRecord auditRecord = new AuditRecord();
        auditRecord.setMethod(request.getMethod());
        auditRecord.setCreated(LocalDateTime.now());

        StringBuilder msg = new StringBuilder();
        msg.append(prefix);
        msg.append("uri=").append(request.getRequestURI());
        auditRecord.setUrl(request.getRequestURI());
        auditRecord.setResponseStatus(status);

        if (isIncludeQueryString()) {
            String queryString = request.getQueryString();
            if (queryString != null) {
                queryString = queryString.replaceAll("password=([A-Za-z0-9]{0,20})", "password=*******");
                msg.append('?').append(queryString);
                auditRecord.setUrl(request.getRequestURI() + "?" + queryString);
            }
        }

        if (isIncludeClientInfo()) {
            String client = request.getRemoteAddr();
            if (StringUtils.hasLength(client)) {
                msg.append(";client=").append(client);
            }
            HttpSession session = request.getSession(false);
            if (session != null) {
                msg.append(";session=").append(session.getId());
            }
            String user = request.getRemoteUser();
            if (user != null) {
                auditRecord.setUserName(user);
                msg.append(";user=").append(user);
            }
        }

        if (isIncludeHeaders()) {
            msg.append(";headers=").append(new ServletServerHttpRequest(request).getHeaders());
        }

        if (isIncludePayload()) {
            ContentCachingRequestWrapper wrapper =
                    WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
            if (wrapper != null) {
                byte[] buf = wrapper.getContentAsByteArray();
                if (buf.length > 0) {
                    int length = Math.min(buf.length, getMaxPayloadLength());
                    String payload;
                    try {
                        payload = new String(buf, 0, length, wrapper.getCharacterEncoding());
                    } catch (UnsupportedEncodingException ex) {
                        payload = "[unknown]";
                    }
                    msg.append(";payload=").append(payload);
                    auditRecord.setBody(payload);
                }
            }
        }
        if (auditRecord.getUserName() != null) {
            auditRecordRepository.save(auditRecord);
        }
        msg.append(";status:").append(status);
        return msg.toString();
    }
}
