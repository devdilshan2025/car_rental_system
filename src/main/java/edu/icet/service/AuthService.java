package edu.icet.service;

import edu.icet.model.dto.AuthResponseDTO;
import edu.icet.model.dto.User;

public interface AuthService {
    String register(User userDTO);
    AuthResponseDTO login(User loginRequest);
}
