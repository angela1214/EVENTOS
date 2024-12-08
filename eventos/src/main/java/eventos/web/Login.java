package eventos.web;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class Login implements Serializable {
	
	private String user;
    private String password;
    private boolean logged;
    private boolean error;
    
	@Inject
	private FacesContext facesContext;

    public Login() {
    	this.clear();
    }

    public void handleLogin() throws IOException {
        if (user.equals("propietario") && password.equals("aadd")) {
            this.logged = true;
            HttpSession session = (HttpSession) this.facesContext.getExternalContext().getSession(true);
            session.setAttribute("user", user);
            facesContext.getExternalContext().redirect("/espacio/listado.xhtml");
        } else if (user.equals("organizador") && password.equals("aadd")) {
            this.logged = true;
            HttpSession session = (HttpSession) this.facesContext.getExternalContext().getSession(true);
            session.setAttribute("user", user);
            facesContext.getExternalContext().redirect("/evento/listado.xhtml");
        } else if (user.equals("admin") && password.equals("aadd")) {
            this.logged = true;
            HttpSession session = (HttpSession) this.facesContext.getExternalContext().getSession(true);
            session.setAttribute("user", user);
            facesContext.getExternalContext().redirect("/espacio/listado.xhtml");
        } else {
            this.error = true;
        }
    }

    public void logOut() throws IOException {
    	this.clear();
    	facesContext.getExternalContext().redirect("/index.xhtml");
    }
    
    public void clear() {
    	this.user="";
    	this.password = "";
    	this.logged = false;
    	this.error= false;
    }
    
	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public boolean isLogged() {
		return logged;
	}

	public boolean isError() {
		return error;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public void setError(boolean error) {
		this.error = error;
	}
	
	public boolean isPropietario() {
		return user.equals("propietario");
	}

	public boolean isOrganizador() {
		return user.equals("organizador");
	}
	
	public boolean isAdmin() {
		return user.equals("admin");
	}
	
}