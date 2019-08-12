function initProcedureHistory(procedureId) {
	$("#procedureHistory").datagrid({
		loadMsg : "数据加载中,请稍后……",
		fitColumns : true,
		nowrap:true,
		autoLoad : false,
		idField : 'id',
		view : tcwOrderInfoView,
		url : '../../procedure/getProcedureHistory',
		queryParams: {
			procedureId: procedureId
		},
		method : 'post',
		columns : [ [
			{field : 'type',title : '操作',width : 50,align : 'center',halign : 'center',formatter : function(value, row,index) {
				if (value == 0) {
					return "入库";
				} else {
					return "出库";
				}
			}},
			{field : 'fileNames',title : '出/入库手续',width : 100,align : 'center',halign : 'center',formatter : function(value, row,index) {
				if (value) {
					return "<div title='"+value+"' class='textEllipsis'>"+ value + "</div>";
				} else {
					return '';
				}
			}},
			{field : 'proposer',title : '申请人',width : 100,align : 'center',halign : 'center'},
			{field : 'createTime',title : '出/入库时间',width : 90,align : 'center',halign : 'center'},
			{field : 'remark',title : '备注',width : 200,align : 'center',halign : 'center'}
		]],
		pagination : true,
		singleSelect : true,
		fitStyle : true

	});
}
function procedureCombobox(_readonly) {
	initAddressPCA('#addProcedure','#province','#city','#area',_readonly);
}

/*手续管理-查看详情*/
function initPProcedureData(_id,procedure){
	/* 出入库历史 */
	initProcedureHistory(procedure.id);
	$("#procedureHistory").datagrid("load",{
		procedureId: procedure.id
	});
	$("#addProcedure").form('load', procedure);
	$("#addProcedure input,#addProcedure textarea").attr("readonly", true);
	if(procedure.procedureFileList!=null&&procedure.procedureFileList.length>0){
		var procedureFileList=procedure.procedureFileList;
		for (var i=0;i<procedureFileList.length;i++) {
			var fileType=procedureFileList[i].fileType;
			var pstate=procedureFileList[i].procedureState;
			var filePath=procedureFileList[i].imgPath;
			var fileId=procedureFileList[i].id;
			var attachmentId=procedureFileList[i].attachmentId;
			if(pstate==0){
				$("input[name='fileTypes'][value='"+fileType+"']").prop("checked", true);
			}
			if(pstate==0){
				$(".addpic").eq(fileType).children("img").attr("src", filePath);
				$(".addpic").eq(fileType).children("input[type='hidden']").val(attachmentId);
				$(".addpic").eq(fileType).children("input[type='file']").remove();
				$('#li-file-' + fileType).show();
			}
		}
	}
	$("#addProcedure input[type='checkbox'],#addProcedure input[type='radio']").click(function(){
		return false;
	});
}
function initProcedureData(_id,procedure){
	/* 出入库历史 */
	$(".procedureHistory-DIV").hide();
	$("#addProcedure").form('load', procedure);
	$("#addProcedure input,#addProcedure textarea").attr("readonly", true);
	if(procedure.procedureFileList!=null&&procedure.procedureFileList.length>0){
		var procedureFileList=procedure.procedureFileList;
		for (var i=0;i<procedureFileList.length;i++) {
			var fileType=procedureFileList[i].fileType;
			var pstate=procedureFileList[i].procedureState;
			var filePath=procedureFileList[i].imgPath;
			var fileId=procedureFileList[i].id;
			var attachmentId=procedureFileList[i].attachmentId;
			if(pstate==0){
				$("input[name='fileTypes'][value='"+fileType+"']").prop("checked", true);
			}
			if(pstate==0){
				$(".addpic").eq(fileType).children("img").attr("src", filePath);
				$(".addpic").eq(fileType).children("input[type='hidden']").val(attachmentId);
				$(".addpic").eq(fileType).children("input[type='file']").remove();
				$('#li-file-' + fileType).show();
			}
		}
	}
	$("#addProcedure input[type='checkbox'],#addProcedure input[type='radio']").click(function(){
		return false;
	});
}
/*手续入库*/
function initAddProcedure(_id,procedure){
	procedureCombobox(false);
	/* 出入库历史 */
	initProcedureHistory(procedure.id);
	$("#procedureHistory").datagrid("load",{
		procedureId: procedure.id
	});
	$("#addProcedure").form('load', procedure);
	
	
	
}