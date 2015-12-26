<%@page import="com.pallasli.constant.SystemConstant"%>
<%@page import="com.pallasli.utils.ZipUtils"%>
<%@page import="com.pallasli.utils.FileUtils"%>
<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*"
	pageEncoding="gb2312"%>
<%@page import="com.zhuozhengsoft.pageoffice.excelwriter.*"%>
<%@page import="java.awt.Color"%>
<%@page import="java.text.*"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%
	String panelId = request.getParameter("panelId");
	String zipPath=SystemConstant.WEB_ROOT+ "download/test.zip"; 
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

	Pallasli.study.api.REMOTING_API.enableBuffer = 0;  
	Ext.Direct.addProvider(Pallasli.study.api.REMOTING_API);  
    Ext.Loader.setPath({
        'Ext.ux.desktop': 'scripts/desktop/js',
        'MyDesktop': 'scripts/desktop/mydesktop'
    });

    Ext.require('MyDesktop.Notepad');
		Ext.onReady(function(){
			
			var panel=Ext.getCmp("<%=panelId%>");
			  Ext.define('Task', { 
				  autoLoad:true,
			        extend: 'Ext.data.Model',  
			        fields: [  
			            {name: 'id',  type: 'number'},  
			            {name: 'text',  type: 'string'}  ,  
			            {name: 'path',  type: 'string'}  ,  
			            {name: 'attributes',  type: 'json'}  
			        ], 
			        proxy: {  
			              type: 'direct',  
			              api:{  
				            read: KnowledgeAction.loadKnowledgeType,  
				            create: KnowledgeAction.addKnowledgeType,  
				            destroy: KnowledgeAction.deleteKnowledgeType,  
				            update: KnowledgeAction.alterKnowledgeType 
			              },
			              //paramOrder:["node"],
			              extraParams: {
			                  parentId: "",
				              id: ""
			              },  
			              reader:{  
			                messageProperty:"Msg",  
			              }
			          } 
			    });  

			  //KnowledgeAction.loadKnowledgeType
			  //KnowledgeAction.saveKnowledgeType
			  //KnowledgeAction.alterKnowledgeType
			    var store = Ext.create('Ext.data.TreeStore',{  
			        model: 'Task',  
			     //   proxy: {  
			     //       type: 'ajax',  
			     //       url: 'data/knowledgeTypeTree.json'    //����Ҫ����������ſ��Է���json����Ȼ�ᱨ���ܾ�����  
			     //   },  
			        reader: {  
			            type: 'json'  
			        },  
			        root: {  
			            text: 'root',  
			            id: '0',  
			        }  
			    });  
			    Ext.define('Knowledge',{
			        extend: 'Ext.data.Model',
			        fields: [ 'caption',  'content' ,
						        'id'
						    ]
			    });

			    // create the Data Store
			    var gridstore = Ext.create('Ext.data.Store', {
			        model: 'Knowledge', 
			        proxy: {
			            type: 'direct',
			            directFn: KnowledgeAction.loadKnowledges,
			            paramOrder: ['parentId']
			         } ,
			        
			        //proxy: {
			            // load using HTTP
			          //  type: 'ajax',
			          //  url: ' ',
			            // the return will be XML, so lets set up a reader
			           // reader: {
			           //     type: 'xml',
			                // records will have an "Item" tag
			           //     record: 'knowledge',
			           //     idProperty: 'id',
			           //     totalRecords: '@TotalResults'
			          //  }
			       // }, 
			        listeners : {  
			            load : function(store,records){      
			                store.filterBy(function(record,id){         
			                  return true;  
			               });         
			                       
			               }  
			             } 
			    });


			    var columns = [
								{
									"id" : "id",
									"dataIndex":"id",
									"hidden":true
								},{
									"id" : "caption",
									"dataIndex":"caption",
									"text":"֪ʶ��",
									"flex":1,
									"align":"left"
								},{
									"id" : "content",
									"dataIndex":"content",
									"text":"����",
									"flex":4,
									"align":"left",
									renderer:function(value, metadata, record, rowIndex, columnIndex, store) { 
										   // metadata.attr = 'ext:qtip="������:<br/>' +  "<code>"+value+"</code>"+"'"; 
										    return value; 
									}    
								}];
			    var knowledgePanel=Ext.create("Ext.grid.Panel",{
					title:"֪ʶ���б�",
			        width: 540,
			        height: 200,
					region:"center",
					stripeRows: true,
					lineBreak : false,
					cellSelect : true,
					loadMask: {msg:'����װ��...'},
					columns : columns,
					 dockedItems: [{
				            dock: 'top',
				            xtype: 'toolbar',
				            items: ["->",{
				                tooltip: '���֪ʶ��',
				                text: '����',
				                handler: function(){	
				                	var xmlpath=gridstore.proxy.url;
			                		var win=new MyDesktop.KnowledgeWindow({

				                		parentId:gridstore.proxy.extraParams.parentId,
				                		xmlpath:xmlpath,app:myDesktopApp}).createWindow();
			                		
			                		win.on("close",function(){
			                			gridstore.reload();
			                		});
			                		win.on("hide",function(){
			                			gridstore.reload();
			                		});
			                		win.show();
			                	
				                }
				            },{
				                tooltip: '�޸�֪ʶ��',
				                text: '�޸�',
				                handler: function(btn,e){	
				                	var records=knowledgePanel.getSelectionModel().getSelection();

				                	if(records.length>0){
					                	var recordData=records[0].data;

					                	var xmlpath=gridstore.proxy.url;
					                	var win=new MyDesktop.KnowledgeWindow({
					                		parentId:gridstore.proxy.extraParams.parentId,
					                		xmlpath:xmlpath,id:recordData.id,operationFlag:"chg",caption:recordData.caption,content:recordData.content   ,app:myDesktopApp}).createWindow();
					                	win.on("close",function(){
					                		knowledgePanel.getStore().removeAll();
					                		knowledgePanel.getStore().reload();
				                		});
				                		win.on("hide",function(){
					                		knowledgePanel.getStore().removeAll();
				                			knowledgePanel.getStore().reload();
				                		});
				                		win.show();
				                	}
				                }
				            },{
				                tooltip: 'ɾ��֪ʶ��',
				                text: 'ɾ��',
				                handler: function(btn,e){	
				                	var records=knowledgePanel.getSelectionModel().getSelection();

				                	if(records.length>0){
					                	var recordData=records[0].data;
					                	Ext.Msg.show({
					                	     title:'�Ƿ�ɾ��?',
					                	     msg: '��ȷ��Ҫɾ��'+recordData.caption+'��?',
					                	     buttons: Ext.Msg.YESNOCANCEL,
					                	     icon: Ext.Msg.QUESTION,
					                	     fn:function(btn){
													if(btn=="yes"){
								                	KnowledgeAction.deleteKnowledge(recordData,function(result){
								                		knowledgePanel.getStore().removeAll();
							                			knowledgePanel.getStore().reload();
								                	}); 
													}
					                	     }
					                	});
				                	}
				                }
				            },{
				                tooltip: '�鿴֪ʶ��',
				                text: '�鿴',
				                handler: function(btn,e){	
				                	var records=knowledgePanel.getSelectionModel().getSelection();

				                	if(records.length>0){
					                	var recordData=records[0].data;
					                	new MyDesktop.KnowledgeWindow({id:recordData.id,operationFlag:"read",caption:recordData.caption,content:recordData.content,app:myDesktopApp}).createWindow().show();
				                	}
				                }
				            },{
				                tooltip: '����֪ʶ��',
				                text: '����word',
				                handler: function(){
				                	var selectionMode = menuTree.getSelectionModel();  
				                    var selection = selectionMode.getSelection();//��ȡѡ�е�ֵ  
			                		var recordData={};
			                		recordData.parentId=gridstore.proxy.extraParams.parentId;
			                		recordData.wordTitle=selection[0].data.text;
				                	KnowledgeAction.exportAllDocKnowledge(recordData,function(result){
				                		 
				                	}); 
				                }
				            },{
				                tooltip: '����֪ʶ��',
				                text: '����html',
				                handler: function(){
				                	
				                	var selectionMode = menuTree.getSelectionModel();  
				                    var selection = selectionMode.getSelection();//��ȡѡ�е�ֵ  
			                		var recordData={};
			                		recordData.parentId=gridstore.proxy.extraParams.parentId;
			                		recordData.wordTitle=selection[0].data.text;
				                	KnowledgeAction.exportAllHtmlKnowledge(recordData,function(result){
				                		
				                		var jsIframe = document.createElement("iframe");
				            			jsIframe.style.display = "none";//none iframe
				            			document.body.appendChild(jsIframe);
				            			with(window.frames[window.frames.length - 1]){
				            				document.open();
				            				var downloadform=document.createElement("form");
				            				downloadform.action="Download";
				            				downloadform.method="post";
				            				var filename=document.createElement("input");
				            				filename.name="filename";
				            				filename.value="ѧϰ�ܽ�.zip";
				            				downloadform.appendChild(filename);
				            				
				            				document.appendChild(downloadform);
				            				downloadform.submit();
				            				document.close();
				            			} 
				                		 
				                		//window.open('jsppage.do?url=common/download');
				                		return;
				                		Ext.Ajax.request({
				                			//url:'download.action',
				                			url:'jsppage.do?url=common/download',
				                		    
				                			//method:'post',
				                			params:{path:'<%=zipPath%>'}
				                		});
					                	 
				                	}); 
				                }
				            }]
				        }],
					store : gridstore
				  });
			    function rowdblclickFn(grid, rowindex, e){    
			    	grid.getSelectionModel().each(function(rec){     
				        alert(rec.get('caption')); //fieldName����¼�е��ֶ���     
			        });     
			    }
			    knowledgePanel.addListener('itemcontextmenu', itemcontextmenu); 
			    knowledgePanel.addListener('itemdblclick', rowdblclickFn);  
			 // ���õĺ���  
			 function itemcontextmenu(his, record, item, index, e) {    
			     // itemcontextmenu( Ext.view.View this, Ext.data.Model record,  
			     // HTMLElement item, Number index, Ext.EventObject e, Object eOpts )  
			     // ����������Ҽ��˵�   
			     e.preventDefault();    
			     e.stopEvent();// ȡ�������Ĭ���¼�     
			     var array = [ {    
			                 text : '��Ӵ���',    
			                 handler : function() {   
			   
			                 }    
			             }, {    
			                 text : '�޸Ĵ���',    
			                 handler : function() {  
			   
			                 }    
			             }, {    
			                 text : 'ɾ������',    
			                 handler : function() {  
			                     alert(record.raw.id);    
			                 }    
			             }];    
			     var nodemenu = new Ext.menu.Menu({    
			         items : array    
			     });    
			     nodemenu.showAt(e.getXY());// �˵��򿪵�λ��    
			 };  
			    var menuTree = Ext.create('Ext.tree.Panel',{  
			        title: '֪ʶ����',  
			        autoScroll:true,    
			        width: 200,    
			        store: store,  
					region:"west",
				    hideHeaders:true,  
			        rootVisible: false,  

			        viewConfig:{  
			            toggleOnDblClick:false,  
			            plugins: {  
			                ptype: 'treeviewdragdrop'  
			            },  
			            listeners:{  
			                refresh:function(){  
			                    this.select(0);  
			                    this.focus(0);  
			                }  
			            }  
			        },  
			        plugins:[{ptype:"cellediting",  
			        	
			            listeners:{  
			                beforeedit:function(e){  
			                    if(e.record.isRoot()) return false;  
			                }  
			            }  
			        }], 
			        columns:[  
			                 {xtype:"treecolumn",dataIndex:"text",flex:1,  
			                     field:{allowBlank:false}  
			                 }  
			             ], 
			        tbar:[  
			              {text:"����",id:"add",handler:function(){
				               var tree=this.up("treepanel"); 
				            	var selectionMode = tree.getSelectionModel();  
			                    var selection = selectionMode.getSelection()[0];//��ȡѡ�е�ֵ  
			            	  var button=this;
			                 var form_addType=Ext.create("Ext.form.Panel",{
		                          border: false,
		                          fieldDefaults: {
		                              labelWidth: 100
		                          },
		                          defaultType: 'textfield',
		                          bodyPadding: 5,
		                          floatable: false,
		                          items:[
				                          	{xtype:"hidden",name:"parentId",value:selection.data.id},
				                          	{fieldLabel:"��������",name:"parentText",anchor:"100%",readOnly:true,value:selection.data.text},
				                          	{fieldLabel:"��������",name:"text",anchor:"100%"},	,		
				                          	{fieldLabel:"�Ƿ���ڵ����",anchor:"100%",xtype: 'radiogroup',
				                                // Arrange radio buttons into two columns, distributed vertically
				                                columns: 2,
				                                vertical: true,
				                                items: [
				                                    { boxLabel: '��', name: 'isRootChild', inputValue: '0' , checked: true},
				                                    { boxLabel: '��', name: 'isRootChild', inputValue: '1'}
				                                ]},	
				                          	{fieldLabel:"�Ƿ�����ӷ���",anchor:"100%",xtype: 'radiogroup',
				                                // Arrange radio buttons into two columns, distributed vertically
				                                columns: 2,
				                                vertical: true,
				                                items: [
				                                    { boxLabel: '��', name: 'hasChild', inputValue: '0' },
				                                    { boxLabel: '��', name: 'hasChild', inputValue: '1', checked: true}
				                                ]}	                          
		                          ],
		                          bbar:["->",{
		                        	  text:"����",handler:function(){
		                        		 var formValues=form_addType.getForm().getValues();
		                        		 var parentId=formValues["parentId"];
		                        		 var parent=selection;
		                        		 if(formValues["isRootChild"]=="1"){
		                        			 parentId=0;
		                        			 parent=tree.store.tree.root;
		                        		 }
		                        		 
		                        		 var rec=new Task({  
			 				            	    text:formValues["text"], 
			 				            	    leaf:formValues["hasChild"]=="0", 
		 				            	    //id:"sssssss",  ��id��alter����id��create
		 				            	    	parentId:parentId 
		 				            	});  
		 				            	rec.save({ 
		 				            	    parentNode:parent,  
		 				            	    success:function(rec,opt){  
		 				            	        if(opt.parentNode.isExpanded()) { 
		 				            	            opt.parentNode.appendChild(rec);  
		 				            	        }else {
		 				            	            opt.parentNode.expand();  
		 				            	            opt.parentNode.appendChild(rec);  
		 				            	        } 
		 				            	    },  
		 				            	    failure:function(e,op){  
		 				            	        Ext.Msg.alert("��������",op.error);  
		 				            	    },  
		 				            	    scope:tree  
		 				            	}); 
		                        	  }
		                          }]
		                      
			                 });   
			            	 var win = Ext.create('widget.window', {
			                      title: '���ӽڵ�',
			                      closable: true,
			                      closeAction: 'hide',
			                      width: 600,
			                      minWidth: 350,
			                      height: 350,
			                      modal: true,
			                      layout: {
			                          type: 'fit',
			                          padding: 5
			                      },
			                      items: [form_addType]
			                  });
			            	 win.show();
			              }},  
			              {text:"ɾ��",id:"delete",disabled:true,handler:function(){  
				               var tree=this.up("treepanel"); 
				            	var selectionMode = tree.getSelectionModel();  
			                    var selection = selectionMode.getSelection()[0];//��ȡѡ�е�ֵ  
			            	  
			                    var parent=selection.parentNode;
                     		 var rec=new Task({  
				            	        id:selection.data.id
				            	});  
                     		Ext.Msg.show({
		                	     title:'�Ƿ�ɾ��?',
		                	     msg: '��ȷ��Ҫɾ��'+selection.data.text+'��?',
		                	     buttons: Ext.Msg.YESNOCANCEL,
		                	     icon: Ext.Msg.QUESTION,
		                	     fn:function(btn){
										if(btn=="yes"){

							            	rec.destroy({ 
							            	    parentNode:parent,  
							            	    success:function(rec,opt){  
							            	    	console.log(rec,opt);
						            	            opt.parentNode.removeChild(selection);   
							            	    },  
							            	    failure:function(e,op){  
							            	        Ext.Msg.alert("��������",op.error);  
							            	    },  
							            	    scope:tree  
							            	});  
										}
		                	     }
		                	});
			              }},  
			              {text:"�޸�",id:"update",disabled:true,handler:function(){  
			            	  var tree=this.up("treepanel"); 
				            	var selectionMode = tree.getSelectionModel();  
			                    var selection = selectionMode.getSelection()[0];//��ȡѡ�е�ֵ  
			            	  var button=this;
			                 var form_addType=Ext.create("Ext.form.Panel",{
		                          border: false,
		                          fieldDefaults: {
		                              labelWidth: 100
		                          },
		                          defaultType: 'textfield',
		                          bodyPadding: 5,
		                          floatable: false,
		                          items:[
				                          	{xtype:"hidden",name:"id",value:selection.data.id},
				                          	{xtype:"hidden",name:"parentId",value:selection.data.parentId},
				                          	{fieldLabel:"��������",name:"parentText",anchor:"100%",readOnly:true,value:selection.data.text},
				                          	{fieldLabel:"��������",name:"text",anchor:"100%",value:selection.data.text},
				                          	{fieldLabel:"�Ƿ�����ӷ���",anchor:"100%",xtype: 'radiogroup',
				                                // Arrange radio buttons into two columns, distributed vertically
				                                columns: 2,
				                                vertical: true,
				                                items: [
				                                    { boxLabel: '��', name: 'hasChild', inputValue: '0' , checked: selection.data.leaf},
				                                    { boxLabel: '��', name: 'hasChild', inputValue: '1', checked: !selection.data.leaf}
				                                ]}
		                          ],
		                          bbar:["->",{
		                        	  text:"����",handler:function(){
		                        		 console.log( form_addType.getForm().getValues());
		                        		 var formValues=form_addType.getForm().getValues();
		                        		 var parentId=formValues["parentId"];
		                        		 var parent=selection;
		                        		 if(formValues["isRootChild"]=="1"){
		                        			 parentId=0;
		                        			 parent=tree.store.tree.root;
		                        		 }
 
		                        		 var rec=new Task({  
			 				            	    text:formValues["text"], 
			 				            	    leaf:formValues["hasChild"]=="0", 
		 				            	        id:selection.data.id, // ��id��alter����id��create
		 				            	    	parentId:parentId 
		 				            	});  
 
		 				            	rec.save({ 
		 				            	    parentNode:parent,  
		 				            	    success:function(rec,opt){  
		 				            	    	opt.parentNode.set('text', rec.data.text);
		 				            	    	opt.parentNode.set('leaf', rec.data.leaf);
		 				            	    	opt.parentNode.set('parentId', rec.data.parentId);
		 				            	    	opt.parentNode.commit();
		 				            	    },  
		 				            	    failure:function(e,op){  
		 				            	        Ext.Msg.alert("��������",op.error);  
		 				            	    },  
		 				            	    scope:tree  
		 				            	}); 
		                        	  }
		                          }]
		                      
			                 });   
			            	 var win = Ext.create('widget.window', {
			                      title: '���ӽڵ�',
			                      closable: true,
			                      closeAction: 'hide',
			                      width: 600,
			                      minWidth: 350,
			                      height: 350,
			                      modal: true,
			                      layout: {
			                          type: 'fit',
			                          padding: 5
			                      },
			                      items: [form_addType]
			                  });
			            	 win.show();
			              }},  
			              "|",  
			              {text:"ˢ��",handler:function(){  
			                  this.up("treepanel").store.load();  
			              }}  
			          ],  
			        listeners : {  
			            'itemclick' : function(view,record,itemEl,index,e){
			            	var selectionMode = this.getSelectionModel();  
		                    var modeType = selectionMode.getSelectionMode();//SINGLE, MULTI or SIMPLE  
		                    var selection = selectionMode.getSelection();//��ȡѡ�е�ֵ  
		                    var parentN=selection[0].parentNode;
		                    var dir="";
		                    while(!parentN.data.root){
		                    	dir=parentN.data.text+"/"+dir;
		                    		parentN=parentN.parentNode;
		                    }

			                if(record.data.leaf){ 
			                		gridstore.proxy.extraParams.parentId=record.data.id;
			                	//	gridstore.proxy.url="data/ѧϰ�ܽ�/"+dir.toLowerCase()+record.data.text+".xml";
			                		gridstore.load(record.data.id);
			                }else{  
			                    if(record.data.expanded){  
			                        view.collapse(record);  
			                    }else{  
			                        view.expand(record);  
			                    }  
			                }  
			            } ,  
			            selectionchange:function(view,rs){  
			                Ext.getCmp("delete").setDisabled(rs.length==0); 
			                Ext.getCmp("update").setDisabled(rs.length==0);  
			            }
			        }  
			    });  

			var ui=Ext.create('Ext.Panel', {
				layout:"border",
				items:[menuTree,knowledgePanel]
        	});

			panel.add(ui);
		});
	</script>
</html>
