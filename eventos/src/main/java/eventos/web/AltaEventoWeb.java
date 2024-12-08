package eventos.web;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import eventos.modelo.Categoria;
import eventos.dto.EspacioFisicoDTO;
import eventos.servicio.IServicioEspaciosFisicos;
import eventos.servicio.IServicioEventos;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class AltaEventoWeb implements Serializable {
	
    private String nombre;
    private String descripcion;
    private String organizador;
    private Integer plazas = 0;
    private Date fecha_inicio;
    private Date fecha_fin;
	private String id_evento;
	
	private boolean creado;
    
    private List<EspacioFisicoDTO> espacios_fisicos;
    private List<Categoria> categorias;
    
    private Categoria hideNoSelectOption_Categoria;
    private String hideNoSelectOption_Espacio;

    private IServicioEventos servicioEventos;
    private IServicioEspaciosFisicos servicioEspaciosFisicos;	
    
	@Inject
	private FacesContext facesContext;

    public AltaEventoWeb() throws RepositorioException {
        this.servicioEventos = FactoriaServicios.getServicio(IServicioEventos.class);
        this.servicioEspaciosFisicos = FactoriaServicios.getServicio(IServicioEspaciosFisicos.class);
        this.espacios_fisicos = this.servicioEspaciosFisicos.getAllActives();
        this.categorias = Arrays.asList(Categoria.values());
    }
    
    public void altaEvento() {
    	
        // comprobación de campos
        try {
        	
        	Map<String,Object> sesion = this.facesContext.getExternalContext().getSessionMap();
        	
            if (sesion != null) {
                this.organizador = (String) sesion.get("user");
            }
            
        	LocalDateTime fechaInicio = null;
        	LocalDateTime fechaFin = null;
        	
        	if(fecha_inicio != null && fecha_fin != null) {
        		fechaInicio = LocalDateTime.ofInstant(fecha_inicio.toInstant(), ZoneId.systemDefault());
                fechaFin =  LocalDateTime.ofInstant(fecha_fin.toInstant(), ZoneId.systemDefault());
        	}
        		
			this.id_evento = this.servicioEventos.alta(this.nombre, this.descripcion,
			this.organizador,this.plazas, this.hideNoSelectOption_Categoria, fechaInicio,
			fechaFin, this.hideNoSelectOption_Espacio);
			
			this.creado = true;
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Evento [ID="+this.id_evento+"]", "Ha sido dado de alta correctamente."));
			this.clearFields();
			
        } catch (Exception e) {
        	this.creado = false;
        	String title="";
        	String message = e.getMessage();
        	String[] partes = e.getMessage().split(":", 2);
            if (partes.length > 0) {
            	title =  partes[0].trim();
                message = partes[1].trim();
            }      
            
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, title, message));
        }
        
    }
	
    public void clearFields() throws RepositorioException {
    	this.nombre = null;
    	this.descripcion = null;
        this.plazas = 0;
    	this.espacios_fisicos = this.servicioEspaciosFisicos.getAllActives();
        this.categorias = Arrays.asList(Categoria.values());
    }

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getOrganizador() {
		return organizador;
	}

	public Integer getPlazas() {
		return plazas;
	}

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public String getId_evento() {
		return id_evento;
	}

	public boolean isCreado() {
		return creado;
	}

	public List<EspacioFisicoDTO> getEspacios_fisicos() {
		return espacios_fisicos;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public Categoria getHideNoSelectOption_Categoria() {
		return hideNoSelectOption_Categoria;
	}

	public String getHideNoSelectOption_Espacio() {
		return hideNoSelectOption_Espacio;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setOrganizador(String organizador) {
		this.organizador = organizador;
	}

	public void setPlazas(Integer plazas) {
		this.plazas = plazas;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public void setId_evento(String id_evento) {
		this.id_evento = id_evento;
	}

	public void setCreado(boolean creado) {
		this.creado = creado;
	}

	public void setEspacios_fisicos(List<EspacioFisicoDTO> espacios_fisicos) {
		this.espacios_fisicos = espacios_fisicos;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public void setHideNoSelectOption_Categoria(Categoria hideNoSelectOption_Categoria) {
		this.hideNoSelectOption_Categoria = hideNoSelectOption_Categoria;
	}

	public void setHideNoSelectOption_Espacio(String hideNoSelectOption_Espacio) {
		this.hideNoSelectOption_Espacio = hideNoSelectOption_Espacio;
	}    
	
}