package edu.icet.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponseDTO {
    private String token;
    private String name;
    private String email;
    private String role;
    private Long userId; // ඔයාගේ User DTO එකේ වගේම මෙතනත් Long පාවිච්චි කරන්න
}