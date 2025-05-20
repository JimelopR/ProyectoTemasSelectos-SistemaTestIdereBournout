/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mac.ProyectoTemasSelectos.utils;
import com.mac.ProyectoTemasSelectos.dtos.RespuestaResultadoDTO;
import com.mac.ProyectoTemasSelectos.dtos.ResultadoSubescalaDTO;
import com.mac.ProyectoTemasSelectos.dtos.ResultadoTestDTO;
import com.mac.ProyectoTemasSelectos.services.TestMostrarService;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;



/**
 *
 * @author jimena
 */

@Service
public class PdfGeneradorUtil {

    @Autowired
    private TestMostrarService testMostrarService; // Servicio que ya devuelve el DTO completo

    public byte[] generarTestResultadoPdfFromHtml(Long idAsignacion) throws IOException {
        ResultadoTestDTO resultado = testMostrarService.obtenerResultado(idAsignacion);

        if (resultado == null) {
            throw new RuntimeException("Resultado del Test no encontrado para ID de asignación: " + idAsignacion);
        }

        // Ya los tienes directamente del DTO:
        List<ResultadoSubescalaDTO> resultadosSubescala = resultado.getResultadosSubescala();

        // Agrupa las respuestas por subescala, ahora usando el objeto RespuestaResultadoDTO
        Map<String, List<RespuestaResultadoDTO>> respuestasPorSubescala;

        if (resultado.getRespuestas() != null) {
            respuestasPorSubescala = resultado.getRespuestas().stream()
                .collect(Collectors.groupingBy(
                    RespuestaResultadoDTO::getSubescalaNombre, // Usa el getter del DTO
                    LinkedHashMap::new, // Para mantener el orden de las subescalas si es importante
                    Collectors.toList() // Colecta objetos RespuestaResultadoDTO directamente en una lista
                ));
        } else {
            respuestasPorSubescala = new LinkedHashMap<>(); // Inicializa vacío si no hay respuestas
        }

        // Genera el HTML con los datos del DTO completo
        String htmlContent = generarHtmlForPdf(
            resultado,
            resultadosSubescala,
            respuestasPorSubescala
        );

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(htmlContent, "/");
            builder.toStream(os);
            builder.run();
        } catch (Exception e) {
            throw new IOException("Error generating PDF from HTML", e);
        }

        return os.toByteArray();
    }

    // Modifica la firma para recibir los DTOs directamente
    private String generarHtmlForPdf(
        ResultadoTestDTO resultado,
        List<ResultadoSubescalaDTO> resultadosSubescala, // Ya es List<ResultadoSubescalaDTO>
        Map<String, List<RespuestaResultadoDTO>> respuestasPorSubescala // Ya es Map<String, List<RespuestaResultadoDTO>>
    ) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>");
        html.append("<html lang=\"es\">");
        html.append("<head>");
        html.append("<meta charset=\"UTF-8\">");
        html.append("<title>Resultado del Test</title>");
        html.append("<style>");
        // Tus estilos CSS aquí. Puedes copiar los de tu frontend si son simples,
        // o definir unos básicos para el PDF.
        html.append("body { font-family: Arial, sans-serif; margin: 20mm; line-height: 1.6; color: #333; }");
        html.append("h2 { color: #2c3e50; text-align: center; margin-bottom: 20px; }");
        html.append("h3 { color: #34495e; border-bottom: 1px solid #ddd; padding-bottom: 5px; margin-top: 25px; }");
        html.append("h4 { color: #2980b9; margin-top: 20px; }");
        html.append("h5 { color: #16a085; margin-top: 15px; }");
        html.append("p { margin-bottom: 8px; }");
        html.append("ul { list-style-type: none; padding-left: 0; }");
        html.append("li { margin-bottom: 5px; background-color: #f8f9fa; padding: 8px 12px; border-radius: 4px; border: 1px solid #e9ecef; }");
        html.append("strong { font-weight: bold; }");
        html.append("hr { border: 0; height: 1px; background-color: #ccc; margin: 30px 0; }");
        html.append(".container { padding: 20px; background-color: #fff; border: 1px solid #eee; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");
        html.append("<div class=\"container\">");

        // Utiliza los getters del ResultadoTestDTO principal
        html.append("<h2>Resultado del Test #" + resultado.getTestAsignadoId() + "</h2>");
        html.append("<h3>" + (resultado.getTitulo() != null ? resultado.getTitulo() : "N/A") + "</h3>");
        html.append("<p><strong>Tipo:</strong> " + (resultado.getTipo() != null ? resultado.getTipo() : "N/A") + "</p>");

        html.append("<p><strong>Evaluado:</strong> " + (resultado.getEvaluadoNombreCompleto() != null ? resultado.getEvaluadoNombreCompleto() : "N/A") + " (" + resultado.getEdadEvaluado() + " años, " + (resultado.getGenero() != null ? resultado.getGenero() : "N/A") + ")</p>");
        html.append("<p><strong>Evaluador:</strong> " + (resultado.getEvaluadorNombreCompleto() != null ? resultado.getEvaluadorNombreCompleto() : "N/A") + "</p>");

        html.append("<p><strong>Trabaja:</strong> " + (resultado.getTrabaja() != null && resultado.getTrabaja() ? "Sí" : "No") + "</p>");
        html.append("<p><strong>Fecha Asignación:</strong> " + (resultado.getFechaAsignacion() != null ? resultado.getFechaAsignacion().format(dateFormatter) : "N/A") + "</p>");
        html.append("<p><strong>Fecha Respuesta:</strong> " + (resultado.getFechaRespuesta() != null ? resultado.getFechaRespuesta().format(dateFormatter) : "N/A") + "</p>");

        html.append("<hr />");

        html.append("<h4>Resultados por Subescala</h4>");
        if (resultadosSubescala != null && !resultadosSubescala.isEmpty()) {
            html.append("<ul>");
            // Itera directamente sobre ResultadoSubescalaDTO
            for (ResultadoSubescalaDTO sub : resultadosSubescala) {
                html.append("<li>");
                html.append("<strong>" + sub.getNombre() + "</strong>: Puntuación " + sub.getPuntuacion() + " - Interpretación: <strong>" + sub.getInterpretacion() + "</strong>");
                html.append("</li>");
            }
            html.append("</ul>");
        } else {
            html.append("<p>No hay resultados de subescala disponibles.</p>");
        }

        html.append("<hr />");

        html.append("<h4>Respuestas por Subescala</h4>");
        if (respuestasPorSubescala != null && !respuestasPorSubescala.isEmpty()) {
            // Itera sobre el mapa, donde la clave es el nombre de la subescala y el valor es List<RespuestaResultadoDTO>
            for (Map.Entry<String, List<RespuestaResultadoDTO>> entry : respuestasPorSubescala.entrySet()) {
                String subescalaNombre = entry.getKey();
                List<RespuestaResultadoDTO> items = entry.getValue();
                html.append("<div style=\"margin-bottom: 20px;\">");
                html.append("<h5>" + subescalaNombre + "</h5>");
                html.append("<ul>");
                // Itera directamente sobre RespuestaResultadoDTO
                for (RespuestaResultadoDTO item : items) {
                    html.append("<li>");
                    html.append("<strong>" + item.getPregunta() + "</strong>: " + item.getTextoOpcion() + " (Valor: " + item.getValor() + ")");
                    html.append("</li>");
                }
                html.append("</ul>");
                html.append("</div>");
            }
        } else {
            html.append("<p>No hay respuestas detalladas disponibles.</p>");
        }

        html.append("</div>");
        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }
}