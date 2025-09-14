package com.infnet.spring_security.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

// @Configuration → indica que essa classe fornece beans e configurações para o Spring.
// @EnableWebSecurity → ativa o Spring Security no projeto, permitindo configurar filtros e regras de segurança.
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // jwt.public.key → chave pública para verificar tokens JWT.
    // jwt.private.key → chave privada para gerar tokens JWT.
    @Value("${jwt.public.key}")
    private RSAPublicKey key;
    @Value("${jwt.private.key}")
    private RSAPrivateKey priv;

    // Cria um bean do tipo SecurityFilterChain, que define como as requisições HTTP serão protegidas.

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Desabilita CSRF (Cross-Site Request Forgery).
        // Geralmente feito em APIs REST, porque não há formulários HTML.
        // requestMatchers("/authenticate").permitAll() → endpoint /authenticate é público (não precisa de token).
        // anyRequest().authenticated() → qualquer outro endpoint exige autenticação.
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/authenticate").permitAll()
                                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .oauth2ResourceServer(
                        conf -> conf.jwt(
                                jwt -> jwt.decoder(jwtDecoder())));
        // httpBasic: Habilita HTTP Basic Authentication (usuário e senha via header). Útil para testes rápidos ou autenticação inicial.
        // oauth2ResourceServer() → indica que essa aplicação valida tokens JWT em requests protegidos.
        // .jwt(decoder(jwtDecoder())) → define o decoder que verifica o JWT, usando a chave pública (RSAPublicKey).

        // Retorna o SecurityFilterChain pronto.
        return http.build();
    }

    // Criptografar senhas no banco. Comparar senhas digitadas com a criptografia do banco.
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Cria um bean JwtDecoder, que será usado pelo Spring Security para validar tokens JWT assinados com RSA usando a chave pública.
    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(this.key).build();
    }

    // Cria um bean JwtEncoder, que gera tokens JWT assinados com a chave privada RSA:
    //RSAKey.Builder(this.key).privateKey(this.priv).build() → cria um objeto RSAKey com chave pública e privada.
    //ImmutableJWKSet<>(new JWKSet(jwk)) → prepara o conjunto de chaves (JWK).
    //NimbusJwtEncoder(jwks) → codifica tokens JWT usando essas chaves
    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(this.key).privateKey(this.priv).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }
}
