package io.catalyte.training.teamname.security;

public interface AuthService {

  AuthToken login(Credential credentials);
}
