package models;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class StudyGroup implements Serializable {
    private Long group_id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String groupName; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int studentsCount; //Значение поля должно быть больше 0, Поле может быть null
    private Long expelledStudents; //Значение поля должно быть больше 0
    private Integer transferredStudents; //Значение поля должно быть больше 0, Поле не может быть null
    private FormOfEducation formOfEducation; //Поле не может быть null
    private Person groupAdmin; //Поле не может быть null
    private String userName;

    public StudyGroup(String groupName, LocalDateTime creationDate, Coordinates coordinates, int studentsCount, Long expelledStudents, Integer transferredStudents, FormOfEducation formOfEducation, String userName, Person groupAdmin){
        this.groupName = groupName;
        this.creationDate = creationDate;
        this.coordinates = coordinates;
        this.studentsCount = studentsCount;
        this.expelledStudents = expelledStudents;
        this.transferredStudents = transferredStudents;
        this.formOfEducation = formOfEducation;
        this.userName = userName;
        this.groupAdmin = groupAdmin;
    }

    public StudyGroup(){}

    @Serial
    private static final long serialVersionUID = 1L;

    public Long getGroupId(){
        return group_id;
    }
    public void setGroupId(Long group_id) {
        this.group_id = group_id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getStudentsCount() {
        return studentsCount;
    }
    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    public Long getExpelledStudents() {
        return expelledStudents;
    }
    public void setExpelledStudents(Long expelledStudents) {
        this.expelledStudents = expelledStudents;
    }

    public Integer getTransferredStudents() {
        return transferredStudents;
    }
    public void setTransferredStudents(Integer transferredStudents) {
        this.transferredStudents = transferredStudents;
    }

    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }
    public void setFormOfEducation(FormOfEducation formOfEducation) {
        this.formOfEducation = formOfEducation;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }
    public void setGroupAdmin(Person groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    @Override
    public String toString(){
        return  "\"group_id: " + group_id +
                ", userName: " + userName +
                ", groupName: " + groupName  +
                ", creationDate: " + creationDate +
                ", coordinates: " + coordinates +
                ", studentsCount: " + studentsCount +
                ", expelledStudents: " + expelledStudents +
                ", transferredStudents: " + transferredStudents +
                ", formOfEducation: " + formOfEducation +
                ", " + groupAdmin + "\"";
    }
}
