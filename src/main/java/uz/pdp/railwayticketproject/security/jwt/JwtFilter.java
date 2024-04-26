package uz.pdp.railwayticketproject.security.jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.railwayticketproject.entity.Role;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(AUTHORIZATION);

        if (header == null || !header.startsWith(BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = header.substring(BEARER.length());


        if (!jwtProvider.validateToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        Claims claims = jwtProvider.parseToken(token);

        List<String> roles = (List<String>) claims.get("roles");

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        claims.getSubject(),
                        null,
                        roles.stream().map(SimpleGrantedAuthority::new).toList()
                )
        );

        filterChain.doFilter(request, response);
    }
}
