package com.example.demo.security;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
public class AuthTokenFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(AuthTokenFilter.class);
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = jwtUtils.parseJwt(request);
            if (jwt != null) {
                if (jwtUtils.validateJwtToken(jwt)) {
                    String json = jwtUtils.getJsonFromJwtToken(jwt);

                    Object obj = new JSONParser(json);
                    CustomUserDetails customUserDetails = new CustomUserDetails();

                    JSONObject jo = (JSONObject) obj;
                    String name = (String) jo.get("name");
                    String id = (String) jo.get("id");
                    String validated = (String) jo.get("validated");

                    customUserDetails.setName(name);
                    customUserDetails.setId(id);
                    customUserDetails.setValidated(validated);

                    if (!name.equals("sonnet") || !id.equals("001") || !validated.equals("true")) {
                        throw new IllegalArgumentException();
                    }
                    SecurityContextHolder.getContext().setAuthentication((Authentication) customUserDetails);
                }
            }
        } catch (Exception e) {
            log.error("unauthorized: $s", e);
        }
        filterChain.doFilter(request, response);
    }
}
