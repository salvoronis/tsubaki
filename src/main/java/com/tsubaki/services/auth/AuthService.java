package com.tsubaki.services.auth;

import com.tsubaki.dto.LoginRequestDto;
import com.tsubaki.dto.LoginResponseDto;
import com.tsubaki.dto.RegisterRequestDto;
import com.tsubaki.exceptions.AlreadyTakenException;

public interface AuthService {
    LoginResponseDto singIn(LoginRequestDto loginRequestDto);

    void register(RegisterRequestDto registerRequestDto) throws AlreadyTakenException;
}
