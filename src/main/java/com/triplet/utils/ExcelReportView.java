package com.triplet.utils;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.triplet.model.User;

public class ExcelReportView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		response.setHeader("Content-Disposition", "attachment;filename=\"users.xlsx\"");

		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) model.get("users");
		Sheet sheet = workbook.createSheet("Users Data");
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("id");
		header.createCell(1).setCellValue("fullname");
		header.createCell(2).setCellValue("username");
		header.createCell(3).setCellValue("email");
		header.createCell(4).setCellValue("avatar");
		header.createCell(5).setCellValue("phone");
		header.createCell(6).setCellValue("address");
		header.createCell(7).setCellValue("password");
		header.createCell(8).setCellValue("create_time");
		header.createCell(9).setCellValue("update_time");
		header.createCell(10).setCellValue("delete_time");

		DateTimeUtils dateTimeUtils = new DateTimeUtils();
		int rowNum = 1;
		for (User user : users) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(user.getId());
			row.createCell(1).setCellValue(user.getFullname());
			row.createCell(2).setCellValue(user.getUsername());
			row.createCell(3).setCellValue(user.getEmail());
			row.createCell(4).setCellValue(user.getAvatar());
			row.createCell(5).setCellValue(user.getPhone());
			row.createCell(6).setCellValue(user.getAddress());
			row.createCell(7).setCellValue(user.getPassword());
			row.createCell(8).setCellValue(dateTimeUtils.convertDateTime(user.getCreate_time()));
			row.createCell(9).setCellValue(dateTimeUtils.convertDateTime(user.getUpdate_time()));
			row.createCell(10).setCellValue(dateTimeUtils.convertDateTime(user.getDelete_time()));
		}
	}
}