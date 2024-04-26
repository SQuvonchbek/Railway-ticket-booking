package uz.pdp.railwayticketproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.railwayticketproject.dto.auth.LoginDto;
import uz.pdp.railwayticketproject.dto.auth.SignUpDto;
import uz.pdp.railwayticketproject.dto.auth.UserUpdateDto;
import uz.pdp.railwayticketproject.entity.User;
import uz.pdp.railwayticketproject.mapstruct.UserMapper;
import uz.pdp.railwayticketproject.repository.UserRepository;
import uz.pdp.railwayticketproject.response.JwtResponse;
import uz.pdp.railwayticketproject.security.jwt.JwtProvider;
import uz.pdp.railwayticketproject.service.AuthenticationService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(SignUpDto signUpDto) {
        if (userRepository.existsByEmailAndEnabledTrue(signUpDto.username())){
            throw new IllegalArgumentException("Username already exists");
        }
        String encode = passwordEncoder.encode(signUpDto.password());
        User user = UserMapper.INSTANCE.dtoToEntity(signUpDto);
        user.setPassword(encode);

        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserUpdateDto updateDto) {
        User user = userRepository.findById(updateDto.id()).orElse(null);

        if (user == null){
            return null;
        }
        if (updateDto.username() != null) {
            user.setUsername(updateDto.username());
        }
        if (updateDto.email() != null) {
            user.setEmail(updateDto.email());
        }
        if (updateDto.password() != null) {
            user.setPassword(updateDto.password());
        }
        return userRepository.save(user);
    }

    @Override
    public JwtResponse loginAccount(LoginDto loginDto) {
        User user = User.builder()
                .username(loginDto.username())
                .password(loginDto.password())
                .build();
        String token = jwtProvider.generateToken(user);

        return new JwtResponse(token);
    }

}
