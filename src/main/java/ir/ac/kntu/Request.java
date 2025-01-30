package ir.ac.kntu;

public class Request {
    private AuthenticationType authentication;
    private String answer = new String();

    Request() {

    }

    public Request(AuthenticationType authentication, String answer) {
        setAnswer(answer);
        setAuthentication(authentication);
    }

    public AuthenticationType getAuthentication() {
        return this.authentication;
    }

    public void setAuthentication(AuthenticationType authentication) {
        this.authentication = authentication;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "{" +
                " authentication='" + getAuthentication() + "'" +
                ", answer='" + getAnswer() + "'" +
                "}";
    }

}
