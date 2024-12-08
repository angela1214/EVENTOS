package eventos.modelo;

public class Usuario {

    private String nombre;
    private String apellidos;
    private String nif;
    private String usermail;
    private String username;
    private String password;
    
	public String getNombre() {
		return nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public String getNif() {
		return nif;
	}
	public String getUsermail() {
		return usermail;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public void setUsermail(String usermail) {
		this.usermail = usermail;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
}
