package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Martijn van der Pol on 15-03-18
 **/

@Entity
public class Log implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String className;

    private String methodName;

    private String usedParameters;

    private Date time;

    public Log() {
    }

    public Log(String className, String methodName, String usedParameters) {
        this.className = className;
        this.methodName = methodName;
        this.usedParameters = usedParameters;
        this.time = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getUsedParameters() {
        return usedParameters;
    }

    public void setUsedParameters(String usedParameters) {
        this.usedParameters = usedParameters;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
