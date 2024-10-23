package edu.icet.crm.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    String error;
    String status;
}
