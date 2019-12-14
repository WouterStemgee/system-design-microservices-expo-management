package be.ugent.sysdev2.multimedia.adapters.messaging;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmergencyRequest {
    private enum type {
        FIRE,CO,MANUAL,NUCLEAR_ATTACK
    };
    private type emergencyType;

    private int severity;
    private String source;

    public EmergencyRequest(int severity,String source,type emergencyType){
        this.emergencyType=emergencyType;
        this.severity=severity;
        this.source=source;
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

    public String getType(){
        return emergencyType.toString();
    }
}
