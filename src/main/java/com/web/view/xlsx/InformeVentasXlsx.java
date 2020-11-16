package com.web.view.xlsx;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.web.modelo.Boleta;
import com.web.modelo.Busqueda;
import com.web.modelo.Producto;

@Component("informes.xlsx")
public class InformeVentasXlsx extends AbstractXlsxView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		List<Boleta> boletas = (List<Boleta>) model.get("boletas");
		Busqueda busqueda = (Busqueda) model.get("busqueda");
		Integer total = (Integer) model.get("total");
		LinkedHashMap<Producto, Integer> productos = (LinkedHashMap<Producto, Integer>) model.get("productos");
		
		String s = busqueda.getFechaInicio().toString();
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"informe_view.xlsx\""));
		
		Sheet sheet = workbook.createSheet("Boletas");

		
		Row details = sheet.createRow(0);
		Cell cell = details.createCell(0);
		cell.setCellValue("Informe Ventas");
		
		// formato para fecha
		CreationHelper createHelper = workbook.getCreationHelper();  
		CellStyle cellStyle = workbook.createCellStyle();  
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));   
        
        cell = details.createCell(1);
        cell.setCellValue("Desde: ");
		
        cell = details.createCell(2);
        cell.setCellValue(busqueda.getFechaInicio());
        cell.setCellStyle(cellStyle);
        
		details.createCell(3).setCellValue("Hasta: ");
		
		cell = details.createCell(4);
        cell.setCellValue(busqueda.getFechaTermino());
        cell.setCellStyle(cellStyle);
		
		details.createCell(5).setCellValue("Total: ");
		details.createCell(6).setCellValue(total);
		
		Row header = sheet.createRow(1);
		header.createCell(0).setCellValue("Id");
		header.createCell(1).setCellValue("Fecha");
		header.createCell(2).setCellValue("Forma Pago");
		header.createCell(3).setCellValue("Estado");
		header.createCell(4).setCellValue("Monto");
		
		for (int i = 0; i < boletas.size(); i++) {
			Row row = sheet.createRow(i+2);
			row.createCell(0).setCellValue(boletas.get(i).getIdBoleta());
			
			cell = row.createCell(1);
	        cell.setCellValue(boletas.get(i).getFecha());
	        cell.setCellStyle(cellStyle);
			
			row.createCell(2).setCellValue(boletas.get(i).getFormaPago());
			row.createCell(3).setCellValue(boletas.get(i).getEstado());
			row.createCell(4).setCellValue(boletas.get(i).getMonto());
		}

		Sheet hojaProductos = workbook.createSheet("Productos");
		Row headerProductos = hojaProductos.createRow(0);
		headerProductos.createCell(0).setCellValue("Nombre");
		headerProductos.createCell(1).setCellValue("Descripción");
		headerProductos.createCell(2).setCellValue("Precio");
		headerProductos.createCell(3).setCellValue("Cantidad");
		headerProductos.createCell(4).setCellValue("Total Vendido");

		int rowCountProducto = 1;
		for (Map.Entry<Producto, Integer> entry : productos.entrySet()) {
			Row rowProducto = hojaProductos.createRow(rowCountProducto);
			rowProducto.createCell(0).setCellValue(entry.getKey().getNombre());
			rowProducto.createCell(1).setCellValue(entry.getKey().getDescripcion());
			rowProducto.createCell(2).setCellValue(entry.getKey().getPrecio());
			rowProducto.createCell(3).setCellValue(entry.getValue());
			rowProducto.createCell(4).setCellValue(entry.getKey().getPrecio() * entry.getValue());
			rowCountProducto++;
		}

		for (int i=0; i<5; i++) {
			sheet.autoSizeColumn(i);
			hojaProductos.autoSizeColumn(i);
		}
		
	}

}
