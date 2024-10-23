package edu.icet.crm.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuccessResponse {
    private Object data;
    private String message;
    private String status;
}
