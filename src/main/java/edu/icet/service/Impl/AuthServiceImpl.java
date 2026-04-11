package edu.icet.service.impl;

import edu.icet.model.dto.AuthResponseDTO;
import edu.icet.model.dto.User;
import edu.icet.model.entity.UserEntity;
import edu.icet.repository.UserRepository;
import edu.icet.service.AuthService;
import edu.icet.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public String register(User userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return "Email already exists!";
        }
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(userEntity);
        return "User Registered Successfully!";
    }

    @Override
    public AuthResponseDTO login(User loginRequest) {
        UserEntity user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }


        String token = jwtService.generateToken(user.getEmail(), user.getName(), user.getRole().name());

        return AuthResponseDTO.builder()
                .token(token)
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}