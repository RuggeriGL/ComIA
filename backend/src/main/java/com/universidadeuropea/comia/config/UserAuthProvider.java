package com.universidadeuropea.comia.config;

import java.security.AlgorithmConstraints;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.universidadeuropea.comia.dto.UserDto;
import com.universidadeuropea.comia.entity.Usuario;
import com.universidadeuropea.comia.exceptions.AppException;
import com.universidadeuropea.comia.mappers.UserMapper;
import com.universidadeuropea.comia.repository.UsuarioRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserAuthProvider {

    private final UsuarioRepository usuarioRepository;
    private final UserMapper userMapper;

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(UserDto userDto){
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3_600_000);
        //TESTS - 401 error
        //Date validity = new Date(now.getTime() + 5 * 1000); // 5 segundos * 1000 ms/segundo


        return JWT.create()
            .withIssuer(userDto.getEmail())
            .withIssuedAt(now)
            .withExpiresAt(validity)
            .withClaim("fistName", userDto.getFirstName())
            .sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication validateToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm).build();
        
        DecodedJWT decoded = verifier.verify(token);

        UserDto userDto = UserDto.builder()
                .email(decoded.getIssuer())
                .firstName(decoded.getClaim("firstName").asString())
                .build();
        
        return new UsernamePasswordAuthenticationToken(userDto, null, Collections.emptyList());
    }

    public Authentication validateTokenStrongly(String token){
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm).build();
        
        DecodedJWT decoded = verifier.verify(token);

        Usuario usuario = usuarioRepository.findByEmail(decoded.getIssuer())
                .orElseThrow(() -> new AppException("Usuario desconocido", HttpStatus.NOT_FOUND));

                return new UsernamePasswordAuthenticationToken(userMapper.toUserDto(usuario), null, Collections.emptyList());
    }
}