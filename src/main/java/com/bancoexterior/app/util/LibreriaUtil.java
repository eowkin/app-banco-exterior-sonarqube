package com.bancoexterior.app.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import com.bancoexterior.app.inicio.model.Menu;




@Component
public class LibreriaUtil {
	
	private static final Logger LOGGER = LogManager.getLogger(LibreriaUtil.class);
	                                              //                "#,##0.00"
    public static final String NUMEROFORMAT                       = "#,##0.00";
	
    public static final char COMA                                 = ',';
    
    public static final char PUNTO                                = '.';
    
    private static final String STRDATEFORMET = "yyyy-MM-dd";
 
    public static final String CHANNEL = "0005";
	
	public String obtenerIdSesion() {
		LocalDateTime ahora = LocalDateTime.now();
		String valorAno = "";
		valorAno = ahora.getYear()+"";
		
		
		String valorMes = getValorMes(ahora);
		
		String valorDia = getValorDia(ahora);
		
		
		String valorHora = "";
		if(ahora.getHour() < 10) {
			valorHora = "0"+ahora.getHour();
		}else {
			valorHora = ""+ahora.getHour();
		}
		
		
		String valorMin = "";
		if(ahora.getMinute() < 10) {
			valorMin = "0"+ahora.getMinute();
		}else {
			valorMin = ""+ahora.getMinute();
		}
		
		
		String valorSeg = "";
		if(ahora.getSecond() < 10) {
			valorSeg = "0"+ahora.getSecond();
		}else {
			valorSeg = ""+ahora.getSecond();
		}
		
		
	
		return valorAno+valorMes+valorDia+valorHora+valorMin+valorSeg;
	}
	
	
	public String obtenerFechaYYYYMMDD() {
		LocalDateTime ahora = LocalDateTime.now();
		String valorAno = "";
		valorAno = ahora.getYear()+"";
		
		String valorMes = getValorMes(ahora);
		
		String valorDia = getValorDia(ahora);
	
		return valorAno+"-"+valorMes+"-"+valorDia;
	}
	public String obtenerIdSesionCce() {
		LocalDateTime ahora = LocalDateTime.now();
		String valorAno = "";
		valorAno = ahora.getYear()+"";
	
		String valorMes = getValorMes(ahora);
		
		String valorDia = getValorDia(ahora);
		
		return valorDia+valorMes+valorAno;
	}
	
	public String obtenerFechaHoy() {
		LocalDateTime ahora = LocalDateTime.now();
		String valorAno = "";
		valorAno = ahora.getYear()+"";
		
		String valorMes = getValorMes(ahora);
	
		String valorDia = getValorDia(ahora);
	
		return valorDia+"/"+valorMes+"/"+valorAno;
	}

	
	public String getValorMes(LocalDateTime ahora) {
		String valorMes = "";
		if(ahora.getMonthValue() < 10) {
			valorMes = "0"+ahora.getMonthValue();
		}else {
			valorMes = ""+ahora.getMonthValue();
		}
		
		return valorMes;
	}
	
	
	public String getValorDia(LocalDateTime ahora) {
		String valorDia = "";
		if(ahora.getDayOfMonth() < 10) {
			valorDia = "0"+ahora.getDayOfMonth();
		}else {
			valorDia = ""+ahora.getDayOfMonth();
		}
		
		return valorDia;
	}
	
	/**
	* Convierte un tipo de dato String a BigDecimal.
	* Ideal para obtener el dato de un JTextField u otro componente y realizar las operaciones
	* matem??ticas sobre ese dato.
	* @param num
	* @return BigDecimal
	*/
	public  BigDecimal stringToBigDecimal(String num){
		//se inicializa en 0
		BigDecimal money = BigDecimal.ZERO;
		//sino esta vacio entonces
		if(!num.isEmpty()){
			/**
			* primero elimina los puntos y luego remplaza las comas en puntos.
			*/
			String formatoValido = num.replace(".", "").replace(",", ".");
			money = new BigDecimal(formatoValido);
		}//if
		return money;
	}//metodo
	/**
	* Convierte un tipo de dato BigDecimal a String.
	* Ideal para mostrar el dato BigDecimal en un JTextField u otro componente de texto.
	* @param big
	* @return String
	*/
	public String bigDecimalToString(BigDecimal big){
		double datoDoubleD = 0;
		//se verifica que sean correctos los argumentos recibidos
		if(big != null)
			datoDoubleD = big.doubleValue();
		/**
		* Los # indican valores no obligatorios
		* Los 0 indican que si no hay valor se pondr?? un cero
		*/
		NumberFormat formatter = new DecimalFormat(NUMEROFORMAT);
		return formatter.format(datoDoubleD);
	}//metodo
	
	public  String formatNumber(BigDecimal numero) {
		
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator(COMA);
        decimalFormatSymbols.setGroupingSeparator(PUNTO);
        DecimalFormat df = new DecimalFormat(NUMEROFORMAT, decimalFormatSymbols);
        
         return df.format(numero);
        
    }
	
	   
    public String fechayhora() {
        DateFormat fecha = new SimpleDateFormat(STRDATEFORMET);
        DateFormat hora = new SimpleDateFormat("hh:mm:ss");
        String fechacompleta;   
        fechacompleta= fecha.format(new Date()) + "T" + hora.format(new Date()) ;
        return fechacompleta;
       
    } 
    
    public String getChannel() {
        return CHANNEL;
    }
	
    public String getSchmeNm(String nroIdEmisor) {
    	char valor = nroIdEmisor.charAt(0);
    
    	if(valor == 'J' || valor == 'G' || valor == 'C' || valor == 'R') {
    		return "SRIF";
    	}else {
    		if(valor == 'V' || valor == 'E') {
    			return "SCID";
    		}else {
    			if(valor == 'P') {
    				return "SCID";
    			}else {
    				return "NO-ASIGNADO";
    			}
    		}
    	}
    }
    
    public String getProducto(String codProducto) {
    	return codProducto.substring(1,codProducto.length());
    }
    
    public String getEndToEndId(String bancoEmisor, String referencia) {
    	
    	return bancoEmisor+obtenerIdSesion()+referencia;
    }
    
    
    
    
    
    
    
    
    
    public boolean isFechaValidaDesdeHasta(String fechaDesde, String fechaHasta) {
		
		SimpleDateFormat formato = new SimpleDateFormat(STRDATEFORMET);
		
        try {
        	
        	
        	Date fechaDate1 = formato.parse(fechaDesde);
        	Date fechaDate2 = formato.parse(fechaHasta);
        	
        	if ( fechaDate2.before(fechaDate1) ){
        		LOGGER.info("La fechaHasta es menor que la fechaDesde");
        		return false;
        	}else{
        	     if ( fechaDate1.before(fechaDate2) ){
        	    	 LOGGER.info("La fechaDesde es menor que la fechaHasta");
        	    	 return true;
        	     }else{
        	    	 LOGGER.info("La fechaDesde es igual que la fechaHasta");
        	    	 return true;
        	     } 
        	}
        } 
        catch (ParseException ex) 
        {
        	LOGGER.error(ex.getMessage());
        }
        
        return false;
	}
    
    public BigDecimal montoSerch(BigDecimal numero) {
		if(numero != null) {
			return stringToBigDecimal(formatNumber(numero));
		}
		return new BigDecimal("0.00");
	}
	
	
	public boolean isPermisoMenu(HttpSession httpSession, int valor) {
		List<Menu> listaMenu = (List<Menu>)httpSession.getAttribute("listaMenu");
		List<Integer> listaMenuInt = new ArrayList<>();
		boolean permiso = false;
		if(listaMenu != null) {
			for (Menu menu : listaMenu) {
				listaMenuInt.add(menu.getIdMenu());
			}
			
			for (Integer intMenu : listaMenuInt) {
				if(intMenu == valor)
					permiso = true;
			}
			return permiso;
		}else {
			return permiso;
		}
		
	}
}
