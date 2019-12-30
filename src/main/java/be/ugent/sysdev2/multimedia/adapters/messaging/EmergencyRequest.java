package be.ugent.sysdev2.multimedia.adapters.messaging;

import be.ugent.sysdev2.multimedia.domain.type;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmergencyRequest {
    private type emergencyType;
    private int severity;
    private String source;

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
