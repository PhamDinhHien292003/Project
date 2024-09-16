package com.example.vgc_project.jwt;

import com.example.vgc_project.DTO.RoleDTO;
import com.example.vgc_project.DTO.UserDTO;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;


@Component
public class UserJwt {

    @Value("${encodeKey.key}")
    String encodeKey;


    Logger logger = LoggerFactory.getLogger(this.getClass());
    public String generateToken(UserDTO data){
        String jws= "";
        if(data == null ){
            return null;
        }
        try{
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(encodeKey));
             jws = Jwts.builder().subject(data.getUsername()).claim("id",data.getId()).claim("scope", roleToScope(data.getRoles())).signWith(key).compact();


        }catch
            (Exception e){
            logger.error(e.getMessage());
        }


        Payload payload = new Payload("jws");

        JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256),payload) ;

        try {
            jwsObject.sign(new MACSigner(encodeKey));
        } catch (JOSEException e) {
            logger.error(e.getMessage());
            return null;
        }
        return jws ;
    }


    public boolean isTrueToken(String token){
        try{
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(encodeKey));
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);


            return true;
        }
        catch(Exception e){
            logger.warn("Invalid token");
            return false ;
        }
    }

    public Claims getAllClaimsFromToken(String token) {
        try {
            // Decode the base64-encoded key
            SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(encodeKey));

            // Parse the JWT token and return claims
            return Jwts.parser() // Use parserBuilder instead of parser
                    .setSigningKey(key) // Set the signing key
                    .build() // Build the parser
                    .parseClaimsJws(token) // Parse the token
                    .getBody(); // Get the claims
        } catch (SignatureException e) {
            logger.warn("Invalid JWT signature.");
            return null;
        } catch (Exception e) {
            logger.warn("Some error occurred when parsing the token: " + e.getMessage());
            return null;
        }
    }


    public String roleToScope(List<RoleDTO> lst ){
        StringJoiner joiner = new StringJoiner(" ");
        if(!CollectionUtils.isEmpty(lst)) lst.forEach(s -> joiner.add(s.getRole()));
        return joiner.toString();
    }
}
