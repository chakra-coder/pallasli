<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*"
	pageEncoding="gb2312"%>
<%@page import="com.zhuozhengsoft.pageoffice.excelwriter.*"%>
<%@page import="java.awt.Color"%>
<%@page import="java.text.*"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
	//����PageOfficeCtrl�ؼ��ķ���ҳ��
	PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
	poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //���б���
	poCtrl1.setCaption("�򵥵ĸ�Excel��ֵ");
	poCtrl1.setSaveFilePage("jsppage.do?url=pageOffice/savefile");
	//poCtrl1.setSaveDataPage("jsppage.do?url=pageOffice/savedata");
	//����Workbook����
	Workbook workBook = new Workbook();
	Sheet sheet=workBook.openSheet("Sheet1");
	workBook.setDisableSheetSelection(true);
	workBook.setDisableSheetRightClick(true);
	sheet.setReadOnly(true);
	//Table table=sheet.openTable("A1:Y500");
	//table.setReadOnly(false);
	//table.setSubmitName("Info");
	poCtrl1.setWriter(workBook);
	
	poCtrl1.setOfficeToolbars(false);
	//���ز˵���
	poCtrl1.setMenubar(false);
	//���ع�����
	poCtrl1.setCustomToolbar(false);
	//��Word�ļ�
//	poCtrl1.webOpen("test.xls", OpenModeType.xlsNormalEdit, "����");
	poCtrl1.setTagId("PageOfficeCtrl2"); //���б���
	// ��ȡ����
	String panelid = request.getParameter("panelid");
	String appkey = request.getParameter("appkey");
	

	String stores = request.getParameter("stores");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>
	<script language='javascript'>
		Ext.onReady(function(){

			var panel = Ext.create('Ext.panel.Panel', {
        	});
			var btn_loadexcel = new Ext.Button({
				text:"����Excel�ļ�",
				handler:function(){
					document.getElementById("PageOfficeCtrl2").ExcelImportDialog(); 
				}
			});
			var btn_loadword = new Ext.Button({
				text:"����word�ļ�",
				handler:function(){
					document.getElementById("PageOfficeCtrl2").WordImportDialog(); 
				}
			});
			var btn_loadppt = new Ext.Button({
				text:"����microsoft�ļ�",
				handler:function(){
					document.getElementById("PageOfficeCtrl2").ShowDialog(1); 
				}
			});
			var btn_drexcel = new Ext.Button({
				text:"����",
				handler:function(){
					document.getElementById("PageOfficeCtrl2").WebSave(); 
					var path = document.getElementById("PageOfficeCtrl2").CustomSaveResult;
					if(path==''){
						alert("�ϴ�ʧ��");
					}else{
						MyAction.doEcho(path,function(result){
							console.log(result);
						});
						//var directsave=eval("< %=submitDirect%>");
						//directsave({excelpath:path,key:key,orders:"xmdm1:order1,"},function(result){
							
						//});
					}
				}
			});
			new Ext.Panel({
				border:false,
				renderTo:"btn-panel2",
                buttonAlign:'center', 
                buttons:[btn_loadexcel,btn_loadword,btn_loadppt,btn_drexcel]
				
			});
		});
	</script>
	<body>
		<div style="width: auto; height: 93%;z-index: 9999">
			<div style="width: 100%; height: 100%; float:left; z-index:9999">
				<po:PageOfficeCtrl id="PageOfficeCtrl2"  ></po:PageOfficeCtrl>
			</div>
		</div>
		<div id="btn-panel2" style="width: 100%; height:7%; z-index:9999">
			
		</div>
	</body>
</html>
