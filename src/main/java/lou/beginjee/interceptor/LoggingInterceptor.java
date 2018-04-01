package lou.beginjee.interceptor;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@LoggingEnabled
public class LoggingInterceptor {

    @Inject
    Logger log;

    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {
        String targetClass = ic.getTarget().getClass().getSimpleName();
        String targetMethod = ic.getMethod().getName();
        String method = String.format("%s.%s()", targetClass, targetMethod);
        Object output = ic.proceed();
        log.info(method + " returns " + output);
        return output;
    }
}
