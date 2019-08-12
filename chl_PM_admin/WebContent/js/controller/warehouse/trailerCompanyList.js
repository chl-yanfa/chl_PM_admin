//车辆列表
$(document).ready(function(){
	//初始化表格数据
	initDateGrid();
	reLoadData(0);
});
//初始化数据表格
function initDateGrid(){
	$("#trailerCompanyList").datagrid({
    	loadMsg : "数据加载中,请稍后……",
        nowrap : true,
        striped : true,
        fit : false,
        fitColumns : true,
        url : URLMODULE.getTowingCompanyList,
        method : 'post',
        autoLoad:false,
        idField : 'id',
        view:tcwOrderInfoView,
        columns : [[
                {title : '公司名称', field : 'companyName', width : 120, align : 'left',align:'center'},
                {title : '联系人', field : 'contactPerson', width : 60, align : 'center'},
                {title : '联系电话', field : 'contactPhone', width : 60,align:'center'},
                {title:'所属区域',field:'areas',width:80,align:'center'},
                {title : '备注', field : 'remark', width : 120,align:'center', formatter:function(value,row,index){
                	if(value){
                        return "<div title='"+value+"' class='textEllipsis'>"+value+"</div>";
                    }else{
                        return '';
                    }
                }},
                {title : '操作', field : 'id', width : 60, align : 'center',formatter:function(value,row,index){
                	var id=value;
                	var optionStr = '';
	            	var editor="editor('"+id+"')";
	            	var deleteRow="deleteRow('"+id+"')";
	            	var styleStr='href="javascript:void(0);" style="font-size: 14px;color: #4EA2FF;"';
                	optionStr += '<a '+styleStr+' onclick="'+editor+'">编辑</a>&nbsp;&nbsp;';
                   	optionStr += '<a '+styleStr+' onclick="'+deleteRow+'">删除</a>&nbsp;&nbsp;';
                   return optionStr;
               }}
              ]],
        toolbar : toolbarType,
        pagination : true,
        singleSelect : true,
        fitStyle : true
    });
	$('#tb-companyName').searchbox({
	   width :150
	});
	$('#tb-contactPerson').searchbox({
		width :150
	});
	$('#tb-contactPhone').searchbox({
	   width :150
	});
	//初始化所属分公司
	$('#tb-areas').combobox({
		multiple : false,
		editable : false,
		width : 150,
		method : 'get',
		url : '../../areas',
		valueField : 'id',
		textField : 'areasName'
	});
	$('#iconSearch').click(function() {
		reLoadData(0);
	});
	$('#iconReset').click(function() {
		$('#tb-areas').combobox('clear');
		$('#tb-companyName').searchbox('clear');
		$('#tb-contactPerson').searchbox('clear');
		$('#tb-contactPhone').searchbox('clear');
		reLoadData(0);
	});
	$('#iconAdd').click(function() {
		addTrailer();
	});
}
//重新加载表格数据
function reLoadData(page){
	var loadType = page==1?"reload":"load";
	$("#trailerCompanyList").datagrid(loadType, {
		areasId: $('#tb-areas').combobox('getValue'),
        companyName: $('#tb-companyName').searchbox('getValue').trim(),
        contactPerson: $('#tb-contactPerson').searchbox('getValue').trim(),
        contactPhone: $('#tb-contactPhone').searchbox('getValue').trim()
    });
}
//重新加载表格数据
function initSubCompany(){
	//初始化所属分公司
	$('#trailerCompany #areasId').combobox({
		multiple : false,
		editable : false,
		width : 300,
		method : 'get',
		url : '../../areas',
		valueField : 'id',
		textField : 'areasName',
		onSelect:function(rec){
			$("#areas").val(rec.areasName);
		}
	});
}
//新增
function addTrailer(){
	$("#trailerCompany input,#trailerCompany textarea").val('');
	$('#trailerCompany #areasId').combobox('clear');
	$('#trailer_DIALOG').dialog({
		title: "新增拖车公司",
		width: 500,
		top:10,
		modal: true,
		href: "../../html/warehouse/addEditTrailer.html",
		buttons:[
			{id:'submitFormBtn', text:'提交', handler:function(){
				addOrEditTrailer("trailer_DIALOG",0);
			}},
			{text:'取消', handler:function(){
				$('#trailer_DIALOG').dialog("close");
			}}
		],
		onLoad: function () {
			initSubCompany();
			checkWord(document.getElementById('remark'),'remark-check',200);
		},
		onClose: function () {
			reLoadData(0);
		}
	});
}
//进入编辑页面-把新增页面的信息带过来
function editor(id) {
	$("#trailerCompany input,#trailerCompany textarea").val('');
	$('#trailerCompany #areasId').combobox('clear');
	$.ajax({type: 'post', url:'../../towingCompany/getTowingCompanyById', dataType: "json",
		data:{id : id},
		success:function(data){
			$('#trailer_DIALOG').dialog({
				title: "编辑拖车公司信息",
				width: 500,
				top:10,
				modal: true,
				href: "../../html/warehouse/addEditTrailer.html",
				buttons:[
					{id:'submitFormBtn', text:'提交', handler:function(){
						addOrEditTrailer("trailer_DIALOG",1);
					}},
					{text:'取消', handler:function(){
						$('#trailer_DIALOG').dialog("close");
					}
				}],
				onLoad: function () {
					initSubCompany();
					$("#trailerCompany").form('load', data.entity);
					checkWord(document.getElementById('remark'),'remark-check',200);
				},
				onClose: function () {
					reLoadData(0);
				}
			});
		},
		error: function(e) {
			$.messager.alert('提示', "编辑拖车公司信息失败");
		}
	});
}
//删除
function deleteRow(id) {
	$.messager.confirm('提示', '确定删除吗?', function(r){
		if (r){
			$.ajax({type: 'post', url:'../../towingCompany/deleteTowingCompany',
				data:{id : id},
				success:function(data){
					if (data.returnCode == "000000") {
						$.messager.alert('提示', "删除成功");
						reLoadData(1);						
					}else{
						$.messager.alert('提示', "删除失败");
					}
				},
				error: function(e) {
					$.messager.alert('提示', "失败");
				}
			});
		}
	});
}
/*
 * 新增/编辑
 * _type:0 新增     1 编辑 
 * */
function addOrEditTrailer(_dialogId, _type) {
	if (!$('#trailerCompany').form("validate")) {
		return;
	}
	var postjson = "";
	postjson = $('#trailerCompany input,#trailerCompany textarea').serialize();
	var msg=_type==0?'新增':'编辑';
	var actionName = "";
	switch (_type) {
	case 0:
		actionName = "addTowingCompany"
		break;
	case 1:
		actionName = "updateTowingCompany"
		break;
	}
	$.ajax({
		type : 'post',
		url : '../../towingCompany/' + actionName,
		dataType : "json",
		data : postjson,
		success : function(data) {
			if (data.returnCode == "000000") {
				$('#' + _dialogId).dialog("close");
				$.messager.alert('提示', msg+"信息成功");
			}else{
				$.messager.alert('提示', msg+"信息失败");
			}
		},
		error : function(e) {
			$.messager.alert('提示', "失败");
		}
	});
}