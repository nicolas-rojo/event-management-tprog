package com.miseventos.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import cliente.ws.eventos.ControladorEventoWSService;
import cliente.ws.eventos.IControladorEventoWS;
import cliente.ws.usuarios.ControladorUsuarioWSService;
import cliente.ws.usuarios.IControladorUsuarioWS;
import cliente.ws.eventos.DataEdicion;
import cliente.ws.usuarios.DataUsuario;
import cliente.ws.usuarios.DataOrganizador;
import cliente.ws.usuarios.DataDetalleRegistro;
import cliente.ws.eventos.DataPatrocinioCompleto;
import cliente.ws.eventos.DataTRegistro;
import cliente.ws.usuarios.ParEdicionRegistro;
import com.miseventos.utils.fabricaWS;
import java.util.ArrayList;


@WebServlet("/consultaEdicion")
public class ConsultaEdicion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IControladorEventoWS IEV_WS;
	private IControladorUsuarioWS ICU_WS;

	@Override
	public void init() throws ServletException {
		IEV_WS = fabricaWS.getControladorEventoWS();
		ICU_WS = fabricaWS.getControladorUsuarioWS();

        System.out.println("ConsultaEventoWS");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession(false);

		request.setAttribute("registrado", false);

		String eventoSeleccionado = request.getParameter("evento");
		String edicionSeleccionada = request.getParameter("edicion");

		DataUsuario dataU = null;
		String tipo = null;
		String nickname = null;
		String email = null;

		if (session != null) {
			dataU = (DataUsuario) session.getAttribute("datosUsr");
			tipo = (String) session.getAttribute("tipoUsr");
			if (dataU != null) {
				nickname = dataU.getNickname();
				email = dataU.getEmail();
				//ICU.setAsistencia(edicionSeleccionada, nickname); //Hardcodeo que si asisito aparezca para descargar el pdf
			}
			
		}

		if (eventoSeleccionado == null || edicionSeleccionada == null) {
			request.setAttribute("error", "Faltan parámetros de evento o edición");
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
			return;
		}

		String organizador = IEV_WS.obtenerOrganizadorEdicion(eventoSeleccionado, edicionSeleccionada);
		DataEdicion dataEd = IEV_WS.obtenerEdicionEvento(eventoSeleccionado, edicionSeleccionada);

		if (dataEd == null) {
			request.setAttribute("error", "No se encontró la edición del evento");
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
			return;
		}

		DataOrganizador dataOrg = null;
		try {
			dataOrg = ICU_WS.getOrganizador(organizador);
		} catch (Exception e) {
			e.printStackTrace();
	        request.setAttribute("error", "No se pudieron cargar los datos del organizador");
	        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
	        return;
		}

		List<DataPatrocinioCompleto> dataPatrocinios = IEV_WS.obtenerPatrociniosEdicion(eventoSeleccionado, edicionSeleccionada).getItem();
		List<String> TRegistros = IEV_WS.obtenerTipoRegistrosEdicion(eventoSeleccionado, edicionSeleccionada).getItem();

		List<DataTRegistro> dataTRegistros = new ArrayList<>();
		if (TRegistros != null) {
			for (String TRegistro : TRegistros) {
				DataTRegistro data = IEV_WS.getDataTRegistro(eventoSeleccionado, edicionSeleccionada, TRegistro);
				if (data != null)
					dataTRegistros.add(data);
			}
		}

		if (tipo != null && dataU != null) {
			if ("asistente".equals(tipo)) {
				ParEdicionRegistro registro = ICU_WS.estaRegistrado(nickname, edicionSeleccionada);
				System.out.println(nickname + " " + edicionSeleccionada);
				
				// ✅ Validar que el registro tenga contenido válido
				if (registro != null && registro.getNombreEdicion() != null 
					&& !registro.getNombreEdicion().isEmpty()) {
					
					System.out.println("ESTOY REGISTRADO");
					
					// ✅ Intentar obtener detalles del registro
					try {
						DataDetalleRegistro detalleReg = ICU_WS.getDetallesRegistro(nickname, registro);
						request.setAttribute("registrado", true);
						request.setAttribute("dataRegistro", registro);
						request.setAttribute("detalleRegistro", detalleReg);
					} catch (Exception e) {
						System.err.println("Error al obtener detalles del registro: " + e.getMessage());
						e.printStackTrace();
						// Marcar como registrado pero sin detalles
						request.setAttribute("registrado", true);
						request.setAttribute("dataRegistro", registro);
					}
				} else {
					System.out.println("NO ESTOY REGISTRADO");
					request.setAttribute("registrado", false);
				}
			} else if ("organizador".equals(tipo)) {
				if (dataU.getNickname().equals(organizador)) {
					request.setAttribute("organizaEdicion", true);
					List<String> dataRegistros = ICU_WS.getUsuariosRegistrados(edicionSeleccionada).getItem();
					request.setAttribute("dataRegistros", dataRegistros);
				}
			}
		}
		request.setAttribute("dataEdicion", dataEd);
		request.setAttribute("dataOrganizador", dataOrg);
		request.setAttribute("dataTRegistros", dataTRegistros);
		request.setAttribute("dataPatrocinios", dataPatrocinios);
		request.getRequestDispatcher("/WEB-INF/consultaEdicion.jsp").forward(request, response);
	}
}