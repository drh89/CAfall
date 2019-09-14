/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;
import entities.Members;

/**
 *
 * @author Dennis
 */
public class MembersDTO {
    
    private String name;
    private String studentId;
    private String color;
    
    
   public MembersDTO(Members m){
       this.name = m.getName();
       this.studentId = m.getStudentId();
       this.color = m.getColor();
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
   
   
}
