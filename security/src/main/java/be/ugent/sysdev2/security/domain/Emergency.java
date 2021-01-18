package be.ugent.sysdev2.security.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;


public class Emergency {

    private type emergencyType;

    private int severity;
    private String source;

    @Autowired
    public Emergency(type emergencyType,int severity,String source){
        this.emergencyType = emergencyType;
        this.severity = severity;
        this.source = source;
    }
    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setType(type t){
        this.emergencyType = t;
    }

    public type getType(){
        return emergencyType;
    }
}
