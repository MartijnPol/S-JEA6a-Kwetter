package interceptor;

import domain.Log;
import service.LoggingService;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.Arrays;
import java.util.Date;

@Interceptor
@Logging
public class LoggingInterceptor {

    @Inject
    private LoggingService loggingService;

    @AroundInvoke
    public Object log(InvocationContext context) throws Exception {
        String className = context.getMethod().getDeclaringClass().getName();
        String methodName = context.getMethod().getName();
        String paramsName = Arrays.toString(context.getParameters());

        Log log = new Log(className, methodName, paramsName, new Date());
        loggingService.save(log);

        return context.proceed();
    }

}
