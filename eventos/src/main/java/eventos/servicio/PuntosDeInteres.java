package eventos.servicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import eventos.modelo.PuntoDeInteres;

public class PuntosDeInteres implements IPuntosDeInteres {
	
	private String uri_api = "http://api.geonames.org/findNearbyWikipedia?&username=aadd&lang=ES";
	private List<PuntoDeInteres> puntos = null;

	@Override
	public List<PuntoDeInteres> obtenerPuntosDeInteres(double latitud, double longitud) throws Exception {
		
		if (latitud < -90.0 || latitud > 90.0) 
			throw new IllegalArgumentException("latitud: no debe ser menor a -90º ni mayor a 90º");

		if (longitud < -180.0 || longitud > 180.0) 
			throw new IllegalArgumentException("longitud: no debe ser menor a -180º ni mayor a 180º");
		
		this.uri_api += "&lat=" + latitud + "&lng=" + longitud;
		
		 try {
	           
			// Consulta a la API geonames
			URI uri = new URI(this.uri_api);
			URL url = uri.toURL();
			

			try {
				
				InputStream inputStream = url.openStream();
			    this.readXML(inputStream);
			    
			} catch (Exception e) {
			    e.printStackTrace();
			}
            
            // Devolver lista
            return null;

        } catch (Exception e) {
            e.printStackTrace();
        }
		 
		return null;
		
	}
	
	private void readXML(InputStream xml) throws ParserConfigurationException, SAXException, IOException {
		
		List<PuntoDeInteres> puntosObtenidos = new ArrayList<>();
		
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder analizador = factoria.newDocumentBuilder();
	
			
			Document documento = analizador.parse(xml);
			
			Element raiz = documento.getDocumentElement();
	
			NodeList nodosElementos = raiz.getElementsByTagName("entry");
			
			for (int i = 0; i < nodosElementos.getLength(); i++) {
		        Node nodoElemento = nodosElementos.item(i);
		        
		        if (nodoElemento.getNodeType() == Node.ELEMENT_NODE) {

		            Element elemento = (Element) nodoElemento;
		            
		            NodeList titulos = elemento.getElementsByTagName("title");
		            String titulo = titulos.item(0).getTextContent();
		            
		            NodeList descripciones = elemento.getElementsByTagName("summary");
		            String descripcion = descripciones.item(0).getTextContent();

		            NodeList distancias = elemento.getElementsByTagName("distance");
		            String distancia = distancias.item(0).getTextContent();
		            
		            NodeList urls = elemento.getElementsByTagName("wikipediaUrl");
		            String url = urls.item(0).getTextContent();
		            
		            PuntoDeInteres p = new PuntoDeInteres(titulo, descripcion, Double.parseDouble(distancia), url);
		            System.out.println(p.toString());
		            puntosObtenidos.add(p);
		            
		        }
		    }
		
	}
	
}
