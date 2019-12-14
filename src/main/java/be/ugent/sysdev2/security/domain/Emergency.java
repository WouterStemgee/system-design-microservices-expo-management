package be.ugent.sysdev2.security.domain;

public class Emergency {

    private type emergencyType = type.FIRE;

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

    public type getType(){
        return emergencyType;
    }
}
