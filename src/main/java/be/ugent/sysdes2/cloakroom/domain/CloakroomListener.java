package be.ugent.sysdes2.cloakroom.domain;

public interface CloakroomListener {
    public void onItemAddSuccess(CloakroomItem cloakroomItem);
    public void onItemAddFail(CloakroomItem cloakroomItem, CloakroomReason cloakroomReason);
}