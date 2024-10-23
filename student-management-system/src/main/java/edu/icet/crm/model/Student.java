package edu.icet.crm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Student {
    private String id;
    private String stdName;
    private String stdAge;
    private String stdContactNo;
    private String guardianName;
    private String guardianAddress;
    private String guardianContactNo;
    private boolean isActive = true;

    public boolean isValidStudent() {
        return (StringUtils.hasText(this.stdName) &&
                StringUtils.hasText(this.stdAge) &&
                StringUtils.hasText(this.stdContactNo) &&
                StringUtils.hasText(this.guardianName) &&
                StringUtils.hasText(this.guardianAddress)&&
                StringUtils.hasText(this.guardianContactNo));
    }
}
