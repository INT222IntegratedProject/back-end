package sit.integrated.project.models;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    private final Users users;

    public JwtResponse(String jwttoken, Users users) {
        this.jwttoken = jwttoken;
        this.users = users;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public Users getusers(){
        return this.users;
    }
}