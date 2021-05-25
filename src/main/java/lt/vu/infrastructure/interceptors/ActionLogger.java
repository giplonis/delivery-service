package lt.vu.infrastructure.interceptors;

import lt.vu.infrastructure.logger.Logger;
import lt.vu.infrastructure.security.LoggedInUser;
import lt.vu.persistence.entities.User;
import lt.vu.persistence.orm.entities.LogEntry;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.io.Serializable;
import java.util.Date;

@Interceptor
@LoggedAction
public class ActionLogger implements Serializable {

    @Inject
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    @LoggedInUser
    @Inject
    private User user;

    @AroundInvoke
    @Transactional
    public Object logMethodInvocation(InvocationContext context) throws Exception {
        this.storeLogEntry(this.createLogEntryFromContext(context));

        return context.proceed();
    }

    private LogEntry createLogEntryFromContext(InvocationContext context) {
        LogEntry logEntry = new LogEntry();

        logEntry.setCreatedAt(new Date());
        if (this.user != null) {
            logEntry.setUserId(this.user.getId());
        }
        logEntry.setClassName(context.getMethod().getDeclaringClass().getName());
        logEntry.setMethodName(context.getMethod().getName());
        String endpoint = this.getEndpoint(context);
        if (endpoint != null) {
            logEntry.setEndpoint(endpoint);
        }
        String httpMethod = this.getHttpMethod(context);
        if (httpMethod != null) {
            logEntry.setHttpMethod(httpMethod);
        }

        return logEntry;
    }

    private String getEndpoint(InvocationContext context) {
        if (!context.getMethod().getDeclaringClass().isAnnotationPresent(Path.class)) {
            return null;
        }

        String endpoint = "api/v1" + context.getMethod().getDeclaringClass().getAnnotation(Path.class).value();
        if (context.getMethod().isAnnotationPresent(Path.class)) {
            endpoint += context.getMethod().getAnnotation(Path.class).value();
            if (endpoint.endsWith("/")) {
                endpoint = endpoint.substring(0, endpoint.length() - 1);
            }
        }

        return endpoint;
    }

    private String getHttpMethod(InvocationContext context) {
        if (context.getMethod().isAnnotationPresent(GET.class)) {
            return HttpMethod.GET;
        }
        if (context.getMethod().isAnnotationPresent(PUT.class)) {
            return HttpMethod.PUT;
        }
        if (context.getMethod().isAnnotationPresent(POST.class)) {
            return HttpMethod.POST;
        }
        if (context.getMethod().isAnnotationPresent(DELETE.class)) {
            return HttpMethod.DELETE;
        }
        if (context.getMethod().isAnnotationPresent(PATCH.class)) {
            return HttpMethod.PATCH;
        }
        if (context.getMethod().isAnnotationPresent(HEAD.class)) {
            return HttpMethod.HEAD;
        }
        if (context.getMethod().isAnnotationPresent(OPTIONS.class)) {
            return HttpMethod.OPTIONS;
        }

        return null;
    }

    private void storeLogEntry(LogEntry logEntry) {
        try {
            this.entityManager.persist(logEntry);
        } catch (Exception e) {
            this.logger.error(e);
        }
    }
}
