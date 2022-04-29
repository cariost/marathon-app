package com.rzerosystems.marathonapp.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Getter
@Component
@RequiredArgsConstructor
public class WebOAuth2ConfigHelper {

  private final WebOAuth2AuthenticationSuccessHandler webOAuth2AuthSuccessHandler;

  private static String getUserId(Map<String, Object> map) {
    HttpServletRequest currentRequest =
        ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    String requestURI = currentRequest.getRequestURI();
    String userId = null;

    if (requestURI.equals(WebOAuth2Config.GOOGLE_LOGIN_URL)) {
      userId = String.valueOf(map.get("sub"));
    } else if (requestURI.equals(WebOAuth2Config.GITHUB_LOGIN_URL)) {
      userId = String.valueOf(map.get("id"));
    }

    if (userId == null) {
      throw new BadCredentialsException("User-Id could not be determined.");
    }

    log.info("Identified User-Id \"{}\"", userId);
    return userId;
  }


  @Component
  private static class WebOAuth2AuthenticationSuccessHandler
      extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException, ServletException {
      setDefaultTargetUrl("/");
      super.onAuthenticationSuccess(request, response, authentication);
    }
  }
}
