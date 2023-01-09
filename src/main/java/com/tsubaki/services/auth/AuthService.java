package com.tsubaki.services.auth;

import com.tsubaki.dto.auth.LoginRequestDto;
import com.tsubaki.dto.auth.LoginResponseDto;
import com.tsubaki.dto.auth.RegisterRequestDto;
import com.tsubaki.exceptions.AlreadyTakenException;

public interface AuthService {
    LoginResponseDto singIn(LoginRequestDto loginRequestDto);

    void register(RegisterRequestDto registerRequestDto) throws AlreadyTakenException;
}
